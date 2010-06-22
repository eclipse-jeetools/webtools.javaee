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
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
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
import org.eclipse.jst.jee.ejb.Activator;

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
	protected Path EJB_JAR_XML_PATH = new Path(J2EEConstants.EJBJAR_DD_URI);


	public EJB3MergedModelProvider(IProject project) {
		super(project);
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
		if (modelPath == null){
			return getModelObject();
		} else if (EJB_JAR_XML_PATH.equals(modelPath)){
			return getXmlEjbJar();
		} else if ("java".equals(modelPath.getFileExtension()) || "class".equals(modelPath.getFileExtension())) { //$NON-NLS-1$ //$NON-NLS-2$
			return getAnnotationEjbJar();
		}
		return getModelObject();
	}

	@Override
	public void modify(Runnable runnable, IPath modelPath) {
		/*
		 * Someone has called modify before loading the model. Try to load the
		 * model.
		 */
		if (mergedModel == null)
			getMergedModel();
		if (isDisposed()) {
			return;
		}
		modifyWithBackup(runnable, modelPath);
	}

	/*
	 * The method will call modify to the ddProvider and during this time the
	 * getMergedModel() method will return the ddModel.
	 * 
	 * After dd modify is finished there will be a merge between dd model and
	 * annotation model.
	 * 
	 * During dd modify there might be notifications from ddProvider which will
	 * result in changes of the mergedModel.
	 */
	private void modifyWithBackup(Runnable runnable, IPath modelPath) {
		EJBJar backup = mergedModel;
		try {
			mergedModel = (EJBJar) ddProvider.getModelObject();
			ddProvider.modify(runnable, modelPath);
		} finally {
			mergedModel = backup;
		}
		clearModel(mergedModel);
		/*
		 * Reload the model.
		 */
		merge(getXmlEjbJar(), getAnnotationEjbJar());
	}

	@Override
	protected void annotationModelChanged(IModelProviderEvent event) {
		internalModelChanged(event);
	}

	@Override
	protected void xmlModelChanged(IModelProviderEvent event) {
		internalModelChanged(event);
	}

	/*
	 * Notifications from xml and annotation may come in different threads. This
	 * depends on the implementation of the model providers, but to make sure
	 * that no race conditions occurs I am synchronizing this method.
	 */
	private synchronized void internalModelChanged(IModelProviderEvent event) {
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
			Activator.logError(e);
		}
	}

	private String getClientNameFromJarName(String jarName) {
		if (jarName == null)
			return null;
		if (jarName.endsWith(IJ2EEModuleConstants.JAR_EXT))
			return jarName.substring(0, jarName.lastIndexOf(IJ2EEModuleConstants.JAR_EXT));
		return jarName;
	}

	@Override
	protected EJBJar merge(EJBJar ddModel, EJBJar annotationsModel) {
		try {
			if (mergedModel != ddModel) {
				clearModel(mergedModel);
				mergedModel.setEnterpriseBeans(EjbFactory.eINSTANCE.createEnterpriseBeans());
				mergeWithModel(ddModel);
				mergeWithModel(annotationsModel);
			}
		} catch (ModelException e) {
			Activator.logError(e);
		}
		return mergedModel;
	}

	private void mergeWithModel(EJBJar annotationsModel) throws ModelException {
		EjbJarMerger merger = new EjbJarMerger(mergedModel, annotationsModel, ModelElementMerger.ADD);
		merger.process();
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

	@Override
	protected EJBJar createNewModelInstance() {
		return EjbFactory.eINSTANCE.createEJBJar();
	}

}
