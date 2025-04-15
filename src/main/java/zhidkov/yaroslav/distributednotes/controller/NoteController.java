package zhidkov.yaroslav.distributednotes.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import zhidkov.yaroslav.distributednotes.model.Note;
import zhidkov.yaroslav.distributednotes.service.NoteService;

import java.util.List;

@Data
@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api/v1")
public class NoteController {

    @NonNull
    private final NoteService noteService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/notes")
    public List<Note> showAllNotes() {
        return noteService.showAllNotes();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/notes/{id}")
    public Note showNoteById(@PathVariable String id) {
        Note note = noteService.showNoteById(id);
        if (note == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
        }
        return note;
    }


    @PostMapping("/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@Valid @RequestBody Note note) {
        if (noteService.existsById(note.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Note already exists");
        }
        return noteService.createNote(note.getId(), note.getTitle(), note.getContent());
    }

    @PutMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Note updateNote(@PathVariable String id, @Valid @RequestBody Note note) {
        Note updatedNote = noteService.updateNote(id, note.getTitle(), note.getContent());
        if (updatedNote == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
        }
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNote(@PathVariable String id) {
        if (noteService.showNoteById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note not found");
        }
        noteService.deleteById(id);
    }
}