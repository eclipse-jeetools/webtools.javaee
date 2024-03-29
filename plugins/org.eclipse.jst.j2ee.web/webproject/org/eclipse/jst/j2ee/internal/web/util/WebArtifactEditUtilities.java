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
package org.eclipse.jst.j2ee.internal.web.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class WebArtifactEditUtilities {
	
	public static IVirtualComponent getWebComponent(Servlet servlet) {
		IProject project = ProjectUtilities.getProject(servlet);
		IVirtualComponent component = ComponentCore.createComponent(project);
			WebArtifactEdit edit = null;
			try {
				if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
					edit = WebArtifactEdit.getWebArtifactEditForRead(component);
					WebApp webapp = edit.getWebApp();
					if (webapp.getServletNamed(servlet.getServletName()) != null)
						return component;
				}
			} finally {
				if (edit != null)
					edit.dispose();
			}
		return null;
	}

}
