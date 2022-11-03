package Controllers;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
   
       
    public void userLogIn(ActionEvent event) throws IOException{
    	checkLogin();
    }
    
    private void checkLogin() throws IOException{
    	Stage LogInView = new Stage();
		LogInView.initModality(Modality.APPLICATION_MODAL);
		LogInView.setTitle("System Log In");
		Scene login = new Scene(Main.loadFXML("ComputerRepairStore"));

		LogInView.setScene(login);
		LogInView.showAndWait();
    	    	
    	if(usernameTextField.getText().toString().equals("admin") && passwordTextField.getText().toString().equals("123")) {
    		wrongLogIn.setText("Success!");
    	
    		
    	}else if (usernameTextField.getText().isEmpty() && passwordTextField.getText().isEmpty()) {
    		wrongLogIn.setText("Please enter your username and password.");
    	
    	}else {
    		wrongLogIn.setText("Wrong username or password!");
    	}    	
    	
    	
    }

}
