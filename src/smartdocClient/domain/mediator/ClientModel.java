package smartdocClient.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;

import utility.observer.RemoteObserver;

public interface ClientModel extends Remote, RemoteObserver<String> {
	

	public boolean verifyLogin(String login, String password) throws RemoteException;

	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender) throws RemoteException;
}
