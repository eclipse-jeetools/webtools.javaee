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
package org.eclipse.jst.j2ee.commonarchivecore;



import java.util.List;



/**
 * Insert the type's description here. Creation date: (03/19/01 3:31:53 PM)
 * 
 * @author: Administrator
 */
public class XmlValidationResult {
	protected ModuleFile archive;
	protected List caughtExceptions;

	/**
	 * XmlValidationResult constructor comment.
	 */
	public XmlValidationResult() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (03/19/01 3:34:45 PM)
	 * 
	 * @return com.ibm.etools.commonarchive.ModuleFile
	 */
	public ModuleFile getArchive() {
		return archive;
	}

	/**
	 * Insert the method's description here. Creation date: (03/19/01 3:34:45 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List getCaughtExceptions() {
		return caughtExceptions;
	}

	/**
	 * Insert the method's description here. Creation date: (03/19/01 3:34:45 PM)
	 * 
	 * @param newArchive
	 *            com.ibm.etools.commonarchive.ModuleFile
	 */
	public void setArchive(ModuleFile newArchive) {
		archive = newArchive;
	}

	/**
	 * Insert the method's description here. Creation date: (03/19/01 3:34:45 PM)
	 * 
	 * @param newCaughtExceptions
	 *            java.util.List
	 */
	public void setCaughtExceptions(java.util.List newCaughtExceptions) {
		caughtExceptions = newCaughtExceptions;
	}
}
