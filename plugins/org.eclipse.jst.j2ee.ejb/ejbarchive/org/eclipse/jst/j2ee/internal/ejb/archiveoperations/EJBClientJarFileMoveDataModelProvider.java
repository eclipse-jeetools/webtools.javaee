/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.Set;

import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


public class EJBClientJarFileMoveDataModelProvider
 						extends AbstractDataModelProvider
 						implements IEJBClientJarFileMoveDataModelProperties{
    
	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EJB_CLIENTVIEW_PROJECT_NAME);
		propertyNames.add(EJB_PROJECT_NAME);
		propertyNames.add(FILES_TO_MOVE_MAP);
		return propertyNames;
	}
	

	@Override
	public IDataModelOperation getDefaultOperation() {
		return new EJBClientJarFileMoveOperation( model );
	}

}
