package br.com.mentorama.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {

    final static Logger LOGGER = LoggerFactory.getLogger(GenericControllerAdvice.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> handleException(final ResourceNotFoundException e) {
        final String resourceNotFound = "Resource not found!";
        LOGGER.error(resourceNotFound);
        return new ResponseEntity<>(resourceNotFound, HttpStatus.NOT_FOUND);
    }
}
