package com.utcluj.api.contracts.dtos;

import com.utcluj.persistence.model.enums.TransactionOperationType;

import java.sql.Timestamp;

/**
 * Created by todericidan on 3/22/2017.
 */
public class TransactionDTO {

    private Long transactionID;

    private Long accountID;

    private String creationTime;

    private TransactionOperationType operationType;

    private float amount;

    private float initialBalance;

    private float closingBalance;

    private String description;

    public TransactionDTO() {
    }

    public TransactionDTO(Long transactionID, Long accountID, String creationTime, TransactionOperationType operationType, float amount, float initialBalance, float closingBalance, String description) {
        this.transactionID = transactionID;
        this.accountID = accountID;
        this.creationTime = creationTime;
        this.operationType = operationType;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.closingBalance = closingBalance;
        this.description = description;
    }


    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public TransactionOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(TransactionOperationType operationType) {
        this.operationType = operationType;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(float initialBalance) {
        this.initialBalance = initialBalance;
    }

    public float getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(float closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
