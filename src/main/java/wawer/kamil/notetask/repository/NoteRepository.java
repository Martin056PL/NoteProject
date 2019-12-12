package wawer.kamil.notetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wawer.kamil.notetask.model.Note;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note  n inner join n.noteDetailsList l where l.version = l.size")
    List<Note> findLastesVersion();


}
