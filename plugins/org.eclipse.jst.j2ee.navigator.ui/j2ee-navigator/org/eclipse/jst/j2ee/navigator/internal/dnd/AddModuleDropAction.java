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
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author mdelder
 *  
 */
public class AddModuleDropAction extends AddProjectToEarDropAction {

	public AddModuleDropAction() {
		super();
	}

	protected boolean validateProjectToAdd(IProject projectToAdd, int earVersion) {
		//TODO fix up to use components
//		J2EENature moduleNature = J2EENature.getRegisteredRuntime(projectToAdd);
//		if (null == moduleNature || moduleNature.getNatureID().equals(IEARNatureConstants.NATURE_ID) || moduleNature.getJ2EEVersion() > earVersion) {
//			return false;
//		}
		return true;
	}

	protected IDataModel getDataModel(IProject earProject, IProject projectToAdd) {
		//TODO fix up to use components
//		return AddModuleToEARDataModel.createAddToEARDataModel(null,null);
		return null;
	}

	public boolean validateDrop(CommonDropAdapter dropAdapter, Object target, int operation, TransferData transferType) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean run(CommonDropAdapter dropAdapter, Object source, Object target) {
		// TODO Auto-generated method stub
		return false;
	}

}
