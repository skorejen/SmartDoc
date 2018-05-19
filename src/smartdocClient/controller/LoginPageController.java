package smartdocClient.controller;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import smartdocClient.domain.mediator.ClientModel;
import smartdocClient.domain.mediator.ClientModelManager;
import javafx.event.ActionEvent;

public class LoginPageController implements Initializable {

	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Button register;
	@FXML
	private Button signin;


	private ClientController clientController;
	
	
	public LoginPageController() 
	{
		clientController = ClientController.getInstance();
	}
	
	public void signInButtonPressed(ActionEvent event) throws IOException {
		{
			String password1 = pass.getText();
			String username1 = user.getText();

			String cpr = clientController.verifyLogin(username1, password1);
			if (!cpr.equals("0")) {
				
				if(clientController.getAccount(cpr).equals("D"))
				{
					Parent signin = FXMLLoader.load(getClass().getResource("../view/DoctorLoggedIN.fxml"));

					Scene home_page_scene = new Scene(signin);
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					app_stage.setScene(home_page_scene);
					app_stage.show();
				}
				
				if(cpr.equals("111111-2222"))
				{
					Parent signin = FXMLLoader.load(getClass().getResource("../view/AdministratorGUI.fxml"));
					
					Scene home_page_scene = new Scene(signin);
					Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					app_stage.setScene(home_page_scene);
					app_stage.show();
				}
				

			
				
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
		           alert.setTitle("Please try again!");
		           alert.setContentText("There was an error with your username or password combination.Please try again or reset your password");
		           alert.showAndWait();
			}
		}

	}

	public void registerButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/LoginPageRegister.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}
	
	public void forgotPasswordButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/ForgotPassword.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
