package com.identifycourses.models;

public class LanguageLevels {
	private String languageLevel;
	private int count;
	
	public LanguageLevels(String languageLevel, int count) {
		super();
		this.languageLevel = languageLevel;
		this.count = count;
	}

	public String getLanguageLevel() {
		return languageLevel;
	}

	public void setLanguageLevel(String languageLevel) {
		this.languageLevel = languageLevel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
