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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaPackage;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;


/**
 * Insert the type's description here. Creation date: (9/1/2000 1:39:37 PM)
 * 
 * @author: Administrator
 */
public class RoleHelper extends FeatureHelper {
	static final String LINK_CLASS_TYPE_PATTERN = "{0}To{1}Link";//$NON-NLS-1$
	static final String LINK_GETTER_NAME_PATTERN = "get{0}Link";//$NON-NLS-1$
	static final String KEY_GETTER_NAME_PATTERN = "get{0}Key";//$NON-NLS-1$
	static final String KEY_SETTER_NAME_PATTERN = "privateSet{0}Key";//$NON-NLS-1$
	static final String HOME_FINDER_PATTERN = "find{0}By{1}";//$NON-NLS-1$
	static final String SECONDARY_SETTER_NAME_PREFIX = "secondarySet";//$NON-NLS-1$
	static final String SECONDARY_ADD_NAME_PREFIX = "secondaryAdd";//$NON-NLS-1$
	static final String SECONDARY_REMOVE_NAME_PREFIX = "secondaryRemove";//$NON-NLS-1$
	static final String SETTER_NAME_PREFIX = IEJBGenConstants.SETTER_PREFIX;
	static final String GETTER_NAME_PREFIX = IEJBGenConstants.GETTER_PREFIX;
	static final String ADD_NAME_PREFIX = "add";//$NON-NLS-1$
	static final String REMOVE_NAME_PREFIX = "remove";//$NON-NLS-1$
	static final String PARAMETER_NAME_PREFIX = "arg";//$NON-NLS-1$
	static final String LINK_IVAR_SUFFIX = "Link";//$NON-NLS-1$
	protected List attributeHelpers;
	protected List oldAttributeHelpers;
	protected List cmp20KeyAttributeHelpers;

	/**
	 * PersistentRoleGodegenHelper constructor comment.
	 */
	public RoleHelper(EObject aMetaObject) {
		super(aMetaObject);
	}



	/**
	 * Collect the names of the methods that were added to the bean class for a particular role.
	 */
	public static void collectRoleMethodNames(CommonRelationshipRole aRole, List aList) {
		if (aRole == null || aList == null)
			return;
		aList.add(getLinkGetterName(aRole));
		if (aRole.isForward()) {
			aList.add(getKeyGetterName(aRole));
			aList.add(getKeySetterName(aRole));
		}
		if (aRole.isNavigable())
			aList.add(getGetterName(aRole));
		if (!isMany(aRole)) {
			if (!aRole.getOppositeAsCommonRole().isKey())
				aList.add(getSetterName(aRole));
			if (!aRole.isKey())
				aList.add(getSecondarySetterName(aRole));
		} else {
			aList.add(getSecondaryAddName(aRole));
			aList.add(getSecondaryRemoveName(aRole));
			if (aRole.isNavigable()) {
				if (!aRole.getOppositeAsCommonRole().isKey())
					aList.add(getAddName(aRole));
				if (aRole.getOppositeAsCommonRole() != null && !aRole.getOppositeAsCommonRole().isRequired())
					aList.add(getRemoveName(aRole));
			}
		}
	}

	protected AttributeHelper createAttributeHelper(CMPAttribute attribute) {
		AttributeHelper helper = new AttributeHelper(attribute);
		helper.setParent(this);
		return helper;
	}

