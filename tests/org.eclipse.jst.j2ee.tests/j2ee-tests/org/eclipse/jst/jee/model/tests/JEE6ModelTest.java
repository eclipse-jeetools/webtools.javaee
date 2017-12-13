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
package org.eclipse.jst.jee.model.tests;
import junit.framework.Assert;
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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.archive.emftests.GeneralEMFPopulationTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences.Keys;
import org.eclipse.jst.j2ee.jca.project.facet.IConnectorFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.internal.util.ApplicationResourceImpl;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.jst.javaee.applicationclient.internal.util.ApplicationclientResourceImpl;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.internal.util.EjbResourceImpl;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.internal.util.JcaResourceImpl;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFragment;
import org.eclipse.jst.javaee.web.internal.util.WebResourceImpl;
import org.eclipse.jst.javaee.webfragment.internal.util.WebfragmentResourceImpl;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.webfragment.operations.WebFragmentProjectCreationOperationTest;

public class JEE6ModelTest extends GeneralEMFPopulationTest {
	
	private static final String PROJECTNAME = "TestNewModels";
	public JEE6ModelTest(String name) {
		super(name);
	}
	
	
    public static Test suite() {
        return new SimpleTestSuite(JEE6ModelTest.class);
    }
    /**
	 * @param eObject
	 */
	
