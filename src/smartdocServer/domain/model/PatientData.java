package smartdocServer.domain.model;

import java.io.Serializable;

public class PatientData implements Serializable {

	private static final long serialVersionUID = -2344071584884140587L;
	private String cpr;
	private String illnesses;
	private String alergies;
	private int height;
	private int weight;
	private boolean Smoker;
	private String vaccines;
	private String familyIllnesses;
	private boolean insurance;
	private boolean pregnancy;

	public PatientData(String cpr, String illnesses, String alergies, int height, int weight, boolean Smoker,
			String vaccines, String familyIllnesses, boolean insurance, boolean pregnancy) {
		this.cpr = cpr;
		this.illnesses = illnesses;
		this.alergies = alergies;
		this.height = height;
		this.weight = weight;
		this.Smoker = Smoker;
		this.vaccines = vaccines;
		this.familyIllnesses = familyIllnesses;
		this.insurance = insurance;
		this.pregnancy = pregnancy;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(String illnesses) {
		this.illnesses = illnesses;
	}

	public String getAlergies() {
		return alergies;
	}

	public void setAlergies(String alergies) {
		this.alergies = alergies;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean getisSmoker() {
		return Smoker;
	}

	public void setSmoker(boolean Smoker) {
		this.Smoker = Smoker;
	}

	public String getVaccines() {
		return vaccines;
	}

	public void setVaccines(String vaccines) {
		this.vaccines = vaccines;
	}

	public String getFamilyIllnesses() {
		return familyIllnesses;
	}

	public void setFamilyIllnesses(String familyIllnesses) {
		this.familyIllnesses = familyIllnesses;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public boolean isPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(boolean pregnancy) {
		this.pregnancy = pregnancy;
	}

}
