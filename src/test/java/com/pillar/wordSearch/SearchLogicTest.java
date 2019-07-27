package com.pillar.wordSearch;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
		String nextLine = scanner.nextLine();
		String[] lettersToSearch = new String[] {"U","M","K","H","U","L","K","I","N","V","J","O","C","W","E"};
		String[] lettersFromFile = logic.lettersToSearch(nextLine); 
		assertEquals(lettersToSearch.length, lettersFromFile);
	}
}
