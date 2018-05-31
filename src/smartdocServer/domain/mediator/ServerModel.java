package smartdocServer.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientList;
import smartdocServer.domain.model.PatientPrescription;
import utility.observer.RemoteSubject;

public interface ServerModel extends Remote, RemoteSubject<String> {

	/**
	 * The accesses the database and verifies if the account of the specific login
	 * and password exists, if so - it returns the cpr of the account
	 * 
	 * @param login
	 * @param password
	 * @return
	 * @throws RemoteException
	 */
	public String verifyLogin(String login, String password) throws RemoteException;

	/**
	 * Creates the doctor of the specific parameters by calling this method on DBS
	 * object (adapter)
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
	 * @return
	 * @throws RemoteException
	 */
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException;

	/**
	 * Creates the patient of the specific parameters by calling the same method on
	 * DBS object (adapter)
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
	 * @return
	 * @throws RemoteException
	 */
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String gender) throws RemoteException;

	/**
	 * Calls method on DBS class to receive data from database about a doctor with
	 * specific cpr. It then creates an object of Doctor and returns it
	 * 
	 * @param login
	 * @return
	 * @throws RemoteException
	 */
	public Doctor getDoctor(String cpr) throws RemoteException;

	/**
	 * Calls method on DBS class to receive data about the type of account of the
	 * speciific cpr.
	 * 
	 * @param cpr
	 * @return
	 * @throws RemoteException
	 */
	public String getType(String cpr) throws RemoteException;
	
	public Patient getPatient(String cpr) throws RemoteException;

	/**
	 * Returns an object of specific type, received from getType method, e.g. type =
	 * "D", then returns Doctor object
	 * 
	 * @param cpr
	 * @return
	 * @throws RemoteException
	 */
	public Object getAccount(String cpr, String type) throws RemoteException;
	public PatientList getPatientList() throws RemoteException;
	public DoctorList getDoctorList() throws RemoteException;
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr) throws RemoteException;
	public PatientList getAssignedPatientList(String cpr) throws RemoteException;
	public PatientPrescription getPatientPrescription(String cpr) throws RemoteException;
	
	public void deleteDoctor(String cpr) throws RemoteException;
	public void deletePatient(String cpr) throws RemoteException;
	public boolean updatePrescription(String cpr,String prescription, LocalDate appointment, String problem,
			String recommendations) throws RemoteException;
	public void deletePatientByLogin(String login) throws RemoteException;
	public void deleteDoctorByLogin(String login) throws RemoteException;

	
}
