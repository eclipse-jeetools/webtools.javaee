package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.j2ee.project.WebUtilities;

public class WebResourcePropertyTester extends PropertyTester {

	private static final String WEB_RESOURCE = "webResource"; //$NON-NLS-1$
	
	public WebResourcePropertyTester() {
	}

	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof IResource))
			return false;
		
		IResource resource = (IResource) receiver;
		if (WEB_RESOURCE.equals(property)) {
			return WebUtilities.isWebResource(resource);
		} 
		
		return false;
	}

}
