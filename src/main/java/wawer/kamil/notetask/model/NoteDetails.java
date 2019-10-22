package wawer.kamil.notetask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "note")
public class NoteDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long detailsId;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private LocalDateTime dateOfModification = now();

    private Long version = 1L;

    @ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH},fetch = LAZY)
    @JoinColumn(name = "id")
    @JsonIgnore
    private Note note;

    public NoteDetails(String title, String content) {
        this.title = title;
        this.content = content;
        this.dateOfModification = now();
        this.version = 1L;
    }
}
