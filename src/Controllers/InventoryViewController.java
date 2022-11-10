package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DBStructure.DBMethods;
import DBStructure.Update;

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
import javafx.scene.image.ImageView;

public class InventoryViewController{

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
	

	// Try to populate the table with data from the MySQL database
	public ObservableList<Product> getProducts() throws SQLException {
		ObservableList<Product> products = FXCollections.observableArrayList();
		try {

			ResultSet rs = DBMethods.dataExecuteQuery("SELECT * FROM item_db.inventory");

			while (rs.next()) {
				products.add(new Product(rs.getString("item_name"),
						rs.getDouble("item_amount"), rs.getInt("item_qty")));
			}

		} catch (SQLException e) {
			System.out.println("DB Connection failed at table population!");
		}

		return products;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
			
		
		// load database
		
		  try {
		  Update.runSqlScript("schema"); ResultSet rs =
		  DBMethods.dataExecuteQuery("SELECT item_name FROM item_db.inventory LIMIT 1"
		  );
		  
		  System.out.println("Database connected and populated!");
		  
		  searchInventoryListView.setItems(getProducts()); 
		  } 
		  catch (SQLException e) {
		  System.out.println("DB Connection failed at table population!" + e); 
		  }
		  catch
		  (Exception ex) { System.out.println("DB Connection failed at runSqlScript!" +
		  ex); }
		 

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
