package com.utcluj.persistence.model;

import com.utcluj.persistence.model.enums.AccountType;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by todericidan on 3/20/2017.
 */
@Entity
@Table(name= "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "date_of_creation")
    private Timestamp dateOfCreation;

    @Column(name = "balance")
    private float balance;

    public Account() {
    }

    public Account(AccountType accountType, Timestamp dateOfCreation, float balance) {
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

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
