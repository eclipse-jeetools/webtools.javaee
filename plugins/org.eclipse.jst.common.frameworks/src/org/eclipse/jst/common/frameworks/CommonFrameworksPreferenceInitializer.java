/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.frameworks;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;



public class CommonFrameworksPreferenceInitializer extends
		AbstractPreferenceInitializer {

	public CommonFrameworksPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	public void initializeDefaultPreferences() {
		IEclipsePreferences node = new DefaultScope().getNode(CommonFrameworksPlugin.getDefault().getBundle().getSymbolicName());
		
		// migration preferences
		node.put(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER, ProductManager.getProperty(IProductConstants.DEFAULT_SOURCE_FOLDER));
		node.put(CommonFrameworksPlugin.OUTPUT_FOLDER, ProductManager.getProperty(IProductConstants.OUTPUT_FOLDER));
	}

}
