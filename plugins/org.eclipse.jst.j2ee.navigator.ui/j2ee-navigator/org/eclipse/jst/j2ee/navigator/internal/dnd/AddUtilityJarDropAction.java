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
/*
 * Created on Jun 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal.dnd;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.application.operations.AddArchiveProjectToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.AddUtilityProjectToEARDataModel;
import org.eclipse.jst.j2ee.internal.wizard.ServerTargetUIHelper;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author jsholl
 *  
 */
public class AddUtilityJarDropAction extends AddProjectToEarDropAction {

	public AddUtilityJarDropAction() {
		super();
	}

	protected boolean validateProjectToAdd(IProject projectToAdd, int earVersion) {
		try {
			return projectToAdd.hasNature(JavaCore.NATURE_ID);
		} catch (CoreException e) {
			//Ignore
		}
		return false;
	}

	protected WTPOperationDataModel getDataModel(IProject earProject, IProject projectToAdd) {
		WTPOperationDataModel dataModel = null;
		boolean syncServerTarget = ServerTargetUIHelper.setModuleServerTargetIfNecessary(earProject, projectToAdd, getShell());
		dataModel = AddUtilityProjectToEARDataModel.createAddToEARDataModel(earProject.getName(), projectToAdd);
		dataModel.setBooleanProperty(AddArchiveProjectToEARDataModel.SYNC_TARGET_RUNTIME, syncServerTarget);
		return dataModel;
	}

}