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
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.web.internal.util.WebResourceImpl;

public class Web25ModelProvider extends JEE5ModelProvider implements IModelProvider {
	
	private WebResourceImpl webResource = null;

	public Web25ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.WEBAPP_DD_URI));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	public Object getModelObject(IPath modelPath) {
		WebResourceImpl webRes = (WebResourceImpl)getModelResource(modelPath);
		if (webRes != null && webRes.getContents().size() > 0) 
			return webRes.getWebApp();
		return null;
	}

}
