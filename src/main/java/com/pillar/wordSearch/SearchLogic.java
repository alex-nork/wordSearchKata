package com.pillar.wordSearch;

public class SearchLogic {

	public String firstLine(String firstLine) {
		
		return firstLine;
		
	}
	
	public String[] searchWordsSeparated(String firstLine) {
		
		String[] searchWords = firstLine.split(",");
		return searchWords;
		
	}
	
	public String[] lettersToSearch(String lineOfLetters) {
		
		String[] searchLetters = lineOfLetters.split(",");
		return searchLetters;
	}
	
	public String stringOfLetters(String[] searchLetters) {
		
		
		return null;
	}
	
	
}
