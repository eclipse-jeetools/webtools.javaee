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
 * Created on Apr 22, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationImportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.FileImpl;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.swt.graphics.Image;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AvailableUtilJarsAndWebLibProvider implements IStructuredContentProvider, ITableLabelProvider {

	public AvailableUtilJarsAndWebLibProvider() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EARFile) {
			Object[] array = EnterpriseApplicationImportDataModel.getAllUtilities((EARFile) inputElement).toArray();

			List filteredProjects = new ArrayList();
			if (array != null && array.length != 0)
				filteredProjects = filterEJBClientJars(array, ((EARFile) inputElement));
			else
				return new Object[0];

			array = filteredProjects.toArray();
			Arrays.sort(array, new Comparator() {
				public int compare(Object o1, Object o2) {
					return getColumnText(o1, 0).compareTo(getColumnText(o2, 0));
				}

			});
			return array;
		}
		return new Object[0];
	}

	/**
	 * @param array
	 * @return
	 */
	private List filterEJBClientJars(Object[] array, EARFile ear) {
		List utilities = new ArrayList(array.length);
		for (int i = 0; i < array.length; i++) {
			utilities.add(array[i]);
		}
		List ejbJars = ear.getEJBJarFiles();
		if (ejbJars != null) {
			List clientNames = new ArrayList(ejbJars.size());
			for (int j = 0; j < ejbJars.size(); j++) {
				EJBJar jar = ((EJBJarFile) ejbJars.get(j)).getDeploymentDescriptor();
				if (jar != null) {
					clientNames.add(jar.getEjbClientJar());
				}
			}
			if (clientNames != null && !clientNames.isEmpty()) {
				List toRemove = new ArrayList();
				for (int k = 0; k < clientNames.size(); k++) {
					String projectName = (String) clientNames.get(k);
					for (int l = 0; l < utilities.size(); l++) {
						File file = (File) utilities.get(l);
						if (file.getName().equals(projectName))
							toRemove.add(utilities.get(l));
					}
				}
				utilities.removeAll(toRemove);
			}
		}
		return utilities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		return AvailableJarsProvider.getUtilImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		FileImpl file = (FileImpl) element;
		if (file.getURI().startsWith(ArchiveConstants.WEBAPP_LIB_URI)) {
			String parentWarFileName = ((WARFile) file.eContainer()).getName();
			return parentWarFileName + "#" + file.getURI(); //$NON-NLS-1$
		}
		return file.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		//Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		//Auto-generated method stub
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
		//Auto-generated method stub
	}

}