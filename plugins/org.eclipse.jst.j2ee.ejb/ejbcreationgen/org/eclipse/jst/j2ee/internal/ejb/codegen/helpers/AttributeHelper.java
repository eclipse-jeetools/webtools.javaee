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
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;


/**
 * Insert the type's description here. Creation date: (10/10/00 12:45:13 PM)
 * 
 * @author: Steve Wasleski
 */
public class AttributeHelper extends FeatureHelper {
	static final String NULL_STRING = "null";//$NON-NLS-1$
	private String fTypeName = null;
	private int fArrayDimensions = 0;
	private String fInitializer = null;
	private boolean fGenerateAccessors = false;
	private boolean fRemote = false;
	private boolean fLocal = false;

	/**
	 * PropertyHelper constructor comment.
	 * 
	 * @param aMetaObject
	 *            org.eclipse.emf.ecore.EObject
	 */
	public AttributeHelper(EObject aMetaObject) {
		super(aMetaObject);
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return int
	 */
	public int getArrayDimensions() {
		return fArrayDimensions;
	}

	public CMPAttribute getAttribute() {
		return (CMPAttribute) getMetaObject();
	}

	public int getAttributeIndex() {
		// TODO This could be better shared if we moved the impl down to J2EE libs
		CMPAttribute attr = (CMPAttribute) getMetaObject();
		return ((ContainerManagedEntity) getEjb()).getPersistentAttributes().indexOf(attr);
	}

	/**
	 * Returns the type name with array decorations.
	 */
	public String getGenerationTypeName() {
		if (getTypeName() == null)
			return null;

		StringBuffer genType = new StringBuffer(getTypeName());
		for (int i = 0; i < getArrayDimensions(); i++) {
			genType.append("[]");//$NON-NLS-1$
		}
		return genType.toString();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInitializer() {
		return fInitializer;
	}

	public CMPAttribute getOldAttribute() {
		if (getMetadataHistory().getOldMetadata() == null)
			return null;
		return (CMPAttribute) getMetadataHistory().getOldMetadata();
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/00 1:14:20 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getOldGenerationTypeName() {
		if (isDelete())
			return getTypeName();
		return ((FeatureHistory) getMetadataHistory()).getTypeName();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTypeName() {
		if (fTypeName == null && getAttribute() != null) {
			JavaHelpers type = getAttribute().getType();
			if (type != null)
				setTypeName(type.getQualifiedName());
		}
		return fTypeName;
	}

	public boolean isAddingKey() {
		CMPAttribute old = getOldAttribute();
		if (old == null)
			return true;
		return !old.isKey() && getAttribute().isKey();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return int
	 */
	public boolean isArray() {
		return getArrayDimensions() > 0;
	}

	public boolean isAttributeHelper() {
		return true;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return boolean
	 */
	public boolean isGenerateAccessors() {
		return fGenerateAccessors;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 2:19:59 PM)
	 * 
	 * @return boolean
	 */
	public boolean isRemote() {
		return fRemote;
	}

	public boolean isLocal() {
		return fLocal;
	}

	public boolean isRemovingKey() {
		CMPAttribute old = getOldAttribute();
		return old != null && old.isKey() && getAttribute().isKey();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newArrayDimensions
	 *            int
	 */
	public void setArrayDimensions(int newArrayDimensions) {
		fArrayDimensions = newArrayDimensions;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newGenerateAccessors
	 *            boolean
	 */
	public void setGenerateAccessors(boolean newGenerateAccessors) {
		fGenerateAccessors = newGenerateAccessors;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newInitializer
	 *            java.lang.String
	 */
	public void setInitializer(java.lang.String newInitializer) {
		fInitializer = newInitializer;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 2:19:59 PM)
	 * 
	 * @param newRemote
	 *            boolean
	 */
	public void setRemote(boolean newRemote) {
		fRemote = newRemote;
	}

	public void setLocal(boolean newLocal) {
		fLocal = newLocal;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newTypeName
	 *            java.lang.String
	 */
	public void setTypeName(java.lang.String newTypeName) {
		fTypeName = newTypeName;
	}
}