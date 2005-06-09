package org.eclipse.jst.j2ee.internal.web.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class WebArtifactEditUtilities {
	
	public static IVirtualComponent getWebComponent(Servlet servlet) {
		IProject project = ProjectUtilities.getProject(servlet);
		IFlexibleProject flexProject = ComponentCore.createFlexibleProject(project);
		IVirtualComponent[] components = flexProject.getComponents();
		for (int i = 0; i < components.length; i++) {
			IVirtualComponent component = components[i];
			WebArtifactEdit edit = null;
			try {
				if (component.getComponentTypeId().equals(IModuleConstants.JST_WEB_MODULE)) {
					edit = WebArtifactEdit.getWebArtifactEditForRead(component);
					WebApp webapp = edit.getWebApp();
					if (webapp.getServletNamed(servlet.getServletName()) != null)
						return component;
				}
			} finally {
				if (edit != null)
					edit.dispose();
			}
		}
		return null;
	}

}
