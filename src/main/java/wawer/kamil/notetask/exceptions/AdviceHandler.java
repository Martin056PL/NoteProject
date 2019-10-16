package wawer.kamil.notetask.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class AdviceHandler extends ResponseEntityExceptionHandler {

    private final ExceptionFormat exceptionFormat;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> validationMap = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            validationMap.put(error.getField(), error.getDefaultMessage());
        }

        String mapToString = convertMapToEditedString(validationMap);
        exceptionFormat.setStatus(HttpStatus.BAD_REQUEST);
        exceptionFormat.setMessage(mapToString);
        return ResponseEntity.badRequest().body(exceptionFormat);
    }

    private String convertMapToEditedString(Map<String, String> map) {
        String string = map.toString().replace("=", " = ");
        return string.substring(1, string.length() - 1);
    }

    @ExceptionHandler(NotContentFoundException.class)
    public ResponseEntity<Object> notFoundHandler(){
        exceptionFormat.setStatus(HttpStatus.NOT_FOUND);
        exceptionFormat.setMessage("Your note haven't been found");
        return new ResponseEntity<>(exceptionFormat,exceptionFormat.getStatus());
    }
}
