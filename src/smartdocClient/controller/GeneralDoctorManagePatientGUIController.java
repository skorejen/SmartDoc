package smartdocClient.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GeneralDoctorManagePatientGUIController implements Initializable {

	@FXML
	private TextField inputCPR;

	@FXML
	private Button signOut;

	@FXML
	private Button back;

	@FXML
	private Button clear;

	@FXML
	private Button continueB;
	
    private String cprTemporary;

	private ClientController controller;

	public GeneralDoctorManagePatientGUIController() {
		controller = ClientController.getInstance();
	}

	public void signOutButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}
	}

	public void backButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("GeneralDoctorGUI.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	public void continueButtonPressed(ActionEvent event) throws IOException {
		{   String CPR = inputCPR.getText();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GeneralDoctorManagePatientGUI(2).fxml"));
		
		Parent root = loader.load();
		
		Scene home_page_scene = new Scene(root);
		
		
		GeneralDoctorManagePatientGUI2Controller transfer = loader.getController();
		transfer.setcprFromPreviousScene(CPR);
		
		
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		app_stage.setScene(home_page_scene);
		app_stage.show();
		
	
		
		
		
			
		}
	}



	public void clearButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("GeneralDoctorManagePatientGUI.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
