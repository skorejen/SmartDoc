package smartdocClient.controller;

import java.awt.Button;
import java.awt.Label;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PatientLoggedInController {

	@FXML
	private Button goToMyPacients;
	@FXML
	private Button myInfo;
	@FXML
	private Button signOut;
	@FXML
	private Label doctorName;
	
	public void goToMyStatusButtonPressed(ActionEvent event) throws IOException {
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("FAILED");
			alert.setContentText(
					"There is no feature implemented yet!");
			alert.showAndWait();
		}

	}
	
	public void myInfoButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/MyInfoPatient.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}
	
	public void signOutButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/LoginPage.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}
	
	
}
