package org.eclipse.jem.internal.beaninfo.ui;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BPListElement.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:20:50 $ 
 */


import org.eclipse.jem.internal.beaninfo.adapters.IBeaninfosDocEntry;
/**
 * A Base ListElement (i.e. element in a list viewer) for
 * a BeanInfo Search Path Entry.
 */

public abstract class BPListElement extends Object {
	protected IBeaninfosDocEntry entry;
	protected boolean missing; // Library/folder/classpath entry for this entry could not be found.

	public BPListElement(IBeaninfosDocEntry entry, boolean missing) {
		this.entry = entry;
		this.missing = missing;
	}

	public IBeaninfosDocEntry getEntry() {
		return entry;
	}

	public boolean isMissing() {
		return missing;
	}

	/**
	 * Certain entries cannot have their export status changed.
	 */
	public abstract boolean canExportBeChanged();

	/**
	 * Return whether this entry is exported.
	 */
	public abstract boolean isExported();

	/**
	 * If export can be changed, change it.
	 */
	public abstract void setExported(boolean exported);

}