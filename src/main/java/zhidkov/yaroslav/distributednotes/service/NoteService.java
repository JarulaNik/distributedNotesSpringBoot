package zhidkov.yaroslav.distributednotes.service;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import zhidkov.yaroslav.distributednotes.model.Note;

import java.util.List;
import java.util.Optional;

@Service
public interface NoteService {

    List<Note> showAllNotes();

    @NotNull Optional<Note> getNoteById(Long id);

    Note createNote(Long id, String title, String content);

    Note updateNote(Long id, String title, String content);

    void deleteNoteById(Long id);
}
