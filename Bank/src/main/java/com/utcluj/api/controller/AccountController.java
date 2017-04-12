package com.utcluj.api.controller;

import com.utcluj.api.contracts.dtos.AccountDTO;
import com.utcluj.persistence.model.Account;
import com.utcluj.services.AccountService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by todericidan on 3/21/2017.
 */
@RestController
@RequestMapping("/account")
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDTO> findAllAccounts() {
        return accountService.getAllAccounts();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountByID(@PathVariable("id") Long id){
        AccountDTO account = null;
        try {
           account = accountService.getAccountById(id);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<AccountDTO>(account, HttpStatus.CREATED);
    }




    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> registerAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO newAccount = null;

        if(accountDTO.getBalance()<0){
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        try{
            newAccount = accountService.addAccount(accountDTO);
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<AccountDTO>(newAccount, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@PathVariable("id") Long id,@RequestBody AccountDTO account) {
        try {
            accountService.updateAccount(id, account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid data", HttpStatus.BAD_REQUEST);
        }
    }



    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
        try {
            accountService.deleteAccount(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
