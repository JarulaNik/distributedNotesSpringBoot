package zhidkov.yaroslav.distributednotes.service;
import zhidkov.yaroslav.distributednotes.model.Note;

import java.util.List;


public interface NoteService {

    List<Note> showAllNotes();

    Note showNoteById(String id);

    Note createNote(String id, String title, String content);

    Note updateNote(String id, String title, String content);

    void deleteNoteById(String id);

    boolean existsById(String id);

}
