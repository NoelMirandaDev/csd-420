/* Developer: Noel Miranda
 * Date of creation: February 11, 2025
 * Program objective: In this class, you are to use three threads to output three types of characters to
 * the console. In the first thread, you are to output random letter characters such as a, b, c, d …
 * In the second thread, you are to output random number digits such as 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
 * In the third thread, you are to output random characters such as !, @, #, $, %, &, *
 * Display a minimum of 10,000 of each of the three sets.
 * 
 * Write test code that ensures all methods function correctly.
*/

// Declared Package
package edu.advancedjava.module8;

//Imported Java Classes
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NoelThreeThreads {
	public static void main (String[] args) {
		// Creates a fixed thread pool with a maximum of three threads
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		
		// Submits the runnable tasks to the thread pool for the executor to run
		threadPool.execute(new PrintRandomChar(10000));
		threadPool.execute(new PrintRandomInt(10000));
		threadPool.execute(new PrintRandomSpecialChar(10000));
		
		// Shuts down the executor
		threadPool.shutdown();
		
	}
	
	// Defining the random character output set/task for the thread pool
	public static class PrintRandomChar implements Runnable {
		private Random randomGenerator = new Random();
		private int n;
		
		public PrintRandomChar (int n) {
			this.n = n;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < n; i++) {
				System.out.print((char) ('a' + randomGenerator.nextInt(26)) + " ");
			}
		}
	}
	
	// Defining the random number digits output set/task for the thread pool
	public static class PrintRandomInt implements Runnable {
		private Random randomGenerator = new Random();
		private int n;
		
		public PrintRandomInt(int n) {
			this.n = n;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < n; i++) {
				System.out.print(randomGenerator.nextInt(10) + " ");
			}
		}
	}
	
	// Defining the random special characters (!, @, #, $, %, &, *) output set/task for the thread pool
	public static class PrintRandomSpecialChar implements Runnable {
		private char[] charArray = {'!', '@', '#', '$', '%', '&', '*'};
		Random randomGenerator = new Random();
		private int n;
		
		public PrintRandomSpecialChar (int n) {
			this.n = n;
		}
		
		@Override 
		public void run() {
			for (int i = 0; i < n; i++) {
				System.out.print(charArray[randomGenerator.nextInt(charArray.length)] + " ");
				
			}
		}
	}
}