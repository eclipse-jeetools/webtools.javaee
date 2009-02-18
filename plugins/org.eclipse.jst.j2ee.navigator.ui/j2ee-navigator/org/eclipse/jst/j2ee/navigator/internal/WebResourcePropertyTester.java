package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class WebResourcePropertyTester extends PropertyTester {

	private static final String WEB_RESOURCE = "webResource"; //$NON-NLS-1$
	
	public WebResourcePropertyTester() {
	}

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof IResource))
			return false;
		
		IResource resource = (IResource) receiver;
		if (WEB_RESOURCE.equals(property)) {
			IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
			if (comp != null) {
				IPath rootPath = comp.getRootFolder().getWorkspaceRelativePath();
				IPath webInfPath = rootPath.append(J2EEConstants.WEB_INF);
				IPath metaInfPath = rootPath.append(J2EEConstants.META_INF);
				IPath resourcePath = resource.getFullPath();
				return rootPath.isPrefixOf(resourcePath) && 
						!rootPath.equals(resourcePath) &&
						!webInfPath.isPrefixOf(resourcePath) && 
						!metaInfPath.isPrefixOf(resourcePath);
			}
		}
		
		return false;
	}

}
