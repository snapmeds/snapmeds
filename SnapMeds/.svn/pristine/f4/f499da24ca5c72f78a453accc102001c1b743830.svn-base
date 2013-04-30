package com.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A rule represents an ordering of dosage attributes. If a string matches the
 * rule, we can parse it into attributes
 * 
 * @author Joel Dodge, Tom Zhang
 */
public class Rule {

	private static String doseRegex = "((?:\\d|ONE|TWO|THREE|FOUR|FIVE|SIX|SEVEN|EIGHT|NINE|TEN).*?)";
	private static final String routeRegex = "((?:BY|\\w+lly\\b|IN).*?)";
	private static final String instrRegex = "((?:WITH|THE).*)";
	private static final String freqRegex = "((?:EVERY|DAILY|ONCE|TWICE|IN THE|\\d\\b|ONE|TWO|THREE|FOUR|FIVE|SIX|SEVEN|EIGHT|NINE|TEN).*?)";
	private static final String reasonRegex = "(FOR.*)";
	private static final String durationRegex = "((?:UNTIL|FOR.*?(?:\\d+|ONE|TWO|THREE|FOUR|FIVE|SIX|SEVEN|EIGHT|NINE|TEN)).*)";
	private static final String warningRegex = "(AVOID.*)";

	private static Map<DosageAttr, String> attrExprs;

	static {
		attrExprs = new HashMap<DosageAttr, String>();
		attrExprs.put(DosageAttr.DOSE, doseRegex);
		attrExprs.put(DosageAttr.ROUTE, routeRegex);
		attrExprs.put(DosageAttr.INSTRUCTIONS, instrRegex);
		attrExprs.put(DosageAttr.FREQUENCY, freqRegex);
		attrExprs.put(DosageAttr.REASON, reasonRegex);
		attrExprs.put(DosageAttr.DURATION, durationRegex);
		attrExprs.put(DosageAttr.WARNINGS, warningRegex);
	}

	private Pattern rule;
	private DosageAttr[] attrs;

	public Rule(DosageAttr[] attrs) {
		this.attrs = attrs;
		StringBuffer buf = new StringBuffer();
		buf.append("(?i)"); // Case insensitive flag
		buf.append(".*?"); // Ignore any unnecessary characters at beginning
							// of dosage (ex. "Take")
		for (int i = 0; i < attrs.length; i++) {
			buf.append(attrExprs.get(attrs[i]));
			if (i != attrs.length - 1)
				buf.append(' ');
		}
		rule = Pattern.compile(buf.toString());
	}

	public Dosage matchRule(String dosageText) {
		Dosage dosage = new Dosage();
		Matcher m = rule.matcher(dosageText);
		if (m.matches()) {
			for (int i = 0; i < attrs.length; i++) {
				attrs[i].set(dosage, m.group(i + 1));
			}
			return dosage;
		} else {
			return null;
		}
	}
}
