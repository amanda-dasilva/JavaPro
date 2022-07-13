package com.mentorama.movie.controllers;

import com.mentorama.movie.entities.Movie;
import com.mentorama.movie.exceptions.DuplicateMovieException;
import com.mentorama.movie.exceptions.MovieRatingException;
import com.mentorama.movie.interfaces.IMovieService;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final List<Movie> movies;

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
        this.movies = new ArrayList<>();
    }
    @GetMapping
    public List<Movie> findAll(@RequestParam(required = false) String movie) {
        return movieService.findAll(movie);
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable("id") Integer id) {
        return movieService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody final Movie movie) throws DuplicateMovieException, MovieRatingException {
        Integer id = movieService.add(movie);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody final Movie movie) throws MovieRatingException {
        movieService.update(movie);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        movieService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
