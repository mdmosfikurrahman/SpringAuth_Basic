package mmr.mosfik.SpringAuth.security.auth.service;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.exception.AccessForbiddenException;
import mmr.mosfik.SpringAuth.security.auth.dto.ChangePasswordRequest;
import mmr.mosfik.SpringAuth.security.auth.model.user.User;
import mmr.mosfik.SpringAuth.security.auth.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new AccessForbiddenException("Wrong password");
        }

        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new AccessForbiddenException("Password doesn't match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        repository.save(user);
    }
}
