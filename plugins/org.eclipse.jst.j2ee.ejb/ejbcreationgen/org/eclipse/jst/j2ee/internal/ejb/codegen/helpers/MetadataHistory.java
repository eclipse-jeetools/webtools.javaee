/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;



import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.emf.utilities.ExtendedEcoreUtil;


/**
 * Insert the type's description here. Creation date: (11/14/2000 12:18:54 PM)
 * 
 * @author: Administrator
 */
public class MetadataHistory {
	private java.lang.String name;
	private EObject oldMetadata;

	/**
	 * MetadataHistory constructor comment.
	 */
	public MetadataHistory() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:19:30 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getName() {
		return name;
	}

	protected String getName(EObject aRefObject) {
		return ExtendedEcoreUtil.getName(aRefObject);
	}

	/**
	 * Insert the method's description here. Creation date: (2/18/2001 7:16:27 PM)
	 * 
	 * @return org.eclipse.emf.ecore.EObject
	 */
	public org.eclipse.emf.ecore.EObject getOldMetadata() {
		return oldMetadata;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:19:30 PM)
	 * 
	 * @param newName
	 *            java.lang.String
	 */
	public void setName(java.lang.String newName) {
		name = newName;
	}

	/**
	 * Insert the method's description here. Creation date: (2/18/2001 7:16:27 PM)
	 * 
	 * @param newOldMetadata
	 *            org.eclipse.emf.ecore.EObject
	 */
	public void setOldMetadata(org.eclipse.emf.ecore.EObject newOldMetadata) {
		oldMetadata = newOldMetadata;
		if (oldMetadata != null)
			storeHistory(newOldMetadata);
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:30:23 PM)
	 * 
	 * @param anObject
	 *            EObject
	 */
	public void storeHistory(EObject anObject) {
		setName(getName(anObject));
	}
}