/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;


public class EJBAttributeMaintenanceFactoryImpl extends EjbAdapterFactory {
	public static final String ADAPTER_KEY = "EJBAttributeMaintenance"; //$NON-NLS-1$
	
	public EJBAttributeMaintenanceFactoryImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ejb.util.EjbAdapterFactory#createAdapter(org.eclipse.emf.common.notify.Notifier)
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		Adapter targetAdapter = (Adapter) modelSwitch.doSwitch((EObject)target);
		return ExtendedEcoreUtil.createAdapterForLoading(targetAdapter, (EObject) target);
	}


	@Override
	public Adapter createEJBRelationAdapter() {
		return new EJBRelationAttributeMaintenanceAdapter();
	}
	
	@Override
	public Adapter createRelationshipsAdapter() {
		return new RelationshipsAttributeMaintenanceAdapter();
	}

	@Override
	public boolean isFactoryForType(Object type) {
		return ADAPTER_KEY == type;
	}
}

