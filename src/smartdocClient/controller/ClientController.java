package smartdocClient.controller;

import java.rmi.RemoteException;

import smartdocClient.domain.mediator.ClientModel;
import smartdocClient.view.ClientView;

public class ClientController {

	private ClientView clientView;

	private ClientModel clientModel;
	
	public ClientController(ClientView clientView, ClientModel clientModel) {
		
			this.clientView=clientView;
			this.clientModel=clientModel;
	}
	
	public void execute(int choice){
	
		switch(choice) {
			case 1:
				
				break;
				
				
				
	
			
				
				
		}
	}
}
