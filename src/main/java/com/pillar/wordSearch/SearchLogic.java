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
	
	public String stringOfLetters(String searchLetters) {
		String[] lettersAsArray = lettersToSearch(searchLetters);
		String letters = ""; 
		for(String letter : lettersAsArray) {
			letters += letter;
		}
		return letters;
	}
	
	public boolean doesContainSearchWord(String searchWord, String lettersBeingSearched) {
		return (lettersBeingSearched.contains(searchWord));
	}
	
	public int[] xCoordinates(String line, String searchWord) {
		String[] lineLetters = lettersToSearch(line);
		String[] searchWordLetters = lettersToSearch(searchWord);
		
		int[] xCoordinates = new int[searchWord.length()];
		int counter = 0;
		for(String searchLetter : searchWordLetters) {
			for(int i = 0; i < lineLetters.length - 1; i++) {
				if(lineLetters[i].equals(searchLetter)) {
					xCoordinates[counter] = i;
					counter++;
					break;
				}
			}
			
		}
		return xCoordinates;
	}
	
	public String reverseSearchWord(String searchWord) {
		
		String reversedSearchWord = "";
		for(int i = searchWord.length() - 1; i >= 0; i--) {
			reversedSearchWord += searchWord.charAt(i);
		}
		return reversedSearchWord;
	}
}





