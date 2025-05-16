package zhidkov.yaroslav.distributednotes.service;

import jakarta.validation.constraints.Email;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import zhidkov.yaroslav.distributednotes.model.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> showAllUsers();

    @NotNull Optional<User> getUserById(String id);

    User createUser(String userName, @Email String email, String password);

    User updateUser(String userName, @Email String email, String password);

    void deleteUserById(String id);
}
