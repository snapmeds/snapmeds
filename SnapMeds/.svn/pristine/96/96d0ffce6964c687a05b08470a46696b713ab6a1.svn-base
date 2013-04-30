package com.snapmeds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.utilities.Prescription;
import com.utilities.Storage;

/**
 * Works with Android memory to load/save data.
 * 
 * @author Onur Karaman
 * @author Zach Wand
 * @author Matt Omori
 * 
 */
public class AndroidStorage extends Storage {
	private static Context context;
	private static final String PRESCRIPTION_KEY = "snapmeds storage key";

	public static void setContext(Context c) {
		context = c;
	}

	/**
	 * Loads the serialized object into memory from Android's shared
	 * preferences.
	 * 
	 * @return the list of prescriptions in the user's cabinet
	 * @throws IOException
	 *             information about a failure reading from memory
	 * @see com.utilities.Storage#load()
	 */
	@Override
	public List<Prescription> load() throws IOException {
		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		String prescriptionSerialized = settings.getString(PRESCRIPTION_KEY,
				null);

		List<Prescription> prescriptions = new ArrayList<Prescription>();

		if (prescriptionSerialized == null) {
			return prescriptions;
		}

		ObjectMapper serializer = new ObjectMapper();
		JavaType prescriptionListType = serializer.getTypeFactory()
				.constructCollectionType(List.class, Prescription.class);

		try {
			prescriptions = serializer.readValue(prescriptionSerialized,
					prescriptionListType);
			prescriptions = prescriptions != null ? prescriptions
					: new ArrayList<Prescription>();
		} catch (JsonMappingException e) {
			save(new ArrayList<Prescription>());
		}
		return prescriptions;
	}

	/**
	 * Adds a prescription to the list and saves it.
	 * 
	 * @param prescription
	 *            the prescription to add to the cabinet
	 * @throws IOException
	 *             information about a failure writing to memory
	 * @see com.utilities.Storage#add(com.utilities.Prescription)
	 */
	@Override
	public void add(Prescription prescription) throws IOException {
		List<Prescription> prescriptions = load();
		prescriptions.add(prescription);

		write(prescriptions);
	}

	/**
	 * Saves over a prescription that was previously in the cabinet.
	 * 
	 * @param prescription
	 *            the prescription to update in the cabinet
	 * @throws IOException
	 *             information about a failure writing to memory
	 * @see com.utilities.Storage#editPrescription(com.utilities.Prescription)
	 */
	@Override
	public void editPrescription(Prescription prescription) throws IOException {
		List<Prescription> prescriptions = load();
		for (int i = 0; i < prescriptions.size(); i++) {
			if (prescriptions.get(i).sameDrug(prescription)) {
				prescriptions.set(i, prescription);
			}
		}

		write(prescriptions);
	}

	/**
	 * Saves a new cabinet list, overwriting the current one if it exists.
	 * 
	 * @param list
	 *            the list to save as the new cabinet
	 * @throws IOException
	 *             information about a failure writing to memory
	 * @see com.utilities.Storage#save(java.util.List)
	 */
	@Override
	public void save(List<Prescription> list) throws IOException {
		write(list);
	}

	/**
	 * Writes list of Prescriptions to the activity's persistence apply function
	 * is used because it allows writing to occur asynchronously.
	 * 
	 * @param activity
	 *            the activity to write the Prescriptions to
	 * @param prescriptions
	 *            the list of Prescriptions to write
	 * @throws IOException
	 *             information about a failure writing to memory
	 */
	private static void write(List<Prescription> prescriptions)
			throws IOException {
		ObjectMapper serializer = new ObjectMapper();
		String serializedList = serializer.writeValueAsString(prescriptions);

		SharedPreferences settings = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(PRESCRIPTION_KEY, serializedList);
		editor.apply();
	}
}
