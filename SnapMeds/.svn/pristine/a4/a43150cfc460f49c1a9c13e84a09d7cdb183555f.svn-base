package com.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;

import com.utilities.Drug;

/**
 * Queryer is an abstract class for pulling drug objects from a database. It
 * supports getting drugs from ndcs or name
 */
abstract public class Queryer {

	public JSONObject getSPLsFromNDC(String ndc) {
		return getJSONfromURL(getSPLsURLFromNDC(ndc));
	}

	public JSONObject getSPLsFromDrugname(String drugname) {
		return getJSONfromURL(getSPLsURLFromDrugname(drugname));
	}

	public JSONObject getImprintDataFromNDC(String ndc) {
		return getJSONfromURL(getImprintDataURLFromNDC(ndc));
	}

	public abstract Drug getDrugFromNDC(String ndc);

	abstract protected String getSPLsURLFromNDC(String ndc);

	abstract protected String getImprintDataURLFromNDC(String ndc);

	abstract protected String getSPLsURLFromDrugname(String name);

	protected JSONObject getJSONfromURL(String url) {
		JSONObject json = null;
		try {
			String jsonString = readURL(url);
			if (jsonString != null) {
				json = new JSONObject(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	protected String readURL(String urlString) {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			String read;
			while ((read = reader.readLine()) != null)
				buffer.append(read);

			return buffer.toString();
		} catch (Exception e) {
			return null;
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public abstract List<Drug> getDrugsFromNDC(String ndc);
}
