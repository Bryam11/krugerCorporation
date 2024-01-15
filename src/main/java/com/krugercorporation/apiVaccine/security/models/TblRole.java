package com.krugercorporation.apiVaccine.security.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_role")
public class TblRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "rol_name", nullable = false, length = 20)
    private String rolName;

    @JsonBackReference(value = "tblUserRolByIdRol")
    @JsonIgnore
    @OneToMany(mappedBy = "tblRoleByIdRol", fetch = FetchType.LAZY)
    private List<TblUserRol> tblUserRolByIdRol;

}
