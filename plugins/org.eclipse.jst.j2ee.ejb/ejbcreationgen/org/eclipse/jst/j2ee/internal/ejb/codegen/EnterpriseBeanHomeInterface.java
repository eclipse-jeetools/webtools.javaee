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

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;


/**
 * Insert the type's description here. Creation date: (10/5/00 3:44:48 PM)
 * 
 * @author: Steve Wasleski
 */
public abstract class EnterpriseBeanHomeInterface extends EnterpriseBeanInterface {
	protected String fName = null;

	/**
	 * EnterpriseBeanRemoteInterface constructor comment.
	 */
	public EnterpriseBeanHomeInterface() {
		super();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		String typeString = isRemote() ? "Home" : "Local Home"; //$NON-NLS-1$ //$NON-NLS-2$
		return typeString + " interface for Enterprise Bean: " + ((EnterpriseBean) getSourceElement()).getName() + IEJBGenConstants.LINE_SEPARATOR; //$NON-NLS-1$
	}

	/**
	 * For a home it is always EJBHome.
	 */
	protected List getInheritedSuperInterfaceNames() {
		List inheritedInterfaces = new ArrayList();
		inheritedInterfaces.add(getBaseSuperInterfaceName());
		return inheritedInterfaces;
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		if (fName == null)
			fName = Signature.getSimpleName(getClientInterfaceQualifiedName());
		return fName;
	}

	/**
	 * This implementation sets the mofObject as the source element and checks for a home interface
	 * helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		EnterpriseBeanHelper ejbHelper = (EnterpriseBeanHelper) getTopLevelHelper();
		if (isRemote())
			setClassRefHelper(ejbHelper.getHomeHelper());
		else
			setClassRefHelper(ejbHelper.getLocalHomeHelper());
		super.initialize(mofObject);

		//For client view
		this.initializeClientView();
	}

	public boolean isRemote() {
		return true;
	}

	protected String getBaseSuperInterfaceName() {
		if (isRemote())
			return IEJBGenConstants.EJBHOME_INTERFACE_NAME;
		return IEJB20GenConstants.EJBLOCALHOME_INTERFACE_NAME;
	}

	protected String getClientInterfaceQualifiedName() {
		EJBClassReferenceHelper helper = getClassRefHelper();
		if (helper != null)
			return helper.getJavaClass().getQualifiedName();
		if (isRemote())
			return ((EnterpriseBean) getSourceElement()).getHomeInterfaceName();
		return ((EnterpriseBean) getSourceElement()).getLocalHomeInterfaceName();
	}

	/**
	 * Initializes the client view generators.
	 * 
	 * @throws GenerationException
	 */
	protected void initializeClientView() throws GenerationException {
		super.initializeClientView();
	}// initializeClientView

}