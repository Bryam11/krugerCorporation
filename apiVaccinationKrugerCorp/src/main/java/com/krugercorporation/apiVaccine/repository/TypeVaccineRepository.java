package com.krugercorporation.apiVaccine.repository;



import com.krugercorporation.apiVaccine.models.TblTypeVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeVaccineRepository extends JpaRepository<TblTypeVaccine, Integer> {


}
