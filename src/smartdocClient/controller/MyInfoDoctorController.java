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

public class MyInfoDoctorController implements Initializable{

	@FXML
	private Label cprLabel;

	@FXML
	private Label fNameLabel;

	@FXML
	private Label lNameLabel;

	@FXML
	private Label phoneLabel;

	@FXML
	private Label dobLabel;

	@FXML
	private Label emailLabel;

	@FXML
	private Label typeLabel;
	
	@FXML
	private Label genderLabel;
	
	@FXML
	private Label specialityLabel;


	@FXML
	private Button signOut;
	@FXML
	private Button back;
	
	private ClientController controller;
	
	public MyInfoDoctorController() {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			cprLabel.setText(controller.getDoctorData().getCpr());

			fNameLabel.setText(controller.getDoctorData().getFname());
			lNameLabel.setText(controller.getDoctorData().getLname());
			phoneLabel.setText(controller.getDoctorData().getPhone()+"");
			dobLabel.setText(controller.getDoctorData().getDob().toString());
			emailLabel.setText(controller.getDoctorData().getEmail());
			typeLabel.setText(controller.getDoctorData().getType());
			genderLabel.setText(controller.getDoctorData().getGender());
			specialityLabel.setText(controller.getDoctorData().getSpeciality());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
