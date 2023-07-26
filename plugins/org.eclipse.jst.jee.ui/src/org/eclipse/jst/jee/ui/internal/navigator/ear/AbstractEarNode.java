/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * @author gormov
 *
 */
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
			IPath relativeRuntimePath = null;
			if (runtimePath != null){
				relativeRuntimePath = runtimePath.makeRelative();
			}
			IVirtualReference[] refComponents = earComponent.getReferences();
			for (int i = 0; i < refComponents.length; i++) {
				IVirtualComponent module = refComponents[i].getReferencedComponent();
				if (module == null) continue;
				// if component types passed in is null then return all components
				if (componentTypes == null || componentTypes.size() == 0) {
					components.add(refComponents[i]);
				} else {
					if (componentTypes.contains(JavaEEProjectUtilities.getJ2EEComponentType(module))) {
						if (relativeRuntimePath != null){
							IPath runtimePath2 = getRealRuntimePath(refComponents[i]);
							if (runtimePath2.makeRelative().equals(relativeRuntimePath)) {
								components.add(refComponents[i]);
							}
						} else {
							components.add(refComponents[i]);
						}	
					}
				}
			}
		}
		return components;
	}

	/**
	 * This method will return complete list of the binaries contained in the lib dir.
	 * 
	 * @param virtualComponent
	 * @param runtimePath
	 * @return
	 */
	protected List getBinariesInLibDir(IVirtualComponent virtualComponent, IPath runtimePath, List componentTypes) {
		List components = new ArrayList();
		IVirtualComponent earComponent = virtualComponent;
		if (earComponent != null ) {
			IPath relativeRuntimePath = null;
			if (runtimePath != null){
				relativeRuntimePath = runtimePath.makeRelative();
			}
			IVirtualReference[] refComponents = earComponent.getReferences();
			for (int i = 0; i < refComponents.length; i++) {
				IVirtualComponent module = refComponents[i].getReferencedComponent();
				if (module == null) continue;
				// if component types passed in is null then return all components
				if (module.isBinary() && relativeRuntimePath != null && !relativeRuntimePath.isEmpty() && getRealRuntimePath(refComponents[i]).makeRelative().equals(relativeRuntimePath)) {
					if (componentTypes != null && !componentTypes.contains(JavaEEProjectUtilities.getJ2EEComponentType(module)) ) {
						if (runtimePath != null && !runtimePath.makeAbsolute().equals(new Path("/"))){//$NON-NLS-1$
							components.add(refComponents[i]);
						}
					} else {
						components.add(refComponents[i]);
					}


				} 
			}
		}
		return components;
	}
	
	
	/**
	 * This method will return all the binaries contained in the lib dir.
	 * It will not consider modules in lib dir as libraries for that use 
	 * getBinariesInLibDir(IVirtualComponent virtualComponent, IPath runtimePath, List componentTypes)
	 * 
	 * @param virtualComponent
	 * @param runtimePath
	 * @return
	 * @deprecated since it is not returning the complete list of libraries
	 *  use: getBinariesInLibDir(IVirtualComponent virtualComponent, IPath runtimePath, List componentTypes)
	 */
	protected List getBinariesInLibDir(IVirtualComponent virtualComponent, IPath runtimePath) {
		return getBinariesInLibDir(virtualComponent, runtimePath, null);
	}

	protected IPath getRealRuntimePath(IVirtualReference vr){
		IPath runtimePath2 = vr.getRuntimePath();
		if(vr.getArchiveName() != null){
			IPath namePath = new Path (vr.getArchiveName());
			if (namePath.segmentCount()>1 && runtimePath2.segmentCount()==0){
				runtimePath2 = new Path("/"+namePath.removeLastSegments(1).toPortableString()); //$NON-NLS-1$
			}
		}
		return runtimePath2;
	}


}
