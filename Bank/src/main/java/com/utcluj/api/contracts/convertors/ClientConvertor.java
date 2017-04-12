package com.utcluj.api.contracts.convertors;

import com.utcluj.api.contracts.dtos.ClientDTO;
import com.utcluj.persistence.model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by todericidan on 3/21/2017.
 */
public class ClientConvertor {

    private static final String CSV_SEPARATOR = ",";

    public static void toCSV(Client client){
        try
        {
            FileWriter fw = new FileWriter("Archieved_clients.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

            StringBuffer oneLine = new StringBuffer();
            oneLine.append(client.getSSN());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(client.getFirstName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(client.getLastName());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(client.getAddress());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(client.getIdentityCardNumber());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(client.getEmail());
            bw.write(oneLine.toString());
            bw.newLine();

            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }


    public static ClientDTO toDTO(Client model){
        ClientDTO dto = null;

        if(model != null){
            dto = new ClientDTO(model.getSSN(),model.getFirstName(),model.getLastName(),model.getIdentityCardNumber(),model.getAddress(),model.getEmail(),AccountConvertor.toDTO(model.getAccountsList()));
        }

        return dto;
    }

    public static List<ClientDTO> toDTO(List<Client> models){
        List<ClientDTO> dtos = new ArrayList<>();
        if ((models!=null) && (!models.isEmpty())){
            for (Client model : models){
                dtos.add(toDTO(model));
            }
        }
        return dtos;
    }
}
