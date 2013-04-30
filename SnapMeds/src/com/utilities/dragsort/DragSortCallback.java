package com.utilities.dragsort;

/**
 * Simple callback interface used by DragSortCursorAdapter when items in the
 * list are modified. Use this when a DragSortListView needs persistent edits.
 * 
 * @author Matt Omori
 * 
 */
public interface DragSortCallback {

	/**
	 * Gets object at corresponding list position.
	 * 
	 * @param pos
	 *            the list position of the item being requested.
	 */
	public Object get(int pos);

	/**
	 * Adds an object to the end of the list.
	 * 
	 * @param obj
	 *            the object to be added.
	 */
	public void add(Object obj);

	/**
	 * Removes an object by its list position.
	 * 
	 * @param pos
	 *            the list position of the item being removed.
	 */
	public void remove(int pos);

	/**
	 * Moves an object from one position to another in the list.
	 * 
	 * @param to
	 *            the new list position of the item being reordered.
	 * @param from
	 *            the previous list position of the item being reordered.
	 */
	public void reorder(int to, int from);
}
