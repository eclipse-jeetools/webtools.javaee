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
package org.eclipse.jst.j2ee.ejb.internal.operations;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author jlanuti
 *  
 */
public class DeleteEnterpriseBeanDataModel extends EditModelOperationDataModel {

	public static final String DELETE_BEANS_ACTION_ID = "com.ibm.wtp.generic.Delete"; //$NON-NLS-1$
	public static final String DELETE_BEANS_ACTION_NAME = EJBCodeGenResourceHandler.getString("DeleteEnterpriseBeanDataModel_UI_1"); //$NON-NLS-1$

	public static final String BEANS_TO_BE_DELETED = "DeleteEnterpriseBeanDataModel.BEANS_TO_BE_DELETED"; //$NON-NLS-1$

	public static final String SHELL = "DeleteEnterpriseBeanDataModel.SHELL"; //$NON-NLS-1$

	/**
	 * @deprecated no longer used
	 */
	protected IOperationHandler operationHandler = null;

	/**
	 * @deprecated no longer used
	 */
	protected IProgressMonitor progressMonitor = null;

	/**
	 * Default constructor
	 */
	public DeleteEnterpriseBeanDataModel() {
		super();
	}

	/**
	 * @deprecated Default constructor
	 */
	public DeleteEnterpriseBeanDataModel(IOperationHandler operationHandler, IProgressMonitor monitor) {
		super();
		this.operationHandler = operationHandler;
		this.progressMonitor = monitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		WTPOperation op = new DeleteEnterpriseBeanOperation(this);
		return op;
	}

	protected IOperationHandler getOperationHandler() {
		return operationHandler;
	}

	/**
	 * @deprecated no longer used
	 */
	protected IProgressMonitor getProgressMonitor() {
		return progressMonitor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(BEANS_TO_BE_DELETED);
		addValidBaseProperty(SHELL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#init()
	 */
	protected void init() {
		super.init();
		setProperty(EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
	}

}