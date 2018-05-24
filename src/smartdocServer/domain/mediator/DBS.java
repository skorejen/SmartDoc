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

		Date dateSQL = parseDateToDbs(dob);
		String type = "P";

		try {
			myDatabase.update(sql, cpr, login, passwordHex);
			myDatabase.update(sql1, cpr, fname, lname, phone, dateSQL, email, type, gender);
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
	public ArrayList<Object[]> getPatientList() throws RemoteException {
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
	public ArrayList<Object[]> getDoctorList() throws RemoteException {
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

}
