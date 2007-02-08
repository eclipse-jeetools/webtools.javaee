/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.web.project.facet;

import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.jee.internal.common.JEEVersionUtil;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class WebFacetInstallDataModelProvider extends org.eclipse.jst.j2ee.web.project.facet.WebFacetInstallDataModelProvider implements IWebFacetInstallDataModelProperties {

	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return JEEVersionUtil.convertWebVersionStringToJ2EEVersionID(version.getVersionString());
	}
}
