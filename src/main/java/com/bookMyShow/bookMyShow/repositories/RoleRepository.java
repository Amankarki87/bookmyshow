package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Role;
import com.bookMyShow.bookMyShow.models.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value="select role from Role role where role.name= :roleName")
    Role findByName(@Param("roleName") RoleEnum roleName);

    @Query(value="select role from Role role where role.name in :roleNames")
    List<Role> findByNameIn(@Param("roleNames") List<RoleEnum> roleNames);
}
