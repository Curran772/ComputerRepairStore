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
import javafx.scene.Node;
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

	private static ObservableList<Product> prodData = FXCollections.observableArrayList();

	public InventoryViewController() throws SQLException {

	}
	
	@FXML 
	void searchInventoryPressed(ActionEvent event) {

	}
	 

	@FXML
	void updateInventoryPressed(ActionEvent event) {
		
	}

	@FXML
	void switchToComputerRepairStoreView(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
}
