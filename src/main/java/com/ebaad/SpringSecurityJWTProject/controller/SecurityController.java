package com.ebaad.SpringSecurityJWTProject.controller;

import com.ebaad.SpringSecurityJWTProject.service.MyUserDetailsService;
import com.ebaad.SpringSecurityJWTProject.webtoken.JwtService;
import com.ebaad.SpringSecurityJWTProject.webtoken.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @GetMapping("/")
    public String defaultPage() {
        return "Welcome to Default Page!";
    }

    @GetMapping("/home")
    public String home(){
        return "Welcome to Home Page!";
    }

    @GetMapping("/user/home")
    public String user(){
        return "Welcome to User Home Page!";
    }

    @GetMapping("/admin/home")
    public String admin(){
        return "Welcome to Admin Home Page!";
    }

    // for JWT, to authenticate the jwt token through username and password and get token
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));
        // If the username and password is correct, we return the json web token
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(userDetailsService.loadUserByUsername(loginForm.username()));
        } else{
            throw new UsernameNotFoundException("User not Found! Invalid Credentials");
        }
    }
}
