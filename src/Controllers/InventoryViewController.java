package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import DBStructure.Update;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryViewController {

	@FXML
	private Button returnButton;

	@FXML
	private TextField inventorySearchBar;

	@FXML
	private Button searchInventoryBtn;

	@FXML
	private ImageView inventoryPic;

	@FXML
	private TextField productNameTextField;

	@FXML
	private TextField quantityTextField;

	@FXML
	private ListView<Product> searchInventoryListView;

	@FXML
	private Button updateInventory;

	@FXML
	private TextField inventoryCostTextField;
	private int quantity = 0;
	private double cost = 0.00;

	public void initialize() throws SQLException {

		searchInventoryListView.setItems(Update.getProducts()); // bind purchaseListView to products

		// when ListView selection changes, show product ImageView
		searchInventoryListView.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Product>() {
					@Override
					public void changed(ObservableValue<? extends Product> ov, Product oldValue, Product newValue) {
						inventoryPic.setImage(new Image(newValue.getThumbImage()));
						productNameTextField.setText(newValue.getItem());
						quantityTextField.setText(String.valueOf(quantity));
						inventoryCostTextField.setText(String.valueOf(cost));
					}
				});
	}

	public InventoryViewController() throws SQLException {}
	@FXML
	void searchInventoryPressed(ActionEvent event) {}

	@FXML
	void updateInventoryPressed(ActionEvent event) {}

	@FXML
	void switchToComputerRepairStoreView(ActionEvent event) throws IOException {
		Stage MainView = new Stage();
		MainView.initModality(Modality.APPLICATION_MODAL);
		MainView.setTitle("Computer Repair Store");

		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.hide();
	}
}
