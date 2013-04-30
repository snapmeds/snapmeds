package com.utilities;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Stores data associated with a drug. Primarily a data class.
 * 
 */
public class Drug {
	private String adverseReactions;
	private String conflictingConditions;
	private String genericNames;
	private String precautions;
	private String boxWarnings;
	private String uses;
	private String warnings;
	private String name;
	private String medicationGuide;
	private String setID;

	private static final String DEFAULT_ADVERSE_REACTIONS = "No Results but Adverse Reactions May Still Exist";
	private static final String DEFAULT_CONFLICTING_CONDITIONS = "No Results, but Conflicting Conditions May Still Exist";
	private static final String DEFAULT_GENERIC_NAMES = "No Results but Generic Names May Still Exist";
	private static final String DEFAULT_PRECAUTIONS = "No Results but Precautions May Still Exist";
	private static final String DEFAULT_BOX_WARNINGS = "No Results but Box Warnings May Still Exist";
	private static final String DEFAULT_USES = "No Results but Other Uses May Still Exist";
	private static final String DEFAULT_WARNINGS = "No Results but Warnings May Still Exist";
	private static final String DEFAULT_MEDICATION_GUIDE = "No Results but a Medicination Guide May Still Exist";

	public Drug() {
		adverseReactions = DEFAULT_ADVERSE_REACTIONS;
		conflictingConditions = DEFAULT_CONFLICTING_CONDITIONS;
		genericNames = DEFAULT_GENERIC_NAMES;
		precautions = DEFAULT_PRECAUTIONS;
		boxWarnings = DEFAULT_BOX_WARNINGS;
		uses = DEFAULT_USES;
		warnings = DEFAULT_WARNINGS;
		medicationGuide = DEFAULT_MEDICATION_GUIDE;
	}

	/**
	 * Checks the drug's name field to see if this is a valid and populated
	 * drug.
	 * 
	 * @return true if valid and populated, false otherwise
	 */
	@JsonIgnore
	public boolean isPopulated() {
		return getName() != null && !getName().isEmpty()
				&& !getName().equalsIgnoreCase("null")
				&& !getName().equalsIgnoreCase("false");
	}

	public String getAdverseReactions() {
		return adverseReactions;
	}

	public void setAdverseReactions(String adverseReactions) {
		if (adverseReactions == null || adverseReactions.isEmpty()) {
			adverseReactions = DEFAULT_ADVERSE_REACTIONS;
		}
		this.adverseReactions = adverseReactions;
	}

	public String getConflictingConditions() {
		return conflictingConditions;
	}

	public void setConflictingConditions(String conflictingConditions) {
		if (conflictingConditions == null || conflictingConditions.isEmpty()) {
			conflictingConditions = DEFAULT_CONFLICTING_CONDITIONS;
		}
		this.conflictingConditions = conflictingConditions;
	}

	public String getGenericNames() {
		return genericNames;
	}

	public void setGenericNames(String genericNames) {
		if (genericNames == null || genericNames.isEmpty()) {
			genericNames = DEFAULT_GENERIC_NAMES;
		}
		this.genericNames = genericNames;
	}

	public String getPrecautions() {
		return precautions;
	}

	public void setPrecautions(String precautions) {
		if (precautions == null || precautions.isEmpty()) {
			precautions = DEFAULT_PRECAUTIONS;
		}
		this.precautions = precautions;
	}

	public String getBoxWarnings() {
		return boxWarnings;
	}

	public void setBoxWarnings(String boxWarnings) {
		if (boxWarnings == null || boxWarnings.isEmpty()) {
			boxWarnings = DEFAULT_BOX_WARNINGS;
		}
		this.boxWarnings = boxWarnings;
	}

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		if (uses == null || uses.isEmpty()) {
			uses = DEFAULT_USES;
		}
		this.uses = uses;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		if (warnings == null || warnings.isEmpty()) {
			warnings = DEFAULT_WARNINGS;
		}
		this.warnings = warnings;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMedicationGuide() {
		return medicationGuide;
	}

	public void setMedicationGuide(String medicationGuide) {
		if (medicationGuide == null || medicationGuide.isEmpty()) {
			medicationGuide = DEFAULT_MEDICATION_GUIDE;
		}
		this.medicationGuide = medicationGuide;
	}

	public String getSetID() {
		return setID;
	}

	public void setSetID(String setID) {
		this.setID = setID;
	}

	private boolean checkStrings(String a, String b) {
		if (a != null && !a.equals(b)) {
			return false;
		}
		if (a == null && b != null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Drug)) {
			return false;
		}
		Drug otherDrug = (Drug) other;
		return checkStrings(getAdverseReactions(),
				otherDrug.getAdverseReactions())
				&& checkStrings(getConflictingConditions(),
						otherDrug.getConflictingConditions())
				&& checkStrings(getGenericNames(), otherDrug.getGenericNames())
				&& checkStrings(getPrecautions(), otherDrug.getPrecautions())
				&& checkStrings(getBoxWarnings(), otherDrug.getBoxWarnings())
				&& checkStrings(getUses(), otherDrug.getUses())
				&& checkStrings(getWarnings(), otherDrug.getWarnings())
				&& checkStrings(getName(), otherDrug.getName())
				&& checkStrings(getMedicationGuide(),
						otherDrug.getMedicationGuide())
				&& checkStrings(getSetID(), otherDrug.getSetID());
	}
}
