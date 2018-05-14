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
					"jdbc:postgresql://localhost:5432/Clinique", "postgres", "");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	void insertAccount() {
		String sql = "insert into accounts values ('mihai','A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3')";
		try {
			myDatabase.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

