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
package org.eclipse.jst.j2ee.jca.internal.plugin;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationOperationOld;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.UpdateModuleReferencesInEARProjectCommand;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleExtensionImpl;
import org.eclipse.jst.j2ee.internal.moduleextension.JcaModuleExtension;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;


public class JcaModuleExtensionImpl extends EarModuleExtensionImpl implements JcaModuleExtension {

	/**
	 *  
	 */
	public JcaModuleExtensionImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.JcaModuleExtension#initializeEjbReferencesToModule(org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature,
	 *      org.eclipse.jst.j2ee.internal.internal.internal.earcreation.UpdateModuleReferencesInEARProjectCommand)
	 */
	public void initializeEjbReferencesToModule(J2EENature moduleNature, UpdateModuleReferencesInEARProjectCommand cmd) {
		//no references to EJBs
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.JcaModuleExtension#createProjectInfo()
	 */
	//	public J2EEJavaProjectInfo createProjectInfo() {
	//		return new ConnectorProjectInfo();
	//	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.JcaModuleExtension#createImportOperation(org.eclipse.core.resources.IProject,
	 *      org.eclipse.jst.j2ee.internal.internal.commonarchivecore.RARFile)
	 */
	//	public J2EEImportOperation createImportOperation(IProject proj, RARFile rarFile){
	//		return new RARImportOperation(proj, rarFile);
	//	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#getNatureID()
	 */
	public String getNatureID() {
		return IConnectorNatureConstants.NATURE_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectCreationOperation(org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEModuleCreationDataModel)
	 */
	public J2EEModuleCreationOperationOld createProjectCreationOperation(J2EEModuleCreationDataModelOld dataModel) {
		return new ConnectorModuleCreationOperation((ConnectorModuleCreationDataModel) dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createProjectDataModel()
	 */
	public J2EEModuleCreationDataModelOld createProjectDataModel() {
		return new ConnectorModuleCreationDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.moduleextension.EarModuleExtension#createImportDataModel()
	 */
	public J2EEModuleImportDataModel createImportDataModel() {
		return new ConnectorModuleImportDataModel();
	}
}