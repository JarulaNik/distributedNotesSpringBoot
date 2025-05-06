package zhidkov.yaroslav.distributednotes.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import zhidkov.yaroslav.distributednotes.model.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @NotNull
    Optional<Image> findById(@NotNull Long id);

    void deleteImageById(Long id);
}
