package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long>  {
}
