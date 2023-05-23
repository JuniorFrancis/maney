package com.maney.api.service.impl;

import com.maney.api.constants.Role;
import com.maney.api.model.User;
import com.maney.api.model.request.AuthenticationRequest;
import com.maney.api.model.request.RegisterRequest;
import com.maney.api.model.responses.AuthenticationResponse;
import com.maney.api.repository.UserRepository;
import com.maney.api.service.AuthenticationService;
import com.maney.api.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.maney.api.handlers.ValidatorHandler.checkNotNull;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder encoder,
                                     JwtService jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        checkNotNull(user);

        String jwtToken = jwtService.generateToken(user.get());

        return new AuthenticationResponse.Builder()
                .withToken(jwtToken)
                .build();

    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
           User user = new User.Builder()
                   .withFirstname(request.getFirstname())
                   .withEmail(request.getEmail())
                   .withPassword(encoder.encode(request.getPassword()))
                   .withRole(Role.PERSONAL)
                   .build();

           userRepository.save(user);

           String jwtToken = jwtService.generateToken(user);

           return new AuthenticationResponse.Builder()
                   .withToken(jwtToken)
                   .build();

    }
}
