package com.krugercorporation.apiVaccine.repository;


import com.krugercorporation.apiVaccine.security.models.TblRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<TblRole, Integer> {
    TblRole findByRolName(String rolName);


}
