package com.company.domain;

import com.company.person.Client;
import com.company.persistence.MovieType;

import java.util.Scanner;
public class Animation extends Film{
    private Boolean requiresSupervizor;

    public Animation() {
    }

    public Animation(String movieName, Integer duration, Integer noTickets, Integer recommendedAge, Boolean requiresSupervisor) {
        this.type= MovieType.ANIMATION;
        this.duration = duration;
        this.movieName = movieName;
        this.nrTickets = noTickets;
        this.recommendedAge = recommendedAge;
        this.soldTickets=0;
        this.requiresSupervizor=requiresSupervisor;
    }

    public Animation readAnimation() {
        System.out.println("Movie Name, Duration, Number of Available Tickets, Recommended Age, Requires Supervizor:");
        Scanner myObj = new Scanner(System.in);
        String readMovieName = myObj.nextLine();
        Integer readDuration = myObj.nextInt();
        Integer readNoTickets = myObj.nextInt();
        Integer readRecommendedAge = myObj.nextInt();
        Boolean readRequiresSupervisor=myObj.nextBoolean();
        return new Animation(readMovieName, readDuration, readNoTickets, readRecommendedAge, readRequiresSupervisor);

    }

    public void setRequiresSupervizor(Boolean requiresSupervizor) {
        this.requiresSupervizor = requiresSupervizor;
    }

    public Double calculatedPrice(Client client) {
        Integer clientAge = client.getAge();
        price = 10.0;
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

    public Boolean getRequiresSupervizor() {
        return requiresSupervizor;
    }
    @Override
    public String toString() {
        return "Animation{" +
                "Duration='" + duration + '\'' +
                ", Movie Name='" + movieName + '\'' +
                ", Nr Tickets='" + nrTickets + '\'' +
                '}';
    }
}
