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
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.CMPEntityHomeInterface;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (5/7/2001 9:48:45 AM)
 * 
 * @author: Administrator
 */
public class CMP20EntityLocalHomeInterface extends CMPEntityHomeInterface {
	/**
	 * CMPEntityHomeInterface constructor comment.
	 */
	public CMP20EntityLocalHomeInterface() {
		super();
	}

	/*
	 * @see EnterpriseBeanHomeInterface#isRemote()
	 */
	public boolean isRemote() {
		return false;
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		List names = new ArrayList();

		// Do them all on create or inheritance change.
		if ((refHelper != null && refHelper.isCreate()) || topHelper.isKeyChanging()) {
			addCMPCreateMemberGeneratorNames(names);
		} else {
			// If not delete and there are feature helpers, redo create.
			if (((refHelper == null) || (!refHelper.isDelete())) && (topHelper.isKeyShapeChanging() || topHelper.getRoleHelpers().size() > 0 || topHelper.isRequiredRolesChanging())) {
				addCMPUpdateMemberGeneratorNames(names);
			} else
				addCMPUpdateMemberGeneratorNamesForFlatKeyRoles(names);
		}

		// For EJB 2.0
		if (topHelper.getEjb().getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {
			// Check for specialized create() method
			if (refHelper != null && !refHelper.isDelete() && topHelper != null && topHelper.getOldMetaObject() == null) {
				// if there are non key required attributes, codegen a create() method
				JavaParameterDescriptor[] reqAttFields, reqRoleFields;
				reqAttFields = EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator());
				if (reqAttFields != null && reqAttFields.length > 0) {
					names.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_HOME_SPECIALIZED_CREATE);
				} else {
					reqRoleFields = EJBGenHelpers.getEntityNonKeyRequiredRoleFieldsAsBeanParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator());
					if (reqRoleFields != null && reqRoleFields.length > 0)
						names.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_HOME_SPECIALIZED_CREATE);
				}
			}
		}
		return names;
	}

	/**
	 * Method addCMPUpdateMemberGeneratorNamesForFlatKeyRoles.
	 * 
	 * @param names
	 */
	private void addCMPUpdateMemberGeneratorNamesForFlatKeyRoles(List names) {
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (helper.hasOrHadKeyRoles(getSourceContext().getNavigator()))
			names.add(IEJB20GenConstants.CMP20_FLAT_KEY_ROLE_HOME_CREATE);
	}



	/**
	 * @see CMPEntityHomeInterface#getRoleHelpers()
	 */
	protected List getRoleHelpers() {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.CMPEntityHomeInterface#addCMPCreateMemberGeneratorNames(List)
	 */
	protected void addCMPCreateMemberGeneratorNames(List names) {
		names.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_HOME_CREATE);
		addCMPUpdateMemberGeneratorNamesForFlatKeyRoles(names);
		names.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_HOME_FIND_BY_PRIMARY_KEY);
	}


	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.CMPEntityHomeInterface#addCMPUpdateMemberGeneratorNames(List)
	 */
	protected void addCMPUpdateMemberGeneratorNames(List names) {
		addCMPUpdateMemberGeneratorNamesForFlatKeyRoles(names);
		names.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_HOME_CREATE);
	}


}