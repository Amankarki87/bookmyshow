package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriumRepository extends JpaRepository<Auditorium,Long> {
}
