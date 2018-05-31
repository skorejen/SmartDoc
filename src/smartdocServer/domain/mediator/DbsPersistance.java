package smartdocServer.domain.mediator;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.model.PatientList;

public interface DbsPersistance {

	/**
	 * Verifies if the account of specific login and password exists in the
	 * database, if not returns 0, if it does returns cpr number of the person who
	 * this account belong to
	 * 
	 * @param login
	 * @param password
	 * @return cpr number of a person, or 0 if verification failed
	 */
	public String verifyLogin(String login, String password);

	/**
	 * Creates a doctor in the database, by inserting data to relations
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
	 */
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String speciality, String type, String gender);

	/**
	 * Creates a patient in the database, by inserting data to relations
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
	 */
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String gender);

	/**
	 * Look for a matching cpr result in the database and returns that row as an
	 * array of objects in an ArrayList which contains one result
	 * 
	 * @param cpr
	 * @return
	 */
	public ArrayList<Object[]> getDoctor(String cpr);

	/**
	 * Look for a matching cpr result in the database and returns that row as an
	 * array of objects in an ArrayList which contains one result
	 * 
	 * @param cpr
	 * @return
	 */
	public ArrayList<Object[]> getAccountData(String cpr);

	/**
	 * Looks for a matching cpr result in the database and returns that row as an
	 * array of objects in an ArrayList which contains one result
	 * 
	 * @param cpr
	 * @return
	 */
	public ArrayList<Object[]> getSpeciality(String cpr);
	public ArrayList<Object[]> getPatientList();
	public ArrayList<Object[]> getDoctorList();
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr);
	public ArrayList<Object[]> getAssignedPatientList(String cpr);
	public ArrayList<Object[]> getPatientPrescription(String cpr);
	public void deleteDoctor(String cpr);
	public void deletePatient(String cpr);
	public boolean updatePrescription(String cpr,String prescription, LocalDate appointment, String problem,
			String recommendations);
	public void initializeAdmin();
		
	
}
