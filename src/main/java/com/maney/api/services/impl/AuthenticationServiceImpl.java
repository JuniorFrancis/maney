package com.maney.api.services.impl;

import com.maney.api.constants.Role;
import com.maney.api.exceptions.AlreadyExistingUsernameException;
import com.maney.api.models.User;
import com.maney.api.models.request.AuthenticationRequest;
import com.maney.api.models.request.RegisterRequest;
import com.maney.api.models.responses.AuthenticationResponse;
import com.maney.api.repositories.UserRepository;
import com.maney.api.services.AuthenticationService;
import com.maney.api.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new UsernameNotFoundException("Invalid Credentials")
        );

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse.Builder()
                .withToken(jwtToken)
                .build();

    }

    @Override
    public AuthenticationResponse register(RegisterRequest request) {

            if(userRepository.existsByEmail(request.getEmail())){
                throw new AlreadyExistingUsernameException("User already existing");
            }

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
