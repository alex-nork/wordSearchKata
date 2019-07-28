package com.pillar.wordSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	
	private static SearchLogic logic = new SearchLogic();
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		try (Scanner userInput = new Scanner(System.in)) {
			//Get the path of the input file
			File inputFile;
			while(true) {
				System.out.println("What is the file to be searched?");
				String path = userInput.nextLine();
				//validate input file
				inputFile = new File(path);
				if(!inputFile.exists()) {
					System.out.println(path + " does not exist");
					continue;
				}
				else if(!inputFile.isFile()) {
					System.out.println(path + " is not a file");
					continue;
				}
				break;
			}
			//this will be referenced for y-coordinate
			try (Scanner inputScanner = new Scanner(inputFile.getAbsoluteFile())) {
				String[] searchWords = logic.searchWordsSeparated(inputScanner.nextLine());
				for(String word : searchWords) {
					int lineNumber = 0;
					Scanner newInputScanner = new Scanner(inputFile.getAbsoluteFile());
					if(newInputScanner.hasNextLine()) {
						newInputScanner.nextLine();
					}
						while(newInputScanner.hasNextLine()) {
							String line = newInputScanner.nextLine();
							if(logic.doesContainSearchWord(word, logic.stringOfLetters((line)))) {
								System.out.println(word + ": " + lineNumber);
							}
							lineNumber++;
							/*
							 * Here is where the logic methods will be called
							 * These will include leftToRight, rightToLeft,
							 * topToBottom, bottomToTop, and diagonal
							 */
						}
					newInputScanner.close();
				}
				
			}
			
		}
		
		
	}
	
}
