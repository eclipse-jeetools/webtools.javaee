/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Sep 29, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.moduleextension;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.archive.operations.ImportOption;
import org.eclipse.jst.j2ee.internal.project.J2EENature;


public abstract class EarModuleExtensionImpl implements EarModuleExtension {
	/**
	 *  
	 */
	public EarModuleExtensionImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.moduleextension.EarModuleExtension#getNature(org.eclipse.core.internal.resources.Project)
	 */
	public J2EENature getNature(Project proj) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.moduleextension.EarModuleExtension#getNatureID()
	 */
	public abstract String getNatureID();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.moduleextension.EarModuleExtension#createProjectCreationOperation(com.ibm.etools.archive.ear.operations.ImportOption)
	 */
	public J2EEModuleCreationOperation createProjectCreationOperation(ImportOption option) {
		if (option.getArchiveType() == ImportOption.MODULE)
			return createProjectCreationOperation((J2EEModuleCreationDataModel) option.getModel());
		return null;
	}

}