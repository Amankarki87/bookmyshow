package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Auditorium;
import com.bookMyShow.bookMyShow.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium,Long> {
    @Query(value="select theatre from Auditorium auditorium where auditorium.name= :auditoriumName " +
            "and auditorium.theatre.id= :theatreId"
    )
    Optional<Auditorium> findByNameAndTheatreId(
            @Param("auditoriumName") String auditoriumName,
            @Param("theatreId") Long theatreId
    );
}
