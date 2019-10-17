package wawer.kamil.notetask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.responseDTO.ResponseNote;
import wawer.kamil.notetask.service.NoteService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("notes")
@RestControllerAdvice
public class NoteController {

    private final NoteService service;

    @GetMapping
    public ResponseEntity<List<ResponseNote>> getAllNotes() {
        return ok(service.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNote> getById(@PathVariable Long id) throws NotContentFoundException {
        return ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseNote> addNewNote(@Valid @RequestBody RequestNote request) throws URISyntaxException {
        ResponseNote response = service.createNote(request);
        return created(new URI("note=" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseNote> updateNoteById(@PathVariable Long id, @Valid @RequestBody RequestNote request)
            throws NotContentFoundException {
        return ok(service.updateNoteById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteById(@PathVariable Long id) throws NotContentFoundException {
        service.deleteById(id);
        return noContent().build();
    }
}
