package com.bookMyShow.bookMyShow.repositories;

import com.bookMyShow.bookMyShow.models.Privilege;
import com.bookMyShow.bookMyShow.models.PrivilegeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Long>  {
    @Query(value="select privilege from Privilege privilege where privilege.name= :privilegeName")
    Privilege findByName(@Param("privilegeName") PrivilegeEnum privilegeName);
}
