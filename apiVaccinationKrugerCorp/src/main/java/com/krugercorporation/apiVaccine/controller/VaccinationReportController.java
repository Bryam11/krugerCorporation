package com.krugercorporation.apiVaccine.controller;


import com.krugercorporation.apiVaccine.service.dao.VaccinationReportServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/vaccinationReport")
public class VaccinationReportController {

    @Autowired
    VaccinationReportServiceDao vaccinationReportServiceDao;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeeByStatusVaccination")
    public ResponseEntity getEmployeeByStatusVaccination(@Param("Status Vaccination") String statusVaccination) {
        return ResponseEntity.ok(vaccinationReportServiceDao.getStatusVaccination(statusVaccination));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeeByTypeVaccine")
    public ResponseEntity getEmployeeByTypeVaccine(@Param("Type Vaccine") String typeVaccine) {
        return ResponseEntity.ok(vaccinationReportServiceDao.getEmployeeByStatusVaccination(typeVaccine));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeeByVaccinationDate")
    public ResponseEntity getEmployeeByVaccinationDate(@Param("Date Start") String dateStart, @Param("Date End") String dateEnd) {
        return ResponseEntity.ok(vaccinationReportServiceDao.getVaccinationDate(dateStart, dateEnd));
    }
}
