package smartdocClient.domain.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class Patient 
{

	private String fname;
	private String lname;
	private int phone;
	private Date dob;
	private String email;
	private String cpr;
	private char gender;
	private String type;
	
	

	public Patient(String fname, String lname, int phone, Date dob, String email, String cpr, char gender) 
	{
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.dob = dob;
		this.email = email;
		this.cpr = cpr;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Patient [fname=" + fname + ", lname=" + lname + ", phone=" + phone + ", dob=" + dob + ", email=" + email
				+ ", cpr=" + cpr + ", gender=" + gender + "]";
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Date getDob() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dobFormatted = (Date) formatter.parse(formatter.format(dob)); 
		return dobFormatted;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
}
