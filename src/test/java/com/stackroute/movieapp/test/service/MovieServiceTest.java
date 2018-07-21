package com.stackroute.movieapp.test.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exception.MovieAlreadyExistsException;
import com.stackroute.movieapp.exception.MovieNotFoundException;
import com.stackroute.movieapp.repositories.MovieRepository;
import com.stackroute.movieapp.services.MovieServiceImpl;
import com.stackroute.movieapp.services.NextSequenceService;

@RunWith(SpringRunner.class)
public class MovieServiceTest {

	@Mock
	private MovieRepository movieRepo;
	@Mock
	NextSequenceService nextSequenceService;
	private Movie movie;
	private Movie movie1;

	/**
	 * injecting mocks in MovieServiceImpl object
	 */
	@InjectMocks
	private MovieServiceImpl movieServiceImpl;

	/**
	 * variable to hold user defined movies list
	 */
	private Optional<Movie> optionMovie;

	/**
	 * Initializing the declarations
	 */
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie("POC", "good Movie", "www.abc.com", "2015-03-31");
		movie1 = new Movie("ANTMAN", "good Movie", "www.zaruti.com", "2014-03-31");
		optionMovie = Optional.of(movie);

	}

	/**
	 * testing mock creation
	 */
	@Test
	public void testMockCreation() {
		assertNotNull("jpaRepository creation fails: use @injectMocks on movieServicempl", movieRepo);
	}

	/**
	 * testing the save method
	 * 
	 * @throws MovieAlreadyExistsException
	 */
	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(movie);
		Iterable<Movie> movieIterable = movieList;

		when(movieRepo.findAll()).thenReturn(movieIterable);
		when(movieRepo.save(movie1)).thenReturn(movie1);
		when(nextSequenceService.getNextSequence(anyString())).thenReturn(1);
		assertEquals(movie1, movieServiceImpl.saveMovie(movie1));
	}

	@Test(expected = MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(movie);
		Iterable<Movie> movieIterable = movieList;
		when(movieRepo.findAll()).thenReturn(movieIterable);
		movieServiceImpl.saveMovie(movie);

	}

	@Test
	public void testGetMovieByIdSuccess() throws MovieNotFoundException {
		when(movieRepo.findById(anyInt())).thenReturn(optionMovie);
		assertEquals(optionMovie, movieServiceImpl.getMovieById(1));
	}

	@Test(expected = MovieNotFoundException.class)
	public void testGetMovieByIdFailure() throws MovieNotFoundException {
		when(movieRepo.findById(anyInt())).thenReturn(Optional.empty());
		movieServiceImpl.getMovieById(1);
	}
	@Test
	public void testDeleteMovieByIdSuccess() throws  MovieNotFoundException{
		when(movieRepo.findById(anyInt())).thenReturn(optionMovie);
		 doNothing().when(movieRepo).deleteById(anyInt());
		 assertTrue(movieServiceImpl.deleteMovie(anyInt()));
	}
	@Test(expected = MovieNotFoundException.class)
	public void testDeleteMovieByIdFailure() throws MovieNotFoundException{
		when(movieRepo.findById(anyInt())).thenReturn(Optional.empty());
		movieServiceImpl.deleteMovie(anyInt());
	}
	@Test
	public void testUpdateMovieByIdSuccess() throws  MovieNotFoundException{
		when(movieRepo.findById(anyInt())).thenReturn(optionMovie);
		when(movieRepo.save(movie)).thenReturn(movie);
		assertEquals(movie,movieServiceImpl.updateMovie(movie, anyInt()));
	}
	@Test(expected = MovieNotFoundException.class)
	public void testUpdateMovieByIdFailure() throws MovieNotFoundException{
		when(movieRepo.findById(anyInt())).thenReturn(Optional.empty());
		movieServiceImpl.updateMovie(movie,anyInt());
	}
}