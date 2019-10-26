package wawer.kamil.notetask.model.requestDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestNote {

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String content;
}
