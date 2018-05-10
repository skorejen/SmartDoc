package smartdocClient.view;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import smartdocClient.controller.ClientController;

public class ClientView implements IClientView {

	private ClientController clientController;
	private Scanner input;

	public ClientView() {
		input = new Scanner(System.in);
		
	}

	
	public void startView(ClientController clientController) {
		this.clientController = clientController;
		Thread t = new Thread(this);
		t.start();
	}

	

	public void run() {
		boolean continueWorking = true;
		while (continueWorking) {
			// Read input from user input.
			System.out.print("1) Type 1 to add new member \n 2) Type 2 to get all members \n 3) Type 3 to get all members who not paid 4)"
					+ " Type 0 to exit\n Enter choice: ");
			int choice = input.nextInt();
			input.nextLine();

			// clientController.execute(choice);
			if (choice == 0) {
				continueWorking = false;
			} else {
			}
		}
	}

	
	public String get(String text) {
		System.out.print("Enter " + text + ": ");
		return input.nextLine();
	}

	
	public void show(String text) {
		System.out.println(text);
	}


	
	public void update(Observable o, Object arg) {
		String message = (String) arg;
		show(message);
		
	}

	





}
