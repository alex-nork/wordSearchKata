package com.pillar.wordSearch;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

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
		//first test to see if inputScanner gets first line and returns the unaltered String
		assertEquals("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA", logic.firstLine(firstLine));
	}
	
	@Test
	public void searchWordsSeparatedTest() {
		String[] searchWords = new String[] {"BONES","KHAN","KIRK","SCOTTY","SPOCK","SULU","UHURA"};
		String[] firstLineWords = logic.searchWordsSeparated(firstLine);
		/*
		 * tests to see if the method is returning an array with the expected length
		 * and with the expected values in the correct array indexes
		 */
		Assert.assertEquals(searchWords.length, firstLineWords.length);
		Assert.assertEquals(searchWords[0], firstLineWords[0]);
		Assert.assertEquals(searchWords[6], firstLineWords[6]);
		
	}
}
