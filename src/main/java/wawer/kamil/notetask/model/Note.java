package wawer.kamil.notetask.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class Note implements Serializable {

    private static final long serialVersionUID = 2359335976189765545L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private final LocalDateTime dateOfInitialCreation = now();

    private LocalDateTime dateOfModification = now();
}
