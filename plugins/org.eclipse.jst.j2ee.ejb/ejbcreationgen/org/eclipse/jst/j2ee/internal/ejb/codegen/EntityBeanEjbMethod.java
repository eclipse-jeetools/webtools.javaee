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


import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IDependentGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
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
public abstract class EntityBeanEjbMethod extends JavaMethodGenerator {
	/**
	 * EntityBeanEjbMethod constructor comment.
	 */
	public EntityBeanEjbMethod() {
		super();
	}

	/**
	 * This implementation initializes the generators with the RoleHelper.
	 */
	protected void createDependentGenerator(String aGeneratorName) throws GenerationException {
		IDependentGenerator depGen = getDependentGenerator(aGeneratorName);
		depGen.initialize(getSourceElement());
	}

	/**
	 * We need to delete any generator that is added if the EJB is becoming a root.
	 */
	protected JavaMemberHistoryDescriptor createHistoryDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		JavaMemberHistoryDescriptor historyDesc = super.createHistoryDescriptor(desc);
		if (shouldDelete())
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

	protected boolean shouldDelete() {
		EntityHelper helper = (EntityHelper) getTopLevelHelper();
		if (helper.getConcreteBeanHelper() != null || isBasicLifeCycleMethod())
			return false;
		return !helper.isBecomingRootEJB() || helper.getSupertype() != null;
	}

	/**
	 * Method isBasicLifeCycleMethod.
	 * 
	 * @return boolean
	 */
	protected boolean isBasicLifeCycleMethod() {
		return false;
	}

	public static boolean containsMatchingMethod(List methods, Method originalMethod) {
		java.util.Iterator i = methods.iterator();
		while (i.hasNext()) {
			Method candidateMethod = (Method) i.next();
			if (methodsMatch(candidateMethod, originalMethod))
				return true;
		}
		return false;
	}

	public static boolean methodsMatch(Method originalMethod, Method candidateMethod) {
		if (candidateMethod.getName().equals(originalMethod.getName())) {
			JavaParameter[] originalParams = originalMethod.listParametersWithoutReturn();
			JavaParameter[] candidateParams = candidateMethod.listParametersWithoutReturn();
			if (candidateParams.length == originalParams.length) {
				for (int j = 0; j < originalParams.length; j++) {
					JavaParameter originalParam = originalParams[j];
					String originalTypeName = ((JavaHelpers) originalParam.getEType()).getQualifiedName();
					JavaParameter candidateParam = candidateParams[j];
					String candidateTypeName = ((JavaHelpers) candidateParam.getEType()).getQualifiedName();
					if (!originalTypeName.equals(candidateTypeName))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public static boolean testBeanMethodExceptions(EnterpriseBean ejb, String methodName, java.util.List parmTypes, String exceptionName) {
		JavaClass beanClass = ejb.getEjbClass();
		Method beanMethod = null;
		if (parmTypes != null && !parmTypes.isEmpty())
			beanMethod = beanClass.getPublicMethodExtended(methodName, parmTypes);
		else {
			java.util.List methods = beanClass.getPublicMethodsExtendedNamed(methodName);
			if (!methods.isEmpty())
				beanMethod = (Method) methods.iterator().next();
		}
		if (beanMethod != null) {
			Iterator exceptions = beanMethod.getJavaExceptions().iterator();
			while (exceptions.hasNext()) {
				JavaClass exc = (JavaClass) exceptions.next();
				if (exc.getQualifiedName().equals(exceptionName))
					return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMethodGenerator#dispatchToMergeStrategy(org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMethodHistoryDescriptor,
	 *      org.eclipse.jdt.core.jdom.IDOMMethod)
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMethodHistoryDescriptor methodHistory, IDOMMethod newMethod) throws GenerationException {
		if (isBasicLifeCycleMethod() && methodHistory.getOldMember() != null && !((EntityHelper) getTopLevelHelper()).isMigrationCleanup()) {
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