package zhidkov.yaroslav.distributednotes.implementation;


import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhidkov.yaroslav.distributednotes.model.Note;
import zhidkov.yaroslav.distributednotes.repository.NoteRepository;
import zhidkov.yaroslav.distributednotes.service.NoteService;

import java.util.List;
import java.util.Optional;


@Service
public class NoteServiceImplementation implements NoteService {


    private final NoteRepository noteRepository;


    public NoteServiceImplementation(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    @Transactional
    public List<Note> showAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    @Transactional
    public @NotNull Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    @Transactional
    public Note createNote(Long id, String title, String content) {
        Note note = Note.builder()
                .title(title)
                .content(content)
                .build();
        return noteRepository.save(note);
    }

    @Override
    @Transactional
    public Note updateNote(Long id,String title, String content) {
        Note note = Note.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        return noteRepository.save(note);
    }


    @Override
    @Transactional
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
