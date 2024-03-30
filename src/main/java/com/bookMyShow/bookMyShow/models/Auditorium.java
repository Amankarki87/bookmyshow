package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Auditorium extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "auditorium")
    private List<Seat> seats;

    @ManyToOne
    private Theatre theatre;
}
