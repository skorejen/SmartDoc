package smartdocServer.domain.model;

import java.io.Serializable;

public class PatientHistory implements Serializable {

	private static final long serialVersionUID = -2344071584884140587L;
	private String cpr;
	private String Ilnesses;
	private String Alergies;
	private int Height;
	private int Weight;
	private boolean smoker;
	private String Vaccines;
	private String FamilyIlnesses;
	private boolean Insurance;
	private boolean pregnancy;

	public PatientHistory(String cpr, String ilnesses, String alergies, int height, int weight, boolean smoker,
			String vaccines, String familyIlnesses, boolean insurance, boolean pregnancy) {
		this.cpr = cpr;
		Ilnesses = ilnesses;
		Alergies = alergies;
		Height = height;
		Weight = weight;
		this.smoker = smoker;
		Vaccines = vaccines;
		FamilyIlnesses = familyIlnesses;
		Insurance = insurance;
		this.pregnancy = pregnancy;
	}

	public String getIlnesses() {
		return Ilnesses;
	}

	public void setIlnesses(String ilnesses) {
		Ilnesses = ilnesses;
	}

	public String getAlergies() {
		return Alergies;
	}

	public void setAlergies(String alergies) {
		Alergies = alergies;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public int getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	public String getVaccines() {
		return Vaccines;
	}

	public void setVaccines(String vaccines) {
		Vaccines = vaccines;
	}

	public String getFamilyIlnesses() {
		return FamilyIlnesses;
	}

	public void setFamilyIlnesses(String familyIlnesses) {
		FamilyIlnesses = familyIlnesses;
	}

	public boolean isInsurance() {
		return Insurance;
	}

	public void setInsurance(boolean insurance) {
		Insurance = insurance;
	}

	public boolean isPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(boolean pregnancy) {
		this.pregnancy = pregnancy;
	}

}
