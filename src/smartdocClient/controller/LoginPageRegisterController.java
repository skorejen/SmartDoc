package smartdocClient.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;



import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPageRegisterController implements Initializable{

	@FXML
	private TextField firstname;
	@FXML
	private TextField lastname;
	@FXML
	private TextField cpr;
	@FXML
	private TextField dob;
	@FXML
	private TextField email;
	@FXML
	private TextField phoneno;
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField pass;
	


	
	@FXML
	private Button register;
	@FXML
	private Button clear;
	@FXML
	private Button back;
	
	
	@FXML
	private JFXComboBox genderchoice;
	
	private ClientController clientController;
	
	
	public void backButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/LoginPage.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}
	
	public void clearButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/LoginPageRegister.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
			
			
		}

	}
	

	
	public void registerButtonPressed(ActionEvent event) throws IOException {
		{
			
			String loginVar = username.getText();
			String passwordVar = pass.getText();
			String firstnameVar=firstname.getText();
			String lastnameVar=lastname.getText();
			String cprVar=cpr.getText();
			
			String emailVar=email.getText();
			String phoneNoVar=phoneno.getText();
			
			int phoneNo = Integer.parseInt(phoneNoVar);
			
			String genderVar= genderchoice.getValue().toString();

			String date = dob.getText();
			String [] dateVariables = new String[2];
			dateVariables = date.split("-");
			String trimYear = dateVariables[0];
			String trimMonth = dateVariables[1];
			String trimDay = dateVariables[2];
			
			System.out.println(trimYear+" "+trimMonth+" "+trimDay);
			
			LocalDate dateOfBirthVar = LocalDate.of(Integer.parseInt(trimYear), Integer.parseInt(trimMonth), Integer.parseInt(trimDay));


			if(genderVar.equals("Male")) {
				genderVar = "M";
			} else if (genderVar.equals("Female"))
			{
				genderVar = "F";
			} else
			{
				genderVar = "M";
			}
			
		clientController  = ClientController.getInstance();
		clientController.createPatient(loginVar, passwordVar, firstnameVar, lastnameVar, cprVar, phoneNo, emailVar, dateOfBirthVar, genderVar);
			
			
			Parent register = FXMLLoader.load(getClass().getResource("../view/SUCCESS.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		genderchoice.getItems().setAll("Male", "Female", "Other");
		
		
	}
}
