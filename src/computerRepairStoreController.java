import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class computerRepairStoreController {
	
		private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
		private static final NumberFormat percent = NumberFormat.getPercentInstance();
		
		private BigDecimal taxPercentage = new BigDecimal(0.07);
		
		@FXML
	    private TableColumn<?, ?> amountColumn;

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
	    private TableColumn<?, ?> itemColumn;

	    @FXML
	    private Button payButton;

	    @FXML
	    private TextField pmtAmountField;

	    @FXML
	    private TextField pmtChangeField;

	    @FXML
	    private ChoiceBox<String> pmtMethodField;
	    
	    private String[] choices = {"Cash", "Check", "Card"};

	    @FXML
	    private Button printRecieptButton;

	    @FXML
	    private TableColumn<String, String> quantityColumn;

	    @FXML
	    private Button removeItemButton;

	    @FXML
	    private Button removeItemButton1;

	    @FXML
	    private Button removeItemButton11;

	    @FXML
	    private TextField subTotalField;

	    @FXML
	    private TextField taxField;

	    @FXML
	    private TextField totalDueField;
	    
	    //changeDueBox = pmtAmountField - totalDueField
	    //totalDueBox = subTotalBox + taxTotalBox
	    //taxTotalBox = BigDecimal tax = subTotalBox.multiply(taxPercentage); 
	    //subTotalBox = amountColumn
	    
	    
	    @FXML
		private void removeItemButtonPressed(ActionEvent event) {
			
		}
		
		@FXML
		private void payButtonPressed(ActionEvent event) {
			
		}
		
		@FXML
		private void printReceiptButtonPressed(ActionEvent event) {
			
		}
		
		@FXML
		private void clearPurchaseButtonPressed(ActionEvent event) {
			
		}
		
		@FXML
		private void exitButtonPressed(ActionEvent event) {
			
		}
	  
	    private void totalAmountCalculation(ActionEvent event) {

			try {
				BigDecimal amount = new BigDecimal(subTotalField.getText());
				BigDecimal tax = amount.multiply(taxPercentage);
				BigDecimal total = amount.add(tax);

				taxField.setText(currency.format(tax));
				totalDueField.setText(currency.format(total));
			} 
			catch (NumberFormatException ex) {
				subTotalField.setText("Enter Amount");
				subTotalField.selectAll();
				subTotalField.requestFocus();
			}
		}

	public void subTotalBox(ActionEvent actionEvent) {
	}

	public void taxTotalBox(ActionEvent actionEvent) {
	}

	public void totalDueBox(ActionEvent actionEvent) {
	}

	public void changeDueBox(ActionEvent actionEvent) {
	}

	    
	    

		/*public void initialize(	URL arg0, ResourceBundle arg1) {
	    	currency.setRoundingMode(RoundingMode.HALF_UP);
	    	pmtMethodField.getItems().addAll
	    	//pmtMethodField.setItems(FXCollections.observableArrayList("Cash", "Check", "Card"));
	    	
	    	tipSliderButton.valueProperty().addListener(
	    		new ChangeListener<Number>() {
	    			
	    			@Override
	    			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	    				taxPercentage = BigDecimal.valueOf(newValue.intValue()/ 100.0);
	    				
	    				taxField.setText(percent.format(taxPercentage));
	    		}
	    }
	    	
	    );
	    }*/

	}

	



