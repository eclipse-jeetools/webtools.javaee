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
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.archive.emftests.GeneralEMFPopulationTest;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.web.WebApp;
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

public class ModelProviderTest extends GeneralEMFPopulationTest {
	
	private static final String PROJECTNAME = "TestModelProviders";
	private final String descText = "Testing setting the desc";
	public ModelProviderTest(String name) {
		super(name);
	}

    public static Test suite() {
        return new SimpleTestSuite(ModelProviderTest.class);
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

	public void testUseEar5Model() throws Exception {

		String projName = "TestEE5EarProject";//$NON-NLS-1$
		IProject earProj = createEarProject(projName, J2EEVersionConstants.JEE_5_0_ID);
		
		final IModelProvider provider = ModelProviderManager.getModelProvider(earProj);
		
	
		provider.modify(new Runnable() {
			public void run() {
				Application ear = (Application)provider.getModelObject();
				if (ear.getDescriptions().isEmpty())
					ear.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
				Description desc = (Description)ear.getDescriptions().get(0);
				desc.setValue(descText);
			}
		}
			, null);
		
		//Close project to force flush
		earProj.close(new NullProgressMonitor());
		//Re-open project
		earProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(earProj);
		Application sameEar = (Application)newProvider.getModelObject();
		Description desc = (Description)sameEar.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());
		
	}
	public void testUseEar5NoDDModel() throws Exception {

		String projName = "TestEE5EarProject";//$NON-NLS-1$
		IProject earProj = createEarProject(projName, J2EEVersionConstants.JEE_5_0_ID);
		
		IFolder contentFolder= earProj.getFolder(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APPLICATION_CONTENT_FOLDER));
		IFile ddFile = contentFolder.getFile(J2EEConstants.APPLICATION_DD_URI);
		if (ddFile.exists()) {
			ddFile.delete(true, null);
		}
		
		final IModelProvider provider = ModelProviderManager.getModelProvider(earProj);
		
	
		provider.modify(new Runnable() {
			public void run() {
				Application ear = (Application)provider.getModelObject();
				if (ear.getDescriptions().isEmpty())
					ear.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
				Description desc = (Description)ear.getDescriptions().get(0);
				desc.setValue(descText);
			}
		}
			, null);
		
