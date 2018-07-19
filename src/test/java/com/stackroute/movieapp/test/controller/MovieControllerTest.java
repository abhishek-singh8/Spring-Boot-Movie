package com.stackroute.movieapp.test.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.movieapp.controller.MovieController;
import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.services.MovieService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	@Autowired
	private MockMvc movieMockMvc;

	@MockBean
	MovieService movieService;
	Movie movie;
	@Before
	public void setUp() throws Exception {
	    movie = new Movie("MI4", "9to1", "Poster.jpg", "2013");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void displayAllMovies() throws Exception {
		when(movieService.getAllMovies()).thenReturn(null);
		movieMockMvc.perform(get("/api/v1/movies")).andExpect(status().isOk());

	}
	@Test
	   public void testSaveMovie()throws Exception{        
	       when(movieService.saveMovie(movie)).thenReturn(movie);
	       movieMockMvc.perform(post("/api/v1/movie",movie)
	                   .contentType(MediaType.APPLICATION_JSON)
	                   .content(asJsonString(movie)))
	                   .andExpect(status().isCreated());   
	                       
	   }
	@Test
	public void testSaveMovieException() throws MovieAlreadyExistsException,Exception{
		when(movieService.saveMovie(movie)).thenThrow(MovieAlreadyExistsException.class);
		movieMockMvc.perform(post("/api/v1/movie",movie).contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie))).andExpect(status().isConflict());
	}

	@Test
	public void getMovieByIDTest() throws Exception {
		Movie movie = new Movie("MI4", "9to1", "Poster.jpg", "2013");
		Optional<Movie> optionMovie = Optional.of(movie);
		when(movieService.getMovieById(anyInt())).thenReturn(optionMovie);
		movieMockMvc.perform(
				get("/api/v1/getByID/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(optionMovie)))
				.andExpect(status().isOk()).andDo(print());
	}
	@Test
	public void getMovieByIDExceptionTest() throws MovieNotFoundException,Exception{
		when(movieService.getMovieById(anyInt())).thenThrow(MovieNotFoundException.class);
		movieMockMvc.perform(get("/api/v1/getByID/1")).andExpect(status().isConflict());
	}
	@Test
	public void deleteMovieByIDTest() throws Exception {
		//Movie movie = new Movie("MI4", "9to1", "Poster.jpg", "2013");
		//Optional<Movie> optionMovie = Optional.of(movie);
		when(movieService.deleteMovie(anyInt())).thenReturn(true);
		movieMockMvc.perform(
				delete("/api/v1/delete/1")).andExpect(status().isOk());
				
	}
	@Test
	public void deleteMovieByIDExceptionTest() throws MovieNotFoundException,Exception{
		when(movieService.deleteMovie(anyInt())).thenThrow(MovieNotFoundException.class);
		movieMockMvc.perform(delete("/api/v1/delete/1")).andExpect(status().isConflict());
	}
	@Test
	   public void testUpdateMovie()throws Exception{        
	       when(movieService.updateMovie(anyObject(),anyInt())).thenReturn(movie);
	       movieMockMvc.perform(put("/api/v1/update/1",movie)
	                   .contentType(MediaType.APPLICATION_JSON)
	                   .content(asJsonString(movie)))
	                   .andExpect(status().isCreated());   
	                       
	   }
	@Test
	public void uodateByIDExceptionTest() throws MovieNotFoundException,Exception{
		when(movieService.updateMovie(anyObject(),anyInt())).thenThrow(MovieNotFoundException.class);
		movieMockMvc.perform(put("/api/v1/update/1",movie).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie))).andExpect(status().isConflict());
	}

	public static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}