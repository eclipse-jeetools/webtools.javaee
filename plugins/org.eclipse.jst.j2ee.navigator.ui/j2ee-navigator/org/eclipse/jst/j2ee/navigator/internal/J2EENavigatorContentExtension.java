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
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.Comparator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.common.util.CommonUtil;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EEJavaClassProviderHelper;
import org.eclipse.wst.common.internal.emfworkbench.integration.DynamicAdapterFactory;
import org.eclipse.wst.common.navigator.internal.views.DefaultNavigatorContentExtension;
import org.eclipse.wst.common.navigator.internal.views.actions.CommonEditActionGroup;
import org.eclipse.wst.common.navigator.views.INavigatorContentProvider;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * This navigator extension provides a Java specific presentation of workspace elements.
 */
public class J2EENavigatorContentExtension extends DefaultNavigatorContentExtension {

	private final static String VIEWER_ID = IJ2EENavigatorConstants.VIEWER_ID;

	private J2EEEditActionGroup editActionGroup = null;

	private INavigatorContentProvider contentProvider = null;

	private ILabelProvider labelProvider;

	private Comparator j2eeComparator;

	private J2EERootObjectManager rootObjectManager = null;

	public class J2EEComparator implements Comparator {

		private J2EEViewerSorter j2eeViewSorter = new J2EEViewerSorter();

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Object o1, Object o2) {
			J2EEJavaClassProviderHelper providerHelperLeftArg = null;
			J2EEJavaClassProviderHelper providerHelperRightArg = null;

			if (o1 instanceof J2EEJavaClassProviderHelper)
				providerHelperLeftArg = (J2EEJavaClassProviderHelper) o1;
			if (o2 instanceof J2EEJavaClassProviderHelper)
				providerHelperRightArg = (J2EEJavaClassProviderHelper) o2;


			/* if both arguments are J2EEJavaClassProviderHelpers */
			if (providerHelperLeftArg != null && providerHelperRightArg != null)
				return providerHelperLeftArg.getText().compareTo(providerHelperRightArg.getText());
			/* if exactly one of the arguments are J2EEJavaClassProviderHelpers */
			else if (providerHelperLeftArg != null && o2 instanceof EnterpriseBean)
				return 1;
			else if (providerHelperRightArg != null && o1 instanceof EnterpriseBean)
				return -1;
			return j2eeViewSorter.compare(null, o1, o2);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			return obj instanceof J2EEComparator;
		}

	}

	public J2EENavigatorContentExtension() {
		super();
	}

	/**
	 * Configure and return a composite adapter factory for our contents
	 */
	public AdapterFactory createAdapterFactory() {
		DynamicAdapterFactory factory = new DynamicAdapterFactory(VIEWER_ID);
		return factory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getContentProvider()
	 */
	public INavigatorContentProvider getContentProvider() {
		if (contentProvider == null) {
			contentProvider = new J2EENavigationContentProvider(createAdapterFactory(), this);
		}
		return contentProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getDescription(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public String getDescription(IStructuredSelection selection) {
		if (selection != null && !selection.isEmpty()) {

			Object element = selection.getFirstElement();
			if (element instanceof EObject) {
				EObject eObj = (EObject) element;
				if (CommonUtil.isDeploymentDescriptorRoot(eObj, true /* include ears */)) {
					IProject parent = ProjectUtilities.getProject(eObj);
					String path = new Path(eObj.eResource().getURI().toString()).makeRelative().toString();
					if (parent == null)
						return path;
					int startIndex = path.indexOf(parent.getFullPath().toString());
					return -1 == startIndex ? path : path.substring(startIndex);
				}
			}

		}
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getLabelProvider()
	 */
	public ILabelProvider getLabelProvider() {
		if (labelProvider == null) {
			labelProvider = new J2EENavigationLabelProvider(createAdapterFactory());
		}
		return labelProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#init(org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorExtensionSite)
	 */
	public void doInit() {
		rootObjectManager = new J2EERootObjectManager(this, getExtensionSite());
	}

	/**
	 * @return Returns the rootObjectManager.
	 */
	public J2EERootObjectManager getRootObjectManager() {
		return rootObjectManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentExtension#getComparator()
	 */
	public Comparator getComparator() {
		if (j2eeComparator == null)
			j2eeComparator = new J2EEComparator();
		return j2eeComparator;
	}

	/**
	 * @return Returns the editActionGroup.
	 */
	public CommonEditActionGroup getEditActionGroup() {
		if (editActionGroup == null)
			editActionGroup = new J2EEEditActionGroup(this);
		return editActionGroup;
	}

}
