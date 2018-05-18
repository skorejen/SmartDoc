package smartdocServer.domain.mediator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Date;

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
		String sql2 = "insert into doctor_speciality values (?)";
		
		
		 
		
		Date dateSQL = parseDateToDbs(dob);
		
		System.out.println(dob.toString());
		
			try {
				myDatabase.update(sql, cpr, login, passwordHex);
				myDatabase.update(sql1, cpr, fname,lname,phone,dateSQL,email,type,gender);
				myDatabase.update(sql2, cpr, speciality);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		
		
		return true;
	}
	
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone, String email, LocalDate dob, String gender) {
		
		String passwordHex = passwordToHex(password);
		String sql = "insert into account values (?,?,?)";
		String sql1 = "insert into account_data values(?,?,?,?,?,?,?,?)";

		Date dateSQL = parseDateToDbs(dob);
		String type = "P";

		try {
			myDatabase.update(sql, cpr, login, passwordHex);
			myDatabase.update(sql1, cpr, fname,lname,phone,dateSQL,email,type,gender);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Date parseDateToDbs(LocalDate date) {
		String[] dateVariables = date.toString().split("-");
		String trimYear = dateVariables[0];
		String trimMonth = dateVariables[1];
		String trimDay = dateVariables[2];
		
		System.out.println(trimYear+" "+trimMonth+" "+trimDay);
		
		int year = Integer.parseInt(trimYear)-1900;
		int month = Integer.parseInt(trimMonth);
		
			month--;
		
		int day = Integer.parseInt(trimDay);
		
		Date dateSQL = new Date(year,month,day);
		return dateSQL;
	}
	
	public LocalDate parseDateFromDbs(Date date) {
		return null;
	}
	
}
	
		
		
	


