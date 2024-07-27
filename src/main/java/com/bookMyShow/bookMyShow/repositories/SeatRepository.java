package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long>  {

    @Query(value="select seat from Seat seat where seat.auditorium.id= :auditoriumId " +
            "and seat.seatNumber in :seatNumbers"
    )
    List<Seat> findByAuditoriumIdAndSeatNumberIn(
            @Param("auditoriumId") Long auditoriumId,
            @Param("seatNumbers") List<String> seatNumbers
    );

    @Query(value="select seat from Seat seat where seat.id in :seatIds ")
    List<Seat> findAllByIdIn(@Param("seatIds") List<Long> seatIds);
}
