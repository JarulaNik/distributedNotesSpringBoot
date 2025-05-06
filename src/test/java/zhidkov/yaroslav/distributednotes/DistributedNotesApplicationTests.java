//package zhidkov.yaroslav.distributednotes;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import zhidkov.yaroslav.distributednotes.controller.NoteController;
//import zhidkov.yaroslav.distributednotes.model.Note;
//import zhidkov.yaroslav.distributednotes.service.NoteService;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//class NoteControllerTest {
//
//    @Mock
//    private NoteService noteService;
//
//    @InjectMocks
//    private NoteController noteController;
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void showAllNotes_shouldReturnAllNotes() throws Exception {
//        List<Note> notes = Arrays.asList(
//                new Note("1", "Title 1", "Content 1"),
//                new Note("2", "Title 2", "Content 2")
//        );
//        when(NoteService.showAllNotes()).thenReturn(notes);
//
//        mockMvc.perform(get("/api/v1/notes"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value("1"))
//                .andExpect(jsonPath("$[1].id").value("2"));
//    }
//
//    @Test
//    void showNoteById_shouldReturnNote() throws Exception {
//        Note note = new Note("1", "Title 1", "Content 1");
//        when(noteService.showNoteById("1")).thenReturn(note);
//
//        mockMvc.perform(get("/api/v1/notes/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value("1"));
//    }
//
//    @Test
//    void showNoteById_shouldReturnNotFound_whenNoteDoesNotExist() throws Exception {
//        when(noteService.showNoteById("1")).thenReturn(null);
//
//        mockMvc.perform(get("/api/v1/notes/1"))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void createNote_shouldCreateNote() throws Exception {
//        Note note = new Note("1", "Title 1", "Content 1");
//        when(noteService.createNote("1", "Title 1", "Content 1")).thenReturn(note);
//        when(noteService.existsById("1")).thenReturn(false);
//
//        mockMvc.perform(post("/api/v1/notes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(note)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value("1"));
//    }
//
//    @Test
//    void createNote_shouldReturnConflict_whenNoteAlreadyExists() throws Exception {
//        Note note = new Note("1", "Title 1", "Content 1");
//        when(noteService.existsById("1")).thenReturn(true);
//
//        mockMvc.perform(post("/api/v1/notes")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(note)))
//                .andExpect(status().isConflict());
//    }
//
//    @Test
//    void updateNote_shouldUpdateNote() throws Exception {
//        Note note = new Note("1", "Updated Title", "Updated Content");
//        when(noteService.updateNote("1", "Updated Title", "Updated Content")).thenReturn(note);
//
//        mockMvc.perform(put("/api/v1/notes/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(note)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.title").value("Updated Title"));
//    }
//
//    @Test
//    void updateNote_shouldReturnNotFound_whenNoteDoesNotExist() throws Exception {
//        Note note = new Note("1", "Updated Title", "Updated Content");
//        when(noteService.updateNote("1", "Updated Title", "Updated Content")).thenReturn(null);
//
//        mockMvc.perform(put("/api/v1/notes/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(note)))
//                .andExpect(status().isNotFound());
//    }
//
//
//    @Test
//    void deleteNote_shouldDeleteNote() throws Exception {
//        Note note = new Note("1", "Title 1", "Content 1");
//        when(noteService.showNoteById("1")).thenReturn(note); // Добавляем мокирование showNoteById
//        mockMvc.perform(delete("/api/v1/notes/1"))
//                .andExpect(status().isOk());
//
//        verify(noteService, times(1)).deleteNoteById("1");
//    }
//
//    @Test
//    void deleteNote_shouldReturnNotFound_whenNoteDoesNotExist() throws Exception {
//        when(noteService.showNoteById("1")).thenReturn(null);
//        mockMvc.perform(delete("/api/v1/notes/1"))
//                .andExpect(status().isNotFound());
//    }
//}