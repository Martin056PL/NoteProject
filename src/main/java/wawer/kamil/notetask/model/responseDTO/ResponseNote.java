package wawer.kamil.notetask.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class ResponseNote {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime dateOfInitialCreation;

    private LocalDateTime dateOfModification;
}
