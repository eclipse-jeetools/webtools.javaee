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
package org.eclipse.jst.j2ee.model.internal.validation;


import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;


/**
 * This class is a copy of org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper.
 * org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper cannot be used
 * directly by the EJB Validator (to calculate role method names) because
 * it uses classes which are not used by WebSphere. Until the long-term
 * fix can be put in, this class will be used.
 */
public class RoleHelper {
	static final String KEY_GETTER_NAME_PATTERN = "get{0}Key";//$NON-NLS-1$
	static final String KEY_SETTER_NAME_PATTERN = "privateSet{0}Key";//$NON-NLS-1$
	static final String LINK_GETTER_NAME_PATTERN = "get{0}Link";//$NON-NLS-1$
	static final String ADD_NAME_PREFIX = "add";//$NON-NLS-1$
	static final String GETTER_NAME_PREFIX = "get"; //$NON-NLS-1$
	static final String REMOVE_NAME_PREFIX = "remove";//$NON-NLS-1$
	static final String SECONDARY_ADD_NAME_PREFIX = "secondaryAdd";//$NON-NLS-1$
	static final String SECONDARY_REMOVE_NAME_PREFIX = "secondaryRemove";//$NON-NLS-1$
	static final String SECONDARY_SETTER_NAME_PREFIX = "secondarySet";//$NON-NLS-1$
	static final String SETTER_NAME_PREFIX = "set"; //$NON-NLS-1$
	protected EjbModuleExtensionHelper modHelper;
/**
 * RoleHelper constructor comment.
 */
public RoleHelper() {
	super();
}
/**
 * Collect the names of the methods that were added to the bean class
 * for a particular role.
 */
public static void collectRoleMethodNames(CommonRelationshipRole aRole, List aList) {
	if (aRole == null || aList == null) return;
	aList.add(getLinkGetterName(aRole));
	if (aRole.isForward()) {
		aList.add(getKeyGetterName(aRole));
		aList.add(getKeySetterName(aRole));
	}
	if (aRole.isNavigable())
		aList.add(getGetterName(aRole));
	if (!isMany(aRole)) {
		try {
			if(aRole.getOppositeAsCommonRole() != null) {
				if (!aRole.getOppositeAsCommonRole().isKey())
					aList.add(getSetterName(aRole));
				if (!aRole.isKey()) {
					aList.add(getSecondarySetterName(aRole));
				}
			}
		}
		catch(NullPointerException exc) {
			// If a NullPointerException is caught, assume that the role isn't a key.
			// Just continue.
		}
	} else {
		aList.add(getSecondaryAddName(aRole));
		aList.add(getSecondaryRemoveName(aRole));
		if (aRole.isNavigable()) {
			try {
				if(aRole.getOppositeAsCommonRole() != null) {
					if (!aRole.getOppositeAsCommonRole().isKey()) {
						aList.add(getAddName(aRole));
					}
				}
			}
			catch(NullPointerException exc) {
				// If a NullPointerException is thrown, just assume that the opposite isn't a key.
			}
			if (aRole.getOppositeAsCommonRole() != null && !aRole.getOppositeAsCommonRole().isRequired())
				aList.add(getRemoveName(aRole));
		}
	}
}
/**
 * Return aString where the first character is uppercased.
 */
public static final String firstAsUppercase(String aString) {
	if (aString != null && aString.length() >0 && !Character.isUpperCase(aString.charAt(0))) {
		char[] chars = aString.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return String.valueOf(chars);
	}
	return aString;
}
protected static String format(String pattern, String[] replacements) {
	return java.text.MessageFormat.format(pattern, replacements);
}
/**
 * Return the add method name for @aRole.
 * It will be in the format "add{aRole name}".
 * For example, a role named "department" would return
 * addDepartment.
 */
public static String getAddName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return ADD_NAME_PREFIX + arg;
}
/**
 * Return the getter method name for @aRole.
 * It will be in the format "get{aRole name}".
 * For example, a role named "department" would return
 * getDepartment.
 */
public static String getGetterName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return GETTER_NAME_PREFIX + arg;
}
/**
 * Return the key getter method name for @aRole.
 * It will be in the format "get{aRole name}Key".
 * For example, a role named "department" would return
 * getDepartmentKey.
 */
public static String getKeyGetterName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return format(KEY_GETTER_NAME_PATTERN, new String[]{arg});
}
/**
 * Return the key setter method name for @aRole.
 * It will be in the format "privateSet{aRole name}Key".
 * For example, a role named "department" would return
 * privateSetDepartmentKey.
 */
public static String getKeySetterName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return format(KEY_SETTER_NAME_PATTERN, new String[]{arg});
}
/**
 * Return the link getter method name for @aRole.
 * It will be in the format "get{aRole name}Link".
 * For example, a role named "department" would return
 * getDepartmentLink.
 */
public static String getLinkGetterName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return format(LINK_GETTER_NAME_PATTERN, new String[]{arg});
}
/**
 * Return the remove method name for @aRole.
 * It will be in the format "remove{aRole name}".
 * For example, a role named "department" would return
 * removeDepartment.
 */
public static String getRemoveName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return REMOVE_NAME_PREFIX + arg;
}
/**
 * Return the secondary add method name for @aRole.
 * It will be in the format "secondaryAdd{aRole name}".
 * For example, a role named "department" would return
 * secondaryAddDepartment.
 */
public static String getSecondaryAddName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return SECONDARY_ADD_NAME_PREFIX + arg;
}
/**
 * Return the secondary remove method name for @aRole.
 * It will be in the format "secondaryRemove{aRole name}".
 * For example, a role named "department" would return
 * secondaryRemoveDepartment.
 */
public static String getSecondaryRemoveName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return SECONDARY_REMOVE_NAME_PREFIX + arg;
}
/**
 * Return the secondary setter method name for @aRole.
 * It will be in the format "secondarySet{aRole name}".
 * For example, a role named "department" would return
 * secondarySetDepartment.
 */
public static String getSecondarySetterName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return SECONDARY_SETTER_NAME_PREFIX + arg;
}
/**
 * Return the setter method name for @aRole.
 * It will be in the format "set{aRole name}".
 * For example, a role named "department" would return
 * setDepartment.
 */
public static String getSetterName(CommonRelationshipRole aRole) {
	if (aRole == null) return null;
	String arg = firstAsUppercase(aRole.getName());
	return SETTER_NAME_PREFIX + arg;
}
/**
 * Return true if the multiplicity is many.
 */
public static boolean isMany(CommonRelationshipRole aRole) {
	return aRole != null && aRole.isMany();
}
}
