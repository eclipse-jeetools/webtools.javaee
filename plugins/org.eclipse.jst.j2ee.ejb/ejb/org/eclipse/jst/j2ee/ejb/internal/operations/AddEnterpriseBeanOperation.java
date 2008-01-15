/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jem.util.UIContextDetermination;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class AddEnterpriseBeanOperation extends AbstractDataModelOperation implements INewJavaClassDataModelProperties {

	protected IModelProvider provider;

	/**
	 * This is the constructor which should be used when creating the operation.
	 * It will not accept null parameter. It will not return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * 
	 * @param dataModel
	 * @return AddBeansOperation
	 */
	public AddEnterpriseBeanOperation(IDataModel dataModel) {
		super(dataModel);
		provider = ModelProviderManager.getModelProvider(getTargetProject());
	}

	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
	throws ExecutionException {
		// create the java class
		createBeanClass();
		return OK_STATUS;
	}

	@Override
	public IStatus execute(final IProgressMonitor monitor, final IAdaptable info)
	throws ExecutionException {
		Runnable runnable = null;
		try {
			Object ctx = null;
			if (UIContextDetermination.getCurrentContext() == UIContextDetermination.UI_CONTEXT) {
				Display display = Display.getCurrent();
				if (display != null)
					ctx = display.getActiveShell();
			}

			if (provider.validateEdit(null, ctx).isOK()) {
				runnable = new Runnable() {

					public void run() {
						try {
							doExecute(monitor, info);
						} catch (ExecutionException e) {
							WTPCommonPlugin.createErrorStatus(e.toString());
						}
					}
				};
				provider.modify(runnable, null);
			}
			// return doExecute(monitor, info);
			return Status.CANCEL_STATUS;
		} finally {

		}
	}

	public IProject getTargetProject() {
		String projectName = model.getStringProperty(PROJECT_NAME);
		return ProjectUtilities.getProject(projectName);
	}
	
	protected String createBeanClass() {
		return null;
	}

	/**
	 * This method will return the qualified java class name as specified by the
	 * class name and package name properties in the data model. This method
	 * should not return null.
	 * 
	 * @see INewJavaClassDataModelProperties#CLASS_NAME
	 * @see INewJavaClassDataModelProperties#JAVA_PACKAGE
	 * 
	 * @return String qualified java classname
	 */
	public final String getQualifiedClassName() {
		// Use the java package name and unqualified class name to create a
		// qualified java class name
		String packageName = model.getStringProperty(JAVA_PACKAGE);
		String className = model.getStringProperty(CLASS_NAME);
		// Ensure the class is not in the default package before adding package
		// name to qualified name
		if (packageName != null && packageName.trim().length() > 0)
			return packageName + "." + className; //$NON-NLS-1$
		return className;
	}
}
