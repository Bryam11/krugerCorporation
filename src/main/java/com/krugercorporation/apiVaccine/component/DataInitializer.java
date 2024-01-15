package com.krugercorporation.apiVaccine.component;


import com.krugercorporation.apiVaccine.constants.GeneralConstants;
import com.krugercorporation.apiVaccine.models.TblEmployee;
import com.krugercorporation.apiVaccine.models.TblPerson;

import com.krugercorporation.apiVaccine.models.TblTypeVaccine;

import com.krugercorporation.apiVaccine.repository.*;

import com.krugercorporation.apiVaccine.security.enums.RolName;
import com.krugercorporation.apiVaccine.security.models.TblRole;
import com.krugercorporation.apiVaccine.security.models.TblUser;
import com.krugercorporation.apiVaccine.security.models.TblUserRol;
import com.krugercorporation.apiVaccine.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class DataInitializer {

    @Autowired
    TypeVaccineRepository typeVaccineRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    TblEmployeeVaccineRepository employeeVaccineRepository;

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


    @PostConstruct
    public void init() {

        if (personRepository.findByCedula("01023456789") != null) {
            return;
        }
        // load data for type vaccine
        TblTypeVaccine tblTypeVaccine = new TblTypeVaccine();
        tblTypeVaccine.setName("Sputnik");
        typeVaccineRepository.save(tblTypeVaccine);

        tblTypeVaccine = new TblTypeVaccine();
        tblTypeVaccine.setName("AstraZeneca");
        typeVaccineRepository.save(tblTypeVaccine);

        tblTypeVaccine = new TblTypeVaccine();
        tblTypeVaccine.setName("Pfizer");
        typeVaccineRepository.save(tblTypeVaccine);

        tblTypeVaccine = new TblTypeVaccine();
        tblTypeVaccine.setName("Jhonson&Jhonson");
        typeVaccineRepository.save(tblTypeVaccine);

        // load data for rol
        TblRole tblRole = new TblRole();
        tblRole.setRolName(RolName.ADMIN.rolName);
        rolRepository.save(tblRole);

        tblRole = new TblRole();
        tblRole.setRolName(RolName.EMPLOYEE.rolName);
        rolRepository.save(tblRole);

        // load data for user admin
        TblPerson person = new TblPerson();
        person.setName("Bryan Alexander");
        person.setLastName("Carrillo Mora");
        person.setCedula("01023456789");
        person.setEmail("administrador@gmail.com");
        person = personRepository.save(person);

        TblEmployee employee = new TblEmployee();
        employee.setHiringDate(new Date());
        employee.setTblPersonByIdPerson(person);
        employee = employeeRepository.save(employee);

        TblUser user = new TblUser();
        user.setUserName("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setTblEmployeeByIdEmployee(employee);
        user = userRepository.save(user);

        TblUserRol userRol = new TblUserRol();
        userRol.setTblUserByIdUser(user);
        userRol.setStatus(GeneralConstants.EMPLOYEE_ACTIVE);
        userRol.setTblRoleByIdRol(rolRepository.findById(RolName.ADMIN.idRol).get());
        userRolRepository.save(userRol);

    }
}
