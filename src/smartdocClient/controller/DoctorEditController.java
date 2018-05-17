package smartdocClient.controller;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

public class DoctorEditController implements Initializable {

	@FXML
	private Button back;
	@FXML
	private JFXTreeTableView tableview;

	public void backButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/AdministratorGUI.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		TreeTableColumn firstName = new TreeTableColumn("First Name");
		TreeTableColumn lastName = new TreeTableColumn("Last Name");
		TreeTableColumn cpr = new TreeTableColumn("CPR");
		TreeTableColumn age = new TreeTableColumn("Birthday");
		TreeTableColumn speciality = new TreeTableColumn("Speciality");
		TreeTableColumn phoneNo = new TreeTableColumn("Phone Number");
		TreeTableColumn username = new TreeTableColumn("Username");
		TreeTableColumn password = new TreeTableColumn("Password");
		TreeTableColumn email = new TreeTableColumn("E-mail");
		TreeTableColumn gender = new TreeTableColumn("E-mail");
		
		

		tableview.getColumns().addAll(firstName, lastName, cpr, age,gender, speciality, phoneNo, username, password, email);
	}
}