		//Close project to force flush
		earProj.close(new NullProgressMonitor());
		//Re-open project
		earProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(earProj);
		Application sameEar = (Application)newProvider.getModelObject();
		Description desc = (Description)sameEar.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());
		
	}

	public void testUseEar14Model() throws Exception {

		String projName = "TestEE14EarProject";//$NON-NLS-1$
		IProject earProj = createEarProject(projName, J2EEVersionConstants.J2EE_1_4_ID);
		final IModelProvider provider = ModelProviderManager.getModelProvider(earProj);
		
		// Test getting model through path api.
		org.eclipse.jst.j2ee.application.Application ear = (org.eclipse.jst.j2ee.application.Application)provider.getModelObject(new Path(J2EEConstants.APPLICATION_DD_URI));
		
		
		provider.modify(new Runnable() {
			public void run() {
				org.eclipse.jst.j2ee.application.Application ear = (org.eclipse.jst.j2ee.application.Application)provider.getModelObject();
//				if (ear.getDescriptions().isEmpty())
//					ear.getDescriptions().add(CommonFactory.eINSTANCE.createDescription());
				ear.setDescription(descText);
				
			}
		}
			, null);
		
		//Close project to force flush
		earProj.close(new NullProgressMonitor());
		//Re-open project
		earProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(earProj);
		org.eclipse.jst.j2ee.application.Application sameEar = (org.eclipse.jst.j2ee.application.Application)newProvider.getModelObject();
		org.eclipse.jst.j2ee.common.Description desc = (org.eclipse.jst.j2ee.common.Description)sameEar.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());
	}
	
	public void testUseWeb25Model() throws Exception {

		String projName = "TestEE5WebProject";//$NON-NLS-1$
		IProject webProj = createWebProject(projName, J2EEVersionConstants.WEB_2_5_ID);

		final IModelProvider provider = ModelProviderManager.getModelProvider(webProj);
		
		provider.modify(new Runnable() {
			public void run() {
				WebApp webApp = (WebApp)provider.getModelObject();
				if (webApp.getDescriptions().isEmpty())
					webApp.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
				Description desc = (Description)webApp.getDescriptions().get(0);
				desc.setValue(descText);
			}
		}
			, null);
		
		//Close project to force flush
		webProj.close(new NullProgressMonitor());
		//Re-open project
		webProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(webProj);
		WebApp sameWebApp = (WebApp)newProvider.getModelObject();
		Description desc = (Description)sameWebApp.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());

	}
	public void testUseWeb25NoDDModel() throws Exception {

		String projName = "TestEE5WebProject";//$NON-NLS-1$
		IProject webProj = createWebProject(projName, J2EEVersionConstants.WEB_2_5_ID);
		
		IFolder contentFolder= webProj.getFolder(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.WEB_CONTENT_FOLDER));
		IFile ddFile = contentFolder.getFile(J2EEConstants.WEBAPP_DD_URI);
		if (ddFile.exists()) {
			ddFile.delete(true, null);
		}

		final IModelProvider provider = ModelProviderManager.getModelProvider(webProj);
		
		provider.modify(new Runnable() {
			public void run() {
				WebApp webApp = (WebApp)provider.getModelObject();
				if (webApp.getDescriptions().isEmpty())
					webApp.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
				Description desc = (Description)webApp.getDescriptions().get(0);
				desc.setValue(descText);
			}
		}
			, null);
		
		//Close project to force flush
		webProj.close(new NullProgressMonitor());
		//Re-open project
		webProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(webProj);
		WebApp sameWebApp = (WebApp)newProvider.getModelObject();
		Description desc = (Description)sameWebApp.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());

	}

	public void testUseWeb24Model() throws Exception {
		
		String projName = "TestEE14WebProject";//$NON-NLS-1$
		IProject webProj = createWebProject(projName, J2EEVersionConstants.WEB_2_4_ID);
		final IModelProvider provider = ModelProviderManager.getModelProvider(webProj);
		
		provider.modify(new Runnable() {
			public void run() {
				org.eclipse.jst.j2ee.webapplication.WebApp webApp = (org.eclipse.jst.j2ee.webapplication.WebApp)provider.getModelObject();
//				if (webApp.getDescriptions().isEmpty())
//					webApp.getDescriptions().add(CommonFactory.eINSTANCE.createDescription());
//				org.eclipse.jst.j2ee.common.Description desc = (org.eclipse.jst.j2ee.common.Description)webApp.getDescriptions().get(0);
//				desc.setValue(descText);
				webApp.setDescription(descText);
			}
		}
			, null);
		
		//Close project to force flush
		webProj.close(new NullProgressMonitor());
		//Re-open project
		webProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(webProj);
		org.eclipse.jst.j2ee.webapplication.WebApp sameWebApp = (org.eclipse.jst.j2ee.webapplication.WebApp)newProvider.getModelObject();
		org.eclipse.jst.j2ee.common.Description desc = (org.eclipse.jst.j2ee.common.Description)sameWebApp.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());
	
	}
	public void testUseEjb3NoDDModel() throws Exception {

		String projName = "TestEE5EjbProject";//$NON-NLS-1$
		IProject ejbProj = createEjbProject(projName, J2EEVersionConstants.EJB_3_0_ID);

		IFolder ejbFolder= ejbProj.getFolder(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER));
		IFile ejbJarXmlFile = ejbFolder.getFile(J2EEConstants.EJBJAR_DD_URI);
		if (ejbJarXmlFile.exists()) {
			ejbJarXmlFile.delete(true, null);
		}
		
		final IModelProvider provider = ModelProviderManager.getModelProvider(ejbProj);
		
		provider.modify(new Runnable() {
			public void run() {
				EJBJar ejbJar = (EJBJar)provider.getModelObject();
				if (ejbJar.getDescriptions().isEmpty())
					ejbJar.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
				Description desc = (Description)ejbJar.getDescriptions().get(0);
				desc.setValue(descText);
			}
		}
			, null);
		
		//Close project to force flush
		ejbProj.close(new NullProgressMonitor());
		//Re-open project
		ejbProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(ejbProj);
		EJBJar sameEjbJar = (EJBJar)newProvider.getModelObject();
		Description desc = (Description)sameEjbJar.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());

	}
	public void testUseEjb3Model() throws Exception {

		String projName = "TestEE5EjbProject";//$NON-NLS-1$
		IProject ejbProj = createEjbProject(projName, J2EEVersionConstants.EJB_3_0_ID);

		final IModelProvider provider = ModelProviderManager.getModelProvider(ejbProj);
		
		provider.modify(new Runnable() {
			public void run() {
				EJBJar ejbJar = (EJBJar)provider.getModelObject();
				if (ejbJar.getDescriptions().isEmpty())
					ejbJar.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
				Description desc = (Description)ejbJar.getDescriptions().get(0);
				desc.setValue(descText);
			}
		}
			, null);
		
		//Close project to force flush
		ejbProj.close(new NullProgressMonitor());
		//Re-open project
		ejbProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(ejbProj);
		EJBJar sameEjbJar = (EJBJar)newProvider.getModelObject();
		Description desc = (Description)sameEjbJar.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());

	}

	public void testUseEjb21Model() throws Exception {
		
		String projName = "TestEE14EjbProject";//$NON-NLS-1$
		IProject ejbProj = createEjbProject(projName, J2EEVersionConstants.EJB_2_1_ID);
		final IModelProvider provider = ModelProviderManager.getModelProvider(ejbProj);
		
		provider.modify(new Runnable() {
			public void run() {
				org.eclipse.jst.j2ee.ejb.EJBJar ejbJar = (org.eclipse.jst.j2ee.ejb.EJBJar)provider.getModelObject();
//				if (ejbJar.getDescriptions().isEmpty())
//					ejbJar.getDescriptions().add(CommonFactory.eINSTANCE.createDescription());
//				org.eclipse.jst.j2ee.common.Description desc = (org.eclipse.jst.j2ee.common.Description)ejbJar.getDescriptions().get(0);
//				desc.setValue(descText);
				ejbJar.setDescription(descText);
			}
		}
			, null);
		
		//Close project to force flush
		ejbProj.close(new NullProgressMonitor());
		//Re-open project
		ejbProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(ejbProj);
		org.eclipse.jst.j2ee.ejb.EJBJar sameEjbJar = (org.eclipse.jst.j2ee.ejb.EJBJar)newProvider.getModelObject();
		org.eclipse.jst.j2ee.common.Description desc = (org.eclipse.jst.j2ee.common.Description)sameEjbJar.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());
	
	}
	
	private ProjectResourceSet getResourceSet(String projName) {
		IProject proj = getProject(projName);
		return (ProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
	}

	private IProject createWebProject(String projName, int vers) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IWebFacetInstallDataModelProperties.class);
		String webVersionString = J2EEVersionUtil.convertVersionIntToString(vers);
		IProjectFacet webFacet = ProjectFacetsManager.getProjectFacet(IWebFacetInstallDataModelProperties.DYNAMIC_WEB);
		IProjectFacetVersion webFacetVersion = webFacet.getVersion(webVersionString); //$NON-NLS-1$
		addWebProjectProperties(dataModel, projName, webFacetVersion);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(setupJavaInstallAction(projName,CommonFrameworksPlugin.getDefault().getPluginPreferences().getString(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER)));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}

	private IProject createEjbProject(String projName, int vers) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(vers);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IEjbFacetInstallDataModelProperties.EJB);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.EJB);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(setupJavaInstallAction(projName,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER)));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}

	private IProject createEarProject(String projName, int vers) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEarFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(vers);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}

	private IProject createAppClientProject(String projName , int vers) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IAppClientFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(vers);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IAppClientFacetInstallDataModelProperties.APPLICATION_CLIENT);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.APPLICATION_CLIENT);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(setupJavaInstallAction(projName,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APP_CLIENT_CONTENT_FOLDER)));
		dataModel.getDefaultOperation().execute( new NullProgressMonitor(), null);
		IProject webProj = ResourcesPlugin.getWorkspace().getRoot().getProject(projName);
		return webProj;
	}

	protected IDataModel setupJavaInstallAction(String aProjectName, String srcFolder) {
		IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProjectName);
		String jVersion = "5.0";
		dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, jVersion); //$NON-NLS-1$
		dm.setStringProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, srcFolder); //$NON-NLS-1$
		return dm;
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

    private void addVersionProperties(IDataModel dataModel, String projName, IProjectFacetVersion fv, String facetString){

		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
		FacetDataModelMap map = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel model = (IDataModel) map.get(facetString);
		model.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);
		model.setProperty(IFacetInstallDataModelProperties.FACET_VERSION, fv);
		
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

	public void testUseAppClient14Model() throws Exception {
	
			String projName = "TestEE14AppClientProject";//$NON-NLS-1$
			IProject appClientProj = createAppClientProject(projName, J2EEVersionConstants.J2EE_1_4_ID);
			final IModelProvider provider = ModelProviderManager.getModelProvider(appClientProj);
			
			// Test getting model through path api.
			ApplicationClient client = (ApplicationClient)provider.getModelObject(new Path(J2EEConstants.APP_CLIENT_DD_URI));
			
			
			provider.modify(new Runnable() {
				public void run() {
					ApplicationClient client = (ApplicationClient)provider.getModelObject();
	//				if (ear.getDescriptions().isEmpty())
	//					ear.getDescriptions().add(CommonFactory.eINSTANCE.createDescription());
					client.setDescription(descText);
					
				}
			}
				, null);
			
			//Close project to force flush
			appClientProj.close(new NullProgressMonitor());
			//Re-open project
			appClientProj.open(new NullProgressMonitor());
			
			
			IModelProvider newProvider = ModelProviderManager.getModelProvider(appClientProj);
			ApplicationClient sameEar = (ApplicationClient)newProvider.getModelObject();
			org.eclipse.jst.j2ee.common.Description desc = (org.eclipse.jst.j2ee.common.Description)sameEar.getDescriptions().get(0);
			Assert.assertEquals(descText, desc.getValue());
		}

	public void testUseAppClient5Model() throws Exception {
	
			String projName = "TestEE5AppClientProject";//$NON-NLS-1$
			IProject appClientProj = createAppClientProject(projName, J2EEVersionConstants.JEE_5_0_ID);
			final IModelProvider provider = ModelProviderManager.getModelProvider(appClientProj);
			
			// Test getting model through path api.
			org.eclipse.jst.javaee.applicationclient.ApplicationClient client = (org.eclipse.jst.javaee.applicationclient.ApplicationClient)provider.getModelObject(new Path(J2EEConstants.APP_CLIENT_DD_URI));
			
			
			provider.modify(new Runnable() {
				public void run() {
					org.eclipse.jst.javaee.applicationclient.ApplicationClient client = (org.eclipse.jst.javaee.applicationclient.ApplicationClient)provider.getModelObject();
					if (client.getDescriptions().isEmpty())
						client.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
					Description desc = (Description)client.getDescriptions().get(0);
					desc.setValue(descText);
					
				}
			}
				, null);
			
			//Close project to force flush
			appClientProj.close(new NullProgressMonitor());
			//Re-open project
			appClientProj.open(new NullProgressMonitor());
			
			
			IModelProvider newProvider = ModelProviderManager.getModelProvider(appClientProj);
			org.eclipse.jst.javaee.applicationclient.ApplicationClient sameClient = (org.eclipse.jst.javaee.applicationclient.ApplicationClient)newProvider.getModelObject();
			Description desc = (Description)sameClient.getDescriptions().get(0);
			Assert.assertEquals(descText, desc.getValue());
		}
	public void testUseAppClient5NoDDModel() throws Exception {
		
		String projName = "TestEE5AppClientProject";//$NON-NLS-1$
		IProject appClientProj = createAppClientProject(projName, J2EEVersionConstants.JEE_5_0_ID);
		
		IFolder contentFolder= appClientProj.getFolder(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APP_CLIENT_CONTENT_FOLDER));
		IFile ddFile = contentFolder.getFile(J2EEConstants.APP_CLIENT_DD_URI);
		if (ddFile.exists()) {
			ddFile.delete(true, null);
		}
		
		final IModelProvider provider = ModelProviderManager.getModelProvider(appClientProj);
		
		// Test getting model through path api.
		org.eclipse.jst.javaee.applicationclient.ApplicationClient client = (org.eclipse.jst.javaee.applicationclient.ApplicationClient)provider.getModelObject(new Path(J2EEConstants.APP_CLIENT_DD_URI));
		
		
		provider.modify(new Runnable() {
			public void run() {
				org.eclipse.jst.javaee.applicationclient.ApplicationClient client = (org.eclipse.jst.javaee.applicationclient.ApplicationClient)provider.getModelObject();
				if (client.getDescriptions().isEmpty())
					client.getDescriptions().add(JavaeeFactory.eINSTANCE.createDescription());
				Description desc = (Description)client.getDescriptions().get(0);
				desc.setValue(descText);
				
			}
		}
			, null);
		
		//Close project to force flush
		appClientProj.close(new NullProgressMonitor());
		//Re-open project
		appClientProj.open(new NullProgressMonitor());
		
		
		IModelProvider newProvider = ModelProviderManager.getModelProvider(appClientProj);
		org.eclipse.jst.javaee.applicationclient.ApplicationClient sameClient = (org.eclipse.jst.javaee.applicationclient.ApplicationClient)newProvider.getModelObject();
		Description desc = (Description)sameClient.getDescriptions().get(0);
		Assert.assertEquals(descText, desc.getValue());
	}
}
