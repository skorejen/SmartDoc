package smartdocClient.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.Patient;
import utility.observer.RemoteObserver;

public interface ClientModel extends Remote, RemoteObserver<String> {
	

	public String verifyLogin(String login, String password) throws RemoteException;

	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException;
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String gender) throws RemoteException;
	public String getAccountAndType(String cpr) throws RemoteException;
	public Doctor getDoctorModel() throws RemoteException;
	public Patient getPatientModel() throws RemoteException;
}
