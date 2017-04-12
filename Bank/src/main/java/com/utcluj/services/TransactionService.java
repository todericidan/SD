package com.utcluj.services;

import com.utcluj.api.contracts.convertors.TransactionConvertor;
import com.utcluj.api.contracts.dtos.TransactionDTO;
import com.utcluj.persistence.model.Account;
import com.utcluj.persistence.model.Transaction;
import com.utcluj.persistence.model.enums.TransactionOperationType;
import com.utcluj.persistence.repository.AccountJPARepository;
import com.utcluj.persistence.repository.TransactionJPARepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by todericidan on 3/23/2017.
 */
@Service
public class TransactionService {

    @Autowired
    private TransactionJPARepository transactionJPARepository;

    @Autowired
    private AccountJPARepository accountJPARepository;

    public List<TransactionDTO> getAllTransactions() {
        return TransactionConvertor.toDTO(transactionJPARepository.findAll());
    }

    public List<TransactionDTO> getTransactionById(Long id) throws Exception {
        List<Transaction> transactions = transactionJPARepository.findAll();

        if (transactions.size() == 0) {
           // throw new Exception("no transactions");
        }
        List<Transaction> transactionsToBeReturned= new ArrayList<Transaction>();
        for(Transaction t :transactions){
            if(t.getAccountID()==id){
                transactionsToBeReturned.add(t);
            }
        }
        return TransactionConvertor.toDTO(transactionsToBeReturned);
    }

    public TransactionDTO addTransferTransaction(TransactionDTO transaction, Long id1, Long id2) throws Exception {
        Account accountFrom = accountJPARepository.findOne(id1);
        Account accountTo = accountJPARepository.findOne(id2);

        if(transaction.getAmount()<0){
            throw new NotFoundException("Amount negative");
        }

        if(accountFrom.getBalance()-transaction.getAmount()<0){
            throw new NotFoundException("Amount no supported by the account");
        }else if (!transaction.getOperationType().equals(TransactionOperationType.TRANSFER)){
            throw new NotFoundException("Not a transfer type");
        }
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        Transaction transactionFrom = new Transaction(transaction.getAccountID(),
                currentTimestamp,
                transaction.getOperationType(),
                transaction.getAmount(),
                accountFrom.getBalance(),
                accountFrom.getBalance() - transaction.getAmount(),
                "Tranfer money to "+id2);

        accountFrom.setBalance(accountFrom.getBalance() - transaction.getAmount());

        Transaction transactionTo = new Transaction(id2,
                currentTimestamp,
                transaction.getOperationType(),
                transaction.getAmount(),
                accountTo.getBalance(),
                accountTo.getBalance() + transaction.getAmount(),
                "Receive money from account "+id1);

        accountTo.setBalance(accountTo.getBalance() + transaction.getAmount());

        transactionJPARepository.save(transactionFrom);
        transactionJPARepository.save(transactionTo);
        accountJPARepository.save(accountFrom);
        accountJPARepository.save(accountTo);

        return TransactionConvertor.toDTO(transactionFrom);
    }

    public TransactionDTO payUtilityBill(TransactionDTO transaction) throws Exception{
        Account accountFrom = accountJPARepository.findOne(transaction.getAccountID());
        Transaction transactionToBeCreated;

        if(transaction.getAmount()<0){
            throw new NotFoundException("Amount negative");
        }

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        if(accountFrom.getBalance() - transaction.getAmount()<0){
            throw new NotFoundException("Amount no supported by the account");
        }else if(transaction.getOperationType().equals(TransactionOperationType.UTILITYBILLPAYMENT)) {
            transactionToBeCreated = new Transaction(transaction.getAccountID(),
                    currentTimestamp,
                    transaction.getOperationType(),
                    transaction.getAmount(),
                    accountFrom.getBalance(),
                    accountFrom.getBalance() - transaction.getAmount(),
                    transaction.getDescription());
            accountFrom.setBalance(accountFrom.getBalance() - transaction.getAmount());

            Transaction newTransaction = transactionJPARepository.save(transactionToBeCreated);
            accountJPARepository.save(accountFrom);

            return TransactionConvertor.toDTO(newTransaction);

        } else {
            throw new NotFoundException("Not a utility payment");
        }

    }

    public TransactionDTO  addSingleCoupledTransaction(TransactionDTO transaction) throws Exception {
        Account account = accountJPARepository.findOne(transaction.getAccountID());

        if(transaction.getAmount()<0){
            throw new NotFoundException("Amount negative");
        }

        if (account == null) {
            throw new NotFoundException("No account with that id");
        }

        Transaction transactionToBeCreated;

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        if(transaction.getOperationType().equals(TransactionOperationType.DEPOSIT)) {
            transactionToBeCreated = new Transaction(transaction.getAccountID(),
                    currentTimestamp,
                    transaction.getOperationType(),
                    transaction.getAmount(),
                    account.getBalance(),
                    account.getBalance() + transaction.getAmount(),
                    transaction.getDescription());
            account.setBalance(account.getBalance() + transaction.getAmount());

        } else if (transaction.getOperationType().equals(TransactionOperationType.WITHDRAWAL)) {
            if(account.getBalance() - transaction.getAmount()<0) {
                throw new NotFoundException("Amount no supported by the account");
            }else {
                transactionToBeCreated = new Transaction(transaction.getAccountID(),
                        currentTimestamp,
                        transaction.getOperationType(),
                        transaction.getAmount(),
                        account.getBalance(),
                        account.getBalance() - transaction.getAmount(),
                        transaction.getDescription());
                account.setBalance(account.getBalance() - transaction.getAmount());
            }
        } else {
            throw new NotFoundException("No such type of simple Trans");
        }

        Transaction newTransaction = transactionJPARepository.save(transactionToBeCreated);
        accountJPARepository.save(account);

       return TransactionConvertor.toDTO(newTransaction);
    }

    public void deleteTransaction(Long id) throws Exception {
        Transaction transaction = transactionJPARepository.findOne(id);
        if (transaction == null) {
            throw new NotFoundException("No transaction with that id");
        }

        transactionJPARepository.delete(id);
        TransactionConvertor.toCSV(transaction);

    }

}
