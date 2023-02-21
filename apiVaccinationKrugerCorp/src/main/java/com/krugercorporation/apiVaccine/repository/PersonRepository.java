package com.krugercorporation.apiVaccine.repository;


import com.krugercorporation.apiVaccine.models.TblPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<TblPerson, Integer> {

    TblPerson findByCedula(String cedula);


}
