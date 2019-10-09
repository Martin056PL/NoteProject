package wawer.kamil.notetask.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Note implements Serializable {

    private static final long serialVersionUID = 2359335976189765545L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private final LocalDateTime dateOfInitialCreation;

    private LocalDateTime dateOfModification;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.dateOfInitialCreation = now();
        this.dateOfModification = now();
    }
}
