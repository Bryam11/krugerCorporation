
package com.krugercorporation.apiVaccine.security.service;

import com.krugercorporation.apiVaccine.security.models.EmployeeMain;

import com.krugercorporation.apiVaccine.security.models.TblUser;
import com.krugercorporation.apiVaccine.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {



    @Autowired
    UserRepository userRolRepository;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        TblUser user = userRolRepository.findByUserName(nombreUsuario).get();
        return EmployeeMain.build(user);
    }
}
