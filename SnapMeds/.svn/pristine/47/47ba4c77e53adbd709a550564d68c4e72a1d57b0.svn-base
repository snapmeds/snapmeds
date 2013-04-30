package com.utilities;

/**
 * Contains all data corresponding to a prescribed medication. Created when a
 * user saves a medication to their medicine cabinet.
 * 
 * @author Terence Zhao
 * @author Matt Omori
 * @author Onur Karaman
 * @author Bobby Heidkamp
 * 
 */
public class Prescription {
	private Drug drug;
	private Dosage dosage;
	private String note;
	private String imagePath;

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public Dosage getDosage() {
		return dosage;
	}

	public void setDosage(Dosage dosage) {
		this.dosage = dosage;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String path) {
		imagePath = path;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Prescription)) {
			return false;
		}
		Prescription otherPrescription = (Prescription) other;
		if (getDrug() != null && !getDrug().equals(otherPrescription.getDrug())) {
			return false;
		}
		if (getDrug() == null && otherPrescription.getDrug() != null) {
			return false;
		}
		if (getDosage() != null
				&& !getDosage().equals(otherPrescription.getDosage())) {
			return false;
		}
		if (getDosage() == null && otherPrescription.getDosage() != null) {
			return false;
		}
		if (getNote() != null && !getNote().equals(otherPrescription.getNote())) {
			return false;
		}
		if (getNote() == null && otherPrescription.getNote() != null) {
			return false;
		}
		if (getImagePath() != null
				&& !getImagePath().equals(otherPrescription.getImagePath())) {
			return false;
		}
		if (getImagePath() == null && otherPrescription.getImagePath() != null) {
			return false;
		}
		return true;
	}

	/**
	 * Checks to see if the drug in two prescriptions is the same.
	 * 
	 * @param other
	 *            the prescription to compare against
	 * 
	 * @return whether or not the drug is the same
	 * 
	 */
	public boolean sameDrug(Prescription other) {
		if (other == null) {
			return false;
		}
		if (getDrug() == null && other.getDrug() == null) {
			return true;
		}
		if (getDrug() == null || other.getDrug() == null) {
			return false;
		}
		return getDrug().getSetID().equals(other.getDrug().getSetID());
	}

	/**
	 * Saves a prescription to the application's storage mechanism.
	 */
	public void save() {
		try {
			Storage.getInstance().editPrescription(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}