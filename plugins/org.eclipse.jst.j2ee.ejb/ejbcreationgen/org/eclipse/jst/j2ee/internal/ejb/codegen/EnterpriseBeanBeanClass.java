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



import org.eclipse.jdt.core.Signature;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;


/**
 * Insert the type's description here. Creation date: (10/11/00 9:45:44 AM)
 * 
 * @author: Steve Wasleski
 */
public abstract class EnterpriseBeanBeanClass extends EnterpriseBeanClass {
	private String fName = null;

	/**
	 * EnterpriseBeanBeanClass constructor comment.
	 */
	public EnterpriseBeanBeanClass() {
		super();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Bean implementation class for Enterprise Bean: " + ((EnterpriseBean) getSourceElement()).getName() + IBaseGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		if (fName == null)
			fName = Signature.getSimpleName(((EnterpriseBean) getSourceElement()).getEjbClassName());
		return fName;
	}

	/**
	 * Return the supertype Entity for the sourceElement.
	 * 
	 * @return Entity
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
	 * Checks for EJB inheritance. If none let the super check the helper.
	 * 
	 * @return java.lang.String
	 */
	protected String getSuperclassName() throws GenerationException {
		String name = null;
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		EnterpriseBean oldSuperEJB, superEJB;
		oldSuperEJB = helper.getOldSupertype();
		superEJB = getSourceSupertype();
		if (oldSuperEJB != null && helper.isEJBRemoved())
			return oldSuperEJB.getEjbClassName();
		if (oldSuperEJB != null && superEJB == null)
			return null;
		if (superEJB == null || (oldSuperEJB != null && superEJB.getEjbClassName().equals(oldSuperEJB.getEjbClassName())))
			name = super.getSuperclassName();
		else
			name = superEJB.getEjbClassName();
		return name;
	}

	/**
	 * @see org.eclipse.jst.j2ee.ejb.codegen.EnterpriseBeanClass#getJavaTypeClass(EnterpriseBean)
	 */
	protected JavaClass getJavaTypeClass(EnterpriseBean ejb) {
		return ejb.getEjbClass();
	}

	/**
	 * Return the supertype Entity for the sourceElement.
	 * 
	 * @return Entity
	 */
	protected boolean hasSourceSupertype() {
		return getSourceSupertype() != null;
	}

	/**
	 * This implementation sets the mofObject as the source element and checks for a bean class
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		setClassRefHelper(((EnterpriseBeanHelper) getTopLevelHelper()).getBeanHelper());
		super.initialize(mofObject);
	}
}