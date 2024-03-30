package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Show extends BaseModel {
    private String startTime;
    private String endTime;
}
