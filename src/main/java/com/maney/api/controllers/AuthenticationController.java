package com.maney.api.controllers;

import com.maney.api.models.request.AuthenticationRequest;
import com.maney.api.models.request.RegisterRequest;
import com.maney.api.models.responses.AuthenticationResponse;
import com.maney.api.services.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    private final AuthenticationServiceImpl authenticationService;


    @ResponseBody
    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest authenticationRequest) {
        return authenticationService.register(authenticationRequest);
    }

    @ResponseBody
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest);
    }

}
