package wawer.kamil.notetask.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wawer.kamil.notetask.model.DTO.NoteDTO;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.repository.NoteRepository;
import wawer.kamil.notetask.service.NoteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<Note> getAllNotes(){
        return repository.findAll();
    }

}
