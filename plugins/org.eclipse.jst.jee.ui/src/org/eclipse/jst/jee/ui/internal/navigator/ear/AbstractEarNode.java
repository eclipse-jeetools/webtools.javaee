/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public abstract class AbstractEarNode {
	public static int MODULES_TYPE = 0;    
	public static int LIBS_TYPE = 1;

	protected int type;

	protected static List implicitUtilityReferenceTypes =
		Arrays.asList(new String[] {  
				IModuleConstants.JST_APPCLIENT_MODULE,
				IModuleConstants.JST_WEB_MODULE,
				IModuleConstants.JST_EJB_MODULE,
				IModuleConstants.JST_CONNECTOR_MODULE});

	private IProject earProject;

	public AbstractEarNode(IProject earProject) {
		this.earProject = earProject;
	}

	public IProject getEarProject() {
		return earProject;
	}

	public abstract List getModules();


	public int getType() {
		return type;
	}

	public abstract String getText();

	protected List getComponentReferencesAsList(List componentTypes, IVirtualComponent virtualComponent, IPath runtimePath) {
		List components = new ArrayList();
		IVirtualComponent earComponent = virtualComponent;
		if (earComponent != null ) {
			IVirtualReference[] refComponents = earComponent.getReferences();
			for (int i = 0; i < refComponents.length; i++) {
				IVirtualComponent module = refComponents[i].getReferencedComponent();
				if (module == null) continue;
				// if component types passed in is null then return all components
				if (componentTypes == null || componentTypes.size() == 0) {
					components.add(refComponents[i]);
				} else {
					if (componentTypes.contains(JavaEEProjectUtilities.getJ2EEComponentType(module))) {
						components.add(refComponents[i]);
					}
				}
			}
		}
		return components;
	}


}
