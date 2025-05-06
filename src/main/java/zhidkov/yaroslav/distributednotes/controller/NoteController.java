package zhidkov.yaroslav.distributednotes.controller;

import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhidkov.yaroslav.distributednotes.model.Note;
import zhidkov.yaroslav.distributednotes.service.NoteService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1")
public class NoteController {


    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/notes")
    public ResponseEntity<List<Note>> showAllNotes() {
        List<Note> notes = noteService.showAllNotes();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Optional<Note>> getNoteById(@PathVariable Long id) {
        @NotNull Optional<Note> note = noteService.getNoteById(id);
        if (note.isPresent()) {
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Optional<Note> existingNote = noteService.getNoteById(note.getId());
        if (existingNote.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Note createdNote = noteService.createNote(note.getId(), note.getTitle(), note.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote( @Valid @RequestBody Note note) {
        Note updatedNote = noteService.updateNote(note.getId(), note.getTitle(), note.getContent());
        if (updatedNote != null) {
            return ResponseEntity.ok(updatedNote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        if (noteService.getNoteById(id).isPresent()) {
            noteService.deleteNoteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}