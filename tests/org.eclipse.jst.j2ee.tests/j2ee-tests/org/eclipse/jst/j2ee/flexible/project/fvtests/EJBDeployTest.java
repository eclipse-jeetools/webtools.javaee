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
package org.eclipse.jst.j2ee.flexible.project.fvtests;
import junit.framework.Test;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.archive.emftests.GeneralEMFPopulationTest;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wst.server.core.model.IModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;

public class EJBDeployTest extends GeneralEMFPopulationTest {
	
	private static final String PROJECTNAME = "TestNewModels";
	public static final String PACKAGE = "test"; //$NON-NLS-1$
    public static final String SESSION_BEAN_NAME = "TestSessionBean"; //$NON-NLS-1$
	public EJBDeployTest(String name) {
		super(name);
	}
	
	
    public static Test suite() {
        return new SimpleTestSuite(EJBDeployTest.class);
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

            projectHandle.open(new SubProgressMonitor(monitor, 1000));

        } finally {
            monitor.done();
        }
    }


public void testEJBDeployment() throws Exception {
		
	String projName = "TestEE5EjbProject";//$NON-NLS-1$
	createEjbProject(projName);
	
	IProject project = getProject(projName);
	// Create Session bean
	createSessionBean(projName);
	
	
	boolean found = false;
	IVirtualComponent component = ComponentCore.createComponent(project);
	J2EEFlexProjDeployable deployable = new J2EEFlexProjDeployable(project, component);
	try {
		if (deployable.isSingleRootStructure()) {
			IModuleResource[] members = deployable.members();
			for (int i=0; i<members.length; i++) {
				IModuleResource packageFolder = members[i];
				String name = packageFolder.getName();
				if (name.equals(PACKAGE)) {
					IModuleResource[] javaMembers = ((IModuleFolder)packageFolder).members();
					for (int j = 0; j < javaMembers.length; j++) {
						IModuleResource javaResource = javaMembers[j];
						if (javaMembers[j].getName().equals("TestSessionBean.class"))
							found = true;
					}
				}
			}
		}
		assertTrue("Can't find class files in ejb deployment",found);

	}catch (CoreException e) {
		fail(e.getMessage());
	}
	
	
	

}


private void createSessionBean(String projName) throws ExecutionException {
	IDataModel dm = DataModelFactory.createDataModel(NewSessionBeanClassDataModelProvider.class);
	dm.setProperty(INewJavaClassDataModelProperties.PROJECT_NAME, projName);
	dm.setProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE, PACKAGE);
	dm.setProperty(INewJavaClassDataModelProperties.CLASS_NAME, SESSION_BEAN_NAME);
	IStatus operationStatus = dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
	try {
		Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD,null);
	} catch (OperationCanceledException e) {
		e.printStackTrace();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
private ProjectResourceSet getResourceSet(String projName) {
		IProject proj = getProject(projName);
		return (ProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
	}


	private IProject createEjbProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEjbFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.EJB_3_0_ID);
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
	private IProject createEarProject(String projName) throws ExecutionException {
		IDataModel dataModel = DataModelFactory.createDataModel(IEarFacetInstallDataModelProperties.class);
		String versionString = J2EEVersionUtil.convertVersionIntToString(J2EEVersionConstants.JEE_5_0_ID);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IEarFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		IProjectFacetVersion facetVersion = facet.getVersion(versionString); //$NON-NLS-1$
		addVersionProperties(dataModel, projName, facetVersion,IJ2EEFacetInstallDataModelProperties.ENTERPRISE_APPLICATION);
		dataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, projName);
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

	

}
