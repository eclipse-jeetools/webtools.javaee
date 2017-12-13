/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WebProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
    @Override
    public void verify(IDataModel model) throws Exception {
    	super.verify(model);
    	
    	this.verifyContextRoot();
    	this.verifyContentDir();
    	this.verifyJavaSrcDir();
    }
    
    @Override
    protected void setFacetProjectType() {
    	this.facetProjectType = J2EEProjectUtilities.DYNAMIC_WEB;
    }
    
    @Override
    protected IFile getDDFile() {
    	return component.getRootFolder().getFile(J2EEConstants.WEBAPP_DD_URI).getUnderlyingFile();
    }
    
    @Override
    protected void verifyDD(Object modelObj) {
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(project);
		if(version.equals(J2EEVersionConstants.VERSION_2_5_TEXT) || version.equals(J2EEVersionConstants.VERSION_3_0_TEXT) || version.equals(J2EEVersionConstants.VERSION_3_1_TEXT)){
			WebApp web = (WebApp)modelObj;
			Assert.assertEquals("Invalid project version", version, web.getVersion().getLiteral());
		} else {
			org.eclipse.jst.j2ee.webapplication.WebApp web = (org.eclipse.jst.j2ee.webapplication.WebApp)modelObj;
			Assert.assertEquals("Invalid project version", version, web.getVersion());
		}
    }
    
    private void verifyContextRoot() {
    	FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
    	IDataModel facetModel = facetMap.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);

    	
    	boolean addToEAR = model.getBooleanProperty(IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR);
    	
		//context root need only be tested if project added to EAR
		if(addToEAR) {
	    	String expectedContextRoot = facetModel.getStringProperty(IWebFacetInstallDataModelProperties.CONTEXT_ROOT);
			String earName = model.getStringProperty(IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME);
			IProject ear = ProjectUtilities.getProject(earName);
			
	    	IVirtualComponent earComponent = ComponentCore.createComponent(ear);
	    	IVirtualReference projRef = J2EEProjectUtilities.getComponentReference(earComponent, project.getName());
	    	IVirtualComponent projComponentFromEARRef = projRef.getReferencedComponent();
	    	IProject projFromEARRef = projComponentFromEARRef.getProject();
	    	Assert.assertTrue("EAR reference to the project should be identical to the project", project == projFromEARRef);
	    	
	    	//TODO this check should be replaced with the proper API defined in https://bugs.eclipse.org/bugs/show_bug.cgi?id=199920
	    	IModelProvider earProvider = ModelProviderManager.getModelProvider(ear);
	    	System.err.println("TODO -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=199920");
	    	IVirtualFolder rootFolder = earComponent.getRootFolder();
	    	IPath path = new Path(J2EEConstants.APPLICATION_DD_URI);
	    	IVirtualFile vFile = rootFolder.getFile(path);
	    	if(vFile.exists()){
		    	Object earModelObj = earProvider.getModelObject();
		    	//this test can only be performed if EAR had DD
		    	if(earModelObj != null) {
		    		String actualContextRoot = null;
			    	String projArchiveName = projRef.getArchiveName();
			    	String version = J2EEProjectUtilities.getJ2EEProjectVersion(project);
					if(version.equals(J2EEVersionConstants.VERSION_2_5_TEXT)){
						Application earApp = (Application)earModelObj;
						Module projMod = earApp.getFirstModule(projArchiveName);
						actualContextRoot = projMod.getWeb().getContextRoot();
					} else {
						org.eclipse.jst.j2ee.application.Application earApp = (org.eclipse.jst.j2ee.application.Application)earModelObj;
						org.eclipse.jst.j2ee.application.Module projMod = earApp.getFirstModule(projArchiveName);
						WebModule webModule = (WebModule)projMod;
						actualContextRoot = webModule.getContextRoot();
					}
					Assert.assertEquals("EAR should have module with context root: " + expectedContextRoot, expectedContextRoot, actualContextRoot);
		    	}
	    	}
		}
    }
    
    private void verifyContentDir() {
    	FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
    	IDataModel facetModel = facetMap.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);
		String contentDir = facetModel.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER);
		IPath contentDirPath = new Path(contentDir);
		Assert.assertTrue("Content directory should exist", project.exists(contentDirPath));
    }
    
    private void verifyJavaSrcDir() {
    	FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
    	IDataModel facetModel = facetMap.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);
		String javaSrcDir = facetModel.getStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER);
		IPath javaSrcDirPath = new Path(javaSrcDir);
		Assert.assertTrue("Java source directory should exist", project.exists(javaSrcDirPath));
    }
}
