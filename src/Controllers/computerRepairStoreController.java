package Controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import DBStructure.DBMethods;
import DBStructure.Update;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;

public class computerRepairStoreController implements Initializable {

	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

	private BigDecimal taxPercentage = new BigDecimal(0.07);

	private int count = 0;

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button returnButton;
	
	@FXML
	private Button button0;

	@FXML
	private Button button1;

	@FXML
	private Button button2;

	@FXML
	private Button button3;

	@FXML
	private Button button4;

	@FXML
	private Button button5;

	@FXML
	private Button button6;

	@FXML
	private Button button7;

	@FXML
	private Button button8;

	@FXML
	private Button button9;

	@FXML
	private Button buttonClear;

	@FXML
	private Button buttonPeriod;

	@FXML
	private Button payButton;

	@FXML
	private TextField pmtAmountField;

	@FXML
	private TextField pmtChangeField;

	@FXML
	private ChoiceBox<String> pmtMethodField;

	private String[] pmtType = { "Cash", "Check", "Card" };

	@FXML
	private Button printReceiptButton;

	@FXML
	private TableView<Product> tableView;

	@FXML
	private TableColumn<Product, Double> amountColumn;

	@FXML
	private TableColumn<Product, Integer> quantityColumn;

	@FXML
	private TableColumn<Product, String> itemColumn;

	@FXML
	private Button removeItemButton;

	@FXML
	private Button clearPurchaseButton;

	@FXML
	private Button checkInventoryButton;

	@FXML
	private TextField subTotalField;

	@FXML
	private TextField taxField;

	@FXML
	private TextField totalDueField;


	public void initialize(java.net.URL location, ResourceBundle resources) {

		// set up the columns in the table
		itemColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("item"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("amount"));

		// load database
		try {
			Update.runSqlScript("schema");

			Connection conn = DBMethods.getConnection();
			ResultSet rs = conn.createStatement().executeQuery("SELECT item_name FROM item_db.product LIMIT 1");

			// Only populates table with first time data if table is empty
			if (!rs.next()) {
				Update.runSqlScript("seed");
				System.out.println("Database table is empty... planting seed data!");
			}

			System.out.println("Database connected and populated!");

			tableView.setItems(getProducts());
		} catch (SQLException e) {
			System.out.println("DB Connection failed at table population!");
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// update the table to allow quantity to be changed
		tableView.setEditable(true);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		// This will allow the user to select multiple rows for deletion
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// These items are for the choiceBox
		// initializing choice box
		pmtMethodField.getItems().addAll(pmtType);
		pmtMethodField.setOnAction(this::choiceBoxField);
	}

	/**
	 * This method allows us to switch to the ComputerRepairStore FXML file
	 */
	@FXML
	public void switchToComputerRepairStore(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ComputerRepairStore.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
		
	/**
	 * This method allows us to switch to the InventoryView FXML file
	 */
	@FXML
	public void switchToInventoryView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("InventoryView.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This method gets the value from the choice box and sets it.
	 */
	@FXML
	public void choiceBoxField(ActionEvent event) {
		String pmtChoice = pmtMethodField.getValue();
		pmtMethodField.setAccessibleText(pmtChoice);
	}

	/**
	 * This method allows user to double-click on the quantity column cell and edit it to update the purchase table
	 */
	@FXML
	public void changeQuantityColumnEvent(CellEditEvent<Product, Integer> editedCell) {
		Product quantitySelected = tableView.getSelectionModel().getSelectedItem();
		quantitySelected.setQuantity((int)editedCell.getNewValue());
	}

	/**
	 * this method will remove the selected row(s) from the table
	 */
	@FXML
	private void removeItemButtonPressed() {
		ObservableList<Product> selectedRows, allProducts;
		allProducts = tableView.getItems();

		// this gives us the rows that were selected
		selectedRows = tableView.getSelectionModel().getSelectedItems();

		// loop over selected rows and remove the products from the list that are
		// selected
		for (Product products : selectedRows) {
			allProducts.remove(products);
		}
	}

	/**
	 * Action event when the clear purchase button is pressed
	 */
	@FXML
	private void clearPurchaseButtonPressed(ActionEvent event) {
		ObservableList<Product> selectedRows, allProducts;
		allProducts = tableView.getItems();
		selectedRows = tableView.getSelectionModel().getSelectedItems();
	}

	@FXML
	private void printReceiptButtonPressed(ActionEvent event) {

	}

	@FXML
	private void exitButtonPressed(ActionEvent event) {
		ComputerRepairStore.exitButtonPressed(stage);
	}

	/**
	 * This method adds a period if the payment field does not contain a "."
	 */
	@FXML
	public void periodButtonPressed(ActionEvent event) {

		if (event.getSource() == buttonPeriod) {
			if(count < 1) {
				pmtAmountField.setText(pmtAmountField.getText() + ".");
				count ++;
			}
		}
	}

	/**
	 * This method clears the payment and change fields
	 */
	@FXML
	public void clearButtonPressed(ActionEvent event) {
		pmtAmountField.setText("");
		pmtChangeField.setText("");
	}

	/**
	 * This method calculates the amount of money due back, if there is any, when
	 * the pay button is pressed
	 * 
	 */
	@FXML
	private void payButtonPressed(ActionEvent event) {
		try {
			BigDecimal pmtAmount = new BigDecimal(pmtAmountField.getText());
			BigDecimal total = new BigDecimal(totalDueField.getText());
			BigDecimal change = total.subtract(pmtAmount);

			pmtChangeField.setText(currency.format(change));

		} catch (IllegalArgumentException e) {
			if (pmtAmountField.getText().isEmpty()) {
				System.out.println("Payment Received must be >= 0.");
			}
		}
	}

	/**
	 * This method calculates the total of all items added to purchase list,
	 * calculates the tax and adds it to the subtotal, then calculates the total
	 * due
	 */
	@FXML //
	private void totalBoxes(ActionEvent event) {
		try {
			BigDecimal itemCost = new BigDecimal(amountColumn.getText());
			BigDecimal subtotal = itemCost.add(itemCost);

			subTotalField.setText(currency.format(subtotal));

			BigDecimal tax = subtotal.multiply(taxPercentage);
			BigDecimal total = subtotal.add(tax);

			taxField.setText(currency.format(tax));
			totalDueField.setText(currency.format(total));
			currency.setRoundingMode(RoundingMode.HALF_UP);
		} catch (NumberFormatException ex) {
			 subTotalField.setText("Enter Item Cost ");
			 subTotalField.selectAll();
			 subTotalField.requestFocus();
		}
	}

	 @FXML public void numberButtonPressed(ActionEvent event) {
		 String enterNumber = pmtAmountField.getText();
		 if (enterNumber == "") {
			 pmtAmountField.setText(button7.getText());
		 } else {
			 enterNumber = pmtAmountField.getText() + button7.getText();
			 pmtAmountField.setText(enterNumber);
		 }
	 }

	/**
	 * Method that returns an observable list of the items in the database table
	 */
	public ObservableList<Product> getProducts() throws SQLException {
		ObservableList<Product> products = FXCollections.observableArrayList();

		// Try to populate the table with data from the MySQL database
		try {
			Connection conn = DBMethods.getConnection();

			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM item_db.product");

			while (rs.next()) {
				products.add(new Product(rs.getString("item_name"),
						rs.getDouble("item_amount"), rs.getInt("item_qty")));
			}
		} catch (SQLException e) {
			System.out.println("DB Connection failed at table population!");
			throw e;
		}
		return products;
	}

	public void buttonClearPressed(ActionEvent actionEvent) {
	}
}
