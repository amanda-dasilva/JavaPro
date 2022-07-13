package com.mentorama.movie.services;

import com.mentorama.movie.entities.Movie;
import com.mentorama.movie.exceptions.DuplicateMovieException;
import com.mentorama.movie.exceptions.MovieRatingException;
import com.mentorama.movie.interfaces.IMovieService;
import com.mentorama.movie.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
public class MovieService implements IMovieService {

    private MovieRepository movieRepository = MovieRepository.getInstance();


   public List<Movie> findAll(String movie) {
        if(movie != null) {
            return movieRepository.findAll(movie);
        }
        return movieRepository.findAll();
    }
    public Movie findById(Integer id) {
        return movieRepository.findById(id);
    }


    public Integer add(final Movie movie) throws DuplicateMovieException, MovieRatingException {
        if(movie.getId() == null) {
            movie.setId(movieRepository.count() + 1);
        }
        movieRepository.add(movie);
        return movie.getId();
    }

    public void update(final Movie movie) throws MovieRatingException {
        movieRepository.update(movie);
    }
    public void delete(@PathVariable("id") Integer id) {
        movieRepository.delete(id);
    }


}
