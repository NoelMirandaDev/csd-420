/* Developer: Noel Miranda
 * Date of creation: January 11, 2025
 * Program objective: Create a program that will read the file (<yourname> datafile.dat) and display the data.
 * Test the code to ensure the code functions correctly.
*/

// Declared Package
package edu.advancedjava.module1;

// Imported Java Classes
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.Date;
import java.io.IOException;
import java.io.File;

public class InputStreamExample {
	
	public static void main(String[] args) {
		// Declared variables to be read from ObjectInputStream
		int[] randomIntArray;
		Date currentDate;
		double[] randomDoubleArray;
		
		File file = new File("noeldatafile.dat");
		
		// Checks if the file exists
		if (file.exists()) {
			// Checks if the file is readable
			if (file.canRead()) {
				// Try-with-resources for ObjectInputStream to trigger AutoCloseable interface
				try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
					// Initialize declared variables
					randomIntArray = (int[]) input.readObject(); 
					currentDate = (Date) input.readObject();
					randomDoubleArray = (double[]) input.readObject();
					
					// Display read data from noeldatafile.dat
					System.out.print("Array of random integers: ");
					for (int i = 0; i < 5; i++) {
						System.out.print(randomIntArray[i] + " ");
					}
					
					System.out.print("\n\nDate: ");
					System.out.print(currentDate);
					
					System.out.print("\n\nArray of random double values: ");
					for (int i = 0; i < 5; i++) {
						System.out.printf("%.2f ", randomDoubleArray[i]);
					}
					
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Error reading from file: " + e.getMessage());
					e.printStackTrace();
				} 
			} else {
				System.out.println("File exists but cannot be read due to permission issues.");
			} 
		} else {
			System.out.println("File not found. Please check the file path.");
		}
	}
}