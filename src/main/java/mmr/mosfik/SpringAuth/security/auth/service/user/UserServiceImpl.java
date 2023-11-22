package mmr.mosfik.SpringAuth.security.auth.service.user;

import lombok.RequiredArgsConstructor;
import mmr.mosfik.SpringAuth.exception.AccessForbiddenException;
import mmr.mosfik.SpringAuth.exception.ResourceNotFoundException;
import mmr.mosfik.SpringAuth.security.auth.dto.ChangePasswordRequest;
import mmr.mosfik.SpringAuth.security.auth.model.user.User;
import mmr.mosfik.SpringAuth.security.auth.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    @Override
    public String changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new AccessForbiddenException("Wrong password");
        }

        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new AccessForbiddenException("Password doesn't match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        repository.save(user);

        return "Password changed Successfully!";
    }

    @Override
    public User getUserById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User newUser, Integer id){
        return repository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setGender(newUser.getGender());
                    user.setRole(newUser.getRole());
                    return repository.save(user);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    public List<User> deleteUserById(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        } else {
            repository.deleteById(id);
        }

        return repository.findAll();
    }

    @Override
    public List<User> deleteAllUsers() {
        if (repository.count() != 0) {
            repository.deleteAll();
        } else {
            throw new ResourceNotFoundException("No Users Found!");
        }
        return repository.findAll();
    }
}
