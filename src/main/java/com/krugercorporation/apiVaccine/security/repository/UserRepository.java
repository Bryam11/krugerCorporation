package com.krugercorporation.apiVaccine.security.repository;


import com.krugercorporation.apiVaccine.security.models.TblUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {
    Optional<TblUser> findByUserName(String username);

    boolean existsByUserName(String username);

}
