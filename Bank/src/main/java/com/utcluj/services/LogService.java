package com.utcluj.services;

import com.utcluj.api.contracts.convertors.LogConvertor;
import com.utcluj.api.contracts.dtos.LogDTO;
import com.utcluj.persistence.model.Log;
import com.utcluj.persistence.model.primarykey.LogPrimaryKey;
import com.utcluj.persistence.repository.LogsJPARepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by todericidan on 3/19/2017.
 */

@Service
public class LogService {

    @Autowired
    private LogsJPARepository logsJPARepository;

    public List<LogDTO> getAllLogs() {
        return LogConvertor.toDTO(logsJPARepository.findAll());
    }


    public List<LogDTO> getLogsByUsername(String username) throws Exception {
        List<Log> logs = logsJPARepository.findAll();

        List<Log> logsToBeReturned = new ArrayList<>();

        for(Log log:logs){
            if(log.getId().getUsername().equals(username)){
                logsToBeReturned.add(log);
            }
        }

        if (logs.size() == 0) {
            throw new Exception();
        }

        return LogConvertor.toDTO(logsToBeReturned);
    }

    public List<LogDTO> filterLogsBetweenDatesForUser(String username, Timestamp startDate, Timestamp stopDate)throws Exception {
        List<Log> logs = logsJPARepository.findAll();

        List<Log> logsToBeReturned = new ArrayList<>();

        for(Log log:logs){
            if(log.getId().getUsername().equals(username)){

                if( (log.getId().getLogTime().compareTo(startDate)>0)&&(log.getId().getLogTime().compareTo(stopDate)<0)) {
                    logsToBeReturned.add(log);
                }
            }
        }

        if (logs.size() == 0) {
            throw new Exception();
        }



        return LogConvertor.toDTO(logsToBeReturned);


    }


    public LogDTO addLogs(LogDTO log) throws Exception {

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        Log logToBeCreated = new Log(new LogPrimaryKey(currentTimestamp,log.getUsername()),log.getLogDescription());

        Log newLog = logsJPARepository.save(logToBeCreated);

        return LogConvertor.toDTO(newLog);
    }

    public void deleteLog(String username) throws Exception {

        List<Log> logs = logsJPARepository.findAll();
        List<Log> logsToBeAchieved = new ArrayList<>();

        for(Log log:logs){
            if(log.getId().getUsername().equals(username)){
                logsToBeAchieved.add(log);
            }
        }

        if (logsToBeAchieved.size() == 0) {
            throw new NotFoundException("No employee with that username");
        }

        for(Log log:logsToBeAchieved) {
            if(log.getId().getUsername().equals(username)){
                logsJPARepository.delete(log);
            }
        }
        LogConvertor.toCSV(logsToBeAchieved);

    }



}
