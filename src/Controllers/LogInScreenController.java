package Controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogInScreenController {

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button logInBtn;

	@FXML
	private Label wrongLogIn;
	
	private ObservableList<Employee> employees = FXCollections.observableArrayList();
	HashMap<String, String> loginInfo = new HashMap<String, String>();
	
	Employee e1 = new Employee("111111", "Jane", "Green", "123");
	Employee e2 = new Employee("222222", "Max", "Brown", "123"); 
	Employee e3 = new Employee("333333", "Rob", "Schneider", "123");
	Employee e4 = new Employee("444444", "Dweight", "Howard", "123");
	Employee e5 = new Employee("555555", "Amy", "Smith", "123");
	Employee e6 = new Employee("666666", "Stacy", "Anderson", "123");
	
	protected HashMap getLoginInfo() {
		loginInfo.put(e1.getEmployeeID(),e1.getEmployeePassword());
		loginInfo.put(e2.getEmployeeID(),e2.getEmployeePassword());
		loginInfo.put(e3.getEmployeeID(),e3.getEmployeePassword());
		loginInfo.put(e4.getEmployeeID(),e4.getEmployeePassword());
		loginInfo.put(e5.getEmployeeID(),e5.getEmployeePassword());
		loginInfo.put(e6.getEmployeeID(),e6.getEmployeePassword());
		
		return loginInfo;
	}
	
	private void userIDAndPassword() {		
	
		}
	
	public void userLogIn(ActionEvent event) throws IOException {
		checkLogin();
		
	}

	private void checkLogin() throws IOException {
		getLoginInfo();
		
		/*
		 * if (){//usernameTextField.getText().toString().equals("") //&&
		 * passwordTextField.getText().toString().equals("")) {
		 * wrongLogIn.setText("Success!");
		 * 
		 * Main.setRoot("ComputerRepairStore");
		 * Main.changeStageTitle("Computer Repair Store");
		 * 
		 * 
		 * } else if (usernameTextField.getText().isEmpty() &&
		 * passwordTextField.getText().isEmpty()) {
		 * wrongLogIn.setText("Please enter your username and password.");
		 * 
		 * } else { wrongLogIn.setText("Wrong username or password!"); }
		 */
		
		

	}
	@FXML
	public void handleEnterPressed(KeyEvent event) throws IOException {
		if(event.getCode().equals(KeyCode.ENTER)){
		checkLogin();
	}
	}
}
