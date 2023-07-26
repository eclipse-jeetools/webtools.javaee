/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.j2ee.project.WebUtilities;

public class WebResourcePropertyTester extends PropertyTester {

	private static final String WEB_RESOURCE = "webResource"; //$NON-NLS-1$
	
	public WebResourcePropertyTester() {
	}

	@Override
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
