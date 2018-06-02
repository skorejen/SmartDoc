package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import smartdocServer.domain.mediator.DBS;
import smartdocServer.domain.model.Doctor;
import smartdocServer.domain.model.Patient;
import utility.persistence.MyDatabase;



class DBSTest {
	
	static DBS dbs;
	static MyDatabase myDatabase;
	static Patient patient;
	static Doctor doctor;
	static String loginDoctor;
	static String passDoctor;
	static String loginPatient;
	static String passPatient;

	@BeforeAll
	public static void beforeAllOpenDatabase() {
		dbs = new DBS();
		try {
			myDatabase = new MyDatabase("org.postgresql.Driver",
					"jdbc:postgresql://localhost:5432/smartdocdatabase", "postgres", "sallie");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

		LocalDate date = LocalDate.now();
		patient = new Patient("555555-5555","firstName","lastName",date,5214424,"blahblah@op.l","P","M");
		doctor = new Doctor("444444-4444","firstName","lastName",date,2242422,"blafsd@osd","D","M","Urologyst");
		loginDoctor = "blah";
		passDoctor = "321";
		loginPatient = "halb";
		passPatient = "321";
	}
	
	
	@Test
	void passwordToHexTest() {
		String passHex = dbs.passwordToHex("321");
		assertEquals(passHex,"8D23CF6C86E834A7AA6EDED54C26CE2BB2E7493538C61BDD5D2197997AB2F72");
	}
	
	@Test
	void verifyLoginFakeDataTest() {
		String login = "not existing login";
		String password = "not existing password";
		String returnCpr = dbs.verifyLogin(login, password);
		assertEquals(returnCpr,"0");
	}
	
	@Test
	void verifyLoginAdminDataTest() {
		String login = "admin";
		String password = "321";
		String returnCpr = dbs.verifyLogin(login, password);
		boolean check;
		if(returnCpr.equals("0"))
		{
			check = false;
		} else
		{
			check = true;
		}
		assertTrue(check);
	}
	
	@Test
	void createDoctorTest() {
		dbs.deleteDoctor(doctor.getCpr());
		LocalDate date = LocalDate.now();
		
		boolean successCreate = dbs.createDoctor(loginDoctor, passDoctor, doctor.getFname(), doctor.getLname(), doctor.getCpr(), doctor.getPhone(), doctor.getEmail(),
				doctor.getDob(), doctor.getSpeciality(), doctor.getType(), doctor.getGender());
		ArrayList<Object[]> doctor = dbs.getDoctor(this.doctor.getCpr());
		assertTrue(successCreate);
		assertEquals(doctor.get(0)[0],this.doctor.getCpr());
		
		
		dbs.deleteDoctorByLogin(loginDoctor);
	}
	
	@Test
	void deleteDoctorTest() {
		dbs.deleteDoctor(doctor.getCpr());
		LocalDate date = LocalDate.now();
		
		boolean successCreate = dbs.createDoctor(loginDoctor, passDoctor, doctor.getFname(), doctor.getLname(), doctor.getCpr(), doctor.getPhone(), doctor.getEmail(),
				doctor.getDob(), doctor.getSpeciality(), doctor.getType(), doctor.getGender());
		ArrayList<Object[]> doctor = dbs.getDoctor(this.doctor.getCpr());
		assertTrue(successCreate);
		assertEquals(doctor.get(0)[0],this.doctor.getCpr());
		
		boolean deleteSuccess=false;
		
		dbs.deleteDoctor(this.doctor.getCpr());
		
		doctor = dbs.getDoctor(this.doctor.getCpr());
		if(doctor.isEmpty()) {
			deleteSuccess = true;
		}
		assertTrue(deleteSuccess);
	}
	
	@Test
	void parseDateToDbsTest() {
		LocalDate date1 = LocalDate.of(1990,6,5);
		
	Date date = dbs.parseDateToDbs(date1);
	assertEquals(date.toString(),date1.toString());
	}
	
	@Test
	void createPatientTest() {
		
		dbs.deletePatient(this.patient.getCpr());
		LocalDate date = LocalDate.now();
		
		boolean successCreate = dbs.createPatient(loginPatient, passPatient, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		assertEquals(patient.get(0)[0],this.patient.getCpr());
		
		
		
		dbs.deletePatient(this.patient.getCpr());
	}
	
	@Test
	void deletePatientTest() {
		dbs.deletePatient(this.patient.getCpr());
		LocalDate date = LocalDate.now();
		
		boolean successCreate = dbs.createPatient(loginPatient, loginDoctor, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		assertEquals(patient.get(0)[0],this.patient.getCpr());
		
		
		boolean deleteSuccess=false;
		
		dbs.deletePatient(this.patient.getCpr());
		
		patient = dbs.getAccountData(this.patient.getCpr());
		if(patient.isEmpty()) {
			deleteSuccess = true;
		}
		assertTrue(deleteSuccess);
		
	}
	
	@Test
	void deletePatientByLogin() {
		dbs.deletePatient(this.patient.getCpr());
		
		
		boolean successCreate = dbs.createPatient(loginPatient, passPatient, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		assertEquals(patient.get(0)[0],this.patient.getCpr());
		
		
		
		boolean deleteSuccess=false;
		
		dbs.deletePatientByLogin(loginPatient);
		
		patient = dbs.getAccountData(this.patient.getCpr());
		if(patient.isEmpty()) {
			deleteSuccess = true;
		}
		assertTrue(deleteSuccess);
	}
	
	@Test
	void deleteDoctorByLogin() {
		dbs.deleteDoctor(doctor.getCpr());
		
		boolean successCreate = dbs.createDoctor(loginDoctor, passDoctor, doctor.getFname(), doctor.getLname(), doctor.getCpr(), doctor.getPhone(), doctor.getEmail(),
				doctor.getDob(), doctor.getSpeciality(), doctor.getType(), doctor.getGender());
		ArrayList<Object[]> doctor = dbs.getDoctor(this.doctor.getCpr());
		assertTrue(successCreate);
		assertEquals(doctor.get(0)[0],this.doctor.getCpr());
		
		boolean deleteSuccess=false;
		
		dbs.deleteDoctorByLogin(loginDoctor);
		
		doctor = dbs.getDoctor(this.doctor.getCpr());
		if(doctor.isEmpty()) {
			deleteSuccess = true;
		}
		assertTrue(deleteSuccess);
	}
	
	@Test
	void getSpecialityTest() {
		boolean successCreate = dbs.createDoctor(loginDoctor, passDoctor, doctor.getFname(), doctor.getLname(), doctor.getCpr(), doctor.getPhone(), doctor.getEmail(),
				doctor.getDob(), doctor.getSpeciality(), doctor.getType(), doctor.getGender());
		ArrayList<Object[]> doctor = dbs.getDoctor(this.doctor.getCpr());
		assertTrue(successCreate);
		
		String speciality= (String)dbs.getSpeciality(this.doctor.getCpr()).get(0)[0];
		assertEquals(this.doctor.getSpeciality(),speciality);

		dbs.deleteDoctorByLogin(loginDoctor);
	}
	
	@Test
	void getPatientList() {
		boolean successCreate = dbs.createPatient(loginPatient, passPatient, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		ArrayList<Object[]> patients = dbs.getPatientList();
		
		boolean successfulReturn = false;
		if(patients.size()>0) {
			successfulReturn = true;
		} else {
			successfulReturn = false;
		}

		assertTrue(successfulReturn);

		dbs.deletePatient(this.patient.getCpr());
	}
	
	@Test
	void getDoctorList() {

		boolean successCreate = dbs.createDoctor(loginDoctor, passDoctor, doctor.getFname(), doctor.getLname(), doctor.getCpr(), doctor.getPhone(), doctor.getEmail(),
				doctor.getDob(), doctor.getSpeciality(), doctor.getType(), doctor.getGender());
		ArrayList<Object[]> doctor = dbs.getDoctor(this.doctor.getCpr());
		assertTrue(successCreate);
		
		assertEquals(doctor.get(0)[0],this.doctor.getCpr());
ArrayList<Object[]> doctors = dbs.getDoctorList();
		
		boolean successfulReturn = false;
		if(doctors.size()>0) {
			successfulReturn = true;
		} else {
			successfulReturn = false;
		}

		assertTrue(successfulReturn);

		dbs.deleteDoctor(this.doctor.getCpr());
		
	}
	
	@Test
	void assignPatientToDoctorTest() {
		boolean successCreate = dbs.createDoctor(loginDoctor, passDoctor, doctor.getFname(), doctor.getLname(), doctor.getCpr(), doctor.getPhone(), doctor.getEmail(),
				doctor.getDob(), doctor.getSpeciality(), doctor.getType(), doctor.getGender());
		ArrayList<Object[]> doctor = dbs.getDoctor(this.doctor.getCpr());
		assertTrue(successCreate);
		boolean successCreatePatient = dbs.createPatient(loginPatient, passPatient, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		assertTrue(successCreatePatient);
		boolean success = dbs.assignPatientToDoctor(this.patient.getCpr(), this.doctor.getCpr());
		
		assertTrue(success);
		dbs.deletePatient(this.patient.getCpr());
		dbs.deleteDoctor(this.doctor.getCpr());
	}
	
	@Test
	void getAssignedPatientListTest() {
		boolean successCreate = dbs.createDoctor(loginDoctor, passDoctor, doctor.getFname(), doctor.getLname(), doctor.getCpr(), doctor.getPhone(), doctor.getEmail(),
				doctor.getDob(), doctor.getSpeciality(), doctor.getType(), doctor.getGender());
		ArrayList<Object[]> doctor = dbs.getDoctor(this.doctor.getCpr());
		assertTrue(successCreate);
		boolean successCreatePatient = dbs.createPatient(loginPatient, passPatient, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		assertTrue(successCreatePatient);
		boolean success = dbs.assignPatientToDoctor(this.patient.getCpr(), this.doctor.getCpr());
		
		assertTrue(success);
		
		ArrayList<Object[]> assignedPatient = dbs.getAssignedPatientList(this.doctor.getCpr());
		assertEquals(this.patient.getCpr(),(String) assignedPatient.get(0)[0]);
		
		dbs.deletePatient(this.patient.getCpr());
		dbs.deleteDoctor(this.doctor.getCpr());
	}
	
	@Test
	void getPatientPrescriptionTest() {
		
		boolean successCreate = dbs.createPatient(loginPatient, passPatient, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		ArrayList<Object[]> prescription = dbs.getPatientPrescription(this.patient.getCpr());

		boolean successReturn = false;
		if(prescription.size()>0) {
			successReturn = true;
		} else {
			successReturn = false;
		}
		assertTrue(successReturn);
		dbs.deletePatient(this.patient.getCpr());
		
	}
	
	@Test
	void updatePatientPrescriptionTest() {
		boolean successCreate = dbs.createPatient(loginPatient, passPatient, patient.getFname(), patient.getLname(), this.patient.getCpr(),
				patient.getPhone(), patient.getEmail(), patient.getDob(), patient.getGender());
		ArrayList<Object[]> patient = dbs.getAccountData(this.patient.getCpr());
		assertTrue(successCreate);
		ArrayList<Object[]> prescription = dbs.getPatientPrescription(this.patient.getCpr());
		String prescriptionStringFirst = (String) prescription.get(0)[1];
		
		dbs.updatePrescription(this.patient.getCpr(), "NOT 0 ANYMORE", this.patient.getDob(), "0", "0");
		
		ArrayList<Object[]> prescription2 = dbs.getPatientPrescription(this.patient.getCpr());
		String prescriptionStringSecond = (String) prescription.get(0)[1];
		
		assertEquals(prescriptionStringFirst, prescriptionStringSecond);

		dbs.deletePatient(this.patient.getCpr());
	}

}

