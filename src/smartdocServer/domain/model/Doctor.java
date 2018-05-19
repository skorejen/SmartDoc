package smartdocServer.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Doctor implements Serializable{
	
	private String cpr;
	private String fname;
	
	private String lname;
	private LocalDate dob;
	private int phone;
	private String email;
	private String type;
	private String gender;
	private String speciality;
	
	public Doctor(String cpr, String fname, String lname, LocalDate dob, int phone, String email, String type,
			String gender, String speciality) {
		super();
		this.cpr = cpr;
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.type = type;
		this.gender = gender;
		this.speciality = speciality;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	
	

}
