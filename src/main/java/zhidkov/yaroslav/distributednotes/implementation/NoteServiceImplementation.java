package zhidkov.yaroslav.distributednotes.implementation;


import org.springframework.stereotype.Service;
import zhidkov.yaroslav.distributednotes.model.Note;
import zhidkov.yaroslav.distributednotes.service.NoteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteServiceImplementation implements NoteService {

    private final Map<String, Note> noteMap = new HashMap<>();

    @Override
    public List<Note> showAllNotes() {
        return new ArrayList<>(noteMap.values());
    }

    @Override
    public Note showNoteById(String id) {
        return noteMap.get(id);
    }

    @Override
    public Note createNote(String id, String title, String content) {
        Note note = new Note(id, title, content);
        noteMap.put(id, note);
        return note;
    }

    @Override
    public Note updateNote(String id, String title, String content) {
        if (noteMap.containsKey(id)) {
            Note note = noteMap.get(id);
            note.setTitle(title);
            note.setContent(content);
            noteMap.put(id, note);
            return note;
        }
        return null;
    }

    @Override
    public void deleteNoteById(String id) {
        noteMap.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return noteMap.containsKey(id);
    }

}
