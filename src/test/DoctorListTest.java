package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import smartdocClient.domain.model.Doctor;
import smartdocClient.domain.model.DoctorList;

class DoctorListTest {

	@Test
	void test() {
		

		LocalDate date = LocalDate.now();
		Doctor doctor1 = new Doctor("070999-1100", "Juan", "Pastor", date, 232323, "amigo@via.com", "d",
				"f", "cardiologist");
		Doctor doctor2 = new Doctor("070999-1111", "Jose", "Pastor", date, 232323, "amigo@via.com", "d",
				"m", "cardiologist");
		
	//////Test DoctorList: addDoctor, getNumberOfDoctors
		DoctorList doctorList = new DoctorList();
		doctorList.addDoctor(doctor1);
		doctorList.addDoctor(doctor2);
		
		assertEquals(doctorList.getNumberOfDoctors(),2);

	//////Test DoctorList: getDoctor

		
		assertEquals(doctorList.getDoctor(0), doctor1);
	
				
	//////Test DoctorList: removeDoctor
		

		doctorList.removeDoctor(0);
		assertEquals(doctorList.getDoctor(0), doctor2);

			
	//////Test DoctorList: isEmpty

		//doctorList.removeDoctor(0);
		assertEquals(doctorList.isEmpty(), false);
		

		
	//////Test DoctorList: getDoctorByFname

		assertEquals(doctorList.getDoctorByFname("Jose"), doctor2);
		
	//////Test DoctorList: getDoctorByLname

		assertEquals(doctorList.getDoctorByLname("Pastor"), doctor2);
		

	//////Test DoctorList: getDoctorByCpr

		assertEquals(doctorList.getDoctorByCpr("070999-1111"), doctor2);		

	//////Test DoctorList: getDoctorBySpeciality

			assertEquals(doctorList.getSpeciality("cardiologist"), doctor2);		
			
	}
}

