package smartdocClient.controller;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.DoctorList;
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
	private JFXTextField cprDoctorInput;

	@FXML
	private Button signOut;
	@FXML
	private Button back;
	@FXML
	private Button apply;
	@FXML
	private Button patientData;

	@FXML
	private TableView<Doctor> assignPatient;
	@FXML
	private TableColumn<Doctor, String> fName;
	@FXML
	private TableColumn<Doctor, String> lName;
	@FXML
	private TableColumn<Doctor, String> speciality;
	@FXML
	private TableColumn<Doctor, String> gender;
	@FXML
	private TableColumn<Doctor, String> eMail;
	@FXML
	private TableColumn<Doctor, String> type;
	@FXML
	private TableColumn<Doctor, LocalDate> dob;
	@FXML
	private TableColumn<Doctor, Integer> phone;
	@FXML
	private TableColumn<Doctor, String> cpr;

	private ClientController controller;

	public GeneralDoctorManagePatientGUI2Controller() {

		controller = ClientController.getInstance();

		;

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
			Parent register = FXMLLoader.load(getClass().getResource("GeneralDoctorManagePatientGUI.fxml"));
			Scene home_page_scene = new Scene(register);
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app_stage.setScene(home_page_scene);
			app_stage.show();

		}
	}

	public void applyButtonPressed(ActionEvent event) throws IOException {
		{
			
				boolean check = controller.assignPatientToDoctor(cprLabel.getText(), cprDoctorInput.getText());
			if(check) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("SUCCESS");
				alert.setContentText(
						"The operation was completed without any errors!");
				alert.showAndWait();
			} else {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failure");
				alert.setContentText(
						"The isn't valid or doesn't exist in database");
				alert.showAndWait();
			}
			
		}
	}
	
	public void patientDataButtonPressed(ActionEvent event) throws IOException {
		{
			//
			String CPR = cprFromPreviousScene;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GeneralDoctorManagePatientGUI(2)Switch.fxml"));
		
		Parent root = loader.load();
		
		Scene home_page_scene = new Scene(root);
		
		
		GeneralDoctorManagePatientGUI2Controller2switchController transfer = loader.getController();
		transfer.setcprFromPreviousScene(CPR);
		
		
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		app_stage.setScene(home_page_scene);
		app_stage.show();
		
	
		
		
		

		}
	}

	public void setcprFromPreviousScene(String cpr) {
		System.out.println(cprFromPreviousScene + " before instantiate");

		this.cprFromPreviousScene = cpr;

		System.out.println(cprFromPreviousScene + " after instantiate");
		try {
			Patient patient = controller.getPatient(cprFromPreviousScene);

			fNameLabel.setText(patient.getFname());
			lNameLabel.setText(patient.getLname());
			dobLabel.setText(patient.getDob().toString());
			emailLabel.setText(patient.getEmail());
			genderLabel.setText(patient.getGender());
			typeLabel.setText(patient.getType());
			cprLabel.setText(patient.getCpr());
			phoneNumberLabel.setText(patient.getPhone() + "");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cpr.setCellValueFactory(new PropertyValueFactory<>("cpr"));
		fName.setCellValueFactory(new PropertyValueFactory<>("fname"));
		lName.setCellValueFactory(new PropertyValueFactory<>("lname"));
		dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		eMail.setCellValueFactory(new PropertyValueFactory<>("email"));
		type.setCellValueFactory(new PropertyValueFactory<>("type"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		speciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
		
		
		
		
	
		

		
		try {
			assignPatient.setItems(getDoctors());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<Doctor> getDoctors() throws RemoteException {
		ObservableList<Doctor> doctor = FXCollections.observableArrayList();
		 
		
		
		
		 DoctorList doctors = controller.getDoctorList();
		for (int i = 0; i < doctors.getNumberOfDoctors(); i++) {
			System.out.println(doctors.getDoctor(i).toString());
			doctor.add( doctors.getDoctor(i));

		} 
		return doctor;
	}
}
