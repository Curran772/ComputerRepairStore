package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import DBStructure.DBMethods;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryViewController extends ComputerRepairStoreController{

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
	
	//private ObservableList<Product> listItems = FXCollections.observableArrayList();
	
	
	
	public void initialize() throws SQLException {
		//ComputerRepairStoreController cp = new ComputerRepairStoreController();
		ArrayList<Product> listItems = new ArrayList<>(getInventoryList());
		
		// get items from database
		
		//listItems = DBMethods.getProducts("inventory");
		searchInventoryListView.getItems().addAll(listItems);//DBMethods.getProducts("inventory"));
		
		searchInventoryListView.setItems(DBMethods.getProducts("inventory")); // bind purchaseListView to products
		
		
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
	
		
	@FXML
	void searchInventoryPressed(ActionEvent event) {
		searchInventoryListView.getItems().clear();
		searchInventoryListView.getItems().addAll(searchList(inventorySearchBar.getText(),listItems));
				
	}
	

	private List<String> searchList(String searchItems, List<String> listOfItems){
		
		List<String> searchItemsArray = Arrays.asList(searchItems.trim().split(" "));
		
		return listOfItems.stream().filter(input -> {
			return searchItemsArray.stream().allMatch(listItems -> 
			input.toLowerCase().contains(listItems.toLowerCase()));
		}).collect(Collectors.toList());
				
	}

	

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
