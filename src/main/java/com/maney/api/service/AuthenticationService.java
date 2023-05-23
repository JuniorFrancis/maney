package com.maney.api.service;

import com.maney.api.model.request.AuthenticationRequest;
import com.maney.api.model.request.RegisterRequest;
import com.maney.api.model.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest authenticationRequest);
}
