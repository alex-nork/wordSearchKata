package com.pillar.wordSearch;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;

public class SearchLogicTest {

	private SearchLogic logic = new SearchLogic();
	private static File inputFile;
	private static Scanner scanner;
	
	@BeforeClass
	public static void readFile() throws FileNotFoundException {
		inputFile = new File("WordSearchText");
		scanner = new Scanner(inputFile);
	}
	
	
	
	@Test
	public void leftToRightTest() {
		String firstLine = scanner.nextLine();
		//first test to see if inputScanner gets first line and returns the unaltered String
		assertEquals("BONES", logic.leftToRight(firstLine));
	}
	
	
}
