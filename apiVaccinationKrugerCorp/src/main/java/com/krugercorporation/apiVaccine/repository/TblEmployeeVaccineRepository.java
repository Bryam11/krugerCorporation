package com.krugercorporation.apiVaccine.repository;



import com.krugercorporation.apiVaccine.models.TblEmployeeVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblEmployeeVaccineRepository extends JpaRepository<TblEmployeeVaccine, Integer> {


}
