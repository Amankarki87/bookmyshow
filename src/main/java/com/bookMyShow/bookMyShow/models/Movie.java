package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Movie extends BaseModel {
    private String name;
    private Genre genre;

    @ManyToMany
    private List<Actor> actors;
}
