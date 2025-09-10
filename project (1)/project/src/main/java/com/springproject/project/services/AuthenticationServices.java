package com.springproject.project.services;

import com.springproject.project.dto.SignUpRequest;
import com.springproject.project.entities.User;

public interface AuthenticationServices {


    User signup(SignUpRequest signUpRequest);
}
