/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tets.ear.verifiers;

import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.JEEProjectCreationDataModelVerifier;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EARProjectCreationDataModelVerifier extends JEEProjectCreationDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
		
		this.verifyContentDir();
		this.verifyProjectDependencies();
	}
	
	@Override
	protected void setFacetProjectType() {
		this.facetProjectType = J2EEProjectUtilities.ENTERPRISE_APPLICATION;
	}
	
	@Override
	protected IFile getDDFile() {
		return component.getRootFolder().getFile(J2EEConstants.APPLICATION_DD_URI).getUnderlyingFile();
	}
	
	@Override
	protected void verifyDD(Object modelObj) {
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(project);
		if(version.equals(J2EEVersionConstants.VERSION_5_0_TEXT)){
			Application ear = (Application)modelObj;
			Assert.assertEquals("Invalid project version", J2EEVersionConstants.VERSION_5_TEXT, ear.getVersion());
		} else if(version.equals(J2EEVersionConstants.VERSION_6_0_TEXT)){
			Application ear = (Application)modelObj;
			Assert.assertEquals("Invalid project version", J2EEVersionConstants.VERSION_6_TEXT, ear.getVersion());
		} else {
			org.eclipse.jst.j2ee.application.Application ear = (org.eclipse.jst.j2ee.application.Application)modelObj;
			Assert.assertEquals("Invalid project version", version, ear.getVersion());
		}
	}
	
	private void verifyContentDir() {
		FacetDataModelMap factMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel earModel = (IDataModel) factMap.get(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		String contentDir = earModel.getStringProperty(IEarFacetInstallDataModelProperties.CONTENT_DIR);
		
		IFolder contentFolder = project.getFolder(contentDir);
		Assert.assertTrue("Content directory should exist", contentFolder.exists());
	}
	
	private void verifyProjectDependencies()throws Exception {
		FacetDataModelMap factMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel earModel = (IDataModel) factMap.get(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		
		List j2eeDependencies = (List)earModel.getProperty(IEarFacetInstallDataModelProperties.J2EE_PROJECTS_LIST);
		List javaDependencies = (List)earModel.getProperty(IEarFacetInstallDataModelProperties.JAVA_PROJECT_LIST);
		
		IProject[] referencedProjs = project.getReferencedProjects();
		
		boolean foundDependency = false;
		IProject dependencyProj = null;
		
		//be sure this EAR has a referenced project for each J2EE dependency
		if(j2eeDependencies != null) {
			for(int i = 0; i < j2eeDependencies.size(); i++) {
				dependencyProj = (IProject)j2eeDependencies.get(i);
				
				for(int j = 0; j < referencedProjs.length && !foundDependency; j++) {
					foundDependency = (dependencyProj == referencedProjs[j]);
				}
				
				Assert.assertTrue("EAR should have a referenced project " + dependencyProj.getName(), foundDependency);
			}
		}
		
		//be sure this EAR has a referenced project for each Java dependency
		if(javaDependencies != null) {
			for(int i = 0; i < javaDependencies.size(); i++) {
				dependencyProj = (IProject)javaDependencies.get(i);
				
				for(int j = 0; j < referencedProjs.length && !foundDependency; j++) {
					foundDependency = (dependencyProj == referencedProjs[j]);
				}
				
				Assert.assertTrue("EAR should have a referenced project " + dependencyProj.getName(), foundDependency);
			}
		}
	}
}
