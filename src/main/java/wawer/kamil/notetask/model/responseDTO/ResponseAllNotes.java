package wawer.kamil.notetask.model.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class ResponseAllNotes {

    private Long id;

    private LocalDateTime dateOfInitialCreation;

    private List<ResponseDetails> noteDetailsList;

}
