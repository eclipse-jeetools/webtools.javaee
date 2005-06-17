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
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaUtilityJarImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEJavaComponentSaveStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEUtilityJarImportOperationNew extends AbstractDataModelOperation {

	public J2EEUtilityJarImportOperationNew(IDataModel dataModel) {
		super(dataModel);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IDataModel nestedComonentCreationDM = model.getNestedModel(IJavaUtilityJarImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION);
		IVirtualComponent component = (IVirtualComponent) model.getProperty(IJavaUtilityJarImportDataModelProperties.COMPONENT);

		if (!component.getRootFolder().exists()) {
			model.getNestedModel(IJavaUtilityJarImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION).getDefaultOperation().execute(monitor, info);
		}

		IProject javaProject = component.getProject();
		Archive jarFile = (Archive) model.getProperty(IJavaUtilityJarImportDataModelProperties.FILE);

		J2EEJavaComponentSaveStrategyImpl strat = new J2EEJavaComponentSaveStrategyImpl(component);

		strat.setProgressMonitor(new SubProgressMonitor(monitor, 1));
		try {
			jarFile.save(strat);
			JemProjectUtilities.appendJavaClassPath(javaProject, JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER"))); //$NON-NLS-1$)
			JemProjectUtilities.forceClasspathReload(javaProject);
		} catch (SaveFailureException e) {
			Logger.getLogger().logError(e);
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
		return OK_STATUS;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

}