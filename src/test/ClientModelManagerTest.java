package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import smartdocClient.controller.ClientController;
import smartdocServer.domain.mediator.ServerModel;
import smartdocServer.domain.mediator.ServerModelManager;
import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientPrescription;
import smartdocServer.view.ServerView;

public class ClientModelManagerTest {

	private static ServerModel server;
	private static Doctor doctor;
	private static Patient patient;

	@BeforeAll
	public static void initialize() {
		ServerModelManager serverModelManager = ServerModelManager.getInstance();
		ServerView view = new ServerView(serverModelManager);
		server = ServerModelManager.getInstance();
	}
	
	@Test
	void testSingleton() {
		ServerModelManager c1 = ServerModelManager.getInstance();
		ServerModelManager c2 = ServerModelManager.getInstance();
		assertEquals(c1, c2);
	}
	
	@Test
	void testVerifyLogin() throws RemoteException {

		String serverTest = server.verifyLogin("admin", "321");
		assertEquals(serverTest, "111111-2222");
	}
	
	@Test
	void testCreateDoctor() throws RemoteException, PSQLException {
		server.deleteDoctor("321322-5432");
		LocalDate now = LocalDate.now();
		boolean clientCreateDoctorTest;
		clientCreateDoctorTest = server.createDoctor("doctorTest", "321", "Doctor", "House", "321322-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");
		server.deleteDoctor("321322-5432");
		assertEquals(clientCreateDoctorTest, true);
	}

	@Test
	void testCreatePatient() throws RemoteException, PSQLException {
		server.deletePatient("320000-5432");
		LocalDate now = LocalDate.now();
		boolean clientCreatePatientTest;
		clientCreatePatientTest = server.createPatient("patient", "321", "Patient", "Secret", "320000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		assertEquals(clientCreatePatientTest, true);
	}
	
	@Test
	void testGetPatientType() throws RemoteException {
		String type = "D";
		server.deleteDoctor("321323-5432");
		LocalDate now = LocalDate.now();
		boolean clientCreateDoctorTest = server.createDoctor("doctorTest", "321", "Doctor", "House", "321323-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");
		String type2 = server.getType("321323-5432");
		assertEquals(type, type2);

	}

	@Test
	void testGetDoctorByCpr() throws RemoteException {

		server.deleteDoctor("320011-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreateDoctorTest = server.createDoctor("doctorTest", "321", "Doctor", "House", "320011-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		smartdocServer.domain.model.Doctor doc1 = server.getDoctor("321321-5432");
		String doc1CPR = doc1.getCpr();

		assertEquals(doc1CPR, "321321-5432");
	}

	@Test
	void testGetPatientByCpr() throws RemoteException {

		server.deletePatient("320000-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreatePatient = server.createPatient("patient", "321", "Patient", "Patient", "320000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		Patient pat1 = server.getPatient("320000-5432");

		String pat1CPR = pat1.getCpr();

		assertEquals(pat1CPR, "320000-5432");
	}

	@Test
	void testAssignPatientToDoctor() throws RemoteException {
		// delete and create patient #1
		server.deletePatient("325000-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreatePatient1 = server.createPatient("patient", "321", "Patient", "Patient", "325000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		// delete and create patient #2
		server.deletePatient("325002-5432");

		boolean clientCreatePatient2 = server.createPatient("patient", "321", "Patient", "Patient", "325002-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		// delete and create doctor
		server.deleteDoctor("321321-5432");

		boolean clientCreateDoctorTest = server.createDoctor("doctorTest", "321", "Doctor", "House", "321321-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		server.assignPatientToDoctor("325000-5432", "321321-5432");
		server.assignPatientToDoctor("325002-5432", "321321-5432");

		int number = server.getAssignedPatientList("321321-5432").getNumberOfPatients();
		assertEquals(number, 2);
	}

	@Test
	void testUpdatePrescription() throws RemoteException {
		// delete and create patient
		server.deletePatient("325000-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreatePatient1 = server.createPatient("patient", "321", "Patient", "Patient", "325000-5432",
				50607080, "chikybriky@gmail.com", now, "M");
		PatientPrescription prescription1 = new PatientPrescription("325000-5432", "Pills", now, "poor",
				"Get a Bachelor");
		server.updatePrescription("325000-5432", "Pills", now, "poor", "Get a Bachelor");
		PatientPrescription prescription2 = server.getPatientPrescription("325000-5432");
		String prc1 = prescription1.getCpr();
		String prc2 = prescription2.getCpr();
		assertEquals(prc1, prc2);
		
		//deleting everything
				server.deletePatient("325000-5432");
				server.deleteDoctor("321321-5432");
				server.deletePatient("325002-5432");
				server.deleteDoctor("320011-5432");
				server.deleteDoctor("321323-5432");
				server.deletePatient("320000-5432");
				server.deleteDoctor("320011");
				server.deletePatient("321462-5432");
				server.deleteDoctor("321322-5432");
	}

	
}
