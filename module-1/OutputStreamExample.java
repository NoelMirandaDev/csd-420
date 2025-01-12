/* Developer: Noel Miranda
 * Date of creation: January 11, 2025
 * Program objective: Create a program that stores an array of five random integers, a Date objective 
 * instance using the current date, and an array of five random double values. Output the data in a file 
 * titled <yourname> datafile.dat.
*/

// Declared Package
package edu.advancedjava.module1;

// Imported Java Classes
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.io.IOException;

public class OutputStreamExample {
	
	public static void main(String[] args) {
		// Declared integer array of size five
		int[] randomIntArray = new int[5];
		
		// Declared double array of size five
		double[] randomDoubleArray = new double[5];
		
		// Fill declared integer array with five random integers between 0-99
		for (int i = 0; i < 5; i++) {
			randomIntArray[i]= (int) (Math.random() * 100);
		}
		
		// Date objective instance using current date
		Date currentDate = new Date();
		
		// Fill declared double array with five random double values between 0.0-100.0
		for (int i = 0; i < 5; i++) {
			randomDoubleArray[i]= Math.random() * 100;
		}
	
		// Try-with-resources for ObjectOutputStream to trigger AutoCloseable interface
		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("noeldatafile.dat"))) {
			output.writeObject(randomIntArray);
			output.writeObject(currentDate);
			output.writeObject(randomDoubleArray);
		} catch (IOException e) {
			System.out.println("IO error: ");
			e.printStackTrace();
		}
	}
}