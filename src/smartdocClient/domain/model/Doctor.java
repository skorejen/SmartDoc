package smartdocClient.domain.model;

import java.util.Date;

public class Doctor {
	
	private String fname;
	private String lname;
	private String cpr;
	private int phone;
	private String email;
	private String type;
	private String gender;
	private String speciality;
	public Doctor(String fname, String lname, String cpr, int phone, int age, String speciality, Date dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.cpr = cpr;
		this.phone = phone;
		this.speciality = speciality;
		this.dob = dob;
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
	public String getCpr() {
		return cpr;
	}
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public Date getDob() {
		return dob;
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
	private Date dob;

}
