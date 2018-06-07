package smartdocClient.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientList;
import smartdocServer.domain.model.PatientPrescription;
import utility.observer.RemoteObserver;

public interface ClientModel extends Remote {
	
/**
 * 
 * @param login
 * @param password
 * @return The method return 0 if the Login succeeds or 0 if it doesn't.
 * @throws RemoteException
 */
	public String verifyLogin(String login, String password) throws RemoteException;

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
	 * @return Creates a doctor in the database, by inserting data to relations returns true or false if the operation succeeded or not
	 * @throws RemoteException
	 */
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException;
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
	 * @return returns true or false if the operation succeeded or not
	 * @throws RemoteException
	 */
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String gender) throws RemoteException;
	/**
	 * 
	 * @param cpr
	 * @return the type of the Account "D","G" or "P" depending on which user is logged in.
	 * @throws RemoteException
	 */
	public String getAccountAndType(String cpr) throws RemoteException;
	
	/**
	 * 
	 * @return the method calls the Model of the Doctor once it's logged in and returns the Doctor's as an Object
	 * @throws RemoteException
	 */
	public Doctor getDoctorModel() throws RemoteException;
	/**
	 * 
	 * @return the method calls the Model of the Patient once it's logged in and returns the Doctor's as an Patient
	 * @throws RemoteException
	 */
	public Patient getPatientModel() throws RemoteException;
	// the difference between getting PatientModel and getting Patient is by using the latter you get any Patient from database, and
	// in the first case you get the model for the signed in account
	/**
	 * 
	 * @param cpr
	 * @return the method return the patient with the CPR entered as a parameter
	 * @throws RemoteException
	 */
	public Patient getPatient(String cpr) throws RemoteException;
	/**
	 * 
	 * @param cpr
	 * @return the method return the doctor with the CPR entered as a parameter
	 * @throws RemoteException
	 */
	public Doctor getDoctor(String cpr) throws RemoteException;
	/**
	 * 
	 * @return It returns an ArrayList full of Arrays that contains Patient model
	 * @throws RemoteException
	 */
	public PatientList getPatientList() throws RemoteException;
	/**
	 * 
	 * @returnIt returns an ArrayList full of Arrays that contains Doctor model
	 * @throws RemoteException
	 */
	public DoctorList getDoctorList() throws RemoteException;
	/**
	 * 
	 * @param patientCpr
	 * @param doctorCpr
	 * @return This method is for connecting the cpr of the doctor and the cpr of the patient in the database
	 * @throws RemoteException
	 */
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr) throws RemoteException;
	/**
	 * 
	 * @param cpr
	 * @return The method returns searches for the Doctor's CPR in the database and it
		 * return an ArrayList full of Arrays that are containing the patients that are
		 * assigned to this doctor
	 * @throws RemoteException
	 */
	public PatientList getAssignedPatientList(String cpr) throws RemoteException;
	/**
	 * 
	 * @param cpr
	 * @return It returns an ArrayList full of Arrays that are representing the prescription fields (recommendation,prescription,appointment,problem)
	 * @throws RemoteException
	 */
	public PatientPrescription getPatientPrescription(String cpr) throws RemoteException;
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
	public boolean updatePrescription(String cpr,String prescription, LocalDate appointment, String problem,
			String recommendations) throws RemoteException;
	/**
	 * It deletes Doctor from all the databases that was present in by their CPR.
	 * @param cpr
	 */
	public void deleteDoctor(String cpr) throws RemoteException;
	/**
	 * It deletes Patient from all the databases that was present in by their CPR.
	 * @param cpr
	 */
	public void deletePatient(String cpr) throws RemoteException;
	/**
	 * It deletes Patient from all the databases that was present in by their username.
	 * @param login
	 */
	public void deletePatientByLogin(String login) throws RemoteException;
	/**
	 * It deletes the Doctor from all the databases that was present in by their CPR.
	 * @param login
	 */
	public void deleteDoctorByLogin(String login) throws RemoteException;
	/**
	 * It updates Patient History based on the CPR entered,modifying the PatientHistory fields
	 * @param cpr
	 */
	public boolean updatePatientHistory(String cpr, String ilnesses, String alergies, String height, String weight,
			String smoker, String vaccines, String familyIlnesses, String insurance, String pregnancy) throws RemoteException;


	
}
