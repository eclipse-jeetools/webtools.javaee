/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.internal.ProjectFacetVersion;
import org.eclipse.wst.common.tests.DataModelVerifier;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class JEEProjectCreationDataModelVerifier extends DataModelVerifier {

	protected IDataModel model = null;
	protected IProject project = null;
	protected IVirtualComponent component = null;
	protected String facetProjectType = null;
	
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
		
		this.model = model;
		this.setFacetProjectType();
		
		String projName = model.getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME);
		project = ProjectUtilities.getProject(projName);
		this.verifyProject();
		
		component = ComponentCore.createComponent(project);
		this.verifyComponent();
		
		this.verifyProjectVersion();
		this.verifyModelProvider();
	}
	
	protected abstract void setFacetProjectType();
	
	protected abstract IFile getDDFile();
	
	protected abstract void verifyDD(Object modelObj);
	
	private void verifyProject(){
		Assert.assertTrue("The project should exist", project.exists());
	}
	
	private void verifyComponent() {
		Assert.assertNotNull("Component should not be null", component);
		Assert.assertTrue("Component should exist", component.exists());
	}
	
	private void verifyProjectVersion() throws Exception{
		FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetModel = facetMap.getFacetDataModel(facetProjectType);
        ProjectFacetVersion version = (ProjectFacetVersion)facetModel.getProperty(IFacetDataModelProperties.FACET_VERSION);
        
        IProjectFacet projFact = version.getProjectFacet();
        String projFactID = projFact.getId();
        boolean verifyVersion = ProjectFacetsManager.isProjectFacetDefined(projFactID);
        Assert.assertTrue("Project facet: "+projFactID+" should be defined", verifyVersion);
        
        IFacetedProject facetedProject =  ProjectFacetsManager.create(project);
        Assert.assertTrue("Project fact version: "+version+"should be defined", facetedProject.getProjectFacets().contains(version));
	}
	
	
	protected void verifyModelProvider() throws Exception {
		IModelProvider provider = ModelProviderManager.getModelProvider(project);
		Object modelObj = provider.getModelObject();
		Object modelObj2 = null;
		
		IArchive archive = null;
        try{
        	archive = JavaEEArchiveUtilities.INSTANCE.openArchive(component);
        	
        	FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
            IDataModel facetModel = facetMap.getFacetDataModel(facetProjectType);
            boolean hasDD = facetModel.getBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD);
            
            IFile deploymentDescriptorFile = this.getDDFile();
            if(hasDD != deploymentDescriptorFile.exists()){
            	Assert.fail("Deployment descriptor should exist if project is supposed to have a deployment descriptor");
            }

            if(hasDD) {
				Assert.assertNotNull("Deployment Descriptor should not be null", modelObj);
				this.verifyDD(modelObj);
				
				System.err.println("TODO -- //modelObj2 = archive.getModelObject();");
            	System.err.println("     -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=195670");
            	//modelObj2 = archive.getModelObject();
			} else {
				Assert.assertNull("Project should not have a Deployment Descriptor", modelObj);
			}
            if(modelObj != modelObj2){
            	System.err.println("TODO -- Deployment Descriptor should be equal to its self");
            	System.err.println("     -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=195670");
            	//AssertWarn.warnTrue("Deployment Descriptor should be equal to its self", modelObj == modelObj2);
            }
        } finally {
        	if(archive != null){
        		JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
        	}
        }
	}
}
