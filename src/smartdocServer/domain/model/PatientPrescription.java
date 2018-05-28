package smartdocServer.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

public class PatientPrescription implements Serializable {

	private static final long serialVersionUID = -2344071584884140587L;
	private String cpr;
	private String prescription;
	private LocalDate appointments;
	private String problem;
	private String recommendation;
	private String doctorCPR;

	public PatientPrescription(String cpr, String prescription, LocalDate appointments, String problem,String recommendation, String doctorCPR) {
		this.cpr = cpr;
		this.prescription = prescription;
		this.appointments = appointments;
		this.problem = problem;
		this.recommendation = recommendation;
		this.doctorCPR = doctorCPR;
	}
	
	public PatientPrescription(String prescription, LocalDate appointments, String problem, String doctorCPR) {
		this.prescription = prescription;
		this.appointments = appointments;
		this.problem = problem;
		this.doctorCPR = doctorCPR;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public LocalDate getAppointments() {
		return appointments;
	}

	public void setAppointments(LocalDate appointments) {
		this.appointments = appointments;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getRecommendatiob() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getDoctorCPR() {
		return doctorCPR;
	}

	public void setDoctorCPR(String doctorCPR) {
		this.doctorCPR = doctorCPR;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
