/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.swt.graphics.Image;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class AvailableUtilJarsAndWebLibProvider implements IStructuredContentProvider, ITableLabelProvider {

	public AvailableUtilJarsAndWebLibProvider() {
		//Default constructor
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof ArchiveWrapper){
			ArchiveWrapper wrapper = (ArchiveWrapper)inputElement;
			List <ArchiveWrapper> utilities = wrapper.getEARUtilitiesAndWebLibs();
			if (utilities.size() > 0){
				filterEJBClientJars(utilities, wrapper);
				Object [] array = utilities.toArray();
				Arrays.sort(array, new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						return getColumnText(o1, 0).compareTo(getColumnText(o2, 0));
					}
				});
				return array;
			}
		}
		return new Object[0];
	}

	/**
	 * @param array
	 * @return
	 */
	private void filterEJBClientJars(List <ArchiveWrapper> utilities, ArchiveWrapper earWrapper) {
		List <ArchiveWrapper> modules = earWrapper.getEarModules();
		for(ArchiveWrapper module : modules){
			if(module.isEJBJarFile()){
				ArchiveWrapper clientWrapper = earWrapper.getEJBClientArchiveWrapper(module);
				if(null != clientWrapper){
					boolean removed = false;
					for(int i=0;i<utilities.size() && !removed; i++){
						if(clientWrapper.getUnderLyingArchive() == utilities.get(i).getUnderLyingArchive()){
							utilities.remove(i);
							removed = true;
						}
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return AvailableJarsProvider.getUtilImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		ArchiveWrapper wrapper = (ArchiveWrapper)element;
		IPath path = wrapper.getPath();
		if(path.toString().startsWith(ArchiveConstants.WEBAPP_LIB_URI)){
			return wrapper.getParent().getName()+"#"+wrapper.getPath(); //$NON-NLS-1$
		}
		return wrapper.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		//Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		//Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
	 *      java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		//Auto-generated method stub
	}

}
