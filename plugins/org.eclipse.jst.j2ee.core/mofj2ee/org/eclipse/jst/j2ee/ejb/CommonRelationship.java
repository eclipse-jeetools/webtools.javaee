/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
/**
 * Insert the type's description here.
 * Creation date: (10/26/2001 9:24:27 AM)
 * @author: Administrator
 * @since 1.0 */
public interface CommonRelationship extends EObject {
	/**
	 * Returns the EList of the common roles
	 * @return EList reference
	 */
	public EList getCommonRoles();
	
	/**
	 * Gets the first common role
	 * @return CommonRelationshipRole reference
	 */
	public CommonRelationshipRole getFirstCommonRole();
	
	/**
	 * Gets the second common role
	 * @return CommonRelationshipRole
	 */
	public CommonRelationshipRole getSecondCommonRole();
	
	/**
	 * Returns the name of the CommonRelationship
	 * @return String value
	 */
	public String getName();
	
	/**
	 * Sets the name of the CommonRelationship
	 * @param value the String value of the name
	 */
	public void setName(String value);
}



