package wawer.kamil.notetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wawer.kamil.notetask.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}
