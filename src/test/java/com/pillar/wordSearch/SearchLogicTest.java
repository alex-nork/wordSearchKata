package com.pillar.wordSearch;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SearchLogicTest {

	private SearchLogic logic = new SearchLogic();
	private static File inputFile;
	private static Scanner scanner;
	private String firstLine;

	@BeforeClass
	public static void readFile() {
		inputFile = new File("WordSearchText");

	}

	@Before
	public void setup() throws FileNotFoundException {
		scanner = new Scanner(inputFile);
		firstLine = scanner.nextLine();
	}

	@Test
	public void firstLineTest() {
		// first test to see if inputScanner gets first line and returns the String
		assertEquals("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA", logic.firstLine(firstLine));
	}

	@Test
	public void searchWordsSeparatedTest() {
		String[] searchWords = new String[] { "BONES", "KHAN", "KIRK", "SCOTTY", "SPOCK", "SULU", "UHURA" };
		String[] firstLineWords = logic.searchWordsSeparated(firstLine);
		/*
		 * tests to see if the method is returning an array with the expected length and
		 * with the expected values in the correct array indexes
		 */
		assertEquals(searchWords.length, firstLineWords.length);
		assertEquals(searchWords[0], firstLineWords[0]);
		assertEquals(searchWords[6], firstLineWords[6]);

	}
	
	@Test
	public void lettersToSearchTest() {
		//essentially same test as above but for first row of letters
		String nextLine = scanner.nextLine();
		String[] lettersToSearch = new String[] {"U","M","K","H","U","L","K","I","N","V","J","O","C","W","E"};
		String[] lettersFromFile = logic.lettersToSearch(nextLine); 
		
		assertEquals(lettersToSearch.length, lettersFromFile.length);
		assertEquals(lettersToSearch[0], lettersFromFile[0]);
		assertEquals(lettersToSearch[14], lettersFromFile[14]);
	}
	
	@Test
	public void stringOfLettersTest() {
		//turning the string[] into a string so that .contains can be used on it later
		String nextLine = scanner.nextLine();
		String lettersToSearch = "UMKHULKINVJOCWE";
		String lettersFromFile = logic.stringOfLetters(nextLine);
		
		assertEquals(lettersToSearch, lettersFromFile);
	}
	
	@Test
	public void doesContainSearchWordTest() {
		//checking to see if first line of letters contains a certain word
		String nextLine = scanner.nextLine();
		String searchWord = "HULK";
		String lettersBeingSearched = logic.stringOfLetters(nextLine);
		
		assertEquals(true, logic.doesContainSearchWord(searchWord, lettersBeingSearched));
	}
	
	@Test
	public void xCoordinatesTest() {
		String nextLine = scanner.nextLine();
		String searchWord = "HULK";
		int[] xCoordinates = new int[] {3, 4, 5, 6};
		assertEquals(xCoordinates.length, logic.xCoordinates(nextLine, searchWord).length);
	}
	
	@Test
	public void reverseSearchWordTest() {
		String searchWord = "HULK";
		assertEquals("KLUH", logic.reverseSearchWord(searchWord));
	}
	
	@Test
	public void flippedGridTest() {
		Map<Integer,String> testMap = new TreeMap<Integer,String>();
		testMap.put(0, "ADG");
		testMap.put(1, "BEH");
		testMap.put(2, "CFI");
		
		assertEquals("ABC", logic.flippedGrid(testMap).get(0));
		assertEquals("DEF", logic.flippedGrid(testMap).get(1));
		assertEquals("GHI", logic.flippedGrid(testMap).get(2));
	}
}