    protected Object primCreateAttributeValue(EAttribute att, EObject eObject) {
        if (att.getEAttributeType() == XMLTypePackage.eINSTANCE.getQName()) 
        	return null;
        else
            return super.primCreateAttributeValue(att, eObject);
    }
    
    
	protected void setUp() throws Exception {
		super.setUp();
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		RendererFactory.getDefaultRendererFactory().setValidating(false);
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
					e.printStackTrace();
				} catch (CoreException e) {
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



//    public void testNewEJBModelPopulation() throws Exception {
//		
//		EMFAttributeFeatureGenerator.reset();
//		String newModelPathURI = "/testejbmodel.xml";
//		URI uri = URI.createPlatformResourceURI("/" + getProject().getName() + newModelPathURI,false);
//		ResourceSet resSet = getResourceSet();
//		registerFactory(uri, resSet, new EjbResourceFactoryImpl());
//
//		EjbResourceImpl ejbRes = (EjbResourceImpl) resSet.createResource(uri);
//		
//		setVersion(J2EEVersionConstants.JEE_5_0_ID);
//		
//		EJBJarDeploymentDescriptor DDroot = EjbFactory.eINSTANCE.createEJBJarDeploymentDescriptor();
//		EJBJar jar = EjbFactory.eINSTANCE.createEJBJar();
//		ejbRes.getContents().add((EObjectImpl)DDroot);
//		DDroot.setEjbJar(jar);
//		populateRoot((EObjectImpl)jar);
//		
//		ejbRes.save(null);
//		
//
//	}
    
//    public void testReadNewWebModel() throws Exception {
//		
//    	String projName = "TestEE5WebProject";//$NON-NLS-1$
//    	createWebProject(projName);
//    	
//    	
//    	EMFAttributeFeatureGenerator.reset();
//		String modelPathURI = J2EEConstants.WEBAPP_DD_URI;
//		URI uri = URI.createURI("web333/" + modelPathURI);
//		ProjectResourceSet resSet = getResourceSet(projName);
//		
//
//		WebResourceImpl webRes = (WebResourceImpl) resSet.getResource(uri,true);
//		
//		if (webRes.getContents().size() > 0) {
//			WebAppDeploymentDescriptor ddRoot = (WebAppDeploymentDescriptor)webRes.getContents().get(0);
//			Assert.assertTrue(ddRoot.getWebApp() != null);
//		}
//	
//		
//
//	}
public void testEJBModel() throws Exception {
		
	String projName = "TestEE6EjbProject";//$NON-NLS-1$
	createEjbProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.EJBJAR_DD_URI;
	URI uri = URI.createURI(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER) + "/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	
	EjbResourceImpl ejbRes = (EjbResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(ejbRes.getContents().size() > 0);
	
	if (ejbRes.getContents().size() > 0) {
		EJBJar jar = ejbRes.getEjbJar();
		populateRoot((EObjectImpl)jar);
		ejbRes.save(null);
	}
	ejbRes.unload();
	// OK now load again using a new Resource
	ejbRes = (EjbResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(ejbRes.getContents().size() > 0);
	
	if (ejbRes.getContents().size() > 0) {
		EJBJar jar = ejbRes.getEjbJar();
		jar.getDescriptions();
	}
	

}
public void testAppClientModel() throws Exception {
	
	String projName = "TestEE6AppClientProject";//$NON-NLS-1$
	createAppClientProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.APP_CLIENT_DD_URI;
	URI uri = URI.createURI(CreationConstants.DEFAULT_APPCLIENT_SOURCE_FOLDER + "/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	ApplicationclientResourceImpl appClientRes = (ApplicationclientResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(appClientRes.getContents().size() > 0);
	
	if (appClientRes.getContents().size() > 0) {
		ApplicationClient client = appClientRes.getApplicationClient();
		populateRoot((EObjectImpl)client);
		appClientRes.save(null);
	}

}
public void testEarModel() throws Exception {
	
	String projName = "TestEE6EarProject";//$NON-NLS-1$
	createEarProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.APPLICATION_DD_URI;
	URI uri = URI.createURI(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APPLICATION_CONTENT_FOLDER) + "/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	ApplicationResourceImpl earRes = (ApplicationResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(earRes.getContents().size() > 0);
	
	if (earRes.getContents().size() > 0) {
		Application ear = earRes.getApplication();
		populateRoot((EObjectImpl)ear);
		earRes.save(null);
	}

}
public void testConnectorModel() throws Exception {
	
	String projName = "TestEE6ConnectorProject";//$NON-NLS-1$
	createConnectorProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.RAR_DD_URI;
	URI uri = URI.createURI(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.JCA_CONTENT_FOLDER) + "/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	JcaResourceImpl connectorRes = (JcaResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(connectorRes.getContents().size() > 0);
	
	if (connectorRes.getContents().size() > 0) {
		Connector connector = connectorRes.getConnector();
		populateRoot((EObjectImpl)connector);
		connectorRes.save(null);
	}

}
public void testWarModel() throws Exception {
	
	String projName = "TestEE6WarProject";//$NON-NLS-1$
	createWebProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.WEBAPP_DD_URI;
	URI uri = URI.createURI(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.WEB_CONTENT_FOLDER) + "/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	WebResourceImpl webRes = (WebResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(webRes.getContents().size() > 0);
	
	if (webRes.getContents().size() > 0) {
		WebApp ear = webRes.getWebApp();
		populateRoot((EObjectImpl)ear);
		webRes.save(null);
	}

}

public void testWebFragmentModel() throws Exception {
	
	String projName = "TestEE6WebFragmentProject";//$NON-NLS-1$
	createWebfragmentProject(projName);
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.WEBFRAGMENT_DD_URI;
	URI uri = URI.createURI( "src/" + modelPathURI);
	ProjectResourceSet resSet = getResourceSet(projName);
	
	WebfragmentResourceImpl webRes = (WebfragmentResourceImpl) resSet.getResource(uri,true);
	Assert.assertTrue(webRes.getContents().size() > 0);
	
	if (webRes.getContents().size() > 0) {
		WebFragment fragment = webRes.getWebFragment();
		populateRoot((EObjectImpl)fragment);
		webRes.save(null);
	}

}

	private ProjectResourceSet getResourceSet(String projName) {
		IProject proj = getProject(projName);
		return (ProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
	}


	private IProject createWebProject(String projName) throws ExecutionException {
		
			String webVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.WEB_3_0_ID);
			IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IWebFacetInstallDataModelProperties.DYNAMIC_WEB);
			IProjectFacetVersion webFacetVersion = webFacet.getVersion(webVersionString);
			IDataModel dataModel = WebProjectCreationOperationTest.getWebDataModel(projName, null, null, null, null,
					webFacetVersion, true);
			dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
			IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
			return webProj;
		
	}
	private IProject createWebfragmentProject(String projName) throws ExecutionException {
		
		String webFragmentVersionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.WEBFRAGMENT_3_0_ID);
		IProjectFacet javaFacet = ProjectFacetsManager.getProjectFacet(IJ2EEFacetConstants.JAVA);
		IDataModel javaFacetModel = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		javaFacetModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projName);
    	javaFacetModel.setProperty(IFacetDataModelProperties.FACET_VERSION, JavaEEFacetConstants.JAVA_6);
		javaFacetModel.setProperty(IJavaFacetInstallDataModelProperties.DEFAULT_OUTPUT_FOLDER_NAME,
				J2EEPlugin.getDefault().getJ2EEPreferences().getString(Keys.DYN_WEB_OUTPUT_FOLDER) );
		javaFacetModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		IProjectFacet webFragmentFacet = ProjectFacetsManager.getProjectFacet(IJ2EEFacetConstants.WEBFRAGMENT);
		IProjectFacetVersion webFragmentFacetVersion = webFragmentFacet.getVersion(webFragmentVersionString);
		IDataModel dataModel = WebFragmentProjectCreationOperationTest.getWebFragmentDataModel(projName, null, null, webFragmentFacetVersion, true);
		dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		IProject webFragmentProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webFragmentProj;	
	}
	private IProject createEjbProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_3_1_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IEjbFacetInstallDataModelProperties.EJB);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.EJB);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.remove(IModuleConstants.JST_JAVA);
		map.add(setupJavaInstallAction(projName,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER)));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject ejbProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return ejbProj;
	}
	private IProject createEarProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEarFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_6_0_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject earProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return earProj;
	}
	private IProject createAppClientProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_6_0_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.APPLICATION_CLIENT);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.remove(IModuleConstants.JST_JAVA);
		map.add(setupJavaInstallAction(projName,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APP_CLIENT_CONTENT_FOLDER)));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}
	private IProject createConnectorProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IConnectorFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JCA_1_6_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IConnectorFacetInstallDataModelProperties.JCA);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.JCA);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.remove(IModuleConstants.JST_JAVA);
		map.add(setupJavaInstallAction(projName,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.JCA_CONTENT_FOLDER)));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject connectorProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return connectorProj;
	}
	protected IDataModel setupJavaInstallAction(String aProjectName, String srcFolder) {
		IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProjectName);
		String jVersion = "1.6";
		dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, jVersion);
		dm.setStringProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, srcFolder); //$NON-NLS-1$
		return dm;
	}
    private void addWebProjectProperties(IDataModel dataModel, String projName, IProjectFacetVersion web25){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel webmodel = (IDataModel) map.get(IWebFacetInstallDataModelProperties.DYNAMIC_WEB);
		webmodel.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, web25);
