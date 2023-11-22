package mmr.mosfik.SpringAuth.security.auth.service.authenication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mmr.mosfik.SpringAuth.security.auth.dto.AuthenticationRequest;
import mmr.mosfik.SpringAuth.security.auth.dto.AuthenticationResponse;
import mmr.mosfik.SpringAuth.security.auth.dto.RegisterRequest;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
