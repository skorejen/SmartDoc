package smartdocServer.view;

import java.util.Observable;

import smartdocServer.controller.ServerController;
import smartdocServer.domain.mediator.ServerModelManager;

public class ServerView implements View{

	private ServerController serverController;
	
	 public ServerView(ServerModelManager serverModelManager) {
		serverModelManager.addObserver(this);
	 }

	@Override
	public void update(Observable arg0, Object ifLogIn) {
		if((Boolean) ifLogIn == true) {
			System.out.println("A user has accessed to the system.");
		}
		else {
			System.out.println("A user has failed trying to access to the system.");
		}
		
	}

}
