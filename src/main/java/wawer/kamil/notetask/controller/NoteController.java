package wawer.kamil.notetask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.service.NoteService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("notes")
public class NoteController {

    private final NoteService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<Note>> getAllNotes(){
        return ok(service.getAllNotes());
    }


}
