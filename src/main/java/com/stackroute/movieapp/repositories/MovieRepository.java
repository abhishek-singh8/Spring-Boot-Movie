package com.stackroute.movieapp.repositories;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stackroute.movieapp.domain.Movie;
@Repository
public interface MovieRepository extends CrudRepository<Movie,Integer>{

	
//	
//	@Query("From Movie m where m.title=:Title")
//	public Iterable<Movie> getMovieByTitle(@Param("Title") String title);
}
