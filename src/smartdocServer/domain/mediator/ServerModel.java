package smartdocServer.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import utility.observer.RemoteSubject;

public interface ServerModel extends Remote, RemoteSubject<String> {
	

	public boolean verifyLogin(String login, String password) throws RemoteException;
	public boolean createDoctor(String fname, String lname, int cpr, int phone, int age, String speciality) throws RemoteException;

}
