package com.stackroute.movieapp.services;

import java.util.Optional;


import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;

public interface MovieService {

	public Movie saveMovie(Movie movie)throws MovieAlreadyExistsException;
	public Iterable<Movie> getAllMovies();
	public boolean deleteMovie(int id) throws MovieNotFoundException;
	public Movie updateMovie(Movie movie,int id) throws MovieNotFoundException;
	public Optional<Movie> getMovieById(int id) throws MovieNotFoundException;
//	public Iterable<Movie> getMovieByTitle(String title);
}
