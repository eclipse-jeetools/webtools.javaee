package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewMessageDrivenBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;

public class AddEJBeanOperationTest extends OperationTestCase implements
		INewJavaClassDataModelProperties {
	
    public static final String EJB_PROJECT_NAME = "EjbProject"; //$NON-NLS-1$
    
    public static final String PACKAGE = "test"; //$NON-NLS-1$
    
    public static final String SESSION_BEAN_NAME = "TestSessionBean"; //$NON-NLS-1$
    public static final String SESSION_BEAN_CLASS_NAME = PACKAGE + "." + SESSION_BEAN_NAME; //$NON-NLS-1$
    public static final String SESSION_BEAN_LOCAL_NAME = SESSION_BEAN_NAME + "Local"; //$NON-NLS-1$
    public static final String SESSION_BEAN_LOCAL_CLASS_NAME = PACKAGE + "." + SESSION_BEAN_LOCAL_NAME; //$NON-NLS-1$
    
    public static final String MESSAGE_DRIVEN_BEAN_NAME = "TestMDBean"; //$NON-NLS-1$
    public static final String MESSAGE_DRIVEN_BEAN_CLASS_NAME = PACKAGE + "." + MESSAGE_DRIVEN_BEAN_NAME; //$NON-NLS-1$
    
	public AddEJBeanOperationTest() {
		super();
	}
	
	public AddEJBeanOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
        return new TestSuite(AddEJBeanOperationTest.class);
    }
	
	public void testAddSessionBean_EJB30_Defaults() throws Exception {
    	createEJBProject(EJB_PROJECT_NAME, JavaEEFacetConstants.EJB_3);
    	IProject proj = ProjectUtilities.getProject(EJB_PROJECT_NAME);

    	addSessionBean_Defaults();

		assertJavaFileExists(SESSION_BEAN_CLASS_NAME);
		assertJavaFileExists(SESSION_BEAN_LOCAL_CLASS_NAME);
    	
    	// no EJB3 annotation model to check yet
    }
	
	public void testAddMessageDrivenBean_EJB30_Defaults() throws Exception {
    	createEJBProject(EJB_PROJECT_NAME, JavaEEFacetConstants.EJB_3);
    	IProject proj = ProjectUtilities.getProject(EJB_PROJECT_NAME);

    	addMessageDrivenBean_Defaults();

		assertJavaFileExists(MESSAGE_DRIVEN_BEAN_CLASS_NAME);
    	
    	// no EJB3 annotation model to check yet
    }
	
	private void createEJBProject(String projectName, IProjectFacetVersion version) throws Exception {
    	IDataModel dm = EJBProjectCreationOperationTest.getEJBDataModel(
				projectName, null, null, null, version, false);
    	runAndVerify(dm);
    }
	
	private void addSessionBean_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewSessionBeanClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, EJB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, SESSION_BEAN_NAME);
        runAndVerify(dm);
    }
	
	private void addMessageDrivenBean_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewMessageDrivenBeanClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, EJB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, MESSAGE_DRIVEN_BEAN_NAME);
        runAndVerify(dm);
    }
    
    private void assertJavaFileExists(String fullyQualifiedName) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(
				ResourcesPlugin.getWorkspace().getRoot())
				.getJavaModel().getJavaProject(EJB_PROJECT_NAME);
		assertNotNull(javaProject);
		IFile file = (IFile) javaProject.findType(fullyQualifiedName).getResource();
		assertNotNull(file);
		assertTrue(file.exists());
    }

}
