package wawer.kamil.notetask.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotContentFoundException extends Exception {

    private static final long serialVersionUID = 6924825127891728300L;

    public NotContentFoundException(String message){
        super(message);
    }
}
