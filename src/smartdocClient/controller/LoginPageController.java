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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import smartdocClient.domain.mediator.ClientModel;
import smartdocClient.domain.mediator.ClientModelManager;
import smartdocClient.view.ClientView;
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


	private ClientModel clientModel;
	
	public LoginPageController() {
		clientModel = new ClientModelManager();
	}
	
	public void signInButtonPressed(ActionEvent event) throws IOException {
		{
			String password1 = pass.getText();
			String username1 = user.getText();

			if (clientModel.verifyLogin(username1, password1)) {
				Parent signin = FXMLLoader.load(getClass().getResource("../view/SUCCESS.fxml"));
				Scene home_page_scene = new Scene(signin);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(home_page_scene);
				app_stage.show();
				
			} else {
				Parent signin = FXMLLoader.load(getClass().getResource("../view/FAILED.fxml"));
				Scene home_page_scene = new Scene(signin);
				Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				app_stage.setScene(home_page_scene);
				app_stage.show();
			}
		}

	}

	public void registerButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
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
