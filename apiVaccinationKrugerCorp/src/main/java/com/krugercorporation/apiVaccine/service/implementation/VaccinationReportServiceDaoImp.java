package com.krugercorporation.apiVaccine.service.implementation;


import com.krugercorporation.apiVaccine.dto.EmployeeByTypeVaccineDto;
import com.krugercorporation.apiVaccine.dto.EmployeeStatusVaccineDto;
import com.krugercorporation.apiVaccine.repository.EmployeeRepository;
import com.krugercorporation.apiVaccine.service.dao.VaccinationReportServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationReportServiceDaoImp implements VaccinationReportServiceDao {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<EmployeeStatusVaccineDto> getStatusVaccination(String status) {
        return employeeRepository.listAllEmployeesByStatusVaccine(status);
    }

    @Override
    public List<EmployeeByTypeVaccineDto> getEmployeeByStatusVaccination(String type) {
        return employeeRepository.listAllEmployeesByTypeVaccine(type);

    }

    @Override
    public List<EmployeeByTypeVaccineDto> getVaccinationDate(String dateStart, String dateEnd) {
        return employeeRepository.listAllEmployeesByDateVaccine(dateStart, dateEnd);
    }


}
