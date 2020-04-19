package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

public class AuditService {
    private static AuditService movie_instance = null;
    private AuditService() {
    }

    public static AuditService getInstance() {
        if (movie_instance == null)
            movie_instance = new AuditService();

        return movie_instance;
    }
    public void writeActionToFile(Integer i) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.txt",true))) {
            Date date = new Date();
            switch(i) {
                case 0:
                    bufferedWriter.write("Added a movie,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 1:
                    bufferedWriter.write("Added a customer,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 2:
                    bufferedWriter.write("Sold a ticket,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 3:
                    bufferedWriter.write("Checked available tickets for a movie,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 4:
                    bufferedWriter.write("Checked if a customer is registered,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 5:
                    bufferedWriter.write("Made subscription for a customer,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 6:
                    bufferedWriter.write("Displayed all movies,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 7:
                    bufferedWriter.write("Displayed movies from a specific category,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 8:
                    bufferedWriter.write("Displayed customers,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 9:
                    bufferedWriter.write("Displayed sold tickets,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                case 10:
                    bufferedWriter.write("Deleted a client,"+new Timestamp(date.getTime()));
                    bufferedWriter.newLine();
                    break;
                default:
                    break;

            }

        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }

    }


}
