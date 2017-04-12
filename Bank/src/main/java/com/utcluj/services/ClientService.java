package com.utcluj.services;

import com.utcluj.api.contracts.convertors.AccountConvertor;
import com.utcluj.api.contracts.convertors.ClientConvertor;
import com.utcluj.api.contracts.dtos.AccountDTO;
import com.utcluj.api.contracts.dtos.ClientDTO;
import com.utcluj.persistence.model.Account;
import com.utcluj.persistence.model.Client;
import com.utcluj.persistence.repository.AccountJPARepository;
import com.utcluj.persistence.repository.ClientJPARepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by todericidan on 3/21/2017.
 */
@Service
public class ClientService {

    @Autowired
    private ClientJPARepository clientJPARepository;

    @Autowired
    private AccountJPARepository accountJPARepository;


    public List<ClientDTO> getAllClients() {
        return ClientConvertor.toDTO((clientJPARepository.findAll()));
    }

    public ClientDTO getClientBySSN(String ssn) throws Exception {
        Client client = clientJPARepository.findOne(ssn);
        if (client == null) {
            throw new NotFoundException("No client with that ssn");
        }
        return ClientConvertor.toDTO(client);
    }


    public List<AccountDTO> getClientAccounts(String ssn) throws Exception {
        Client client = clientJPARepository.findOne(ssn);
        if (client == null) {
            throw new NotFoundException("No client with that ssn");
        }

        if(client.getAccountsList().size()==0){
            throw new NotFoundException("Client has no accounts");
        }
        return AccountConvertor.toDTO(client.getAccountsList());
    }



    public ClientDTO createClient(ClientDTO clientDTO) throws Exception {
        if(isEmail(clientDTO.getEmail())) {
            if(clientDTO.getSSN().length()==13) {
                if(checkSsn(clientDTO.getSSN())) {
                    Client toBeCreated = new Client(clientDTO.getSSN(), clientDTO.getFirstName(),
                            clientDTO.getLastName(),
                            clientDTO.getIdentityCardNumber(),
                            clientDTO.getAddress(),
                            clientDTO.getEmail());
                    Client newClient = clientJPARepository.save(toBeCreated);

                    return ClientConvertor.toDTO(newClient);
                }
                else{
                    throw new NotFoundException("SSn is invalid");

                }
            }
            else{
                throw new NotFoundException("SSn is not of size 13");
            }
        }
        else{
            throw new NotFoundException("Email is invalid");
        }
    }

    public void updateClient(String ssn, ClientDTO newUserData) throws Exception {
        Client client = clientJPARepository.findOne(ssn);
        if (client == null) {
            throw new NotFoundException("No client with that ssn");
        }

        if(isEmail(client.getEmail())) {
            client.setSSN(ssn);
            client.setFirstName(newUserData.getFirstName());
            client.setLastName(newUserData.getLastName());
            client.setAddress(newUserData.getAddress());
            client.setIdentityCardNumber(newUserData.getIdentityCardNumber());
            client.setEmail(newUserData.getEmail());
            //client.setAccountsList(newUserData.getAccountList());
            clientJPARepository.save(client);
        }
        else{
            throw new NotFoundException("Email is invalid");
        }
    }

    public void deleteClient(String ssn) throws Exception {
        Client client= clientJPARepository.findOne(ssn);
        if (client == null) {
            throw new NotFoundException("No client with that ssn");
        }

        if(client.getAccountsList().size()==0){
            //throw new NotFoundException("Client has no accounts");
        } else {
            for (Account account : client.getAccountsList()) {
                AccountConvertor.toCSV(account);
                accountJPARepository.delete(account);
            }
        }
        ClientConvertor.toCSV(client);
        clientJPARepository.delete(client);
    }

    public ClientDTO addAccountToClient(String ssn,AccountDTO account) throws Exception {
        Client client = clientJPARepository.findOne(ssn);
        if (client == null) {
            throw new NotFoundException("No client with that ssn");
        }

        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        Account accountToBeAdded = new Account(account.getAccountType(),currentTimestamp,account.getBalance());
        client.addAccount(accountToBeAdded);
        accountJPARepository.save(accountToBeAdded);
        //clientJPARepository.save(client);

       // Account newAccount = accountJPARepository.save(accountToBeAdded);

        return ClientConvertor.toDTO(client);

    }


    public boolean checkSsn(String ssn){
        Pattern pattern;
        Matcher matcher;
        String SSN_PATTERN="^[1-2]+[0-9]+[0-9]+[0-1]+[0-9]+[0-3]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]$";

        pattern = Pattern.compile(SSN_PATTERN);

        matcher = pattern.matcher(ssn);
        return matcher.matches();
    }


    public boolean isEmail(String email){
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);

        matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
