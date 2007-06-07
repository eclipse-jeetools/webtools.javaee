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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.javaee.applicationclient.internal.util.ApplicationclientResourceImpl;

public class AppClient5ModelProvider extends JEE5ModelProvider {
	
	public AppClient5ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.APP_CLIENT_DD_URI));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	public Object getModelObject(IPath modelPath) {
		ApplicationclientResourceImpl appRes = (ApplicationclientResourceImpl)getModelResource(modelPath);
		if (appRes != null && appRes.getContents().size() > 0) 
			return appRes.getApplicationClient();
		return null;
	}


}
