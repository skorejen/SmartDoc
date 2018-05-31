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
import utility.persistence.MyDatabase;



class DBSTest {
	
	static DBS dbs;
	static MyDatabase myDatabase;

//	@Test
//	void verifyLoginTest() {
//
//		System.out.println(""+dbs.verifyLogin("jakub123", "12345"));
//		
//		assertEquals(true, dbs.verifyLogin("jakub123", "12345"));
//	}
	
	@BeforeAll
	public static void beforeAllOpenDatabase() {
		dbs = new DBS();
		try {
			myDatabase = new MyDatabase("org.postgresql.Driver",
					"jdbc:postgresql://localhost:5432/smartdocdatabase", "postgres", "sallie");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
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
		String cpr="121244-4444";
		dbs.deleteDoctor(cpr);
		LocalDate date = LocalDate.now();
		
		boolean successCreate = dbs.createDoctor("asd", "123", "Marek", "Cieb", cpr, 12442412, "sdasd@op.l",
				date, "Dragologyst", "D", "M");
		ArrayList<Object[]> doctor = dbs.getDoctor(cpr);
		assertTrue(successCreate);
		assertEquals(doctor.get(0)[0],cpr);
		
		
		
		dbs.deleteDoctor(cpr);
	}
	
	@Test
	void deleteDoctorTest() {
		String cpr="121244-4444";
		dbs.deleteDoctor(cpr);
		LocalDate date = LocalDate.now();
		
		boolean successCreate = dbs.createDoctor("asd", "123", "Marek", "Cieb", cpr, 12442412, "sdasd@op.l",
				date, "Dragologyst", "D", "M");
		ArrayList<Object[]> doctor = dbs.getDoctor(cpr);
		
		assertTrue(successCreate);
		
		boolean deleteSuccess=false;
		
		dbs.deleteDoctor(cpr);
		
		doctor = dbs.getDoctor(cpr);
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
		String cpr="121244-4444";
		dbs.deletePatient(cpr);
		LocalDate date = LocalDate.now();
		
		boolean successCreate = dbs.createPatient("patient", "321", "Patient", "Secret", cpr,
				50607080, "chikybriky@gmail.com", date, "M");
		ArrayList<Object[]> patient = dbs.getAccountData(cpr);
		assertTrue(successCreate);
		assertEquals(patient.get(0)[0],cpr);
		
		
		
		dbs.deletePatient(cpr);
	}
	
	

}

