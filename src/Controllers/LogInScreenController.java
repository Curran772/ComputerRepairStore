package Controllers;

import java.io.IOException;
import java.util.HashMap;

import DBStructure.DBMethods;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
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

	/*
	 * private ObservableList<Employee> employeeList =
	 * FXCollections.observableArrayList(); public void
	 * setEmployeeList(ObservableList<Employee> employees) { this.employeeList =
	 * employees; } public ObservableList<Employee> getEmployeeList() { return
	 * this.employeeList; }
	 */

	Employee e1 = new Employee("111111", "Jane", "Green", "111111", "123");
	Employee e2 = new Employee("222222", "Max", "Brown", "222222", "123");
	Employee e3 = new Employee("333333", "Rob", "Schneider", "333333", "123");
	Employee e4 = new Employee("444444", "Dweight", "Howard", "444444", "123");
	Employee e5 = new Employee("555555", "Amy", "Smith", "555555", "123");
	Employee e6 = new Employee("666666", "Stacy", "Anderson", "666666", "123");

	public void initialize() {

	}

	public void userLogIn(ActionEvent event) throws IOException {
		checkLogin();

	}

	protected void checkLogin() throws IOException {

		if (usernameTextField.getText().equals(e1.getUsername())
				&& passwordTextField.getText().contains(e1.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");

		} else if (usernameTextField.getText().equals(e1.getUsername())
				&& passwordTextField.getText().contains(e1.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
		} else if (usernameTextField.getText().equals(e2.getUsername())
				&& passwordTextField.getText().contains(e2.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");

		} else if (usernameTextField.getText().equals(e3.getUsername())
				&& passwordTextField.getText().contains(e3.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");

		} else if (usernameTextField.getText().equals(e4.getUsername())
				&& passwordTextField.getText().contains(e4.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");

		} else if (usernameTextField.getText().equals(e5.getUsername())
				&& passwordTextField.getText().contains(e5.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");

		} else if (usernameTextField.getText().equals(e6.getUsername())
				&& passwordTextField.getText().contains(e6.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");

		} else if (usernameTextField.getText().equals("") && passwordTextField.getText().contains("")) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");

		} else if (usernameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty()) {
			wrongLogIn.setText("Please enter your username and password.");

		} else {
			wrongLogIn.setText("Wrong username or password!");
		}

	}

	@FXML
	public void handleEnterPressed(KeyEvent event) throws IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			checkLogin();
		}
	}
}
