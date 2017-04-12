package com.utcluj.services;

import com.utcluj.api.contracts.convertors.AccountConvertor;
import com.utcluj.api.contracts.dtos.AccountDTO;
import com.utcluj.persistence.model.Account;
import com.utcluj.persistence.repository.AccountJPARepository;
import com.utcluj.persistence.repository.ClientJPARepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by todericidan on 3/21/2017.
 */
@Service
public class AccountService {

    @Autowired
    private AccountJPARepository accountJPARepository;


    public List<AccountDTO> getAllAccounts() {
        return AccountConvertor.toDTO(accountJPARepository.findAll());
    }

    public AccountDTO getAccountById(Long id){
        return AccountConvertor.toDTO(accountJPARepository.findOne(id));
    }

    public AccountDTO addAccount(AccountDTO account) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        Account accountToBeCreated = new Account(account.getAccountType(),currentTimestamp,account.getBalance());
        Account newAccount = accountJPARepository.save(accountToBeCreated);

        if(account.getBalance() < 0.0f){
            throw new NotFoundException("Invalid balance");
        }

        return AccountConvertor.toDTO(newAccount);
    }




    public void deleteAccount(Long id) throws Exception {
        Account account = accountJPARepository.findOne(id);
        if (account == null) {
            throw new NotFoundException("No account with that id");
        }
        accountJPARepository.delete(account);
        AccountConvertor.toCSV(account);

    }

    public void updateAccount(Long id ,AccountDTO accountDTO) throws  Exception {
        Account account = accountJPARepository.findOne(id);
        if(account == null){
            throw new NotFoundException("No account with that id");
        }

        if(accountDTO.getBalance() < 0.0f){
            throw new NotFoundException("Invalid balance");
        }

        account.setBalance(accountDTO.getBalance());
        account.setAccountType(accountDTO.getAccountType());

        accountJPARepository.save(account);
    }


}
