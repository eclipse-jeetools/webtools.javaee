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



import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.MergeResults;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public abstract class SessionBeanEjbMethod extends JavaMethodGenerator {
	/**
	 * SessionBeanEjbMethod constructor comment.
	 */
	public SessionBeanEjbMethod() {
		super();
	}

	/**
	 * We need to delete any generator that is added if the EJB is becoming a root.
	 */
	protected JavaMemberHistoryDescriptor createHistoryDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		JavaMemberHistoryDescriptor historyDesc = super.createHistoryDescriptor(desc);
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		if (!helper.isBecomingRootEJB() && !isBasicLifeCycleMethod())
			historyDesc.setDeleteOnly(true);
		return historyDesc;
	}

	/**
	 * Throw the EJB specified exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		if (getDeclaringTypeGenerator().isInterface())
			return new String[]{IEJBGenConstants.REMOTE_EXCEPTION_NAME};
		return super.getExceptions();
	}

	protected boolean isBasicLifeCycleMethod() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMethodGenerator#dispatchToMergeStrategy(org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMethodHistoryDescriptor,
	 *      org.eclipse.jdt.core.jdom.IDOMMethod)
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMethodHistoryDescriptor methodHistory, IDOMMethod newMethod) throws GenerationException {
		if (isBasicLifeCycleMethod() && methodHistory.getOldMember() != null) {
			MergeResults mr = new MergeResults();
			mr.setDeleteOnly(methodHistory.isDeleteOnly());
			mr.setOldMember(methodHistory.getOldMember());
			mr.setCollisionMember(methodHistory.getCollisionMember());
			mr.setGenerate(false); //Do not generate if there is an old member.
			return mr;
		}
		return super.dispatchToMergeStrategy(methodHistory, newMethod);
	}
}