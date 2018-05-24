package smartdocClient.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientList;
import utility.observer.RemoteObserver;

public interface ClientModel extends Remote, RemoteObserver<String> {
	

	public String verifyLogin(String login, String password) throws RemoteException;

	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException;
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String gender) throws RemoteException;
	public String getAccountAndType(String cpr) throws RemoteException;
	
	public Doctor getDoctorModel() throws RemoteException;
	public Patient getPatientModel() throws RemoteException;
	// the difference between getting PatientModel and getting Patient is by using the latter you get any Patient from database, and
	// in the first case you get the model for the signed in account
	public Patient getPatient(String cpr) throws RemoteException;
	public Doctor getDoctor(String cpr) throws RemoteException;
	public PatientList getPatientList() throws RemoteException;
	public DoctorList getDoctorList() throws RemoteException;
}
