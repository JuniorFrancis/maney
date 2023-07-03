package com.maney.api.handlers;

import com.maney.api.models.User;
import com.maney.api.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserHandler {

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    public User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        return userRepository.findByEmail(email).orElseThrow(
            () -> new UsernameNotFoundException("User not found")
        );

    }

    public Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails)principal).getUsername();

        User user = userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found"));

        return user.getId();
    }
}
