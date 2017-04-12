package com.utcluj.api.contracts.dtos;

import com.utcluj.persistence.model.enums.AccountType;

import java.sql.Timestamp;

/**
 * Created by todericidan on 3/21/2017.
 */
public class AccountDTO {

    private Long accountId;

    private AccountType accountType;

    private String dateOfCreation;

    private float balance;

    public AccountDTO() {
    }

    public AccountDTO(Long accountId, AccountType accountType, String dateOfCreation, float balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.dateOfCreation = dateOfCreation;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}

