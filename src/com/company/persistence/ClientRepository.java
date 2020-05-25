package com.company.persistence;

import com.company.connection.DatabaseConnection;
import com.company.person.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository {

    private static ClientRepository instance;

    private static final String INSERT_CLIENT = "INSERT INTO clients (id, lastName, firstName, age, hasSubscription) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_CLIENT = "SELECT * FROM clients WHERE id = ?";
    private static final String UPDATE_CLIENT_AGE = "UPDATE clients SET age = ? WHERE id = ?";
    private static final String UPDATE_CLIENT_SUBSCRIPTION = "UPDATE clients SET hasSubscription = ? WHERE id = ?";
    private static final String DELETE_CLIENT = "DELETE FROM clients WHERE id=?";

    private ClientRepository() {
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }

        return instance;
    }

    public void saveClient(Client client) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_CLIENT)) {
            statement.setInt(1, client.getId());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getFirstName());
            statement.setInt(4, client.getAge());
            statement.setBoolean(5, client.getHasSubscription());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new client was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new client: " + e.getMessage());
            new Client();
        }
    }

    public Client findClient(Integer id) {
        Client client = new Client();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_CLIENT)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find client: Client was not found!");
                    return client;
                }

                System.out.println("Client was found!");
                client.setId(result.getInt("id"));
                client.setLastName(result.getString("lastName"));
                client.setFirstName(result.getString("firstName"));
                client.setAge(result.getInt("age"));
                client.setHasSubscription(result.getBoolean("hasSubscription"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find client: " + e.getMessage());
        }
        return client;
    }

    public void updateClientAge(Client client) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_CLIENT_AGE)) {
            statement.setInt(2, client.getId());
            statement.setInt(1, client.getAge());


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update client: " + e.getMessage());
            new Client();
            return;
        }

        System.out.println("Something went wrong when trying to update client: Client was not found!");
        new Client();
    }
    public void updateClientSubscription(Client client) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_CLIENT_SUBSCRIPTION)) {
            statement.setInt(2, client.getId());
            statement.setBoolean(1, client.getHasSubscription());


            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Client was updated successfully!");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update client: " + e.getMessage());
            new Client();
            return;
        }

        System.out.println("Something went wrong when trying to update client: Client was not found!");
        new Client();
    }

    public void deleteClient(Integer id) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(DELETE_CLIENT)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Client was deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete client: " + e.getMessage());
        }

    }
}

