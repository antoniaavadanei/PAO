package com.company.domain;


import com.company.person.Client;
import com.company.persistence.MovieType;


public abstract class Film {
    protected Integer duration;
    protected String movieName;
    protected Integer nrTickets;
    protected Double price;
    protected Integer recommendedAge;
    protected Integer soldTickets;
    protected Client client;
    protected Integer id;
    protected MovieType type;

    public Film() {
    }

    public Film(String movieName, Integer duration,  Integer nrTickets, Integer recommendedAge) {
        this.duration = duration;
        this.movieName = movieName;
        this.nrTickets = nrTickets;
        this.recommendedAge = recommendedAge;
        this.soldTickets = 0;
    }

    public abstract Double calculatedPrice(Client client);

    public String  getType() { return String.valueOf(type); }

    public String getMovieName() {
        return movieName;
    }

    public Integer getRecommendedAge() {
        return recommendedAge;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getId() { return id; }

    public void setSoldTickets(Integer soldTickets) {
        this.soldTickets = soldTickets;
    }

    public void setDuration(Integer duration) { this.duration = duration; }

    public void setMovieName(String movieName) { this.movieName = movieName; }

    public void setNrTickets(Integer nrTickets) { this.nrTickets = nrTickets; }

    public void setId(Integer id) { this.id = id; }

    public void setType(MovieType type) { this.type = type; }

    public void setPrice(Double price) { this.price = price; }

    public void setRecommendedAge(Integer recommendedAge) { this.recommendedAge = recommendedAge; }

    public Integer getNrTickets() {
        return nrTickets;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getSoldTickets() {
        return soldTickets;
    }

}

