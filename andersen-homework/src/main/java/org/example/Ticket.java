package org.example;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class Ticket {
    @Pattern(regexp = "^[A-Za-z0-9]{1,4}$", message = "ID must be 1-4 alphanumeric characters.")
    private String id;

    @Pattern(regexp = "^.{0,10}$", message = "concertHall must be 10 characters or fewer.")
    private String concertHall;

    @Pattern(regexp = "^\\d{3}$", message = "Event code must be exactly 3 digits")
    private String eventCode;

    @Min(value=0, message = "Time must be a positive Unix timestamp")
    private long time;

    private boolean isPromo;

    @Pattern(regexp = "[ABC]", message = "Studium sector must be 'A', 'B', and 'C'")
    private char studiumSector;

    @DecimalMax(value = "10.000", message = "Max backpack weight must be 10 kg or less with grams precision")
    private double maxBackpackWeight;


    public Ticket(String id, String concertHall, String eventCode, long time, boolean isPromo, char studiumSector, double maxBackpackWeight) {
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.studiumSector = studiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
    }

    public Ticket(String concertHall, String eventCode, long time) {
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
    }

    public Ticket() {

    }
}
