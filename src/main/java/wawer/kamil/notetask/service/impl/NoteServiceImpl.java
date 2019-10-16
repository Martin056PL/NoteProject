package wawer.kamil.notetask.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.repository.NoteRepository;
import wawer.kamil.notetask.service.NoteService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String YOUR_NOTE_HAVEN_T_BEEN_FOUND = "Your note haven't been found";
    private final NoteRepository repository;


    @Override
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @Override
    public Note getById(Long id) throws NotContentFoundException {
        return repository.findById(id).orElseThrow(NotContentFoundException::new);
    }

    @Override
    public Note createNote(Note note) {
        return repository.save(note);
    }

    @Override
    public Note updateNoteById(Long id, Note newestNote) throws NotContentFoundException {
        Note note = repository.findById(id).orElseThrow(NotContentFoundException::new);
        updateNote(note, newestNote);
        return repository.save(note);
    }

    private void updateNote(Note noteFromDB, Note newestNote) {
        noteFromDB.setTitle(newestNote.getTitle());
        noteFromDB.setContent(newestNote.getContent());
        noteFromDB.setDateOfModification(LocalDateTime.now());
    }

    @Override
    public void deleteById(Long id) throws NotContentFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotContentFoundException();
        }
    }


}
