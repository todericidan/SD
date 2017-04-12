package com.utcluj.api.controller;

import com.utcluj.api.contracts.convertors.LogConvertor;
import com.utcluj.api.contracts.dtos.LogDTO;
import com.utcluj.persistence.model.Log;
import com.utcluj.services.LogService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by todericidan on 3/19/2017.
 */

@RestController
@RequestMapping("/log")
@CrossOrigin
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LogDTO> findAllLogs() {
        return logService.getAllLogs();
    }

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public List<LogDTO>getLogsOfUser(@PathVariable("username") String username){
        try {
            List<LogDTO> logs = logService.getLogsByUsername(username);
            if(logs.size() > 0) {
                return logs;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ArrayList<LogDTO>();
    }

    @RequestMapping(value="/{username}/{startdate}/{stopdate}", method = RequestMethod.GET)
    public List<LogDTO>getLogsOfUserFiltered(
            @PathVariable("username") String username,
            @PathVariable("startdate")String startDate,
            @PathVariable("stopdate")String stopDate){

        String[] start= startDate.split("-");

        String[] stop= stopDate.split("-");

        int startYear =  Integer.parseInt(start[0])-1900;
        int  startMonth =  Integer.parseInt(start[1])-1;
        int startDay = Integer.parseInt(start[2]);

        int stopYear = Integer.parseInt(stop[0])-1900;
        int stopMonth = Integer.parseInt(stop[1])-1;
        int  stopDay = Integer.parseInt(stop[2]);

        Timestamp startDateTime = new Timestamp(startYear,startMonth,startDay,0,0,0,0);
        Timestamp stopDateTime = new Timestamp(stopYear,stopMonth,stopDay,0,0,0,0);

        try {
            List<LogDTO> logs = logService.filterLogsBetweenDatesForUser(username,startDateTime,stopDateTime);


            if(logs.size() > 0) {
                return logs;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ArrayList<LogDTO>();
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerLog(@RequestBody LogDTO log) {
       LogDTO newLog = null;

        try{
            newLog = logService.addLogs(log);
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<LogDTO>(newLog, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/{username}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("username") String username) {
        try {
            logService.deleteLog(username);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




}
