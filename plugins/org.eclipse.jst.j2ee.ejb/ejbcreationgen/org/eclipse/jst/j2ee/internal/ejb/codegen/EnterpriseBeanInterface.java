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



import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaCompilationUnitGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaInterfaceGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;


/**
 * Insert the type's description here. Creation date: (10/5/00 3:44:48 PM)
 * 
 * @author: Steve Wasleski
 */
public abstract class EnterpriseBeanInterface extends JavaInterfaceGenerator {
	private EJBClassReferenceHelper fClassRefHelper = null;

	/**
	 * EnterpriseBeanRemoteInterface constructor comment.
	 */
	public EnterpriseBeanInterface() {
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
	 * Insert the method's description here. Creation date: (10/13/00 4:49:19 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	protected EJBClassReferenceHelper getClassRefHelper() {
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
	 * Return the supertype EnterpriseBean for the sourceElement.
	 * 
	 * @return EnterpriseBean
	 */
	protected EnterpriseBean getSourceSupertype() {
		EnterpriseBean entity = (EnterpriseBean) getSourceElement();
		EjbModuleExtensionHelper helper = getEjbModuleExtension();
		if (helper != null)
			return helper.getSuperType(entity);
		return null;
	}

	protected EjbModuleExtensionHelper getEjbModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/**
	 * Remote interfaces extend another remote interface or javax.ejb.EJBObject.
	 * 
	 * @return java.lang.String[]
	 */
	protected String[] getSuperInterfaceNames() throws GenerationException {
		Set namesList = new HashSet();

		// Get the other interfaces from the helper.
		if (getClassRefHelper() != null)
			namesList.addAll(getClassRefHelper().getSuperInterfaceNames());

		// Get the inherited interfaces.
		namesList.addAll(getInheritedSuperInterfaceNames());

		return (String[]) namesList.toArray(new String[namesList.size()]);
	}

	/**
	 * This implementation sets the mofObject as the source element and checks for a remote
	 * interface helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);

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
	 * Insert the method's description here. Creation date: (10/13/00 4:49:19 PM)
	 * 
	 * @param newClassRefHelper
	 *            org.eclipse.jst.j2ee.ejb.codegen.helpers.EJBClassReferenceHelper
	 */
	protected void setClassRefHelper(org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper newClassRefHelper) {
		fClassRefHelper = newClassRefHelper;
	}

	/**
	 * Initializes the client view generators.
	 * 
	 * @throws GenerationException
	 */
	protected void initializeClientView() throws GenerationException {
		IBaseGenerator generator = null;

		if (this.getClassRefHelper() != null && this.getClassRefHelper().getMethodCollection() != null) {

			generator = getGenerator(IEJBGenConstants.EJB_METHOD_GROUP_GENERATOR);
			generator.initialize(this.getClassRefHelper());
		}// if

	}// initializeClientView

}