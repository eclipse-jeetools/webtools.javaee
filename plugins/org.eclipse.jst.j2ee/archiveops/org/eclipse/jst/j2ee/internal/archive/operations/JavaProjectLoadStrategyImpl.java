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
package org.eclipse.jst.j2ee.internal.archive.operations;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.JavaModel;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverter;
import org.eclipse.jem.util.emf.workbench.WorkbenchURIConverterImpl;


/**
 * Create a Java Project Load Strategy based on J2EE Load Strategy which is used by common archive
 * operations.
 */
public class JavaProjectLoadStrategyImpl extends J2EELoadStrategyImpl {

	//	IProject project;
	protected IJavaProject javaProject;
	protected List sourceContainers = null;

	public static final String JAVA_EXTENSION = "java"; //$NON-NLS-1$
	public static final String CLASS_EXTENSION = "class"; //$NON-NLS-1$


	/**
	 * Constructor for JavaProjectLoadStrategyImpl.
	 */
	public JavaProjectLoadStrategyImpl(IProject proj) {
		super();
		project = proj;
		filesList = new ArrayList();
		JavaModel model = JavaProjectUtilities.getJavaModel();
		javaProject = model.getJavaProject(project.getName());

	}

	public IContainer getModuleContainer() {
		return JavaProjectUtilities.getJavaProjectOutputContainer(getProject());
	}

	/*
	 * @see J2EELoadStrategyImpl#getProjectURIConverter()
	 */
	public WorkbenchURIConverter getProjectURIConverter() {
		if (projectURIConverter != null)
			return projectURIConverter;

		IContainer outputContainer = JavaProjectUtilities.getJavaProjectOutputContainer(getProject());
		List uriContainers = new ArrayList();
		uriContainers.add(outputContainer);
		if (isExportSource()) {
			List localSourceFolders = getSourceFolders();
			//We always want the output container at the front of the list
			//If the output container is also source, then remove it so it's not searched twice
			localSourceFolders.remove(outputContainer);
			uriContainers.addAll(localSourceFolders);
		}
		projectURIConverter = new WorkbenchURIConverterImpl();
		for (int i = 0; i < uriContainers.size(); i++) {
			projectURIConverter.addInputContainer((IContainer) uriContainers.get(i));
		}
		return projectURIConverter;
	}

	/*
	 * Overridden from superclass, V5. For future releases, need to clean up the implementation in
	 * the superclass, but for now, overriding for minimum disruption.
	 */
	protected IPath getOutputPathForFile(IPath aPath) throws Exception {

		if (isProjectMetaFile(aPath.toString())) {
			if (shouldIncludeProjectMetaFiles())
				return aPath;
			return null;
		}
		if (!checkIfNeeded(aPath))
			return null;

		List containers = getProjectURIConverter().getInputContainers();
		for (int i = 0; i < containers.size(); i++) {
			IContainer aContainer = (IContainer) containers.get(i);
			IPath containerPath = aContainer.getProjectRelativePath();
			if (aPath.matchingFirstSegments(containerPath) == containerPath.segmentCount()) {
				IPath result = aPath.removeFirstSegments(containerPath.segmentCount());
				//The next three lines cover the case where you have the project as source
				//and a folder as the output
				if (isProjectMetaFile(result.toString()) && !shouldIncludeProjectMetaFiles())
					return null;
				return result;
			}
		}
		return null;
	}


	public List getSourceFolders() {
		if (sourceContainers == null)
			sourceContainers = JavaProjectUtilities.getSourceContainers(project);
		return sourceContainers;
	}


	protected boolean checkIfNeeded(IPath path) throws Exception {
		if (path == null || path.segmentCount() == 0)
			return false;

		String ext = path.getFileExtension();

		if (isJava(ext))
			return isExportSource();
		return true;
	}

	protected boolean isResource(String ext) {
		return ext == null || (!isClass(ext) && !isJava(ext));
	}

	protected boolean isClass(String ext) {
		return CLASS_EXTENSION.equals(ext);
	}

	protected boolean isJava(String ext) {
		return JAVA_EXTENSION.equals(ext);
	}

}