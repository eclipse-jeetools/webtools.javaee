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
/*
 * Created on Oct 29, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;

import java.util.List;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.commands.EJBCopyGroup;
import org.eclipse.jst.j2ee.internal.ejb.commands.IEJBCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.wst.common.emf.utilities.CopyGroup;



/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments TODO 1. Needs comments for api, 2.collapse extend
 * EjbModuleExtensionHelper
 */
public interface IEJBCodegenHandler {
	public static final String GENRALIZATION_HELPER_KEY = "EJB_GENERALIZATION_HELPER"; //$NON-NLS-1$

	boolean isBecomingRootEJB(EnterpriseBean anEJB, List extendedHelpers);

	boolean isChangingInheritance(EnterpriseBean anEJB, List extendedHelpers);

	/**
	 * 
	 * @param ejb
	 * @return
	 * @deprecated TODO remove from this api
	 */
	List get11Roles(EnterpriseBean ejb);

	CommonRelationshipRole getOldRole(EnterpriseBean anEJB, String roleName);

	/**
	 * This will be deleted. Not needed.
	 * 
	 * @deprecated TODO remove from this api
	 */
	boolean hasLocalRelationshipRoles(EnterpriseBean anEJB);

	List getExtendedEjb11RelationshipRolesWithType(EnterpriseBean anEJB);

	/**
	 * Return true if the provider has any extension documents that contain references to the
	 * objects in the ejb deployment descriptor.
	 * 
	 * @param editModel
	 * @return
	 */
	boolean hasDefinedExtensions(EJBEditModel editModel); //command copier methods

	/**
	 * For all objects in the passed copy group, return a new copy group that contains all the
	 * necessary extension objects required by code generation. This is in order to return the old
	 * extension objects during code generatation.
	 * 
	 * @param copyGrp
	 * @param editModel
	 * @return
	 * @see #getOldRole(EnterpriseBean, String)
	 */
	CopyGroup getEJBExtensionCopyGroup(EJBCopyGroup copyGrp, EJBEditModel editModel);

	/**
	 * 
	 * @deprecated TODO remove this method and all references
	 */
	Object getDependentCommandAdaptor();

	/**
	 * Create any commands necessary for inheritance code generation and append to the parent
	 * 
	 * @param supertype
	 * @param parentCommand
	 */
	void createEJBInheritanceCommand(EnterpriseBean supertype, IEJBCommand parentCommand);

}