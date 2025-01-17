/* Developer: Noel Miranda
 * Date of creation: January 16, 2025
 * Program objective: Create a program using a recursive method that returns the sum of the following type inputs.
 * m(i) = 1/2 + 2/3 + 3/4 + 4/5 + 5/6 … i/(i + 1)
 * ‘m’ is to be replaced with the method name you use.
 * 
 * Use three different input finishing values, testing your code to ensure it functions correctly.
 * Examples: m(4), m(9), m(14).
*/

// Declared Package
package edu.advancedjava.module2;

//Imported Java Class
import java.util.Scanner;

public class RecursionExercise {
	// Main method
	public static void main (String[] args) {
		// Test code to ensure program functions correctly
		System.out.println("******************************* Program Test *******************************\n");
		System.out.println("Mathetimatical formula in this program: m(i) = 1/2 + 2/3 + 3/4 + 4/5 + 5/6 … i/(i + 1)\n");
		System.out.println("Expected sum from input of 4 is approximately 2.7167");
		System.out.println("Result of test 1 for an input of 4: " + recursiveMethod(4));
		System.out.println("\nExpected sum from input of 9 is approximately 7.0710");
		System.out.println("Result of test 2 for an input of 9: " + recursiveMethod(9));
		System.out.println("\nExpected sum from input of 14 is approximately 11.6818");
		System.out.println("Result of test 3 for an input of 14: " + recursiveMethod(14));
		System.out.println("\nTest Completed!\n\n");
		
		// Code for user input
		System.out.println("*********************** Program User Input Option Below ***********************\n");
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please type in a whole number to receive the result based of the program's formula: ");
		
		// Declaring variable for input value
		int inputValue;
		
		// While loop to prompt user until a correct input is typed in
		while (true) {
			// verifies if an actual integer was inputed
			if (input.hasNextInt()) {
				inputValue = input.nextInt();
				// Verifies if integer is positive
				if (inputValue >= 0) {
					break;
				} else {
					System.out.print("\nThe number must be positive. Please try again: ");
				}
			} else {
				System.out.print("\nOops, invalid input. Please type in a whole number: ");
				input.next(); // Consumes invalid input
			}
		}
		// prevents resource leak
		input.close();
		System.out.println("\nThe sum of the inputted number into the program's formula is " + recursiveMethod(inputValue));
	}
	
	// Recursive helper method
	public static double recursiveMethod (int n) {
		return recursiveMethod(n, 0); // Calls auxiliary recursive method with base case value
		
	}
	// Auxiliary tail-recursive method
	private static double recursiveMethod (int n, double sum) {
		if (n == 0) { // base case
			return sum;
		} else {
			sum += (double) n / (n + 1);
			return recursiveMethod(n - 1, sum); // recursive call
		}
	}
}