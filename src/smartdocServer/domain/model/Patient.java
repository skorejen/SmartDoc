package smartdocServer.domain.model;

import java.time.LocalDate;
import java.io.Serializable;

public class Patient implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2344071584884140587L;
	private String cpr;
	private String fname;
	
	private String lname;
	private LocalDate dob;
	private int phone;
	private String email;
	private String type;
	private String gender;
	
	

	public Patient(String cpr, String fname, String lname, LocalDate dob, int phone, String email, String type,
			String gender) 
	{
		this.cpr = cpr;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.dob = dob;
		this.type = type;
		this.email = email;
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

	public LocalDate getDob(){
		 
		return dob;
	}

	public void setDob(LocalDate dob) {
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
