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
import smartdocServer.domain.model.PatientPrescription;

public class MyInfoPatientController implements Initializable{

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
	
	@FXML
	private Label prescriptionLabel;
	
	@FXML
	private Label appointmentLabel;
	
	@FXML
	private Label recommendationLabel;
	
	@FXML
	private Label problemLabel;
	
	
	
	
	@FXML
	private Button signOut;
	@FXML
	private Button back;
	
	private ClientController controller;
	
	public MyInfoPatientController() {
		controller = ClientController.getInstance();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			fNameLabel.setText(controller.getPatientData().getFname());
			lNameLabel.setText(controller.getPatientData().getLname());
			dobLabel.setText(controller.getPatientData().getDob().toString());
			emailLabel.setText(controller.getPatientData().getEmail());
			genderLabel.setText(controller.getPatientData().getGender());
			typeLabel.setText(controller.getPatientData().getType());
			cprLabel.setText(controller.getPatientData().getCpr());
			
			phoneNumberLabel.setText(controller.getPatientData().getPhone()+ "");
			
			PatientPrescription prescription = controller.getPatientPrescription(controller.getPatientData().getCpr());
			
			problemLabel.setText(prescription.getProblem());
			prescriptionLabel.setText(prescription.getPrescription());
			appointmentLabel.setText(prescription.getAppointments().toString());
			recommendationLabel.setText(prescription.getRecommendation());
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
