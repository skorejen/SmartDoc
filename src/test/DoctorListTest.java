package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.DoctorList;
import smartdocServer.domain.mediator.ServerModelManager;
import smartdocServer.domain.model.Patient;
import smartdocServer.domain.model.PatientList;

class DoctorListTest {
	static DoctorList doctorList;
	static ServerModelManager server;
	static LocalDate date = LocalDate.now();
	static Doctor doctor1;
	static Doctor doctor2;
	
	@BeforeAll
		public static void initialize(){
			server = ServerModelManager.getInstance();
			doctorList = new DoctorList();
			LocalDate date = LocalDate.now();
			doctor1 = new Doctor("070999-1100", "Juan", "Pastor", date, 232323, "amigo@via.com", "d",
					"f", "cardiologist");
			doctor2 = new Doctor("070999-1111", "Jose", "Pastor", date, 232323, "amigo@via.com", "d",
					"m", "cardiologist");
			}
	

		

	
	@Test //works
		
	void testAddDoctorGetNumberOfDoctors() {
		doctorList.addDoctor(doctor1);
		doctorList.addDoctor(doctor2);
		assertEquals(doctorList.getNumberOfDoctors(),3);
		}
	



		
		
		

	@Test //Works
		void testGetDoctor() {

		assertEquals(doctorList.getDoctor(0), doctor2);
		}


			
	@Test //Works
	 	void testRemoveDoctor() {
		

		doctorList.addDoctor(doctor1);
		doctorList.addDoctor(doctor2);
		doctorList.removeDoctor(0);
		assertEquals(doctorList.getDoctor(0), doctor2);
		}

	@Test //Works
	void testIsEmpty() {

		//doctorList.removeDoctor(0);
		assertEquals(doctorList.isEmpty(), false);
	}

	
	@Test //Works
	void TestGetDoctorByFname() {

		assertEquals(doctorList.getDoctorByFname("Jose"), doctor2);
	}
	@Test //Works
	void testGetDoctorByLname(){

		assertEquals(doctorList.getDoctorByLname("Pastor"), doctor2);
	}

	@Test //Works
	void TestGetDoctorByCpr(){

		assertEquals(doctorList.getDoctorByCpr("070999-1111"), doctor2);		
	}
	@Test //Works
	void TestGetDoctorBySpeciality(){

			assertEquals(doctorList.getSpeciality("cardiologist"), doctor2);		
			
	}
	
}