package smartdocClient.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import smartdocServer.domain.model.Patient;

public class GeneralDoctorManagePatientGUI2Controller implements Initializable {

	@FXML
	private Label fNameLabel;

	@FXML
	private Label lNameLabel;

	@FXML
	private Label dobLabel;

	@FXML
	private Label emailLabel;

	@FXML
	private Label genderLabel;

	@FXML
	private Label typeLabel;

	@FXML
	private Label cprLabel;

	@FXML
	private Label phoneNumberLabel;

	private String cprFromPreviousScene;

	@FXML
	private Button signOut;
	@FXML
	private Button back;
	@FXML
	private Button apply;

	private ClientController controller;

	public GeneralDoctorManagePatientGUI2Controller() {

		controller = ClientController.getInstance();

		;

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

	public void backButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/PatientLoggedIN.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	public void applyButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/PatientLoggedIN.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		

	}

	public void setcprFromPreviousScene(String cpr) {
		System.out.println(cprFromPreviousScene + " before instantiate");
		
	this.cprFromPreviousScene=cpr;
	
	System.out.println(cprFromPreviousScene+" after instantiate");
	 try {
		Patient patient = controller.getPatient(cprFromPreviousScene);
			
		
		
				
		fNameLabel.setText(patient.getFname()); 
		lNameLabel.setText(patient.getLname());
		dobLabel.setText(patient.getDob().toString());
		emailLabel.setText(patient.getEmail());
		genderLabel.setText(patient.getGender());
		typeLabel.setText(patient.getType());
		cprLabel.setText(patient.getCpr());
		phoneNumberLabel.setText(patient.getPhone()+""); 
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

	
}
