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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
/**
 * Insert the type's description here.
 * Creation date: (10/26/2001 9:24:27 AM)
 * @author: Administrator
 */
public interface CommonRelationship extends EObject {
public EList getCommonRoles();
public CommonRelationshipRole getFirstCommonRole();
public CommonRelationshipRole getSecondCommonRole();
public String getName();
public void setName(String value);
}



