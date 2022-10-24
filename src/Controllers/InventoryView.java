package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InventoryView {

    @FXML
    private Button returnButton;

    @FXML
    void switchToComputerRepairStoreView(ActionEvent event) {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();
    }

}
