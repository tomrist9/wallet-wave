package org.example.model;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.example.enums.StudiumSector;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends Entity {
//    @Pattern(regexp = "^[A-Za-z0-9]{1,4}$", message = "ID must be 1-4 alphanumeric characters.")
//    private String id;

    @Pattern(regexp = "^.{0,10}$", message = "concertHall must be 10 characters or fewer.")
    private String concertHall;

    @Pattern(regexp = "^\\d{3}$", message = "Event code must be exactly 3 digits")
    private String eventCode;

    @Min(value=0, message = "Time must be a positive Unix timestamp")
    private long time;

    private boolean isPromo;

    private StudiumSector studiumSector;
    @DecimalMax(value = "10.000", message = "Max backpack weight must be 10 kg or less with grams precision")
    private double maxBackpackWeight;

    private LocalDateTime createdAt;
    private BigDecimal price;


    public Ticket(String id, String concertHall, String eventCode, long time, boolean isPromo, StudiumSector studiumSector, double maxBackpackWeight, BigDecimal price) {
//        this.id = id;
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
    public Ticket() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public StudiumSector getStudiumSector() {
        return studiumSector;
    }

    public void setStudiumSector(StudiumSector studiumSector) {

        this.studiumSector = studiumSector;
    }

    public String getAllTicketValues() {
        return "Ticket Details:\n" +
                "ID: " + getId() + "\n" +
                "Concert Hall: " + concertHall + "\n" +
                "Event Code: " + eventCode + "\n" +
                "Time: " + time + "\n" +
                "Promo: " + (isPromo ? "Yes" : "No") + "\n" +
                "Stadium Sector: " + studiumSector + "\n" +
                "Max Backpack Weight: " + maxBackpackWeight + " kg\n" +
                "Created At: " + (createdAt != null ? createdAt.toString() : "N/A") + "\n" +
                "Price: " + (price != null ? price.toString() : "N/A");

    }
    public void share(String phoneNumber){
        System.out.println("Ticket shared via phone to: " + phoneNumber);
    }
    public void share(String phoneNumber, String email){
        System.out.println("Ticket shared via phone and email  to: " + phoneNumber + email);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;
        return time == ticket.time && isPromo == ticket.isPromo && Double.compare(ticket.maxBackpackWeight, maxBackpackWeight) == 0 && Objects.equals(concertHall, ticket.concertHall) && Objects.equals(eventCode, ticket.eventCode) && getStudiumSector() == ticket.getStudiumSector() && Objects.equals(getCreatedAt(), ticket.getCreatedAt()) && Objects.equals(getPrice(), ticket.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(concertHall, eventCode, time, isPromo, getStudiumSector(), maxBackpackWeight, getCreatedAt(), getPrice());
    }

    @Override
    public String toString() {
        return "Ticket{" +
//                "id='" + id + '\'' +
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
