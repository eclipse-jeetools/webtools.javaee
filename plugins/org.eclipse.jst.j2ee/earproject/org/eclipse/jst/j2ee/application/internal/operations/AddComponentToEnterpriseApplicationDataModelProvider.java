/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.project.EJBUtilities;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class AddComponentToEnterpriseApplicationDataModelProvider extends CreateReferenceComponentsDataModelProvider implements IAddComponentToEnterpriseApplicationDataModelProperties {

	public AddComponentToEnterpriseApplicationDataModelProvider() {
		super();
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (TARGET_COMPONENTS_TO_URI_MAP.equals(propertyName)) {
			Map map = new HashMap();
			List components = (List) getProperty(TARGET_COMPONENT_LIST);
			for (int i = 0; i < components.size(); i++) {
				IVirtualComponent component = (IVirtualComponent) components.get(i);
				IProject project = component.getProject();
				String name = component.getName();
				if(name != null)
					name = name.replace(' ','_');
				if( name != null ) 
					name = new Path(name).lastSegment();
				if (!component.isBinary()) {
					if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
						name += IJ2EEModuleConstants.WAR_EXT;
					} else if (JavaEEProjectUtilities.isJCAProject(project)) {
						name += IJ2EEModuleConstants.RAR_EXT;
					} else {
						name += IJ2EEModuleConstants.JAR_EXT;
					}
				}
				map.put(component, name);
			}
			setProperty(propertyName, map);
			return map;
		}
		
		else if (TARGET_COMPONENTS_DEPLOY_PATH_MAP.equals(propertyName)) {
			Map map = new HashMap();
			List CompList = (List) getProperty(TARGET_COMPONENT_LIST);
			IVirtualComponent earComp = (IVirtualComponent)getProperty(SOURCE_COMPONENT);
			String libDir = EarUtilities.getEARLibDir(earComp);
			if (libDir != null && libDir.length() > 0) {
				String libDirPath = new Path(libDir).makeAbsolute().toString();
				for (int i = 0; i < CompList.size(); i++) {
					if(null != CompList.get(i)){
						IVirtualComponent Comp = (IVirtualComponent) CompList.get(i);
						IProject CompProject = Comp.getProject();
						if(JavaEEProjectUtilities.isUtilityProject(CompProject) && !EJBUtilities.isEJBClientProject(CompProject) && JavaEEProjectUtilities.isJEEComponent(earComp)){
							map.put(Comp, libDirPath);
						}
					}
				}
				setProperty(propertyName, map);
				return map;
			} return super.getDefaultProperty(propertyName);
		}
		 else
		
		return super.getDefaultProperty(propertyName);
	}


	@Override
	public IDataModelOperation getDefaultOperation() {
		return new AddComponentToEnterpriseApplicationOp(model);
	}

}
