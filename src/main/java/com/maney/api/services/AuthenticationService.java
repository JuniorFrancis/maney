package com.maney.api.services;

import com.maney.api.models.request.AuthenticationRequest;
import com.maney.api.models.request.RegisterRequest;
import com.maney.api.models.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest authenticationRequest);
}
