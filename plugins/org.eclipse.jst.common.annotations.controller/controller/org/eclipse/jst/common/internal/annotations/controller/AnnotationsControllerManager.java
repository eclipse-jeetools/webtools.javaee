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
 * Created on Mar 25, 2004
 */
package org.eclipse.jst.common.internal.annotations.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.WeakHashMap;

import org.eclipse.core.internal.runtime.Assert;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.internal.annotations.registry.AnnotationsControllerResources;
import org.eclipse.wst.common.frameworks.internal.enablement.EnablementIdentifier;
import org.eclipse.wst.common.frameworks.internal.enablement.EnablementIdentifierEvent;
import org.eclipse.wst.common.frameworks.internal.enablement.EnablementManager;
import org.eclipse.wst.common.frameworks.internal.enablement.IEnablementIdentifier;
import org.eclipse.wst.common.frameworks.internal.enablement.IEnablementIdentifierListener;
import org.eclipse.wst.common.frameworks.internal.enablement.Identifiable;
import org.eclipse.wst.common.frameworks.internal.enablement.IdentifiableComparator;


/**
 * AnnotationsControllerRegistry for reading annotations controller extensions
 */
public class AnnotationsControllerManager extends RegistryReader implements IEnablementIdentifierListener {

	public static final AnnotationsControllerManager INSTANCE = new AnnotationsControllerManager();
	public static final String ANNOTATION_BUILDER = "com.ibm.ws.rapiddeploy.annotations.core.AnnotationBuilder"; //$NON-NLS-1$

	static {
		INSTANCE.readRegistry();
	}

	private SortedSet descriptors;

	private Map annotationsControllers;

	public static class Descriptor implements Identifiable {

		public static final String ANNOTATIONS_CONTROLLER = "annotationsController"; //$NON-NLS-1$

		public static final String ATT_ID = "id"; //$NON-NLS-1$

		public static final String CLASS = "class"; //$NON-NLS-1$

		private final IConfigurationElement configElement;
		private final String ID;
		private final int loadOrder;
		private static int loadOrderCounter = 0;

		public Descriptor(IConfigurationElement aConfigElement) {
			super();
			Assert.isLegal(ANNOTATIONS_CONTROLLER.equals(aConfigElement.getName()), AnnotationsControllerResources.getString("AnnotationsControllerManager_ERROR_0")); //$NON-NLS-1$
			this.configElement = aConfigElement;
			this.ID = this.configElement.getAttribute(ATT_ID);
			loadOrder = loadOrderCounter++;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.wst.common.frameworks.internal.enablement.Identifiable#getID()
		 */
		public String getID() {
			return ID;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.wst.common.frameworks.internal.enablement.Identifiable#getLoadOrder()
		 */
		public int getLoadOrder() {
			return loadOrder;
		}

		public AnnotationsController createInstance() {
			AnnotationsController instance = null;
			try {
				instance = (AnnotationsController) configElement.createExecutableExtension(CLASS);
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
			return instance;
		}
	}

	/**
	 * Default constructor
	 */
	public AnnotationsControllerManager() {
		super("org.eclipse.wst.common.internal.annotations.controller", "annotationsController"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * read extension element
	 */
	public boolean readElement(IConfigurationElement element) {
		if (!element.getName().equals(Descriptor.ANNOTATIONS_CONTROLLER))
			return false;
		addAnnotationController(new Descriptor(element));
		return true;
	}

	/**
	 * @param descriptor
	 */
	protected void addAnnotationController(Descriptor descriptor) {
		EnablementManager.INSTANCE.getIdentifier(descriptor.getID(), null).addIdentifierListener(this);
		getDescriptors().add(descriptor);
	}

	/**
	 * @return Returns the annotationControllers.
	 */
	protected SortedSet getDescriptors() {
		if (descriptors == null)
			descriptors = new TreeSet(IdentifiableComparator.getInstance());
		return descriptors;
	}

	public Descriptor getDescriptor(IProject project) {
		for (Iterator iter = getDescriptors().iterator(); iter.hasNext();) {
			Descriptor descriptor = (Descriptor) iter.next();
			IEnablementIdentifier identifier = EnablementManager.INSTANCE.getIdentifier(descriptor.getID(), project);
			if (identifier.isEnabled())
				return descriptor;
		}
		return null;
	}

	/**
	 * Determine if any annotations are supported
	 */
	public boolean isAnyAnnotationsSupported() {
		return getDescriptors().size() > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.enablement.IEnablementIdentifierListener#identifierChanged(org.eclipse.wst.common.frameworks.internal.enablement.EnablementIdentifierEvent)
	 */
	public void identifierChanged(EnablementIdentifierEvent identifierEvent) {
		IProject project = ((EnablementIdentifier) identifierEvent.getIdentifier()).getProject();
		getAnnotationsControllers().remove(project);
	}

	/**
	 * Return the annotations controller for the specified project
	 */
	public AnnotationsController getAnnotationsController(IProject project) {
		AnnotationsController controller = (AnnotationsController) getAnnotationsControllers().get(project);
		if (controller == null) {
			if (!hasBuilder(project, ANNOTATION_BUILDER))
				return null;
			Descriptor descriptor = getDescriptor(project);
			if (descriptor != null)
				getAnnotationsControllers().put(project, (controller = descriptor.createInstance()));
		}

		return controller;
	}

	/**
	 * @return Returns the annotationControllers.
	 */
	public Map getAnnotationsControllers() {
		if (annotationsControllers == null)
			annotationsControllers = new WeakHashMap();
		return annotationsControllers;
	}

	public boolean hasBuilder(IProject project, String builderName) {
		try {
			ICommand[] builders = project.getDescription().getBuildSpec();
			for (int i = 0; i < builders.length; i++) {
				ICommand builder = builders[i];
				if (builder != null) {
					if (builder.getBuilderName().equals(builderName))
						return true;
				}
			}
		} catch (Exception e) {
			// Do nothing
		}
		return false;
	}
}