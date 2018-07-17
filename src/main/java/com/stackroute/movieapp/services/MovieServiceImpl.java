package com.stackroute.movieapp.services;

import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.repositories.MovieRepository;
import static org.springframework.data.mongodb.core.query.Query.*;
import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;
@Service
public class MovieServiceImpl implements MovieService {

	MovieRepository movieRepository;
	@Autowired
	public MovieServiceImpl (MovieRepository movieRepository) {
		this.movieRepository=movieRepository;
	}
	@Autowired
	NextSequenceService nextSequenceService;



	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		        Iterable<Movie> movies= getAllMovies();
		        Iterator<Movie> iterator = movies.iterator();
		     //   movie.setId(nextSequenceService.getNextSequence("counter"));
		        while(iterator.hasNext()) {
		            Movie m = iterator.next();
		            if(movie.equals(m)) {
		                throw new MovieAlreadyExistsException("Movie already exists");
		            }
		        }
		        movie.setId(nextSequenceService.getNextSequence("counter"));
		        return movieRepository.save(movie);
		    }		
	

	
	public Iterable<Movie> getAllMovies() {
		
		return movieRepository.findAll();
	}
    
	public void deleteMovie(int id) throws MovieNotFoundException {
		 Optional<Movie> movie=getMovieById(id);
		 if(movie.isPresent()) {
		     movieRepository.deleteById(id);
		 }else {
			 throw new MovieNotFoundException("Movie not found");
		 }
	}
	
	public Movie updateMovie(Movie movie,int id) throws MovieNotFoundException {
		 Optional<Movie> getMovie=getMovieById(id);
		 if(getMovie.isPresent()) {
			 movie.setId(id);
			 return movieRepository.save(movie);
			 }
		 else {
			 throw new MovieNotFoundException("Movie not found");
		 }
		
		
	}
	
    public Optional<Movie> getMovieById(int id) throws MovieNotFoundException {
    	 Optional<Movie>movie=movieRepository.findById(id);
    	 if(movie.isPresent()) {
    		 return movie;  
    	 }else {
    		 throw new MovieNotFoundException("Movie not found");
    	 }
    	 
    }

//
//	@Override
//	public Iterable<Movie> getMovieByTitle(String title) {
//		return movieRepository.getMovieByTitle(title);
//	}
	
}
