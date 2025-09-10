package com.springproject.project.services.impl;

import com.springproject.project.dto.JwtAuthenticationResponse;
import com.springproject.project.dto.SignUpRequest;
import com.springproject.project.dto.SigninRequest;
import com.springproject.project.entities.Role;
import com.springproject.project.entities.User;
import com.springproject.project.repository.UserRepository;
import com.springproject.project.services.AuthenticationServices;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServicesImpl implements AuthenticationServices {


    private  final UserRepository userRepository ;

    public AuthenticationServicesImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTServicesImpl jwtServices) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtServices = jwtServices;
    }

    private final PasswordEncoder passwordEncoder ;

    private final JWTServicesImpl jwtServices ;



    public User signup(SignUpRequest signUpRequest){

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setUsername(signUpRequest.getUsername());
        user.setRole(Role.USER);
        user.setPassword(PasswordEncoder.encode(signUpRequest.getPassword()));

       return userRepository.save(user);
    }


    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authencationManger.authentication(new UsernamePasswordAuthenticationToken(signinRequest.getClass() ,
                signinRequest.getClass()));

        var user = userRepository.findByEmail(signinRequest.getEmail().orElseThow(() -> new IllegalArgumentException("Invaild password"));
        var jwt = jwtServices.generateToken(user);
        var refresh = jwtServices.generateToken(user);
    }


}
