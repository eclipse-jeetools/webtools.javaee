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


import java.util.List;

import org.eclipse.jdt.internal.compiler.env.IConstants;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.GeneratorNotFoundException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.CMPEntityBeanClass;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:10:05 AM)
 * 
 * @author: Steve Wasleski
 */
public class CMP20EntityBeanClass extends CMPEntityBeanClass {
	/**
	 * CMPEntityBeanClass constructor comment.
	 */
	public CMP20EntityBeanClass() {
		super();
	}

	/**
	 * The generator examines the source model and derives the modifer flags for this target
	 * element. The modifier flags are defined in
	 * org.eclipse.jdt.internal.compiler.env.api.IConstants. The default value AccPublic for
	 * members. The field generators change the default to AccPrivate.
	 */
	protected int deriveFlags() throws GenerationException {
		if (getClassRefHelper() == null || getClassRefHelper().isBeanHelper())
			return IConstants.AccPublic | IConstants.AccAbstract;
		return IConstants.AccPublic;
	}

	/**
	 * Returns the logical name for an attribute generator.
	 */
	public String getAttributeGeneratorName(AttributeHelper attrHelper) {
		String name = null;
		// TODO need to fix update path
		if (attrHelper.isUpdate())
			name = attrHelper.getUpdateGeneratorName();
		if (name == null)
			name = IEJB20GenConstants.ENTITY20_ATTRIBUTE;
		return name;
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		List names = super.getRequiredMemberGeneratorNames();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		// For EJB 2.0
		if (topHelper.getEjb().getVersionID() >= J2EEVersionConstants.EJB_2_0_ID) {
			EJBClassReferenceHelper refHelper = getClassRefHelper();
			// Check for specialized ejbCreate() and ejbPostCreate()
			if (refHelper != null && !refHelper.isDelete() && topHelper != null && topHelper.getOldMetaObject() == null) {
				// if there are non key required attributes, codegen a ejbCreate() and
				// ejbPostCreate()
				if (EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator()) != null || (getRequiredRolesLength() > getKeyRolesLength())) {
					names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_EJBCREATE);
					names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_EJBPOSTCREATE);
				}
			}
		}
		if (topHelper.isMigrationCleanup())
			addLifecycleMemberGeneratorNames(names);
		return names;
	}

	protected int getRequiredRolesLength() throws GenerationException {
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		JavaParameterDescriptor[] result = EJBGenHelpers.getEntityRequiredRoleFieldsAsBeanParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator());
		if (result == null)
			return 0;
		return result.length;
	}

	protected int getKeyRolesLength() throws GenerationException {
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		JavaParameterDescriptor[] result = EJBGenHelpers.getEntityKeyRoleFieldsAsBeanParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator());
		if (result == null)
			return 0;
		return result.length;
	}

	/**
	 * @see CMPEntityBeanClass#getRoleGenerator()
	 */
	protected IBaseGenerator getRoleGenerator() throws GeneratorNotFoundException {
		return getGenerator(IEJB20GenConstants.CMP20_ENTITY_BEAN_ROLE);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.CMPEntityBeanClass#addCMPCreateMemberGeneratorNames(List)
	 */
	protected void addCMPCreateMemberGeneratorNames(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) throws GenerationException {
		if (!hasSourceSupertype()) {
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_FIELD);
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_SETTER);
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_GETTER);
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_UNSET);
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBCREATE);
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBPOSTCREATE);
			EntityHelper helper = (EntityHelper) getTopLevelHelper();
			if (helper.hasOrHadKeyRoles(getSourceContext().getNavigator())) {
				names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE);
				names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE);

				// Check for specialized ejbCreate() and ejbPostCreate()
				if (refHelper != null && !refHelper.isDelete() && topHelper != null && topHelper.getOldMetaObject() == null) {
					// if there are non key required attributes, codegen a ejbCreate() and
					// ejbPostCreate()
					if (EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator()) != null) {
						names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE);
						names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE);
					}
				}
			}
		}
		addLifecycleMemberGeneratorNames(names);
	}

	protected void addLifecycleMemberGeneratorNames(List names) {
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBACTIVATE);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBLOAD);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBPASSIVATE);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBREMOVE);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBSTORE);
	}

	protected void addCMPChangingInheritanceMemberGeneratorNames(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) throws GenerationException {
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_FIELD);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_SETTER);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_GETTER);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CONTEXT_UNSET);
		if (topHelper.isBecomingRootEJB())
			addLifecycleMemberGeneratorNames(names);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBCREATE);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBPOSTCREATE);
		if (topHelper.hasOrHadKeyRoles(getSourceContext().getNavigator())) {
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE);
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE);

			// Check for specialized ejbCreate() and ejbPostCreate()
			if (refHelper != null && !refHelper.isDelete() && topHelper != null && topHelper.getOldMetaObject() == null) {
				// if there are non key required attributes, codegen a ejbCreate() and
				// ejbPostCreate()
				if (EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(), topHelper, getSourceContext().getNavigator()) != null) {
					names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE);
					names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE);
				}
			}
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.CMPEntityBeanClass#addCMPUpdateMemberGeneratorNamesForKeyShapeChange(List)
	 */
	protected void addCMPUpdateMemberGeneratorNamesForKeyShapeChange(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) throws GenerationException {
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBCREATE);
		names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_EJBPOSTCREATE);
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (helper.hasOrHadKeyRoles(getSourceContext().getNavigator())) {
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE);
			names.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE);
			/*
			 * // Check for specialized ejbCreate() and ejbPostCreate() if (refHelper!=null &&
			 * !refHelper.isDelete() && topHelper!=null) { // if there are non key required
			 * attributes, codegen a ejbCreate() and ejbPostCreate() if
			 * (EJBGenHelpers.getEntityNonKeyRequiredAttributeFieldsAsParms(topHelper.getEntity(),
			 * topHelper, getSourceContext().getNavigator())!=null) {
			 * names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE);
			 * names.add(IEJB20GenConstants.CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE); } }
			 */
		}
	}

	protected void addCMPUpdateMemberGeneratorNamesForLinks(List names, EJBClassReferenceHelper refHelper, EntityHelper topHelper) {
		//do nothing since we do not have links
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.CMPEntityBeanClass#initialize(Object)
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		List roleHelpers = helper.getRoleOrKeyPropagationHelpers();
		RoleHelper roleHelper;
		for (int i = 0; i < roleHelpers.size(); i++) {
			roleHelper = (RoleHelper) roleHelpers.get(i);
			if (shouldInitializeAttributeGenerators(roleHelper))
				initializeAttributeGenerators(roleHelper);
		}
	}

	private boolean shouldInitializeAttributeGenerators(RoleHelper roleHelper) {
		if (roleHelper.isKey() || roleHelper.isKeyPropagationHelper())
			return true;
		CommonRelationshipRole role = roleHelper.getOldRole();
		if (role != null && role.isKey())
			return true;
		return false;
	}


	private void initializeAttributeGenerators(RoleHelper roleHelper) throws GenerationException {
		List helpers = roleHelper.getCMP20KeyAttributeHelpers();
		AttributeHelper helper;
		IBaseGenerator attrGen;
		for (int i = 0; i < helpers.size(); i++) {
			helper = (AttributeHelper) helpers.get(i);
			attrGen = getGenerator(getAttributeGeneratorName(helper));
			attrGen.initialize(helper);
		}
	}
}