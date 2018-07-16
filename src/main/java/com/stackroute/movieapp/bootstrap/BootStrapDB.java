package com.stackroute.movieapp.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.repositories.MovieRepository;

@Configuration
public class BootStrapDB implements ApplicationListener<ContextRefreshedEvent>{
//public class BootStrapDB implements CommandLineRunner{
    
	private MovieRepository movieRepo;
	
	@Autowired
	public BootStrapDB(MovieRepository movieRepository) {
		this.movieRepo=movieRepository;
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		Movie movie1=new Movie("abcd","9to1","Poster.jpg","2013");
		movieRepo.save(movie1);
		
	}

	
}
