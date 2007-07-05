/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class AppClientComponentArchiveLoadAdapter extends ComponentArchiveLoadAdapter {

	public AppClientComponentArchiveLoadAdapter(IVirtualComponent vComponent) {
		super(vComponent);
	}
	
	@Override
	protected IPath getDefaultModelObjectPath() {
		return new Path(J2EEConstants.APP_CLIENT_DD_URI);
	}

}
