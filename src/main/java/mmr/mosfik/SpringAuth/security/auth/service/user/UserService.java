package mmr.mosfik.SpringAuth.security.auth.service.user;

import mmr.mosfik.SpringAuth.security.auth.dto.ChangePasswordRequest;
import mmr.mosfik.SpringAuth.security.auth.model.user.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    String changePassword(ChangePasswordRequest request, Principal connectedUser);
    User getUserById(Integer id);
    List<User> getAllUsers();
    User updateUser(User user, Integer id);
    List<User> deleteUserById(Integer id);
    List<User> deleteAllUsers();

}