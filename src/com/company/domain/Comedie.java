package com.company.domain;

import com.company.person.Client;
import com.company.persistence.MovieType;

import java.util.Scanner;

public class Comedie extends Film {
    public Comedie(String movieName, Integer duration,  Integer nrTickets, Integer recommendedAge) {
        super(movieName, duration,  nrTickets, recommendedAge);
        this.type= MovieType.COMEDY;
    }

    public Comedie() {
    }

    public Comedie readComedie() {
        System.out.println("Movie Name, Duration, Number of Available Tickets, Recommended Age:");
        Scanner myObj = new Scanner(System.in);
        String MovieName = myObj.nextLine();
        Integer Duration = myObj.nextInt();
        Integer NoTickets = myObj.nextInt();
        Integer RecommendedAge = myObj.nextInt();
        return new Comedie(MovieName, Duration, NoTickets, RecommendedAge);

    }

    public Double calculatedPrice(Client client) {
        Integer clientAge = client.getAge();
        price = 25.0;
        if (clientAge < 7)
            return price;
        else if (clientAge <= 23)
            price += 5.0;
        else
            price *= 2.0;
        if( client.getHasSubscription())
            price=price*0.8;
        return price;
    }


}
