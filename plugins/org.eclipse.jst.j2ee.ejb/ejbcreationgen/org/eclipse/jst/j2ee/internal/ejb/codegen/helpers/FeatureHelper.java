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
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Insert the type's description here. Creation date: (9/1/2000 1:38:53 PM)
 * 
 * @author: Administrator
 */
public class FeatureHelper extends EJBGenerationHelper {
	protected String fName = null;
	protected boolean fKey = false;

	/**
	 * PersistentFeatureCodegenHelper constructor comment.
	 */
	public FeatureHelper(EObject aMetaObject) {
		super(aMetaObject);
	}

	public EStructuralFeature getFeature() {
		return (EStructuralFeature) getMetaObject();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:17:02 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getName() {
		if (fName == null && getFeature() != null)
			fName = getFeature().getName();
		return fName;
	}

	public boolean isAttributeHelper() {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 11:02:10 PM)
	 * 
	 * @return boolean
	 */
	public boolean isKey() {
		return fKey;
	}

	public boolean isPersistentFeatureHelper() {
		return true;
	}

	public boolean isRoleHelper() {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 11:02:10 PM)
	 * 
	 * @param newKey
	 *            boolean
	 */
	public void setKey(boolean newKey) {
		fKey = newKey;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:17:02 PM)
	 * 
	 * @param newName
	 *            java.lang.String
	 */
	public void setName(String newName) {
		fName = newName;
	}
}