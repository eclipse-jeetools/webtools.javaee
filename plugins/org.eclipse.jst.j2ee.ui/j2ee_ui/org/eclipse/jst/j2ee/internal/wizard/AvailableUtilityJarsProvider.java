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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEUtilityJarListImportDataModel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AvailableUtilityJarsProvider implements IStructuredContentProvider, ITableLabelProvider {

	public AvailableUtilityJarsProvider() {
		//default constructor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		/*
		 * Object[] array = EARImportDataModel.getAllUtilities((EARFile) inputElement).toArray();
		 */
		Object[] array = getJarFilesFromDirectory(inputElement);
		Arrays.sort(array, new Comparator() {

			public int compare(Object o1, Object o2) {
				return getColumnText(o1, 0).compareTo(getColumnText(o2, 0));
			}

		});
		return array;
	}

	/**
	 * @param inputElement
	 * @return
	 */
	private Object[] getJarFilesFromDirectory(Object inputElement) {

		List collectedJars = new ArrayList();
		WTPOperationDataModel model = null;
		if (inputElement instanceof WTPOperationDataModel)
			model = (WTPOperationDataModel) inputElement;
		if (model != null) {

			String fileName = model.getStringProperty(J2EEUtilityJarListImportDataModel.AVAILABLE_JARS_DIRECTORY);
			File directory = new File(fileName);
			if (directory.exists() && directory.canRead() && directory.isDirectory()) {
				File[] availableFiles = directory.listFiles();

				if (availableFiles == null)
					return new File[0];

				for (int i = 0; i < availableFiles.length; i++)
					if (availableFiles[i] != null && availableFiles[i].getName().endsWith(".jar"))collectedJars.add(availableFiles[i]); //$NON-NLS-1$
			}
		}
		return collectedJars.toArray();
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
		/*
		 * FileImpl file = (FileImpl) element; if
		 * (file.getURI().startsWith(ArchiveConstants.WEBAPP_LIB_URI)) { String parentWarFileName =
		 * ((WARFile) file.eContainer()).getName(); return parentWarFileName + "#" + file.getURI();
		 * //$NON-NLS-1$ } else return file.getName();
		 */
		return element.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		//dispose
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