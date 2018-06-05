package smartdocClient.controller;

import java.io.IOException;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminDeleteDoctorPatientController {

	@FXML
	private JFXTextField inputCPR;

	@FXML
	private Button signOut;

	@FXML
	private Button back;

	@FXML
	private Button clear;

	@FXML
	private Button continueB;

	private ClientController controller;

	public AdminDeleteDoctorPatientController() {
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
			Parent register = FXMLLoader.load(getClass().getResource("AdministratorGUI.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	public void continueButtonPressed(ActionEvent event) throws IOException {
		{
			//
			String CPR = inputCPR.getText();
           //controller.AdminDeleteDoctorPatientController(CPR);
			String type = controller.getAccountAndType(CPR);
       controller.AdminDeleteDoctorPatientController(type,CPR);
       Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("SUCCESS");
		alert.setContentText(
				"The operation was completed without any errors!");
		alert.showAndWait();
           
		}
	}

	public void clearButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("AdminDeleteDoctorPatient.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}
}
