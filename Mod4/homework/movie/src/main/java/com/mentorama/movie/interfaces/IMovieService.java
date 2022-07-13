package com.mentorama.movie.interfaces;

import com.mentorama.movie.entities.Movie;
import com.mentorama.movie.exceptions.DuplicateMovieException;
import com.mentorama.movie.exceptions.MovieRatingException;

import java.util.List;

public interface IMovieService {

    List<Movie> findAll(String movie);
    Movie findById(Integer id);
    Integer add(final Movie movie) throws DuplicateMovieException, MovieRatingException;
    void update(final Movie movie) throws MovieRatingException;
    void delete(Integer id);

}
