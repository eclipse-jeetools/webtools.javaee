/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.model.internal;

import java.util.HashMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class AppClient5ModelProviderFactory implements IModelProviderFactory {
	
	private HashMap<IProject, IModelProvider> xmlResources = new HashMap<IProject, IModelProvider>();

	public IModelProvider create(IProject project) {
		IModelProvider result = getResource(project);
		if(result == null){
			result = new AppClient5ModelProvider(project);
			addResource(project, result);
		}
		return result;
	}

	public IModelProvider create(IVirtualComponent component) {
		return new AppClient5ModelProvider(component.getProject());
	}
	
	private void addResource(IProject project, IModelProvider modelProvider){
		xmlResources.put(project, modelProvider);
	}

	private IModelProvider getResource(IProject project){
		return xmlResources.get(project);
	}
}
