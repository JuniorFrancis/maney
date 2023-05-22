package com.maney.api.controller;

import com.maney.api.model.request.AuthenticationRequest;
import com.maney.api.model.request.RegisterRequest;
import com.maney.api.model.responses.AuthenticationResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    @PostMapping("/register")
    @ResponseBody
    public AuthenticationResponse register(@RequestBody RegisterRequest authenticationRequest) {
        return new AuthenticationResponse("");
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return new AuthenticationResponse("");
    }

}
