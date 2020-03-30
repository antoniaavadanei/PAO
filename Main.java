package com.company;

import java.util.List;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Main {

    public static void main(String[] args) {
        Service customerService = new Service();
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
                    "\tEXIT--(10)\n"
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
                            customerService.registerFilm(c1, 0);
                            break;
                        }
                        case 1: {
                            Animation c1 = new Animation();
                            c1 = c1.readAnimation();
                            customerService.registerFilm(c1, 1);
                            break;
                        }
                        case 2: {
                            Drama c1 = new Drama();
                            c1 = c1.readDrama();
                            customerService.registerFilm(c1, 2);
                            break;
                        }
                        case 3: {
                            Horror c1 = new Horror();
                            c1 = c1.readHorror();
                            customerService.registerFilm(c1, 3);
                            break;
                        }
                        case 4: {
                            Romantic c1 = new Romantic();
                            c1 = c1.readRomantic();
                            customerService.registerFilm(c1, 4);
                            break;
                        }
                        case 5: {
                            Thriller c1 = new Thriller();
                            c1 = c1.readThriller();
                            customerService.registerFilm(c1, 5);
                            break;
                        }
                    }
                    break;
                }
                case 1: {
                    Client cl1 = new Client();
                    cl1 = cl1.readClient();
                    customerService.registercustomer(cl1);
                    break;
                }
                case 2: {
                    customerService.buyTicket();
                    break;
                }
                case 3: {
                    System.out.println("Name of the movie you want to check:");
                    Scanner check = new Scanner(System.in);
                    String name = check.nextLine();
                    Film f = customerService.searchFilmByName(name);
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
                    if (customerService.isRegistered(c1))
                        System.out.println("Customer is registered.");
                    else
                        System.out.println("Customer is NOT registered.");
                    break;
                }
                case 5: {
                    Client c = new Client();
                    c = c.readClient();
                    c.setHasSubscription(TRUE);
                    if (!customerService.isRegistered(c)) {
                        customerService.registercustomer(c);
                    }
                    break;
                }
                case 6: {
                    System.out.println("Movies are:");
                    customerService.displayFilms();
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
                    List<Film> listOfMovies = customerService.returnFilmsByType(type);
                    if (listOfMovies == null)
                        System.out.println("There is no movie in this category.");
                    else
                        for (Film film : listOfMovies)
                            System.out.println("'" + film.getMovieName() + "', " + film.duration + "mins. , recommended age: " + film.recommendedAge);
                    break;
                }
                case 8: {
                    System.out.println("customers");
                    customerService.displaycustomers();
                    break;
                }
                case 9: {
                    customerService.displaySoldTickets();
                    break;
                }
                case 10: {
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


