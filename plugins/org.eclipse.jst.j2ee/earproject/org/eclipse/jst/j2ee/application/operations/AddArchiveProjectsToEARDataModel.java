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
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddArchiveProjectsToEARDataModel extends EditModelOperationDataModel {
	/**
	 * Requred. This must be a list of IProjects. This list must contain all projects being added
	 * even if there is a corresponding model in the MODULE_MODELS list.
	 */
	public static final String MODULE_LIST = "AddArchiveProjectsToEARDataModel.MODULE_LIST"; //$NON-NLS-1$

	/**
	 * Optional - This is a list of AddArchiveProjectsToEARDataModel objects. This allows for non
	 * default URIs or other overrides based on the module type.
	 */
	public static final String MODULE_MODELS = "AddArchiveProjectsToEARDataModel.MODULE_MODELS"; //$NON-NLS-1$

	/**
	 * This is a convenience property to return models for all projects in the MODULE_LIST. So you
	 * could have some module models defined with overrides and this property will ensure that a
	 * model gets created for projects in the MODULE_LIST that do not have a model.
	 */
	public static final String ALL_MODULE_MODELS = "AddModulesToEARDataModel.ALL_MODULE_MODELS"; //$NON-NLS-1$

	public WTPOperation getDefaultOperation() {
		return new AddArchiveProjectsToEAROperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#init()
	 */
	protected void init() {
		super.init();
		setProperty(EDIT_MODEL_ID, IEARNatureConstants.EDIT_MODEL_ID);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(MODULE_LIST);
		addValidBaseProperty(MODULE_MODELS);
		addValidBaseProperty(ALL_MODULE_MODELS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(MODULE_LIST) || propertyName.equals(MODULE_MODELS)) {
			return Collections.EMPTY_LIST;
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doGetProperty(java.lang.String)
	 */
	protected Object doGetProperty(String propertyName) {
		if (propertyName.equals(ALL_MODULE_MODELS)) {
			return computeAllModuleModels();
		}
		return super.doGetProperty(propertyName);
	}

	/**
	 * @return
	 */
	private List computeAllModuleModels() {
		List projects = (List) getProperty(MODULE_LIST);
		List models = (List) getProperty(MODULE_MODELS);
		if (!projects.isEmpty())
			return computeAllArchiveModels(projects, models);
		return models;
	}

	/**
	 * @param projects
	 * @param models
	 * @return
	 */
	private List computeAllArchiveModels(List projects, List models) {
		List allModels = new ArrayList(projects.size());
		String earProjName = getStringProperty(PROJECT_NAME);
		IProject project = null;
		AddArchiveProjectToEARDataModel model = null;
		for (int i = 0; i < projects.size(); i++) {
			project = (IProject) projects.get(i);
			model = findOrCreateModel(project, models);
			if (model != null) {
				model.setProperty(AddArchiveProjectToEARDataModel.PROJECT_NAME, earProjName);
				allModels.add(model);
			}
		}
		return allModels;
	}

	/**
	 * @param project
	 * @param models
	 * @return
	 */
	private AddArchiveProjectToEARDataModel findOrCreateModel(IProject project, List models) {
		if (!models.isEmpty()) {
			AddArchiveProjectToEARDataModel model = null;
			for (int i = 0; i < models.size(); i++) {
				model = (AddArchiveProjectToEARDataModel) models.get(i);
				if (project.equals(model.getProperty(AddArchiveProjectToEARDataModel.ARCHIVE_PROJECT)))
					return model;
			}
		}
		//Not found so we need to create one.
		return AddArchiveProjectToEARDataModel.createArchiveModel(project);
	}

}