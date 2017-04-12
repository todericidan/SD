package com.utcluj.services;

import com.utcluj.api.contracts.convertors.EmployeeConvertor;
import com.utcluj.api.contracts.convertors.LogConvertor;
import com.utcluj.api.contracts.dtos.EmployeeDTO;
import com.utcluj.persistence.model.Employee;
import com.utcluj.persistence.model.Log;
import com.utcluj.persistence.repository.EmployeeJPARepository;
import com.utcluj.persistence.repository.LogsJPARepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by todericidan on 3/18/2017.
 */

@Service
public class EmployeeService {

    @Autowired
    private EmployeeJPARepository employeeJPARepository;

    @Autowired
    private LogsJPARepository logsJPARepository;

    public List<EmployeeDTO> getAllEmployees() {
        return EmployeeConvertor.toDTO(employeeJPARepository.findAll());
    }

    public int getAdminPermissionForUsername(String username) throws Exception {
        Employee employee = employeeJPARepository.findOne(username);

        if (employee == null) {
            throw new Exception();
        }

        return employee.getIsAdmin();
    }

    public boolean checkPasswordForUsername(String username,String password) throws Exception {
        Employee employee = employeeJPARepository.findOne(username);

        if (employee == null) {
            throw new Exception();
        }

        if(employee.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public EmployeeDTO getEmployeeByUsername(String username) throws Exception {
        Employee employee = employeeJPARepository.findOne(username);

        if (employee == null) {
            throw new Exception();
        }

        return EmployeeConvertor.toDTO(employee);
    }

    public EmployeeDTO addEmployee(EmployeeDTO employee) throws Exception {
        Employee employeeToBeCreated = new Employee(employee.getUsername(), employee.getPassword(), employee.getIsAdmin());

        Employee newEmployee = employeeJPARepository.save(employeeToBeCreated);

        return EmployeeConvertor.toDTO(newEmployee);
    }

    public void deleteEmployee(String username) throws Exception {
        Employee employee = employeeJPARepository.findOne(username);
        if (employee == null) {
            throw new NotFoundException("No employee with that username");
        }

        List<Log> logs = logsJPARepository.findAll();
        List<Log> logsToBeArchieved = new ArrayList<>();
        for(Log log:logs) {
            if(log.getId().getUsername().equals(username)){
                logsToBeArchieved.add(log);
                logsJPARepository.delete(log);
            }
        }
        LogConvertor.toCSV(logsToBeArchieved);

        employeeJPARepository.delete(employee);
        EmployeeConvertor.toCSV(employee);
    }

    public void updateEmployee(String username,EmployeeDTO employeeDTO) throws  Exception {
        Employee employee = employeeJPARepository.findOne(username);
        if(employee == null){
            throw new NotFoundException("No employee with that username");
        }

        employee.setIsAdmin(employeeDTO.getIsAdmin());
        employee.setPassword(employeeDTO.getPassword());
        employeeJPARepository.save(employee);
    }


}
