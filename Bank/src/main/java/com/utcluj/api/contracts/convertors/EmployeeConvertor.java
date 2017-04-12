package com.utcluj.api.contracts.convertors;

import com.utcluj.api.contracts.dtos.EmployeeDTO;
import com.utcluj.persistence.model.Employee;
import com.utcluj.persistence.model.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by todericidan on 3/18/2017.
 */
public class EmployeeConvertor {

    private static final String CSV_SEPARATOR = ",";

    public static void toCSV(Employee model){
        try
        {
            FileWriter fw = new FileWriter("Archieved_employees.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

                StringBuffer oneLine = new StringBuffer();
                oneLine.append(model.getUsername());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(model.getPassword());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(model.getIsAdmin());
                bw.write(oneLine.toString());
                bw.newLine();

            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }


    public static void toCSV(List<Employee> models){
       for(Employee model :models){
           toCSV(model);
       }
    }

    public static EmployeeDTO toDTO(Employee model){
        EmployeeDTO dto = null;

        if(model != null){
            dto = new EmployeeDTO(model.getUsername(),model.getPassword(),model.getIsAdmin());
        }

        return dto;

    }

    public static List<EmployeeDTO> toDTO(List<Employee> models){
        List<EmployeeDTO> dtos = new ArrayList<>();

        if ((models!=null) && (!models.isEmpty())){
            for (Employee model : models){
                dtos.add(toDTO(model));
            }
        }

        return dtos;
    }
}
