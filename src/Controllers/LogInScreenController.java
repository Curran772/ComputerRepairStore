/**
 * Class Name: LogInScreenContoller
 * Class Description: The purpose of this class is to allow a user to login to the 
 * computer store main screen.  User must have a username and password that matches
 * one from the Employee Files.
 * 
 * @author Amber Robertson and Curran Buss
 */
package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import Objects.Employee;
import Objects.Employees;
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

	public void initialize() {}

	public void userLogIn(ActionEvent event) throws IOException {
		checkLogin();
	}

	protected void checkLogin() {
		// check username and password for login and create xml file to hold the information of the current user
		try (BufferedReader input = Files.newBufferedReader(Paths.get("employees.xml"))) {
			Employees employees;
			employees = JAXB.unmarshal(input, Employees.class);
			try (BufferedWriter output = Files.newBufferedWriter(Paths.get("currentUser.xml"))) {
				for (Employee e : employees.getEmployees()) {
					if (usernameTextField.getText().equals(e.getUsername())
					&& passwordTextField.getText().contains(e.getPassword())) {
						wrongLogIn.setText("Success!");

						Main.setRoot("ComputerRepairStore", true);
						Main.changeStageTitle("Computer Repair Store");
						JAXB.marshal(e.toString(), output);
						break;
					} else if (usernameTextField.getText().isEmpty()
							&& passwordTextField.getText().isEmpty()){
						wrongLogIn.setText("Success!");

						Main.setRoot("ComputerRepairStore", true);
						Main.changeStageTitle("Computer Repair Store");
						JAXB.marshal("Admin", output);
						break;
					} else {
						wrongLogIn.setText("Wrong username or password!");
					}
				}

			} catch (IOException ioException) {
				System.err.println("Error opening file. Terminating.");
			}

		} catch (IOException e) {

		}
	}

	@FXML
	public void handleEnterPressed(KeyEvent event) throws IOException {
		if (event.getCode().equals(KeyCode.ENTER)) {
			checkLogin();
		}
	}
}
