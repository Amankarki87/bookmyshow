package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Show;
import com.bookMyShow.bookMyShow.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long>  {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value="select showSeat from ShowSeat showSeat where showSeat.seat.id in :seats and showSeat.show = :show")
    List<ShowSeat> findAllBySeatInAndShow(@Param("seats") List<Long> seats, @Param("show") Show show);
}
