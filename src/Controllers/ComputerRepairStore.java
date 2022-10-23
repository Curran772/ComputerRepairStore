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

public class ComputerRepairStore extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Resources/ComputerRepairStore.fxml"));

			Scene scene = new Scene(root); // attach scene graph to scene
			stage.setTitle("Controllers.ComputerRepairStore"); // displayed in window's title bar
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
	
	public void exitButtonPressed(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("You are about to Exit!");
		//alert.setContentText("Do you want to Exit?");
				
		if(alert.showAndWait().get() == ButtonType.OK) {		
		System.out.println("You successfully logged out!");
		stage.close();
		}
		
	}

	public static void main(String[] args) {
		// create a Controllers.ComputerRepairStore object and call its start method
		launch(args);
	}
}
