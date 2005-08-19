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
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
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
        createWebComponent(24, "TestWeb" );
    }
  
    
    private void createWebComponent(int j2eeVersion, String aModuleName) throws Exception{
        IDataModel model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
        model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
        model.setProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
        model.setBooleanProperty(IWebComponentCreationDataModelProperties.ADD_TO_EAR, true);
        model.getDefaultOperation().execute(new NullProgressMonitor(), null);
        
        IProject project = ProjectUtilities.getProject(aModuleName);
        if( project.exists()){
        	IVirtualComponent component = ComponentCore.createComponent(project, aModuleName);
        	createArchiveComponent(component);
        }	
    }

	public void createArchiveComponent(IVirtualComponent component){
		
		IPath path = new Path("JUNIT_HOME/junit.jar");


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
		
			IVirtualReference ref = ComponentCore.createReference( component, archive, new Path("/WEB-INF/lib") );
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