	/**
	 * Create a List of AttributeHelpers for each attribute of the role. The type should be wrapped
	 * if it is a primitive so we can deal with null key values in the bean. Creation date:
	 * (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	protected List createAttributeHelpers(List attributes) {
		List helpers = new ArrayList(attributes.size());
		CMPAttribute attribute;
		AttributeHelper helper;
		List oldAttributes = null;
		if (isUpdate() && getOldRole() != null)
			oldAttributes = getOldRole().getAttributes();
		for (int i = 0; i < attributes.size(); i++) {
			attribute = (CMPAttribute) attributes.get(i);
			if (isCreate())
				helper = createNewAttributeHelper(attribute);
			else if (isDelete())
				helper = createDeleteAttributeHelper(attribute);
			else
				helper = createUpdateAttributeHelper(attribute, oldAttributes, i);
			helpers.add(helper);
		}
		return helpers;
	}

	protected List createCMP20KeyAttributeHelpers(List attributes) {
		int type = 1;
		if (!isDelete() && getRole().isKey())
			type = 2;
		return createCMP20KeyAttributeHelpers(attributes, type);
	}

	protected List createCMP20KeyAttributeHelpers(List attributes, int type) {
		if (attributes.isEmpty())
			return Collections.EMPTY_LIST;
		List helpers = new ArrayList(attributes.size());
		CMPAttribute attribute;
		AttributeHelper helper = null;
		for (int i = 0; i < attributes.size(); i++) {
			attribute = (CMPAttribute) attributes.get(i);
			helper = createCMP20KeyAttributeHelper(attribute, type);
			if (helper != null)
				helpers.add(helper);
		}
		return helpers;
	}

	protected List createCMP20UpdateKeyAttributeHelpers() {
		List oldAtts = getOldRole().getAttributes();
		List newAtts = getRole().getAttributes();
		boolean oldKey = getOldRole().isKey();
		boolean newKey = getRole().isKey();
		if (oldKey && !newKey)
			return createCMP20KeyAttributeHelpers(oldAtts, 1);
		if (!oldKey && newKey)
			return createCMP20KeyAttributeHelpers(newAtts, 2);
		if (oldKey && newKey) //both are true so it is still a key
			return createCMP20UpdateKeyAttributeHelpers(oldAtts, newAtts);
		return Collections.EMPTY_LIST;
	}

	protected List createCMP20UpdateKeyAttributeHelpers(List oldAtts, List newAtts) {
		List helpers = new ArrayList();
		List missingNew = findMissing(newAtts, oldAtts);
		List missingOld = findMissing(oldAtts, newAtts);
		collectCMP20CreateDeleteKeyAttributeHelpers(missingNew, missingOld, helpers);
		List newUpdate = new ArrayList(newAtts);
		newUpdate.removeAll(missingNew);
		List oldUpdate = new ArrayList(oldAtts);
		oldUpdate.removeAll(missingOld);
		collectCMP20UpdateKeyAttributeHelpers(newUpdate, oldUpdate, helpers);
		return helpers;
	}

	protected void collectCMP20CreateDeleteKeyAttributeHelpers(List newAtts, List oldAtts, List helpers) {
		helpers.addAll(createCMP20KeyAttributeHelpers(newAtts, 2));
		helpers.addAll(createCMP20KeyAttributeHelpers(oldAtts, 1));
	}

	protected void collectCMP20UpdateKeyAttributeHelpers(List newAtts, List oldAtts, List helpers) {
		CMPAttribute newAtt, oldAtt;
		AttributeHelper helper;
		for (int i = 0; i < newAtts.size(); i++) {
			newAtt = (CMPAttribute) newAtts.get(i);
			for (int j = 0; j < oldAtts.size(); j++) {
				oldAtt = (CMPAttribute) oldAtts.get(j);
				if (oldAtt.getName().equals(newAtt.getName())) {
					helper = createUpdateAttributeHelper(newAtt, oldAtt);
					helpers.add(helper);
				}
			}
		}
	}

	protected List findMissing(List list1, List list2) {
		int l1Size = list1.size();
		int l2Size = list2.size();
		CMPAttribute l1Att, l2Att;
		boolean found2 = false;
		List result = null;
		for (int i = 0; i < l1Size; i++) {
			found2 = false;
			l1Att = (CMPAttribute) list1.get(i);
			for (int j = 0; j < l2Size; j++) {
				l2Att = (CMPAttribute) list2.get(j);
				if (l2Att.getName().equals(l1Att.getName())) {
					found2 = true;
					break;
				}
			}
			if (!found2) {
				if (result == null)
					result = new ArrayList();
				result.add(l1Att);
			}
		}
		if (result == null)
			result = Collections.EMPTY_LIST;
		return result;
	}

	protected AttributeHelper createCMP20KeyAttributeHelper(CMPAttribute attribute, int type) {
		AttributeHelper helper = null;
		switch (type) {
			case 1 :
				helper = createDeleteAttributeHelper(attribute);
				break;
			case 2 :
				helper = createNewAttributeHelper(attribute);
				break;
		}
		if (helper != null)
			helper.setGenerateAccessors(true);
		return helper;
	}

	protected AttributeHelper createDeleteAttributeHelper(CMPAttribute attribute) {
		AttributeHelper helper = createAttributeHelper(attribute);
		helper.setDelete();
		return helper;
	}

	protected AttributeHelper createNewAttributeHelper(CMPAttribute attribute) {
		AttributeHelper helper = createAttributeHelper(attribute);
		helper.setCreate();
		return helper;
	}

	protected AttributeHelper createUpdateAttributeHelper(CMPAttribute attribute, List oldAttributes, int index) {
		CMPAttribute old = index < oldAttributes.size() ? (CMPAttribute) oldAttributes.get(index) : null;
		return createUpdateAttributeHelper(attribute, old);
	}

	protected AttributeHelper createUpdateAttributeHelper(CMPAttribute attribute, CMPAttribute oldAttribute) {
		AttributeHelper helper = createAttributeHelper(attribute);
		if (oldAttribute != null) {
			AttributeHistory history = new AttributeHistory();
			history.storeHistory(oldAttribute);
			helper.setMetadataHistory(history);
		}
		helper.setUpdate();
		return helper;
	}

	/**
	 * Return aString where the first character is uppercased.
	 */
	protected static String firstAsUppercase(String aString) {
		if (aString == null)
			return null;
		return EJBGenHelpers.firstAsUppercase(aString);
	}

