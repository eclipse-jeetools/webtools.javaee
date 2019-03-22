/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 29, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.moduleextension;

import org.eclipse.jst.j2ee.internal.archive.operations.ImportOption;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


public abstract class EarModuleExtensionImpl implements EarModuleExtension {
	/**
	 *  
	 */
	public EarModuleExtensionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectCreationOperation(com.ibm.etools.archive.ear.operations.ImportOption)
	 */
	@Override
	public IDataModelOperation createProjectCreationOperation(ImportOption option) {
		if (option.getArchiveType() == ImportOption.MODULE)
			return option.getModel().getDefaultOperation();
		return null;
	}

	@Override
	public IDataModelOperation createProjectCreationOperation(IDataModel dataModel) {
		return dataModel.getDefaultOperation();
	}

}
