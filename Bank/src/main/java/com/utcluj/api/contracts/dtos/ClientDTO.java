package com.utcluj.api.contracts.dtos;

import com.utcluj.persistence.model.Account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by todericidan on 3/21/2017.
 */
public class ClientDTO {

    private String SSN;

    private String firstName;

    private String lastName;

    private String identityCardNumber;

    private String address;

    private String email;

    private List<AccountDTO> accountList  = new ArrayList<>();

    public ClientDTO() {
    }

    public ClientDTO(String SSN, String firstName, String lastName, String identityCardNumber, String address, String email, List<AccountDTO> accountList) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.email = email;
        this.accountList = accountList;
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

    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountDTO> accountList) {
        this.accountList = accountList;
    }

    public void addAccount(AccountDTO account){
        this.accountList.add(account);
    }

}
