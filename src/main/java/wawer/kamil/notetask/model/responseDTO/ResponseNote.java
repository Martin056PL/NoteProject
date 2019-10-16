package wawer.kamil.notetask.model.responseDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class ResponseNote {

    @JsonIgnore
    private Long id;

    private String title;

    private String content;

    private LocalDateTime dateOfInitialCreation;

    private LocalDateTime dateOfModification;
}
