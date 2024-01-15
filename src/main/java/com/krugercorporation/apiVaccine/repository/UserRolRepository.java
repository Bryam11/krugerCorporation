package com.krugercorporation.apiVaccine.repository;

import com.krugercorporation.apiVaccine.security.models.TblUserRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolRepository extends JpaRepository<TblUserRol, Integer> {


}
