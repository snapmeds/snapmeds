package com.utilities;

import java.util.List;

import com.snapmeds.AndroidStorage;

/**
 * Storage abstract factory to aid with persistence. Accessed like a singleton
 * through getInstance().
 * 
 * @author Matt Omori
 * 
 */
public abstract class Storage {
	private static Storage instance = new AndroidStorage();

	/**
	 * Gets the current Storage object singleton factory.
	 * 
	 * @return current - instance of the Storage factory.
	 */
	public static Storage getInstance() {
		return instance;
	}

	/**
	 * Sets the Storage object instance.
	 * 
	 * @param newInstance
	 *            - the new Storage factory instance you want to use.
	 */
	public static void setInstance(Storage newInstance) {
		instance = newInstance;
	}

	/**
	 * Loads List of Prescriptions from storage
	 * 
	 * @return List<Prescription> - list of Prescriptions persisted
	 * @throws Exception
	 *             - information if loading doesn't work
	 */
	public abstract List<Prescription> load() throws Exception;

	/**
	 * Adds Prescription to storage
	 * 
	 * @param prescription
	 *            - the Prescription object being stored
	 * @throws Exception
	 *             - information if adding doesn't work
	 */
	public abstract void add(Prescription prescription) throws Exception;

	/**
	 * Edits the Prescription in storage
	 * 
	 * @param prescription
	 *            - the Prescription being edited
	 * @throws Exception
	 *             - information if editing isn't successful
	 */
	public abstract void editPrescription(Prescription prescription)
			throws Exception;

	/**
	 * Saves a list in Storage, overwriting whatever was there previously.
	 * 
	 * @param list
	 *            - the new list to save
	 * @throws Exception
	 *             - information if saving isn't successful
	 */
	public abstract void save(List<Prescription> list) throws Exception;
}
