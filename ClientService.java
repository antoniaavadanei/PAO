package com.company;

import java.io.*;

public class ClientService {
    private static ClientService single_instance = null;
    private ClientService() {
    }

    public static  ClientService getInstance() {
        if (single_instance == null)
            single_instance = new ClientService();

        return single_instance;
    }

    public void readClientsFromFile(Service S) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\anton\\IdeaProjects\\ProiectPAO\\client.txt"))) {
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFields = currentLine.split(",");
                Client client = new Client(Integer.parseInt(dataFields[0]),dataFields[1], dataFields[2],Boolean.parseBoolean(dataFields[3]));
                S.registerCustomer(client);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return;
        }
        System.out.println("Successfully read " + S.clientList.size() + " clients!");
    }

    public void writeClientsToFile(Service S) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("client.txt"))) {
            for (Client client : S.clientList) {
                bufferedWriter.write(client.getAge() + "," + client.getLastName()+","+client.getFirstName()+","+client.getHasSubscription().toString().toUpperCase());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
            return;
        }
        System.out.println("Successfully wrote " + S.clientList.size() + " clients!");
        System.out.println();
    }


}
