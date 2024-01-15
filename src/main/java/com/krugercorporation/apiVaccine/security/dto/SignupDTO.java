package com.krugercorporation.apiVaccine.security.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String cedula;
    private String names;
    private String surnames;
    private String email;
    private Integer idRole;

}
