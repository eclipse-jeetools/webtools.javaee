/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * BEA Systems, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.classpathdep;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.j2ee.internal.classpathdep.UpdateClasspathAttributesDataModelProperties;
import org.eclipse.jst.j2ee.internal.classpathdep.UpdateClasspathAttributesDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * Contains utility code for executing the UpdateClasspathAttributeOperation.
 */
public class UpdateClasspathAttributeUtil implements IClasspathDependencyConstants {

	/**
	 * Updates the specified Java project so that only the specified classpath entries have
	 * the WTP component dependency attribute.
	 * @param projectName Name of the target Java project.
	 * @param entryToRuntimePath Map from IClasspathEntry to runtime path for all entries that should 
	 * have the component dependency attribute.
	 * @return Status from the operation.
	 * @throws ExecutionException Thrown if an error is encountered.
	 */	
	public static IStatus updateDependencyAttributes(final IProgressMonitor monitor, final String projectName, final Map entryToRuntimePath) 
		throws ExecutionException {
		return createUpdateDependencyAttributesOperation(projectName, entryToRuntimePath).execute(monitor, null);
	}
	
	/**
	 * Creates the IDataModelOperation that will update the classpath for the specified Java project so that
	 * only the specified list of classpath entries will have WTP component dependency attribute. 
	 * @param projectName Name of the target Java project.
	 * @param entryToRuntimePath Map from IClasspathEntry to runtime path for all entries that should 
	 * have the component dependency attribute.
	 * @return The operation.
	 */
	public static IDataModelOperation createUpdateDependencyAttributesOperation(final String projectName, final Map entryToRuntimePath) {
		return createOperation(projectName, entryToRuntimePath, UpdateClasspathAttributesDataModelProperties.ENTRIES_WITH_ATTRIBUTE);
	}
	
	/**
	 * Adds the WTP component dependency attribute to the specified classpath entry using the default runtime path for the project. Does NOT check that the
	 * specified entry is a valid entry for the dependency attribute.
	 * 
	 * @param monitor Progress monitor. Can be null.
	 * @param projectName Name of the target Java project.
	 * @param entry Classpath entry to which the attribute should be added.
	 * @return Status from the operation.
	 * @throws ExecutionException Thrown if an error is encountered.
	 */	
	public static IStatus addDependencyAttribute(final IProgressMonitor monitor, final String projectName, final IClasspathEntry entry) 
		throws ExecutionException {
		return addDependencyAttribute(monitor, projectName, entry, null);
	}
	
	/**
	 * Adds the WTP component dependency attribute to the specified classpath entry. Does NOT check that the
	 * specified entry is a valid entry for the dependency attribute.
	 * 
	 * @param monitor Progress monitor. Can be null.
	 * @param projectName Name of the target Java project.
	 * @param entry Classpath entry to which the attribute should be added.
	 * @param runtimePath Runtime path to which entry elements should be deployed. Null if the default runtime path for the project should be used.
	 * @return Status from the operation.
	 * @throws ExecutionException Thrown if an error is encountered.
	 */	
	public static IStatus addDependencyAttribute(final IProgressMonitor monitor, final String projectName, final IClasspathEntry entry, final IPath runtimePath) 
		throws ExecutionException {
		final Map entryToRuntimePath = new HashMap();
		entryToRuntimePath.put(entry, runtimePath);
		return createOperation(projectName, entryToRuntimePath, UpdateClasspathAttributesDataModelProperties.ENTRIES_TO_ADD_ATTRIBUTE).execute(monitor, null);
	}
	
	/**
	 * Removes the WTP component dependency attribute from the specified classpath entry. Does NOT check that the
	 * specified entry is a valid entry for the dependency attribute.
	 * 
	 * @param monitor Progress monitor. Can be null.
	 * @param projectName Name of the target Java project.
	 * @param entry Classpath entry from which the attribute should be removed.
	 * @return Status from the operation.
	 * @throws ExecutionException Thrown if an error is encountered.
	 */	
	public static IStatus removeDependencyAttribute(final IProgressMonitor monitor, final String projectName, final IClasspathEntry entry) 
	throws ExecutionException {
		final Map entryToRuntimePath = new HashMap();
		entryToRuntimePath.put(entry, null);
		return createOperation(projectName, entryToRuntimePath, UpdateClasspathAttributesDataModelProperties.ENTRIES_TO_REMOVE_ATTRIBUTE).execute(monitor, null);
	}
	
	private static IDataModelOperation createOperation(String projectName, final Map entryToRuntimePath, final String entryMapProperty) { 
		IDataModel dataModel = DataModelFactory.createDataModel(new UpdateClasspathAttributesDataModelProvider());
		dataModel.setProperty(UpdateClasspathAttributesDataModelProperties.PROJECT_NAME, projectName);
		dataModel.setProperty(entryMapProperty, entryToRuntimePath); 
		return dataModel.getDefaultOperation();
	}
	
}
