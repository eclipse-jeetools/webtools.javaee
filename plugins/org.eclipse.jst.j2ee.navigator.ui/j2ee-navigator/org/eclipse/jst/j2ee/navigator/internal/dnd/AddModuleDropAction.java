/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.AddModuleToEARDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author mdelder
 *  
 */
public class AddModuleDropAction extends AddProjectToEarDropAction {

	public AddModuleDropAction() {
		super();
	}

	protected boolean validateProjectToAdd(IProject projectToAdd, int earVersion) {
		J2EENature moduleNature = J2EENature.getRegisteredRuntime(projectToAdd);
		if (null == moduleNature || moduleNature.getNatureID().equals(IEARNatureConstants.NATURE_ID) || moduleNature.getJ2EEVersion() > earVersion) {
			return false;
		}
		return true;
	}

	protected WTPOperationDataModel getDataModel(IProject earProject, IProject projectToAdd) {
		//TODO fix up to use module
		return AddModuleToEARDataModel.createAddToEARDataModel(null,null);
	}

}