package Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class ComputerRepairStore extends Application {

	public static Scene scene;
	public static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		try {
			ComputerRepairStore.stage = stage;
			scene = new Scene(loadFXML("ComputerRepairStore"));
			stage.setTitle("Computer Repair Store"); // displayed in window's title bar
			stage.setScene(scene); // attach scene to stage
			stage.show(); // display the stage
			
			stage.setOnCloseRequest(event -> {// alert box confirming exit will show before closing
				event.consume();				// when the x button in the top corner is pressed
			    exitButtonPressed(stage);
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// When any exit button is pressed in the application
	public static void exitButtonPressed(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You are about to Exit!");
				
		if(alert.showAndWait().get() == ButtonType.OK) {		
		System.out.println("You successfully logged out!");
		System.exit(0);
		}
	}

	// Loads the passed in string and returns the loaded fxml file with the matching name
	public static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(ComputerRepairStore.class.getResource("/Resources/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	// Change the root scene
	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	// Change the window title
	public static void changeStageTitle(String title) {
		stage.setTitle(title);
	}

	public static void main(String[] args) {
		// create a Controllers.ComputerRepairStore object and call its start method
		launch(args);
	}
}
