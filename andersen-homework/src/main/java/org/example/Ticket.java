package org.example;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;
    private BigDecimal price;


    public Ticket(String id, String concertHall, String eventCode, long time, boolean isPromo, char studiumSector, double maxBackpackWeight, BigDecimal price) {
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.isPromo = isPromo;
        this.studiumSector = studiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.createdAt = LocalDateTime.now();
        this.price = price;
    }

    public Ticket( String concertHall, String eventCode, long time,  BigDecimal price) {

        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.createdAt = LocalDateTime.now();
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Ticket() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", time=" + time +
                ", isPromo=" + isPromo +
                ", studiumSector=" + studiumSector +
                ", maxBackpackWeight=" + maxBackpackWeight +
                ", createdAt=" + createdAt +
                ", price=" + price +
                '}';
    }
}
