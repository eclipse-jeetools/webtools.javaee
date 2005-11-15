/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class RemoveDeletedComponentFromEAROperation extends
		RemoveComponentFromEnterpriseApplicationOperation {

	private final ProjectRefactorMetadata refactoredMetadata;
	
	public RemoveDeletedComponentFromEAROperation(final IDataModel model, 
			final ProjectRefactorMetadata metadata) {
		super(model);
		refactoredMetadata = metadata;
	}
	
	/**
	 * Override to use cached metadata to determine project type.
	 */
	protected void removeModule(Application application, IVirtualComponent wc) {
		Application dd = application;
		String name = wc.getName();
		if (refactoredMetadata.isWeb()) {
			name += ".war"; //$NON-NLS-1$			
		} else if (refactoredMetadata.isEJB()) {
			name += ".jar"; //$NON-NLS-1$			
		} else if (refactoredMetadata.isAppClient()) {
			name += ".jar"; //$NON-NLS-1$			
		} else if (refactoredMetadata.isConnector()) {
			name += ".rar"; //$NON-NLS-1$			
		}
		Module existingModule = dd.getFirstModule(name);
		dd.getModules().remove(existingModule);

	}
	
}
