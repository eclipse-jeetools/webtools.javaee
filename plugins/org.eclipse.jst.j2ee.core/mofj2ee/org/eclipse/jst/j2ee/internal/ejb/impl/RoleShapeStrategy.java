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
package org.eclipse.jst.j2ee.internal.ejb.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.IRoleShapeStrategy;


/**
 * Insert the type's description here.
 * Creation date: (11/6/2000 11:41:33 AM)
 * @author: Administrator
 */
public abstract class RoleShapeStrategy implements IRoleShapeStrategy {
	protected boolean busy = false;
	protected CommonRelationshipRole role;
	protected ArrayList visitedKeyTypes = new ArrayList(5);
/**
 * FlattenedRoleShapeStrategy constructor comment.
 */
public RoleShapeStrategy(CommonRelationshipRole aRole) {
	super();
	setRole(aRole);
}
protected String appendName(String fullName, String additionalName) {
	return fullName + ATTRIBUTE_NAME_JOINER + additionalName;
}
protected boolean canContinue() {
	return !busy && role.isForward() && role.getName() != null;
}
protected boolean canComputeNames() {
	//return getRole().getOpposite() != null;
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (11/16/00 9:19:27 PM)
 * @return CommonRelationshipRole
 */
public CommonRelationshipRole getRole() {
	return role;
}
/**
 * Insert the method's description here.
 * Creation date: (11/16/00 9:19:27 PM)
 * @return CommonRelationshipRole
 */

public boolean isFlat () {
	return false;
}
public boolean isRound () {
	return false;
}
/**
 * Creates an appended name while uppercasing the first letter of the secondName.
 */
public static String makeJavaName(String firstName, String secondName) {
	StringBuffer nameBuffer = new StringBuffer(firstName);
	nameBuffer.append(Character.toUpperCase(secondName.charAt(0)));
	for (int i = 1; i < secondName.length(); i++)
		nameBuffer.append(secondName.charAt(i));
	return nameBuffer.toString();
}
public void reconcileAttributes(List roleAttributes) {
	if (canContinue()) {
		busy = true;
		String attributeName = getRole().getName();
		List computedNames = new ArrayList();
		if (canComputeNames()){
			visitedKeyTypes.clear();
			reconcileAttributes(getRole(), attributeName, roleAttributes, computedNames);
		}
		removeExtraAttributes(computedNames);
		busy = false;
	}
}
/**
 * getFields method comment.
 */
protected abstract void reconcileAttributes(CommonRelationshipRole aRole, String attributeName, List aList, List computedNames);
/**
 * Remove attributes from our role whose name is not contained in the list of
 * @computedNames.  Also, remove from the role's source EJB.
 */
protected void removeExtraAttributes(List computedNames) {
	if (!getRole().getAttributes().isEmpty()) {
		java.util.Iterator it = getRole().getAttributes().iterator();
		ContainerManagedEntity cmp = getRole().getSourceEntity();
		CMPAttribute attribute;
		while (it.hasNext()) {
			attribute = (CMPAttribute) it.next();
			if (computedNames.contains(attribute.getName()))
				continue;
			it.remove();
			cmp.getKeyAttributes().remove(attribute);
			cmp.getPersistentAttributes().remove(attribute);
		}
	}
}
/**
 * Insert the method's description here.
 * Creation date: (11/16/00 9:19:27 PM)
 * @param newRole com.ibm.ejs.models.base.extensions.ejbext.EjbRelationshipRole
 */
public void setRole(CommonRelationshipRole newRole) {
	role = newRole;
}
}

















































