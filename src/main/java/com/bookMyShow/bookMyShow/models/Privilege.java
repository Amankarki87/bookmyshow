package com.bookMyShow.bookMyShow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Privilege extends BaseModel {
    @Enumerated(EnumType.STRING)
    private PrivilegeEnum name;
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
