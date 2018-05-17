package smartdocServer.domain.mediator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import utility.persistence.MyDatabase;

public class DBS implements DbsPersistance  {

private MyDatabase myDatabase;
	
	public DBS() {
		try {
			myDatabase = new MyDatabase("org.postgresql.Driver",
					"jdbc:postgresql://localhost:5432/Clinique", "postgres", "sallie");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public String fromByteToHex(String password) {
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
			
			passwordHex = fromByteToHex(password);
		
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
	public boolean createDoctor(String login, String password, String fname, String lname, int cpr, int phone, String email, Date dob, String speciality) {
		
		String sql = "insert into account values (?,'?',)";
		return false;
	}
	
}
	
		
		
	


