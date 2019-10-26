package wawer.kamil.notetask.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PUBLIC;

@NoArgsConstructor(access = PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class ResponseNote {

    private Long id;

    private LocalDateTime dateOfInitialCreation;

    private String title;

    private String content;

    private LocalDateTime dateOfModification;

    private Boolean isDeleted;
}
