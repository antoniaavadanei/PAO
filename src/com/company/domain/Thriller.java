package com.company.domain;

import com.company.person.Client;
import com.company.persistence.MovieType;

import java.util.Scanner;

public class Thriller extends Film {

    public Thriller(String movieName, Integer duration,  Integer nrTickets, Integer recommendedAge) {
        super(movieName, duration,  nrTickets, recommendedAge);
        this.type= MovieType.THRILLER;
    }

    public Thriller() {
    }

    public Thriller readThriller() {
        System.out.println("Movie Name, Duration, Number of Available Tickets, Recommended Age:");
        Scanner myObj = new Scanner(System.in);
        String MovieName = myObj.nextLine();
        Integer Duration = myObj.nextInt();
        Integer NoTickets = myObj.nextInt();
        Integer RecommendedAge = myObj.nextInt();
        return new Thriller(MovieName, Duration, NoTickets, RecommendedAge);

    }

    public Double calculatedPrice(Client client) {
        Integer clientAge = client.getAge();
        price = 17.0;
        if (clientAge < 7)
            return price;
        else if (clientAge <= 23)
            price += 5.0;
        else
            price *= 2.0;
        if (client.getHasSubscription())
            price = price * 0.8;
        return price;
    }

    @Override
    public Integer getRecommendedAge() {
        return super.getRecommendedAge();
    }
}
