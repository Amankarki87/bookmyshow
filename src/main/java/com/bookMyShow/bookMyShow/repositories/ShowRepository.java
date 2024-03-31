package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Long> {
}
