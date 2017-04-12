package com.utcluj.api.controller;

import com.utcluj.api.contracts.dtos.EmployeeDTO;

import com.utcluj.persistence.model.Employee;
import com.utcluj.services.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by todericidan on 3/18/2017.
 */

@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeDTO> findAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value="/login/{username}/{password}", method = RequestMethod.POST)
    public ResponseEntity<?> logIn(@PathVariable("username") String username,@PathVariable("password") String password){
        try {
            EmployeeDTO employee;
            if(employeeService.checkPasswordForUsername(username,password)){
                employee = employeeService.getEmployeeByUsername(username);
            }
            else{
                employee = new EmployeeDTO();
            }

            return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);

        }  catch (Exception exception) {
            EmployeeDTO employee= new EmployeeDTO();
            return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
            //return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username){
        try {
            EmployeeDTO employee;
            employee =employeeService.getEmployeeByUsername(username);

            return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);

        }  catch (Exception exception) {
            EmployeeDTO employee= new EmployeeDTO();
            return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDTO employee) {
        EmployeeDTO newEmployee = null;

        try{
            newEmployee = employeeService.addEmployee(employee);
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<EmployeeDTO>(newEmployee, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmployee(@PathVariable("username") String username,@RequestBody EmployeeDTO employee) {
        try {
            employeeService.updateEmployee(username, employee);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{username}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("username") String username) {
        try {
            employeeService.deleteEmployee(username);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
