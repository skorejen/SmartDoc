package smartdocServer.domain.mediator;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

import utility.observer.AbstractRemoteSubject;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubjectDelegate;

public class ServerModelManager extends Observable implements ServerModel 
{

	private static ServerModelManager instance;
	private  DbsPersistance dbsPersistance;
	RemoteSubjectDelegate<String> subject;

	
	private ServerModelManager()
	{
		try {
			subject = new RemoteSubjectDelegate<>(this);
			dbsPersistance = new DBS();
			Registry reg = LocateRegistry.createRegistry(1099);
			UnicastRemoteObject.exportObject(this, 0);
			Naming.rebind("vipassanaServer", this);
			System.out.println("Server is running");
			
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	
	public static ServerModelManager getInstance()
	{
		if(instance == null)
		{
			instance = new ServerModelManager();
		}
		return instance;
	}
	

	public boolean verifyLogin(String login, String password) {
		boolean ifPassed;
		ifPassed =  dbsPersistance.verifyLogin(login, password);
		return ifPassed;
	}

	@Override
	public boolean createDoctor(String fname, String lname, int cpr, int phone, int age, String speciality) {
		
		return dbsPersistance.createDoctor(fname, lname, cpr, phone, age, speciality);
	}

	@Override
	public void addObserver(RemoteObserver<String> o) throws RemoteException {
		subject.addObserver(o);
		
	}

	@Override
	public void deleteObserver(RemoteObserver<String> o) throws RemoteException {
		subject.deleteObserver(o);
		
	}

	
	
//	public synchronized void addMember(String name, String lastName, boolean payment) 
//	{
//		try {
//			dbsPersistance.addMember(name, lastName, payment);
//		} catch (SQLException e) {
//			super.notifyObservers("Info from SERVER: Something went wrong while adding member");
//			
//			System.out.println(e.getMessage());
//			return;
//		}
//		super.notifyObservers("Info from SERVER: Added member successfuly");
//		
//	}
	
//	public synchronized String getMembers() {
//		ArrayList<Object[]> array = dbsPersistance.getMembers();
//		members = new MemberList();
//		
//		for(int i=0;i<array.size();i++) {
//			Object[] row = array.get(i);
//			String name = row[0].toString();
//			String lastName = row[1].toString();
//			boolean payment = Boolean.parseBoolean(row[2].toString());
//			int memberNo = Integer.parseInt(row[3].toString());
//
//			members.addMember(new Member(name,lastName,payment,memberNo));
//		}
//		
//		
//		
//		return members.toString();
//	}
//
//	@Override
//	public String getNotPaidMembers() throws RemoteException {
//		ArrayList<Object[]> array = dbsPersistance.getNotPaidMembers();
//		members = new MemberList();
//		
//		for(int i=0;i<array.size();i++) {
//			Object[] row = array.get(i);
//			String name = row[0].toString();
//			String lastName = row[1].toString();
//			boolean payment = Boolean.parseBoolean(row[2].toString());
//			int memberNo = Integer.parseInt(row[3].toString());
//
//			members.addMember(new Member(name,lastName,payment,memberNo));
//		}
//		return members.toString();
//	}

}
