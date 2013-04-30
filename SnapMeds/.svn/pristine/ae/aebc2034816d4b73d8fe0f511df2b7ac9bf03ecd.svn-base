package com.query;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.utilities.Drug;

/**
 * DailyMedQueryer is a query class that extends queryer It is designed to pull
 * drugs from a database based off of the dailyMed website and setids.
 * 
 */
public class DailyMedQueryer extends Queryer {

	// indices used for accessing fields from json
	final int ADVERSE_REACTIONS = 0;
	final int BOX_WARNINGS = 1;
	final int CONFLICTING_CONDITIONS = 2;
	final int GENERIC_NAMES = 3;
	final int MEDICATION_GUIDE = 4;
	final int NAME = 5;
	final int PRECAUTIONS = 6;
	final int USES = 7;
	final int WARNINGS = 8;
	final int SETID = 9;

	/**
	 * Returns of all possible drugs from an ndc (checks all variants of
	 * hyphens)
	 * 
	 * @param ndc
	 *            the National Drug Code to search from
	 * @return a list of Drugs with the NDC.
	 * @see Drug
	 */
	@Override
	public List<Drug> getDrugsFromNDC(String ndc) {
		LinkedList<Drug> drugs = new LinkedList<Drug>();
		ndc = ndc.replace("-", "");

		String[] ndcs = new String[3];
		if (ndc.length() < 10) {
			return drugs;
		}
		ndcs[0] = ndc.substring(0, 4) + "-" + ndc.substring(4, 8) + "-"
				+ ndc.substring(8);
		ndcs[1] = ndc.substring(0, 5) + "-" + ndc.substring(5, 8) + "-"
				+ ndc.substring(8);
		ndcs[2] = ndc.substring(0, 5) + "-" + ndc.substring(5, 9) + "-"
				+ ndc.substring(9);

		for (int i = 0; i < 3; i++) {
			Drug drug = getDrugFromNDC(ndcs[i]);
			if (drug != null && drug.isPopulated()) {
				drugs.add(drug);
			}
		}
		return drugs;
	}

	/**
	 * Checks database for drug with that ndc. If it does not exist, find the
	 * setid from Dailymed webpage, put it in the database, and get the drug.
	 * 
	 * @param ndcWithHyphens
	 *            the National Drug Code with hyphens
	 * 
	 * @return the {@link Drug} object corresponding to the NDC
	 */
	public Drug getDrugFromNDC(String ndcWithHyphens) {
		Drug drug = new Drug();
		// call our database, if does not exist, then put it in
		JSONArray json = getDrugJSONFromNDC(ndcWithHyphens);
		try {
			if (json == null
					|| ((String) ((JSONObject) json.get(NAME)).get("name"))
							.isEmpty()) {
				String setid = getSetIDFromNDC(ndcWithHyphens); // call Dailymed
																// webpage
				if (setid == null) {
					return drug;
				}
				setNDCSetIDTable(ndcWithHyphens, setid);
				json = getDrugJSONFromNDC(ndcWithHyphens);
			}
		} catch (JSONException e) {
			// do nothing
		}
		populateDrug(drug, json);
		return drug.isPopulated() ? drug : null;
	}

