package zhidkov.yaroslav.distributednotes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zhidkov.yaroslav.distributednotes.dto.LoginRequest;
import zhidkov.yaroslav.distributednotes.dto.RegisterRequest;
import zhidkov.yaroslav.distributednotes.dto.TokenPair;
import zhidkov.yaroslav.distributednotes.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok("Register successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenPair> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        TokenPair tokenPair = authService.login(loginRequest);
        return ResponseEntity.ok(tokenPair);
    }
}
