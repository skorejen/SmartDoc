package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;

import smartdocServer.domain.mediator.ServerModelManager;

class ServerModelManagerTest {
	
	static ServerModelManager server;
	
	@BeforeAll
	public static void initialize(){
		server = ServerModelManager.getInstance();
	}
	
	@Test
	void testSingleton() {
		String server1=ServerModelManager.getInstance().toString();
		String server2=ServerModelManager.getInstance().toString();
		assertEquals(server1,server2);
	}
	
	@Test
	void testVerifyLoginFail() {
		assertEquals(server.verifyLogin("blah", "123"),"0");
	}
	
	@Test
	void testCreatingPatient() {
		
	}
	
	@Test
	void testAddingAndGettingDoctor() {
		LocalDate date = LocalDate.now();
		server.createDoctor("asd", "123", "Marek", "Cieb", "121244-4444", 12442412, "sdasd@op.l",
				date, "Dragologyst", "D", "M");
		assertEquals(server.getDoctor("121244-4444").getCpr(),"121244-4444");
	}

}
