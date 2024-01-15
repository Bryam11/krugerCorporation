package com.krugercorporation.apiVaccine.security.enums;

public enum RolName {

    ADMIN(1, "ROLE_ADMIN"),
    EMPLOYEE(2, "ROLE_EMPLOYEE");


    public final Integer idRol;
    public final String rolName;

    RolName(Integer idRol, String rolName) {
        this.idRol = idRol;
        this.rolName = rolName;
    }
}
