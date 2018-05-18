package smartdocClient.domain.model;

import java.util.Date;

public class Doctor {
	
	private String fname;
	private String lname;
	private int cpr;
	private int phone;
	private int age;
	private String speciality;
	private Date dob;
	public Doctor(String fname, String lname, int cpr, int phone, int age, String speciality, Date dob, String gender) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.cpr = cpr;
		this.phone = phone;
		this.age = age;
		this.speciality = speciality;
		this.dob = dob;
	}


}
