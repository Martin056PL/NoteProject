package wawer.kamil.notetask.service;

import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.requestDTO.RequestNote;
import wawer.kamil.notetask.model.responseDTO.ResponseAllNotes;
import wawer.kamil.notetask.model.responseDTO.ResponseNote;

import java.util.List;

public interface NoteService {

    List<ResponseAllNotes> getAllNotes();

    ResponseAllNotes getById(Long id) throws NotContentFoundException;

    ResponseAllNotes createNote(RequestNote requestNote);

    ResponseNote updateNoteById(Long id, RequestNote note) throws NotContentFoundException;

    void deleteById(Long id) throws NotContentFoundException;
}
