package com.krugercorporation.apiVaccine.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_type_vaccine")
public class TblTypeVaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_vaccine")
    private Integer idTypeVaccine;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @JsonBackReference(value = "tblPersonVaccine")
    @JsonIgnore
    @OneToMany(mappedBy = "tblTypeVaccineByIdTypeVaccine" , fetch = FetchType.LAZY)
    private List<TblEmployeeVaccine> tblPersonVaccine;

}
