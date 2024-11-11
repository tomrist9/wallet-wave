package org.example;

import org.example.model.Ticket;

import java.io.IOException;
import java.util.List;

import static org.example.service.TicketService.loadTickets;
import static org.example.service.TicketService.validateTickets;

public class Main {
    public static void main(String[] args) {
        try {
            List<Ticket> tickets = loadTickets("tickets.json");
            validateTickets(tickets);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}