/* Developer: Noel Miranda
 * Date of creation: February 19, 2025
 * 
 * Program objective: 
 * Write a program that views and updates fan information stored in database 
 * titled "databasedb", user ID titled “*******” with a password “*******”.
 * 
 * The table name is to be “fans” with the fields of ID (integer, PRIMARY KEY), 
 * firstname (varchar(25)), lastname (varchar(25)), and favoriteteam (varchar(25)).
 * 
 * Your interface is to have 2 buttons to display and update records.
 *      - The display button will display the record with the provided ID in the 
 *      display (ID user provides). 
 *      - The update button will update the record in the database with the changes 
 *      made in the display. 
 * 
 * Your application is not to create or delete the table. 
 * 
 * To work on this using your home database, you can make the table and 
 * populate it. 
 * 
 * When the application is tested, the table will already be created 
 * and populated.
 * 
 * Write test code that ensures all methods and the interface functions 
 * correctly.
*/

// Declared package
package edu.advancedjava.module10;

// imported java Classes (SQL)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// imported javafx classes
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewUpdateFansDB extends Application{
	
	// Global Text fields to display record's data 
	private TextField idTF, firstNameTF, lastNameTF, favoriteTeamTF;
	
	// Global Buttons for displaying and updating records
	private Button displayBT, updateBT;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		// Declares TextFiels
		idTF = new TextField();
		idTF.setPromptText("Enter ID");
		idTF.setMaxWidth(200);
		
		firstNameTF = new TextField();
		firstNameTF.setPromptText("First name");
		firstNameTF.setMaxWidth(200);
		
		lastNameTF = new TextField();
		lastNameTF.setPromptText("Last name");
		lastNameTF.setMaxWidth(200);
		
		favoriteTeamTF = new TextField();
		favoriteTeamTF.setPromptText("Favorite team");
		favoriteTeamTF.setMaxWidth(200);
		
		// Declares Buttons
		displayBT = new Button("Display Record");
		updateBT = new Button("Update Record");
		updateBT.setDisable(true); // disables update button until a record is displayed
		
		
		// Setting up declared UI elements in specified locations using gridPane
		GridPane grid = new GridPane();
		grid.add(new Label("View Fans' Records from Our Database\n\t\tWith Just the Fan's ID #"), 1, 0);
		grid.add(new Label("Fan's ID (Required): "), 0, 2);
		grid.add(idTF, 1, 2);
		grid.add(displayBT, 2, 2);
		grid.add(new Label("Retrieved & Updateable Data "), 1, 5);
		grid.add(new Label("Fan's First Name: "), 0, 6);
		grid.add(firstNameTF, 1,6);
		grid.add(new Label("Fan's Last Name: "), 0, 7);
		grid.add(lastNameTF, 1, 7);
		grid.add(new Label("Fan's Favorite Team: "), 0, 8);
		grid.add(favoriteTeamTF, 1, 8);
		grid.add(updateBT, 1, 9);
		
		// Sets gaps between grid cells
		grid.setHgap(10);
		grid.setVgap(10);
		
		// Align gridpane's content at the center (within the grid)
		grid.setAlignment(Pos.CENTER);
		
		// Main layout
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(grid); // sets the grid at the center of borderpane
		
		Scene scene = new Scene(borderPane, 600, 350);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fans Database Lookup");
		primaryStage.show();
		
		// Event handling for buttons
		displayBT.setOnAction(e -> getRecord());
		updateBT.setOnAction(e -> updateRecord());
	}
	
	// Method for receiving database connection
	private Connection getConnection() throws SQLException, ClassNotFoundException {
		// Loads MySQL driver (new driver class and this step is unnecessary in modern JDBC)
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Establishes the connection with new URL format (helps avoid time zone errors and 
		// disables default SSL)
		// Credentials removed for privacy purposes; replace when running.
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/databasedb?useSSL=false&serverTimezone=UTC",
				"*******",
				"*******");
	}
	
	// Method to display database records when firing the display button event
	private void getRecord() {
		// Gets the text from ID text field
		String id = idTF.getText();
		
		// Query to retrieve records
		String sql = "SELECT * FROM fans WHERE ID = ?";
		
		
		// Tries to retrieve record from database with ID specified by user & 
		// automatically closes resources
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			// Catches exceptions for non-numeric values in the id text field
			try {
			pstmt.setInt(1, Integer.parseInt(id));
			} catch (Exception ex) {
				idTF.setText("Numeric Values Only");
				updateBT.setDisable(true);
				return;
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			// Displays retrieved data in the appropriate text fields
			if (rs.next()) {
				firstNameTF.setText(rs.getString("firstname"));
				lastNameTF.setText(rs.getString("lastname"));
				favoriteTeamTF.setText(rs.getString("favoriteteam"));
				updateBT.setDisable(false); // Makes update button available
			} else {
				idTF.setText("ID " + id + " not found in database");
				firstNameTF.setText("");
				lastNameTF.setText("");
				favoriteTeamTF.setText("");
				updateBT.setDisable(true);
			}
		} catch (ClassNotFoundException ex) {
			System.out.println("* Error loading mysql driver");
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			System.out.println("Error retrieving Record: ");
				ex.printStackTrace();
		}
	}
	
	// Method for updating existing records in database when firing the update record button event
	private void updateRecord() {
		// Gets the text from first name, last name, and favorite team text fields
		String id = idTF.getText();
		String firstName = firstNameTF.getText();
		String lastName = lastNameTF.getText();
		String favoriteTeam = favoriteTeamTF.getText();
		
		// Does not allow blank text fields when trying to update records to avoid unintentional mistakes
		if (firstName.isBlank() || lastName.isBlank() || favoriteTeam.isBlank()) {
			if (firstName.isBlank()) {
				firstNameTF.setText("Field can't be blank");
			}
			if (lastName.isBlank()) {
				lastNameTF.setText("Field can't be blank");
			}
			if (favoriteTeam.isBlank()) {
				favoriteTeamTF.setText("Field can't be blank");
			}
			
			return;
		}
		
		// SQL update statement
		String sql = "UPDATE fans SET " + 
					 "firstname = ?, " +
					 "lastname = ?, " +
					 "favoriteteam = ? " +
					 "WHERE ID = ?";
		
		// Tries to update record from database with ID specified by user & automatically closes resources
		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			// Fill SQL Statement with new data to be updated to database
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, favoriteTeam);
			
			// Checks again for non-numeric values in the id text field just in case 
			try {
			pstmt.setInt(4, Integer.parseInt(id));
			} catch (Exception ex) {
				idTF.setText("Numeric Values Only");
				updateBT.setDisable(true);
				return;
			}
			
			// execute update
			int rowsUpdated = pstmt.executeUpdate();
			
			// Checks if rows were updated successfully
			if (rowsUpdated > 0) {
				// Notifies user of successful update
				idTF.setText("Successfully updated ID " + id);
				firstNameTF.setText("");
				lastNameTF.setText("");
				favoriteTeamTF.setText("");
				updateBT.setDisable(true);
			} else {
				// Notifies user of no rows updated most likely due to them 
				// switching the id number after displaying a different id #
				idTF.setText("Unexistent ID in Database");
				firstNameTF.setText("");
				lastNameTF.setText("");
				favoriteTeamTF.setText("");
				updateBT.setDisable(true);
			}
			
		} catch (ClassNotFoundException | SQLException ex) {
			idTF.setText("Error updating ID " + id);
			firstNameTF.setText("");
			lastNameTF.setText("");
			favoriteTeamTF.setText("");
			updateBT.setDisable(true);
			ex.printStackTrace();
		}
	}
	
	// Not required, but included to ensure the program runs correctly in different setups
	public static void main(String[] args) {
		launch(args); // Launches the JavaFX application (calls the start() method)
	}	
}

/************************* TESTS PERFORMED ******************************
 * I first tested each method independently to ensure they function as 
 * expected (confirming changes with database after query and 
 * update executions), including error handling for invalid inputs and 
 * database interactions. After confirming their functionality, I then 
 * linked them to the UI elements. Given my current curriculum knowledge, 
 * this is the extent of the testing I am able to implement. Further 
 * testing could be added as I learn more about advanced concepts in 
 * testing and database management.
 * 
 * Handled: Prevents SQL Injection, prevents improper/empty inputs, disables
 * update button until a record is retrieved. 
 * 
 * Future Note: In professional setting, replace hard coded credentials.
*/