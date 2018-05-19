package smartdocClient.domain.mediator;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Observable;

import smartdocServer.domain.mediator.ServerModel;
import smartdocServer.domain.mediator.ServerModelManager;
import utility.observer.RemoteSubject;

public class ClientModelManager extends Observable implements ClientModel {

	private ServerModel server;
	
	public ClientModelManager() {
			
		
		try {
			server = (ServerModel) Naming.lookup("rmi://localhost:1099/vipassanaServer");
			UnicastRemoteObject.exportObject(this, 0);
			server.addObserver(this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public synchronized void update(RemoteSubject<String> subject, String updateMsg) throws RemoteException {
		
		
	}
	
	

	public String verifyLogin(String login, String password) {
		try {
			return server.verifyLogin(login, password);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return "0";
	}


	@Override
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender)
	{
		
		try {
			return server.createDoctor(login, password, fname, lname, cpr, phone, email, dob, speciality, type, gender);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String gender){
		
		try {
			return server.createPatient(login, password, fname, lname, cpr, phone, email, dob, gender);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	

}
