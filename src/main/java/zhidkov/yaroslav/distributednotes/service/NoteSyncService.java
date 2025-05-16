package zhidkov.yaroslav.distributednotes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zhidkov.yaroslav.distributednotes.model.Note;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteSyncService {

    private final NoteService noteService;

    @Scheduled(fixedRate = 60000)
    public void syncNotes() {
        List<Note> notes = noteService.showAllNotes();

    }
}