package com.krugercorporation.apiVaccine.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.krugercorporation.apiVaccine.security.models.TblUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_employee")
public class TblEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Integer idEmployee;

    @Column(name = "hiring_date")
    private Date hiringDate;

    @Column(name = "vaccination_status", length = 20)
    private String vaccinationStatus;

    @Column(name = "status")
    private Boolean status;


    @JsonBackReference(value = "tblPersonVaccine")
    @JsonIgnore
    @OneToMany(mappedBy = "tblEmployeeByIdEmployee", fetch = FetchType.LAZY)
    private List<TblEmployeeVaccine> tblPersonVaccine;

    @JsonManagedReference(value = "tblPersonByIdPerson")
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id_person", nullable = false)
    private TblPerson tblPersonByIdPerson;

    @JsonManagedReference(value = "tblPersonByIdPerson")
    @JsonIgnore
    @OneToOne(mappedBy = "tblEmployeeByIdEmployee", fetch = FetchType.LAZY)
    private TblUser tblUserByIdEmployee;

}
