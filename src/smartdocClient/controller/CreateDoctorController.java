package smartdocClient.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateDoctorController implements Initializable{

	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField cPR;
	@FXML
	private TextField age;
	@FXML
	private TextField speciality;
	@FXML
	private TextField phoneNo;
	
	@FXML
	private CheckBox male;
	@FXML
	private CheckBox female;
	
	@FXML
	private Button register;
	@FXML
	private Button clear;
	
	@FXML
	private Button back;
	
	private ClientController clientController;
	
	public void backButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/AdministratorGUI.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}
	
	public void clearButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/CreateDoctor.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}
	
	public void submitButtonPressed(ActionEvent event) throws IOException {
		{
			String firstnamE=firstName.getText();
			String lastnamE=lastName.getText();
			String cpR=cPR.getText();
			String agE=age.getText();
			String specialitY=speciality.getText();
			String phonenO=phoneNo.getText();
			
			int cpr = Integer.parseInt(cpR);
			int phoneNo = Integer.parseInt(phonenO);
			int age = Integer.parseInt(agE);
			
		clientController  = ClientController.getInstance();
		clientController.createDoctor(firstnamE, lastnamE, cpr, phoneNo, age, specialitY);
			
			
			Parent register = FXMLLoader.load(getClass().getResource("../view/SUCCESS.fxml"));
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
