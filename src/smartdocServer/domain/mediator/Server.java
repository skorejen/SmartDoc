package smartdocServer.domain.mediator;

import java.util.Scanner;

import smartdocServer.view.ServerView;

public class Server {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Write the dbs username");
		String username = input.nextLine();
		System.out.println("Write the dbs password");
		String password = input.nextLine();
		System.out.println("Write the dbs port");
		String port = input.nextLine();
		System.out.println("Write the dbs ip");
		String ip = input.nextLine();
		System.out.println("Write the dbs databaseName");
		String databaseName = input.nextLine();
		ServerModelManager serverModelManager = ServerModelManager.getInstance(username,password,port,ip,databaseName);
		ServerView view = new ServerView(serverModelManager);
	}

}
