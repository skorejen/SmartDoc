package smartdocServer.domain.mediator;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import smartdocServer.domain.model.Doctor;
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
	

	public String verifyLogin(String login, String password) {
		String cpr;
		cpr=dbsPersistance.verifyLogin(login, password);
		super.setChanged();
		super.notifyObservers(cpr);
		return cpr;
	}


	@Override
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender) {
		boolean ifsuccessful;
		ifsuccessful=dbsPersistance.createDoctor(login, password, fname, lname, cpr, phone, email, dob, speciality, type, gender);
		super.setChanged();
		super.notifyObservers(ifsuccessful);
		return ifsuccessful; 
	}

	@Override
	public void addObserver(RemoteObserver<String> o) throws RemoteException {
		subject.addObserver(o);
		
	}

	@Override
	public void deleteObserver(RemoteObserver<String> o) throws RemoteException {
		subject.deleteObserver(o);
		
	}

	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String gender) throws RemoteException{
		
		return dbsPersistance.createPatient(login,password,fname,lname,cpr,phone,email,dob,gender);
	}
	
	public Doctor getDoctor(String cpr) {
		
		ArrayList<Object[]> array = dbsPersistance.getAccount(cpr);
		
		String fname = (String) array.get(0)[1];
		String lname = (String) array.get(0)[2];
		int phone = (int) array.get(0)[3];

		System.out.println("DATE: "+array.get(0)[4].toString());
		LocalDate dob = parseDateFromDbs((Date) array.get(0)[4]);
		String email =(String)  array.get(0)[5];
		String type = (String) array.get(0)[6];
		String gender =(String)  array.get(0)[7];
		
		ArrayList<Object[]> arraySpeciality = dbsPersistance.getSpeciality(cpr);
		String speciality =(String)  array.get(0)[0];
		
		Doctor doctor = new Doctor(cpr, fname, lname, dob, phone, email, type, gender, speciality);
		return doctor;
	}
	
	public LocalDate parseDateFromDbs(Date sqlDate) {
		String date = sqlDate.toString();
		String [] dateVariables = new String[2];
		dateVariables = date.split("-");
		String trimYear = dateVariables[0];
		String trimMonth = dateVariables[1];
		String trimDay = dateVariables[2];
		
		System.out.println(trimYear+" "+trimMonth+" "+trimDay);
		
		LocalDate dateOfBirthVar = LocalDate.of(Integer.parseInt(trimYear), Integer.parseInt(trimMonth), Integer.parseInt(trimDay));
		return dateOfBirthVar;
	}

	@Override
	public String getType(String cpr) throws RemoteException {
		System.out.println(cpr);
		ArrayList<Object[]> array = dbsPersistance.getAccount(cpr);
		System.out.println(array.get(0)[6].toString());
		String type = (String) array.get(0)[6];
		
		return type;
	}

	@Override
	public Object getAccount(String cpr) throws RemoteException {
		String type = getType(cpr);
		if(type.equals("D"))
		{
			return (Doctor) getDoctor(cpr);
		} else if (type.equals("G"))
		{
			//return general doctor
		} else if (type.equals("A"))
		{
			// return admin
		} else if (type.equals("P"))
		{
			//return patient
		}
		System.out.println(type);
		return null;
		
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
