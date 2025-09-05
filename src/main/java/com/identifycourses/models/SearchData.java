package com.identifycourses.models;

public class SearchData {
	private String searchText;
	
	public SearchData() {}
	
	public SearchData(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchData() {
		return searchText;
	}

	public void setSearchData(String searchText) {
		this.searchText = searchText;
	}
}
