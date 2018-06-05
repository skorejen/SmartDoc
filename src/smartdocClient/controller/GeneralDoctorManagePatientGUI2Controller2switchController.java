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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.model.Patient;

public class GeneralDoctorManagePatientGUI2Controller2switchController implements Initializable {

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
	private TextField ilnessTextField;
	@FXML
	private TextField alergiesTextField;
	@FXML
	private TextField heightTextField;
	@FXML
	private TextField weightTextField;
	@FXML
	private TextField smokerTextField;
	@FXML
	private TextField vaccinesTextField;
	@FXML
	private TextField familyIlnessTextField;
	@FXML
	private TextField insuranceTextField;
	@FXML
	private TextField pregnancyTextField;
	



	private ClientController controller;

	public GeneralDoctorManagePatientGUI2Controller2switchController() {

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
			String CPR = cprFromPreviousScene;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("GeneralDoctorManagePatientGUI(2).fxml"));
			
			Parent root = loader.load();
			
			Scene home_page_scene = new Scene(root);
			
			
			GeneralDoctorManagePatientGUI2Controller transfer = loader.getController();
			transfer.setcprFromPreviousScene(CPR);
			
			
			Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			app_stage.setScene(home_page_scene);
			app_stage.show();
			
		
		}
	}

	public void applyButtonPressed(ActionEvent event) throws IOException {
		{
			System.out.println("BLAH");
			controller.updatePatientHistory(cprFromPreviousScene, ilnessTextField.getText(),alergiesTextField.getText()
					,heightTextField.getText(),weightTextField.getText(),smokerTextField.getText(),
					vaccinesTextField.getText(),familyIlnessTextField.getText(),insuranceTextField.getText(),pregnancyTextField.getText());
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

	}

	
}
