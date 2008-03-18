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
package org.eclipse.jst.jee.model.internal.common;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.IModelProviderListener;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public abstract class AbstractMergedModelProvider<T> implements IModelProvider {

	protected IModelProvider ddProvider;

	protected boolean isOnceDisposed = false;

	private class AnnotationModelListener implements IModelProviderListener {
		public void modelsChanged(IModelProviderEvent event) {
			AbstractMergedModelProvider.this.annotationModelChanged(event);
		}
	}

	private class XmlModelListener implements IModelProviderListener {
		public void modelsChanged(IModelProviderEvent event) {
			AbstractMergedModelProvider.this.xmlModelChanged(event);
		}
	}

	protected Collection<IModelProviderListener> listeners;

	protected IProject project;

	private AnnotationModelListener annotationModelListener;
	private XmlModelListener xmlModelListener;

	protected T mergedModel;

	public AbstractMergedModelProvider(IProject project) {
		this.project = project;
	}

	public void addListener(IModelProviderListener listener) {
		getListeners().add(listener);
	}

	/**
	 * Returns the model merged from annotation and xml model. If the project is
	 * closed or does not exist the returns <code>null</code>
	 * 
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject()
	 */
	public Object getModelObject() {
		return getMergedModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#modify(java.lang.Runnable,
	 *      org.eclipse.core.runtime.IPath)
	 */
	public void modify(Runnable runnable, IPath modelPath) {
	}

	public void removeListener(IModelProviderListener listener) {
		getListeners().remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#validateEdit(org.eclipse.core.runtime.IPath,
	 *      java.lang.Object)
	 */
	public IStatus validateEdit(IPath modelPath, Object context) {
		if (ddProvider == null)
			getModelObject();
		return ddProvider.validateEdit(modelPath, context);
	}

	protected abstract void annotationModelChanged(IModelProviderEvent event);

	protected abstract void xmlModelChanged(IModelProviderEvent event);

	protected abstract T merge(T ddModel, T annotationsModel);

	/**
	 * Load the annotation model in the context of the ddModel.
	 * 
	 * @param ddModel
	 * @return
	 * @throws CoreException
	 */
	protected abstract IModelProvider loadAnnotationModel(T ddModel) throws CoreException;

	/**
	 * @return
	 * @throws CoreException
	 */
	protected abstract IModelProvider loadDeploymentDescriptorModel() throws CoreException;

	protected Collection<IModelProviderListener> getListeners() {
		if (listeners == null)
			listeners = new HashSet<IModelProviderListener>();
		return listeners;
	}

	protected T getMergedModel() {
		try {
			if (mergedModel == null)
				loadModel();
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}
		return mergedModel;
	}

	protected void loadModel() throws CoreException {
		if (project.isAccessible() == false)
			throw new IllegalStateException("The project <" + project + "> is not accessible."); //$NON-NLS-1$//$NON-NLS-2$
		ddProvider = loadDeploymentDescriptorModel();
		if (ddProvider == null || ddProvider.getModelObject() == null)
			return;
		IModelProvider annotationModelProvider = loadAnnotationModel((T) ddProvider.getModelObject());
		if (annotationModelProvider == null || annotationModelProvider.getModelObject() == null)
			return;
		T ddModel = (T) ddProvider.getModelObject();
		T annotationModel = (T) annotationModelProvider.getModelObject();

		xmlModelListener = new XmlModelListener();
		ddProvider.addListener(xmlModelListener);
		annotationModelListener = new AnnotationModelListener();
		annotationModelProvider.addListener(annotationModelListener);
		mergedModel = merge(ddModel, annotationModel);
		isOnceDisposed = false;
	}

	protected void notifyListeners(IModelProviderEvent event) {
		event.setModel(this);
		event.setProject(project);
		for (IModelProviderListener listener : getListeners()) {
			listener.modelsChanged(event);
		}
	}

	protected boolean shouldDispose(IModelProviderEvent event) {
		return (event.getEventCode() == IModelProviderEvent.UNLOADED_RESOURCE);
	}

}
