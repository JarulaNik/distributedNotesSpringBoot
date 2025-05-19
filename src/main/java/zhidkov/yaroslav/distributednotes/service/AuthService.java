package zhidkov.yaroslav.distributednotes.service;

import zhidkov.yaroslav.distributednotes.dto.LoginRequest;
import zhidkov.yaroslav.distributednotes.dto.RegisterRequest;
import zhidkov.yaroslav.distributednotes.dto.TokenPair;

public interface AuthService {

    void registerUser(RegisterRequest registerRequest);

    TokenPair login(LoginRequest loginRequest);
}
