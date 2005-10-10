/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.deployables;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.deployables.FlexibleProjectServerUtil;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.server.core.IWebModule;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleType;

/**
 * @version 1.0
 * @author
 */
public class J2EEFlexProjWebDeployable extends J2EEFlexProjDeployable implements IWebModule, IModuleType {
    
    /**
     * @param aNature
     * @param aFactoryId
     */
    public J2EEFlexProjWebDeployable(IProject project, String aFactoryId, IVirtualComponent aComponent) {
        super(project, aFactoryId, aComponent);
    }
    
	public String getId() {
		return getProject().getName();
	}

    public String getContextRoot() {
		Properties props = component.getMetaProperties();
		if(props.containsKey(J2EEConstants.CONTEXTROOT))
			return props.getProperty(J2EEConstants.CONTEXTROOT);
	    return component.getName();
    }
	       
	public String getJ2EESpecificationVersion() {
		if (component != null)
			return J2EEVersionUtil.convertVersionIntToString(J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(component.getVersion()));
		return null;
	}

    public String getJSPSpecificationVersion() {

    	String ret = "1.2"; //$NON-NLS-1$
    	String stringVersion = getServletSpecificationVersion();
		int nVersion = J2EEVersionUtil.convertVersionStringToInt(stringVersion);
       	switch( nVersion ){
    	
    		case 22:
    			ret = J2EEVersionConstants.VERSION_1_1_TEXT;
    			break;
    		case 23:
    			ret = J2EEVersionConstants.VERSION_1_2_TEXT;
    			break;
    		case 24:	
    			ret = J2EEVersionConstants.VERSION_2_0_TEXT;
    			break;
      		default:
    			ret = J2EEVersionConstants.VERSION_1_1_TEXT;
    			break;    			
    	}
    	return ret; 
    }

    public String getServletSpecificationVersion() {

		if (component != null)
			return component.getVersion();
		return null;
	}
    
    public String getURI(IModule module) {
    	String result = ""; //$NON-NLS-1$
    	IVirtualComponent comp = ComponentCore.createComponent(module.getProject());
    	if (!comp.isBinary()) {
    		IVirtualReference ref = component.getReference(comp.getName());
    		result = ref.getRuntimePath().append(comp.getName()+IJ2EEModuleConstants.JAR_EXT).toString();
    	}
    	return result;
    }

    public String getType() {
        return "j2ee.web"; //$NON-NLS-1$
    }

    public String getVersion() {
        return "1.2"; //$NON-NLS-1$
    }

    /**
     * Returns the child modules of this module.
     * 
     * @return org.eclipse.wst.server.core.model.IModule[]
     */
    public IModule[] getChildModules() {
    	return getModules();
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
}