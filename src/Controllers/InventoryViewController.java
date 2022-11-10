package Controllers;

import java.io.IOException;
import java.net.URL;
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
	

	private final ObservableList<Product> products = FXCollections.observableArrayList();
	
	public void initialize(URL location, ResourceBundle resources) {
		 // load database
		
		  try {
		  Update.runSqlScript("schema"); ResultSet rs =
		  DBMethods.dataExecuteQuery("SELECT item_name FROM item_db.inventory LIMIT 1"
		  );
		  
		  System.out.println("Database connected and populated!");
		  
		  searchInventoryListView.setItems(products); // bind contact to contactListView
		  } 
		  catch (SQLException e) {
		  System.out.println("DB Connection failed at table population!" + e); 
		  }
		  catch
		  (Exception ex) { System.out.println("DB Connection failed at runSqlScript!" +
		  ex); 
		  }
		  

		  searchInventoryListView.setItems(products.sorted()); 

			// when ListView selection changes, show large cover in ImageView
		  searchInventoryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
				@Override
				public void changed(ObservableValue<? extends Product> ov, Product oldValue, Product newValue) {
					//inventoryPic.setImage(new Image(newValue.getThumbImage()));
					productNameTextField.setText(newValue.getItem());
					inventoryCostTextField.setValue(new Double(amount));
					quantityTextField.setValue(newValue.getQuantity());
					

				}
			});
	}
	@FXML
	void searchInventoryPressed(ActionEvent event) {

	}

	@FXML
	void updateInventoryPressed(ActionEvent event) {

	}

	@FXML
	void switchToComputerRepairStoreView(ActionEvent event) throws IOException {
			Stage MainView = new Stage();
			MainView.initModality(Modality.APPLICATION_MODAL);
			MainView.setTitle("Computer Repair Store");
			Scene inventory = new Scene(Main.loadFXML("ComputerRepairStore"));

			MainView.setScene(inventory);
			MainView.showAndWait();
		
		/*
		 * Stage stage = (Stage) returnButton.getScene().getWindow(); stage.hide();
		 */
}
}
