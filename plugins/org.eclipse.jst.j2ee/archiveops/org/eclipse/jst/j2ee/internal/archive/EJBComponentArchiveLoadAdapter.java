/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class EJBComponentArchiveLoadAdapter extends J2EEComponentArchiveLoadAdapter {

	public EJBComponentArchiveLoadAdapter(IVirtualComponent vComponent) {
		this(vComponent, true);
	}

	public EJBComponentArchiveLoadAdapter(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		super(vComponent, includeClasspathComponents);
	}

	@Override
	protected IPath getDefaultModelObjectPath() {
		return new Path(J2EEConstants.EJBJAR_DD_URI);
	}
}
