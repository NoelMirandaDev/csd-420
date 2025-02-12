/* Developer: Noel Miranda
 * Date of creation: February 11, 2025
 * Program objective: In this class, you are to use three threads to output three types of characters to a 
 * text area for display. In the first thread, you are to output random letter characters such as a, b, c, d …
 * In the second thread, you are to output random number digits such as 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
 * In the third thread, you are to output random characters such as !, @, #, $, %, &, *
 * Display a minimum of 10,000 of each of the three sets.
 * 
 * Write test code that ensures all methods function correctly.
*/

// Declared package
package edu.advancedjava.module8;

// Imported JavaFX Classes
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Imported Java Classes
import java.util.Random;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors;

public class NoelThreeThreads extends Application {
	private static final int COUNT = 10000; // Number of characters to generate per thread
	private TextArea textArea; // text area to display output
	private ExecutorService threadPool; // Thread pool to manage threads
	
	@Override
	public void start(Stage primaryStage) {
		textArea = new TextArea();
		textArea.setEditable(false); // Makes the text area read only
		
		// Creates a container for the text area
		VBox vBox = new VBox(textArea);
		Scene scene = new Scene(vBox, 600, 250);
		
		primaryStage.setTitle("Multi-threaded Output");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Initializes a thread pool with three threads
		threadPool = Executors.newFixedThreadPool(3);
		
		// Submits tasks (threads) to the thread pool
		threadPool.execute(new PrintRandomChar()); // Prints random letters
		threadPool.execute(new PrintRandomInt()); // Prints random numbers
		threadPool.execute(new PrintRandomSpecialChar()); // Prints special characters
	}
	
	@Override
	public void stop() { // Shuts down the thread pool when the application closes
		// checks to ensure that shutdown() is only called if theadPool was actually created
		if (threadPool != null) {
			threadPool.shutdown();
		}
	}
	
	// Helper method to safely update the UI from background threads
	private void appendText(String text) {
		Platform.runLater(() -> textArea.appendText(text)); // This ensures safe UI updates
	}
	
	// Task 1: Generates random lowercase letters (a-z)
	private class PrintRandomChar implements Runnable {
		private Random randomGenerator = new Random();
		
		@Override
		public void run() {
			for (int i = 0; i < COUNT; i++) {
				// Random letter from 'a' to 'z'
				char letter = (char) ('a' + randomGenerator.nextInt(26));
				appendText(letter + " "); // Appends to the TextArea
			}
		}
	}
	
	// Task 2: Generates random numbers (0-9)
	private class PrintRandomInt implements Runnable {
		private Random randomGenerator = new Random();
		
		@Override
		public void run() {
			for (int i = 0; i < COUNT; i++) {
				// Random number from 0 to 9
				int number = randomGenerator.nextInt(10);
				appendText(number + " "); // Appends to the TextArea
			}
		}
	}
	
	// Task 3: Generates random special characters (!, @, #, $, %, &, *)
	private class PrintRandomSpecialChar implements Runnable {
		private Random randomGenerator = new Random();
		private char[] charArray = {'!', '@', '#', '$', '%', '&', '*'};
		
		@Override
		public void run() {
			for (int i = 0; i < COUNT; i++) {
				// Picks random special char
				char specialChar = charArray[randomGenerator.nextInt(charArray.length)];
				appendText(specialChar + " "); // Appends to the TextArea
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args); // Starts the JavaFX application
	}
	
	/* This code was tested multiple times for proper task completion, UI behavior, and thread pool shutdown.
	 * Further testing will require additional tools (external frameworks) that have 
	 * not been taught as of now in the curriculum.
	 * Each task's logic was tested on a different java file on the console with a varying display count.
	 */
}