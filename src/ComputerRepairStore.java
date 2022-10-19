import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ComputerRepairStore extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ComputerRepairStore.fxml"));

			Scene scene = new Scene(root); // attach scene graph to scene
			stage.setTitle("ComputerRepairStore"); // displayed in window's title bar
			stage.setScene(scene); // attach scene to stage
			stage.show(); // display the stage
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// create a ComputerRepairStore object and call its start method
		launch(args);
	}
}
