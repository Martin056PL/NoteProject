package wawer.kamil.notetask.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.Note;
import wawer.kamil.notetask.model.NoteDetails;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.responseDTO.ResponseAllNotes;
import wawer.kamil.notetask.model.responseDTO.ResponseNote;
import wawer.kamil.notetask.repository.NoteRepository;
import wawer.kamil.notetask.service.NoteService;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    private final ModelMapper mapper;
    private final Predicate<Note> isDeleted = note -> note.getIsDeleted().equals(false);


    @Override
    public List<ResponseAllNotes> getAllHistoryOfNotes() {
        return repository.findAll()
                .stream()
                .map(this::mapToDtoAllNotes)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseAllNotes getAllHistoryOfNoteById(Long id) throws NotContentFoundException {
        return repository.findById(id)
                .map(this::mapToDtoAllNotes)
                .orElseThrow(NotContentFoundException::new);
    }

    @Override
    public List<ResponseNote> getAllNotes() throws NotContentFoundException {
        List<Note> noteList = repository.findLastesVersion();
        Boolean isAllDeleted = isAllDeleted(noteList);
        if (noteList.isEmpty() || isAllDeleted) {
            throw new NotContentFoundException();
        } else {
            return repository.findLastesVersion().stream().filter(isDeleted)
                    .sorted(Comparator.comparing(Note::getId))
                    .map(this::transformEntityToDTO)
                    .collect(Collectors.toList());
        }
    }

    private Boolean isAllDeleted(List<Note> list){
        return list.stream().map(Note::getIsDeleted).allMatch(n -> n.equals(true));
    }

    private ResponseNote transformEntityToDTO(Note note) {
        int lastVersionOfNoteDetails = note.getNoteDetailsList().size() - 1;
        NoteDetails noteDetails = note.getNoteDetailsList().get(lastVersionOfNoteDetails);
        return transformDataToDto(note, noteDetails);
    }

    @Override
    public ResponseNote getNoteById(Long id) throws NotContentFoundException {
        Note note = repository.findById(id)
                .filter(isDeleted)
                .orElseThrow(NotContentFoundException::new);
        NoteDetails noteDetails = note.getNoteDetailsList().get(note.getNoteDetailsList().size() - 1);
        return transformDataToDto(note, noteDetails);
    }

    private ResponseNote transformDataToDto(Note note, NoteDetails noteDetails) {
        ResponseNote response = new ResponseNote();
        response.setId(note.getId());
        response.setDateOfInitialCreation(note.getDateOfInitialCreation());
        response.setIsDeleted(note.getIsDeleted());
        response.setDateOfModification(noteDetails.getDateOfModification());
        response.setTitle(noteDetails.getTitle());
        response.setContent(noteDetails.getContent());
        return response;
    }

    @Override
    public ResponseAllNotes createNote(RequestNote requestNote) {
        NoteDetails noteDetails = mapFromDto(requestNote);
        Note note = new Note();
        note.addNoteDetails(noteDetails);
        Note save = repository.save(note);
        return mapToDtoAllNotes(save);
    }

    @Override
    public ResponseNote updateNoteById(Long id, RequestNote newestNote) throws NotContentFoundException {
        Note note = repository
                .findById(id)
                .filter(isDeleted)
                .orElseThrow(NotContentFoundException::new);
        updateDetailsOfNote(note, mapFromDto(newestNote));
        repository.save(note);
        return transformDataToDto(note, mapFromDto(newestNote));
    }

    private void updateDetailsOfNote(Note noteFromDB, NoteDetails newestNote) {
        NoteDetails newNoteDetails = new NoteDetails(newestNote.getTitle(), newestNote.getContent());
        int newestVersion = noteFromDB.getNoteDetailsList().size() + 1;
        newNoteDetails.setVersion(newestVersion);
        noteFromDB.addNoteDetails(newNoteDetails);
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

    private ResponseAllNotes mapToDtoAllNotes(Note note) {
        return mapper.map(note, ResponseAllNotes.class);
    }

    private NoteDetails mapFromDto(RequestNote request) {
        return mapper.map(request, NoteDetails.class);
    }
}
