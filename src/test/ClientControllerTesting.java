package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.time.LocalDate;

import org.junit.After;
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

		LocalDate now = LocalDate.now();
		 
		boolean clientCreateDoctorTest = clientModel.createDoctor("doctorTest22", "321", "Doctor", "House", "321350-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		assertEquals(clientCreateDoctorTest, true);
		clientModel.deleteDoctorByLogin("doctorTest22");
	}

	@Test
	void testCreatePatient() throws RemoteException, PSQLException {

		LocalDate now = LocalDate.now();
		boolean clientCreatePatientTest;
		clientCreatePatientTest = clientModel.createPatient("patient9", "321", "Patient", "Secret", "320000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		assertEquals(clientCreatePatientTest, true);
		clientModel.deletePatientByLogin("patient9");
	}

	@Test
	void testGetPatientType() throws RemoteException {
		String type = "D";
		LocalDate now = LocalDate.now();
		boolean clientCreateDoctorTest = clientModel.createDoctor("doctorTest", "321", "Doctor", "House", "321323-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		String type2 = clientModel.getAccountAndType("321323-5432");

		assertEquals(type, type2);
		clientModel.deleteDoctorByLogin("doctorTest");
	}

	@Test
	void testGetDoctorByCpr() throws RemoteException {

		LocalDate now = LocalDate.now();

		boolean clientCreateDoctorTest = clientModel.createDoctor("doctorTest", "321", "Doctor", "House", "320011-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		smartdocServer.domain.model.Doctor doc1 = clientModel.getDoctor("320011-5432");
		String doc1CPR = doc1.getCpr();

		assertEquals(doc1CPR, "320011-5432");
		clientModel.deleteDoctorByLogin("doctorTest");

	}

	@Test
	void testGetPatientByCpr() throws RemoteException {

		LocalDate now = LocalDate.now();

		boolean clientCreatePatient = clientModel.createPatient("patient4", "321", "Patient", "Patient", "320000-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		Patient pat1 = clientModel.getPatient("320000-5432");

		String pat1CPR = pat1.getCpr();

		assertEquals(pat1CPR, "320000-5432");
		clientModel.deletePatientByLogin("patient4");

	}

	@Test
	void testAssignPatientToDoctor() throws RemoteException {

		
		// delete and create patient #1

		LocalDate now = LocalDate.now();

		boolean clientCreatePatient1 = clientModel.createPatient("patient0", "321", "Patient", "Patient", "325008-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		// delete and create patient #2

		boolean clientCreatePatient2 = clientModel.createPatient("patient2", "321", "Patient", "Patient", "325011-5432",
				50607080, "chikybriky@gmail.com", now, "M");

		// delete and create doctor

		boolean clientCreateDoctorTest = clientModel.createDoctor("doctorTest", "321", "Doctor", "House", "321325-5432",
				50602040, "DoctorStrange@gmail.com", now, "Tester", "D", "M");

		clientModel.assignPatientToDoctor("325008-5432", "321325-5432");
		clientModel.assignPatientToDoctor("325011-5432", "321325-5432");

		int number = clientModel.getAssignedPatientList("321325-5432").getNumberOfPatients();
		assertEquals(number, 2);
		clientModel.deletePatientByLogin("patient0");
		clientModel.deletePatientByLogin("patient2");
		clientModel.deleteDoctorByLogin("doctorTest");

	}

	@Test
	void testUpdatePrescription() throws RemoteException {
		// delete and create patient
		
		LocalDate now = LocalDate.now();

		boolean clientCreatePatient1 = clientModel.createPatient("patient5", "321", "Patient", "Patient", "325012-5432",
				50607080, "chikybriky@gmail.com", now, "M");
		PatientPrescription prescription1 = new PatientPrescription("325012-5432", "Pills", now, "poor",
				"Get a Bachelor");
		clientModel.updatePrescription("325012-5432", "Pills", now, "poor", "Get a Bachelor");
		PatientPrescription prescription2 = clientModel.getPatientPrescription("325012-5432");
		String prc1 = prescription1.getCpr();
		String prc2 = prescription2.getCpr();

		assertEquals(prc1, prc2);
		clientModel.deletePatientByLogin("patient5");
		
		

	}


}
