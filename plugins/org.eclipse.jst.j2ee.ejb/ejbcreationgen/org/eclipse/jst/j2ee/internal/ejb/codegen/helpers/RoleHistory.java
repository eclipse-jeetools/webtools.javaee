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



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;


/**
 * Insert the type's description here. Creation date: (11/14/2000 12:22:13 PM)
 * 
 * @author: Administrator
 */
public class RoleHistory extends FeatureHistory {
	private java.util.List fieldNames;
	private java.lang.String oppositeRoleName;
	private boolean navigable;
	private boolean forward;
	private boolean flat = true;

	/**
	 * RoleHistory constructor comment.
	 */
	public RoleHistory() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:23:12 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List getFieldNames() {
		if (fieldNames == null)
			fieldNames = new ArrayList();
		return fieldNames;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:23:57 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getOppositeRoleName() {
		return oppositeRoleName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/16/00 6:16:27 PM)
	 * 
	 * @return boolean
	 */
	public boolean isFlat() {
		return flat;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:41:50 PM)
	 * 
	 * @return boolean
	 */
	public boolean isForward() {
		return forward;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:24:23 PM)
	 * 
	 * @return boolean
	 */
	public boolean isNavigable() {
		return navigable;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:43:55 PM)
	 * 
	 * @param aRole
	 *            org.eclipse.emf.ecore.EReference
	 */
	public void setFieldNames(CommonRelationshipRole aRole) {
		List fields = aRole.getAttributes();
		Iterator it = fields.iterator();
		while (it.hasNext())
			getFieldNames().add(((CMPAttribute) it.next()).getName());
	}

	/**
	 * Insert the method's description here. Creation date: (11/16/00 6:16:27 PM)
	 * 
	 * @param newFlat
	 *            boolean
	 */
	public void setFlat(boolean newFlat) {
		flat = newFlat;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:41:50 PM)
	 * 
	 * @param newForward
	 *            boolean
	 */
	public void setForward(boolean newForward) {
		forward = newForward;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:24:23 PM)
	 * 
	 * @param newNavigable
	 *            boolean
	 */
	public void setNavigable(boolean newNavigable) {
		navigable = newNavigable;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:42:49 PM)
	 * 
	 * @param aRole
	 *            org.eclipse.emf.ecore.EReference
	 */
	public void setOppositeRoleName(CommonRelationshipRole aRole) {
		CommonRelationshipRole opposite = aRole.getOppositeAsCommonRole();
		if (opposite != null)
			setOppositeRoleName(opposite.getName());
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:23:57 PM)
	 * 
	 * @param newOppositeRoleName
	 *            java.lang.String
	 */
	public void setOppositeRoleName(java.lang.String newOppositeRoleName) {
		oppositeRoleName = newOppositeRoleName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:30:23 PM)
	 * 
	 * @param anObject
	 *            EjbRelationshipRole
	 */
	public void storeHistory(CommonRelationshipRole aRole) {
		//setMultiplicity(aRole.getMultiplicity());
		if (aRole.getTypeEntity() != null)
			setTypeName(aRole.getTypeEntity().getName());
		setFieldNames(aRole);
		setOppositeRoleName(aRole);
		setNavigable(aRole.isNavigable());
		setForward(aRole.isForward());
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:30:23 PM)
	 * 
	 * @param anObject
	 *            EObject
	 */
	public void storeHistory(EObject anObject) {
		super.storeHistory(anObject);
		storeHistory((CommonRelationshipRole) anObject);
	}
}