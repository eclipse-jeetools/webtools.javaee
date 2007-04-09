package org.eclipse.jst.jee.model.tests;
import junit.framework.Test;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.archive.emftests.GeneralEMFPopulationTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor;
import org.eclipse.jst.javaee.application.internal.metadata.ApplicationFactory;
import org.eclipse.jst.javaee.application.internal.util.ApplicationResourceFactoryImpl;
import org.eclipse.jst.javaee.application.internal.util.ApplicationResourceImpl;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor;
import org.eclipse.jst.javaee.ejb.internal.metadata.EjbFactory;
import org.eclipse.jst.javaee.ejb.internal.util.EjbResourceFactoryImpl;
import org.eclipse.jst.javaee.ejb.internal.util.EjbResourceImpl;
import org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor;
import org.eclipse.jst.javaee.web.internal.util.WebResourceImpl;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class JEE5ModelTest extends GeneralEMFPopulationTest {
	
	private static final String PROJECTNAME = "TestNewModels";
	public JEE5ModelTest(String name) {
		super(name);
	}
	
	
    public static Test suite() {
        return new SimpleTestSuite(JEE5ModelTest.class);
    }
    
    
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		if(workspace.getRoot().getProject(PROJECTNAME).isAccessible()) return;
        final IProjectDescription description = workspace
                .newProjectDescription(PROJECTNAME);
        description.setLocation(null);


        // create the new project operation
        IHeadlessRunnableWithProgress op = new IHeadlessRunnableWithProgress() {
            public void run(IProgressMonitor monitor)
           {
                try {
					createProject(description, workspace.getRoot().getProject(PROJECTNAME), monitor);
				} catch (OperationCanceledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        };
        

        // run the new project creation operation
        try {
        	op.run(new NullProgressMonitor());
        } catch (InterruptedException e) {
            return;
        }
	}
    private void createProject(IProjectDescription description,
            IProject projectHandle, IProgressMonitor monitor)
            throws CoreException, OperationCanceledException {
        try {
            monitor.beginTask("", 2000); //$NON-NLS-1$

            projectHandle.create(description, new SubProgressMonitor(monitor,
                    1000));

            if (monitor.isCanceled())
                throw new OperationCanceledException();

            projectHandle.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(monitor, 1000));

        } finally {
            monitor.done();
        }
    }



    public void testNewEJBModelPopulation() throws Exception {
		
		EMFAttributeFeatureGenerator.reset();
		String newModelPathURI = "/testejbmodel.xml";
		URI uri = URI.createPlatformResourceURI("/" + getProject().getName() + newModelPathURI,false);
		ResourceSet resSet = getResourceSet();
		registerFactory(uri, resSet, new EjbResourceFactoryImpl());

		EjbResourceImpl ejbRes = (EjbResourceImpl) resSet.createResource(uri);
		
		setVersion(J2EEVersionConstants.JEE_5_0_ID);
		
		EJBJarDeploymentDescriptor DDroot = EjbFactory.eINSTANCE.createEJBJarDeploymentDescriptor();
		EJBJar jar = EjbFactory.eINSTANCE.createEJBJar();
		ejbRes.getContents().add((EObjectImpl)DDroot);
		DDroot.setEjbJar(jar);
		populateRoot((EObjectImpl)jar);
		
		ejbRes.save(null);
		

	}
    
    public void testReadNewWebModel() throws Exception {
		
    	String projName = "TestEE5WebProject";//$NON-NLS-1$
    	createWebProject(projName);
    	
    	
    	EMFAttributeFeatureGenerator.reset();
		String modelPathURI = J2EEConstants.WEBAPP_DD_URI;
		URI uri = URI.createURI(modelPathURI);
		ProjectResourceSet resSet = getResourceSet(projName);
		

		WebResourceImpl webRes = (WebResourceImpl) resSet.getResource(uri,true);
		
		if (webRes.getContents().size() > 0) {
			WebAppDeploymentDescriptor ddRoot = (WebAppDeploymentDescriptor)webRes.getContents().get(0);
			ddRoot.getWebApp();
		}
	
		

	}


	private ProjectResourceSet getResourceSet(String projName) {
		IProject proj = getProject(projName);
		return (ProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
	}


	private IProject createWebProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IWebFacetInstallDataModelProperties.class);
		String webVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.WEB_2_5_ID);
		IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IWebFacetInstallDataModelProperties.DYNAMIC_WEB);
		IProjectFacetVersion webFacetVersion = webFacet.getVersion(webVersionString); //$NON-NLS-1$
		addWebProjectProperties(dataModel, projName, webFacetVersion);
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}
    private void addWebProjectProperties(IDataModel dataModel, String projName, IProjectFacetVersion web25){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel webmodel = (IDataModel) map.get(IWebFacetInstallDataModelProperties.DYNAMIC_WEB);
		webmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, web25);
		webmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"web333"); //$NON-NLS-1$
        webmodel.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, "src444");
    }


	public void testNewEARModelPopulation() throws Exception {
		
		EMFAttributeFeatureGenerator.reset();
		
		String newModelPathURI = "/testearmodel.xml";
		URI uri = URI.createPlatformResourceURI("/" + getProject().getName() + newModelPathURI,false);
		ResourceSet resSet = getResourceSet();
		registerFactory(uri, resSet, new ApplicationResourceFactoryImpl());
	
		ApplicationResourceImpl ejbRes = (ApplicationResourceImpl) resSet.createResource(uri);
		
		setVersion(J2EEVersionConstants.JEE_5_0_ID);
		
		ApplicationDeploymentDescriptor DDroot = ApplicationFactory.eINSTANCE.createApplicationDeploymentDescriptor();
		Application ear = ApplicationFactory.eINSTANCE.createApplication();
		ejbRes.getContents().add((EObjectImpl)DDroot);
		DDroot.setApplication(ear);
		populateRoot((EObjectImpl)ear);
		
		ejbRes.save(null);
		
	
	}
	public IProject getProject() {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECTNAME);
	}
	public IProject getProject(String projName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
	}


	private void registerFactory(URI uri, ResourceSet resSet, Resource.Factory factory) {
		WTPResourceFactoryRegistry registry = (WTPResourceFactoryRegistry) resSet.getResourceFactoryRegistry();
		registry.registerLastFileSegment(uri.lastSegment(), factory);
	}
	private ResourceSet getResourceSet() {
		ResourceSet set = new ResourceSetImpl();
		set.setResourceFactoryRegistry(WTPResourceFactoryRegistry.INSTANCE);
		return set;
	}



	
	protected void tearDown() throws Exception {
		// Don't delete these files
	}

	

}
