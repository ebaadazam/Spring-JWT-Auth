package com.ebaad.SpringSecurityJWTProject.controller;
import com.ebaad.SpringSecurityJWTProject.model.MyUser;
import com.ebaad.SpringSecurityJWTProject.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// for the purpose of creating the user
@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/add/user")
    public MyUser adduser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
