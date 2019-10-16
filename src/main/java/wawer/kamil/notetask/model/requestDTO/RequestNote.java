package wawer.kamil.notetask.model.requestDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestNote {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
