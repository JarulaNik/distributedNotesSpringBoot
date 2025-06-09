package zhidkov.yaroslav.distributednotes.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zhidkov.yaroslav.distributednotes.model.Image;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @NotNull
    Optional<Image> findById(@NotNull Long id);

    void deleteImageById(Long id);
}
