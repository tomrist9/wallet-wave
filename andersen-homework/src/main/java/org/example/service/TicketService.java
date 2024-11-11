package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.enums.StudiumSector;
import org.example.model.Ticket;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketService {


    public static List<Ticket> loadTickets(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), new TypeReference<List<Ticket>>() {});
    }

    public static void validateTickets(List<Ticket> tickets) {
        int totalTickets = tickets.size();
        int validTickets = 0;
        Map<String, Integer> violationCounts = new HashMap<>();

        for (Ticket ticket : tickets) {
            List<String> violations = getViolations(ticket);

            if (violations.isEmpty()) {
                validTickets++;
            } else {
                for (String violation : violations) {
                    violationCounts.put(violation, violationCounts.getOrDefault(violation, 0) + 1);
                }
                System.out.println("Ticket " + ticket + " has violations: " + String.join(", ", violations));
            }
        }

        String mostPopularViolation = violationCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");

        System.out.println("Total = " + totalTickets);
        System.out.println("Valid = " + validTickets);
        System.out.println("Most popular violation = " + mostPopularViolation);
    }

    public static List<String> getViolations(Ticket ticket) {
        return List.of(
                validateStartDate(ticket),
                validatePrice(ticket),
                validateTicketType(ticket),
                validateEvenPrice(ticket)
        ).stream().filter(v -> v != null).collect(Collectors.toList());
    }

    public static String validateStartDate(Ticket ticket) {
        if (ticket.getTicketType().equals("DAY") || ticket.getTicketType().equals("WEEK") || ticket.getTicketType().equals("YEAR")) {
            if (ticket.getStartDate() == null) return "start date";
            try {
                LocalDate startDate = LocalDate.parse(ticket.getStartDate());
                if (startDate.isAfter(LocalDate.now())) return "start date in future";
            } catch (DateTimeParseException e) {
                return "invalid start date format";
            }
        }
        return null;
    }

    public static String validatePrice(Ticket ticket) {
        return ticket.getPrice() == 0 ? "price" : null;
    }

    public static String validateTicketType(Ticket ticket) {
        return ticket.getTicketType() == null ||
                !(ticket.getTicketType().equals("DAY") || ticket.getTicketType().equals("WEEK") ||
                        ticket.getTicketType().equals("MONTH") || ticket.getTicketType().equals("YEAR"))
                ? "ticket type" : null;
    }

    public static String validateEvenPrice(Ticket ticket) {
        return ticket.getPrice() % 2 != 0 ? "price should be even" : null;
    }

}