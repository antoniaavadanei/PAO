package com.company.service;
import com.company.persistence.TicketRepository;
import com.company.domain.Ticket;

public class TicketService {

    private static TicketService instance;

    private final TicketRepository ticketRepository = TicketRepository.getInstance();

    private TicketService() {
    }

    public static TicketService getInstance() {
        if (instance == null) {
            instance = new TicketService();
        }

        return instance;
    }

    public void saveTicket(Ticket ticket) {
        ticketRepository.saveTicket(ticket);
    }
    public void displayTickets(){
        ticketRepository.printTickets();
    }
}

