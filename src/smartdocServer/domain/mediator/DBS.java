package smartdocServer.domain.mediator;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Date;

import utility.persistence.MyDatabase;

/**
 * The class is the so called "adapter" for the database. Its' methods main
 * purpose is to retrieve or send data to database, and pass the data further to
 * the system.
 * 
 * @author Michal Ciebien
 *
 */
public class DBS implements DbsPersistance {

	private MyDatabase myDatabase;

	/**
	 * The DBS constructor initializes the connection with the database.
	 */
	public DBS() {
		try {
			myDatabase = new MyDatabase("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/smartdocdatabase",
					"postgres", "sallie");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Changes the parameter password into a new hexadecimal String, which is then
	 * ready to pass to database
	 * 
	 * @param password
	 * @return password as hexadecimal string
	 */
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
	public String verifyLogin(String login, String password) {
		String passwordHex = "";

		passwordHex = passwordToHex(password);

		String sql = "SELECT cpr from account where (login=? and password=?)";
		try {
			System.out.println(passwordHex);
			ArrayList<Object[]> array = myDatabase.query(sql, login, passwordHex);

			if (array.isEmpty()) {
				return "0";
			} else {
				return (String) array.get(0)[0];
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";

		}

	}

	@Override
	public boolean createDoctor(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String speciality, String type, String gender) {

		String passwordHex = passwordToHex(password);

		String sql = "insert into account values (?,?,?)";
		System.out.println("Adding a new doctor with these data: " + login + " " + password + " " + cpr + " " + type);
		String sql1 = "insert into account_data values (?,?,?,?,?,?,?,?)";
		String sql2 = "insert into doctor_speciality values (?,?)";

		Date dateSQL = parseDateToDbs(dob);

		System.out.println(dob.toString());

		try {
			myDatabase.update(sql, cpr, login, passwordHex);
			myDatabase.update(sql1, cpr, fname, lname, phone, dateSQL, email, type, gender);
			myDatabase.update(sql2, cpr, speciality);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean createPatient(String login, String password, String fname, String lname, String cpr, int phone,
			String email, LocalDate dob, String gender) {

		String passwordHex = passwordToHex(password);
		String sql = "insert into account values (?,?,?)";
		String sql1 = "insert into account_data values(?,?,?,?,?,?,?,?)";
		String sql2 = "insert into patient_data values(?,?,?,?,?,?,?,?,?,?)";
		String sql3 = "insert into patient_prescription values(?,?,?,?,?)";

		Date dateSQL = parseDateToDbs(dob);
		String type = "P";

		try {
			myDatabase.update(sql, cpr, login, passwordHex);
			myDatabase.update(sql1, cpr, fname, lname, phone, dateSQL, email, type, gender);
			myDatabase.update(sql2, cpr, '0','0',0,0,false,'0','0',false,false);
			myDatabase.update(sql3, cpr, '0',parseDateToDbs(LocalDate.now()),'0','0');
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * The method parses the LocalDate parameter into Date object that is compatible
	 * with postgreSQL database.
	 * 
	 * @param date
	 * @return date object compatible with database
	 */
	public Date parseDateToDbs(LocalDate date) {
		String[] dateVariables = date.toString().split("-");
		String trimYear = dateVariables[0];
		String trimMonth = dateVariables[1];
		String trimDay = dateVariables[2];

		System.out.println(trimYear + " " + trimMonth + " " + trimDay);

		int year = Integer.parseInt(trimYear) - 1900;
		int month = Integer.parseInt(trimMonth);

		month--;

		int day = Integer.parseInt(trimDay);

		Date dateSQL = new Date(year, month, day);
		return dateSQL;
	}

	@Override
	public ArrayList<Object[]> getDoctor(String cpr) {

		String sql = "SELECT * from account_data where (cpr=?)";

		ArrayList<Object[]> array = null;

		try {
			array = myDatabase.query(sql, cpr);
		} catch (SQLException e) {
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public ArrayList<Object[]> getAccountData(String cpr) {

		String sql = "SELECT * from account_data where (cpr=?)";
		ArrayList<Object[]> array = null;
		// array needs to be initialized before try / catch

		try {
			array = myDatabase.query(sql, cpr);
		} catch (SQLException e) {
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public ArrayList<Object[]> getSpeciality(String cpr) {
		String sql = "SELECT speciality from doctor_speciality where (cpr=?)";
		ArrayList<Object[]> array = null;

		try {
			array = myDatabase.query(sql, cpr);
		} catch (SQLException e) {
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public ArrayList<Object[]> getPatientList(){
		String sql = "SELECT * from account_data where (type=?)";
		ArrayList<Object[]> array = null;
		String type = "P";
		try {
			array = myDatabase.query(sql, type);
		} catch (SQLException e) {
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public ArrayList<Object[]> getDoctorList(){
		String sql = "SELECT * from account_data where (type=?)";
		ArrayList<Object[]> array = null;
		String type = "D";
		try {
			array = myDatabase.query(sql, type);
		} catch (SQLException e) {
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
		return array;
	}

	@Override
	public boolean assignPatientToDoctor(String patientCpr, String doctorCpr) {
		String sql = "INSERT INTO patientDoctor values(?,?)";
		
		
		try {
			myDatabase.update(sql, patientCpr, doctorCpr);
		} catch (SQLException e) {
			System.out.println("Failed to retrieve data from database");

			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public ArrayList<Object[]> getAssignedPatientList(String cpr) {
		
			String sql ="select pd.* from (select patientCpr as p from patientDoctor where"
					+ " (doctorCpr=?)) pcpr, account_data pd where pcpr.p =" + 
					"pd.cpr;";
					
			ArrayList<Object[]> array = null;
		
			try {
				array = myDatabase.query(sql, cpr);
			} catch (SQLException e) {
				System.out.println("Failed to retrieve data from database");
				e.printStackTrace();
			}
			return array;
	}
	
	
	public ArrayList<Object[]> getPatientPrescription(String cpr)
	{
		String sql = "select * from patient_prescription where cpr =? ";
		ArrayList<Object[]> array = null;
		
		try
		{
			array = myDatabase.query(sql, cpr);
		}
		catch(SQLException e)
		{
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
		return array;
	}
	
	public void deletePatient(String cpr) {
		String sql = "delete from account_data where (cpr=?)";
		String sql1 = "delete from patient_prescription where (cpr=?)";
		String sql2 = "delete from patient_data where (cpr=?)";
		String sql3 = "delete from patientDoctor where (patientCpr=?)";
		String sql4 = "delete from account where (cpr=?)";
		
		try
		{
			myDatabase.update(sql, cpr);
			myDatabase.update(sql1, cpr);
			myDatabase.update(sql2, cpr);
			myDatabase.update(sql3, cpr);
			myDatabase.update(sql4, cpr);
		}
		catch(SQLException e)
		{
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
		
	}
	
	public void deleteDoctor(String cpr) {
String sql = "delete from account_data where (cpr=?)";
String sql1 = "delete from doctor_speciality where (cpr=?)";
String sql2 = "delete from patientDoctor where (doctorCpr=?)";
String sql3 = "delete from account where (cpr=?)";
		
		
		try
		{
			myDatabase.update(sql, cpr);
			myDatabase.update(sql1, cpr);
			myDatabase.update(sql2, cpr);
			myDatabase.update(sql3, cpr);
		}
		catch(SQLException e)
		{
			System.out.println("Failed to retrieve data from database");
			e.printStackTrace();
		}
	}

	@Override
	public boolean updatePrescription(String cpr, String prescription, LocalDate appointment, String problem,
			String recommendations) {
		
		String sql = "update patient_prescription set cpr=?, prescription=?, appointments=?, problem=?,recommendations=?"
				+ "where cpr=?";
				
				
				try
				{
					myDatabase.update(sql, cpr, prescription, parseDateToDbs(appointment),problem,recommendations,cpr);
				}
				catch(SQLException e)
				{
					
					System.out.println("Failed to retrieve data from database");
					e.printStackTrace();
					return false;
				}
				return true;
	}

}