	/**
	 * Return the add method name for
	 * 
	 * @aRole. It will be in the format "add{aRole name}". For example, a role named "department"
	 *         would return addDepartment.
	 */
	public static String getAddName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return ADD_NAME_PREFIX + arg;
	}

	/**
	 * Return the association table name for
	 * 
	 * @aRole.
	 */
	public static String getAssociationTableName(CommonRelationshipRole aRole) {
		StringBuffer out = new StringBuffer(20);
		CommonRelationship rel = aRole.getCommonRelationship();
		out.append(firstAsUppercase(rel.getFirstCommonRole().getName()));
		out.append("_"); //$NON-NLS-1$
		out.append(firstAsUppercase(rel.getSecondCommonRole().getName()));
		return out.toString();
	}

	/**
	 * Insert the method's description here. Creation date: (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List getAttributeHelpers() {
		if (isDelete())
			return getOldAttributeHelpers();
		else if (attributeHelpers == null)
			initializeAttributeHelpers();
		return attributeHelpers;
	}

	public List getCMP20KeyAttributeHelpers() {
		if (cmp20KeyAttributeHelpers == null)
			initializeCMP20KeyAttributeHelpers();
		return cmp20KeyAttributeHelpers;
	}

	/**
	 * Return the package name for the bean class of the role's EJB.
	 */
	public String getEJBLinkClassPackageName() {
		return getEjb().getEjbClass().getJavaPackage().getName();
	}

	/**
	 * Return the getter method name for
	 * 
	 * @aRole. It will be in the format "get{aRole name}". For example, a role named "department"
	 *         would return getDepartment.
	 */
	public static String getGetterName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return GETTER_NAME_PREFIX + arg;
	}

	/**
	 * Return the home find many method name for
	 * 
	 * @aRole. It will be in the format "find{aRole opposite name}By{aRole name}". For example, a
	 *         role named "department" with an opposite role named "employees" would return
	 *         findEmployeesByDepartment.
	 */
	public static String getHomeFinderName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg1 = firstAsUppercase(aRole.getOppositeAsCommonRole().getName());
		String arg2 = firstAsUppercase(aRole.getName());
		return format(HOME_FINDER_PATTERN, new String[]{arg1, arg2});
	}

	/**
	 * Return the key getter method name for
	 * 
	 * @aRole. It will be in the format "get{aRole name}Key". For example, a role named "department"
	 *         would return getDepartmentKey.
	 */
	public static String getKeyGetterName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return format(KEY_GETTER_NAME_PATTERN, new String[]{arg});
	}

	/**
	 * Return the key setter method name for
	 * 
	 * @aRole. It will be in the format "privateSet{aRole name}Key". For example, a role named
	 *         "department" would return privateSetDepartmentKey.
	 */
	public static String getKeySetterName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return format(KEY_SETTER_NAME_PATTERN, new String[]{arg});
	}

	/**
	 * Return the field name for
	 * 
	 * @aRole. It will be in the format "{aRole name}Link". For example, a role named "department"
	 *         would return departmentLink.
	 */
	public static String getLinkFieldName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		return aRole.getName() + LINK_IVAR_SUFFIX;
	}

	/**
	 * Return the link getter method name for
	 * 
	 * @aRole. It will be in the format "get{aRole name}Link". For example, a role named
	 *         "department" would return getDepartmentLink.
	 */
	public static String getLinkGetterName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return format(LINK_GETTER_NAME_PATTERN, new String[]{arg});
	}

	/**
	 * Return the interface type name for
	 * 
	 * @aRole.
	 */
	public static String getLinkInterfaceTypeName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		if (aRole.isMany())
			return IEJBGenConstants.EJB_MANY_LINK_INT_NAME;
		return IEJBGenConstants.EJB_SINGLE_LINK_INT_NAME;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:17:02 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getName() {
		if (fName == null && getRole() != null)
			fName = getRole().getName();
		return fName;
	}

	/**
	 * Insert the method's description here. Creation date: (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	public java.util.List getOldAttributeHelpers() {
		if (oldAttributeHelpers == null)
			initializeOldAttributeHelpers();
		return oldAttributeHelpers;
	}

	public CommonRelationshipRole getOldOppositeRole() {
		return getOldRole() != null ? getOldRole().getOppositeAsCommonRole() : null;
	}

	/**
	 * Return the EJB held by the typeExtension from the old opposite role.
	 */
	public EnterpriseBean getOldOppositeRoleType() {
		return getRoleType(getOldOppositeRole());
	}

	/**
	 * Return the name of the EJB held by the typeExtension from the old opposite role.
	 */
	public String getOldOppositeRoleTypeName() {
		EnterpriseBean ejb = getOldOppositeRoleType();
		return ejb == null ? null : ejb.getName();
	}

	/**
	 * Return the name of the package that the old role classes were generated into (i.e., Link
	 * classes).
	 */
	public String getOldPackageName() {
		if (getOldRole() != null)
			return getPackageName(getOldRole());
		return getPackageName(getRole());
	}

	public CommonRelationshipRole getOldRole() {
		return (CommonRelationshipRole) getMetadataHistory().getOldMetadata();
	}

	public CommonRelationshipRole getOppositeRole() {
		return getRole().getOppositeAsCommonRole();
	}

	/**
	 * Return the EJB held by the typeExtension from the opposite role.
	 */
	public ContainerManagedEntity getOppositeRoleType() {
		return getRoleType(getOppositeRole());
	}

	/**
	 * Return the name of the EJB held by the typeExtension from the opposite role.
	 */
	public String getOppositeRoleTypeName() {
		EnterpriseBean ejb = getOppositeRoleType();
		return ejb == null ? null : ejb.getName();
	}

	/**
	 * Return the name of the package that the old role classes were generated into (i.e., Link
	 * classes).
	 */
	public static String getPackageName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		EnterpriseBean ejb = aRole.getSourceEntity();
		JavaClass ejbClass = ejb == null ? null : ejb.getEjbClass();
		JavaPackage pack = ejbClass == null ? null : ejbClass.getJavaPackage();
		return pack == null ? null : pack.getName();
	}

	/**
	 * Return the parameter name for
	 * 
	 * @aRole. It will be in the format "arg{aRole name}". For example, a role named "department"
	 *         would return argDepartment.
	 */
	public static String getParameterName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return PARAMETER_NAME_PREFIX + arg;
	}

	/**
	 * Return the remove method name for
	 * 
	 * @aRole. It will be in the format "remove{aRole name}". For example, a role named "department"
	 *         would return removeDepartment.
	 */
	public static String getRemoveName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return REMOVE_NAME_PREFIX + arg;
	}

	/**
	 * If this is a delete, return the old role since it is the same as current except that the
	 * current is disconnected.
	 */
	public CommonRelationshipRole getRole() {
		if (isDelete())
			return (CommonRelationshipRole) getMetadataHistory().getOldMetadata();
		return (CommonRelationshipRole) getMetaObject();
	}

	/**
	 * Return the declaring EJB held by the beanExtension from aRole.
	 */
	public static ContainerManagedEntity getRoleDeclaringBean(CommonRelationshipRole aRole) {
		if (aRole != null)
			return aRole.getSourceEntity();
		return null;
	}

	/**
	 * Return the EJB held by the typeExtension from the role.
	 */
	public ContainerManagedEntity getRoleType() {
		return getRoleType(getRole());
	}

	/**
	 * Return the EJB held by the typeExtension from aRole.
	 */
	public static ContainerManagedEntity getRoleType(CommonRelationshipRole aRole) {
		if (aRole != null)
			return aRole.getTypeEntity();
		return null;
	}

	public static String getRoleTypePrimaryKeyName(CommonRelationshipRole aRole) {
		ContainerManagedEntity cmp = getRoleType(aRole);
		return cmp == null ? null : cmp.getPrimaryKeyName();
	}

	public static String getRoleTypeRemoteInterfaceName(CommonRelationshipRole aRole) {
		ContainerManagedEntity cmp = getRoleType(aRole);
		return cmp == null ? null : cmp.getRemoteInterfaceName();
	}

	/**
	 * Return the secondary add method name for
	 * 
	 * @aRole. It will be in the format "secondaryAdd{aRole name}". For example, a role named
	 *         "department" would return secondaryAddDepartment.
	 */
	public static String getSecondaryAddName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return SECONDARY_ADD_NAME_PREFIX + arg;
	}

	/**
	 * Return the secondary remove method name for
	 * 
	 * @aRole. It will be in the format "secondaryRemove{aRole name}". For example, a role named
	 *         "department" would return secondaryRemoveDepartment.
	 */
	public static String getSecondaryRemoveName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return SECONDARY_REMOVE_NAME_PREFIX + arg;
	}

	/**
	 * Return the secondary setter method name for
	 * 
	 * @aRole. It will be in the format "secondarySet{aRole name}". For example, a role named
	 *         "department" would return secondarySetDepartment.
	 */
	public static String getSecondarySetterName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return SECONDARY_SETTER_NAME_PREFIX + arg;
	}

	/**
	 * Return the setter method name for
	 * 
	 * @aRole. It will be in the format "set{aRole name}". For example, a role named "department"
	 *         would return setDepartment.
	 */
	public static String getSetterName(CommonRelationshipRole aRole) {
		if (aRole == null)
			return null;
		String arg = firstAsUppercase(aRole.getName());
		return SETTER_NAME_PREFIX + arg;
	}

	/**
	 * Create a List of AttributeHelpers for each attribute of the role. The type should be wrapped
	 * if it is a primitive so we can deal with null key values in the bean. Creation date:
	 * (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	protected void initializeAttributeHelpers() {
		attributeHelpers = createAttributeHelpers(getRole().getAttributes());
	}

	/**
	 * Create a List of AttributeHelpers for each attribute of the old role. The type should be
	 * wrapped if it is a primitive so we can deal with null key values in the bean. Creation date:
	 * (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	protected void initializeOldAttributeHelpers() {
		if (getOldRole() == null)
			oldAttributeHelpers = new ArrayList();
		else
			oldAttributeHelpers = createAttributeHelpers(getOldRole().getAttributes());
	}

	protected void initializeCMP20KeyAttributeHelpers() {
		if (isDelete() || isCreate())
			cmp20KeyAttributeHelpers = createCMP20KeyAttributeHelpers(getRole().getAttributes());
		else
			cmp20KeyAttributeHelpers = createCMP20UpdateKeyAttributeHelpers();
	}

	public boolean isKeyPropagationHelper() {
		return false;
	}

	/**
	 * Return true if the multiplicity is many.
	 */
	public static boolean isMany(CommonRelationshipRole aRole) {
		return aRole != null && aRole.isMany();
	}

	public boolean isRoleHelper() {
		return true;
	}

	/**
	 * Return true if this is an Update and the old role isForward and the new role is not forward.
	 */
	public boolean isUpdateForwardChange() {
		return isUpdate() && getOldRole() != null && getOldRole().isForward() && !getRole().isForward();
	}

	/**
	 * If this is a delete, return the old role since it is the same as current except that the
	 * current is disconnected.
	 */
	public CommonRelationshipRole primGetRole() {
		return (CommonRelationshipRole) getMetaObject();
	}

	public boolean isOrWasKey() {
		if (isKey())
			return true;
		if (getOldRole() != null)
			return getOldRole().isKey();
		return false;
	}
}