package com.pillar.wordSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		try (Scanner userInput = new Scanner(System.in)) {
			//Get the path of the input file
			File inputFile;
			while(true) {
				System.out.println("What is the file to be searched?");
				String path = userInput.nextLine();
				//validate input file
				inputFile = new File(path);
				if(inputFile.exists() == false) {
					System.out.println(path + " does not exist");
					continue;
				}
				else if(inputFile.isFile() == false) {
					System.out.println(path + " is not a file");
					continue;
				}
				break;
			}
		}
		
		
	}
	
}
