package com.bookMyShow.bookMyShow.repositories;


import com.bookMyShow.bookMyShow.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
