package zhidkov.yaroslav.distributednotes.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import zhidkov.yaroslav.distributednotes.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @NotNull Optional<User> findById(@NotNull String id);

    void deleteById(@NotNull String id);
}
