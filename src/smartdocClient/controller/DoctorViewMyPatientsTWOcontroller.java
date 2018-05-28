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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private TableView<PatientPrescription> assignPrescription;
	@FXML
	private TableColumn<PatientPrescription, String> prescription;
	@FXML
	private TableColumn<PatientPrescription, LocalDate> appointment;
	@FXML
	private TableColumn<PatientPrescription, String> recommendation;
	@FXML
	private TableColumn<PatientPrescription, String> problem;
	@FXML
	private TableColumn<PatientPrescription, String> doctorCPR;

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
			Parent register = FXMLLoader.load(getClass().getResource("../view/SUCCESS.fxml"));
			Scene home_page_scene = new Scene(register);
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
			// make and PResciption list

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

		appointment.setCellValueFactory(new PropertyValueFactory<>("appointments"));
		prescription.setCellValueFactory(new PropertyValueFactory<>("prescription"));
		problem.setCellValueFactory(new PropertyValueFactory<>("problem"));
		recommendation.setCellValueFactory(new PropertyValueFactory<>("recommendation"));
		doctorCPR.setCellValueFactory(new PropertyValueFactory<>("doctorCPR"));

		try {
			assignPrescription.setItems(getPrescriptions());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ObservableList<PatientPrescription> getPrescriptions() throws RemoteException {

		ObservableList<PatientPrescription> patientprescriptionTable = FXCollections.observableArrayList();

		LocalDate loc = LocalDate.now();
		patientprescriptionTable.add(new PatientPrescription("#1", loc, "Cancer", "Spend time with family", "250998-2137"));
		patientprescriptionTable.add(new PatientPrescription("#2", loc, "Drunk", "Skip Student Bar for 3 months", "431211-2322"));
		patientprescriptionTable.add(new PatientPrescription("#3", loc, "Stupid", "Don't go to Uni", "265387-2654"));

		return patientprescriptionTable;
	}
}
