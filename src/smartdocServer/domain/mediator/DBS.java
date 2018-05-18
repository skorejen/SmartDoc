package smartdocServer.domain.mediator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

import utility.persistence.MyDatabase;

public class DBS implements DbsPersistance  {

private MyDatabase myDatabase;
	
	public DBS() {
		try {
			myDatabase = new MyDatabase("org.postgresql.Driver",
					"jdbc:postgresql://localhost:5432/smartdocdatabase", "postgres", "sallie");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public String passwordToHex(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = password.getBytes();
		md.update(buffer);
		byte[] digest = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(Integer.toHexString((int) (b & 0xff)));
		}
		String passwordHex = sb.toString();
		return passwordHex.toUpperCase();
	}

	@Override
	public boolean verifyLogin(String login, String password) {
		String passwordHex = "";
			
			passwordHex =  passwordToHex(password);
		
		String sql = "SELECT * from account where (login=? and password=?)";
		try {
			System.out.println(passwordHex);
			ArrayList<Object[]> array = myDatabase.query(sql, login, passwordHex);
			
			if(array.isEmpty())
			{
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		
		
		
	}

	@Override
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String speciality, String type, String gender) {
		
		String passwordHex = passwordToHex(password);
		
		String sql = "insert into account values (?,?,?)";
		System.out.println("Adding a new doctor with these data: "+ login +" " + password +" "+ cpr + " " + type);
		String sql1 = "insert into account_data values (?,?,?,?,?,?,?,?)";
		
		LocalDate sqlLocalDate;
		
			try {
				myDatabase.update(sql, cpr, login, password);
				myDatabase.update(sql1, cpr, fname,lname,phone,dob,email,type,gender);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return false;
	}
	
}
	
		
		
	


