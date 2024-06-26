package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Show extends BaseModel {
    private String startTime;
    private String endTime;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Auditorium auditorium;
}
