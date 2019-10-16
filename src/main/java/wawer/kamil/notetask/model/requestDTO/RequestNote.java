package wawer.kamil.notetask.model.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestNote {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
