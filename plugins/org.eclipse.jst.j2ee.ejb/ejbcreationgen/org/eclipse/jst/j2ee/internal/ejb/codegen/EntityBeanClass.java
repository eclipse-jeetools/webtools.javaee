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
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.jdom.IDOMType;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeMerglet;
import org.eclipse.jst.j2ee.internal.java.codegen.MergeResults;


/**
 * Insert the type's description here. Creation date: (10/11/00 9:50:11 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityBeanClass extends EnterpriseBeanBeanClass {
	/**
	 * EntityBeanClass constructor comment.
	 */
	public EntityBeanClass() {
		super();
	}

	/**
	 * Dispatch to the merge strategy. This method is here simply to provide a hook for derived
	 * generators.
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMemberHistoryDescriptor typeHistory, IDOMType newType) throws GenerationException {
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		List names = null;
		if (helper != null && !helper.isDeleteAll() && helper.isChangingInheritance()) {
			names = new ArrayList();
			if (!helper.isBecomingRootEJB())
				names.add(IEJBGenConstants.ENTITYBEAN_INTERFACE_NAME);
		}
		//names = collectedAccessBeanSuperInterfaceNames(names, true);
		if (names != null) {
			JavaTypeMerglet merglet = (JavaTypeMerglet) getCompilationUnitGenerator().getMergeStrategy().createDefaultTypeMerglet();
			merglet.setNoMergeSuperInterfaceNames((String[]) names.toArray(new String[names.size()]));
			return getCompilationUnitGenerator().getMergeStrategy().merge(typeHistory, newType, merglet);
		}
		return super.dispatchToMergeStrategy(typeHistory, newType);
	}

	/**
	 * Returns the logical name for an attribute generator.
	 */
	public String getAttributeGeneratorName(AttributeHelper attrHelper) {
		String name = null;
		if (attrHelper.isUpdate())
			name = attrHelper.getUpdateGeneratorName();
		if (name == null)
			name = IEJBGenConstants.ENTITY_ATTRIBUTE;
		return name;
	}

	/**
	 * For an Entity it is either EntityBean or none.
	 */
	protected List getInheritedSuperInterfaceNames() {
		List names = new ArrayList();
		if (!hasSourceSupertype()) {
			names.add(IEJBGenConstants.ENTITYBEAN_INTERFACE_NAME);
		}
		return names;
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		List names = new ArrayList();

		// Do them all on create.
		if (refHelper != null && refHelper.isCreate()) {
			addLifecycleMemberGeneratorNames(names);
			if (!hasSourceSupertype()) {
				names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_FIELD);
				names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_GETTER);
				names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_SETTER);
				names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_UNSET);
				names.add(IEJBGenConstants.ENTITY_BEAN_EJBCREATE);
				names.add(IEJBGenConstants.ENTITY_BEAN_EJBPOSTCREATE);
				names.add(IEJBGenConstants.ENTITY_BEAN_EJB_FIND_BY_PRIMARY_KEY);
			}
		} else if (topHelper.isChangingInheritance()) {
			names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_FIELD);
			names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_GETTER);
			names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_SETTER);
			names.add(IEJBGenConstants.ENTITY_BEAN_CONTEXT_UNSET);
			if (topHelper.isBecomingRootEJB())
				addLifecycleMemberGeneratorNames(names);
			names.add(IEJBGenConstants.ENTITY_BEAN_EJBCREATE);
			names.add(IEJBGenConstants.ENTITY_BEAN_EJBPOSTCREATE);
			names.add(IEJBGenConstants.ENTITY_BEAN_EJB_FIND_BY_PRIMARY_KEY);
		} else {
			// If not delete and there are feature helpers, redo ejbCreate.
			if ((refHelper == null || !refHelper.isDelete()) && !topHelper.isEJBRemoved()) {
				if (!hasSourceSupertype() && topHelper.isKeyChanging()) {
					names.add(IEJBGenConstants.ENTITY_BEAN_EJB_FIND_BY_PRIMARY_KEY);
				}
			}
		}
		return names;
	}

	protected void addLifecycleMemberGeneratorNames(List names) {
		names.add(IEJBGenConstants.ENTITY_BEAN_EJBACTIVATE);
		names.add(IEJBGenConstants.ENTITY_BEAN_EJBLOAD);
		names.add(IEJBGenConstants.ENTITY_BEAN_EJBPASSIVATE);
		names.add(IEJBGenConstants.ENTITY_BEAN_EJBREMOVE);
		names.add(IEJBGenConstants.ENTITY_BEAN_EJBSTORE);
	}

	/**
	 * This implementation sets the mofObject as the source element and creates attribute
	 * generators.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		Iterator attrHelpers = ((EntityHelper) getTopLevelHelper()).getAttributeHelpers().iterator();
		AttributeHelper attrHelper = null;
		IBaseGenerator attrGen = null;
		while (attrHelpers.hasNext()) {
			attrHelper = (AttributeHelper) attrHelpers.next();
			attrGen = getGenerator(getAttributeGeneratorName(attrHelper));
			attrGen.initialize(attrHelper);
		}
	}
}