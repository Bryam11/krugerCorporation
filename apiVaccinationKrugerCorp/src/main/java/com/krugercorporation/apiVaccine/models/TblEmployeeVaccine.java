package com.krugercorporation.apiVaccine.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_employee_vaccine")
public class TblEmployeeVaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person_vaccine")
    private Integer idPersonVaccine;

    @Column(name = "date_vaccine")
    private Date dateVaccine;

    @Column(name = "dose")
    private Integer dose;


    @JsonManagedReference(value = "tblEmployeeByIdEmployee")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private TblEmployee tblEmployeeByIdEmployee;

    @JsonManagedReference(value = "tblTypeVaccineByIdTypeVaccine")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_vaccine", referencedColumnName = "id_type_vaccine")
    private TblTypeVaccine tblTypeVaccineByIdTypeVaccine;
}
