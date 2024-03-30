package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "show_seat_mapping")
public class ShowSeat extends BaseModel {

    // SS : S
    // 1  : 1
    // M : 1

    @ManyToOne
    private Show show;

    // SS : Seat
    // 1  : 1
    // M : 1

    @ManyToOne
    private Seat seat;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatState state;
}
