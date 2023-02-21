package com.krugercorporation.apiVaccine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeUpdateDto {

    private String cedula;
    private String dateOfBirth;
    private String Address;

    private String telephone;

    private String stateVaccination;

    private Integer idTypeVaccine;

    private Integer dose;

    private String dateVaccination;
}
