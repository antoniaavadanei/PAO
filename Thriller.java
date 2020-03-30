package com.company;

import java.util.Scanner;

public class Thriller extends Film {
    public Thriller(Integer duration, String movieName, Integer nrTickets, Integer recommendedAge) {
        super(duration, movieName, nrTickets, recommendedAge);
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
        Thriller thriller = new Thriller(Duration, MovieName, NoTickets, RecommendedAge);
        return thriller;
    }

    public Double calculatedPrice() {
        Integer clientAge = this.client.getAge();
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
