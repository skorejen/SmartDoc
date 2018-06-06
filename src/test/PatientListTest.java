package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;

import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientList;
import smartdocServer.domain.mediator.ServerModelManager;

class PatientListTest {

	static PatientList patientList;
	static ServerModelManager server;
	static LocalDate date = LocalDate.now();
	static Patient patient1;
	static Patient patient2;
	static Patient patient3;
	
	@BeforeAll
		public static void initialize(){
			server = ServerModelManager.getInstance("postgres","123","5432","localhost","smartdocdatabase");
			patientList = new PatientList();
			patient1 = new Patient("070999-0000", "Pedro", "Claro", date, 11111111, "claro@gmail.com", "p",
					"m");
			patient2 = new Patient("070999-0010", "Amaia", "Sanchez", date, 22222222, "pedrosanchez@gmail.com", "p",
					"f");
			patient3 = new Patient("122999-0010", "Lunes", "Pastor", date, 33333333, "lunes@gmail.com", "p",
					"m");
		}
		
		
	/*static LocalDate date = LocalDate.now();
	static Patient patient1 = new Patient("070999-0000", "Pedro", "Sanchez", date, 11111111, "pedrosanch@gmail.com", "p",
		"m");
	static Patient patient2 = new Patient("070999-0000", "Amaia", "Sanchez", date, 11111111, "pedrosanchez@gmail.com", "p",
			"m");*/
		

		@Test //works

		void testAddPatientGetNumberOfPatients() {
		patientList.addPatient(patient1);
		patientList.addPatient(patient2);
		
		assertEquals(patientList.getNumberOfPatients(),3);
		}
		
		@Test //works


		void testGetPatient() {
		assertEquals(patientList.getPatient(0), patient2);
		}
		
		@Test //works
		

		void testRemovePatient() {
			patientList.addPatient(patient1);
			patientList.addPatient(patient2);
		patientList.removePatient(0);
		assertEquals(patientList.getPatient(0), patient2);
		}

		
		@Test //works

		void testIsEmpty() {
			//patientList.removePatient(0);
		assertEquals(patientList.isEmpty(), false);
		}

		
		@Test //works

		void testGetPatientByFname() {
			patient2=patientList.getPatientByFname("Amaia");
		assertEquals(patient2, this.patient2);
		}
		
		@Test //works

		void testPatientList() {

			patient2=patientList.getPatientByLname("Sanchez");
		assertEquals(patient2, this.patient2);
		}
		
		@Test //works

		void testGetPatientByCpr() {
			patient2=patientList.getPatientByCpr("070999-0010");
		assertEquals(patient2, this.patient2);		
		
		
		
		

	}
		

}




	
	


