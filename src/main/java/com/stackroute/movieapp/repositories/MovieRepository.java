package com.stackroute.movieapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stackroute.movieapp.domain.Movie;

public interface MovieRepository extends CrudRepository<Movie,Integer>{

	
	
	@Query("From Movie m where m.title=:Title")
	public Iterable<Movie> getMovieByTitle(@Param("Title") String title);
}
