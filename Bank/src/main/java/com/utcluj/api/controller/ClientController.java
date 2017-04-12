package com.utcluj.api.controller;

import com.utcluj.api.contracts.dtos.AccountDTO;
import com.utcluj.api.contracts.dtos.ClientDTO;
import com.utcluj.persistence.model.Account;
import com.utcluj.services.ClientService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by todericidan on 3/21/2017.
 */
@RestController
@RequestMapping("/client")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ClientDTO> findAllAccounts() {
        return clientService.getAllClients();
    }


    @RequestMapping(value="/{ssn}", method = RequestMethod.GET)
    public ResponseEntity<?> getClientBySSN(@PathVariable("ssn") String ssn){
        ClientDTO client = null;
        try {
            client = clientService.getClientBySSN(ssn);
            new ResponseEntity<ClientDTO>(client, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<ClientDTO>(client, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerClient(@RequestBody ClientDTO client) {
        ClientDTO newClient = null;

        try{
            newClient = clientService.createClient(client);
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<ClientDTO>(newClient, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/{ssn}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateClient(@PathVariable("ssn") String ssn,@RequestBody ClientDTO client) {
        try {
            clientService.updateClient(ssn,client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/{ssn}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteClient(@PathVariable("ssn") String ssn) {
        try {
            clientService.deleteClient(ssn);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/addaccount/{ssn}",method = RequestMethod.POST)
    public ResponseEntity<?> addAccountToClient(@PathVariable("ssn") String ssn,@RequestBody AccountDTO accountDTO) {
        try {
           ClientDTO client = clientService.addAccountToClient(ssn,accountDTO);
            return new ResponseEntity<ClientDTO>(client,HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
