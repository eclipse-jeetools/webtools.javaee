/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.IModelProviderListener;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.jee.model.internal.common.AbstractMergedModelProvider;
import org.eclipse.jst.jee.model.internal.mergers.EjbJarMerger;
import org.eclipse.jst.jee.model.internal.mergers.ModelElementMerger;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * This model provider gives a "merged" view of the model loaded from the
 * deployment descriptor and from annotated java classes.
 * 
 * <p>
 * A "merged" view contains all the elements that are described in the xml but
 * not with annotation.
 * 
 * A "merged" view contains all the elements that are described with annotations
 * but not in the xml.
 * 
 * In case of conflicts a "merged" view contains the value of the xml.
 * </p>
 * 
 * 
 * Listeners registered with {@link #addListener(IModelProviderListener)} will
 * receive notification for changes in the deployment descriptor and in the
 * annotations model. Notifications may come in different thread from the one
 * changing the resource.
 * 
 * <p>
 * After a notification for changes in the model it is mandatory to call
 * {@link #getModelObject()} to get a new reference to the model object. A new
 * model object might have been created after processing the notification and
 * merging the views.
 * </p>
 * 
 * In case there is an ejb-client-jar element in the deployment descriptor
 * EJB3MergedModelProvider will provide this information to the
 * {@link EJBAnnotationReader}.
 * 
 * 
 * 
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class EJB3MergedModelProvider extends AbstractMergedModelProvider<EJBJar> {

	private IProject clientProject;

	public EJB3MergedModelProvider(IProject project) {
		super(project);
	}

	/**
	 * (non-Javadoc) Synchronizing the loading of the model
	 * 
	 * @see org.eclipse.jst.jee.model.internal.common.AbstractMergedModelProvider#loadModel()
	 */
	protected final synchronized EJBJar loadModel() throws CoreException {
		return super.loadModel();
	}

	@Override
	protected IModelProvider loadAnnotationModel(EJBJar ddModel) throws CoreException {
		if (ddModel.getEjbClientJar() != null)
			clientProject = ResourcesPlugin.getWorkspace().getRoot().getProject(
					getClientNameFromJarName(ddModel.getEjbClientJar()));
		return new EJBAnnotationReader(ProjectFacetsManager.create(project), clientProject);
	}

	@Override
	protected IModelProvider loadDeploymentDescriptorModel() throws CoreException {
		return new Ejb3ModelProvider(project);
	}

	protected EJBJar getAnnotationEjbJar() {
		return (EJBJar) annotationModelProvider.getModelObject();
	}

	protected EJBJar getXmlEjbJar() {
		return (EJBJar) ddProvider.getModelObject();
	}

	public Object getModelObject(IPath modelPath) {
		return null;
	}

	public void modify(Runnable runnable, IPath modelPath) {
		/*
		 * Someone has called modify before loading the model. Try to load the
		 * model.
		 */
		if (mergedModel == null)
			getMergedModel();

		EJBJar backup = mergedModel;
		mergedModel = (EJBJar) ddProvider.getModelObject();
		ddProvider.modify(runnable, modelPath);
		if (isDisposed()) {
			return;
		}
		mergedModel = backup;
		clearModel(mergedModel);

		/*
		 * Reload the model.
		 */
		// getMergedModel();
		merge(getXmlEjbJar(), getAnnotationEjbJar());
	}

	protected void annotationModelChanged(IModelProviderEvent event) {
		internalModelChanged(event);
	}

	protected void xmlModelChanged(IModelProviderEvent event) {
		internalModelChanged(event);
	}

	/*
	 * Notifications from xml and annotation may come in different threads. This
	 * depends on the implementation of the model providers, but to make sure
	 * that no race conditions occurs I am synchronizing this method.
	 */
	private synchronized void internalModelChanged(IModelProviderEvent event) {
		if (isDisposed())
			return;
		if (shouldDispose(event)) {
			dispose();
			notifyListeners(event);
			return;
		}
		/*
		 * The client project was changed. Reload the annotation model.
		 */
		if (clientProject == null && getXmlEjbJar().getEjbClientJar() != null) {
			reloadAnnotationModel();
		} else if (clientProject != null
				&& !clientProject.getName().equals(getClientNameFromJarName(getXmlEjbJar().getEjbClientJar()))) {
			reloadAnnotationModel();
		}
		merge(getXmlEjbJar(), getAnnotationEjbJar());
		notifyListeners(event);
	}

	private void reloadAnnotationModel() {
		try {
			disableInternalNotifications();
			((EJBAnnotationReader) annotationModelProvider).dispose();
			annotationModelProvider = loadAnnotationModel(getXmlEjbJar());
			enableInternalNotifications();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private String getClientNameFromJarName(String jarName) {
		if (jarName == null)
			return null;
		if (jarName.endsWith(".jar")) //$NON-NLS-1$
			return jarName.substring(0, jarName.lastIndexOf(".jar")); //$NON-NLS-1$
		return jarName;
	}

	@Override
	protected EJBJar merge(EJBJar ddModel, EJBJar annotationsModel) {
		if (mergedModel == null) {
			mergedModel = (EJBJar) EjbFactory.eINSTANCE.createEJBJar();
			initMergedModelResource((EObject) ddModel);
		} else {
			clearModel(mergedModel);
		}

		mergedModel.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
		try {
			EjbJarMerger merger = new EjbJarMerger(mergedModel, ddModel, ModelElementMerger.ADD);
			merger.process();
			merger = new EjbJarMerger(mergedModel, annotationsModel, ModelElementMerger.ADD);
			merger.process();
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return mergedModel;
	}

	private void clearModel(EJBJar jar) {
		jar.setAssemblyDescriptor(null);
		jar.setEnterpriseBeans(null);
		jar.getDescriptions().clear();
		jar.getDisplayNames().clear();
		jar.setRelationships(null);
		jar.setEjbClientJar(null);
		jar.setInterceptors(null);
		jar.getIcons().clear();
	}

}
