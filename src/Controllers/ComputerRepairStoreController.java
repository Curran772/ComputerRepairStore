package Controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;

import DBStructure.DBMethods;
import DBStructure.Update;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import static Controllers.Main.stage;

public class ComputerRepairStoreController implements Initializable {

	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

	private ObservableList<Product> prodObsList = FXCollections.observableArrayList();
<<<<<<< HEAD
		
	
	Employee e1 = new Employee("111111", "Jane", "Green", "111111", "123");
	Employee e2 = new Employee("222222", "Max", "Brown", "222222", "123");
	Employee e3 = new Employee("333333", "Rob", "Schneider", "333333", "123");
	Employee e4 = new Employee("444444", "Dweight", "Howard", "444444", "123");
	Employee e5 = new Employee("555555", "Amy", "Smith", "555555", "123");
	Employee e6 = new Employee("666666", "Stacy", "Anderson", "666666", "123");
=======
>>>>>>> Curran

	private BigDecimal taxPercentage = new BigDecimal(0.07);

	private int count = 0;
	private double total = 0;
	private double tax = 0;
	private double totalDue = 0;
	private double change = 0;
	private double totalPaymentAmount = 0;

	@FXML
	private TextField searchBar;

	@FXML
	private Button searchBtn;

	@FXML
	private ListView<Product> purchaseListView;

	@FXML
	private ImageView productImageView;

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

	ObservableList<String>pmtType = FXCollections.observableArrayList("Cash", "Check", "Card");

	@FXML
	private Button printReceiptButton;

	@FXML
	private TableView<Product> tableView;

	@FXML
	private TableColumn<Product, String> itemColumn;

	@FXML
	private TableColumn<Product, Integer> quantityColumn;

	@FXML
	private TableColumn<Product, Double> amountColumn;

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
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// set up the columns in the table
		itemColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("item"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("amount"));

