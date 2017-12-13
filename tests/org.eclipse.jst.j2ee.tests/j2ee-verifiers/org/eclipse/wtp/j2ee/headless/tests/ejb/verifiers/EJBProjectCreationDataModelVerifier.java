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
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyVerificationUtil;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class EJBProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
		
		this.verifyClient();
	}
	
	@Override
	protected void setFacetProjectType() {
		this.facetProjectType = J2EEProjectUtilities.EJB;
	}
	
	@Override
	protected IFile getDDFile() {
		return component.getRootFolder().getFile(J2EEConstants.EJBJAR_DD_URI).getUnderlyingFile();
	}
	
	@Override
	protected void verifyDD(Object modelObj) {
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(project);
		if(version.equals(J2EEVersionConstants.VERSION_3_0_TEXT) || version.equals(J2EEVersionConstants.VERSION_3_1_TEXT) ){
			EJBJar ejb = (EJBJar)modelObj;
			Assert.assertEquals("Invalid project version", version, ejb.getVersion());
		} else {
			org.eclipse.jst.j2ee.ejb.EJBJar ejb = (org.eclipse.jst.j2ee.ejb.EJBJar)modelObj;
			Assert.assertEquals("Invalid project version", version, ejb.getVersion());
		}
	}
	
	private void verifyClient() throws Exception{
		FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetModel = facetMap.getFacetDataModel(facetProjectType);
        boolean createClient = facetModel.getBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT);
        boolean addToEAR = model.getBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR);
       
        //can only have client if added to EAR
        if(createClient && addToEAR) {
        	//be sure the client project exists
        	String clientName = facetModel.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);
    		IProject clientProject = ProjectUtilities.getProject(clientName);
    		Assert.assertTrue("Client project should exist", clientProject.exists());
    	
			String earName = model.getStringProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME);
			
			//be sure the created EAR has reference to the created EJB client
			IProject ear = ProjectUtilities.getProject(earName);
			boolean foundEARRefToClient = false;
			IVirtualReference[] refs = EarUtilities.getUtilityModuleReferences(ComponentCore.createComponent(ear));
			IVirtualComponent clientComp = ComponentCore.createComponent(clientProject);
			for (int i = 0; i < refs.length; i++) {
				IVirtualReference virtualReference = refs[i];
				if (virtualReference.getReferencedComponent().equals(clientComp))
					foundEARRefToClient = true;
			}
			Assert.assertTrue("The created EAR should have a reference to the created EJB client project", foundEARRefToClient);
			
			//be sure EJB has a MANIFEST entry to the EJB client
			String ejbName = facetModel.getStringProperty(IEjbFacetInstallDataModelProperties.FACET_PROJECT_NAME);
			String ejbClientURI = facetModel.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_URI);
			IProject ejbProject = ProjectUtilities.getProject(ejbName);
			DependencyVerificationUtil.verifyManifestReference(ejbProject, ejbClientURI, true);   
			
			
			//be sure the EJB client source folder was created
			String clientSourceFolderPath = facetModel.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER);
			IFolder clientSourceFolder = clientProject.getFolder(clientSourceFolderPath);
			Assert.assertTrue("Client source folder should exist", clientSourceFolder.exists());
        }
	}
}
