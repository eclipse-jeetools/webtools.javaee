/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 21, 2005
 */
package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.debug.ui.actions.ILaunchable;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;

/**
 * @author blancett
 *
 */
public class EnterpriseModuleArtifact implements IModuleArtifact {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.core.IModuleArtifact#getModule()
	 */
	@Override
	public IModule getModule() {
		return null;
	}
	
	public Class[] getAdapterList() {
		return new Class[] { IModuleArtifact.class, ILaunchable.class };
	}

}