		// set custom ListView cell factory
		purchaseListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
			@Override
			public ListCell<Product> call(ListView<Product> listView) {
				return new ImageTextCell();
			}
		});

		// load database
		try {
			Update.runSqlScript("schema");
			ResultSet rs = DBMethods.dataExecuteQuery("SELECT item_name FROM item_db.inventory");

			// Only populates table with first time data if table is empty
			if (!rs.next()) {
				Update.runSqlScript("seed");
				System.out.println("Database table is empty... planting seed data!");
			}
			System.out.println("Database connected and populated!");

			purchaseListView.setItems(Update.getProducts("inventory"));
			prodObsList.addAll(Update.getProducts("user_selection"));

			tableView.setItems(prodObsList);
			tableView.refresh();

		} catch (SQLException e) {
			System.out.println("DB Connection failed at table population!" + e);
		} catch (Exception ex) {
			System.out.println("DB Connection failed at runSqlScript!" + ex);
		}

		// This will allow the user to select multiple rows for deletion
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// These items are for initializing choice box
		pmtMethodField.getItems().addAll(pmtType);
		pmtMethodField.setOnAction(this::choiceBoxField);
		pmtMethodField.setValue("Cash");

		updateTotalFields();
	}

	/**
	 * This method allows us to switch to the InventoryView FXML file
	 */
	@FXML
	public void switchToInventoryView(ActionEvent event) throws IOException {
		Stage InvView = new Stage();
		InvView.initModality(Modality.APPLICATION_MODAL);
		InvView.setTitle("Inventory");
		Scene inventory = new Scene(Main.loadFXML("InventoryView"));

		InvView.setScene(inventory);
		InvView.showAndWait();
	}
	
	@FXML
	void searchBtnPressed(ActionEvent event) {}

	/**
	 * This method gets the value from the choice box and sets it.
	 */
	@FXML
	public void choiceBoxField(ActionEvent event) {
		String pmtChoice = pmtMethodField.getValue();
		pmtMethodField.setAccessibleText(pmtChoice);
	}

	/**
	 * This method allows user to double-click on the quantity column cell and edit
	 * it to update the purchase table
	 */
	@FXML
	public void changeQuantityColumnEvent(CellEditEvent<Product, Integer> editedCell) {
		Product quantitySelected = tableView.getSelectionModel().getSelectedItem();
		quantitySelected.setQuantity(editedCell.getNewValue());
	}

	/**
	 * This method makes it so when the user clicks an item in the list,
	 * the item is added to the users checkout list
	 */
	@FXML
	void addItemToList(MouseEvent event) {
		int index = tableView.getItems().indexOf(purchaseListView.getSelectionModel().getSelectedItem());

		Product product = prodObsList.get(index);

		// Check if the item is already in the table or not
		if (!tableView.getItems().contains(product)) {
			prodObsList.addAll(purchaseListView.getSelectionModel().getSelectedItem());
		}
		if (tableView.getItems().contains(product)) {
			double amount = prodObsList.get(index).getAmount() +
					purchaseListView.getSelectionModel().getSelectedItem().getAmount();
			int quantity = prodObsList.get(index).getQuantity();

			prodObsList.get(index).setAmount(amount);
			prodObsList.get(index).setQuantity(quantity + 1);
			prodObsList.set(index, prodObsList.get(index));
		}

		// Sets the table equal to the Observable List and refreshes it
		tableView.setItems(prodObsList);
		tableView.refresh();
		updateTotalFields();
	}

	/**
	 * this method will remove the selected row(s) from the table
	 */
	@FXML
	private void removeItemButtonPressed() {
		List items =  new ArrayList(tableView.getSelectionModel().getSelectedItems());

		prodObsList.removeAll(items);
		tableView.getSelectionModel().clearSelection();

		updateTotalFields();
	}

	/**
	 * Action event when the clear purchase button is pressed
	 */
	@FXML
	private void clearPurchaseButtonPressed(ActionEvent event) throws SQLException {
		tableView.getItems().clear();
		updateTotalFields();
		prodObsList.clear();
	}

	/**
	 * This method prints a receipt view of purchase totals to the console. Does not
	 * show the products purchased.
	 *
	 */
	@FXML
	private void printReceiptButtonPressed(ActionEvent event) {
		Date date = new Date();		
		Employee user = new Employee();
		e1 = new Employee("111111", "Jane", "Green", "111111", "123");
		e2 = new Employee("222222", "Max", "Brown", "222222", "123");
		e3 = new Employee("333333", "Rob", "Schneider", "333333", "123");
		e4 = new Employee("444444", "Dweight", "Howard", "444444", "123");
		e5 = new Employee("555555", "Amy", "Smith", "555555", "123");
		e6 = new Employee("666666", "Stacy", "Anderson", "666666", "123");
		
		if(user.equals(e1)) {
			System.out.printf("%s %s", e1.getFirstName(), e1.getLastName());
		}else if(user.equals(e2)) {
			System.out.printf("%s %s", e2.getFirstName(), e2.getLastName());
		}else if(user.equals(e3)) {
			System.out.printf("%s %s", e3.getFirstName(), e3.getLastName());
		}else if(user.equals(e4)) {
			System.out.printf("%s %s", e4.getFirstName(), e4.getLastName());
		}else if(user.equals(e5)) {
			System.out.printf("%s %s", e5.getFirstName(), e5.getLastName());
		}else {
			System.out.printf("%s %s", e1.getFirstName(), e1.getLastName());//= new Employee("111111", "Jane", "Green", "password");
		}		
		
		System.out.println();
		ObservableList<Product> purchase = tableView.getItems();

		try {
			File file = new File("invoice.txt");
			FileWriter fw = new FileWriter(file, true);
			if(!file.exists()) {
				file.createNewFile()	;
			}
			PrintWriter pw = new PrintWriter(fw);

			if (pmtChangeField.getText().isEmpty()){
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid payment");
				alert.setHeaderText("Please Press Pay Button!");
				alert.showAndWait();
			} else {
				pw.println();
				pw.println("**************************************************************************");
				pw.println("				      CRS						           				   ");
				pw.println("		             Computer Repair Store				         	     ");
				pw.println("**************************************************************************");
				pw.println();
				pw.println(date);
				pw.println();
				purchase.forEach(pw::println);
				pw.println();
				pw.printf(
						"SubTotal: $%.2f%nTax: $%.2f%nTotal Due: $%.2f%n%nPayment Method: %s%nPayment Amount: $%.2f%nChange: $%.2f%n",
						getTotal(), getTax(), getTotalDue(), pmtMethodField.getValue(), getTotalPaymentAmount(), getChange());
				pw.println();
				pw.printf("You were helped by %s %s.%n%n  Thank you for your purchase!%n%n", user.getFirstName(), user.getLastName());
				pw.close();
				pw.println();
			}
		}
		catch (IOException e) {
			e.printStackTrace();

		}

		System.out.println("Reciept saved to file");
	}

	/**
	 * This method exits the program when exit button is pressed
	 * 
	 */
	@FXML
	private void exitButtonPressed(ActionEvent event) throws SQLException {
		DBMethods.dataExecuteUpdate("DELETE FROM item_db.user_selection;");

		for (Product product : prodObsList) {
			String item_name = product.getItem();
			double item_amount = product.getAmount();
			int item_qty = product.getQuantity();

			DBMethods.dataExecuteUpdate("INSERT INTO item_db.user_selection " +
					"(item_name, item_amount, item_qty) VALUES\n " +
					"('" + item_name + "', " + item_amount + ", " + item_qty + ");");
		}

		Main.exitButtonPressed(stage);
	}

	/**
	 * This method adds a period if the payment field does not contain a "."
	 */
	@FXML
	public void periodButtonPressed(ActionEvent event) {

		if (event.getSource() == buttonPeriod) {
			if (count < 1) {
				pmtAmountField.setText(pmtAmountField.getText() + ".");
				count++;
			}
		}
	}

	/**
	 * This method clears the payment and change fields
	 */
	@FXML
	public void buttonClearPressed(ActionEvent event) {
		pmtAmountField.setText("");
		pmtChangeField.setText("");
		count = 0;
	}

	/**
	 * This method calculates the amount of money due back, if there is any, when
	 * the pay button is pressed
	 * 
	 */
	@FXML
	private void payButtonPressed(ActionEvent event) {
		try {
			BigDecimal pmtAmount = new BigDecimal(String.valueOf(pmtAmountField.getText()));
			setTotalPaymentAmount(pmtAmount.doubleValue());
			BigDecimal total = new BigDecimal(getTotalDue());
			BigDecimal change = total.subtract(pmtAmount);
			setChange(getTotalDue() - getTotalPaymentAmount());

			if (pmtAmount.doubleValue() < getTotalDue()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Not enough payment entered");
				alert.setHeaderText("Please enter payment greater than or equal to total due!");
				alert.showAndWait();
			} else {
				pmtChangeField.setText(currency.format(change));
			}

		} catch (IllegalArgumentException e) {
			if (pmtAmountField.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("No payment entered");
				alert.setHeaderText("Please enter a payment amount!");
				alert.showAndWait();
			}
		}
	}

	/**
	 * This method calculates the total of all items added to purchase list,
	 * calculates the tax and adds it to the subtotal, then calculates the total due
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

	/**
	 * This method allows user to add numbers to the payment Amount Field
	 */
	@FXML
	public void numberButtonPressed(ActionEvent event) {
		if (event.getSource() == button0) {
			pmtAmountField.setText(pmtAmountField.getText() + "0");
		} else if (event.getSource() == button1) {
			pmtAmountField.setText(pmtAmountField.getText() + "1");
		} else if (event.getSource() == button2) {
			pmtAmountField.setText(pmtAmountField.getText() + "2");
		} else if (event.getSource() == button3) {
			pmtAmountField.setText(pmtAmountField.getText() + "3");
		} else if (event.getSource() == button4) {
			pmtAmountField.setText(pmtAmountField.getText() + "4");
		} else if (event.getSource() == button5) {
			pmtAmountField.setText(pmtAmountField.getText() + "5");
		} else if (event.getSource() == button6) {
			pmtAmountField.setText(pmtAmountField.getText() + "6");
		} else if (event.getSource() == button7) {
			pmtAmountField.setText(pmtAmountField.getText() + "7");
		} else if (event.getSource() == button8) {
			pmtAmountField.setText(pmtAmountField.getText() + "8");
		} else if (event.getSource() == button9) {
			pmtAmountField.setText(pmtAmountField.getText() + "9");
		}
	}

	/**
	 * When called, this method updates the total, tax, and total due text fields
	 */
	public void updateTotalFields() {
		setTotal(0);
		setTax(0);
		setTotalDue(0);

		// Add all the item prices together
		if (tableView.getItems() != null) {
			for (Product product : tableView.getItems()) {
				setTotal(total += product.getAmount());
			}
		}

		setTax(getTotal() * 0.05);
		setTotalDue(getTotal() + getTax());

		// Set all the text fields to their respective values
		subTotalField.setText(String.format("$%.2f", getTotal()));
		taxField.setText(String.format("$%.2f", getTax()));
		totalDueField.setText(String.format("$%.2f", getTotalDue()));
	}

	// Setters
	public void setTotal(double total) {
		this.total = total;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public void setTotalDue(double totalDue) {
		this.totalDue = totalDue;
	}
	public void setChange(double change) {
		this.change = Math.abs(change);
	}
	public void setTotalPaymentAmount(double totalPaymentAmount) { this.totalPaymentAmount = totalPaymentAmount; }

	// Getters
	public double getTotal() {
		return this.total;
	}
	public double getTax() {
		return this.tax;
	}
	public double getTotalDue() {
		return this.totalDue;
	}
	public double getChange() {
		return this.change;
	}
	public double getTotalPaymentAmount() { return this.totalPaymentAmount; }
}
