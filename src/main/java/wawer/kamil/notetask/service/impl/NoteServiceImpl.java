package wawer.kamil.notetask.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.responseDTO.ResponseNote;
import wawer.kamil.notetask.repository.NoteRepository;
import wawer.kamil.notetask.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final ModelMapper mapper;
    private final Predicate<Note> isDeleted =  note -> note.getIsDeleted().equals(false);


    @Override
    public List<ResponseNote> getAllNotes() {
        return repository.findAll()
                .stream().filter(isDeleted).map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ResponseNote getById(Long id) throws NotContentFoundException {
        return repository.findById(id).filter(isDeleted)
                .map(this::mapToDto).orElseThrow(NotContentFoundException::new);
    }

    @Override
    public ResponseNote createNote(RequestNote requestNote) {
        Note note = repository.save(mapFromDto(requestNote));
        return mapToDto(note);
    }

    @Override
    public ResponseNote updateNoteById(Long id, RequestNote newestNote) throws NotContentFoundException {
        Note note = repository.findById(id).filter(isDeleted).orElseThrow(NotContentFoundException::new);
        updateNote(note, mapFromDto(newestNote));
        return mapToDto(repository.save(note));
    }

    private void updateNote(Note noteFromDB, Note newestNote) {
        noteFromDB.setTitle(newestNote.getTitle());
        noteFromDB.setContent(newestNote.getContent());
        noteFromDB.setDateOfModification(LocalDateTime.now());
    }

    @Override
    public void deleteById(Long id) throws NotContentFoundException {
        repository.findById(id)
                .filter(isDeleted)
                .map(note -> {
                    note.setIsDeleted(true);
                    return repository.save(note);
                }).orElseThrow(NotContentFoundException::new);
    }

    private ResponseNote mapToDto(Note note) {
        return mapper.map(note, ResponseNote.class);
    }

    private Note mapFromDto(RequestNote request) {
        return mapper.map(request, Note.class);
    }
}
