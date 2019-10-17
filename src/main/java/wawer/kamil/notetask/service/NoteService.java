package wawer.kamil.notetask.service;

import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.responseDTO.ResponseNote;

import java.util.List;

public interface NoteService {

    List<ResponseNote> getAllNotes();

    ResponseNote getById(Long id) throws NotContentFoundException;

    ResponseNote createNote(RequestNote requestNote);

    ResponseNote updateNoteById(Long id, RequestNote note) throws NotContentFoundException;

    void deleteById(Long id) throws NotContentFoundException;
}
