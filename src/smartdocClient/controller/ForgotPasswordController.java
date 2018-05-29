package smartdocClient.controller;

import java.awt.Button;
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

public class ForgotPasswordController {

	@FXML
	private Button back;
	@FXML
	private Button submit;

	public void backButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/LoginPage.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}

	public void submitButtonPressed(ActionEvent event) throws IOException {
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("FAILED");
			alert.setContentText(
					"There is no feature implemented yet!");
			alert.showAndWait();
		}

	}

}
