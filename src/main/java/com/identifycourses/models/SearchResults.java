package com.identifycourses.models;

public class SearchResults {
	private String courseName;
	private String rating;
	private String duration;
	
	public SearchResults(String courseName, String rating, String duration) {
		super();
		this.courseName = courseName;
		this.rating = rating;
		this.duration = duration;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}
