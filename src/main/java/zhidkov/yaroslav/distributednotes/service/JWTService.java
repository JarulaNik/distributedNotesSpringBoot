package zhidkov.yaroslav.distributednotes.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import zhidkov.yaroslav.distributednotes.dto.TokenPair;

public interface JWTService {

    TokenPair generateTokenPair(Authentication authentication);

    String generateAccessToken(Authentication authentication);

    String generateRefreshToken(Authentication authentication);

    boolean isValidToken(String token, UserDetails userDetails);

    String extractUsernameFromToken(String token);
}