package com.utcluj.api.controller;

import com.utcluj.api.contracts.convertors.TransactionConvertor;
import com.utcluj.api.contracts.dtos.TransactionDTO;
import com.utcluj.persistence.model.Transaction;
import com.utcluj.services.TransactionService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by todericidan on 3/25/2017.
 */
@RestController
@RequestMapping("/trans")
@CrossOrigin
public class TransferController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TransactionDTO> findAllEmployees() {
        return transactionService.getAllTransactions();
    }


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccountByAccountId(@PathVariable("id") Long id){
        List<TransactionDTO> transactions = null;
        try {
           transactions = transactionService.getTransactionById(id);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
    }

    @RequestMapping(value="/payBill",method = RequestMethod.POST)
    public ResponseEntity<?> payUtilityBill(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO newTransaction = null;
        try{
            newTransaction = transactionService.payUtilityBill(transactionDTO);
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TransactionDTO>(newTransaction, HttpStatus.CREATED);
    }

    @RequestMapping(value="/simpletransaction",method = RequestMethod.POST)
    public ResponseEntity<?> addSimpleTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO newTransaction = null;

        try{
            newTransaction = transactionService.addSingleCoupledTransaction(transactionDTO);
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TransactionDTO>(newTransaction, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/transfer/{id1}/{id2}", method = RequestMethod.POST)
    public ResponseEntity<?> addTransfer(@PathVariable("id1") Long id1,@PathVariable("id2") Long id2,@RequestBody TransactionDTO transaction) {
        TransactionDTO newTransaction = null;

        try{
            newTransaction = transactionService.addTransferTransaction(transaction,id1,id2);
        } catch (Exception e) {
            return new ResponseEntity<String>("Invalid data", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TransactionDTO>(newTransaction, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTransaction(@PathVariable("id") Long id) {
        try {
            transactionService.deleteTransaction(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }





}
