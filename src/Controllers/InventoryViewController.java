package Controllers;

import java.io.BufferedWriter;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryViewController {

	private ObservableList<Product> productList = FXCollections.observableArrayList();

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
	private TextField productCostTextField;
	 
	public void initialize() throws SQLException {

		quantityTextField.setEditable(true);

		searchInventoryListView.setItems(Update.getProducts()); // bind purchaseListView to products

		productList.setAll(searchInventoryListView.getItems());

		// when ListView selection changes, show product ImageView
		searchInventoryListView.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Product>() {
					@Override
					public void changed(ObservableValue<? extends Product> ov, Product oldValue, Product newValue) {
						inventoryPic.setImage(new Image(newValue.getThumbImage()));
						productNameTextField.setText(newValue.getItem());
						quantityTextField.setText(String.valueOf(newValue.getQuantity()));
						productCostTextField.setText(String.valueOf(newValue.getAmount()));

					}
				});
				
		//Wrap the ObservableLists in a FilteredList (initially display all data)
		FilteredList<Product> filteredList = new FilteredList<>(productList, item -> true);
		searchInventoryListView.setItems(filteredList);

		// Set the filter Predicate whenever the filter changes
		inventorySearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(item -> {
				// If filter text is empty, display all items.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare item with all items
				String lowerCaseFilter = newValue.toLowerCase();

				if (item.getItem().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches item
				} else {
					return false; // does not match
				}
			});
		});
	}

	public InventoryViewController() {}

	@FXML
	void updateInventoryPressed(ActionEvent event) throws SQLException {
		try {
			String quantity = quantityTextField.getText();
			String newName = productNameTextField.getText();
			String cost = productCostTextField.getText();

			int index = searchInventoryListView.getSelectionModel().getSelectedIndex();
			String oldName = productList.get(index).getItem();

			productList.get(index).setQuantity(Integer.parseInt(quantity));
			productList.get(index).setAmount(Double.parseDouble(cost));
			productList.get(index).setItem(newName);

			Update.updateProductName(oldName, newName);
			Update.updateProductQty(newName, quantity);
			Update.updateProductAmount(newName, cost);

			searchInventoryListView.getItems().get(index).setItem(newName);
			searchInventoryListView.getItems().get(index).setQuantity(Integer.parseInt(quantity));
			searchInventoryListView.getItems().get(index).setAmount(Double.parseDouble(cost));
			searchInventoryListView.refresh();
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Didn't select an item before updating inventory!");
		}
	}

	@FXML
	void switchToComputerRepairStoreView(ActionEvent event) throws IOException {
		Stage MainView = new Stage();
		MainView.initModality(Modality.APPLICATION_MODAL);
		MainView.setTitle("Computer Repair Store");
		Stage stage = (Stage) returnButton.getScene().getWindow();
		stage.hide();
	}
}