	/**
	 * getDrugsFromName pulls all drugs from the database that have a name like
	 * the name being searched
	 * 
	 * @param name
	 *            The name being searched
	 * @return a list of drugs from the search
	 */
	public List<Drug> getDrugsFromName(String name) {
		LinkedList<Drug> drugs = new LinkedList<Drug>();
		JSONArray drugsJson = getDrugsJSONFromName(name);

		if (drugsJson != null) {
			for (int i = 0; i < drugsJson.length(); i++) {
				Drug drug = new Drug();
				JSONArray drugJson;
				try {
					drugJson = drugsJson.getJSONArray(i);
					populateDrug(drug, drugJson);
					if (drug.isPopulated()) {
						drugs.add(drug);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return drugs;
	}

	protected JSONArray getDrugsJSONFromName(String name) {
		String get = "?username=" + getDatabaseUsername() + "&password="
				+ getDatabasePassword() + "&name=" + name;
		String url = getGetDatabaseURL();
		return getJSONFromURL(url + get);
	}

	protected JSONArray getDrugJSONFromNDC(String ndc) {
		String get = "?username=" + getDatabaseUsername() + "&password="
				+ getDatabasePassword() + "&ndc=" + ndc;
		String url = getGetDatabaseURL();
		JSONArray json = null;
		// This will only return a single Drug, so just get the first Drug
		try {
			json = getJSONFromURL(url + get).getJSONArray(0);
		} catch (JSONException e) {
			try {
				json = new JSONArray("[]");
			} catch (JSONException e1) {
				return json;
			}
		}
		return json;

	}

	protected JSONArray getJSONFromURL(String url) {
		JSONArray json = null;
		String urljson = "";
		try {
			urljson = readURL(url);
			if (urljson != null) {
				json = new JSONArray(urljson);
			}
		} catch (JSONException e) {
			try {
				json = new JSONArray("[[]]");
			} catch (JSONException e1) {
				json = null;
			}
		}
		return json;
	}

	protected void setNDCSetIDTable(String ndc, String setid) {
		String get = "?username=" + getDatabaseUsername() + "&password="
				+ getDatabasePassword() + "&ndc=" + ndc + "&setid=" + setid;
		readURL(getAddToDatabaseURL() + get);
	}

	protected String getSetIDFromNDC(String ndc) {
		String setid = null;
		try {
			JSONObject json = getSPLsFromNDC(ndc);
			JSONArray data = (JSONArray) json.get("DATA");
			JSONArray first = (JSONArray) data.get(0);
			setid = (String) first.get(0);
		} catch (Exception e) {
			// do nothing
		}
		return setid;
	}

	protected void populateDrug(Drug drug, JSONArray json) {
		try {
			drug.setAdverseReactions(((JSONObject) json.get(ADVERSE_REACTIONS))
					.getString("adversereactions"));
			drug.setBoxWarnings(((JSONObject) json.get(BOX_WARNINGS))
					.getString("boxwarnings"));
			drug.setConflictingConditions(((JSONObject) json
					.get(CONFLICTING_CONDITIONS))
					.getString("conflictingconditions"));
			drug.setGenericNames(((JSONObject) json.get(GENERIC_NAMES))
					.getString("genericnames"));
			drug.setMedicationGuide(((JSONObject) json.get(MEDICATION_GUIDE))
					.getString("medicationguide"));
			drug.setName(((JSONObject) json.get(NAME)).getString("name"));
			drug.setPrecautions(((JSONObject) json.get(PRECAUTIONS))
					.getString("precautions"));
			drug.setUses(((JSONObject) json.get(USES)).getString("uses"));
			drug.setWarnings(((JSONObject) json.get(WARNINGS))
					.getString("warnings"));
			drug.setSetID(((JSONObject) json.get(SETID)).getString("setid"));
		} catch (Exception e) {
			// do nothing
		}
	}

	@Override
	protected String getSPLsURLFromNDC(String ndc) {
		return "http://dailymed.nlm.nih.gov/dailymed/services/v1/ndc/" + ndc
				+ "/spls.json";
	}

	@Override
	protected String getImprintDataURLFromNDC(String ndc) {
		return "http://dailymed.nlm.nih.gov/dailymed/services/v1/ndc/" + ndc
				+ "/imprintdata.json";
	}

	@Override
	protected String getSPLsURLFromDrugname(String name) {
		return "http://dailymed.nlm.nih.gov/dailymed/services/v1/drugname/"
				+ name + "/spls.json";
	}

	protected String getDatabaseUsername() {
		return "restful";
	}

	protected String getDatabasePassword() {
		return "api";
	}

	protected String getGetDatabaseURL() {
		return "http://ec2-54-214-20-135.us-west-2.compute.amazonaws.com/getFromDatabase.php";
	}

	protected String getAddToDatabaseURL() {
		return "http://ec2-54-214-20-135.us-west-2.compute.amazonaws.com/addToDatabase.php";
	}
}
