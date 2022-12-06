package Controllers;
import java.util.function.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.SortOrder;

import DBStructure.DBMethods;
import DBStructure.Update;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

		
		  Product p1 = new Product("Hard Drive", 56.99, 1, "Resources/pictures/ssd.jpg"); 
		  Product p2 = new Product("Charging Cord", 6.99, 2, "Resources/pictures/usbCAdapter.jpg"); 
		  Product p3 = new Product("Flash Drive", 15.99, 2,"Resources/pictures/thumbDrives.jpg"); 
		  Product p4 = new Product("Power Cord", 23.99, 1, "Resources/pictures/usbCadapter.jpg"); 
		  Product p5 = new Product("USB RJ45", 25.99, 1, "Resources/pictures/usbRJ45.jpg" ); 
		  Product p6 = new Product("VGA Adapter", 7.59, 1, "Resources/pictures/vgaAdapter.jpg"); 
		  Product p7 = new Product("RGB Keyboard", 103.99, 1, "Resources/pictures/rgbKeyBoard.jpg"); 
		  Product p8 = new Product("Mini Display Adapter", 8.99, 1, "Resources/pictures/miniDisplayAdapter.jpg"); 
		  Product p9 = new Product("I9 Intel", 423.99, 1, "Resources/pictures/i9Intel.jpg"); 
		  Product p10 = new Product("ddr4 RAM", 60.99, 1, "Resources/pictures/ddr4RAM.jpg"); 
		  Product p11 = new Product("ddr3 RAM", 16.99, 1, "Resources/pictures/ddr3RAM.jpg"); 
		  Product p12 = new Product("MSI Coreliquid CPU AIO Cooler", 59.99, 1, "Resources/pictures/cpuCooler2.jpg");
		  Product p13 = new Product("Coolermaster CPU AIO Cooler", 50.99, 1, "Resources/pictures/cpuCooler1.jpg");
		 
	 
	public void initialize() throws SQLException {
		searchInventoryListView.setItems(Update.getProducts()); // bind purchaseListView to products 
		ObservableList<Product> productList = FXCollections.observableArrayList();	
		  
		productList.addAll(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13);
				
				
		// when ListView selection changes, show product ImageView
		searchInventoryListView.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Product>() {
					@Override
					public void changed(ObservableValue<? extends Product> ov, Product oldValue, Product newValue) {
						inventoryPic.setImage(new Image(newValue.getThumbImage()));
						productNameTextField.setText(newValue.getItem());
						quantityTextField.setText(String.valueOf(newValue.getQuantity()));
						inventoryCostTextField.setText(String.valueOf(newValue.getAmount()));
										
					}
				});
		 
				
		//Wrap the ObservableLists in a FilteredList (initially display all data)
		FilteredList<Product> filteredList = new FilteredList<>(productList, item -> true);
		searchInventoryListView.setItems(filteredList);
		
		//Set the filter Predicate whenever the filter changes
		inventorySearchBar.textProperty().addListener((obervable, oldValue, newValue) -> {
			filteredList.setPredicate(item -> {
				//If filter text is empty, display all items.
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				//Compare item with all items 
				String lowerCaseFilter = newValue.toLowerCase();
											
				if (item.getItem().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches item
				
				}else { 
					return false; // does not match
				}
			
			});
		});
						
}
	
	public InventoryViewController() throws SQLException{}	
	
	public void searchList() {
		
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
