package com.company;

import java.util.Scanner;

public class Comedie extends Film {
    public Comedie(Integer duration, String movieName, Integer nrTickets, Integer recommendedAge) {
        super(duration, movieName, nrTickets, recommendedAge);
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
        Comedie comedie = new Comedie(Duration, MovieName, NoTickets, RecommendedAge);
        return comedie;
    }

    public Double calculatedPrice() {
        Integer clientAge = this.client.getAge();
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
