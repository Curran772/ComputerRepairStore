package Controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.SortOrder;
import javax.xml.bind.JAXB;

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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

	private ObservableList<Product> productList = FXCollections.observableArrayList();

	public void initialize() throws SQLException {

		quantityTextField.setEditable(true);

		searchInventoryListView.setItems(Update.getProducts()); // bind purchaseListView to products

		productList.setAll(searchInventoryListView.getItems());

		// when ListView selection changes, show product ImageView
		searchInventoryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
			@Override
			public void changed(ObservableValue<? extends Product> ov, Product oldValue, Product newValue) {
				inventoryPic.setImage(new Image(newValue.getThumbImage()));
				productNameTextField.setText(newValue.getItem());
				quantityTextField.setText(String.valueOf(newValue.getQuantity()));
				inventoryCostTextField.setText(String.valueOf(newValue.getAmount()));

			}
		});

		// Wrap the ObservableLists in a FilteredList (initially display all data)
		FilteredList<Product> filteredList = new FilteredList<>(productList, item -> true);
		searchInventoryListView.setItems(filteredList);

		// Set the filter Predicate whenever the filter changes
		inventorySearchBar.textProperty().addListener((obervable, oldValue, newValue) -> {
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

	

	public InventoryViewController() throws SQLException {
	}

	@FXML
    void updateInventoryPressed(ActionEvent event) throws SQLException {
        String quantity = quantityTextField.getText();
        String name = productNameTextField.getText();
        int index = searchInventoryListView.getSelectionModel().getSelectedIndex();

        productList.get(index).setQuantity(Integer.parseInt(quantity));
        searchInventoryListView.getItems().get(index).setQuantity(Integer.parseInt(quantity));

        Update.updateProductQty(name, quantity);

        searchInventoryListView.refresh();
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
