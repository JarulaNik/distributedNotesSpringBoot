package zhidkov.yaroslav.distributednotes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Builder
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "name")
    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @Email
    @Column(name = "email", unique = true)
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password is required")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Column(name = "is_account_non_locked")
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Column(name = "is_account_non_expired")
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Column(name = "is_enabled")
    @Override
    public boolean isEnabled() {
        return true;
    }
}