package zhidkov.yaroslav.distributednotes.implementation;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhidkov.yaroslav.distributednotes.dto.LoginRequest;
import zhidkov.yaroslav.distributednotes.dto.RegisterRequest;
import zhidkov.yaroslav.distributednotes.dto.TokenPair;
import zhidkov.yaroslav.distributednotes.model.User;
import zhidkov.yaroslav.distributednotes.repository.UserRepository;
import zhidkov.yaroslav.distributednotes.service.AuthService;
import zhidkov.yaroslav.distributednotes.service.JWTService;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    @Transactional
    public void registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("User with same credentials already exists");
        }

        User user = User
                .builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();
        userRepository.save(user);
    }

    @Override
    public TokenPair login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return jwtService.generateTokenPair(authentication);
        } catch (AuthenticationException e) {
            System.err.println("Ошибка аутентификации: " + e.getMessage());
            throw new RuntimeException("Неверные учетные данные");
        }
    }
}
