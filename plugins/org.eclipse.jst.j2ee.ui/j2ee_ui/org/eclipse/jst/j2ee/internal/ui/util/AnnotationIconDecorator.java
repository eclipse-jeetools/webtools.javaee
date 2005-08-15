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
 * Created on Aug 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.ui.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsController;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerHelper;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.provider.BeanClassProviderHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.swt.graphics.Image;

/**
 * Add overlay for annotated objects
 */
public class AnnotationIconDecorator extends LabelProvider implements ILightweightLabelDecorator {
	
	protected static final String ANNOTATION_IMAGE_DESC_STRING = "annotation_bean_overlay"; //$NON-NLS-1$
	protected static final String ANNOTATION_DISABLED_IMAGE_DESC_STRING = "dis_annotation_bean_overlay"; //$NON-NLS-1$
	private static final ImageDescriptor ANNOTATION_IMG_DESC = getImageDescriptor(ANNOTATION_IMAGE_DESC_STRING);
	private static final ImageDescriptor ANNOTATION_DISABLED_IMG_DESC = getImageDescriptor(ANNOTATION_DISABLED_IMAGE_DESC_STRING);

	public AnnotationIconDecorator() {
		super();
	}

	/**
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 *      org.eclipse.jface.viewers.IDecoration)
	 */
	private boolean isAnnotatedSupported(EObject target) {
		boolean bAnnotationSupported = false;
		if (AnnotationsControllerHelper.INSTANCE.isAnnotated(target)) {
			bAnnotationSupported = true;
		}
		return bAnnotationSupported;
	}

	private AnnotationsController getControllerForProject(IProject targetProject) {
		AnnotationsController controller = AnnotationsControllerManager.INSTANCE.getAnnotationsController(targetProject);
		return controller;
	}

	public void decorate(Object element, IDecoration decoration) {
		EObject eObject = null;
		if (element instanceof BeanClassProviderHelper) {
			BeanClassProviderHelper beanClassHelper = (BeanClassProviderHelper) element;
			eObject = beanClassHelper.getEjb();
		} else if (element instanceof EnterpriseBean || element instanceof Servlet)
			eObject = (EObject) element;
		if (eObject == null)
			return;
		if (isAnnotatedSupported(eObject)) {
			if (isAnnotationEnabled(eObject)) {
				if (ANNOTATION_IMG_DESC != null)
					decoration.addOverlay(ANNOTATION_IMG_DESC);
			} else {
				if (ANNOTATION_DISABLED_IMG_DESC != null)
					decoration.addOverlay(ANNOTATION_DISABLED_IMG_DESC);
			}
		}
	}

	/**
	 * @param bean
	 * @return
	 */
	private boolean isAnnotationEnabled(EObject eObject) {
		IFile annotatedSource = null;
		/* short circuit if possible */
		if (AnnotationsControllerManager.INSTANCE.isAnyAnnotationsSupported()) {
			IProject targetProject = ProjectUtilities.getProject(eObject);
			AnnotationsController controller = getControllerForProject(targetProject);
			if (controller != null)
				annotatedSource = controller.getEnabledAnnotationFile(eObject);
		}
		return (annotatedSource != null) ? true : false;
	}

	public Image decorateImage(Image image, Object element) {
		return image;
	}

	/**
	 * @see ILabelDecorator#decorateText(String, Object)
	 */
	public String decorateText(String text, Object element) {
		return text;
	}

	protected static ImageDescriptor getImageDescriptor(String imageFileName) {
		if (imageFileName != null)
			return J2EEUIPlugin.getDefault().getImageDescriptor(imageFileName);
		return null;
	}
}