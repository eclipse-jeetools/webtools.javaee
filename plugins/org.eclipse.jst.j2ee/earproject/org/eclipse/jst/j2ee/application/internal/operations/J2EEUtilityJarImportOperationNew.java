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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaUtilityJarImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEJavaComponentSaveStrategyImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
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
		String componentName = (String)model.getProperty(IJavaUtilityJarImportDataModelProperties.COMPONENT_NAME);

		IVirtualComponent component = (IVirtualComponent) model.getProperty(IJavaUtilityJarImportDataModelProperties.COMPONENT);

		if ( component == null || !component.exists()) {
			model.getNestedModel(IJavaUtilityJarImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION).getDefaultOperation().execute(monitor, info);
			IProject javaProject = ProjectUtilities.getProject(componentName);
			component = ComponentCore.createComponent(javaProject);			
		}
		IProject javaProject = component.getProject();
		

		Archive jarFile = (Archive) model.getProperty(IJavaUtilityJarImportDataModelProperties.FILE);

		J2EEJavaComponentSaveStrategyImpl strat = new J2EEJavaComponentSaveStrategyImpl(component);

		strat.setProgressMonitor(new SubProgressMonitor(monitor, 1));
		try {
			jarFile.save(strat);
			// To fix the defect that throws dup classpath exception.
			// Because JemProjectUtilities.appendJavaClassPath() does not check dups, we have to check it here.
			// check if JRE_CONTAINER is in the classpath. if not add it
			IJavaProject javaProj = JemProjectUtilities.getJavaProject(javaProject);
			IClasspathEntry[] classpath = javaProj.getRawClasspath();
			String jrePathName = "org.eclipse.jdt.launching.JRE_CONTAINER"; //$NON-NLS-1$
			boolean exists = false;
			for (int i = 0; i < classpath.length; i++) {
				if (classpath[i].getEntryKind() != IClasspathEntry.CPE_CONTAINER)
					continue;
				IPath path = classpath[i].getPath();
				if (path.segmentCount() > 0) {
					String name = path.segment(0).toString();
					if (jrePathName.equals(name)) {
						exists = true;
						break;
					}
				}
			}
			if (!exists) {
				IClasspathEntry newEntry = JavaCore.newContainerEntry(new Path(jrePathName));
				JemProjectUtilities.appendJavaClassPath(javaProject, JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER"))); //$NON-NLS-1$)
				JemProjectUtilities.forceClasspathReload(javaProject);
			}
		} catch (SaveFailureException e) {
			Logger.getLogger().logError(e);
		} catch (JavaModelException je) {
			Logger.getLogger().logError(je);
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