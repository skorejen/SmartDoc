package smartdocClient.controller;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

import smartdocClient.domain.mediator.ClientModel;
import smartdocClient.domain.mediator.ClientModelManager;
import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientList;
import smartdocServer.domain.model.PatientPrescription;

public class ClientController {

	private static ClientModel clientModel;

	private static ClientController instance;

	private ClientController() {

		this.clientModel = new ClientModelManager();
	}

	public static ClientController getInstance() {
		if (instance == null) {
			instance = new ClientController();
		}

		return instance;
	}

	public String verifyLogin(String login, String password) throws RemoteException {
		return clientModel.verifyLogin(login, password);
	}

	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException {
		return clientModel.createDoctor(login, password, fname, lname, cpr, phone, email, dob, speciality, type,
				gender);

	}

	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String gender) throws RemoteException {
		return clientModel.createPatient(login, password, fname, lname, cpr, phone, email, dob, gender);
	}

	public String getAccountAndType(String cpr) throws RemoteException {
		return clientModel.getAccountAndType(cpr);
	}

	public Doctor getDoctorData() throws RemoteException {
		return clientModel.getDoctorModel();
	}
	
	public Patient getPatientData() throws RemoteException {
		return clientModel.getPatientModel();
	}
	
	public Patient getPatient(String cpr) throws RemoteException {
		return clientModel.getPatient(cpr); 
	}
	
	public Doctor getDoctor(String cpr) throws RemoteException {
		return clientModel.getDoctor(cpr);
	}
	
	public PatientList getPatientList() throws RemoteException {
		return clientModel.getPatientList(); // return all patients from database
	}
	
	public DoctorList getDoctorList() throws RemoteException {
		return clientModel.getDoctorList(); // return all patients from database
	}
	
	public PatientList getAssignedPatientList(String cpr) throws RemoteException {
		return clientModel.getAssignedPatientList(cpr);
	}
	
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr) throws RemoteException {
		return clientModel.assignPatientToDoctor(patientCpr, doctorCpr);
	}
	
	public PatientPrescription getPatientPrescription(String cpr) throws RemoteException {
		return clientModel.getPatientPrescription(cpr);
	}
	
	public boolean updatePrescription(String cpr,String prescription, LocalDate appointment, String problem,
			String recommendations) throws RemoteException{
		return clientModel.updatePrescription(cpr, prescription, appointment, problem, recommendations);
	}
}
