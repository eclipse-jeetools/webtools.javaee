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
package org.eclipse.jst.j2ee.internal.ejb20.association.codegen;

import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;
import org.eclispe.jst.j2ee.internal.ejb.association.codegen.EJBRoleMemberGroupGenerator;


/**
 * Insert the type's description here. Creation date: (6/27/2001 9:12:59 PM)
 * 
 * @author: Administrator
 */
public class CMP20EntityBeanRoleGroupGenerator extends EJBRoleMemberGroupGenerator {
	/**
	 * Create the generator that will get the role instance.
	 */
	protected void createGetAccessorGenerator(RoleHelper helper) throws GenerationException {
		createMemberGenerator(IEJB20GenConstants.CMP20_ENTITY_BEAN_ROLE_GETTER, helper);
	}

	/**
	 * Create the generator that will get the role instance for a many role.
	 */
	protected void createManyGetAccessorGenerator(RoleHelper helper) throws GenerationException {
		createMemberGenerator(IEJB20GenConstants.CMP20_ENTITY_BEAN_ROLE_MANY_GETTER, helper);
	}

	/**
	 * Create the generator that will get the role instance for a many role.
	 */
	protected void createManySetAccessorGenerator(RoleHelper helper) throws GenerationException {
		createMemberGenerator(IEJB20GenConstants.CMP20_ENTITY_BEAN_ROLE_MANY_SETTER, helper);
	}

	protected void createMethodGenerators() throws GenerationException {
		RoleHelper helper = getRoleHelper();
		CommonRelationshipRole role = helper.getRole();
		if (role.isNavigable())
			createNavigableMethodGenerators(helper, role);
		else if (helper.isUpdate()) {
			CommonRelationshipRole oldRole = helper.getOldRole();
			if (oldRole.isNavigable()) {
				RoleHelper deleteHelper = new RoleHelper(oldRole);
				MetadataHistory history = new MetadataHistory();
				history.setOldMetadata(oldRole);
				deleteHelper.setMetadataHistory(history);
				deleteHelper.setDelete();
				createNavigableMethodGenerators(deleteHelper, oldRole);
			}
		}
	}

	protected void createNavigableMethodGenerators(RoleHelper helper, CommonRelationshipRole role) throws GenerationException {
		if (!isMany(role)) {
			createGetAccessorGenerator(helper);
			createSetAccessorGenerator(helper);
		} else {
			createManyGetAccessorGenerator(helper);
			createManySetAccessorGenerator(helper);
		}
	}

	/**
	 * Create the generator that will set the role instance.
	 */
	protected void createSetAccessorGenerator(RoleHelper helper) throws GenerationException {
		createMemberGenerator(IEJB20GenConstants.CMP20_ENTITY_BEAN_ROLE_SETTER, helper);
	}

	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		createMethodGenerators();
	}
}