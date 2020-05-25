package com.company.domain;

public class Ticket {
    protected Double price;
    protected String movieName;
    protected String  clientName;

    public Ticket(Double price, String movieName, String clientName) {
        this.price = price;
        this.movieName = movieName;
        this.clientName = clientName;
    }

    public Double getPrice() { return price; }

    public String getMovieName() { return movieName; }

    public String getClientName() { return clientName; }

    public void setPrice(Double price) { this.price = price; }

    public void setMovieName(String movieName) { this.movieName = movieName; }

}
