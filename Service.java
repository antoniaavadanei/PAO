package com.company;

import java.util.Scanner;
import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Service {

    private List<Client> clientList = new ArrayList<>();

    public void registercustomer(Client client) {
        clientList.add(client);
    }

    public Boolean isRegistered(Client c) {

        for (Client client : clientList) {
            if (client.equals(c)) {
                return TRUE;
            }
        }
        return FALSE;
    }

    ClientComparator clientComparator = new ClientComparator();

    public void displaycustomers() {
        Collections.sort(clientList, clientComparator);
        if (clientList == null)
            System.out.println("There is no customer registered");
        else
            for (Client client : clientList)
                System.out.println(client.getLastName() + " " + client.getFirstName() + ", " + client.getAge() + " years old," + " subscription: " + client.getHasSubscription());
    }

    private Map<Integer, List<Film>> filmList = new HashMap<>();

    public void displaySoldTickets() {
        for (Map.Entry<Integer, List<Film>> entry : filmList.entrySet()) {
            List<Film> list = entry.getValue();
            for (Film film : list)
                System.out.println("'" + film.getMovieName() + "', " + film.soldTickets + " tickets sold.");
        }
    }

    public void displayFilms() {
        if (filmList == null)
            System.out.println("No movie added");
        else
            for (Map.Entry<Integer, List<Film>> entry : filmList.entrySet()) {
                List<Film> list = entry.getValue();
                for (Film film : list)
                    System.out.println("'" + film.getMovieName() + "', " + film.duration + "mins. , recommended age: " + film.recommendedAge);
            }
    }

    public Film searchFilmByName(String f) {
        for (Map.Entry<Integer, List<Film>> entry : filmList.entrySet()) {
            List<Film> list = entry.getValue();
            for (Film film : list)
                if (film.movieName.equals(f))///ana=ana
                    return film;

        }
        return null;
    }

    public List<Film> returnFilmsByType(Integer i) {

        return filmList.get(i);
    }

    public void registerFilm(Film film, Integer i) {
        if (!filmList.containsKey(i)) {
            filmList.put(i, new ArrayList<>());
        }
        filmList.get(i).add(film);
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
        System.out.println("Movies from this category are:");
        List<Film> list = this.returnFilmsByType(type);
        if (list == null)
            System.out.println("\tNo available movies in this category.");
        else {
            for (Film film : list)
                System.out.println("'" + film.getMovieName() + "', " + film.duration + "mins. , recommended age: " + film.recommendedAge);
            System.out.println("Chose name of movie:");
            Scanner obj1 = new Scanner(System.in);
            String name = obj1.nextLine();
            Film f = this.searchFilmByName(name);
            if (f == null)
                System.out.println("Movie not found in category");
            System.out.println("Customer:");
            Client c = new Client();
            c = c.readClient();
            if (!isRegistered(c)) {
                this.registercustomer(c);
                System.out.println("The customer is new, so is has been registered now.");
            } else
                System.out.println("The customer registered.");
            f.setClient(c);
            if (f.recommendedAge > f.client.getAge())
                System.out.println("You are below the minimum required age( " + f.recommendedAge + "). ");
            else {
                if (f.nrTickets == 0)
                    System.out.println("No tickets available.");
                else {
                    System.out.println("The ticket for movie '" + f.movieName + "' is " + f.calculatedPrice() + "-Customer: " + c.getLastName() + " " + c.getFirstName());

                    f.soldTickets++;

                }

            }

        }
    }

    public void checkTickets(String f) {
        boolean found;
        found = FALSE;
        for (Map.Entry<Integer, List<Film>> entry : filmList.entrySet()) {
            List<Film> list = entry.getValue();
            for (Film film : list)
                if (film.movieName.equals(f)) {
                    System.out.println(film.nrTickets - film.soldTickets + " available tickets for " + film.movieName);
                    found = TRUE;
                }

        }
        if (found == FALSE)
            System.out.println("Movie not found");
    }


}
