package wawer.kamil.notetask.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.model.responseDTO.ResponseNote;
import wawer.kamil.notetask.service.NoteService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("notes")
public class NoteController {

    private final NoteService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        return ok(service.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id) throws NotContentFoundException {
        return ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseNote> addNewNote(@RequestBody RequestNote requestNote){
        Note note = mapper.map(requestNote, Note.class);
        Note savedNote = service.createNote(note);
        ResponseNote response = mapper.map(savedNote,ResponseNote.class);
        return ok(response);
    }

}
