/*package com.stackroute.movieapp.test.controller;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.movieapp.MovieAppApplication;
import com.stackroute.movieapp.domain.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  MovieAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerIntegrationTest {

    @LocalServerPort
    private int port;

    Movie movie;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<Movie> entity;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Before
    public void setUp() throws Exception {
        
        movie = new Movie("Batman", "very good movie", "xyz", "2017");
        movie.setId(123);
        entity = new HttpEntity<Movie>(movie, headers);

    }

    @After
    public void tearDown() throws Exception {
        restTemplate.exchange(createURLWithPort("/api/v1/movie/123"), HttpMethod.DELETE, entity, String.class);

    }

    *//**
     * Testing save movie method
     * 
     * @throws Exception
     *//*
    @Test
    public void testSaveNewMovieSuccess() throws Exception {
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/v1/movie"), HttpMethod.POST,
                entity, String.class);

        assertNotNull(response);
        String actual = response.getBody();
        assertNotNull(actual);
        assertEquals(201, response.getStatusCodeValue());

    }
}*/