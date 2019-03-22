/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.util;

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
	 * @see org.eclipse.jst.j2ee.ejb.internal.util.CommonRelationshipAttributeMaintenanceAdapter#isRelationshipRolesSF(EObject)
	 */
	@Override
	protected boolean isRelationshipRolesSF(EObject sf) {
		return sf == EJB_PACK.getEJBRelation_RelationshipRoles();
	}

	/**
	 * @see org.eclipse.jst.j2ee.ejb.internal.util.CommonRelationshipAttributeMaintenanceAdapter#isRelationshipsSF(EObject)
	 */
	@Override
	protected boolean isRelationshipsSF(EObject sf) {
		return sf == EJB_PACK.getRelationships_EjbRelations();
	}

	/**
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(Object)
	 */
	@Override
	public boolean isAdapterForType(Object type) {
		return EJBAttributeMaintenanceFactoryImpl.ADAPTER_KEY == type;
	}

}

