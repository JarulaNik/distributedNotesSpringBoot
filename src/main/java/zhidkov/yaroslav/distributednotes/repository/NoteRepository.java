package zhidkov.yaroslav.distributednotes.repository;

import zhidkov.yaroslav.distributednotes.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class NoteRepository {

    private final List<Note> NOTES = new ArrayList<>();


    public List<Note> showAllNotes() {
        return NOTES;
    }

    public Note showNoteById(String id) {
        return NOTES.stream()
                .filter(element -> element.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Note createNote(Note note) {
        return null;
    }

    public Note updateNote(Note note) {
        var noteIndex = IntStream.range(0, NOTES.size())
                .filter(index -> NOTES.get(index).getId().equals(note.getId()))
                .findFirst()
                .orElse(-1);
        if (noteIndex > -1) {
            NOTES.set(noteIndex, note);
            return note;
        }
        return null;
    }

    public void deleteById(String id) {
        var note = showNoteById(id);
        if (note != null) {
            NOTES.remove(note);
        }
    }
}