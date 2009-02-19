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
			org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) adaptableObject;
			return servlet.getServletClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.web.Filter) {
			org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) adaptableObject;
			return filter.getFilterClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.core.Listener) {
			org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) adaptableObject;
			return listener.getListenerClass();
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.webapplication.Servlet) {
			org.eclipse.jst.j2ee.webapplication.Servlet servlet = (org.eclipse.jst.j2ee.webapplication.Servlet) adaptableObject;
			if (servlet.getWebType().isServletType()) {
				return servlet.getServletClass().getQualifiedName();
			}
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.webapplication.Filter) {
			org.eclipse.jst.j2ee.webapplication.Filter filter = (org.eclipse.jst.j2ee.webapplication.Filter) adaptableObject;
			return filter.getFilterClass().getQualifiedName();
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.common.Listener) {
			org.eclipse.jst.j2ee.common.Listener listener = (org.eclipse.jst.j2ee.common.Listener) adaptableObject;
			return listener.getListenerClass().getQualifiedName();
		}
		return null;
	}
	
	private IJavaElement getJavaElement(IJavaProject javaProject, String className) {
		if (className != null && javaProject != null && javaProject.exists()) {
			try {
				return javaProject.findType(className);
			} catch (JavaModelException e) {
				J2EECorePlugin.logError(e);
			}
		}
		return null;
	}

}
