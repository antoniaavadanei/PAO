package com.company.service;


import com.company.connection.DatabaseConnection;
import com.company.domain.*;
import com.company.persistence.ClientRepository;
import com.company.person.Client;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ClientService {

    private static ClientService instance;
    private static final String COUNT__CLIENT = "SELECT * FROM `clients`";
    private static final String SELECT_CLIENTS_NAME = "SELECT * FROM `clients` ORDER BY lastName";

    private final ClientRepository clientRepository = ClientRepository.getInstance();
    private final MovieService movieService =MovieService.getInstance();
    private final TicketService ticketService=TicketService.getInstance();
    private ClientService() {
    }

    public static ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }

        return instance;
    }

    public void saveClient() {
        Client client = new Client().readClient();
        client.setId(maxIdClient() + 1);
        clientRepository.saveClient(client);
    }

    public Client findClient(Integer id) {
        return clientRepository.findClient(id);
    }

    public void updateClientSubscription(Client client) {
        System.out.println("Make subscription (TRUE)/n " +
                "Cancel subscription(FALSE):");
        Scanner myObj = new Scanner(System.in);
        Boolean hasSubscription = myObj.nextBoolean();
        client.setHasSubscription(hasSubscription);
        clientRepository.updateClientSubscription(client);
    }

    public void updateClientAge(Client client) {
        System.out.println("Type client's age:");
        Scanner myObj = new Scanner(System.in);
        Integer age = myObj.nextInt();
        client.setAge(age);
        clientRepository.updateClientAge(client);
    }


    public void deleteClient(Client client) {
        clientRepository.deleteClient(client.getId());
    }

    public int maxIdClient() {
        int max = -1;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT__CLIENT)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    if (max < result.getInt("id"))
                        max = result.getInt("id");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return max;
    }

    public void printClients() {
        if (nrClients() != 0) {
            System.out.println("Clients registered:");
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_CLIENTS_NAME)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("id")+ " " + result.getString("lastName") + ", " + result.getString("firstName")+ ", Age: " + result.getString("age")+ ", HasSubscription " + result.getString("hasSubscription"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("There is no registered client.");
        }
    }

    public int nrClients() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT__CLIENT)) {
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

    public void buyTicket() {
        System.out.println("Buy ticket for what category?:\n" +
                "\tComedy->Type  0 \n" +
                "\tAnimation->Type  1  \n" +
                "\tDrama->Type  2  \n" +
                "\tHorror->Type  3  \n" +
                "\tRomantic->Type  4  \n" +
                "\tThriller->Type  5\n");
        Scanner obj = new Scanner(System.in);
        int type = obj.nextInt();
        switch (type) {
            case 0: {
                printClients();
                System.out.println("Client's id:");
                Integer idClient = obj.nextInt();
                Client client=findClient(idClient);

                System.out.println("Movies from this category are:");
                movieService.printComedies();

                System.out.println("Chose id of movie:");
                Scanner obj1 = new Scanner(System.in);
                Integer id = obj1.nextInt();
                Comedie comedie = movieService.findComedie(id);
                movieService.updateComedie(comedie);

                Double price= comedie.calculatedPrice(client);
                ticketService.saveTicket(new Ticket(price,comedie.getMovieName(),client.getLastName()));
                System.out.println("price= "+ price);
                break;
            }
            case 1: {
                printClients();
                System.out.println("Client's id:");
                Integer idClient = obj.nextInt();
                Client client=findClient(idClient);

                System.out.println("Movies from this category are:");
                movieService.printAnimations();

                System.out.println("Chose id of movie:");
                Scanner obj1 = new Scanner(System.in);
                Integer id = obj1.nextInt();
                Animation animation = movieService.findAnimation(id);
                movieService.updateAnimation(animation);

                Double price= animation.calculatedPrice(client);
                ticketService.saveTicket(new Ticket(price,animation.getMovieName(),client.getLastName()));
                System.out.println("price= "+ price);
                break;
            }
            case 2: {
                printClients();
                System.out.println("Client's id:");
                Integer idClient = obj.nextInt();
                Client client=findClient(idClient);

                System.out.println("Movies from this category are:");
                movieService.printDramas();

                System.out.println("Chose id of movie:");
                Scanner obj1 = new Scanner(System.in);
                Integer id = obj1.nextInt();
                Drama drama = movieService.findDrama(id);
                movieService.updateDrama(drama);

                Double price= drama.calculatedPrice(client);
                ticketService.saveTicket(new Ticket(price,drama.getMovieName(),client.getLastName()));
                System.out.println("price= "+ price);
                break;
            }
            case 3: {
                printClients();
                System.out.println("Client's id:");
                Integer idClient = obj.nextInt();
                Client client=findClient(idClient);

                System.out.println("Movies from this category are:");
                movieService.printHorror();

                System.out.println("Chose id of movie:");
                Scanner obj1 = new Scanner(System.in);
                Integer id = obj1.nextInt();
                Horror horror = movieService.findHorror(id);
                movieService.updateHorror(horror);

                Double price= horror.calculatedPrice(client);
                ticketService.saveTicket(new Ticket(price,horror.getMovieName(),client.getLastName()));
                System.out.println("price= "+ price);
                break;
            }
            case 4: {
                printClients();
                System.out.println("Client's id:");
                Integer idClient = obj.nextInt();
                Client client=findClient(idClient);

                System.out.println("Movies from this category are:");
                movieService.printRomantics();

                System.out.println("Chose id of movie:");
                Scanner obj1 = new Scanner(System.in);
                Integer id = obj1.nextInt();
                Romantic romantic = movieService.findRomantic(id);
                movieService.updateRomantic(romantic);

                Double price= romantic.calculatedPrice(client);
                ticketService.saveTicket(new Ticket(price,romantic.getMovieName(),client.getLastName()));
                System.out.println("price= "+ price);
                break;
            }
            case 5: {
                printClients();
                System.out.println("Client's id:");
                Integer idClient = obj.nextInt();
                Client client=findClient(idClient);

                System.out.println("Movies from this category are:");
                movieService.printThrillers();

                System.out.println("Chose id of movie:");
                Scanner obj1 = new Scanner(System.in);
                Integer id = obj1.nextInt();
                Thriller thriller = movieService.findThriller(id);
                movieService.updateThriller(thriller);

                Double price= thriller.calculatedPrice(client);
                ticketService.saveTicket(new Ticket(price,thriller.getMovieName(),client.getLastName()));
                System.out.println("price= "+ price);
                break;
            }
            default: {
                System.out.println("Wrong category.");
                break;
            }
        }
    }
}
