package com.stackroute.movieapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String title;
	private String imdbId;
	private String poster;
	private String year;
	
	public Movie() {
		
	}
	public Movie(String title, String imdbId, String poster, String year) {
		super();
		this.title = title;
		this.imdbId = imdbId;
		this.poster = poster;
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImdbId() {
		return imdbId;
	}
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Movie [title=" + title + ", imdbId=" + imdbId + ", poster=" + poster + ", year=" + year + "]";
	}
	@Override
	public boolean equals (Object o) {
	    if (!(o instanceof Movie)) {
	        return false;
	    }
	    Movie movie = (Movie)o;
	    return  getTitle().equals(movie.getTitle()) &&
	    		getImdbId().equals(movie.getImdbId())&&
	    		getPoster().equals(movie.getPoster())&&
	    		getYear().equals(movie.getYear());
	}
	
}
