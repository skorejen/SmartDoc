package smartdocClient.domain.model;

import java.util.Date;

public class Doctor {
	
	private String fname;
	private String lname;
	private int cpr;
	private int phone;
	private int age;
	private String speciality;
	public Doctor(String fname, String lname, int cpr, int phone, int age, String speciality, Date dob) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.cpr = cpr;
		this.phone = phone;
		this.age = age;
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
	public int getCpr() {
		return cpr;
	}
	public void setCpr(int cpr) {
		this.cpr = cpr;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	private Date dob;

}
