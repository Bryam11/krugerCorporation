package com.krugercorporation.apiVaccine.controller;

import com.krugercorporation.apiVaccine.dto.EmployeeUpdateDto;
import com.krugercorporation.apiVaccine.dto.Mensaje;
import com.krugercorporation.apiVaccine.handle.Validations;

import com.krugercorporation.apiVaccine.security.dto.SignupDTO;
import com.krugercorporation.apiVaccine.security.models.TblUser;
import com.krugercorporation.apiVaccine.service.dao.EmployeeServiceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceDao employeeServiceDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody EmployeeUpdateDto employeeUpdateDto) {
        String[] error = Validations.validateUpdateEmployee(employeeUpdateDto);
        if (error.length > 0) {
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
        return employeeServiceDao.updateEmployee(employeeUpdateDto);
    }

    @PostMapping("/nuevo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> nuevo(@RequestBody SignupDTO nuevoUsuario) {
        if (employeeServiceDao.validatePersonExist(nuevoUsuario.getCedula())) {
            return new ResponseEntity(new Mensaje("El empleado con esta cedula ya existe"), HttpStatus.BAD_REQUEST);
        }
        String[] error = Validations.ValidateEmployee(nuevoUsuario);
        if (error.length > 0) {
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
        TblUser tblUser = employeeServiceDao.createEmployee(nuevoUsuario);
        return new ResponseEntity("usuario asignado " + tblUser.getUsername(), HttpStatus.OK);
    }


    @GetMapping(value = "/listAllEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> listAllEmployee() {
        return new ResponseEntity<>(employeeServiceDao.listAllEmployee(), HttpStatus.OK);
    }


    @GetMapping(value = "/listAllEmployeeDelete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> listAllEmployeeDelete() {
        return new ResponseEntity<>(employeeServiceDao.listAllEmployeeDelete(), HttpStatus.OK);
    }


    @RequestMapping(value = "/deleteEmployeeByCedula/{Cedula}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteEmployeeByCedula(@PathVariable("Cedula") String cedula) {
        return employeeServiceDao.deleteEmployee(cedula);
    }


}
