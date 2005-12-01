/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jun 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class J2EEDeleteModuleActionPopulator {//implements WTPOperationDataModelUICreator {



	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.extension.ui.WTPOperationDataModelUICreator#createDataModel(java.lang.String,
	 *      java.lang.String, org.eclipse.jface.viewers.IStructuredSelection,
	 *      org.eclipse.ui.IWorkbenchSite)
	 */
	public IDataModel createDataModel(String extendedOperationId, String operationClass, IStructuredSelection selection, IWorkbenchSite site) {
		//TODO fix up
//		J2EEDeleteAction deleteAction = new J2EEDeleteAction(site, (ISelectionProvider) null);
//		WTPOperationDataModel dataModel = IActionWTPOperationDataModel.createDataModel(deleteAction, selection, site.getSelectionProvider(), site.getShell());
//		return dataModel;
		return null;
	}
}
