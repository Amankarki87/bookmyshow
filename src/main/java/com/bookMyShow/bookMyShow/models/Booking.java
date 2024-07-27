package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Booking extends BaseModel {
    private int amount;
    private Date timeOfBookng;

    @ManyToMany
    private List<Seat> seats;

    @ManyToOne
    private User bookedBy;

    @ManyToOne
    private Show show;

    @Enumerated(EnumType.STRING)
    private List<Payment> payment;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}
