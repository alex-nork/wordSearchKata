package com.pillar.wordSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

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

			Map<Integer, String> fullGrid = gridToMap(inputFile);
			String[] words = searchWords(fullGrid);
			Map<Integer, String> letters = letterGrid(fullGrid);
			leftToRightSearch(words, letters);
			System.out.println();
			rightToLeftSearch(words, letters);
			System.out.println();
			topToBottomSearch(words, letters);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	// read the file once and store it in memory using a TreeMap
	private Map<Integer, String> gridToMap(File inputFile) throws FileNotFoundException {
		Integer lineNumber = 0;
		Map<Integer, String> grid = new TreeMap<Integer, String>();

		Scanner inputScanner = new Scanner(inputFile.getAbsoluteFile());

		grid.put(lineNumber, inputScanner.nextLine());
		lineNumber++;
		while (inputScanner.hasNextLine()) {
			String line = inputScanner.nextLine();
			grid.put(lineNumber, logic.stringOfLetters(line));
			lineNumber++;
		}
		return grid;
	}

	// returns a String[] holding each search word
	private String[] searchWords(Map<Integer, String> grid) {
		String[] words = logic.searchWordsSeparated(grid.get(0));

		return words;
	}

	// returns a TreeMap with everything but the first line (searchWords)
	private Map<Integer, String> letterGrid(Map<Integer, String> fullGrid) {
		Set<Integer> keys = fullGrid.keySet();
		Map<Integer, String> letterGrid = new TreeMap<Integer, String>();

		for (int i = 0; i < keys.size() - 1; i++) {
			letterGrid.put(i, fullGrid.get(i + 1));

		}
		return letterGrid;
	}

	private void topToBottomSearch(String[] searchWords, Map<Integer,String> letters) {
		for (String word : searchWords) {
			int rowNumber = 0;
			Map<Integer,String> flippedLetters = logic.flippedGrid(letters);
			for (Map.Entry<Integer, String> row : flippedLetters.entrySet()) {
				if (logic.doesContainSearchWord(word, (row.getValue()))) {
					int[] yCoordinates = logic.xCoordinates(row.getValue(), word);
					System.out.print(word + ": ");
					for(int i = 0; i <yCoordinates.length; i++) {
						if (i == yCoordinates.length - 1) {
							System.out.print("(" + rowNumber + "," + i + ")");
						} else {
							System.out.print("(" + rowNumber + "," + i + "),");
						}
					}
				}
				rowNumber++;
			}
		}
	}
	

	private void leftToRightSearch(String[] searchWords, Map<Integer, String> letters) {
		for (String word : searchWords) {
			// this will be referenced for y-coordinate
			int lineNumber = 0;
			for (Map.Entry<Integer, String> row : letters.entrySet()) {
				// returns boolean after checking if the searchWord is contained in the string
				// of letters on the current line (already split and concatenated)
				if (logic.doesContainSearchWord(word, (row.getValue()))) {
					// new int[] holding the x-coordinates of the word on the line
					int[] xCoordinates = logic.xCoordinates(row.getValue(), word);
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
		}
	}

	// same steps as leftToRightSearch method but the search word is flipped to
	// check if it's spelled backwards on the current line
	private void rightToLeftSearch(String[] searchWords, Map<Integer, String> letters) {
		for (String word : searchWords) {
			// this will be referenced for y-coordinate
			int lineNumber = 0;
			for (Map.Entry<Integer, String> row : letters.entrySet()) {
				// this is where the searchWord is flipped
				if (logic.doesContainSearchWord(logic.reverseSearchWord(word), row.getValue())) {
					int[] xCoordinates = logic.xCoordinates(row.getValue(), word);
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
		}
	}

}