//		webmodel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER,"web333"); //$NON-NLS-1$
//        webmodel.setStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER, "src444");
        webmodel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);
    }
    
    private void addVersionProperties(IDataModel dataModel, String projName, IProjectFacetVersion fv, String facetString){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel model = (IDataModel) map.get(facetString);
		model.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);
		model.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, fv);
		
    }


//	public void testNewEARModelPopulation() throws Exception {
//		
//		EMFAttributeFeatureGenerator.reset();
//		
//		String newModelPathURI = "/testearmodel.xml";
//		URI uri = URI.createPlatformResourceURI("/" + getProject().getName() + newModelPathURI,false);
//		ResourceSet resSet = getResourceSet();
//		registerFactory(uri, resSet, new ApplicationResourceFactoryImpl());
//	
//		ApplicationResourceImpl ejbRes = (ApplicationResourceImpl) resSet.createResource(uri);
//		
//		setVersion(J2EEVersionConstants.JEE_5_0_ID);
//		
//		ApplicationDeploymentDescriptor DDroot = ApplicationFactory.eINSTANCE.createApplicationDeploymentDescriptor();
//		Application ear = ApplicationFactory.eINSTANCE.createApplication();
//		ejbRes.getContents().add((EObjectImpl)DDroot);
//		DDroot.setApplication(ear);
//		populateRoot((EObjectImpl)ear);
//		
//		ejbRes.save(null);
//		
//	
//	}
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
