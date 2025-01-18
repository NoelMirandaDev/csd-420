/* Developer: Noel Miranda
 * Date of creation: January 17, 2025
 * Program objective: Write a test program that contains a generic static method that returns a new ArrayList.
 * The new ArrayList returned will contain all original values with no duplicates from the original ArrayList.
 * Fill the Original ArrrayList with 50 random values from 1 to 20.
 * 
 * Generic static method: public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list)
 * 
 * Write test code that ensures the code functions correctly.
*/

// Declared Package
package edu.advancedjava.module3;

// Imported Java Classes
import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicatesGenericExercise {
	// main method
	public static void main(String[] args) {
		// Integer type ArrayList for original List
		ArrayList<Integer> originalList = new ArrayList<>();
		
		// Instance of Random class for random integer generation
		Random random = new Random();
		
		// Adds 50 random values from 1 to 20 to the original list
		for (int i = 0; i < 50; i++) {
			originalList.add(random.nextInt(20) + 1);
		}
		
		// Test Code to ensure the code functions correctly
		System.out.println("************************* Test Code *************************\n");
		System.out.println("Orignal List (with duplicates):");
		// For loop to print out original list elements (formatted 10 per line)
		for (int i = 1; i < 51; i++) {
			if (i % 10 == 0) {
				System.out.println(originalList.get(i - 1));
			} else {
				System.out.print(originalList.get(i - 1) + " ");
			}
		}
		
		// Assigns new Integer ArrayList to variable
		ArrayList<Integer> noDuplicatesList = removeDuplicates(originalList);
		
		// For loop to print out new list elements (formatted 10 per line)
		System.out.println("\n\nNew List (without duplicates):");
		for (int i = 1; i < (noDuplicatesList.size() + 1); i++) {
			if (i % 10 == 0) {
				System.out.println(noDuplicatesList.get(i - 1));
			} else {
				System.out.print(noDuplicatesList.get(i - 1) + " ");
			}
		}
	}
	
	/* Public static generic method, (allows it to handle any type of ArrayList) which returns 
	 * a new ArrayList (of the same type) with no duplicates */
	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
		// ArrayList with generic type declaration
		ArrayList<E> noDuplicatesList = new ArrayList<>();
		
		// For-each loop to add only unique elements from original list to new ArrayList
		for (E element : list) {
			if (!noDuplicatesList.contains(element)) {
				noDuplicatesList.add(element);
			}
		}
		return noDuplicatesList;
	}
}