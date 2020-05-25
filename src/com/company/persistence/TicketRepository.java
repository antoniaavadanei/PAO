package com.company.persistence;

import com.company.connection.DatabaseConnection;
import com.company.domain.Animation;
import com.company.domain.Ticket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRepository {

    private static TicketRepository instance;

    private static final String INSERT_TICKET = "INSERT INTO `soldTickets` ( movieName,  clientName, price) VALUES (?, ?, ?)";
    private static final String SELECT_TICKETS = "SELECT * FROM `soldTickets`";
    private static final String COUNT_TICKETS = "SELECT * FROM `soldTickets`";

    private TicketRepository() {
    }

    public static TicketRepository getInstance() {
        if (instance == null) {
            instance = new TicketRepository();
        }

        return instance;
    }
    public void saveTicket(Ticket ticket) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_TICKET)) {
            statement.setString(1, ticket.getMovieName());
            statement.setString(2, ticket.getClientName());
            statement.setDouble(3, ticket.getPrice());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new ticket was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new ticket: " + e.getMessage());
            new Animation();
        }
    }
    public int nrTickets() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_TICKETS)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public void printTickets() {
        if (nrTickets() != 0) {
            System.out.println("\n    Tickets registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_TICKETS)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println("MovieName: " + result.getString("movieName")+ ", ClientName " + result.getString("clientName") + ", Price:" + result.getString("price"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered tickets.");
        }
    }
}

