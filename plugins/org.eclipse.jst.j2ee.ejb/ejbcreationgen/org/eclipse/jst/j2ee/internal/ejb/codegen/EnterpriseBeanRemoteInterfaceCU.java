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



import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (10/5/00 2:10:53 PM)
 * 
 * @author: Steve Wasleski
 */
public abstract class EnterpriseBeanRemoteInterfaceCU extends JavaCompilationUnitGenerator {
	protected String fName = null;
	protected String fPackageName = null;

	/**
	 * CMPEntityRemoteInterfaceCU constructor comment.
	 */
	public EnterpriseBeanRemoteInterfaceCU() {
		super();
	}

	/**
	 * getName method comment.
	 */
	protected String getName() {
		if (fName == null)
			fName = Signature.getSimpleName(getClientInterfaceQualifiedName());
		return fName;
	}

	/**
	 * getPackageName method comment.
	 */
	protected String getPackageName() {
		if (fPackageName == null)
			fPackageName = Signature.getQualifier(getClientInterfaceQualifiedName());
		return fPackageName;
	}

	protected String getClientInterfaceQualifiedName() {
		EJBClassReferenceHelper helper = getClientClassReferenceHelper();
		if (helper != null)
			return helper.getJavaClass().getQualifiedName();
		return ((EnterpriseBean) getSourceElement()).getRemoteInterfaceName();
	}

	protected EJBClassReferenceHelper getClientClassReferenceHelper() {
		return ((EnterpriseBeanHelper) getTopLevelHelper()).getRemoteHelper();
	}

	/**
	 * Returns the logical name for the type generator.
	 */
	protected abstract String getTypeGeneratorName();

	/**
	 * Adds creation of the type generator.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		if (!getTopLevelHelper().isDeleteAll()) {
			IBaseGenerator typeGen = getGenerator(getTypeGeneratorName());
			typeGen.initialize(mofObject);
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator#createHistoryDescriptor(JavaCompilationUnitDescriptor)
	 */
	protected JavaCompilationUnitHistoryDescriptor createHistoryDescriptor(JavaCompilationUnitDescriptor desc) throws GenerationException {
		JavaCompilationUnitHistoryDescriptor historyDesc = super.createHistoryDescriptor(desc);
		EJBClassReferenceHelper helper = getClientClassReferenceHelper();
		if (helper != null && helper.isDelete())
			historyDesc.setDeleteOnly(true);
		return historyDesc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator#getDefaultPackageFragmentRoot()
	 */
	protected IPackageFragmentRoot getDefaultPackageFragmentRoot() throws GenerationException {
		IJavaProject clientProj = ((EnterpriseBeanHelper) getTopLevelHelper()).getEJBClientJavaProject();
		if (clientProj != null)
			return getFirstAvailablePackageFragmentRoot(clientProj);
		return super.getDefaultPackageFragmentRoot();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator#getJavaProject()
	 */
	protected IJavaProject getJavaProject() throws GenerationException {
		IJavaProject proj = ((EnterpriseBeanHelper) getTopLevelHelper()).getEJBClientJavaProject();
		if (proj == null)
			proj = super.getJavaProject();
		return proj;
	}
}