package com.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.utilities.dragsort.DragSortCallback;

/**
 * Singleton used across application to access medicine cabinet.
 * 
 * @author Matt Omori
 * 
 */
public class MedicineCabinet implements DragSortCallback,
		Iterable<Prescription> {
	private static final MedicineCabinet instance = new MedicineCabinet();
	private List<Prescription> list;
	private boolean listChanged;

	private MedicineCabinet() {
		list = new ArrayList<Prescription>();
		listChanged = false;
		load();
	}

	public static MedicineCabinet getInstance() {
		return instance;
	}

	/**
	 * Gets cabinet list from Storage instance.
	 */
	public void load() {
		try {
			list = Storage.getInstance().load();
		} catch (Exception e) {
			list = new ArrayList<Prescription>();
		}
	}

	/**
	 * Uses Storage instance to save cabinet list.
	 */
	public void save() {
		if (!listChanged) {
			return;
		}

		try {
			Storage.getInstance().save(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		listChanged = false;
	}

	@Override
	public Iterator<Prescription> iterator() {
		return list.iterator();
	}

	/**
	 * Gets the number of prescriptions in the user's cabinet.
	 * 
	 * @return number of prescriptions in user's cabinet
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Gets a prescription from the cabinet by its position in the list.
	 * 
	 * @param pos
	 *            the position in the list to retrieve from
	 * @return the prescription at the list position
	 */
	public Object get(int pos) {
		return list.get(pos);
	}

	/**
	 * Gets a prescription from the cabinet by its drug's setID.
	 * 
	 * @param setId
	 *            the drug to search for
	 * @return the prescription prescribing the drug with the matching setID
	 */
	public Prescription get(String setId) {
		for (Prescription prescription : list) {
			String currentSetId = prescription.getDrug().getSetID();
			if (currentSetId.equals(setId)) {
				return prescription;
			}
		}
		return null;
	}

	/**
	 * Permanently adds a prescription to the cabinet.
	 * 
	 * @param obj
	 *            the Prescription to add
	 * @see DragSortCallback#add(Object)
	 */
	public void add(Object obj) {
		if (obj == null) {
			return;
		}
		if (!(obj instanceof Prescription)) {
			return;
		}
		try {
			list.add((Prescription) obj);
			Storage.getInstance().save(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permanently removes a prescription from the cabinet by its position in
	 * the list.
	 * 
	 * @param pos
	 *            the position to remove in the cabinet list
	 * @see DragSortCallback#remove(int)
	 */
	public void remove(int pos) {
		listChanged = true;
		try {
			list.remove(pos);
			Storage.getInstance().save(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permanently removes a prescription from the cabinet.
	 * 
	 * @param prescription
	 *            the prescription to remove.
	 */
	public void remove(Prescription prescription) {
		try {
			list.remove(prescription);
			Storage.getInstance().save(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permanently reorders two prescriptions in the cabinet list.
	 * 
	 * @param to
	 *            the new position of the prescription
	 * @param from
	 *            the old position of the prescription
	 * @see DragSortCallback#reorder(int, int)
	 */
	public void reorder(int to, int from) {
		if (to == from) {
			return;
		}
		listChanged = true;
		Prescription p = list.get(from);
		if (from < to) {
			list.add(to + 1, p);
			list.remove(from);
		} else {
			list.add(to, p);
			list.remove(from + 1);
		}
	}

	/**
	 * Determines whether or not a drug is in a user's medicine cabinet.
	 * 
	 * @param setId
	 *            the setID of the requested drug
	 * @return whether or not the drug exists in the cabinet
	 */
	public boolean inCabinet(String setId) {
		return get(setId) != null;
	}

	/**
	 * Gets a reference to the list of prescriptions in the medicine cabinet.
	 * Should be treated as an immutable list so that MedicineCabinet can handle
	 * manipulation and storage.
	 * 
	 * @return a reference to the medicine cabinet list
	 */
	public List<Prescription> getList() {
		return list;
	}

}
