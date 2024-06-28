package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>  {
    @Query(value="select user from User user where user.email= :email")
    Optional<User> findByEmail(@Param("email") String email);
}
