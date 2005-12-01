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
 * Created on Sep 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.moduleextension;

import org.eclipse.jst.j2ee.internal.archive.operations.ImportOption;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;



/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public interface EarModuleExtension {

	/**
	 * Return a default instance of the J2EEModuleCreationDataModel.
	 * 
	 * @return
	 */
	IDataModel createProjectDataModel();

	IDataModel createImportDataModel();

	IDataModelOperation createProjectCreationOperation(IDataModel dataModel);

	IDataModelOperation createProjectCreationOperation(ImportOption option);
}
