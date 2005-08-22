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
 * Created on May 9, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AvailableModuleProjectsProvider implements IStructuredContentProvider, ITableLabelProvider {
	private IDataModel model;

	public AvailableModuleProjectsProvider(IDataModel dataModel) {
		super();
		model = dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		List validModuleProjects = null;
		if (inputElement instanceof IWorkspaceRoot) {
			IProject[] projects = ((IWorkspaceRoot) inputElement).getProjects();
			if (projects.length > 0) {
				int j2eeVersion = model.getIntProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION);
				for (int i = 0; i < projects.length; i++) {
					if (isValid(projects[i], j2eeVersion)) {
						if (validModuleProjects == null)
							validModuleProjects = new ArrayList();
						validModuleProjects.add(projects[i]);
					}
				}
			}
		}
		if (validModuleProjects == null)
			return new Object[0];
		return validModuleProjects.toArray();
	}

	private boolean isValid(IProject project, int j2eeVersion) {
		//migrate to artifact edits
//		try {
//			if (project.hasNature(IEARNatureConstants.NATURE_ID))
//				return false;
//			J2EENature j2eeNature = J2EENature.getRegisteredRuntime(project);
//			if (j2eeNature != null) {
//				return j2eeNature.getJ2EEVersion() <= j2eeVersion;
//			} else if (project.hasNature(JavaCore.NATURE_ID))
//				return true;
//		} catch (CoreException e) {
//			//Ignore
//		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		return ((IProject) element).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		model = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		//do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
	 *      java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		//do nothing
	}
}