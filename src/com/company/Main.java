package com.company;

import com.company.persistence.ActionType;
import com.company.persistence.Audit;
import com.company.person.Client;
import com.company.service.*;


import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Main {

    private static final ClientService clientService = ClientService.getInstance();
    private static final MovieService movieService = MovieService.getInstance();
    private static final TicketService ticketService = TicketService.getInstance();
    private static final Audit audit=Audit.getInstance();
    public static void main(String[] args) throws InterruptedException {

        boolean ok = TRUE;
        while (ok) {
            System.out.println("What action do you want to do?\n" +
                    "\tAdd a movie--(0)\n" +
                    "\tAdd a customer--(1)\n" +
                    "\tSell a ticket--(2)\n" +
                    "\tCheck available tickets for a movie--(3)\n" +
                    "\tUpdate client's age --(4)\n" +
                    "\tUpdate client's subscription--(5)\n" +
                    "\tDisplay all movies--(6)\n" +
                    "\tDisplay movies from a specific category--(7)\n" +
                    "\tDisplay clients--(8)\n" +
                    "\tDisplay sold tickets--(9)\n" +
                    "\tDelete a client--(10)\n" +
                    "\tDelete a movie--(11)\n" +
                    "\tEXIT--(12)\n"
            );
            Scanner myObj = new Scanner(System.in);
            int option = myObj.nextInt();
            switch (option) {
                case 0: {
                    System.out.println("Type of movie that you want to add:\n" +
                            "\tComedy->Type  0 \n" +
                            "\tAnimation->Type  1  \n" +
                            "\tDrama->Type  2  \n" +
                            "\tHorror->Type  3  \n" +
                            "\tRomantic->Type  4  \n" +
                            "\tThriller->Type  5\n"
                    );
                    Scanner obj = new Scanner(System.in);
                    int type = obj.nextInt();
                    switch (type) {
                        case 0: {
                            movieService.saveComedy();
                            audit.write(ActionType.ADD_COMEDY, new Date());
                            break;
                        }
                        case 1: {
                            movieService.saveAnimation();
                            audit.write(ActionType.ADD_ANIMATION, new Date());
                            break;
                        }
                        case 2: {
                            movieService.saveDrama();
                            audit.write(ActionType.ADD_DRAMA, new Date());
                            break;
                        }
                        case 3: {
                            movieService.saveHorror();
                            audit.write(ActionType.ADD_HORROR, new Date());
                            break;
                        }
                        case 4: {
                            movieService.saveRomantic();
                            audit.write(ActionType.ADD_ROMANTIC, new Date());
                            break;
                        }
                        case 5: {
                            movieService.saveThriller();
                            audit.write(ActionType.ADD_THRILLER, new Date());
                            break;
                        }
                        default: {
                            System.out.println("Wrong movie type.");
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    clientService.saveClient();
                    audit.write(ActionType.ADD_CLIENT, new Date());
                    break;
                }
                case 2: {
                    clientService.buyTicket();
                    audit.write(ActionType.SOLD_TICKET, new Date());
                    break;
                }
                case 3: {
                    movieService.printMovies();
                    System.out.println("Type movies's id that you need to check:");
                    Integer id = myObj.nextInt();
                    movieService.printAvailableTickets(id);
                    audit.write(ActionType.DISPLAYED_AVAILABLE_TICKETS, new Date());
                    break;
                }
                case 4: {
                    clientService.printClients();
                    System.out.println("Type client's id:");
                    Integer id = myObj.nextInt();
                    Client clientToFind = clientService.findClient(id);
                    clientService.updateClientAge(clientToFind);
                    audit.write(ActionType.UPDATE_CLIENT_AGE, new Date());
                    break;
                }
                case 5: {
                    clientService.printClients();
                    System.out.println("Type client's id:");
                    Integer id = myObj.nextInt();
                    Client clientToFind = clientService.findClient(id);
                    clientService.updateClientSubscription(clientToFind);
                    audit.write(ActionType.UPDATE_CLIENT_SUBSCRIPTION, new Date());
                    break;
                }
                case 6: {
                    movieService.printMovies();
                    audit.write(ActionType.DISPLAYED_ALL_MOVIES, new Date());
                    break;
                }
                case 7: {
                    System.out.println("Type of movie that you want to display:\n" +
                            "\tComedy->Type  0 \n" +
                            "\tAnimation->Type  1  \n" +
                            "\tDrama->Type  2  \n" +
                            "\tHorror->Type  3  \n" +
                            "\tRomantic->Type  4  \n" +
                            "\tThriller->Type  5\n"
                    );
                    Scanner obj = new Scanner(System.in);
                    int type = obj.nextInt();
                    switch (type) {
                        case 0: {
                            movieService.printComedies();
                            break;
                        }
                        case 1: {
                            movieService.printAnimations();
                            break;
                        }
                        case 2: {
                            movieService.printDramas();
                            break;
                        }
                        case 3: {
                            movieService.printHorror();
                            break;
                        }
                        case 4: {
                            movieService.printRomantics();
                            break;
                        }
                        case 5: {
                            movieService.printThrillers();
                            break;
                        }
                        default: {
                            System.out.println("Wrong movie type.");
                            break;
                        }
                    }
                    audit.write(ActionType.DISPLAYED_MOVIES_FROM_CATEGORY, new Date());
                    break;

                }
                case 8: {
                    clientService.printClients();
                    audit.write(ActionType.DISPLAYED_CLIENTS, new Date());
                    break;
                }
                case 9: {
                    ticketService.displayTickets();
                    audit.write(ActionType.DISPLAYED_SOLD_TICKETS, new Date());
                    break;
                }
                case 10: {
                    clientService.printClients();
                    System.out.println("Type client's id.");
                    Integer id = myObj.nextInt();
                    Client clientToDelete = clientService.findClient(id);
                    clientService.deleteClient(clientToDelete);
                    audit.write(ActionType.DELETED_CLIENT, new Date());
                    break;
                }
                case 11: {
                    movieService.printMovies();
                    System.out.println("Type movie's id that you want to delete");
                    Integer id = myObj.nextInt();
                    movieService.deleteMovie(id);
                    audit.write(ActionType.DELETED_MOVIE, new Date());

                    break;
                }
                case 12: {
                    ok = FALSE;
                    break;
                }
                default:
                    System.out.println("Wrong option!");
                    break;


            }
        }
    }
}