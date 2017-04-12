package com.utcluj.api.contracts.convertors;



import com.utcluj.api.contracts.dtos.TransactionDTO;
import com.utcluj.persistence.model.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by todericidan on 3/22/2017.
 */
public class TransactionConvertor {
    private static final String CSV_SEPARATOR = ",";

    public static void toCSV(Transaction model){
        try
        {
            FileWriter fw = new FileWriter("Archieved_transactions.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuffer oneLine = new StringBuffer();
            oneLine.append(model.getAccountID());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(model.getOperationType());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(model.getAmount());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(model.getCreationTime());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(model.getInitialBalance());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(model.getClosingBalance());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(model.getDescription());
            bw.write(oneLine.toString());
            bw.newLine();

            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }

    public static void toCSV(List<Transaction> models){
        for(Transaction model :models){
            toCSV(model);
        }
    }

    public static TransactionDTO toDTO(Transaction model){
        TransactionDTO dto = null;

        if(model != null){
            dto = new TransactionDTO(model.getTransactionID(),
                    model.getAccountID(),
                    model.getCreationTime().toString(),
                    model.getOperationType(),
                    model.getAmount(),
                    model.getInitialBalance(),
                    model.getClosingBalance(),
                    model.getDescription());
        }

        return dto;

    }

    public static List<TransactionDTO> toDTO(List<Transaction> models){
        List<TransactionDTO> dtos = new ArrayList<>();

        if ((models!=null) && (!models.isEmpty())){
            for (Transaction model : models){
                dtos.add(toDTO(model));
            }
        }

        return dtos;
    }
}
