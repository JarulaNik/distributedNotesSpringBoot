package zhidkov.yaroslav.distributednotes.config;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import zhidkov.yaroslav.distributednotes.repository.UserRepository;

@Configuration
@AllArgsConstructor
public class UserConfig {

    private final UserRepository userRepository;
    
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> (UserDetails) userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}
