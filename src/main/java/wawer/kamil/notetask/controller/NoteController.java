package wawer.kamil.notetask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.responseDTO.ResponseAllNotes;
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

    @GetMapping("/all")
    public ResponseEntity<List<ResponseAllNotes>> getAllHistoryOfNotes() {
        return ok(service.getAllHistoryOfNotes());
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<ResponseAllNotes> getAllHistoryOfNoteById(@PathVariable Long id) throws NotContentFoundException {
        return ok(service.getAllHistoryOfNoteById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNote> getAllNotes(@PathVariable Long id) throws NotContentFoundException {
        return ok(service.getNoteById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseAllNotes> addNewNote(@Valid @RequestBody RequestNote request) throws URISyntaxException {
        ResponseAllNotes response = service.createNote(request);
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
