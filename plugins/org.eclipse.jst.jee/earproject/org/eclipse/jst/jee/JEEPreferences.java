/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 26, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.jee;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Preferences;


/**
 * @author mdelder
 */
public class JEEPreferences {

	public interface Keys {
		
		

	}

	

	

	private Plugin owner = null;
	private Preferences preferences = null;
	private boolean persistOnChange = false;

	public JEEPreferences(Plugin owner) {
		this.owner = owner;
	}

	protected void initializeDefaultPreferences() {

		// placeholder
		// we had to move generate_dd to jst.j2ee as jst.jee is not in the pre-req of many plug-ins
	}

	

	

	
	public void firePreferenceChanged() {
		if (isPersistOnChange())
			persist();
	}

	public void persist() {
		getOwner().savePluginPreferences();
	}

	/**
	 * @return Returns the persistOnChange.
	 */
	public boolean isPersistOnChange() {
		return this.persistOnChange;
	}

	/**
	 * @param persistOnChange
	 *            The persistOnChange to set.
	 */
	public void setPersistOnChange(boolean persistOnChange) {
		this.persistOnChange = persistOnChange;
	}

	public Preferences getPreferences() {
		if (this.preferences == null)
			this.preferences = getOwner().getPluginPreferences();
		return this.preferences;
	}

	/**
	 * @return Returns the owner.
	 */
	private Plugin getOwner() {
		return this.owner;
	}
	
	/**
	 * Returns the current value of the boolean-valued property with the
	 * given name.
	 * The given name must not be <code>null</code>.
	 *
	 * @param name the name of the property
	 * @return the boolean-valued property
	 */
	public boolean getBoolean(String name) {
		return getPreferences().getBoolean(name);
	}

	/**
	 * Sets the current value of the boolean-valued property with the
	 * given name. The given name must not be <code>null</code>.
	 * @param name the name of the property
	 * @param value the new current value of the property
	 */
	public void setValue(String name, boolean value) {
		getPreferences().setValue(name, value);
		firePreferenceChanged();
	}

}
