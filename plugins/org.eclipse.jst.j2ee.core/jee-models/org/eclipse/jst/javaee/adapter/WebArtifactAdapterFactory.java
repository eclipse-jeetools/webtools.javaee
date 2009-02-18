package org.eclipse.jst.javaee.adapter;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;

public class WebArtifactAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IJavaElement.class) {
			IJavaProject javaProject = getJavaProject(adaptableObject);
			String className = getFullyQualifiedClassName(adaptableObject);
			return getJavaElement(javaProject, className);
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] { IJavaElement.class };
	}
	
	private IJavaProject getJavaProject(Object adaptableObject) {
		IResource resource = getResource(adaptableObject);
		if (resource != null) {
			IProject project = resource.getProject();
			return JavaCore.create(project);
		}
		return null;
	}
	
	private IResource getResource(Object adaptableObject) {
		if (adaptableObject instanceof IAdaptable) {
			return (IResource) ((IAdaptable) adaptableObject).getAdapter(IResource.class);
		} 
		return (IResource) Platform.getAdapterManager().getAdapter(adaptableObject, IResource.class);
	}
	
	private String getFullyQualifiedClassName(Object adaptableObject) {
		if (adaptableObject instanceof org.eclipse.jst.javaee.web.Servlet) {
			return ((org.eclipse.jst.javaee.web.Servlet) adaptableObject).getServletClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.web.Filter) {
			return ((org.eclipse.jst.javaee.web.Filter) adaptableObject).getFilterClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.core.Listener) {
			return ((org.eclipse.jst.javaee.core.Listener) adaptableObject).getListenerClass();
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.webapplication.Servlet) {
			return ((org.eclipse.jst.j2ee.webapplication.Servlet) adaptableObject).getServletClass().getQualifiedName();
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.webapplication.Filter) {
			return ((org.eclipse.jst.j2ee.webapplication.Filter) adaptableObject).getFilterClass().getQualifiedName();
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.common.Listener) {
			return ((org.eclipse.jst.j2ee.common.Listener) adaptableObject).getListenerClass().getQualifiedName();
		}
		return null;
	}
	
	private IJavaElement getJavaElement(IJavaProject javaProject, String className) {
		if (javaProject != null && javaProject.exists()) {
			try {
				return javaProject.findType(className);
			} catch (JavaModelException e) {
				J2EECorePlugin.logError(e);
			}
		}
		return null;
	}

}
