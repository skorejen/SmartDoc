package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import smartdocServer.domain.model.PatientPrescription;
import smartdocServer.domain.mediator.ServerModelManager;

class PatientPrescriptionTest {

		static PatientPrescription patientPrescription;
		static LocalDate date = LocalDate.now();
		static ServerModelManager server;
	
	@BeforeAll
	
	public static void initialize() {
		server = ServerModelManager.getInstance("postgres","123","5432","localhost","smartdocdatabase");
		patientPrescription= new PatientPrescription("070999-1111", "Medicine", date, "Headache", "Sleep");
	}
	@Test //Works
	void testCpr() {
		patientPrescription.setCpr("070999-1111");
		patientPrescription.getCpr();
		assertEquals(patientPrescription.getCpr(),"070999-1111");

		
	}
	
	@Test //Works
	void testPrescription() {
		patientPrescription.setPrescription("Medicine");
		patientPrescription.getPrescription();
		assertEquals(patientPrescription.getPrescription(),"Medicine");

		
	}

	@Test //Works
	void testAppointments() {
		patientPrescription.setAppointments(date);
		patientPrescription.getAppointments();
		assertEquals(patientPrescription.getAppointments(),date);

		
	}
	
	@Test //Works
	void testProblem() {
		patientPrescription.setProblem("Headache");
		patientPrescription.getProblem();
		assertEquals(patientPrescription.getProblem(),"Headache");

		
	}
	
	@Test //Works
	void testRecommendation() {
		patientPrescription.setRecommendation("Sleep");
		patientPrescription.getRecommendation();
		assertEquals(patientPrescription.getRecommendation(),"Sleep");

		
	}

}
