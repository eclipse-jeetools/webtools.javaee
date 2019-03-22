/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.model.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class EAR5ModelProviderFactory implements IModelProviderFactory {

	private static Map<IProject, IModelProvider> providersMap = new HashMap<IProject, IModelProvider>();
	
	private static IResourceChangeListener listener = new IResourceChangeListener() {
		
		public void resourceChanged(IResourceChangeEvent event) {
			IResource project = event.getResource();
			if (project instanceof IProject) {
				providersMap.remove(project);
			}
			
		}
	};
	static {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, IResourceChangeEvent.PRE_DELETE | IResourceChangeEvent.PRE_CLOSE);
	}
	public synchronized IModelProvider create(IProject project) {
		IModelProvider provider = providersMap.get(project);
		if (provider == null) {
			provider = new EAR5ModelProvider(project);
			providersMap.put(project, provider);
		}
		return provider;
	}

	public IModelProvider create(IVirtualComponent component) {
		return create(component.getProject());
	}

}
