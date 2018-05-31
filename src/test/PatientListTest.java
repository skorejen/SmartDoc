package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import smartdocClient.domain.model.Patient;
import smartdocClient.domain.model.PatientList;

class PatientListTest {

	@Test
	void test() {
	//	fail("Not yet implemented");
		

		LocalDate date = LocalDate.now();
		Patient patient1 = new Patient("070999-0000", "Pedro", "Sanchez", date, 11111111, "pedrosanchez@gmail.com", "p",
			"m");
		Patient patient2 = new Patient("070999-0000", "Amaia", "Sanchez", date, 11111111, "pedrosanchez@gmail.com", "p",
				"m");
	//////Test PatientList: addPatient, getNumberOfPatients
		PatientList patientList = new PatientList();
		patientList.addPatient(patient1);
		patientList.addPatient(patient2);
		
		assertEquals(patientList.getNumberOfPatients(),2);
	//////Test PatientList: getPatient

		
		assertEquals(patientList.getPatient(0), patient1);
		
	//////Test PatientList: removePatient
		

		patientList.removePatient(0);
		assertEquals(patientList.getPatient(0), patient2);
		
	//////Test PatientList: isEmpty
		//patientList.removePatient(0);
		assertEquals(patientList.isEmpty(), false);
		
	//////Test PatientList: getPatientByFname

		assertEquals(patientList.getPatientByFname("Amaia"), patient2);
		
		
	//////Test PatientList: getPatientByLname

		assertEquals(patientList.getPatientByLname("Sanchez"), patient2);
		
		
	//////Test PatientList: getPatientByCpr

		assertEquals(patientList.getPatientByCpr("070999-0000"), patient2);		
		
		
		
		

	}

}




	
	


