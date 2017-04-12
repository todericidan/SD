package com.utcluj.persistence.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by todericidan on 3/20/2017.
 */
@Entity
@CrossOrigin
@Table(name = "clients")
public class Client {

    @Id
    //@Size(min=13,max=13)
    @Column(name = "ssn")
    private String SSN;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;


    @Column(name = "identity_card_number")
    //@Size(min=8,max=8)
    private String identityCardNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @OneToMany
    @JoinColumn(name="client_ssn")
    private List<Account> accountsList = new ArrayList<>();


    public Client() {
    }

    public Client(String SSN, String firstName, String lastName, String identityCardNumber, String address, String email) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.email = email;

    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public void setAccountsList(List<Account> accountsList) {
        this.accountsList = accountsList;
    }

    public void addAccount(Account account){
        this.accountsList.add(account);
    }
}
