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

	public PatientPrescription(String cpr, String prescription, LocalDate appointments, String problem,String recommendation) {
		this.cpr = cpr;
		this.prescription = prescription;
		this.appointments = appointments;
		this.problem = problem;
		this.recommendation = recommendation;
	}
	
	public PatientPrescription(String prescription, LocalDate appointments, String problem, String doctorCPR) {
		this.prescription = prescription;
		this.appointments = appointments;
		this.problem = problem;
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

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
