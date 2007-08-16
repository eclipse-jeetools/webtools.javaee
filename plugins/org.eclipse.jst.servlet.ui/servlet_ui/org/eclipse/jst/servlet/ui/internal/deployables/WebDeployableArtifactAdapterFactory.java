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
package org.eclipse.jst.servlet.ui.internal.deployables;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.ILaunchable;
import org.eclipse.jst.j2ee.internal.web.deployables.WebDeployableArtifactUtil;
import org.eclipse.wst.server.core.IModuleArtifact;
import org.eclipse.wst.server.core.model.ModuleArtifactAdapterDelegate;

public class WebDeployableArtifactAdapterFactory extends ModuleArtifactAdapterDelegate implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[]{ILaunchable.class};
	}


	public IModuleArtifact getModuleArtifact(Object obj) {
		return WebDeployableArtifactUtil.getModuleObject(obj);
	}

}
