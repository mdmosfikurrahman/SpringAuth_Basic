package mmr.mosfik.SpringAuth.security.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.security.auth.dto.AuthenticationRequest;
import mmr.mosfik.SpringAuth.security.auth.dto.AuthenticationResponse;
import mmr.mosfik.SpringAuth.security.auth.service.AuthenticationService;
import mmr.mosfik.SpringAuth.security.auth.dto.RegisterRequest;
import mmr.mosfik.SpringAuth.exception.AccessForbiddenException;
import mmr.mosfik.SpringAuth.exception.ErrorResponse;
import mmr.mosfik.SpringAuth.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            AuthenticationResponse response = service.register(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return handleException(CONFLICT, e);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = service.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return handleException(NOT_FOUND, e);
        } catch (AccessForbiddenException e) {
            return handleException(FORBIDDEN, e);
        } catch (Exception e) {
            return handleException(UNAUTHORIZED, e);
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    private ResponseEntity<ErrorResponse> handleException(HttpStatus status, Exception e) {
        return ResponseEntity.status(status)
                .body(
                        new ErrorResponse(
                                status.value(),
                                status.getReasonPhrase(),
                                e.getMessage()
                        )
                );
    }
}