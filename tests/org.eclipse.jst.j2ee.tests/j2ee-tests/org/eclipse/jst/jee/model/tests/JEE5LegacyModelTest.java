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
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jem.util.emf.workbench.FlexibleProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.archive.emftests.GeneralEMFPopulationTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarResourceFactory;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBResourceImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class JEE5LegacyModelTest extends GeneralEMFPopulationTest {
	
	private static final String PROJECTNAME = "TESTEJB3Import";
	private static final String BASE_IMPORT_DIR = "TestData" + java.io.File.separatorChar;
	public JEE5LegacyModelTest(String name) {
		super(name);
	}
	
	
    public static Test suite() {
        return new SimpleTestSuite(JEE5LegacyModelTest.class);
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
    public static IDataModel getEJBImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose) {
    	IDataModel importModel = DataModelFactory.createDataModel(new EJBComponentImportDataModelProvider());
    	
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filePath);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, closeArchiveOnDispose);
    	
    	if(creationModel != null) {
    		importModel.setProperty(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION, creationModel);
    	}
    	
    	return importModel;
    }
    protected String getArchivePath(String archiveName) throws Exception {
		HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
		String pluginRelativeFileName = BASE_IMPORT_DIR + getTestDataDirectoryName() + java.io.File.separatorChar + archiveName;
		return ProjectUtility.getFullFileName(plugin, pluginRelativeFileName);
	}
    
	protected void setUp() throws Exception {
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
                	String archiveName = getArchivePath("TestEJB3.jar");
                	IDataModel importModel = getEJBImportDataModel(archiveName, "TESTEJB3Import", null, true);
                	IStatus operationStatus = importModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
				} catch (Exception e) {
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
    
public void testEJBModel() throws Exception {
		
	
	EMFAttributeFeatureGenerator.reset();
	String modelPathURI = J2EEConstants.EJBJAR_DD_URI;
	URI uri = URI.createURI(J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER) + "/" + modelPathURI);
	FlexibleProjectResourceSet resSet = getResourceSet("TESTEJB3Import");
	
	EJBResourceImpl ejbRes = (EJBResourceImpl) resSet.getResource(uri,true,EJBJarResourceFactory.getRegisteredFactory());
	Assert.assertTrue(ejbRes.getContents().size() > 0);
	
	
	Assert.assertTrue(ejbRes.getContents().size() > 0);
	
	if (ejbRes.getContents().size() > 0) {
		org.eclipse.jst.j2ee.ejb.EJBJar jar = ejbRes.getEJBJar();
		List<EnterpriseBean> beanslist = jar.getEnterpriseBeans();
		for (EnterpriseBean enterpriseBean : beanslist) {
			System.out.println(enterpriseBean.getName());
			System.out.println(enterpriseBean.getHomeInterfaceName());
			if (enterpriseBean instanceof Session)
				System.out.println(((Session)enterpriseBean).getServiceEndpointName());
			enterpriseBean.setDescription("Ugh");
		}
	}
	ejbRes.save(null);
	

}
public void testUsingEJBArtifactEdit() {
	EJBArtifactEdit edit = null;
	try {
		edit = EJBArtifactEdit.getEJBArtifactEditForWrite(getProject());
		EJBJar ejb = edit.getEJBJar();
		boolean pass = !ejb.getEnterpriseBeans().isEmpty();
		assertTrue(pass);
	} finally {
		if (edit != null) {
			edit.dispose();
		}
	}
}
private FlexibleProjectResourceSet getResourceSet(String projName) {
		IProject proj = getProject(projName);
		return (FlexibleProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
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


	protected String getTestDataDirectoryName() {
		return "EJBImportTests";
	}

	

}
