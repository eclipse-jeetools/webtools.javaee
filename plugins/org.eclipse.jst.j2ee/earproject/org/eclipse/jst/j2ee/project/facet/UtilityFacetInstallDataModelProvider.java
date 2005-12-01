/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class UtilityFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IUtilityFacetInstallDataModelProperties {
	
	public Object getDefaultProperty(String propertyName) {
		if (FACET_ID.equals(propertyName)) {
			return J2EEProjectUtilities.UTILITY;
		}
		return super.getDefaultProperty(propertyName);
	}

	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return 0;
	}
}
