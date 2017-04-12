package com.utcluj.api.contracts.convertors;

import com.utcluj.api.contracts.dtos.AccountDTO;
import com.utcluj.persistence.model.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by todericidan on 3/21/2017.
 */
public class AccountConvertor {

    private static final String CSV_SEPARATOR = ",";

    public static void toCSV(Account account){
        try
        {
            FileWriter fw = new FileWriter("Archieved_accounts.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuffer oneLine = new StringBuffer();
            oneLine.append(account.getAccountId());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(account.getAccountType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(account.getBalance());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(account.getDateOfCreation());
            bw.write(oneLine.toString());
            bw.newLine();

            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }


    public static AccountDTO toDTO(Account model){
        AccountDTO dto = null;

        if(model != null){
            dto = new AccountDTO(model.getAccountId(),model.getAccountType(),model.getDateOfCreation().toString(),model.getBalance());
        }

        return dto;
    }

    public static List<AccountDTO> toDTO(List<Account> models){
        List<AccountDTO> dtos = new ArrayList<>();
        if ((models!=null) && (!models.isEmpty())){
            for (Account model : models){
                dtos.add(toDTO(model));
            }
        }

        return dtos;
    }


}
