package wawer.kamil.notetask.service;

import wawer.kamil.notetask.exceptions.NotContentFoundException;
import wawer.kamil.notetask.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();

    Note getById(Long id) throws NotContentFoundException;

    Note createNote(Note note);
}
