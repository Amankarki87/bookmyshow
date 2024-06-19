package com.bookMyShow.bookMyShow.repositories;


import com.bookMyShow.bookMyShow.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query(value="select movie from Movie movie where movie.name= :movieName")
    Optional<Movie> findByName(@Param("movieName") String movieName);
}
