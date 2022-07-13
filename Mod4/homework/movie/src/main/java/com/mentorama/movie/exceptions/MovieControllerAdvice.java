package com.mentorama.movie.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class MovieControllerAdvice {
    final static Logger LOGGER = LoggerFactory.getLogger(MovieControllerAdvice.class);

    @ExceptionHandler({DuplicateMovieException.class})
    public ResponseEntity<String> handleExceptionDuplicateMovie(final DuplicateMovieException e) {
        final String duplicateMovieException = "The movie already exists";
        LOGGER.error(duplicateMovieException);
        return new ResponseEntity<>(duplicateMovieException, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MovieRatingException.class})
    public ResponseEntity<String> handleExceptionMovieRating(final MovieRatingException e) {
        final String movieRatingException = "Please, enter a number between 1 and 5 to rate the movie";
        LOGGER.error(movieRatingException);
        return new ResponseEntity<>(movieRatingException, HttpStatus.UNAUTHORIZED);
    }
}
