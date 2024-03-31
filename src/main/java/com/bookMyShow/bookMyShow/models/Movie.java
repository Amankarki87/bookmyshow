package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Movie extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "movie")
    List<Show> shows;
}
