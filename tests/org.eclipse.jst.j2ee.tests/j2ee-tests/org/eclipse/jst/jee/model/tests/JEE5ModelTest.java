package org.eclipse.jst.jee.model.tests;
import junit.framework.Test;

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
import org.eclipse.jst.j2ee.archive.emftests.GeneralEMFPopulationTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
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
import org.eclipse.wst.common.componentcore.internal.impl.WTPResourceFactoryRegistry;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class JEE5ModelTest extends GeneralEMFPopulationTest {
	
	private static final String PROJECTNAME = "TestNewModels";
	public JEE5ModelTest(String name) {
		super(name);
	}
	
	
    public static Test suite() {
        return new SimpleTestSuite(JEE5ModelTest.class);
    }
    
    @Override
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


	private void registerFactory(URI uri, ResourceSet resSet, Resource.Factory factory) {
		WTPResourceFactoryRegistry registry = (WTPResourceFactoryRegistry) resSet.getResourceFactoryRegistry();
		registry.registerLastFileSegment(uri.lastSegment(), factory);
	}
	private ResourceSet getResourceSet() {
		ResourceSet set = new ResourceSetImpl();
		set.setResourceFactoryRegistry(WTPResourceFactoryRegistry.INSTANCE);
		return set;
	}



	@Override
	protected void tearDown() throws Exception {
		// Don't delete these files
	}

	

}
