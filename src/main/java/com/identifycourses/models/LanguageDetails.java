package com.identifycourses.models;

public class LanguageDetails {
	private String language;
	private int count;
	
	public LanguageDetails(String language, int count) {
		super();
		this.language = language;
		this.count = count;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
