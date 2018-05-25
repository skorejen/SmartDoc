package smartdocServer.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Doctor implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 858043133661809053L;
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

	public Doctor(String cpr2, String fname2, String lname2, String string, int phone2, String email2, String type2,
			String gender2, String speciality2) {
		super();
		this.cpr = cpr2;
		this.fname = fname2;
		this.lname = lname2;
		this.dob = null;
		this.phone = phone2;
		this.email = email2;
		this.type = type2;
		this.gender = gender2;
		this.speciality = speciality2;
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
