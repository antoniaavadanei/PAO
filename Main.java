package com.company;

import java.util.List;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Main {

    public static void main(String[] args) {
        Service service = new Service();
        //reading clients
        ClientService clientService = ClientService.getInstance();
        clientService.readClientsFromFile(service);

        //reading movies
        MovieReadingService movieReadingService = MovieReadingService.getInstance();
        movieReadingService.readAnimationsFromFile(service);
        movieReadingService.readComediesFromFile(service);
        movieReadingService.readDramasFromFile(service);
        movieReadingService.readHorrorFromFile(service);


        MovieWritingService movieWritingService = MovieWritingService.getInstance();
        AuditService auditService=AuditService.getInstance();
        
        boolean ok = TRUE;
        while (ok) {
            System.out.println("What action do you want to do?\n" +
                    "\tAdd a movie--(0)\n" +
                    "\tAdd a customer--(1)\n" +
                    "\tSell a ticket--(2)\n" +
                    "\tCheck available tickets for a movie--(3)\n" +
                    "\tCheck if a customer is registered--(4)\n" +
                    "\tMake subscription for a customer--(5)\n" +
                    "\tDisplay all movies--(6)\n" +
                    "\tDisplay movies from a specific category(7)\n" +
                    "\tDisplay customers--(8)\n" +
                    "\tDisplay sold tickets--(9)\n" +
                    "\tDelete a client--(10)\n" +
                    "\tEXIT--(11)\n"
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
                            Comedie c1 = new Comedie();
                            c1 = c1.readComedie();
                            service.registerFilm(c1, 0);
                            movieWritingService.writeComediesToFile(service);
                            break;
                        }
                        case 1: {
                            Animation c1 = new Animation();
                            c1 = c1.readAnimation();
                            service.registerFilm(c1, 1);
                            movieWritingService.writeAnimationsToFile(service);
                            break;
                        }
                        case 2: {
                            Drama c1 = new Drama();
                            c1 = c1.readDrama();
                            service.registerFilm(c1, 2);
                            movieWritingService.writeDramaToFile(service);
                            break;
                        }
                        case 3: {
                            Horror c1 = new Horror();
                            c1 = c1.readHorror();
                            service.registerFilm(c1, 3);
                            movieWritingService.writeHorrorToFile(service);
                            break;
                        }
                        case 4: {
                            Romantic c1 = new Romantic();
                            c1 = c1.readRomantic();
                            service.registerFilm(c1, 4);
                            break;
                        }
                        case 5: {
                            Thriller c1 = new Thriller();
                            c1 = c1.readThriller();
                            service.registerFilm(c1, 5);
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    Client cl1 = new Client();
                    cl1 = cl1.readClient();
                    service.registercustomer(cl1);
                    clientService.writeClientsToFile(service);
                    break;
                }
                case 2: {
                    service.buyTicket();
                    break;
                }
                case 3: {
                    System.out.println("Name of the movie you want to check:");
                    Scanner check = new Scanner(System.in);
                    String name = check.nextLine();
                    Film f = service.searchFilmByName(name);
                    if (f == null)
                        System.out.println("Movie not found.");
                    else
                        System.out.println(f.nrTickets - f.soldTickets + " tickets available for '" + f.movieName + "'");
                    break;
                }
                case 4: {
                    System.out.println("customer:");
                    Client c1 = new Client();
                    c1 = c1.readClient();
                    if (service.isRegistered(c1))
                        System.out.println("Customer is registered.");
                    else
                        System.out.println("Customer is NOT registered.");
                    break;
                }
                case 5: {
                    Client c = new Client();
                    c = c.readClient();
                    c.setHasSubscription(TRUE);
                    if (!service.isRegistered(c)) {
                        service.registercustomer(c);
                    }
                    break;
                }
                case 6: {
                    System.out.println("Movies are:");
                    service.displayFilms();
                    break;
                }
                case 7: {
                    System.out.println("Type of movies that you want to display:\n" +
                            "\tComedy->Type  0 \n" +
                            "\tAnimation->Type  1  \n" +
                            "\tDrama->Type  2  \n" +
                            "\tHorror->Type  3  \n" +
                            "\tRomantic->Type  4  \n" +
                            "\tThriller->Type  5\n"
                    );
                    Scanner obj = new Scanner(System.in);
                    int type = obj.nextInt();
                    List<Film> listOfMovies = service.returnFilmsByType(type);
                    if (listOfMovies == null)
                        System.out.println("There is no movie in this category.");
                    else
                        for (Film film : listOfMovies)
                            System.out.println("'" + film.getMovieName() + "', " + film.duration + "mins. , recommended age: " + film.recommendedAge);
                    break;
                }
                case 8: {
                    service.displaycustomers();
                    break;
                }
                case 9: {
                    service.displaySoldTickets();
                    break;
                }
                case 10: {
                    System.out.println("Last Name and First Name of the customer you want to delete:");
                    Scanner customer = new Scanner(System.in);
                    String LastName = customer.nextLine();
                    String FirstName = customer.nextLine();
                    if(service.findClientByName(LastName,FirstName)){
                        int position=service.returnPositionOfClient(LastName,FirstName);
                        service.deleteClient(position);
                    }
                    else
                        System.out.println("This client is not registered.");
                    clientService.writeClientsToFile(service);
                    break;
                }
                case 11: {
                    ok = FALSE;
                    break;
                }
                default:
                    System.out.println("Wrong option!");
                    break;


            }
            auditService.writeActionToFile(option);

        }


    }


}


