package com.utilities;

import java.util.ArrayList;
import java.util.List;

interface IDosageAttribute {
	public void set(Dosage dosage, String value);
}

/**
 * Dosage attributes are pieces of information that can be found in a dosage
 * 
 * @author Joel Dodge, Tom Zhang
 */
enum DosageAttr implements IDosageAttribute {
	DOSE() {
		public void set(Dosage dosage, String value) {
			dosage.setQuantity(value);
		}
	},
	ROUTE() {
		public void set(Dosage dosage, String value) {
			dosage.setRoute(value);
		}
	},
	FREQUENCY() {
		public void set(Dosage dosage, String value) {
			Frequency frequency = new Frequency();
			frequency.parseString(value);
			dosage.setFrequency(frequency);
		}
	},
	DURATION() {
		public void set(Dosage dosage, String value) {
			dosage.getFrequency().parseDuration(value);
		}
	},
	REASON() {
		public void set(Dosage dosage, String value) {
			dosage.setReason(value);
		}
	},
	WARNINGS() {
		public void set(Dosage dosage, String value) {
			dosage.setWarnings(value);
		}
	},
	INSTRUCTIONS() {
		public void set(Dosage dosage, String value) {
			dosage.setInstructions(value);
		}
	},
}

public class DosageParser {

	private static List<Rule> rules;
	// We return the first successful parse, so the possibleOrderings must be in
	// order of most complex to most simple
	private static final DosageAttr[][] possibleOrderings = {
			{ DosageAttr.DOSE, DosageAttr.INSTRUCTIONS, DosageAttr.FREQUENCY,
					DosageAttr.WARNINGS },
			{ DosageAttr.INSTRUCTIONS, DosageAttr.DOSE, DosageAttr.FREQUENCY,
					DosageAttr.WARNINGS },
			{ DosageAttr.DOSE, DosageAttr.FREQUENCY, DosageAttr.INSTRUCTIONS,
					DosageAttr.DURATION },
			{ DosageAttr.DOSE, DosageAttr.ROUTE, DosageAttr.FREQUENCY,
					DosageAttr.DURATION },
			{ DosageAttr.DOSE, DosageAttr.ROUTE, DosageAttr.FREQUENCY,
					DosageAttr.WARNINGS },
			{ DosageAttr.DOSE, DosageAttr.ROUTE, DosageAttr.FREQUENCY },
			{ DosageAttr.DOSE, DosageAttr.FREQUENCY, DosageAttr.DURATION },
			{ DosageAttr.DOSE, DosageAttr.FREQUENCY, DosageAttr.INSTRUCTIONS },
			{ DosageAttr.DOSE, DosageAttr.FREQUENCY, DosageAttr.REASON },
			{ DosageAttr.DOSE, DosageAttr.FREQUENCY } };

	static {
		rules = new ArrayList<Rule>();
		for (DosageAttr[] ordering : possibleOrderings) {
			rules.add(new Rule(ordering));
		}
	}

	/**
	 * Parse text containing dosage information.
	 * 
	 * @param dosageText
	 *            - String containing dosage information
	 * @return Dosage object containing parsed dosage information
	 */
	public static Dosage parseDosageString(String dosageText) {
		// Remove some punctuation
		String cleanedText = dosageText.replaceAll("!|'|,|\\.|:|;|\\?", "");
		for (Rule rule : rules) {
			Dosage dosage = rule.matchRule(cleanedText);
			if (dosage != null) {
				dosage.setManuallyEntered(false);
				return dosage;
			}
		}
		return null;
	}
}
