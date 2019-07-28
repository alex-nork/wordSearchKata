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
		
			/*
			 * Here is where the logic methods will be called
			 * These will include leftToRight, rightToLeft,
			 * topToBottom, bottomToTop, and diagonal
			 */
			try (Scanner inputScanner = new Scanner(inputFile.getAbsoluteFile())) {
				String[] searchWords = logic.searchWordsSeparated(inputScanner.nextLine());
				for(String word : searchWords) {
					//this will be referenced for y-coordinate
					int lineNumber = 0;
					Scanner newInputScanner = new Scanner(inputFile.getAbsoluteFile());
					if(newInputScanner.hasNextLine()) {
						newInputScanner.nextLine();
					}
						while(newInputScanner.hasNextLine()) {
							String line = newInputScanner.nextLine();
							if(logic.doesContainSearchWord(word, logic.stringOfLetters((line)))) {
								int[] xCoordinates = logic.xCoordinates(line, word);
								System.out.print(word + ": ");
								for(int i = 0; i < xCoordinates.length; i++) {
									if(i == xCoordinates.length - 1) {
										System.out.print("(" + i + "," + lineNumber + ")");
									}
									else {
										System.out.print("(" + i + "," + lineNumber + "),");
									}
								}
							}
							lineNumber++;
							
						}
					newInputScanner.close();
				}
				
			}
			
		}
		
		
	}

}
