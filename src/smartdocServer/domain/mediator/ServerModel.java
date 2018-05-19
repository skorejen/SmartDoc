package smartdocServer.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

import smartdocServer.domain.model.Doctor;
import utility.observer.RemoteSubject;

public interface ServerModel extends Remote, RemoteSubject<String> {
	

	public String verifyLogin(String login, String password) throws RemoteException;
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException;
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String gender) throws RemoteException;
	public Doctor getDoctor(String login) throws RemoteException;
	public String getType(String cpr) throws RemoteException;
	public Object getAccount(String cpr) throws RemoteException;
}
