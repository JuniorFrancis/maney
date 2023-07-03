package com.maney.api.handlers;

import com.maney.api.exceptions.AlreadyExistingUsernameException;
import com.maney.api.exceptions.InvalidCredentialsException;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@ControllerAdvice
public class AuthExceptionHandler extends ExceptionManager {

    @ResponseBody
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.UNAUTHORIZED, request, Optional.of(ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({ ExpiredJwtException.class, ClaimJwtException.class, JwtException.class})
    public ResponseEntity<Object> handleExpiredToken(ExpiredJwtException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.UNAUTHORIZED, request, Optional.of(ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.NOT_FOUND, request, Optional.of(ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({ AlreadyExistingUsernameException.class })
    protected ResponseEntity<Object> handleRegisterDuplicateUsername(AlreadyExistingUsernameException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.BAD_REQUEST, request, Optional.of(ex.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler({ InvalidCredentialsException.class })
    protected ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.BAD_REQUEST, request, Optional.of(ex.getMessage()));
    }

}
