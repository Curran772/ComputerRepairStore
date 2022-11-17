package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBStructure.DBMethods;
import DBStructure.Update;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private int quanity = 0;
	private double cost = 0.00;

	private static ObservableList<Product> prodData = FXCollections.observableArrayList();
	private final ObservableList<Product> products = FXCollections.observableArrayList();

	public void initialize() {

		// populate the ObservableList<Product>
		products.add(new Product("Hard Drive", 56.99, 1, "Resources/pictures/ssd.jpg"));
		products.add(new Product("Charging Cord", 6.99, 2, "Resources/pictures/usbCAdapter.jpg"));
		products.add(new Product("Flash Drive", 15.99, 2, "Resources/pictures/thumbDrives.jpg"));
		products.add(new Product("Power Cord", 23.99, 1, "Resources/pictures/usbCadapter.jpg"));
		products.add(new Product("USB RJ45", 25.99, 1, "Resources/pictures/usbRJ45.jpg"));
		products.add(new Product("VGA Adapter", 7.59, 1, "Resources/pictures/vgaAdapter.jpg"));
		products.add(new Product("RGB Keyboard", 103.99, 1, "Resources/pictures/rgbKeyBoard.jpg"));
		products.add(new Product("Mini Display Adapter", 8.99, 1, "Resources/pictures/miniDisplayAdapter.jpg"));
		products.add(new Product("I9 Intel", 423.99, 1, "Resources/pictures/i9Intel.jpg"));
		products.add(new Product("ddr4 RAM", 60.99, 1, "Resources/pictures/ddr4RAM.jpg"));
		products.add(new Product("ddr3 RAM", 16.99, 1, "Resources/pictures/ddr3RAM.jpg"));
		products.add(new Product("cpuCooler", 59.99, 1, "Resources/pictures/cpuCooler2.jpg"));
		products.add(new Product("cpuCooler", 50.99, 1, "Resources/pictures/cpuCooler1.jpg"));

		searchInventoryListView.setItems(products.sorted()); // bind purchseListView to products

		// when ListView selection changes, show product ImageView
		searchInventoryListView.getSelectionModel().selectedItemProperty().addListener(

				new ChangeListener<Product>() {

					@Override
					public void changed(ObservableValue<? extends Product> ov, Product oldValue, Product newValue) {
						inventoryPic.setImage(new Image(newValue.getThumbImage()));
						productNameTextField.setText(newValue.getItem());
						quantityTextField.setText(String.valueOf(quanity));
						inventoryCostTextField.setText(String.valueOf(cost));
					}
				});
	}

	public InventoryViewController() throws SQLException {

	
	}

	@FXML
	void searchInventoryPressed(ActionEvent event) throws ClassNotFoundException, SQLException {
	}

	@FXML
	void updateInventoryPressed(ActionEvent event) {

	}

	@FXML
	void switchToComputerRepairStoreView(ActionEvent event) throws IOException {
		Stage MainView = new Stage();
		MainView.initModality(Modality.APPLICATION_MODAL);
		MainView.setTitle("Computer Repair Store");
		//Scene inventory = new Scene(Main.loadFXML("ComputerRepairStore"));

		//MainView.setScene(inventory);
		//MainView.showAndWait();

		
		Stage stage = (Stage) returnButton.getScene().getWindow(); stage.hide();
		 
	}

	// Pop Items for TableView
	/*
	 * @FXML void popProduct(Product product) throws ClassNotFoundException {
	 * ObservableList<Product> prodData = FXCollections.observableArrayList();
	 * prodData.add(new Product(product.getItem(), product.getAmount(),
	 * product.getQuantity())); searchInventoryListView.setItems(prodData); }
	 */
}
