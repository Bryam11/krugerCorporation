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
@Table(name = "tbl_person")
public class TblPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_person")
    private Integer idPerson;

    @Column(name = "cedula", length = 100)
    private String cedula;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 100)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "birth_date")
    private Date birthDate;

    @JsonManagedReference(value = "tblEmployeeByIdPerson")
    @JsonIgnore
    @OneToOne(mappedBy = "tblPersonByIdPerson", fetch = FetchType.LAZY)
    private TblEmployee tblEmployeeByIdPerson;

}
