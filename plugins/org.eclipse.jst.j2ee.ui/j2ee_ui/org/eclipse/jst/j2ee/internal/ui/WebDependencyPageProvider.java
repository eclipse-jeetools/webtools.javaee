/******************************************************************************
 * Copyright (c) 2009 Red Hat
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;

public class WebDependencyPageProvider extends AddModuleDependenciesPropertiesPage {

	public WebDependencyPageProvider(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}

	@Override
	protected String getRuntimePath(IVirtualComponent addedComp, String wizardPath) {
		String lastSegment = new Path(wizardPath).lastSegment();
		return new Path(J2EEConstants.WEB_INF_LIB).append(lastSegment).makeAbsolute().toString();
	}

}
