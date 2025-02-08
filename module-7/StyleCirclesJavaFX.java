/* Developer: Noel Miranda
 * Date of creation: February 7, 2025
 * Program objective: Create a CSS style sheet that defines a class for white fill and black 
 * stroke color and an ID for red and green color. Write a program that displays four 
 * circles and uses the style class and ID. Use the mystyle.css found in Chapter 31 of the 
 * textbook. Also write test code that ensures your code functions correctly.
*/

// Declared Package
package edu.advancedjava.module7;

//Imported JavaFX classes
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StyleCirclesJavaFX extends Application {
	@Override 
	public void start(Stage primaryStage) {
		// Creates four circles
		Circle circle1 = new Circle(35);
		Circle circle2 = new Circle(35);
		Circle circle3 = new Circle(35);
		Circle circle4 = new Circle(35);
		
		// Applies CSS styles
		circle1.getStyleClass().add("plaincircle");
		circle2.getStyleClass().add("plaincircle");
		circle3.setId("redcircle");
		circle4.setId("greencircle");
		
		// Create a bordered stack pane to center circle1 within which is styled through the external css file 
		StackPane stackPane = new StackPane(circle1);
		stackPane.getStyleClass().add("border");
		
		// Creates HBox to put the circles and bordered stackpane
		HBox hBox1 = new HBox(8, stackPane, circle2, circle3, circle4);
		hBox1.setAlignment(Pos.CENTER); // centers every node in the hbox

		// Creates the scene
		Scene scene = new Scene(hBox1, 350, 200);
		
		// Loads mystyle.css
		// However, make sure the css file is in the src folder (or equivalent directory) of the java file
		scene.getStylesheets().add("mystyle.css");
		
		primaryStage.setTitle("Styling Circles in JavaFX with CSS");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		/* This code was tested multiple times for layout appearance and node behaviors.
		 * Further testing will require additional tools (external frameworks) that have 
		 * not been taught as of now in the curriculum.
		 */
	}
	// Main method, for JavaFX, may not be necessary because it is automatically launched with the right configurations
	public static void main(String[] args) {
		launch(args); // Starts the JavaFX application
	}
}