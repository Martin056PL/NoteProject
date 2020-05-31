package wawer.kamil.notetask.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "note")
@EntityListeners(AuditingEntityListener.class)
public class Note implements Serializable {

    private static final long serialVersionUID = 2359335976189765545L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_of_initial_creation")
    private LocalDateTime dateOfInitialCreation;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    //@ElementCollection(fetch = LAZY)
    @OneToMany(mappedBy = "note", cascade = ALL)
    //@JoinColumn(name = "id")
    private List<NoteDetails> noteDetailsList;

    public Note() {
        this.dateOfInitialCreation = now();
        this.isDeleted = false;
        this.noteDetailsList = initNoteDetailsList();
    }

    private List<NoteDetails> initNoteDetailsList() {
        if (noteDetailsList == null) {
            return noteDetailsList = new ArrayList<>();
        } else {
            return noteDetailsList;
        }
    }

    public void addNoteDetails(NoteDetails noteDetails){
        noteDetailsList.add(noteDetails);
        noteDetails.setNote(this);
    }


}
