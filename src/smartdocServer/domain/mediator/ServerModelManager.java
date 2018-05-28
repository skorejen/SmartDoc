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
import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientList;
import utility.observer.AbstractRemoteSubject;
import utility.observer.RemoteObserver;
import utility.observer.RemoteSubjectDelegate;

public class ServerModelManager extends Observable implements ServerModel {

	private static ServerModelManager instance;
	private DbsPersistance dbsPersistance;
	RemoteSubjectDelegate<String> subject;

	private ServerModelManager() {
		try {
			subject = new RemoteSubjectDelegate<>(this);
			dbsPersistance = new DBS();
			Registry reg = LocateRegistry.createRegistry(1099);
			UnicastRemoteObject.exportObject(this, 0);
			Naming.rebind("vipassanaServer", this);
			System.out.println("Server is running");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ServerModelManager getInstance() {
		if (instance == null) {
			instance = new ServerModelManager();
		}
		return instance;
	}

	public String verifyLogin(String login, String password) {
		String cpr;
		cpr = dbsPersistance.verifyLogin(login, password);

		if (cpr.equals("0")) {
			super.setChanged();
			super.notifyObservers(false);
		} else {
			super.setChanged();
			super.notifyObservers(true);
		}
		return cpr;
	}

	@Override
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String speciality, String type, String gender) {
		boolean ifsuccessful;
		ifsuccessful = dbsPersistance.createDoctor(login, password, fname, lname, cpr, phone, email, dob, speciality,
				type, gender);
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

	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String gender) throws RemoteException {

		return dbsPersistance.createPatient(login, password, fname, lname, cpr, phone, email, dob, gender);
	}

	public Doctor getDoctor(String cpr) {

		ArrayList<Object[]> array = dbsPersistance.getAccountData(cpr);

		String fname = (String) array.get(0)[1];
		String lname = (String) array.get(0)[2];
		int phone = (int) array.get(0)[3];

		System.out.println("DATE: " + array.get(0)[4].toString());
		LocalDate dob = parseDateFromDbs((Date) array.get(0)[4]);
		String email = (String) array.get(0)[5];
		String type = (String) array.get(0)[6];
		String gender = (String) array.get(0)[7];

		ArrayList<Object[]> arraySpeciality = dbsPersistance.getSpeciality(cpr);
		String speciality = (String) arraySpeciality.get(0)[0];

		Doctor doctor = new Doctor(cpr, fname, lname, dob, phone, email, type, gender, speciality);
		return doctor;
	}

	public LocalDate parseDateFromDbs(Date sqlDate) {
		String date = sqlDate.toString();
		String[] dateVariables = new String[2];
		dateVariables = date.split("-");
		String trimYear = dateVariables[0];
		String trimMonth = dateVariables[1];
		String trimDay = dateVariables[2];

		System.out.println(trimYear + " " + trimMonth + " " + trimDay);

		LocalDate dateOfBirthVar = LocalDate.of(Integer.parseInt(trimYear), Integer.parseInt(trimMonth),
				Integer.parseInt(trimDay));
		return dateOfBirthVar;
	}

	@Override
	public String getType(String cpr) throws RemoteException {
		System.out.println(cpr);
		ArrayList<Object[]> array = dbsPersistance.getAccountData(cpr);

		System.out.println(array.get(0)[6].toString());
		String type = (String) array.get(0)[6];

		return type;
	}

	@Override
	public Object getAccount(String cpr, String type) throws RemoteException {

		if (type.equals("D")) {
			return (Doctor) getDoctor(cpr);
		} else if (type.equals("G")) {
			return (Doctor) getDoctor(cpr);
		} else if (type.equals("A")) {
			// return admin
		} else if (type.equals("P")) {
			return (Patient) getPatient(cpr);
		}
		System.out.println(type);
		return null;

	}

	@Override
	public Patient getPatient(String cpr) throws RemoteException {
		ArrayList<Object[]> array = dbsPersistance.getAccountData(cpr);

		String fname = (String) array.get(0)[1];
		String lname = (String) array.get(0)[2];
		int phone = (int) array.get(0)[3];
		LocalDate dob = parseDateFromDbs((Date) array.get(0)[4]);
		String email = (String) array.get(0)[5];
		String type = (String) array.get(0)[6];
		String gender = (String) array.get(0)[7];

		Patient patient = new Patient(cpr, fname, lname, dob, phone, email, type, gender);
		System.out.println(patient.toString());
		return patient;
	}

	@Override
	public PatientList getPatientList() throws RemoteException {
		
		
			
		PatientList patientList = new PatientList();
		ArrayList<Object[]> array = dbsPersistance.getPatientList();
		
		for(Object[] object: array) 
		{
			String cpr = (String) object[0];
			String fname = (String) object[1];
			String lname = (String) object[2];
			int phone = (int) object[3];

			System.out.println("DATE: " + object[4].toString());
			LocalDate dob = parseDateFromDbs((Date) object[5]);
			String email = (String) object[6];
			String type = (String) object[7];
			String gender = (String) object[8];


			Patient patient = new Patient(cpr, fname, lname, dob, phone, email, type, gender);
			patientList.addPatient(patient);;
		}
		return patientList;
	}

	@Override
	public DoctorList getDoctorList() throws RemoteException {

		DoctorList doctorList = new DoctorList();
		ArrayList<Object[]> array = dbsPersistance.getDoctorList();
		
		for(Object[] object: array) 
		{
			String cpr = (String) object[0];
			String fname = (String) object[1];
			String lname = (String) object[2];
			int phone = (int) object[3];

			System.out.println("DATE: " + object[4].toString());
			LocalDate dob = parseDateFromDbs((Date) object[4]);
			String email = (String) object[5];
			String type = (String) object[6];
			String gender = (String) object[7];
				
			ArrayList<Object[]> arraySpeciality = dbsPersistance.getSpeciality(cpr);
			System.out.println(cpr);
			String speciality = (String) arraySpeciality.get(0)[0];

			Doctor doctor = new Doctor(cpr, fname, lname, dob, phone, email, type, gender, speciality);
			doctorList.addDoctor(doctor);
		}
		return doctorList;
	}

	@Override
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr) throws RemoteException {
		
			
		
		return dbsPersistance.assignPatientToDoctor(patientCpr, doctorCpr);
	}

	@Override
	public PatientList getAssignedPatientList(String cpr) throws RemoteException {
		
		PatientList patientList = new PatientList();
		ArrayList<Object[]> array = dbsPersistance.getAssignedPatientList(cpr);
		
		for(Object[] object: array) 
		{
			String patientCpr = (String) object[0];
			String fname = (String) object[1];
			String lname = (String) object[2];
			int phone = (int) object[3];

			System.out.println("DATE: " + object[4].toString());
			LocalDate dob = parseDateFromDbs((Date) object[5]);
			String email = (String) object[6];
			String type = (String) object[7];
			String gender = (String) object[8];


			Patient patient = new Patient(patientCpr, fname, lname, dob, phone, email, type, gender);
			patientList.addPatient(patient);;
		}
		return patientList;
	}
}
