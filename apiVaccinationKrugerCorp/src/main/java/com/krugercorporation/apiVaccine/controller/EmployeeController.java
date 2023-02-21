package com.krugercorporation.apiVaccine.controller;

import com.krugercorporation.apiVaccine.dto.EmployeeUpdateDto;
import com.krugercorporation.apiVaccine.dto.Mensaje;
import com.krugercorporation.apiVaccine.handle.Validations;

import com.krugercorporation.apiVaccine.security.dto.SignupDTO;
import com.krugercorporation.apiVaccine.service.dao.EmployeeServiceDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceDao employeeServiceDao;

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PutMapping(value = "/update")
    public ResponseEntity update(@RequestBody EmployeeUpdateDto employeeUpdateDto) {
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
        employeeServiceDao.createEmployee(nuevoUsuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/listAllEmployee")
    public ResponseEntity<?> listAllEmployee() {
        return new ResponseEntity<>(employeeServiceDao.listAllEmployee(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/listAllEmployeeDelete")
    public ResponseEntity<?> listAllEmployeeDelete() {
        return new ResponseEntity<>(employeeServiceDao.listAllEmployeeDelete(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/deleteEmployeeByCedula/{Cedula}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployeeByCedula(@PathVariable("Cedula") String cedula) {
        return employeeServiceDao.deleteEmployee(cedula);
    }


}
