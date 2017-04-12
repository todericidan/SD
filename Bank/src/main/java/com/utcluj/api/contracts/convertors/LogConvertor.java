package com.utcluj.api.contracts.convertors;

import com.utcluj.api.contracts.dtos.LogDTO;
import com.utcluj.persistence.model.Log;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by todericidan on 3/19/2017.
 */
public class LogConvertor {

    private static final String CSV_SEPARATOR = ",";

    public static void toCSV(Log log){
        try
        {
            FileWriter fw = new FileWriter("Archieved_logs.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuffer oneLine = new StringBuffer();
            oneLine.append(log.getId().getUsername());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(log.getId().getLogTime());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(log.getLogDescription());
            bw.write(oneLine.toString());
            bw.newLine();

            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }


    public static void toCSV(List<Log> logs){
        for (Log log : logs)
        {
            toCSV(log);
        }
    }

    public static LogDTO toDTO(Log model){
        LogDTO dto = null;

        if(model != null){
            dto = new LogDTO(model.getId().getLogTime().toString(),model.getId().getUsername(),model.getLogDescription());
        }

        return dto;
    }

    public static List<LogDTO> toDTO(List<Log> models){
        List<LogDTO> dtos = new ArrayList<>();

        if ((models!=null) && (!models.isEmpty())){
            for (Log model : models){
                dtos.add(toDTO(model));
            }
        }

        return dtos;
    }




}
