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
package org.eclipse.jst.j2ee.internal.ejb.codegen;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


/**
 * Insert the type's description here. Creation date: (5/7/2001 9:48:45 AM)
 * 
 * @author: Administrator
 */
public class CMPEntityHomeInterface extends EntityHomeInterface {
	/**
	 * CMPEntityHomeInterface constructor comment.
	 */
	public CMPEntityHomeInterface() {
		super();
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
			if (((refHelper == null) || (!refHelper.isDelete())) && (topHelper.isKeyShapeChanging() || topHelper.getRoleHelpers().size() > 0 || topHelper.isRequiredRolesChanging()) || (topHelper.getEjb().getVersionID() >= J2EEVersionConstants.EJB_2_0_ID && topHelper.hasOrHadKeyRoles(getSourceContext().getNavigator()))) {
				addCMPUpdateMemberGeneratorNames(names);
			}
		}

		// Check for specialized create() method
		if (refHelper != null && !refHelper.isDelete() && topHelper != null && topHelper.getOldMetaObject() == null) {
			// if there are non key required attributes, codegen a create() method
			if (EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator()) != null) {
				names.add(IEJBGenConstants.CMP_ENTITY_HOME_SPECIALIZED_CREATE);
			}
		}

		return names;
	}

	protected void addCMPUpdateMemberGeneratorNames(List names) {
		names.add(IEJBGenConstants.CMP_ENTITY_HOME_CREATE);
	}

	protected void addCMPCreateMemberGeneratorNames(List names) {
		names.add(IEJBGenConstants.CMP_ENTITY_HOME_CREATE);
		names.add(IEJBGenConstants.CMP_ENTITY_HOME_FIND_BY_PRIMARY_KEY);
	}

	/**
	 * Return either the actual role helpers or the key propagation helpers.
	 */
	protected List getRoleHelpers() {
		return ((EntityHelper) getTopLevelHelper()).getRoleOrKeyPropagationHelpers();
	}

	/**
	 * This implementation sets the mofObject as the source element and creates role finder
	 * generators.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (helper.getEjb().getVersionID() >= J2EEVersionConstants.EJB_2_0_ID)
			return;
		List roleHelpers = getRoleHelpers();
		RoleHelper roleHelper = null;
		IBaseGenerator gen = null;
		for (int i = 0; i < roleHelpers.size(); i++) {
			roleHelper = (RoleHelper) roleHelpers.get(i);
			if (roleHelper.getOppositeRole() != null && roleHelper.getOppositeRole().isNavigable()) {
				gen = getGenerator(IEJBGenConstants.CMP_ENTITY_HOME_ROLE_FINDERS);
				gen.initialize(roleHelper);
			}
		}
	}
}