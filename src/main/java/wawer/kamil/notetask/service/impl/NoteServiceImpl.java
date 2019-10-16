package wawer.kamil.notetask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.repository.NoteRepository;
import wawer.kamil.notetask.service.NoteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;


    @Override
    public List<Note> getAllNotes(){
        return repository.findAll();
    }

    @Override
    public Note getById(Long id) throws NotContentFoundException {
        return repository.findById(id).orElseThrow(() -> new NotContentFoundException("Your note haven't been found"));
    }

    @Override
    public Note createNote(Note note){
        return repository.save(note);
    }

}
