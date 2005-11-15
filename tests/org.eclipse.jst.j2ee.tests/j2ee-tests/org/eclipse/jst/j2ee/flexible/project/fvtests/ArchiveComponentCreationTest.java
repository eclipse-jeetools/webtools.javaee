/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


public class ArchiveComponentCreationTest extends TestCase {

    
    public static Test suite() {
        return new TestSuite(ArchiveComponentCreationTest.class);
    }

    public ArchiveComponentCreationTest() {
        super();
    }

    public ArchiveComponentCreationTest(String name) {
        super(name);
    }

    
    public void testCreateWebComponent() throws Exception {
        createWebComponent(24, "TestWeb" ); //$NON-NLS-1$
    }
  
    
    private void createWebComponent(int j2eeVersion, String aModuleName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
        model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, aModuleName);
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel webModel = map.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);
        webModel.setIntProperty(IFacetDataModelProperties.FACET_VERSION,j2eeVersion);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
        
        IProject project = ProjectUtilities.getProject(aModuleName);
        if( project.exists()){
        	IVirtualComponent component = ComponentCore.createComponent(project);
        	createArchiveComponent(component);
        }	
    }

	public void createArchiveComponent(IVirtualComponent component){
		
		IPath path = new Path("JUNIT_HOME/junit.jar"); //$NON-NLS-1$


		IPath resolvedPath = JavaCore.getResolvedVariablePath(path);

		java.io.File file = new java.io.File(resolvedPath.toOSString());
		if( file.isFile() && file.exists()){
			String type = VirtualArchiveComponent.VARARCHIVETYPE + IPath.SEPARATOR;
			
			IVirtualComponent archive = ComponentCore.createArchiveComponent( component.getProject(), type +
					path.toString());
			
			ArrayList vlist = new ArrayList();
			IVirtualReference[] oldrefs = component.getReferences();
			for (int j = 0; j < oldrefs.length; j++) {
				IVirtualReference ref = oldrefs[j];
				vlist.add(ref);
			}		
		
			IVirtualReference ref = ComponentCore.createReference( component, archive, new Path("/WEB-INF/lib") ); //$NON-NLS-1$
			vlist.add(ref);	
			
			IVirtualReference[] refs = new IVirtualReference[vlist.size()];
			for (int j = 0; j < vlist.size(); j++) {
				IVirtualReference tmpref = (IVirtualReference) vlist.get(j);
				refs[j] = tmpref;
			}				
			component.setReferences(refs);
		}
	}	    
 }
