package com.krugercorporation.apiVaccine.repository;


import com.krugercorporation.apiVaccine.dto.EmployeeByTypeVaccineDto;
import com.krugercorporation.apiVaccine.dto.EmployeeFindAllDto;
import com.krugercorporation.apiVaccine.dto.EmployeeStatusVaccineDto;
import com.krugercorporation.apiVaccine.models.TblEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<TblEmployee, Integer> {

    List<TblEmployee> findAllByVaccinationStatus(String status);

    @Query(value = "select  tp.cedula as numeroCedula, tp.name as nombres, " +
            "tp.last_name as apellidos , ttv.name as nombreVacuna, tev.date_vaccine as fechaVacunacion, " +
            "tev.dose as dosis " +
            "from tbl_employee te join tbl_employee_vaccine tev on te.id_employee = tev.id_employee " +
            "join tbl_person tp on te.id_person = tp.id_person " +
            "join tbl_type_vaccine ttv on ttv.id_type_vaccine = tev.id_type_vaccine where ttv.name = ?1 ", nativeQuery = true)
    List<EmployeeByTypeVaccineDto> listAllEmployeesByTypeVaccine(String type);

    @Query(value = "select  tp.cedula as numeroCedula, tp.name as nombres, \n" +
            "tp.last_name as apellidos , ttv.name as nombreVacuna, tev.date_vaccine as fechaVacunacion, \n" +
            "tev.dose as dosis \n" +
            "from tbl_employee te join tbl_employee_vaccine tev on te.id_employee = tev.id_employee \n" +
            "join tbl_person tp on te.id_person = tp.id_person \n" +
            "join tbl_type_vaccine ttv on ttv.id_type_vaccine = tev.id_type_vaccine where cast(tev.date_vaccine as Date ) between to_date(?1, 'YYYY-MM-DD') and to_date(?2, 'YYYY-MM-DD') ", nativeQuery = true)
    List<EmployeeByTypeVaccineDto> listAllEmployeesByDateVaccine(String dateStart, String dateEnd);

    @Query(value = "select  tp.cedula as numeroCedula, tp.name as nombres, \n" +
            "tp.last_name as apellidos , ttv.name as nombreVacuna, tev.date_vaccine as fechaVacunacion, \n" +
            "tev.dose as dosis, te.vaccination_status as estadoVacunacion \n" +
            "from tbl_employee te join tbl_employee_vaccine tev on te.id_employee = tev.id_employee \n" +
            "join tbl_person tp on te.id_person = tp.id_person \n" +
            "join tbl_type_vaccine ttv on ttv.id_type_vaccine = tev.id_type_vaccine where te.vaccination_status  = ?1 ", nativeQuery = true)
    List<EmployeeStatusVaccineDto> listAllEmployeesByStatusVaccine(String status);

    @Query(value = "select te.hiring_date  as fechaContratacion, tp.address as direccion,\n" +
            "tp.birth_date as fechaNacimiento , tp.cedula as cedula, tp.email as email ,\n" +
            "tp.last_name as nombres, tp.last_name as apellidos , tp.phone as celular \n" +
            "from tbl_person tp join tbl_employee te on tp.id_person = te.id_person where te.status = ?1 " , nativeQuery = true)
    List<EmployeeFindAllDto> listAllByStatusEmployee(Boolean status);
}
