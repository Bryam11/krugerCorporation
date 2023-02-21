package com.krugercorporation.apiVaccine.security.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String cedula;
    private String name;
    private String lastName;
    private String email;

}
