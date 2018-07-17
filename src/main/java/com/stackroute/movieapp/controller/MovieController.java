package com.stackroute.movieapp.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieService;


import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("api/v1")
public class MovieController {
	@Autowired
	Environment env;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    MovieService movieService;
    @Autowired
    MovieController(MovieService movieService){
    	this.movieService=movieService;
    }
    @RequestMapping(value = "/home" , method = RequestMethod.GET)
	public String displayRestaurant() {
		return "Hello";
	}
    @PostMapping("/movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
    	try {
    		    logger.debug("This is a debug message");
    	        logger.info("This is an info message");
    	        logger.warn("This is a warn message");
    	    
    	        //Environment variable property//
    		    System.out.println( env.getProperty("com.stackroute.username"));
		    	movieService.saveMovie(movie);
		    	return new ResponseEntity<Movie> (movie,HttpStatus.CREATED);
		    }
    	 catch(MovieAlreadyExistsException e){
    		 logger.error("This is an MovieAlreadyExistsException error");
             return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
         }
        
    }
    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovie(){
    	return new ResponseEntity<Iterable<Movie>> (movieService.getAllMovies(),HttpStatus.OK);
    }
    @GetMapping("/getByID/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable int id){
    	try {
    	   Optional<Movie> movie=movieService.getMovieById(id);
    	   return new ResponseEntity<Optional<Movie>> (movie,HttpStatus.CREATED);
    	}catch(MovieNotFoundException e) {
    	   return new ResponseEntity<String> (e.getMessage(),HttpStatus.CONFLICT);
    	}
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id){
    	try {
    	     movieService.deleteMovie(id);
    	     return new ResponseEntity<String> ("Deleted",HttpStatus.OK);
    	}catch(MovieNotFoundException e) {
     	   return new ResponseEntity<String> (e.getMessage(),HttpStatus.CONFLICT);
     	} 
    	
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie,@PathVariable int id){
    	try {
    		Movie movieUpdated=movieService.updateMovie(movie,id);
    		return new ResponseEntity<Movie> (movieUpdated,HttpStatus.CREATED);
    	}catch(MovieNotFoundException e) {
    	   return new ResponseEntity<String> (e.getMessage(),HttpStatus.CONFLICT);
    	}  
    }
//
//    @GetMapping("/getByTitle/{title}")
//    public ResponseEntity<?> getMovieByTitle(@PathVariable String title){
//    	return new ResponseEntity<Iterable<Movie>> (movieService.getMovieByTitle(title),HttpStatus.OK);
//    }
    
}
