package com.springproject.project.controller;


import com.springproject.project.config.JwtAuthenticationFilter;
import com.springproject.project.dto.JwtAuthenticationResponse;
import com.springproject.project.dto.SignUpRequest;
import com.springproject.project.entities.User;
import com.springproject.project.services.AuthenticationServices;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationServices authenticationServices ;

    public AuthController(AuthenticationServices authenticationServices) {
        this.authenticationServices = authenticationServices;
    }

    @PostMapping("/singup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return RequestEntity.ok(authenticationServices.signup(signUpRequest));;
    }

    @PostMapping("/singin")
    public  ResponseEntity<JwtAuthenticationResponse> singin(@RequestBody SignUpRequest signUpRequest){
        return RequestEntity.ok(authenticationServices.signup(signUpRequest));
    }
}
