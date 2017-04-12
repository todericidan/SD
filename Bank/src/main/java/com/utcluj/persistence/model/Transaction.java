package com.utcluj.persistence.model;

import com.utcluj.persistence.model.enums.TransactionOperationType;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by todericidan on 3/22/2017.
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long transactionID;

    @Column(name = "account_id")
    private Long accountID;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "type_of_transaction")
    @Enumerated(EnumType.STRING)
    private TransactionOperationType operationType;

    @Column(name = "amount")
    private float amount;

    @Column(name = "initial_balance")
    private float initialBalance;

    @Column(name = "closing_balance")
    private float closingBalance;

    @Column(name = "transaction_description")
    private String description;

    public Transaction() {
    }

    public Transaction(Long accountID,Timestamp creationTime, TransactionOperationType operationType, float amount, float initialBalance, float closingBalance, String description) {
        this.accountID =accountID;
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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
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
