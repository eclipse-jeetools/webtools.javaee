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
/*
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
public class EARProjectCreationOperationTest extends org.eclipse.wst.common.tests.OperationTestCase {
    public EARProjectCreationOperationTest(String string) {
		super(string);
	}

	public static Test suite() {
        return new SimpleTestSuite(EARProjectCreationOperationTest.class);
    }

	public void testUsingPublicAPI() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IEarFacetInstallDataModelProperties.class);

		String projName = "TestAPIEarProject";//$NON-NLS-1$
		String appVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.J2EE_1_4_ID);
		IProjectFacet earFacet = ProjectFacetsManager.getProjectFacet(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		IProjectFacetVersion earFacetVersion = earFacet.getVersion(appVersionString); //$NON-NLS-1$

		addEarProjectProperties(dataModel, projName, earFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateEarProjectProperties(projName, earFacetVersion);
		
		validateEarDescriptorProperties(projName);		
		
    }

	public void testUsingPublicAPIEar50() throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(IEarFacetInstallDataModelProperties.class);

		String projName = "TestAPIEarProject";//$NON-NLS-1$
		String appVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_5_0_ID);
		IProjectFacet earFacet = ProjectFacetsManager.getProjectFacet(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		IProjectFacetVersion earFacetVersion = earFacet.getVersion(appVersionString); //$NON-NLS-1$

		addEarProjectProperties(dataModel, projName, earFacetVersion);
		
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);

		validateEarProjectProperties(projName, earFacetVersion);
		
    }

	private void validateEarDescriptorProperties(String projName) {
			// Test if op worked
			IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
			
			// does underlying file for deployment descriptor exist
			IVirtualComponent component = ComponentCore.createComponent(proj);
			IFile deploymentDescriptorFile = component.getRootFolder().getFile(J2EEConstants.APPLICATION_DD_URI).getUnderlyingFile();
			
			Assert.assertTrue(deploymentDescriptorFile.exists());

			// is it a valid artifact
			EARArtifactEdit ear = EARArtifactEdit.getEARArtifactEditForRead(proj);
			Assert.assertNotNull(ear);
			if (ear != null)
			Assert.assertNotNull(ear.getApplication());
		}
	  
		private void validateEarProjectProperties(String projName,
				IProjectFacetVersion earFacetVersion) throws CoreException {
			// Test if op worked
			IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
			Assert.assertTrue(proj.exists());
			IVirtualComponent component = ComponentCore.createComponent(proj);
			Assert.assertNotNull(component);
			if (component != null)
			Assert.assertNotNull(component.getName());
			Assert.assertTrue(proj.exists(new Path("/ear333")));

			// Test if facet is right version
			IFacetedProject facetedProject = ProjectFacetsManager.create(proj);
			Assert.assertTrue(facetedProject.hasProjectFacet(earFacetVersion));
		}

	private void addEarProjectProperties(IDataModel dataModel, String projName, IProjectFacetVersion earFacetVersion){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel earmodel = (IDataModel) map.get(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		earmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, earFacetVersion);
		earmodel.setStringProperty(IEarFacetInstallDataModelProperties.CONTENT_DIR,"ear333"); //$NON-NLS-1$
    }

}
