package smartdocClient.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientPrescription;

public class DoctorViewMyPatientsTWOcontroller implements Initializable {

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
	@FXML
	private Button moreDetails;

	
	//INSIDE RIGHT VIEW
	@FXML
	private TextArea prescriptionArea;
	@FXML
	private TextArea recommendationArea;
	@FXML
	private TextArea problemArea;
	@FXML
	private TextField appointmentField;
	
	

	private ClientController controller;

	public DoctorViewMyPatientsTWOcontroller() {

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
			Parent register = FXMLLoader.load(getClass().getResource("../view/DoctorViewMyPatientsONE.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	public void applyButtonPressed(ActionEvent event) throws IOException {
		{
			System.out.println(cprFromPreviousScene);
			
			
			String date = appointmentField.getText();
			String[] dateVariables = new String[2];
			dateVariables = date.split("-");
			String trimYear = dateVariables[0];
			String trimMonth = dateVariables[1];
			String trimDay = dateVariables[2];

			System.out.println(trimYear + " " + trimMonth + " " + trimDay);

			LocalDate appointment = LocalDate.of(Integer.parseInt(trimYear), Integer.parseInt(trimMonth),
					Integer.parseInt(trimDay));
			
			controller.updatePrescription(cprFromPreviousScene, prescriptionArea.getText(),
					appointment, problemArea.getText(), recommendationArea.getText());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("SUCCESS");
			alert.setContentText(
					"The operation was completed without any errors!");
			alert.showAndWait();

		}
	}

	public void setcprFromPreviousScene(String cpr) {
		System.out.println(cprFromPreviousScene + " before instantiate");

		this.cprFromPreviousScene = cpr;

		System.out.println(cprFromPreviousScene + " after instantiate");
		try {
			Patient patient = controller.getPatient(cprFromPreviousScene);
			// make and PResciption list

			fNameLabel.setText(patient.getFname());
			lNameLabel.setText(patient.getLname());
			dobLabel.setText(patient.getDob().toString());
			emailLabel.setText(patient.getEmail());
			genderLabel.setText(patient.getGender());
			typeLabel.setText(patient.getType());
			cprLabel.setText(patient.getCpr());
			phoneNumberLabel.setText(patient.getPhone() + "");
			
			
			
			
//			
//			prescriptionInputField.setText();
//			appointmentInputField.setText();
//			problemInputField.setText();
//			recommendationInputField.setText();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cprFromPreviousScene +"SOMETHING");
		PatientPrescription prescriptionBundle = null; 
		try {
			 prescriptionBundle = controller.getPatientPrescription(cprFromPreviousScene);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// if the data in the prescription bundle is equal to 0 that means that this is a new account
		// and its the first visit of the patient to doctor
		
		if(!prescriptionBundle.getPrescription().equals("0")) {
			prescriptionArea.setText(prescriptionBundle.getPrescription());
		} else {
			prescriptionArea.setText("");
		}
		
		if(!prescriptionBundle.getProblem().equals("0")) {
			problemArea.setText(prescriptionBundle.getProblem());
		} else {
			problemArea.setText("");
		}
			// date is initialized to now() when registered a new account
			appointmentField.setText(prescriptionBundle.getAppointments().toString());
	
			
		if(!prescriptionBundle.getRecommendation().equals("0")) {
			recommendationArea.setText(prescriptionBundle.getRecommendation());
		} else {
			recommendationArea.setText("");
		}
			
			
			
		
		
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		
		
		
		

	}
	
	public void moreDetailsButtonPressed(ActionEvent event) throws IOException {
		{
			//
			String CPR = cprFromPreviousScene;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/DoctorViewMyPatientsTWOswitchVIEW.fxml"));
		
		Parent root = loader.load();
		
		Scene home_page_scene = new Scene(root);
		
		
		DoctorViewMyPatientsTWOswitchVIEWcontroller transfer = loader.getController();
		transfer.setcprFromPreviousScene(CPR);
		
		
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		app_stage.setScene(home_page_scene);
		app_stage.show();
				}
	}

	
}
