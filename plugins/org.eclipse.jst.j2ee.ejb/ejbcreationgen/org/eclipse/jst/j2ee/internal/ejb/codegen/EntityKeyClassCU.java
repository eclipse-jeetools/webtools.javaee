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
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (10/5/00 2:10:53 PM)
 * 
 * @author: Steve Wasleski
 */
public class EntityKeyClassCU extends JavaCompilationUnitGenerator {
	private String fName = null;
	private String fPackageName = null;

	/**
	 * CMPEntityRemoteInterfaceCU constructor comment.
	 */
	public EntityKeyClassCU() {
		super();
	}

	/**
	 * getName method comment.
	 */
	protected String getName() {
		if (fName == null) {
			EJBClassReferenceHelper keyHelper = getKeyClassHelper();
			if (keyHelper != null && keyHelper.isDelete() && keyHelper.getJavaClass() != null)
				fName = Signature.getSimpleName(keyHelper.getJavaClass().getQualifiedName());
			else
				fName = Signature.getSimpleName(((Entity) getSourceElement()).getPrimaryKeyName());
		}
		return fName;
	}

	/**
	 * getPackageName method comment.
	 */
	protected String getPackageName() {
		if (fPackageName == null) {
			EJBClassReferenceHelper keyHelper = getKeyClassHelper();
			if (keyHelper != null && keyHelper.isDelete() && keyHelper.getJavaClass() != null)
				fPackageName = Signature.getQualifier(keyHelper.getJavaClass().getQualifiedName());
			else
				fPackageName = Signature.getQualifier(((Entity) getSourceElement()).getPrimaryKeyName());
		}
		return fPackageName;
	}

	protected EJBClassReferenceHelper getKeyClassHelper() {
		return ((EntityHelper) getTopLevelHelper()).getKeyHelper();
	}

	/**
	 * Returns the logical name for the type generator.
	 */
	protected String getTypeGeneratorName() {
		return IEJBGenConstants.ENTITY_KEY_CLASS;
	}

	/**
	 * Adds creation of the type generator.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		EJBClassReferenceHelper keyHelper = getKeyClassHelper();
		if (keyHelper != null && !keyHelper.isCreate()) {
			JavaCompilationUnitHistoryDescriptor historyDesc = new JavaCompilationUnitHistoryDescriptor();
			historyDesc.setName(getName());
			historyDesc.setPackageName(getPackageName());
			historyDesc.setDeleteOnly(keyHelper.isDelete());
			setHistoryDescriptor(historyDesc);
		}

		if (keyHelper != null && keyHelper.isDelete())
			return;
		IBaseGenerator typeGen = getGenerator(getTypeGeneratorName());
		typeGen.initialize(mofObject);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaCompilationUnitGenerator#getDefaultPackageFragmentRoot()
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
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaCompilationUnitGenerator#getJavaProject()
	 */
	protected IJavaProject getJavaProject() throws GenerationException {
		IJavaProject proj = ((EnterpriseBeanHelper) getTopLevelHelper()).getEJBClientJavaProject();
		if (proj == null)
			proj = super.getJavaProject();
		return proj;
	}
}