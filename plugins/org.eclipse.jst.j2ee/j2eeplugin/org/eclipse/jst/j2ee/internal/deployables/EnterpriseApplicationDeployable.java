/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.server.core.IEnterpriseApplication;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.server.core.IModule;



public class EnterpriseApplicationDeployable extends J2EEFlexProjDeployable implements IEnterpriseApplication {

	public static final String EAR_MODULE_TYPE = IModuleConstants.JST_EAR_MODULE;
	

	public EnterpriseApplicationDeployable(IProject project, String aFactoryId, IVirtualComponent aComponent) {
		super(project, aFactoryId, aComponent);

		update();

	}

	public String getJ2EESpecificationVersion() {
			if (component != null)
				return component.getVersion();
			return null;
		}

	public IModule[] getModules() {
		List modules = new ArrayList();
    	IVirtualReference[] components = component.getReferences();
    	for (int i=0; i<components.length; i++) {
			IVirtualReference reference = components[i];
			IVirtualComponent virtualComp = reference.getReferencedComponent();
			Object module = FlexibleProjectServerUtil.getModule(virtualComp);
			if (module!=null)
				modules.add(module);
		}
        return (IModule[]) modules.toArray(new IModule[modules.size()]);
	}
    
    /**
     * Returns the child modules of this module.
     * 
     * @return org.eclipse.wst.server.core.model.IModule[]
     */
    public IModule[] getChildModules() {
        return getModules();
    }

	public String getURI(IModule module) {
		IVirtualComponent comp = ComponentCore.createComponent(module.getProject());
		EARArtifactEdit earEdit = null;
		String aURI = null;
		try {
			earEdit = EARArtifactEdit.getEARArtifactEditForRead(component);
			if (earEdit != null) {
				aURI = earEdit.getModuleURI(comp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (earEdit != null)
				earEdit.dispose();
		}
		return aURI;
	}
	
	public String getType() {
		return "j2ee.ear"; //$NON-NLS-1$
	}
}