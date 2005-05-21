/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.application.impl.ApplicationFactoryImpl;
import org.eclipse.jst.j2ee.internal.application.provider.ApplicationItemProvider;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.EARProjectMap;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EENotificationImpl;

public class J2EEApplicationItemProvider extends ApplicationItemProvider implements IAdaptable {

	protected static final Class IRESOURCE_CLASS = IResource.class;
	protected static final Class IPROJECT_CLASS = IProject.class;

	protected Map children = new HashMap();
	protected List resourceAdapters = null;

	/**
	 * Constructor for J2EEApplicationItemProvider.
	 * 
	 * @param adapterFactory
	 */
	public J2EEApplicationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * @see ItemProviderAdapter#getChildren(Object)
	 */
	public Collection getChildren(Object object) {
		List localChildren = (List) children.get(object);
		if (localChildren == null)
			return initChildren(object);
		return localChildren;
	}

	protected List initChildren(Object object) {
		Application app = (Application) object;
		List localChildren = new ArrayList(2);
		// MDE: changed getParent(object) to just app
		localChildren.add(new ModulesItemProvider(getAdapterFactory(), null, null, app, app.getModules()));

		IProject project = ProjectUtilities.getProject(app);
		EAREditModel editModel = null;
		try {
			EARNatureRuntime runtime = EARNatureRuntime.getRuntime(project);
			if (runtime != null) {
				editModel = runtime.getEarEditModelForRead(this);
				EARProjectMap map = editModel.getEARProjectMap();
				localChildren.add(map);
				map.eResource().eAdapters().add(getNewAdapter(app));
			}
		} finally {
			if (editModel != null)
				editModel.releaseAccess(this);
		}
		localChildren.add(new J2EEUtilityJarItemProvider(app, getAdapterFactory(), this));

		children.put(object, localChildren);
		return localChildren;
	}

	protected ModulesItemProvider getModulesNode(Object object) {
		return (ModulesItemProvider) ((List) getChildren(object)).get(0);
	}


	/**
	 * @see Adapter#notifyChanged(new ENotificationImpl((InternalEObject)Notifier,
	 *      int,(EStructuralFeature) EObject, Object, Object, int))
	 * @deprecated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Application.class)) {
			case ApplicationPackage.APPLICATION__MODULES :
				break;
			default :
				super.notifyChanged(notification);
		}
		if (notification.getEventType() == Notification.REMOVING_ADAPTER && notification.getOldValue() == this)
			children.remove(notification.getNotifier());
		else if (notification.getFeature() == ApplicationFactoryImpl.getPackage().getApplication_Modules())
			modulesChanged((Application) notification.getNotifier(), notification.getEventType(), notification.getOldValue(), notification.getNewValue(), notification.getPosition());
	}

	protected void modulesChanged(Application app, int eventType, Object oldValue, Object newValue, int pos) {
		ModulesItemProvider provider = getModulesNode(app);
		Collection grandChildren = provider.getChildren();
		switch (eventType) {
			case Notification.ADD : {
				grandChildren.add(newValue);
				break;
			}
			case Notification.ADD_MANY : {
				grandChildren.addAll((Collection) newValue);
				break;
			}
			case Notification.REMOVE : {
				grandChildren.remove(oldValue);
				break;
			}
			case Notification.REMOVE_MANY : {
				grandChildren.removeAll((Collection) oldValue);
				break;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		super.dispose();
		List adapters = getResourceAdapters();
		ResourceAdapter adapter = null;
		for (int i = 0; i < adapters.size(); i++) {
			adapter = (ResourceAdapter) adapters.get(i);
			adapter.getTarget().eAdapters().remove(adapter);
		}
	}

	protected class ResourceAdapter extends AdapterImpl {

		private Application app = null;

		public ResourceAdapter(Application app) {
			this.app = app;
		}

		public void notifyChanged(Notification notification) {

			Resource res = (Resource) notification.getNotifier();
			if (notification.getEventType() == Notification.SET && notification.getFeatureID(null) == Resource.RESOURCE__IS_LOADED && !res.isLoaded()) {
				J2EEApplicationItemProvider.this.children.remove(app);
				res.eAdapters().remove(ResourceAdapter.this);
				fireNotifyChanged(new J2EENotificationImpl(app, Notification.MOVE, (Object) null, (Object) null, 0));

			}
		}
	}

	protected ResourceAdapter getNewAdapter(Application key) {
		ResourceAdapter adapter = new ResourceAdapter(key);
		getResourceAdapters().add(adapter);
		return adapter;
	}

	/**
	 * @return Returns the resourceAdapters.
	 */
	protected List getResourceAdapters() {
		if (resourceAdapters == null)
			resourceAdapters = new ArrayList();
		return resourceAdapters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}

}