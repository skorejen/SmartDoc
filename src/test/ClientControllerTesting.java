package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import smartdocClient.controller.ClientController;
import smartdocClient.domain.mediator.ClientModel;
import smartdocClient.domain.mediator.ClientModelManager;
import smartdocClient.domain.model.Doctor;
import smartdocServer.domain.mediator.ServerModel;
import smartdocServer.domain.mediator.ServerModelManager;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientPrescription;
import smartdocServer.view.ServerView;

public class ClientControllerTesting {

	private static ClientModel clientModel;

	private static ClientController instance;

	@BeforeAll
	public static void initialize() {
		ServerModelManager serverModelManager = ServerModelManager.getInstance();
		ServerView view = new ServerView(serverModelManager);
		clientModel = new ClientModelManager();
		instance.getInstance();

	}

	@Test
	void testSingleton() {
		ClientController c1 = instance.getInstance();
		ClientController c2 = instance.getInstance();
		assertEquals(c1, c2);
	}

	@Test
	void testVerifyLogin() throws RemoteException {

		String clientModelTest = clientModel.verifyLogin("admin", "321");
		assertEquals(clientModelTest, "111111-2222");
	}

	@Test
	void testCreateDoctor() throws RemoteException, PSQLException {
		clientModel.deleteDoctor("321322-5432");
		LocalDate now = LocalDate.now();
		boolean clientCreateDoctorTest;
		clientCreateDoctorTest = clientModel.createDoctor("doctorTest", "321", "Doctor", "House", "321322-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");
		clientModel.deleteDoctor("321322-5432");
		assertEquals(clientCreateDoctorTest, true);
	}

	@Test
	void testCreatePatient() throws RemoteException, PSQLException {
		clientModel.deletePatient("320000-5432");
		LocalDate now = LocalDate.now();
		boolean clientCreatePatientTest;
		clientCreatePatientTest = clientModel.createPatient("patient", "321", "Patient", "Secret", "320000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		assertEquals(clientCreatePatientTest, true);
	}

	@Test
	void testGetPatientType() throws RemoteException {
		String type = "D";
		clientModel.deleteDoctor("321323-5432");
		LocalDate now = LocalDate.now();
		boolean clientCreateDoctorTest = clientModel.createDoctor("doctorTest", "321", "Doctor", "House", "321323-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");
		
		String type2 = clientModel.getAccountAndType("321323-5432");
		assertEquals(type, type2);

	}

	@Test
	void testGetDoctorByCpr() throws RemoteException {

		clientModel.deleteDoctor("320011-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreateDoctorTest = clientModel.createDoctor("doctorTest", "321", "Doctor", "House", "320011-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		smartdocServer.domain.model.Doctor doc1 = clientModel.getDoctor("321321-5432");
		String doc1CPR = doc1.getCpr();

		assertEquals(doc1CPR, "321321-5432");
	}

	@Test
	void testGetPatientByCpr() throws RemoteException {

		clientModel.deletePatient("320000-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreatePatient = clientModel.createPatient("patient", "321", "Patient", "Patient", "320000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		Patient pat1 = clientModel.getPatient("320000-5432");

		String pat1CPR = pat1.getCpr();

		assertEquals(pat1CPR, "320000-5432");
	}

	@Test
	void testAssignPatientToDoctor() throws RemoteException {
		// delete and create patient #1
		clientModel.deletePatient("325000-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreatePatient1 = clientModel.createPatient("patient", "321", "Patient", "Patient", "325000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		// delete and create patient #2
		clientModel.deletePatient("325002-5432");

		boolean clientCreatePatient2 = clientModel.createPatient("patient", "321", "Patient", "Patient", "325002-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		// delete and create doctor
		clientModel.deleteDoctor("321321-5432");

		boolean clientCreateDoctorTest = clientModel.createDoctor("doctorTest", "321", "Doctor", "House", "321321-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		clientModel.assignPatientToDoctor("325000-5432", "321321-5432");
		clientModel.assignPatientToDoctor("325002-5432", "321321-5432");

		int number = clientModel.getAssignedPatientList("321321-5432").getNumberOfPatients();
		assertEquals(number, 2);
	}

	@Test
	void testUpdatePrescription() throws RemoteException {
		// delete and create patient
		clientModel.deletePatient("325000-5432");
		LocalDate now = LocalDate.now();

		boolean clientCreatePatient1 = clientModel.createPatient("patient", "321", "Patient", "Patient", "325000-5432",
				50607080, "chikybriky@gmail.com", now, "M");
		PatientPrescription prescription1 = new PatientPrescription("325000-5432", "Pills", now, "poor",
				"Get a Bachelor");
		clientModel.updatePrescription("325000-5432", "Pills", now, "poor", "Get a Bachelor");
		PatientPrescription prescription2 = clientModel.getPatientPrescription("325000-5432");
		String prc1 = prescription1.getCpr();
		String prc2 = prescription2.getCpr();
		
		
		//deleting everything
		clientModel.deletePatient("325000-5432");
		clientModel.deleteDoctor("321321-5432");
		clientModel.deletePatient("325002-5432");
		clientModel.deleteDoctor("320011-5432");
		clientModel.deleteDoctor("321323-5432");
		clientModel.deletePatient("320000-5432");
		clientModel.deleteDoctor("320011");
		clientModel.deletePatient("321462-5432");
		clientModel.deleteDoctor("321322-5432");
		
		
		assertEquals(prc1, prc2);
	}

}
