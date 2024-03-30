package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Actor extends BaseModel {
    private String name;

    @ManyToMany
    private List<Movie> movies;
}
