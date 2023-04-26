package com.ajudaqui.api.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@SerializedName("Title")
	private String title;
	private String Year;
	private String Runtime;
	private String Plot;
	private String Poster;
	private String imdbRating;
	private String imdbVotes;
	private String imdbID;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getRuntime() {
		return Runtime;
	}
	public void setRuntime(String runtime) {
		Runtime = runtime;
	}
	public String getPlot() {
		return Plot;
	}
	public void setPlot(String plot) {
		Plot = plot;
	}
	public String getPoster() {
		return Poster;
	}
	public void setPoster(String poster) {
		Poster = poster;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getImdbVotes() {
		return imdbVotes;
	}
	public void setImdbVotes(String imdbVotes) {
		this.imdbVotes = imdbVotes;
	}
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	@Override
	public String toString() {
		return "\nFilme: \ntitle= " + title + "\nYear= " + Year + "\\nRuntime= " + Runtime + "\nPlot= " + Plot + "\nPoster= "
				+ Poster + "\nimdbRating= " + imdbRating + "\nimdbVotes= " + imdbVotes + "\nimdbID= " + imdbID ;
	}
	
	

}
