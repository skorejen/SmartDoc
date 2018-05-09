package smartdocServer.domain.mediator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import utility.observer.RemoteSubject;

public interface ServerModel extends Remote, RemoteSubject<String> {
	

	public boolean verifyLogin(String login, String password) throws RemoteException;

}
