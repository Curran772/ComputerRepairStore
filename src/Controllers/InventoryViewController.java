package Controllers;
import java.io.IOException;
import java.sql.SQLException;

import DBStructure.Update;
import Objects.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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


	 
	public void initialize() throws SQLException {
		searchInventoryListView.setItems(Update.getProducts()); // bind purchaseListView to products
		ObservableList<Product> productList = FXCollections.observableArrayList();	
		  
		productList.setAll(searchInventoryListView.getItems());
				
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
