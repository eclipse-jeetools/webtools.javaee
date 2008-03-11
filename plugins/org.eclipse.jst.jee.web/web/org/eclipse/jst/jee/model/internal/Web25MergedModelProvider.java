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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jee.model.internal.common.AbstractMergedModelProvider;
import org.eclipse.jst.jee.model.internal.mergers.ModelElementMerger;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;
import org.eclipse.jst.jee.model.internal.mergers.WebAppMerger;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class Web25MergedModelProvider extends AbstractMergedModelProvider<WebApp> {

	private WebAnnotationReader annotationReader;

	public Web25MergedModelProvider(IProject project) {
		super(project);
	}

	/**
	 * (non-Javadoc) Synchronizing the loading of the model
	 * 
	 * @see org.eclipse.jst.jee.model.internal.common.AbstractMergedModelProvider#loadModel()
	 */
	protected final synchronized void loadModel() throws CoreException {
		super.loadModel();
	}

	@Override
	protected IModelProvider loadAnnotationModel(WebApp ddModel) throws CoreException {
		annotationReader = new WebAnnotationReader(ProjectFacetsManager.create(project), ddModel);
		return annotationReader;
	}

	@Override
	protected IModelProvider loadDeploymentDescriptorModel() throws CoreException {
		ddProvider = new Web25ModelProvider(project);
		return ddProvider;
	}

	private WebApp getAnnotationWebApp() {
		return (WebApp) annotationReader.getModelObject();
	}

	private WebApp getXmlWebApp() {
		return (WebApp) ddProvider.getModelObject();
	}

	public Object getModelObject(IPath modelPath) {
		return null;
	}

	public void modify(Runnable runnable, IPath modelPath) {
		/*
		 * Someone has called modify before loading the model. Try to load the
		 * model.
		 */
		getMergedModel();
		/*
		 * Still not supporting modifications of the merged model. During modify
		 * the model is becoming the ddModel. After modifying the model is
		 * unloaded.
		 */
		mergedModel = (WebApp) ddProvider.getModelObject();
		ddProvider.modify(runnable, modelPath);
		
		/*
		 * Reload the model next time it is wanted.
		 */
		mergedModel = null;
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
		merge(getXmlWebApp(), getAnnotationWebApp());
		notifyListeners(event);
	}

	/**
	 * Returns the dispose state of this model provider. When the provider is
	 * disposed it can not be used until getModelObject is called again.
	 * 
	 * Subclasses may override this method.
	 * 
	 * @return true if the model provider is to be treated as disposed
	 */
	protected boolean isDisposed() {
		return ddProvider == null && annotationReader == null;
	}

	/**
	 * Dispose the model provider. If the provider is already disposed the
	 * method has no effect.
	 * 
	 * Subclasses may override this method.
	 * 
	 * @see #isDisposed()
	 */
	protected void dispose() {
		if (isDisposed())
			return;
		annotationReader.dispose();
		ddProvider = null;
		annotationReader = null;
		mergedModel = null;
	}

	@Override
	protected WebApp merge(WebApp ddModel, WebApp annotationsModel) {
		mergedModel = (WebApp) WebFactory.eINSTANCE.createWebApp();
		try {
			WebAppMerger merger = new WebAppMerger(mergedModel, ddModel, ModelElementMerger.ADD);
			merger.process();
			merger = new WebAppMerger(mergedModel, annotationsModel, ModelElementMerger.ADD);
			merger.process();
		} catch (ModelException e) {
			e.printStackTrace();
		}
		return mergedModel;
	}
}
