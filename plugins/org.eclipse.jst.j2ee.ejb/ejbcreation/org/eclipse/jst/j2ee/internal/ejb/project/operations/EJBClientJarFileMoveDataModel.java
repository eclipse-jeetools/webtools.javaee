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
 * Created on May 20, 2004
 *
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author dfholt
 *  
 */
public class EJBClientJarFileMoveDataModel extends WTPOperationDataModel {

	/**
	 * Required, type String
	 */
	public static final String EJB_PROJECT_NAME = "EJBClientJarFileMoveDataModel.EJB_PROJECT_NAME"; //$NON-NLS-1$
	/**
	 * Required, type String
	 */
	public static final String CLIENT_PROJECT_NAME = "EJBClientJarFileMoveDataModel.CLIENT_PROJECT_NAME"; //$NON-NLS-1$
	/**
	 * Required, type Map see EJBClientJarCreationOperation for api for adding files
	 */
	public static final String FILES_TO_MOVE_MAP = "EJBClientJarFileMoveDataModel.FILES_TO_MOVE_MAP"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(EJB_PROJECT_NAME);
		addValidBaseProperty(CLIENT_PROJECT_NAME);
		addValidBaseProperty(FILES_TO_MOVE_MAP);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new EJBClientJarFileMoveOperation(this);
	}

}