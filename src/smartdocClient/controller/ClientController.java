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

	/**
	 * The method gets an instance and only one if there's no one created yet. It's
	 * part of the Singleton Design Pattern.
	 * 
	 * @return instance of the ClientController
	 */
	public static ClientController getInstance() {
		if (instance == null) {
			instance = new ClientController();
		}

		return instance;
	}
/**
 * 
 * @param login
 * @param password
 * @returns the CPR of the user logged in.
 * @throws RemoteException
 */
	public String verifyLogin(String login, String password) throws RemoteException {
		return clientModel.verifyLogin(login, password);
	}
/**
 * 
 * @param login
 * @param password
 * @param fname
 * @param lname
 * @param cpr
 * @param phone
 * @param email
 * @param dob
 * @param speciality
 * @param type
 * @param gender
 * @return it returns true or false depending on the status of the operation if it was a success or not.
 * @throws RemoteException
 */
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException {
		return clientModel.createDoctor(login, password, fname, lname, cpr, phone, email, dob, speciality, type,
				gender);

	}
/**
 * 
 * @param login
 * @param password
 * @param fname
 * @param lname
 * @param cpr
 * @param phone
 * @param email
 * @param dob
 * @param gender
 * @return it returns true or false depending on the status of the operation if it was a success or not.
 * @throws RemoteException
 */
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String gender) throws RemoteException {
		return clientModel.createPatient(login, password, fname, lname, cpr, phone, email, dob, gender);
	}
/**
 * 
 * @param cpr
 * @returns the type of the Account "D","G" or "P" depending on which user is logged in.
 * @throws RemoteException
 */
	public String getAccountAndType(String cpr) throws RemoteException {
		return clientModel.getAccountAndType(cpr);
	}
/**
 * 
 * @return the method calls the Model of the Doctor once it's logged in
 * @throws RemoteException
 */
	public Doctor getDoctorData() throws RemoteException {
		return clientModel.getDoctorModel();
	}
	/**
	 * 
	 * @return the method calls the Model of the Patient once it's logged in.
	 * @throws RemoteException
	 */
	public Patient getPatientData() throws RemoteException {
		return clientModel.getPatientModel();
	}
/**
 * 
 * @param cpr
 * @return the method return the patient with the CPR entered as a parameter
 * @throws RemoteException
 */
	public Patient getPatient(String cpr) throws RemoteException {
		return clientModel.getPatient(cpr);
	}
	/**
	 * 
	 * @param cpr
	 * @return the method return the doctor with the CPR entered as a parameter
	 * @throws RemoteException
	 */
	public Doctor getDoctor(String cpr) throws RemoteException {
		return clientModel.getDoctor(cpr);
	}
/**
 * 
 * @return It returns an ArrayList full of Arrays that contains Patient model
 * @throws RemoteException
 */
	public PatientList getPatientList() throws RemoteException {
		return clientModel.getPatientList(); // return all patients from database
	}
/**
 * 
 * @returnIt returns an ArrayList full of Arrays that contains Doctor model
 * @throws RemoteException
 */
	public DoctorList getDoctorList() throws RemoteException {
		return clientModel.getDoctorList(); // return all patients from database
	}
/**
 * 
 * @param cpr
 * @return The method returns searches for the Doctor's CPR in the database and it
	 * return an ArrayList full of Arrays that are containing the patients that are
	 * assigned to this doctor
 * @throws RemoteException
 */
	public PatientList getAssignedPatientList(String cpr) throws RemoteException {
		return clientModel.getAssignedPatientList(cpr);
	}
/**
 * 
 * @param patientCpr
 * @param doctorCpr
 * @return This method is for connecting the cpr of the doctor and the cpr of the patient in the database
 * @throws RemoteException
 */
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr) throws RemoteException {
		return clientModel.assignPatientToDoctor(patientCpr, doctorCpr);
	}
/**
 * 
 * @param cpr
 * @return It returns an ArrayList full of Arrays that are representing the prescription fields (recommendation,prescription,appointment,problem)
 * @throws RemoteException
 */
	public PatientPrescription getPatientPrescription(String cpr) throws RemoteException {
		return clientModel.getPatientPrescription(cpr);
	}
/**
 * 
 * @param cpr
 * @param prescription
 * @param appointment
 * @param problem
 * @param recommendations
 * @return It updates the Prescription based on the CPR entered,modifying the prescription fields (recommendation,prescription,appointment,problem)
 * @throws RemoteException
 */
	public boolean updatePrescription(String cpr, String prescription, LocalDate appointment, String problem,
			String recommendations) throws RemoteException {
		return clientModel.updatePrescription(cpr, prescription, appointment, problem, recommendations);
	}
/**
 * 
 * @param cpr
 * @param ilnesses
 * @param alergies
 * @param height
 * @param weight
 * @param smoker
 * @param vaccines
 * @param familyIlnesses
 * @param insurance
 * @param pregnancy
 * @return It updates Patient History based on the CPR entered,modifying the PatientHistory fields
 * @throws RemoteException
 */
	public boolean updatePatientHistory(String cpr, String ilnesses, String alergies, String height, String weight,
			String smoker, String vaccines, String familyIlnesses, String insurance, String pregnancy)
			throws RemoteException {

		return clientModel.updatePatientHistory(cpr, ilnesses, alergies, height, weight, smoker, vaccines,
				familyIlnesses, insurance, pregnancy);

	}
/**
 * The method is deleting completely from the System a user depending on his TYPE and CPR.
 * @param type
 * @param CPR
 * @throws RemoteException
 */
	public void AdminDeleteDoctorPatientController(String type, String CPR) throws RemoteException {
		if (type.equals("P"))
			clientModel.deletePatient(CPR);
		else
			clientModel.deleteDoctor(CPR);

	}

}
