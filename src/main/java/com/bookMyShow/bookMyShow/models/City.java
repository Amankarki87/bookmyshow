package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class City extends BaseModel {
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Theatre> theatres;
}
