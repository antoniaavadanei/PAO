package com.company.persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public final class Audit {
    private static Audit instance = null;

    private Audit() {}

    public static Audit getInstance() {
        if (instance == null)
            instance = new Audit();

        return instance;
    }
    public void write(ActionType actionType, Date data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(actionType + ", " + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write: " + e.getMessage());
        }
    }
}