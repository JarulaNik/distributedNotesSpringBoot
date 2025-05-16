package zhidkov.yaroslav.distributednotes.implementation;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhidkov.yaroslav.distributednotes.model.User;
import zhidkov.yaroslav.distributednotes.repository.UserRepository;
import zhidkov.yaroslav.distributednotes.service.UserService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public @NotNull Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User createUser(String name, String email, String password) {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(String name, String email, String password) {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(email)
                .build();
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
