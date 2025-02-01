/* Developer: Noel Miranda
 * Date of creation: January 31, 2025
 * Program objective: Write a test program that reads words from a text file and displays all non-duplicate words
 * in ascending order and then in descending order. The file is to be referenced in the program, not needing to 
 * be used as a command line reference. The word file is to be titled collection_of_words.txt and included in 
 * your submission. Write test code that ensures the code functions correctly.
*/

// Declared Package
package edu.advancedjava.module5;

// Imported Java Classes
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileReadNonDuplicateWords {
	public static void main(String[] args) {
		// Name of file to be read
		String fileName = "Collection_of_Words.txt";
		
		// Initializes a TreeSet to store unique words in ascending order (default) after reading file
		Set<String> nonDuplicateWords = new TreeSet<>();
		
		// Try-with-resources to open the specified file for reading with automatic closing after
		try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
			// bufferReader reads text from file in chunks instead of character by character
			
			String lineRead; // lines of file will be stored here
			
			// Processes each line in the file
			while ((lineRead = fileReader.readLine()) != null) {
				// Splits the line into words, removes non-alphabetic characters, and converts to lowercase, added to array
				String[] words = lineRead.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+");
				
				// Iterates through each word in the line
				for (String word : words) {
					// Adds the word to the set if it is not empty after trimming spaces (eliminates whitespace)
					if (!word.trim().isEmpty()) {
						nonDuplicateWords.add(word);
					}
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// Handles the case where the file is not found
			System.out.println("Error: File not found.");
			return;
		} catch (IOException e) {
			// Handles other i/o errors
			System.out.println("Error reading file: " + e.getMessage());
			return;
		}
		
		// Displays words in ascending order
		System.out.println("Ascending order: ");
		nonDuplicateWords.forEach(word -> System.out.println(word));
		
		// Converts the treeset into a List to display the words in descending order
		System.out.println("\n\nDescending order: ");
		List<String> descendingList = new ArrayList<>(nonDuplicateWords);
		Collections.reverse(descendingList); // reverses the list to show descending order
		descendingList.forEach(word -> System.out.println(word));
		
		// Execute method for test code
		testCode(nonDuplicateWords);
	}
	
	// Test code method to verify the functionality of the program
	public static void testCode(Set<String> nonDuplicateWords) {
        System.out.println("\n\nRunning Test Code...");

        // Checks if there are any words in the set
        if (nonDuplicateWords.isEmpty()) {
            System.out.println("Test failed: No words found.");
        } else {
            System.out.println("Test passed: Words were read successfully.");
        }

        // Checks if the set size is correct (no duplicates should be present)
        int expectedSize = 21;  // Expected unique words in Collection_of_Words.txt
        if (nonDuplicateWords.size() == expectedSize) {
            System.out.println("Test passed: No duplicates found.");
        } else {
            System.out.println("Test failed: Duplicates may be present or file content is different.");
        }
    }
}