package com.krugercorporation.apiVaccine.service.dao;

import com.krugercorporation.apiVaccine.dto.EmployeeFindAllDto;
import com.krugercorporation.apiVaccine.dto.EmployeeStatusVaccineDto;
import com.krugercorporation.apiVaccine.dto.EmployeeUpdateDto;
import com.krugercorporation.apiVaccine.models.TblEmployee;
import com.krugercorporation.apiVaccine.security.dto.SignupDTO;
import com.krugercorporation.apiVaccine.security.models.TblUser;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeServiceDao {

    TblUser createEmployee(SignupDTO signupDTO);

    ResponseEntity<?> updateEmployee(EmployeeUpdateDto employeeUpdateDto);

    Boolean validatePersonExist(String cedula);

    List<EmployeeFindAllDto> listAllEmployee();

    ResponseEntity<?> deleteEmployee(String cedula);

    List<EmployeeFindAllDto> listAllEmployeeDelete();

    List<EmployeeStatusVaccineDto> findEmployeeByCedula(String cedula);


}
