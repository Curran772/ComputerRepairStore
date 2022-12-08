package Controllers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

import DBStructure.DBMethods;
import DBStructure.Update;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static Controllers.Main.stage;
import Objects.*;
import javafx.util.Callback;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ComputerRepairStoreController implements Initializable {

	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();

	private ObservableList<Product> inventoryList = FXCollections.observableArrayList();

	private BigDecimal taxPercentage = new BigDecimal(0.07);

	private int count = 0;
	private double total = 0;
	private double tax = 0;
	private double totalDue = 0;
	private double change = 0;
	private double totalPaymentAmount = 0;

	private String user = "";

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

	ObservableList<String> pmtType = FXCollections.observableArrayList("Cash", "Check", "Card");

	@FXML
	private Button printReceiptButton;

	@FXML
	private TableView<Product> tableView;

	@FXML
	protected TableColumn<Product, String> itemColumn;

	@FXML
	protected TableColumn<Product, Integer> quantityColumn;

	@FXML
	protected TableColumn<Product, Double> amountColumn;

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

			purchaseListView.setItems(Update.getProducts());

		} catch (SQLException e) {
			System.out.println("DB Connection failed at table population!" + e);
		} catch (Exception ex) {
			System.out.println("DB Connection failed at runSqlScript!" + ex);
		}

		tableView.setItems(inventoryList);
		tableView.refresh();

		// This will allow the user to select multiple rows for deletion
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// These items are for initializing choice box
		pmtMethodField.getItems().addAll(pmtType);
		pmtMethodField.setOnAction(this::choiceBoxField);

		updateTotalFields();

		FilteredList<Product> filteredList = new FilteredList<>(purchaseListView.getItems(), list -> true);
		purchaseListView.setItems(filteredList);
		searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredList.setPredicate(list -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseSearch = newValue.toLowerCase();

				if (list.getItem().toLowerCase().contains(lowerCaseSearch)) {
					return true; // Filter matches item

				}else {
					return false; // does not match
				}
			});
		});
		pmtMethodField.setValue("Cash");

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
	public void changeQuantityColumnEvent(TableColumn.CellEditEvent<Product, Integer> editedCell) {
		Product quantitySelected = tableView.getSelectionModel().getSelectedItem();
		quantitySelected.setQuantity(editedCell.getNewValue());
	}

	/**
	 * This method makes it so when the user clicks an item in the list,
	 * the item is added to the users checkout list
	 */
	@FXML
	void addItemToList(MouseEvent event) {
		// Create the temporary object to be added to the list
		Product prod = new Product(purchaseListView.getSelectionModel().getSelectedItem().getItem(),
				purchaseListView.getSelectionModel().getSelectedItem().getAmount(),
				1);

		if (purchaseListView.getSelectionModel().getSelectedItem().getQuantity() >= 1) {

			// If list is empty check
			if (inventoryList.size() == 0) {
				inventoryList.add(prod);
			} else {
				/*
				 * Here you NEED two separate for loops, if you try to combine them...
				 * You get a duplication bug where the table duplicates the rows being added
				 */
				for (int i = 0; i < inventoryList.size(); i++) {
					if (tableView.getItems().get(i).getItem().equals(prod.getItem())) {
						int qty = tableView.getItems().get(i).getQuantity();
						double amt = inventoryList.get(i).getAmount() + purchaseListView.getSelectionModel()
								.getSelectedItem().getAmount();

						// Using Formatter here to prevent repeating digits bug in Table View
						Formatter fmt = new Formatter();
						fmt.format("%.2f", amt);

						prod.setQuantity(qty + 1);
						prod.setAmount(Double.parseDouble(fmt.toString()));
						inventoryList.set(i, prod);
					}
				}
				for (int i = 0; i < inventoryList.size(); i++) {
					if (!inventoryList.contains(prod)) {
						inventoryList.add(prod);
					}
				}
			}

			// Decreases the total value by 1 each time an item is added to the list.
			purchaseListView.getSelectionModel().getSelectedItem().setQuantity(
					purchaseListView.getSelectionModel().getSelectedItem().getQuantity() - 1);

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No stock left!");
			alert.setHeaderText("No stock left!" +
					"\nPlease select a different item...");
			alert.showAndWait();
		}

		// Set the table equal to the inventory list
		tableView.setItems(inventoryList);
		tableView.refresh();

		// Updates the total amount due
		updateTotalFields();
	}

	/**
	 * this method will remove the selected row(s) from the table
	 */
	@FXML
	private void removeItemButtonPressed() {
		try {
			Product prod = new Product(tableView.getSelectionModel().getSelectedItem().getItem(),
					tableView.getSelectionModel().getSelectedItem().getAmount(),
					tableView.getSelectionModel().getSelectedItem().getQuantity());

			int tableIndex = tableView.getSelectionModel().getSelectedIndex();
			int qty = tableView.getSelectionModel().getSelectedItem().getQuantity() - 1;

			double amt = tableView.getSelectionModel().getSelectedItem().getAmount() -
					(tableView.getSelectionModel().getSelectedItem().getAmount()
							/ tableView.getSelectionModel().getSelectedItem().getQuantity());

			for (int i = 0; i < purchaseListView.getItems().size(); i++) {
				if (purchaseListView.getItems().get(i).getItem().equals(tableView
						.getSelectionModel().getSelectedItem().getItem())) {
					purchaseListView.getItems().get(i).setQuantity(
							purchaseListView.getItems().get(i).getQuantity() + 1);
				}
			}

			// Using Formatter here to prevent repeating digits bug in Table View
			Formatter fmt = new Formatter();
			fmt.format("%.2f", amt);

			if (prod.getQuantity() == 1) {
				inventoryList.remove(tableView.getSelectionModel().getSelectedItem());
			}

			if (prod.getQuantity() > 1) {
				prod.setQuantity(qty);
				prod.setAmount(Double.parseDouble(fmt.toString()));
				inventoryList.set(tableIndex, prod);
			}

			tableView.setItems(inventoryList);

			updateTotalFields();

		} catch (NullPointerException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("No item to delete selected!");
			alert.setHeaderText("Please select item!");
			alert.showAndWait();
		}
	}

	/**
	 * Action event when the clear purchase button is pressed
	 */
	@FXML
	private void clearPurchaseButtonPressed(ActionEvent event) throws SQLException {
		tableView.getItems().clear();
		updateTotalFields();
		inventoryList.clear();
	}



	/**
	 * This method prints a receipt view of purchase totals to the console. Does not
	 * show the products purchased.
	 */
	@FXML
	private void printReceiptButtonPressed(ActionEvent event) {
		Date date = new Date();
		System.out.println();
		ObservableList<Product> purchase = tableView.getItems();

		try {
			File file = new File("invoice.txt");
			FileWriter fw = new FileWriter(file, false);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter pw = new PrintWriter(fw);

			if (pmtChangeField.getText().isEmpty()) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid payment");
				alert.setHeaderText("Please Press Pay Button!");
				alert.showAndWait();
			} else {
				pw.println();
				pw.println("**************************************************************************");
				pw.println("				              CRS 			           				   ");
				pw.println("		             Computer Repair Store				         	     ");
				pw.println("**************************************************************************");
				pw.println();
				pw.println(date);
				pw.println();
				purchase.forEach(pw::println);
				pw.println();
				pw.printf(
						"SubTotal: $%.2f%nTax: $%.2f%nTotal Due: $%.2f%n%nPayment Method: %s%nPayment Amount: $%.2f%nChange: $%.2f%n",
						getTotal(), getTax(), getTotalDue(), pmtMethodField.getValue(), getTotalPaymentAmount(),
						getChange());
				pw.println();
				pw.printf("Thank you for your purchase!");//"You were helped by %s.%n%n Thank you for your purchase!%n%n", employee.toString());
				pw.close();
				pw.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Reciept saved to file");
		tableView.getItems().clear();
		pmtAmountField.clear();
		pmtChangeField.clear();
	}

	/**
	 * This method exits the program when exit button is pressed
	 */
	@FXML
	private void exitButtonPressed(ActionEvent event) {
		Employee employee;

		try (BufferedReader input = Files.newBufferedReader(Paths.get("currentUser.xml"))) {
			employee = JAXB.unmarshal(input, Employee.class);

			setUser(employee.getUsername());

			System.out.println(getUser());

		} catch (IOException e) {
			System.out.println("Failed to open current user :(");
		}

		if (!tableView.getItems().isEmpty()) {
			try(BufferedWriter output =
						Files.newBufferedWriter(Paths.get("src/XmlFiles/table.xml"))) {
				Products products = new Products();
				products.setUser(user);
				for (Product p : tableView.getItems()) {
					products.addToList(p);
				}

				JAXB.marshal(products, output);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		System.out.println(getUser());
		Main.exitButtonPressed(stage);

	}

	public static ObservableList<Product> getXmlAsList(File file) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Products products = (Products) jaxbUnmarshaller.unmarshal(file);
			return FXCollections.observableArrayList(products.getProducts());
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
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

	public void setTotalPaymentAmount(double totalPaymentAmount) {
		this.totalPaymentAmount = totalPaymentAmount;
	}

	public void setUser(String user) { this.user = user; }

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

	public double getTotalPaymentAmount() {
		return this.totalPaymentAmount;
	}

	public String getUser() { return this.user; }
}
