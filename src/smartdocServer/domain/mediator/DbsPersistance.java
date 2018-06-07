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
	 * 
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
	 * @return Creates a doctor in the database, by inserting data to relations
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
	
	/**
	 *It returns an ArrayList full of Arrays that contains Patient model
	 * @param 
	 * @return
	 */
	public ArrayList<Object[]> getPatientList();
	/**
	 *It returns an ArrayList full of Arrays that contains Doctor model
	 * @param 
	 * @return
	 */
	public ArrayList<Object[]> getDoctorList();
	/**
	 * 
	 * @param patientCpr
	 * @param doctorCpr
	 * @return This method is conencting the cpr of the doctor and the cpr of the patient in the database
	 */
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr);
	/**
	 * The method returns searches for the Doctor's CPR in the database and it return an ArrayList full of Arrays that are containing the patients
	 * that are assigned to this doctor
	 * @param cpr
	 * @return
	 */
	public ArrayList<Object[]> getAssignedPatientList(String cpr);
	/**
	 * 
	 * @param cpr
	 * @return It returns an ArrayList full of Arrays that are representing the prescription fields (recommendation,prescription,appointment,problem)
	 */
	public ArrayList<Object[]> getPatientPrescription(String cpr);
	/**
	 * It deletes Doctor from all the databases that was present in by their CPR.
	 * @param cpr
	 */
	public void deleteDoctor(String cpr);
	/**
	 * It deletes Patient from all the databases that was present in by their CPR.
	 * @param cpr
	 */
	public void deletePatient(String cpr);
	/**
	 * It updates the Prescription based on the CPR entered,modifying the prescription fields (recommendation,prescription,appointment,problem)
	 * @param cpr
	 */
	public boolean updatePrescription(String cpr,String prescription, LocalDate appointment, String problem,
			String recommendations);
	/**
	 * This method initialize the system with the ADMIN user that has pre-established login and pass
	 */
	public void initializeAdmin();
	/**
	 * It deletes Patient from all the databases that was present in by their username.
	 * @param login
	 */
	public void deletePatientByLogin(String login);
	/**
	 * It deletes the Doctor from all the databases that was present in by their CPR.
	 * @param login
	 */
	public void deleteDoctorByLogin(String login);
	/**
	 * It updates Patient History based on the CPR entered,modifying the PatientHistory fields
	 * @param cpr
	 */
	public boolean updatePatientHistory(String cpr, String ilnesses, String alergies, String height, String weight,
			String smoker, String vaccines, String familyIlnesses, String insurance, String pregnancy);
		
	
}
