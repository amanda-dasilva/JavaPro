package br.com.mentorama.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentControllerAdvice {

    final static Logger LOGGER = LoggerFactory.getLogger(StudentControllerAdvice.class);

    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<String> handleException(final StudentNotFoundException e) {
        final String studentNotFound = "Student not found!";
        LOGGER.error(studentNotFound);
        return new ResponseEntity<>(studentNotFound, HttpStatus.NOT_FOUND);
    }
}