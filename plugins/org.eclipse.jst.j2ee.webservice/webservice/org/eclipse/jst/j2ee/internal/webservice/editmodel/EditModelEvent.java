/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.editmodel;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

public class EditModelEvent {
	//These are the event codes.

	// Used when the edit model is saved.
	public static final int SAVE = 1;
	// Used when the command stack becomes dirty.
	public static final int DIRTY = 2;
	// Used when a referenced resource is removed from the ResourceSet.
	public static final int REMOVED_RESOURCE = 3;
	// Used when a referenced resource is added to the ResourceSet.
	public static final int ADDED_RESOURCE = 4;
	// Used when the edit model is disposed
	public static final int PRE_DISPOSE = 5;
	// Used when a Resource is loaded or the first object
	// is added to the contents when created.
	public static final int LOADED_RESOURCE = 6;
	// Used when a Resource is unloaded.
	public static final int UNLOADED_RESOURCE = 7;

	private int fEventCode;
	private EditModel fEditModel;
	private List fChangedResources;

	/**
	 * Insert the method's description here. Creation date: (4/12/2001 2:46:59 PM)
	 */
	public EditModelEvent(int anEventCode, EditModel model) {
		setEventCode(anEventCode);
		setEditModel(model);
	}

	public void addResource(Resource aResource) {
		if (aResource != null)
			getChangedResources().add(aResource);
	}

	public void addResources(Collection someResources) {
		if (someResources != null)
			getChangedResources().addAll(someResources);
	}

	/**
	 * Insert the method's description here. Creation date: (4/12/2001 2:46:43 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List getChangedResources() {
		if (fChangedResources == null)
			fChangedResources = new ArrayList();
		return fChangedResources;
	}

	/**
	 * Insert the method's description here. Creation date: (05/21/01 9:01:08 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.workbench.AbstractEditModel
	 */
	public EditModel getEditModel() {
		return fEditModel;
	}

	/**
	 * Insert the method's description here. Creation date: (4/12/2001 2:46:43 PM)
	 * 
	 * @return int
	 */
	public int getEventCode() {
		return fEventCode;
	}

	/**
	 * Insert the method's description here. Creation date: (4/12/2001 2:46:43 PM)
	 * 
	 * @param newChangedResources
	 *            java.util.List
	 */
	public void setChangedResources(java.util.List newChangedResources) {
		fChangedResources = newChangedResources;
	}

	public void setEditModel(EditModel newEditModel) {
		fEditModel = newEditModel;
	}

	public void setEventCode(int newEventCode) {
		fEventCode = newEventCode;
	}
}