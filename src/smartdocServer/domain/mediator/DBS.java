package smartdocServer.domain.mediator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.persistence.MyDatabase;

public class DBS implements DbsPersistance  {

private MyDatabase myDatabase;
	
	public DBS() {
		try {
			myDatabase = new MyDatabase("org.postgresql.Driver",
					"jdbc:postgresql://localhost:5432/Clinique", "postgres", "");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public String fromByteToHex(byte[] digest) {
		StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(Integer.toHexString((int) (b & 0xff)));
		}
		String returnSb = sb.toString();
		return returnSb.toUpperCase();
	}

	@Override
	public boolean verifyLogin(String login, String password) {
		String passwordHex = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] buffer = password.getBytes();
			md.update(buffer);
			byte[] digest = md.digest();
			
			passwordHex = fromByteToHex(digest);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "SELECT * from accounts where (login=? and password=?)";
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
	
}
	
		
		
	


