/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.debug.ui.actions.ILaunchable;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;

/**
 * @author blancett
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnterpriseModuleArtifact implements IModuleArtifact {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.server.core.IModuleArtifact#getModule()
	 */
	public IModule getModule() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Class[] getAdapterList() {
		return new Class[] { IModuleArtifact.class, ILaunchable.class };
	}

}
