package com.ebaad.SpringSecurityJWTProject.repository;

import com.ebaad.SpringSecurityJWTProject.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username); // We used Optional<> as sometimes user exists or sometimes not
}
