package wawer.kamil.notetask.model.DTO;

import javax.validation.constraints.NotEmpty;

public class NoteDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

}
