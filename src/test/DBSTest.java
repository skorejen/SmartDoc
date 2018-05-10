package test;


import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import smartdocServer.domain.mediator.DBS;
import utility.persistence.MyDatabase;



class DBSTest {
	
	DBS dbs;
	MyDatabase myDatabase;

//	@Test
//	void verifyLoginTest() {
//
//		System.out.println(""+dbs.verifyLogin("jakub123", "12345"));
//		
//		assertEquals(true, dbs.verifyLogin("jakub123", "12345"));
//	}
	
	@BeforeEach
	void beforeEachTest() {
		dbs = new DBS();
		try {
			myDatabase = new MyDatabase("org.postgresql.Driver",
					"jdbc:postgresql://localhost:5432/Clinique", "postgres", "sallie");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void insertAccount() {
		String sql = "insert into accounts values ('michael','8D23CF6C86E834A7AA6EDED54C26CE2BB2E7493538C61BDD5D2197997AB2F72')";
		try {
			myDatabase.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

