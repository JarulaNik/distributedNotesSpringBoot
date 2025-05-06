package zhidkov.yaroslav.distributednotes.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import zhidkov.yaroslav.distributednotes.model.Note;
import java.util.Optional;


public interface NoteRepository extends JpaRepository<Note, Long> {

    @NotNull Optional<Note> findById(@NotNull Long id);

    void deleteById(@NotNull Long id);
}