package com.krugercorporation.apiVaccine.service.dao;

import com.krugercorporation.apiVaccine.dto.EmployeeByTypeVaccineDto;
import com.krugercorporation.apiVaccine.dto.EmployeeStatusVaccineDto;


import java.util.List;

public interface VaccinationReportServiceDao {

    List<EmployeeStatusVaccineDto> getStatusVaccination(String status);

    List<EmployeeByTypeVaccineDto> getEmployeeByStatusVaccination(String type);

    List<EmployeeByTypeVaccineDto> getVaccinationDate(String dateStart, String dateEnd);
}
