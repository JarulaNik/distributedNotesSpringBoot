package zhidkov.yaroslav.distributednotes.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhidkov.yaroslav.distributednotes.model.Note;
import zhidkov.yaroslav.distributednotes.service.NoteService;


import java.util.List;


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
    public ResponseEntity<Note> showNoteById(@PathVariable String id) {
        Note note = noteService.showNoteById(id);
        if (note != null) {
            return ResponseEntity.ok(note);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        if (noteService.existsById(note.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Note createdNote = noteService.createNote(note.getId(), note.getTitle(), note.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @Valid @RequestBody Note note) {
        Note updatedNote = noteService.updateNote(id, note.getTitle(), note.getContent());
        if (updatedNote != null) {
            return ResponseEntity.ok(updatedNote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        if (noteService.showNoteById(id) != null) {
            noteService.deleteNoteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}