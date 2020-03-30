package com.company;


public abstract class Film {
    protected Integer duration;
    protected String movieName;
    protected Integer nrTickets;
    protected Double price;
    protected Integer recommendedAge;
    protected Integer soldTickets;

    public Film() {
    }

    public Film(Integer duration, String movieName, Integer nrTickets, Integer recommendedAge) {
        this.duration = duration;
        this.movieName = movieName;
        this.nrTickets = nrTickets;
        this.recommendedAge = recommendedAge;
        this.soldTickets = 0;
    }

    public abstract Double calculatedPrice();
    Client client;

    public String getMovieName() {
        return movieName;
    }

    public Integer getRecommendedAge() {
        return recommendedAge;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
