package org.example.service;

import org.example.enums.StudiumSector;
import org.example.model.Ticket;

import java.math.BigDecimal;

public class TicketService {
    public static void main(String[] args) {
        Ticket emptyTicket=new Ticket();
        System.out.println("Empty ticket has been created"+ emptyTicket);

        Ticket fullTicket=new Ticket("A123", "DisneyHall","432", 1700000000L,  true, StudiumSector.B, 7.5 ,new BigDecimal("150"));
        System.out.println("Full ticket has been created"+ fullTicket);

        Ticket limitedTicket=new Ticket("SydneyHall", "543", 1700000000L,new BigDecimal("150"));
        System.out.println("Limited ticket has been created"+ limitedTicket);
    }
}
