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
import org.eclipse.jdt.core.jdom.IDOMType;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberHistoryDescriptor;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTypeMerglet;
import org.eclipse.jst.j2ee.internal.java.codegen.MergeResults;


/**
 * Insert the type's description here. Creation date: (10/5/00 3:44:48 PM)
 * 
 * @author: Steve Wasleski
 */
public class EnterpriseBeanClientInterface extends EnterpriseBeanInterface {
	protected String fName = null;

	/**
	 * EnterpriseBeanRemoteInterface constructor comment.
	 */
	public EnterpriseBeanClientInterface() {
		super();
	}

	/**
	 * Dispatch to the merge strategy. This method is here simply to provide a hook for derived
	 * generators.
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMemberHistoryDescriptor typeHistory, IDOMType newType) throws GenerationException {
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		if (helper != null && !helper.isDeleteAll() && hasInterfacesToRemove()) {
			JavaTypeMerglet merglet = (JavaTypeMerglet) getCompilationUnitGenerator().getMergeStrategy().createDefaultTypeMerglet();
			merglet.setNoMergeSuperInterfaceNames(getInterfaceNamesToRemove());
			return getCompilationUnitGenerator().getMergeStrategy().merge(typeHistory, newType, merglet);
		}
		return super.dispatchToMergeStrategy(typeHistory, newType);
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		String typeString = getCommentTypeName();
		return typeString + " interface for Enterprise Bean: " + ((EnterpriseBean) getSourceElement()).getName() + IEJBGenConstants.LINE_SEPARATOR; //$NON-NLS-1$
	}

	protected String getCommentTypeName() {
		return isRemote() ? "Remote" : "Local"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * For a remote it is either the inherited remote or EJBObject.
	 */
	protected List getInheritedSuperInterfaceNames() {
		// Get the inherited interface or EJBObject.
		List names = new ArrayList();
		EnterpriseBean superEJB = getSourceSupertype();
		if (superEJB == null)
			names.add(getBaseSuperInterfaceName());
		else {
			if (isRemote())
				names.add(superEJB.getRemoteInterfaceName());
			else
				names.add(superEJB.getLocalInterfaceName());
		}
		return names;
	}

	protected String[] getInterfaceNamesToRemove() {
		if (!hasSuptertypeInterfacesToRemove())
			return null;
		EnterpriseBean oldSuper = ((EnterpriseBeanHelper) getTopLevelHelper()).getOldSupertype();
		if (oldSuper != null) {
			String oldSuperName = isRemote() ? oldSuper.getRemoteInterfaceName() : oldSuper.getLocalInterfaceName();
			return new String[]{oldSuperName};
		}
		return new String[]{getBaseSuperInterfaceName()};
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		if (fName == null)
			fName = Signature.getSimpleName(getClientInterfaceQualifiedName());
		return fName;
	}

	protected String getClientInterfaceQualifiedName() {
		EJBClassReferenceHelper helper = getClassRefHelper();
		if (helper != null)
			return helper.getJavaClass().getQualifiedName();
		EnterpriseBean ejb = (EnterpriseBean) getSourceElement();
		if (isRemote())
			return ejb.getRemoteInterfaceName();
		return ejb.getLocalInterfaceName();
	}

	/**
	 * Override to get required member generator names.
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		return new ArrayList();
	}

	protected boolean hasInterfacesToRemove() {
		return hasSuptertypeInterfacesToRemove();
	}

	protected boolean hasSuptertypeInterfacesToRemove() {
		return ((EnterpriseBeanHelper) getTopLevelHelper()).isChangingInheritance();
	}

	/**
	 * This implementation sets the mofObject as the source element and checks for a remote
	 * interface helper.
	 */
	public void initialize(Object mofObject) throws GenerationException {
		EnterpriseBeanHelper ejbHelper = (EnterpriseBeanHelper) getTopLevelHelper();
		initilializeClassRefHelper(ejbHelper);
		super.initialize(mofObject);

		//For client view creation
		this.initializeClientView();

	}

	/**
	 * @param ejbHelper
	 */
	protected void initilializeClassRefHelper(EnterpriseBeanHelper ejbHelper) {
		if (isRemote())
			setClassRefHelper(ejbHelper.getRemoteHelper());
		else
			setClassRefHelper(ejbHelper.getLocalHelper());
	}

	public boolean isRemote() {
		return true;
	}

	protected String getBaseSuperInterfaceName() {
		if (isRemote())
			return IEJBGenConstants.EJBOBJECT_INTERFACE_NAME;
		return IEJB20GenConstants.EJBOBJECT_LOCAL_INTERFACE_NAME;
	}
}