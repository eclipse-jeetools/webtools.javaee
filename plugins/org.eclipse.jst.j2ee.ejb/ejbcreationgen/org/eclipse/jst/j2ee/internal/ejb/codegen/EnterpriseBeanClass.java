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

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaClassGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (10/5/00 3:44:48 PM)
 * 
 * @author: Steve Wasleski
 */
public abstract class EnterpriseBeanClass extends JavaClassGenerator {
	private EJBClassReferenceHelper fClassRefHelper = null;

	/**
	 * EnterpriseBeanRemoteInterface constructor comment.
	 */
	public EnterpriseBeanClass() {
		super();
	}

	/**
	 * This implementation initializes the generators with the EJB.
	 */
	protected void createRequiredMemberGenerators() throws GenerationException {
		IBaseGenerator memberGen = null;
		Iterator names = getRequiredMemberGeneratorNames().iterator();
		while (names.hasNext()) {
			memberGen = getGenerator((String) names.next());
			memberGen.initialize(getSourceElement());
		}
	}

	/**
	 * Return either the actual role helpers or the key propagation helpers.
	 */
	protected List getActualRoleHelpers() {
		return ((EntityHelper) getTopLevelHelper()).getRoleHelpers();
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/00 4:43:02 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	public org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper getClassRefHelper() {
		return fClassRefHelper;
	}

	/**
	 * Implement to return the correct inherited interface.
	 */
	protected abstract List getInheritedSuperInterfaceNames();

	/**
	 * Override to get required member generator names.
	 */
	protected abstract List getRequiredMemberGeneratorNames() throws GenerationException;

	/**
	 * Return either the actual role helpers or the key propagation helpers.
	 */
	protected List getRoleHelpers() {
		return ((EntityHelper) getTopLevelHelper()).getRoleOrKeyPropagationHelpers();
	}

	/**
	 * Checks for helper specified inheritance.
	 * 
	 * @return java.lang.String
	 */
	protected String getSuperclassName() throws GenerationException {
		if (getClassRefHelper() == null) {
			//get the superclassName of the existing ejbClass
			EnterpriseBeanHelper ejbHelper = (EnterpriseBeanHelper) getTopLevelHelper();
			EnterpriseBean ejb = ejbHelper.getEjb();
			JavaClass javaClass = getJavaTypeClass(ejb);
			if (ejb != null && javaClass != null) {
				JavaClass realSuperClass = javaClass.getSupertype();
				if (realSuperClass != null) {
					String superName = realSuperClass.getQualifiedName();
					if (!IEJBGenConstants.OBJECT_CLASS_NAME.equals(superName))
						return superName;
				}
			}
		} else
			return getClassRefHelper().getSuperclassName();
		return null;
	}

	protected abstract JavaClass getJavaTypeClass(EnterpriseBean ejb);

	/**
	 * Remote interfaces extend another remote interface or javax.ejb.EJBObject.
	 * 
	 * @return java.lang.String[]
	 */
	protected String[] getSuperInterfaceNames() throws GenerationException {
		List namesList = new ArrayList();

		// Get the other interfaces from the helper.
		if (getClassRefHelper() != null)
			namesList.addAll(getClassRefHelper().getSuperInterfaceNames());

		// Get the inherited interfaces.
		namesList.addAll(getInheritedSuperInterfaceNames());

		return (String[]) namesList.toArray(new String[namesList.size()]);
	}

	/**
	 * This implementation sets the mofObject as the source element and sets history.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		if (getTopLevelHelper().isDeleteAll())
			return;

		// If we have a class ref helper and this is not a create, we need a history
		// descriptor for the update or delete.
		if ((fClassRefHelper != null) && (!fClassRefHelper.isCreate())) {
			JavaMemberHistoryDescriptor historyDesc = new JavaMemberHistoryDescriptor();
			if (fClassRefHelper.getOldName() != null)
				historyDesc.setName(fClassRefHelper.getOldName());
			else
				historyDesc.setName(getName());
			historyDesc.setDeleteOnly(fClassRefHelper.isDelete());
			setHistoryDescriptor(historyDesc);
		}

		if (fClassRefHelper != null && fClassRefHelper.isDelete())
			return;
		// Create the required member generators.
		createRequiredMemberGenerators();
	}

	public boolean isKeyGenerator() {
		return false;
	}

	/**
	 * Add helper imports then let the type run.
	 */
	public void run() throws GenerationException {
		// Get the import names from the helper and add them.
		if (getClassRefHelper() != null) {
			Iterator helperImports = getClassRefHelper().getImportNames().iterator();
			JavaCompilationUnitGenerator cuGen = getCompilationUnitGenerator();
			while (helperImports.hasNext())
				cuGen.addImport((String) helperImports.next());
		}
		super.run();
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/00 4:43:02 PM)
	 * 
	 * @param newClassRefHelper
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	protected void setClassRefHelper(org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper newClassRefHelper) {
		fClassRefHelper = newClassRefHelper;
	}
}