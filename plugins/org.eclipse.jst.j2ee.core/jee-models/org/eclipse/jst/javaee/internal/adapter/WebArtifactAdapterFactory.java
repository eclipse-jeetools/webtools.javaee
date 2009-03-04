package org.eclipse.jst.javaee.internal.adapter;

public class WebArtifactAdapterFactory extends JavaEEArtifactAdapterFactory {

	protected String getFullyQualifiedClassName(Object adaptableObject) {
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
	
}
