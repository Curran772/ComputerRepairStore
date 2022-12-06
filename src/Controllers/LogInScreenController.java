package Controllers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LogInScreenController {

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button logInBtn;

	@FXML
	private Label wrongLogIn;

	Employee e1 = new Employee("111111", "Jane", "Green", "111111", "123");
	Employee e2 = new Employee("222222", "Max", "Brown", "222222", "123");
	Employee e3 = new Employee("333333", "Rob", "Schneider", "333333", "123");
	Employee e4 = new Employee("444444", "Dweight", "Howard", "444444", "123");
	Employee e5 = new Employee("555555", "Amy", "Smith", "555555", "123");
	Employee e6 = new Employee("666666", "Stacy", "Anderson", "666666", "123");

	public void initialize() {}

	public void userLogIn(ActionEvent event) throws IOException {
		checkLogin();
	}

	protected void checkLogin() throws IOException {
		// check username and password for login and create xml file to hold the information of the current user
		try (BufferedWriter output = Files.newBufferedWriter(Paths.get("currentUser.xml"))) {
			
		if (usernameTextField.getText().equals(e1.getUsername())
				&& passwordTextField.getText().contains(e1.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
			JAXB.marshal(e1.toString(), output);
		
		} else if (usernameTextField.getText().equals(e2.getUsername())
				&& passwordTextField.getText().contains(e2.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
			JAXB.marshal(e2.toString(), output);

		} else if (usernameTextField.getText().equals(e3.getUsername())
				&& passwordTextField.getText().contains(e3.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
			JAXB.marshal(e3.toString(), output);

		} else if (usernameTextField.getText().equals(e4.getUsername())
				&& passwordTextField.getText().contains(e4.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
			JAXB.marshal(e4.toString(), output);

		} else if (usernameTextField.getText().equals(e5.getUsername())
				&& passwordTextField.getText().contains(e5.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
			JAXB.marshal(e5.toString(), output);

		} else if (usernameTextField.getText().equals(e6.getUsername())
				&& passwordTextField.getText().contains(e6.getPassword())) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
			JAXB.marshal(e6.toString(), output);

		} else if (usernameTextField.getText().equals("") && passwordTextField.getText().contains("")) {
			wrongLogIn.setText("Success!");

			Main.setRoot("ComputerRepairStore");
			Main.changeStageTitle("Computer Repair Store");
			JAXB.marshal("Admin", output);

		} else if (usernameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty()) {
			wrongLogIn.setText("Please enter your username and password.");

		} else {
			wrongLogIn.setText("Wrong username or password!");
		}
		
		} catch (IOException ioException) {
			System.err.println("Error opening file. Terminating.");
		}
		
	}

	@FXML
	public void handleEnterPressed(KeyEvent event) throws IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			checkLogin();
		}
	}
}
