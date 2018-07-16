package com.stackroute.movieapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieService;

@RestController
@RequestMapping("api/v1")
public class MovieController {
	
    MovieService movieService;
    @Autowired
    MovieController(MovieService movieService){
    	this.movieService=movieService;
    }
    
    @PostMapping("/movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
    	try {
		    	movieService.saveMovie(movie);
		    	return new ResponseEntity<Movie> (movie,HttpStatus.CREATED);
		    }
    	 catch(MovieAlreadyExistsException e){
             return new ResponseEntity<String>("User Already Exists",HttpStatus.CONFLICT);
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
    	   return new ResponseEntity<String> ("Movie of this Id not present",HttpStatus.CONFLICT);
    	}
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id){
    	try {
    	     movieService.deleteMovie(id);
    	     return new ResponseEntity<String> ("Deleted",HttpStatus.OK);
    	}catch(MovieNotFoundException e) {
     	   return new ResponseEntity<String> ("Movie of this Id not present for delete",HttpStatus.CONFLICT);
     	} 
    	
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie,@PathVariable int id){
    	try {
    		Movie movieUpdated=movieService.updateMovie(movie,id);
    		return new ResponseEntity<Movie> (movieUpdated,HttpStatus.CREATED);
    	}catch(MovieNotFoundException e) {
    	   return new ResponseEntity<String> ("Movie of this Id not present for update",HttpStatus.CONFLICT);
    	}  
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable String title){
    	return new ResponseEntity<Iterable<Movie>> (movieService.getMovieByTitle(title),HttpStatus.OK);
    }
    
}
