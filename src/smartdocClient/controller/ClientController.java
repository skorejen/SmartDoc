package smartdocClient.controller;

import java.rmi.RemoteException;

import smartdocClient.domain.mediator.ClientModel;
import smartdocClient.domain.mediator.ClientModelManager;
import smartdocClient.view.ClientView;

public class ClientController {

	private static  ClientView clientView;
	private static  ClientModel clientModel;
	
	private static ClientController instance;
	
	private ClientController(ClientView clientView, ClientModel clientModel) {
		
			this.clientView=clientView;
			this.clientModel= new ClientModelManager();
	}
	
	public static ClientController getInstance()
	{
		if(instance == null)
		{
			 instance = new ClientController(clientView,clientModel);
		}
		
		return instance;
	}
	
	public boolean verifyLogin(String login,String password) throws RemoteException
	{
		return clientModel.verifyLogin(login, password);
	}
	
}
