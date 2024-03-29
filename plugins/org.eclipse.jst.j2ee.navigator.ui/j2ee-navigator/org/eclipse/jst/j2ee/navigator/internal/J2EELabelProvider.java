/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jst.j2ee.common.internal.util.CommonUtil;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EEJavaClassProviderHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.provider.J2EEAdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.wst.common.internal.emfworkbench.integration.DynamicAdapterFactory;

/**
 * Root Label provider for Java EE content
 */
public class J2EELabelProvider implements ICommonLabelProvider {

	private AdapterFactoryLabelProvider delegateLabelProvider;

	/**
	 *  
	 */
	public J2EELabelProvider() {
		super();
	}

	/**
	 *  
	 */
	public J2EELabelProvider(String aViewerId) {
		super();
		initialize(aViewerId);
	}

	public void initialize(String aViewerId) {
		delegateLabelProvider = new J2EEAdapterFactoryLabelProvider(new DynamicAdapterFactory(aViewerId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.extensions.ICommonLabelProvider#getDescription(java.lang.Object)
	 */
	@Override
	public String getDescription(Object anElement) {
		if (anElement != null && anElement instanceof EObject) {
			EObject eObj = (EObject) anElement;
			if (CommonUtil.isDeploymentDescriptorRoot(eObj, true /* include ears */)) {
				IProject parent = ProjectUtilities.getProject(eObj);
				if(eObj.eResource() != null) {
					String path = new Path(eObj.eResource().getURI().toString()).makeRelative().toString();
					if (parent == null)
						return path;
					int startIndex = path.indexOf(parent.getFullPath().toString());
					return -1 == startIndex ? path : path.substring(startIndex);
				}
				return getText(eObj);
			}
		}
		return null;
	}

	/**
	 * @param listener
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		if (delegateLabelProvider != null)
			delegateLabelProvider.addListener(listener);
	}

	/**
	 *  
	 */
	@Override
	public void dispose() {
		if (delegateLabelProvider != null)
			delegateLabelProvider.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (delegateLabelProvider != null)
			return delegateLabelProvider.equals(obj);
		return false;
	}

	/**
	 *  
	 */
	public void fireLabelProviderChanged() {
		if (delegateLabelProvider != null)
			delegateLabelProvider.fireLabelProviderChanged();
	}

	/**
	 * @return
	 */
	public AdapterFactory getAdapterFactory() {
		if (delegateLabelProvider != null)
			return delegateLabelProvider.getAdapterFactory();
		return null;
	}

	/**
	 * @param object
	 * @param columnIndex
	 * @return
	 */
	public Image getColumnImage(Object object, int columnIndex) {
		if (delegateLabelProvider != null)
			return delegateLabelProvider.getColumnImage(object, columnIndex);
		return null;
	}

	/**
	 * @param object
	 * @param columnIndex
	 * @return
	 */
	public String getColumnText(Object object, int columnIndex) {
		if (delegateLabelProvider != null)
			return delegateLabelProvider.getColumnText(object, columnIndex);
		return null;
	}

	/**
	 * @param element
	 * @return
	 */
	@Override
	public Image getImage(Object element) {
		if(element instanceof J2EEJavaClassProviderHelper)
			return ((J2EEJavaClassProviderHelper) element).getImage();
		if (element instanceof File)
			return J2EEUIPlugin.getDefault().getImage("jar_obj"); //$NON-NLS-1$
		if(element instanceof LoadingDDNode)
			return ((LoadingDDNode)element).getImage();
		if (element instanceof IProject || element instanceof IJavaElement)
			return null;
		if (delegateLabelProvider != null)
			return delegateLabelProvider.getImage(element);
		return null;
	}

	/**
	 * @param element
	 * @return
	 */
	@Override
	public String getText(Object element) {
		if(element instanceof J2EEJavaClassProviderHelper)
			return ((J2EEJavaClassProviderHelper) element).getText();
		if (element instanceof File)
			return ((File)element).getName();
		if (element instanceof IProject || element instanceof IJavaProject)
			return null;
		if(element instanceof LoadingDDNode)
			return ((LoadingDDNode)element).getText();
		if (element instanceof IJavaElement)
			return null;
		if (delegateLabelProvider != null)
			return delegateLabelProvider.getText(element);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		if (delegateLabelProvider != null)
			return delegateLabelProvider.hashCode();
		return super.hashCode();
	}

	/**
	 * @param object
	 * @param id
	 * @return
	 */
	@Override
	public boolean isLabelProperty(Object object, String id) {
		if (delegateLabelProvider != null)
			return delegateLabelProvider.isLabelProperty(object, id);
		return false;
	}

	/**
	 * @param notification
	 */
	public void notifyChanged(Notification notification) {
		if (delegateLabelProvider != null)
			delegateLabelProvider.notifyChanged(notification);
	}

	/**
	 * @param listener
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		if (delegateLabelProvider != null)
			delegateLabelProvider.removeListener(listener);
	}

	/**
	 * @param adapterFactory
	 */
	public void setAdapterFactory(AdapterFactory adapterFactory) {
		if (delegateLabelProvider != null)
			delegateLabelProvider.setAdapterFactory(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (delegateLabelProvider != null)
			return delegateLabelProvider.toString();
		return super.toString();
	}

	@Override
	public void init(ICommonContentExtensionSite aSite) {
		initialize(IJ2EENavigatorConstants.VIEWER_ID);
		
	}

	@Override
	public void restoreState(IMemento aMemento) {
	}

	@Override
	public void saveState(IMemento aMemento) {
	}
}
