package smartdocClient.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
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

import javafx.stage.Stage;
import smartdocServer.domain.model.PatientList;

public class DoctorViewMyPatientsONEcontroller implements Initializable {

	@FXML
	private JFXComboBox<String> inputCPR;

	@FXML
	private Button signOut;

	@FXML
	private Button back;

	@FXML
	private Button clear;

	@FXML
	private Button continueB;
	

	
   

	private ClientController controller;

	public DoctorViewMyPatientsONEcontroller() {
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
			Parent register = FXMLLoader.load(getClass().getResource("../view/DoctorLoggedIN.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	public void continueButtonPressed(ActionEvent event) throws IOException {
		{   
			//
			String CPR = inputCPR.getValue().toString();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/DoctorViewMyPatientsTWO.fxml"));
		
		Parent root = loader.load();
		
		Scene home_page_scene = new Scene(root);
		
		
		DoctorViewMyPatientsTWOcontroller transfer = loader.getController();
		transfer.setcprFromPreviousScene(CPR);
		
		
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		app_stage.setScene(home_page_scene);
		app_stage.show();
		
	
		
		
		
			
		}
	}



	public void clearButtonPressed(ActionEvent event) throws IOException {
		{
			Parent register = FXMLLoader.load(getClass().getResource("../view/DoctorViewMyPatientsONE.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
         
		
		
			String DoctorsCPR = null;
			try {
				DoctorsCPR = controller.getDoctorData().getCpr();
			} catch (RemoteException e) {
				System.out.println("BLA");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			PatientList patients = null;
			try {
				patients = controller.getAssignedPatientList(DoctorsCPR);
			} catch (RemoteException e) {
				System.out.println("BLAH");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		
		for (int i=0;i<patients.getNumberOfPatients();i++)
		    inputCPR.getItems().add((patients.getPatient(i).getCpr()));
		}
		
		
		
	
	}


