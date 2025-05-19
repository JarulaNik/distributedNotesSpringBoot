package zhidkov.yaroslav.distributednotes.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zhidkov.yaroslav.distributednotes.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(@Email String email);

    Boolean existsByEmail(@Email @NotEmpty(message = "Email is required") String email);

}
