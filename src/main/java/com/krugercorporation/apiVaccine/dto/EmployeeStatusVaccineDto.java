package com.krugercorporation.apiVaccine.dto;

import java.util.Date;


public interface EmployeeStatusVaccineDto {

    String getNumeroCedula();

    String getNombres();

    String getApellidos();

    String getNombreVacuna();

    Date getFechaVacunacion();

    Integer getDosis();

    String getEstadoVacunacion();
}
