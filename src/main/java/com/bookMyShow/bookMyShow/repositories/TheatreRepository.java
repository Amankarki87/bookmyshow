package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.City;
import com.bookMyShow.bookMyShow.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Long>  {
    List<Theatre> findAllByCityId(Long city_id);
}
