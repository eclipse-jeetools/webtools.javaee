package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.internal.operations.AddEjbTimerDataModelProvider;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewMessageDrivenBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
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
    
    public static final String ASYNCHRONOUS = "@Asynchronous"; //$NON-NLS-1$
    public static final String ASYNC_WAS_NOT_EXPECTED = "The @Asynchronous was not expected"; //$NON-NLS-1$
	public static final String ASYNC_EJB_REGEX = ".*(@Asynchronous\\s){1}(@.*\\s)*(public class){1}.*"; //$NON-NLS-1$
	public static final String ASYNC_WAS_EXPECTED = "The @Asynchronous was expected"; //$NON-NLS-1$
	public static final String EJB_WITHOUT_ASYNC = "EjbWithooutAsync"; //$NON-NLS-1$
	public static final String EJB_WITH_ASYNC = "EjbWithAsync"; //$NON-NLS-1$
	public static final String EJB_WITHOUT_ASYNC_CLASS_NAME = PACKAGE + "." + EJB_WITHOUT_ASYNC; //$NON-NLS-1$
	public static final String EJB_WITH_ASYNC_CLASS_NAME = PACKAGE + "." + EJB_WITH_ASYNC; //$NON-NLS-1$

    public static final String TIMER_PERSISTENT_CONFIG_EXPECTED = "EJB Timer persistent configuration was expected"; //$NON-NLS-1$
	public static final String TIMER_NON_PERSISTENT_CONFIG_EXPECTED = "EJB Timer Non-persistent configuration was expected"; //$NON-NLS-1$
	public static final String NON_PERSISTENT_TIMER_REGEX = "@Schedule\\s*\\(.*\\s.*persistent\\s*=\\s*false\\)";
	public static final String NON_PERSISTENT_EJB_TIMER = "NonPersistentEjbTimer"; //$NON-NLS-1$
	public static final String PERSISTENT_EJB_TIMER = "PersistentEjbTimer"; //$NON-NLS-1$
    public static final String NON_PERSISTENT_EJB_TIMER_CLASS_NAME = PACKAGE + "." + NON_PERSISTENT_EJB_TIMER; //$NON-NLS-1$
    public static final String PERSISTENT_EJB_TIMER_CLASS_NAME = PACKAGE + "." + PERSISTENT_EJB_TIMER; //$NON-NLS-1$
    
	public AddEJBeanOperationTest() {
		super();
	}
	
	public AddEJBeanOperationTest(String name) {
		super(name);
	}
	
	public static Test suite() {
        return new TestSuite(AddEJBeanOperationTest.class);
    }
	
	public void testAddSessionBean_EJB30_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddSessionBean_EJB30_Defaults();
		enableJETEmitter();
	}
	
	public void testAddSessionBean_EJB30_Defaults() throws Exception {
    	createEJBProject(EJB_PROJECT_NAME, JavaEEFacetConstants.EJB_3);
    	IProject proj = ProjectUtilities.getProject(EJB_PROJECT_NAME);

    	addSessionBean_Defaults();

		assertJavaFileExists(SESSION_BEAN_CLASS_NAME);
		assertJavaFileExists(SESSION_BEAN_LOCAL_CLASS_NAME);
    	
    	// no EJB3 annotation model to check yet
    }
	
	/**
	 * Verifies the @Asynchronous annotation be added properly.
	 * @throws Exception
	 */
	public void testAddSessionBean_EJB31_Asynchronous() throws Exception {
    	createEJBProject(EJB_PROJECT_NAME, JavaEEFacetConstants.EJB_31);
    	
    	addSessionBeanWithAync(EJB_WITH_ASYNC, true);
    	addSessionBeanWithAync(EJB_WITHOUT_ASYNC, false);

		assertTrue(ASYNC_WAS_EXPECTED, contains(EJB_WITH_ASYNC_CLASS_NAME, ASYNC_EJB_REGEX));
		assertFalse(ASYNC_WAS_NOT_EXPECTED, contains(EJB_WITHOUT_ASYNC_CLASS_NAME, ASYNCHRONOUS));
    }
	
	
	/**
	 * Verifies the EJB Timer be generated properly with the @Schedule annotation with
	 * the attribute persistent set to false, when the Timer is Non-persistent.
	 * @throws Exception
	 */
	public void testAddEjbTimer_EJB31() throws Exception{
		createEJBProject(EJB_PROJECT_NAME, JavaEEFacetConstants.EJB_31);

		addEjbTimer(PERSISTENT_EJB_TIMER, false);    
    	addEjbTimer(NON_PERSISTENT_EJB_TIMER, true); 
    	
    	assertFalse(TIMER_PERSISTENT_CONFIG_EXPECTED, contains(PERSISTENT_EJB_TIMER_CLASS_NAME, NON_PERSISTENT_TIMER_REGEX));
    	assertTrue(TIMER_NON_PERSISTENT_CONFIG_EXPECTED, contains(NON_PERSISTENT_EJB_TIMER_CLASS_NAME, NON_PERSISTENT_TIMER_REGEX));
	}
	
	public void testAddMessageDrivenBean_EJB30_Defaults_NoJETEmitter() throws Exception {
		disableJETEmitter();
		testAddMessageDrivenBean_EJB30_Defaults();
		enableJETEmitter();
	}
	
	public void testAddMessageDrivenBean_EJB30_Defaults() throws Exception {
    	createEJBProject(EJB_PROJECT_NAME, JavaEEFacetConstants.EJB_3);
    	IProject proj = ProjectUtilities.getProject(EJB_PROJECT_NAME);

    	addMessageDrivenBean_Defaults();

		assertJavaFileExists(MESSAGE_DRIVEN_BEAN_CLASS_NAME);
    	
    	// no EJB3 annotation model to check yet
    }

	@Override
	protected void tearDown() throws Exception {
		// uncomment the below line if you want to dump a check whether the
		// .JETEmitters projects is created as a result of the executed
		// operation
//		System.out.println(".JETEmitters exists : "
//				+ ResourcesPlugin.getWorkspace().getRoot().getProject(
//						WTPJETEmitter.PROJECT_NAME).exists());
		super.tearDown();
	}

    private void enableJETEmitter() {
    	Preferences preferences = J2EEPlugin.getDefault().getPluginPreferences();
		preferences.setValue(J2EEPlugin.DYNAMIC_TRANSLATION_OF_JET_TEMPLATES_PREF_KEY, true);
	}

	private void disableJETEmitter() {
		Preferences preferences = J2EEPlugin.getDefault().getPluginPreferences();
		preferences.setValue(J2EEPlugin.DYNAMIC_TRANSLATION_OF_JET_TEMPLATES_PREF_KEY, false);
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
	
	private void addSessionBeanWithAync(String ejbName, boolean async) throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewSessionBeanClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, EJB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, ejbName);
    	dm.setBooleanProperty(INewSessionBeanClassDataModelProperties.ASYNC, async);
        runAndVerify(dm);
	}
	
	/**
	 * Creates an EJB Timer.
	 * @param ejbTimerName name of the EJB Timer.
	 * @param persistent, <em>Boolean</em> indicating the nature of the timer, <em>null<em> for default, 
	 * <em>false</em> for Non-persistent or <em>true</em> for persistent.  
	 * @throws Exception
	 */
	private void addEjbTimer(String ejbTimerName, boolean persistent) throws Exception {
		AddEjbTimerDataModelProvider ejbTimerModel = new AddEjbTimerDataModelProvider();
		IDataModel dm = DataModelFactory.createDataModel(ejbTimerModel);
		dm.setProperty(PROJECT_NAME, EJB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, ejbTimerName);
    	dm.setBooleanProperty(AddEjbTimerDataModelProvider.NON_PERSISTENT, persistent);
    	
    	runAndVerify(dm);
	}
	
	private void addMessageDrivenBean_Defaults() throws Exception {
    	IDataModel dm = DataModelFactory.createDataModel(NewMessageDrivenBeanClassDataModelProvider.class);
    	dm.setProperty(PROJECT_NAME, EJB_PROJECT_NAME);
    	dm.setProperty(JAVA_PACKAGE, PACKAGE);
    	dm.setProperty(CLASS_NAME, MESSAGE_DRIVEN_BEAN_NAME);
        runAndVerify(dm);
    }
    
    private IFile assertJavaFileExists(String fullyQualifiedName) throws JavaModelException {
		IJavaProject javaProject = JavaCore.create(
				ResourcesPlugin.getWorkspace().getRoot())
				.getJavaModel().getJavaProject(EJB_PROJECT_NAME);
		assertNotNull("Java project " + EJB_PROJECT_NAME + " not found", javaProject);
		IType type = javaProject.findType(fullyQualifiedName);
		assertNotNull("Java type " + fullyQualifiedName + " not found", type);
		IFile file = (IFile) type.getResource();
		assertNotNull("Source file for Java type " + fullyQualifiedName + " not found", file);
		assertTrue(file.exists());
		return file;
    }
    
    private boolean contains(String fullyQualifiedName, String regex) throws CoreException, IOException {
    	IFile file = assertJavaFileExists(fullyQualifiedName);
		BufferedReader bReader = new BufferedReader(new InputStreamReader(file.getContents()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = bReader.readLine()) != null) {
	           sb.append(line).append(System.getProperty("line.separator")); //$NON-NLS-1$
	    }
		bReader.close();
		System.out.println(sb);
		Matcher matcher = Pattern.compile(regex).matcher(sb);
		
		return matcher.find();
    }

}
