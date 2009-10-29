/* Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tets.appclient.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AppClientProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {

	
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
		
		this.verifyMainClassField();
	}
	
	@Override
	protected void setFacetProjectType() {
		this.facetProjectType = J2EEProjectUtilities.APPLICATION_CLIENT;
	}
	
	@Override
	protected IFile getDDFile() {
		return component.getRootFolder().getFile(J2EEConstants.APP_CLIENT_DD_URI).getUnderlyingFile();
	}
	
	@Override
	protected void verifyDD(Object modelObj) {
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(project);
		if(version.equals(J2EEVersionConstants.VERSION_5_0_TEXT)){
			ApplicationClient client = (ApplicationClient)modelObj;
			Assert.assertEquals("Invalid project version", J2EEVersionConstants.VERSION_5_TEXT, client.getVersion());
		} else if(version.equals(J2EEVersionConstants.VERSION_6_0_TEXT)){
			ApplicationClient client = (ApplicationClient)modelObj;
			Assert.assertEquals("Invalid project version", J2EEVersionConstants.VERSION_6_TEXT, client.getVersion());
		} else {
			org.eclipse.jst.j2ee.client.ApplicationClient client = (org.eclipse.jst.j2ee.client.ApplicationClient)modelObj;
			Assert.assertEquals("Invalid project version", version, client.getVersion());
		}
	}
	
	private void verifyMainClassField() {
    	FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetModel = facetMap.getFacetDataModel(J2EEProjectUtilities.APPLICATION_CLIENT);
        boolean createDefaultClass = facetModel.getBooleanProperty(IAppClientFacetInstallDataModelProperties.CREATE_DEFAULT_MAIN_CLASS);
        
        if(createDefaultClass) {
        	String projName = model.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
        	IProject proj = ProjectUtilities.getProject(projName);
        	IVirtualComponent appClient = ComponentUtilities.getComponent(projName);
        	IVirtualFolder appClientRootFolder = appClient.getRootFolder();
        	IVirtualFile vertMainClass = appClientRootFolder.getFile("Main.java");
        	Assert.assertTrue("Default main class Main.java should exist", vertMainClass.exists());
        }
	}
}
