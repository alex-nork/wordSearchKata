package com.pillar.wordSearch;

import java.util.Map;
import java.util.TreeMap;

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
		for (String letter : lettersAsArray) {
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
		for (String searchLetter : searchWordLetters) {
			for (int i = 0; i < lineLetters.length - 1; i++) {
				if (lineLetters[i].equals(searchLetter)) {
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
		for (int i = searchWord.length() - 1; i >= 0; i--) {
			reversedSearchWord += searchWord.charAt(i);
		}
		return reversedSearchWord;
	}

	/*
	 * this method flips the grid so (x,y) becomes (y,x) since grids will be square
	 * this should make it easier to look for vertical words
	 */
	public Map<Integer, String> flippedGrid(Map<Integer, String> letters) {

		Map<Integer, String> flippedLetters = new TreeMap<Integer, String>();
		Integer key = 0;

		for (int i = 0; i < letters.get(0).length(); i++) {
			String flippedLine = "";
			for (Map.Entry<Integer, String> line : letters.entrySet()) {
				flippedLine += line.getValue().substring(i, i + 1);
			}
			flippedLetters.put(key, flippedLine);
			key++;
		}

		return flippedLetters;
	}

}
