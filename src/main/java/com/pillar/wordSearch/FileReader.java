package com.pillar.wordSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	private static SearchLogic logic = new SearchLogic();

	public static void main(String[] args) {
		FileReader application = new FileReader();
		application.run();
	}

	private void run() {
		mainMenu();
	}

	public void mainMenu() {
		try (Scanner userInput = new Scanner(System.in)) {
			// Get the path of the input file
			File inputFile;
			while (true) {
				System.out.println("What is the file to be searched?");
				String path = userInput.nextLine();
				// validate input file
				inputFile = new File(path);
				if (!inputFile.exists()) {
					System.out.println(path + " does not exist");
					continue;
				} else if (!inputFile.isFile()) {
					System.out.println(path + " is not a file");
					continue;
				}
				break;
			}
			try (Scanner inputScanner = new Scanner(inputFile.getAbsoluteFile())) {
				String[] searchWords = logic.searchWordsSeparated(inputScanner.nextLine());
				leftToRightSearch(searchWords, inputFile);
				System.out.println();
				rightToLeftSearch(searchWords, inputFile);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

	// this can likely be refactored further
	private void leftToRightSearch(String[] searchWords, File inputFile) throws FileNotFoundException {
		for (String word : searchWords) {
			// this will be referenced for y-coordinate
			int lineNumber = 0;
			// new Scanner so that it starts reading from the first line which each
			// searchWord
			Scanner newInputScanner = new Scanner(inputFile.getAbsoluteFile());
			if (newInputScanner.hasNextLine()) {
				newInputScanner.nextLine();
			}
			while (newInputScanner.hasNextLine()) {
				String line = newInputScanner.nextLine();
				// returns boolean after checking if the searchWord is contained in the string
				// of letters on the current line (already split and concatenated)
				if (logic.doesContainSearchWord(word, logic.stringOfLetters((line)))) {
					// new int[] holding the x-coordinates of the word on the line
					int[] xCoordinates = logic.xCoordinates(line, word);
					System.out.print(word + ": ");
					for (int i = 0; i < xCoordinates.length; i++) {
						if (i == xCoordinates.length - 1) {
							System.out.print("(" + i + "," + lineNumber + ")");
						} else {
							System.out.print("(" + i + "," + lineNumber + "),");
						}
					}
				}
				lineNumber++;
			}
			newInputScanner.close();
		}
	}

	// same steps as leftToRightSearch method but the search word is flipped to
	// check if it's spelled backwards on the current line
	private void rightToLeftSearch(String[] searchWords, File inputFile) throws FileNotFoundException {
		for (String word : searchWords) {
			// this will be referenced for y-coordinate
			int lineNumber = 0;
			Scanner newInputScanner = new Scanner(inputFile.getAbsoluteFile());
			if (newInputScanner.hasNextLine()) {
				newInputScanner.nextLine();
			}
			while (newInputScanner.hasNextLine()) {
				String line = newInputScanner.nextLine();
				// this is where the searchWord is flipped
				if (logic.doesContainSearchWord(logic.reverseSearchWord(word), logic.stringOfLetters((line)))) {
					int[] xCoordinates = logic.xCoordinates(line, word);
					System.out.print(word + ": ");
					for (int i = xCoordinates.length; i > 0; i--) {
						if (i == xCoordinates[0]) {
							System.out.print("(" + i + "," + lineNumber + ")");
						} else {
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
