/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.util;

import org.eclipse.emf.ecore.EObject;

public class EJBRelationAttributeMaintenanceAdapter extends CommonRelationshipAttributeMaintenanceAdapter {
	/**
	 * Constructor for EJBRelationAttributeMaintenanceAdapter.
	 * @param aRelationship
	 */
	public EJBRelationAttributeMaintenanceAdapter() {
		super();
	}

	/**
	 * @see org.eclipse.jst.j2ee.ejb.util.CommonRelationshipAttributeMaintenanceAdapter#isRelationshipRolesSF(EObject)
	 */
	protected boolean isRelationshipRolesSF(EObject sf) {
		return sf == EJB_PACK.getEJBRelation_RelationshipRoles();
	}

	/**
	 * @see org.eclipse.jst.j2ee.ejb.util.CommonRelationshipAttributeMaintenanceAdapter#isRelationshipsSF(EObject)
	 */
	protected boolean isRelationshipsSF(EObject sf) {
		return sf == EJB_PACK.getRelationships_EjbRelations();
	}

	/**
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(Object)
	 */
	public boolean isAdapterForType(Object type) {
		return EJBAttributeMaintenanceFactoryImpl.ADAPTER_KEY == type;
	}

}

