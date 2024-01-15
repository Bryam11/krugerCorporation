package com.krugercorporation.apiVaccine.service.implementation;

import com.krugercorporation.apiVaccine.constants.GeneralConstants;
import com.krugercorporation.apiVaccine.dto.EmployeeFindAllDto;
import com.krugercorporation.apiVaccine.dto.EmployeeStatusVaccineDto;
import com.krugercorporation.apiVaccine.dto.EmployeeUpdateDto;
import com.krugercorporation.apiVaccine.dto.Mensaje;
import com.krugercorporation.apiVaccine.models.TblEmployee;
import com.krugercorporation.apiVaccine.models.TblEmployeeVaccine;
import com.krugercorporation.apiVaccine.models.TblPerson;

import com.krugercorporation.apiVaccine.repository.*;
import com.krugercorporation.apiVaccine.security.dto.SignupDTO;
import com.krugercorporation.apiVaccine.security.models.TblUser;
import com.krugercorporation.apiVaccine.security.models.TblUserRol;
import com.krugercorporation.apiVaccine.security.repository.UserRepository;
import com.krugercorporation.apiVaccine.service.dao.EmployeeServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceDaoImp implements EmployeeServiceDao {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRolRepository userRolRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    TypeVaccineRepository typeVaccineRepository;

    @Autowired
    TblEmployeeVaccineRepository employeeVaccineRepository;


    @Override
    public TblUser createEmployee(SignupDTO signupDTO) {
        TblPerson person = new TblPerson();
        person.setName(signupDTO.getNames());
        person.setLastName(signupDTO.getSurnames());
        person.setCedula(signupDTO.getCedula());
        person.setEmail(signupDTO.getEmail());
        person = personRepository.save(person);

        TblEmployee employee = new TblEmployee();
        employee.setHiringDate(new Date());
        employee.setStatus(GeneralConstants.EMPLOYEE_ACTIVE);
        employee.setTblPersonByIdPerson(person);
        employee = employeeRepository.save(employee);

        TblUser user = new TblUser();
        user.setUserName(generateUsername(signupDTO.getNames(), signupDTO.getSurnames()));
        user.setPassword(passwordEncoder.encode(generatePassword(signupDTO.getNames(), signupDTO.getCedula())));
        user.setTblEmployeeByIdEmployee(employee);
        user = userRepository.save(user);

        TblUserRol userRol = new TblUserRol();
        userRol.setTblUserByIdUser(user);
        userRol.setStatus(GeneralConstants.EMPLOYEE_ACTIVE);
        userRol.setTblRoleByIdRol(rolRepository.findById(signupDTO.getIdRole()).get());
        userRolRepository.save(userRol);

        return user;
    }

    @Override
    public ResponseEntity<?> updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
        TblPerson person = personRepository.findByCedula(employeeUpdateDto.getCedula());
        if (person != null && person.getTblEmployeeByIdPerson().getStatus()) {
            person.setBirthDate(java.sql.Date.valueOf(employeeUpdateDto.getDateOfBirth()));
            person.setAddress(employeeUpdateDto.getAddress());
            person.setPhone(employeeUpdateDto.getTelephone());
            person = personRepository.save(person);

            TblEmployee employee = person.getTblEmployeeByIdPerson();
            employee = employeeRepository.save(employee);


            TblEmployeeVaccine employeeVaccine = new TblEmployeeVaccine();
            employeeVaccine.setDose(employeeUpdateDto.getDose());
            employeeVaccine.setVaccinationStatus(employeeUpdateDto.getStateVaccination());
            employeeVaccine.setTblEmployeeByIdEmployee(employee);
            if (employeeUpdateDto.getStateVaccination().equals(GeneralConstants.STATE_VACCINATION_YES)) {
                employeeVaccine.setDateVaccine(java.sql.Date.valueOf(employeeUpdateDto.getDateVaccination()));
                employeeVaccine.setTblTypeVaccineByIdTypeVaccine(typeVaccineRepository.findById(employeeUpdateDto.getIdTypeVaccine()).get());
            }
            ;
            employeeVaccineRepository.save(employeeVaccine);


            return ResponseEntity.ok(new Mensaje("Empleado actualizado"));
        }
        return ResponseEntity.badRequest().body(new Mensaje("Empleado no encontrado verifique la cédula o que el empleado no este eliminado"));
    }

    @Override
    public Boolean validatePersonExist(String cedula) {
        return personRepository.findByCedula(cedula) != null;

    }

    @Override
    public List<EmployeeFindAllDto> listAllEmployee() {
        return employeeRepository.listAllByStatusEmployee(GeneralConstants.EMPLOYEE_ACTIVE);
    }


    @Override
    public ResponseEntity<?> deleteEmployee(String cedula) {
        TblPerson person = personRepository.findByCedula(cedula);
        if (person != null) {
            TblEmployee employee = person.getTblEmployeeByIdPerson();
            employee.setStatus(GeneralConstants.EMPLOYEE_INACTIVE);
            employeeRepository.save(employee);
            return ResponseEntity.ok(new Mensaje("Empleado eliminado con éxito recuerde que es una eliminación lógica"));
        }
        return ResponseEntity.badRequest().body(new Mensaje("Empleado no encontrado verifique la cédula"));
    }

    @Override
    public List<EmployeeFindAllDto> listAllEmployeeDelete() {
        return employeeRepository.listAllByStatusEmployee(GeneralConstants.EMPLOYEE_INACTIVE);
    }

    @Override
    public List<EmployeeStatusVaccineDto> findEmployeeByCedula(String cedula) {
        return employeeRepository.findEmployeesByCedula(cedula);
    }

    private String generateUsername(String name, String lastName) {
        String username = name.substring(0, 1) + lastName.split(" ")[0];
        int count = 1;
        while (existNameUser(username)) {
            username = username + count;
            count++;
        }
        return username;
    }


    private String generatePassword(String name, String cedula) {
        String password = name.substring(0, 1) + cedula;
        return password;
    }

    private Boolean existNameUser(String name) {
        return userRepository.findByUserName(name).isPresent();
    }

}
