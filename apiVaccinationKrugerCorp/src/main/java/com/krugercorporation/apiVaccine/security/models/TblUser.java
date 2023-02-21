package com.krugercorporation.apiVaccine.security.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.krugercorporation.apiVaccine.models.TblEmployee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class TblUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "user_name", nullable = false, length = 100, unique = true)
    private String userName;

    @Column(name = "password", nullable = false, length = 100)
    private String Password;

    @JsonBackReference(value = "tblUserRolByIdUser")
    @JsonIgnore
    @OneToMany(mappedBy = "tblUserByIdUser", fetch = FetchType.EAGER)
    private List<TblUserRol> tblUserRolByIdUser;

    @JsonManagedReference(value = "tblEmployeeByIdEmployee")
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private TblEmployee tblEmployeeByIdEmployee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (TblUserRol userRol : tblUserRolByIdUser) {
//            authorities.add(new SimpleGrantedAuthority(userRol.getTblRoleByIdRol().getRolName()));
//        }
//        return authorities;
        return Collections.EMPTY_LIST;

    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
