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
package org.eclispe.jst.j2ee.internal.ejb.association.codegen;


import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


/**
 * Insert the type's description here. Creation date: (5/3/2001 9:01:55 AM)
 * 
 * @author: Administrator
 */
public abstract class EJBRoleMemberGroupGenerator extends org.eclipse.jst.j2ee.internal.java.codegen.JavaMemberGroupGenerator {
	private RoleHelper cleanUpRoleHelper;

	/**
	 * EJBLinkMemberGroupGenerator constructor comment.
	 */
	public EJBRoleMemberGroupGenerator() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (8/14/2001 8:19:16 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.RoleHelper
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper createCleanUpRoleHelper() {
		RoleHelper helper = new RoleHelper(getRoleHelper().getRole());
		helper.setMetadataHistory(new MetadataHistory());
		helper.getMetadataHistory().setOldMetadata(getRoleHelper().getOldRole());
		helper.setDelete();
		return helper;
	}

	/**
	 * This implementation initializes the generators with the RoleHelper.
	 */
	protected void createMemberGenerator(String aGeneratorName) throws GenerationException {
		createMemberGenerator(aGeneratorName, getSourceElement());
	}

	/**
	 * This implementation initializes the generators with the RoleHelper.
	 */
	protected void createMemberGenerator(String aGeneratorName, Object anObject) throws GenerationException {
		IBaseGenerator memberGen = getGenerator(aGeneratorName);
		memberGen.initialize(anObject);
	}

	/**
	 * Insert the method's description here. Creation date: (8/14/2001 8:19:16 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.RoleHelper
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper getCleanUpRoleHelper() {
		if (cleanUpRoleHelper == null)
			cleanUpRoleHelper = createCleanUpRoleHelper();
		return cleanUpRoleHelper;
	}

	/**
	 * Subclasses must implement to get the member name from the source model.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return ((RoleHelper) getSourceElement()).getName();
	}

	protected RoleHelper getRoleHelper() {
		return (RoleHelper) getSourceElement();
	}

	protected boolean isMany(CommonRelationshipRole aRole) {
		return RoleHelper.isMany(aRole);
	}

	protected boolean isUpdate() {
		return getRoleHelper().isUpdate();
	}
}