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
 * Created on Dec 2, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.common.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.operations.UpdateManifestDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JARDependencyDataModel extends WTPOperationDataModel {

	/**
	 * String, the ear project name, required
	 */
	public static final String EAR_PROJECT_NAME = "AbstractJARDependencyDataModel.EAR_PROJECT_NAME"; //$NON-NLS-1$

	public static final String REFERENCED_PROJECT_NAME = "AbstractJARDependencyDataModel.REFERENCED_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * nested, required
	 */
	public static final String PROJECT_NAME = UpdateManifestDataModel.PROJECT_NAME;


	/**
	 * Used for client JAR dependency inversion
	 */
	public static final String OPPOSITE_PROJECT_NAME = "AbstractJARDependencyDataModel.OPPOSITE_PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * nested
	 */
	public static final String JAR_LIST = UpdateManifestDataModel.JAR_LIST;

	/**
	 * type Integer, default JAR_MANIPULATION_ADD, other possible values are JAR_MANIPULATION_REMOVE
	 * and JAR_MANIPULATION_INVERT
	 */
	public static final String JAR_MANIPULATION_TYPE = "AbstractJARDependencyDataModel.JAR_MANIPULATION_TYPE"; //$NON-NLS-1$
	public static final int JAR_MANIPULATION_ADD = 0;
	public static final int JAR_MANIPULATION_REMOVE = 1;
	public static final int JAR_MANIPULATION_INVERT = 2;

	private static final String NESTED_MODEL_UPDATE_MAINFEST = "AbstractJARDependencyDataModel.NESTED_MODEL_UPDATE_MAINFEST"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new JARDependencyOperation(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(EAR_PROJECT_NAME);
		addValidBaseProperty(REFERENCED_PROJECT_NAME);
		addValidBaseProperty(JAR_MANIPULATION_TYPE);
		addValidBaseProperty(OPPOSITE_PROJECT_NAME);
	}

	protected void initNestedModels() {
		super.initNestedModels();
		UpdateManifestDataModel updateManifestDataModel = new UpdateManifestDataModel();
		addNestedModel(NESTED_MODEL_UPDATE_MAINFEST, updateManifestDataModel);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(JAR_MANIPULATION_TYPE)) {
			return new Integer(JAR_MANIPULATION_ADD);
		}
		return super.getDefaultProperty(propertyName);
	}

	public IProject getEARProject() {
		return getProjectHandle(EAR_PROJECT_NAME);
	}

	/**
	 * @return
	 */
	public UpdateManifestDataModel getUpdateManifestDataModel() {
		return (UpdateManifestDataModel) getNestedModel(NESTED_MODEL_UPDATE_MAINFEST);
	}

	public IProject getProject() {
		return getUpdateManifestDataModel().getProject();
	}

	public IProject getReferencedProject() {
		return getProjectHandle(REFERENCED_PROJECT_NAME);
	}

	public IProject getOppositeProject() {
		return getProjectHandle(OPPOSITE_PROJECT_NAME);
	}

}