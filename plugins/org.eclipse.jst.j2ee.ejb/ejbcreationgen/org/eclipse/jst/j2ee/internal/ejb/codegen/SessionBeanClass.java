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

import org.eclipse.jdt.core.jdom.IDOMType;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeMerglet;
import org.eclipse.jst.j2ee.internal.java.codegen.MergeResults;


/**
 * Insert the type's description here. Creation date: (10/11/00 9:50:11 AM)
 * 
 * @author: Steve Wasleski
 */
public class SessionBeanClass extends EnterpriseBeanBeanClass {
	/**
	 * SessionBeanClass constructor comment.
	 */
	public SessionBeanClass() {
		super();
	}

	/**
	 * Dispatch to the merge strategy. This method is here simply to provide a hook for derived
	 * generators.
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMemberHistoryDescriptor typeHistory, IDOMType newType) throws GenerationException {
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		if (helper != null && !helper.isDeleteAll() && !helper.isBecomingRootEJB() && helper.isChangingInheritance()) {
			JavaTypeMerglet merglet = (JavaTypeMerglet) getCompilationUnitGenerator().getMergeStrategy().createDefaultTypeMerglet();
			merglet.setNoMergeSuperInterfaceNames(new String[]{IEJBGenConstants.SESSIONBEAN_INTERFACE_NAME});
			return getCompilationUnitGenerator().getMergeStrategy().merge(typeHistory, newType, merglet);
		}
		return super.dispatchToMergeStrategy(typeHistory, newType);
	}

	/**
	 * For a Session it is either SessionBean or none.
	 */
	protected List getInheritedSuperInterfaceNames() {
		List names = new ArrayList();
		if (!hasSourceSupertype())
			names.add(IEJBGenConstants.SESSIONBEAN_INTERFACE_NAME);
		return names;
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		List names = new ArrayList();

		// Do them all on create.
		if (refHelper != null && refHelper.isCreate()) {
			if (!hasSourceSupertype()) {
				names.add(IEJBGenConstants.SESSION_BEAN_CONTEXT_FIELD);
				names.add(IEJBGenConstants.SESSION_BEAN_CONTEXT_GETTER);
				names.add(IEJBGenConstants.SESSION_BEAN_CONTEXT_SETTER);
				names.add(IEJBGenConstants.SESSION_BEAN_EJBCREATE);
			}
			addLifecycleMemberGeneratorNames(names);
		} else if (helper.isChangingInheritance()) {
			names.add(IEJBGenConstants.SESSION_BEAN_CONTEXT_FIELD);
			names.add(IEJBGenConstants.SESSION_BEAN_CONTEXT_GETTER);
			names.add(IEJBGenConstants.SESSION_BEAN_CONTEXT_SETTER);
			if (helper.isBecomingRootEJB())
				addLifecycleMemberGeneratorNames(names);
			names.add(IEJBGenConstants.SESSION_BEAN_EJBCREATE);
		}

		return names;
	}

	protected void addLifecycleMemberGeneratorNames(List names) {
		names.add(IEJBGenConstants.SESSION_BEAN_EJBACTIVATE);
		names.add(IEJBGenConstants.SESSION_BEAN_EJBPASSIVATE);
		names.add(IEJBGenConstants.SESSION_BEAN_EJBREMOVE);
	}
}