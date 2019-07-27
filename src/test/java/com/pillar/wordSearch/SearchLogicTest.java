package com.pillar.wordSearch;

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
		Assert.assertEquals("BONES,KHAN,KIRK,SCOTTY,SPOCK,SULU,UHURA", logic.firstLine(firstLine));
	}
	
	@Test
	public void searchWordsSeparatedTest() {
		String[] searchWords = {"BONES","KHAN","KIRK","SCOTTY","SPOCK","SULU","UHURA"};
		//test to see if the search words are returned as expected in a String[]
		Assert.assertEquals(searchWords, logic.searchWordsSeparated(firstLine));
		
	}
}
