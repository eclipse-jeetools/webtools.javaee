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

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.GeneratorNotFoundException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:10:05 AM)
 * 
 * @author: Steve Wasleski
 */
public class CMPEntityBeanClass extends EntityBeanClass {
	/**
	 * CMPEntityBeanClass constructor comment.
	 */
	public CMPEntityBeanClass() {
		super();
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		List names = new ArrayList();
		if (topHelper.isEJBRemoved())
			return names;
		// Do them all on create or inheritance change.
		boolean isCreate = refHelper != null && refHelper.isCreate();
		if (isCreate || topHelper.isChangingInheritance()) {
			if (!isCreate)
				addCMPChangingInheritanceMemberGeneratorNames(names, refHelper, topHelper);
			else
				addCMPCreateMemberGeneratorNames(names, refHelper, topHelper);

		} else {
			// If not delete and there are feature helpers, redo ejbCreate.
			if (refHelper == null || !refHelper.isDelete()) {
				boolean rolesChanging = topHelper.getRoleHelpers().size() > 0 || topHelper.isLocalRequiredRolesChanging();
				if (rolesChanging || topHelper.isKeyChanging())
					addCMPUpdateMemberGeneratorNamesForKeyShapeChange(names, refHelper, topHelper);
				if (rolesChanging || topHelper.isMigrationCleanup() || topHelper.isKeyShapeChangingByFeature())
					addCMPUpdateMemberGeneratorNamesForLinks(names, refHelper, topHelper);
			}
		}

		// For EJB 1.1 on the beta only
		if (topHelper.getEjb().getVersionID() <= J2EEVersionConstants.EJB_1_1_ID) {
			// Check for specialized ejbCreate() and ejbPostCreate()
			if (refHelper != null && !refHelper.isDelete() && topHelper != null && topHelper.getOldMetaObject() == null) {
				// if there are non key required attributes, codegen a ejbCreate() and
				// ejbPostCreate()
				if (EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator()) != null) {
					names.add(IEJBGenConstants.CMP_SPECIALIZED_ENTITY_BEAN_EJBCREATE);
					names.add(IEJBGenConstants.CMP_SPECIALIZED_ENTITY_BEAN_EJBPOSTCREATE);
				}
			}
		}
		return names;
	}

	protected void addCMPUpdateMemberGeneratorNamesForLinks(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) {
	}

	protected void addCMPUpdateMemberGeneratorNamesForKeyShapeChange(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) throws GenerationException {
		if (!hasSourceSupertype() || topHelper.isLocalRequiredRolesChanging()) {
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBCREATE);
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBPOSTCREATE);
		}
	}

	protected void addCMPCreateMemberGeneratorNames(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) throws GenerationException {
		if (!hasSourceSupertype()) {
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_FIELD);
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_GETTER);
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_SETTER);
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_UNSET);
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBCREATE);
			names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBPOSTCREATE);
		}
		addLifecycleMemberGeneratorNames(names);
	}

	protected void addLifecycleMemberGeneratorNames(List names) {
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBACTIVATE);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBLOAD);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBPASSIVATE);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBREMOVE);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBSTORE);
	}

	protected void addCMPChangingInheritanceMemberGeneratorNames(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) throws GenerationException {
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_FIELD);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_GETTER);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_SETTER);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_CONTEXT_UNSET);
		if (topHelper.isBecomingRootEJB())
			addLifecycleMemberGeneratorNames(names);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBCREATE);
		names.add(IEJBGenConstants.CMP_ENTITY_BEAN_EJBPOSTCREATE);
	}

	/**
	 * This implementation sets the mofObject as the source element and creates role generators.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		List roleHelpers = getRoleHelpers();
		RoleHelper roleHelper = null;
		IBaseGenerator gen = null;
		for (int i = 0; i < roleHelpers.size(); i++) {
			roleHelper = (RoleHelper) roleHelpers.get(i);
			gen = getRoleGenerator();
			gen.initialize(roleHelper);
		}
	}

	protected IBaseGenerator getRoleGenerator() throws GeneratorNotFoundException {
		return getGenerator(IEJBGenConstants.CMP_ENTITY_BEAN_ROLE);
	}
}