package com.company;

import java.util.Comparator;

public class ClientComparator implements Comparator<Client> {


    @Override
    public int compare(Client client, Client t1) {
        return client.getLastName().compareTo(t1.getLastName());
    }
}
