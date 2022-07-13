package com.mentorama.movie.repositories;

import com.mentorama.movie.entities.Movie;
import com.mentorama.movie.exceptions.DuplicateMovieException;
import com.mentorama.movie.exceptions.MovieRatingException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRepository {
    private static MovieRepository movieRepository;

    public static MovieRepository getInstance() {
        if(movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    private final List<Movie> movies;

    private MovieRepository() {
        this.movies = new LinkedList<>();
    }

    public List<Movie> findAll(){
        return movies;
    }
    public List<Movie> findAll(final String movie) {
        return movies.stream()
                .filter(mv -> mv.getTitle().contains(movie))
                .collect(Collectors.toList());
    }
    public Movie findById(Integer id){
        return this.movies.stream()
                .filter(mv -> mv.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void add(Movie movie) throws DuplicateMovieException, MovieRatingException {
        checkDuplicateMovies(movie);
        checkMovieRating(movie);
        this.movies.add(movie);
    }

    public int count() {
        return movies.size();
    }
    public void update(final Movie movie) throws MovieRatingException {
        checkMovieRating(movie);
        movies.stream()
                .filter(mv -> mv.getId().equals(movie.getId()))
                .forEach(mv -> mv.setTitle(movie.getTitle()));
    }
    public void delete(@PathVariable("id") Integer id) {
        movies.removeIf(mv -> mv.getId().equals(id));
    }

    public void checkDuplicateMovies (Movie movie) throws DuplicateMovieException {
        Boolean checkTitleExists = movies.stream()
                .anyMatch(mv -> mv.getTitle().equals(movie.getTitle()));

        Boolean checkDirectoriesExists = movies.stream()
                .anyMatch(mv -> mv.getDirectorName().equals(movie.getDirectorName()));

        Boolean checkYearExists = movies.stream()
                .anyMatch(mv -> mv.getYear().equals(movie.getYear()));

        if(checkTitleExists && checkDirectoriesExists && checkYearExists) {
            throw new DuplicateMovieException();
        }

    }

    public void checkMovieRating(Movie movie) throws MovieRatingException {
        if(movie.getRate() < 1 || movie.getRate() > 5) {
            throw new MovieRatingException();
        }

    }

}
