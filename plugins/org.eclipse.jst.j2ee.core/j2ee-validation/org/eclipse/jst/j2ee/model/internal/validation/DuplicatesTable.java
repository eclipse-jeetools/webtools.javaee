/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * In some places in the code, it is necessary to find out if there
 * are duplicates in a list, and if there are, to register a validation
 * message against the duplicates. This class is used to make finding
 * the duplicates easier.
 */
public class DuplicatesTable extends java.util.HashMap {
	protected static final Boolean UNIQUE = Boolean.FALSE;
	protected static final Boolean DUPLICATE = Boolean.TRUE;
	protected Map _duplicates = null;

	/**
	 * Add an object to this table; if the object is a duplicate, it will be
	 * marked to a duplicate instead of being added twice to the table.
	 */
	public void add(Object o) {
		// Build up hashtable to check for duplicate elements.
		Object key = get(o);
		if (key == null) {
			put(o, UNIQUE);
		}
		else {
			saveDuplicate(o);
			put(o, DUPLICATE);
		}
	}

	protected void saveDuplicate(Object o) {
		// The given object has been marked a duplicate.
		// Given that the HashMap can store only one object with the key, 
		// duplicates are overwritten. This method stores the duplicates
		// elsewhere in a List.
		if (_duplicates == null) {
			_duplicates = new HashMap();
		}

		List dupList = (List) _duplicates.get(o);
		if (dupList == null) {
			dupList = new ArrayList();
		}

		dupList.add(o);
		_duplicates.put(o, dupList);
	}

	/**
	 * If there are duplicates in this table, return true; else, return false.
	 */
	public boolean containsDuplicates() {
		return containsValue(DUPLICATE);
	}
	
	/**
	 * If there are unique elements in this table, return true; else, return false.
	 */
	public boolean containsUniques() {
		return containsValue(UNIQUE);
	}
	
	/**
	 * If there are duplicates in this table, return a set of the duplicates.
	 * Otherwise, return an empty set.
	 */
	public List getDuplicates() {
		if (!containsDuplicates()) {
			return Collections.EMPTY_LIST;
		}

		// Else, get the duplicate elements from the list
		return getElements(DUPLICATE);
	}
	
	/**
	 * If there are duplicates in this table, return a list of the duplicates.
	 * Otherwise, return an empty list.
	 */
	private List getElements(Boolean type) {
		if (type == null) {
			return Collections.EMPTY_LIST;
		}

		// Else, get the duplicate elements from the list
		Set keys = keySet();
		if (keys == null) {
			return Collections.EMPTY_LIST;
		}

		List elements = new ArrayList();
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			Object value = get(key);
			if (value == type) {
				elements.add(key);
			}
		}

		if (type == DUPLICATE) {
			// Also add the elements from the "duplicates" list.
			if (_duplicates != null) {
				iterator = _duplicates.values().iterator();
				while (iterator.hasNext()) {
					List dupList = (List) iterator.next();
					elements.addAll(dupList);
				}
			}
		}

		return elements;
	}
	
	/**
	 * If there are unique elements in this table, return a set of the unique elements.
	 * Otherwise, return an empty set.
	 */
	public List getUniques() {
		if (!containsUniques()) {
			return Collections.EMPTY_LIST;
		}

		// Else, get the unique elements from the list
		return getElements(UNIQUE);
	}

	public void clear() {
		super.clear();
		if(_duplicates != null) {
			_duplicates.clear();
		}
	}
}
