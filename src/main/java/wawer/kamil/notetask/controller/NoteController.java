package wawer.kamil.notetask.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.model.responseDTO.ResponseNote;
import wawer.kamil.notetask.service.NoteService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("notes")
@RestControllerAdvice
public class NoteController {

    private final NoteService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ResponseNote>> getAllNotes() {
        List<ResponseNote> responseNoteList = service.getAllNotes()
                .stream().map(note -> mapper.map(note,ResponseNote.class)).collect(Collectors.toList());
        return ok(responseNoteList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNote> getById(@PathVariable Long id) throws NotContentFoundException {
        Note note = service.getById(id);
        ResponseNote response = mapper.map(note,ResponseNote.class);
        return ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseNote> addNewNote(@Valid @RequestBody RequestNote request) throws URISyntaxException {
        Note note = mapper.map(request, Note.class);
        Note savedNote = service.createNote(note);
        ResponseNote response = mapper.map(savedNote, ResponseNote.class);
        return created(new URI("note=" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseNote> updateNoteById(@PathVariable Long id, @Valid @RequestBody RequestNote request) throws NotContentFoundException {
        Note note = mapper.map(request, Note.class);
        Note updatedNote = service.updateNoteById(id, note);
        ResponseNote response = mapper.map(updatedNote, ResponseNote.class);
        return ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable Long id) throws NotContentFoundException {
        service.deleteById(id);
        return noContent().build();
    }
}
