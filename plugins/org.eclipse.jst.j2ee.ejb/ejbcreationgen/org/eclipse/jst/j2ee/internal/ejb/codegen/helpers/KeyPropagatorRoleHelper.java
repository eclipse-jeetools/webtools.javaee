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
import java.util.List;

import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;


/**
 * Insert the type's description here. Creation date: (8/10/2001 3:49:42 PM)
 * 
 * @author: Administrator
 */
public class KeyPropagatorRoleHelper extends RoleHelper {
	/**
	 * KeyPropagatorRoleHelper constructor comment.
	 * 
	 * @param aMetaObject
	 *            org.eclipse.emf.ecore.EObject
	 */
	public KeyPropagatorRoleHelper(org.eclipse.emf.ecore.EObject aMetaObject) {
		super(aMetaObject);
	}

	/**
	 * Return two lists of CMPAttributes. The first list will contain CMPAttributes that were added
	 * to
	 * 
	 * @secondRole and the second list will contain CMPAttributes that were removed from
	 * @secondRole.
	 */
	protected List[] analyzeRoleAttributes(CommonRelationshipRole firstRole, CommonRelationshipRole secondRole) {
		List added, removed, updated, firstAttributes, secondAttributes;
		firstAttributes = firstRole.getAttributes();
		secondAttributes = secondRole.getAttributes();
		added = new ArrayList(secondAttributes);
		removed = new ArrayList(firstAttributes.size());
		updated = new ArrayList(secondAttributes.size());
		CMPAttribute firstAttribute, secondAttribute;
		boolean found = false;
		for (int i = 0; i < firstAttributes.size(); i++) {
			firstAttribute = (CMPAttribute) firstAttributes.get(i);
			found = false;
			for (int ii = 0; ii < secondAttributes.size(); ii++) {
				secondAttribute = (CMPAttribute) secondAttributes.get(ii);
				if (firstAttribute.getName().equals(secondAttribute.getName())) {
					found = true;
					added.remove(secondAttribute);
					updated.add(secondAttribute);
					break;
				}
			}
			if (!found)
				removed.add(firstAttribute);
		}
		return new List[]{added, removed, updated};
	}

	/**
	 * Create a List of AttributeHelpers for each attribute of the role. The type should be wrapped
	 * if it is a primitive so we can deal with null key values in the bean. Creation date:
	 * (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	protected List createAttributeHelpers(List[] attributes) {
		List adds, deletes, updates, helpers;
		adds = attributes[0];
		deletes = attributes[1];
		updates = attributes[2];
		helpers = new ArrayList(adds.size() + deletes.size() + updates.size());
		CMPAttribute attribute;
		for (int i = 0; i < adds.size(); i++) {
			attribute = (CMPAttribute) adds.get(i);
			helpers.add(createNewAttributeHelper(attribute));
		}
		for (int j = 0; j < deletes.size(); j++) {
			attribute = (CMPAttribute) deletes.get(j);
			helpers.add(createDeleteAttributeHelper(attribute));
		}
		for (int k = 0; k < updates.size(); k++) {
			attribute = (CMPAttribute) updates.get(k);
			helpers.add(createUpdateAttributeHelper(attribute, null));
		}
		return helpers;
	}

	/**
	 * Create a List of AttributeHelpers for each attribute of the role. The type should be wrapped
	 * if it is a primitive so we can deal with null key values in the bean. Creation date:
	 * (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	protected void initializeAttributeHelpers() {
		attributeHelpers = createAttributeHelpers(analyzeRoleAttributes(getOldRole(), getRole()));
	}

	/**
	 * Create a List of AttributeHelpers for each attribute of the old role. The type should be
	 * wrapped if it is a primitive so we can deal with null key values in the bean. Creation date:
	 * (5/4/2001 3:29:24 PM)
	 * 
	 * @return java.util.List
	 */
	protected void initializeOldAttributeHelpers() {
		oldAttributeHelpers = createAttributeHelpers(analyzeRoleAttributes(getRole(), getOldRole()));
	}

	public boolean isKeyPropagationHelper() {
		return true;
	}
}