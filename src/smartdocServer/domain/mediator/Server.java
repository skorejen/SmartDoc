package smartdocServer.domain.mediator;

import smartdocServer.view.ServerView;

public class Server {

	public static void main(String[] args) {
		ServerModelManager serverModelManager = ServerModelManager.getInstance();
		ServerView view = new ServerView(serverModelManager);

	}

}
