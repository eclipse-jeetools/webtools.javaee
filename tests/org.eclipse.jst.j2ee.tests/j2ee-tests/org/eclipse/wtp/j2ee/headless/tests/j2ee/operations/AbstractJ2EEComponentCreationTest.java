package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.AddArchiveProjectsToEARDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IEarComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.IConnectorComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationOperation;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.TaskViewUtility;

public abstract class AbstractJ2EEComponentCreationTest extends TestCase {
	protected static final String ILLEGAL_PROJECT_NAME_MESSAGE = "Illegal project name: ";
	protected static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected exception";
	protected static final String TEST_FAILED_MESSAGE = "Test fails Exception should of been trown";
	protected static final String MANIFEST_CLASS_NAME = "Junit_Test_Dummy_Class";
	protected static final String MANIFEST_WRITE_ERROR = "Could not write to manifest test failed";
	protected static final String MANIFEST_LOCK_ERROR = "Manifest IO error - File could be locked";
	protected static final String MANIFEST_CORE_ERROR = "Java core error";
	protected String projectName = null;
	public static final int APPLICATION_CLIENT_MODULE = 0;
	public static final int WEB_MODULE = 1;
	public static final int EJB_MODULE = 2;
	public static final int EAR_MODULE = 3;
	public IProject ejbproject;
	public IProject earproject;

	public AbstractJ2EEComponentCreationTest(String name) {
		super(name);
	}

	public void createValidComponentNameCreationWithAlphabetChars(
		int MODULE_TYPE,
		int j2eeVersion,
		boolean isMixedChars)
		throws Exception {

		LogUtility.getInstance().resetLogging();
		IDataModel model = null;
		switch (MODULE_TYPE) {
			case WEB_MODULE :
				{
					if (!isMixedChars)
						model = setupWebComponent(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
					else
						model =
							setupWebComponent(
								RandomObjectGenerator.createCorrectRandomProjectNamesAllChars(),
								j2eeVersion);
					break;
				}
			case EJB_MODULE :
				{
					if (!isMixedChars)
						model = setupEJBComponent(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
					else
						model =
							setupEJBComponent(
								RandomObjectGenerator.createCorrectRandomProjectNamesAllChars(),
								j2eeVersion);
					break;
				}
			case APPLICATION_CLIENT_MODULE :
				{
					if (!isMixedChars)
						model =
							setupApplicationClientComponent(
								RandomObjectGenerator.createCorrectRandomProjectNames(),
								j2eeVersion);
					else
						model =
							setupApplicationClientComponent(
								RandomObjectGenerator.createCorrectRandomProjectNamesAllChars(),
								j2eeVersion);
					break;
				}
			case EAR_MODULE :
				{
					if (!isMixedChars)
						model = setupEARComponent(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
					else
						model =
							setupEARComponent(
								RandomObjectGenerator.createCorrectRandomProjectNamesAllChars(),
								j2eeVersion);
					break;
				}
		}

		LogUtility.getInstance().verifyNoWarnings();
		checkValidDataModel(model);
	}

	/**
	 * @param model
	 */
	protected void checkValidDataModel(IDataModel model) {
        //TODO need a verifier for new IDataModel
	/*	DataModelVerifier verifier = DataModelVerifierFactory.getInstance().createVerifier(model);
		try {
			verifier.verify(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	protected static void checkVaildProjectName(String projectName) {
		ProjectUtility.verifyProject(projectName, true);
		//To do verify
	}

	public IDataModel setupEJBComponent(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		IDataModel model = DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
		model.setProperty(IEjbComponentCreationDataModelProperties.PROJECT_NAME, javaProject.getName());
		//model.setProperty(EjbComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(IEjbComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		createEJBComponent(model, null);
		return model;
	}

	public IDataModel setupEARComponent(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject earProject = ProjectUtility.getProject(aProjectName);
		IDataModel earDataModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
		earDataModel.setProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME, aProjectName);
		//earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
		earDataModel.setIntProperty(IEarComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		OperationTestCase.runAndVerify(earDataModel);
		return earDataModel;
	}

	public IDataModel setupWebComponent(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
        IDataModel model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
		model.setProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME, javaProject.getName());
		//model.setProperty(WebComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		createWebComponent(model, null);
		return model;
	}

	public IDataModel setupApplicationClientComponent(String aProjectName, int j2eeVersion)
		throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		IDataModel model = DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());
		model.setProperty(IAppClientComponentCreationDataModelProperties.PROJECT_NAME, javaProject.getName());
		//model.setProperty(AppClientComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		createAppClientComponent(model, null);
		return model;
	}

	public void testJavaCreation() throws Exception {
		createEJBComponent("testEAR", "testEJB", J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.EJB_1_1_ID);
	}

	/**
	 * Create a project and its containing EAR with default model settings
	 * @param projectType
	 * @param earProject
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public static IProject createComponent(int projectType, String earProject, boolean createEAR, String projectName) throws Exception {
		if (createEAR)
			ProjectUtility.deleteProjectIfExists(earProject);
		ProjectUtility.deleteProjectIfExists(projectName);

		if (earProject != null && createEAR) {
			IDataModel earCompCreationDataModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
            earCompCreationDataModel.setProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME, earProject);
            earCompCreationDataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
			ProjectUtility.verifyProject(earProject, true);
		}

		IDataModel projectCreationDataModel = null;
		switch (projectType) {
			case EJB_MODULE :
				projectCreationDataModel =DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
				break;
			case WEB_MODULE :
				projectCreationDataModel = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
				break;
			case APPLICATION_CLIENT_MODULE :
				projectCreationDataModel =DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());
				break;
		}
		if (earProject != null) {
			projectCreationDataModel.setBooleanProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR, true);
			projectCreationDataModel.setProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME, earProject);
		}
		projectCreationDataModel.setProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		projectCreationDataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
		ProjectUtility.verifyProject(projectName, true);

		return ProjectUtility.getProject(projectName);
	}


	public static IProject createEARComponent(String earProject) throws Exception {
		IDataModel projectCreationModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
		projectCreationModel.setProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME, earProject);
		return createEARComponent(projectCreationModel);
	}

	public static IProject createEARComponent(IDataModel model) throws Exception {
		EARComponentCreationOperation op = new EARComponentCreationOperation(model);
		op.execute(new NullProgressMonitor(), null);
		ProjectUtility.verifyProject(model.getStringProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME), true);
		return ProjectUtilities.getProject(model.getStringProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME));
	}

	public static IProject createEJBComponent(IDataModel model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(IEjbComponentCreationDataModelProperties.ADD_TO_EAR, true);
			model.setProperty(IEjbComponentCreationDataModelProperties.EAR_COMPONENT_NAME, earProject.getName());
		}
		EjbComponentCreationOperation ejbOp = new EjbComponentCreationOperation(model);
		ejbOp.execute(new NullProgressMonitor(), null);
		ProjectUtility.verifyProject(model.getStringProperty(IEjbComponentCreationDataModelProperties.PROJECT_NAME), true);
		return ProjectUtilities.getProject(model.getStringProperty(IEjbComponentCreationDataModelProperties.PROJECT_NAME));
	}

	public static void createEARComponent(IDataModel  model, boolean notImport) throws Exception {
		model.setBooleanProperty(IEarComponentCreationDataModelProperties.CREATE_DEFAULT_FILES, notImport);
		EARComponentCreationOperation op = new EARComponentCreationOperation(model);
		op.execute(new NullProgressMonitor(), null);
		ProjectUtility.verifyProject(model.getStringProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME), true);
	}

	public static void createWebComponent(IDataModel model, IProject earProject) throws Exception {
        if (earProject != null) {
            model.setBooleanProperty(IWebComponentCreationDataModelProperties.ADD_TO_EAR, true);
            model.setProperty(IWebComponentCreationDataModelProperties.EAR_COMPONENT_NAME, earProject.getName());
        }
		WebComponentCreationOperation webOp = new WebComponentCreationOperation(model);
		webOp.execute(new NullProgressMonitor(), null);
		ProjectUtility.verifyProject(model.getStringProperty(IWebComponentCreationDataModelProperties.PROJECT_NAME), true);
		TaskViewUtility.verifyNoErrors();
	}

	public static void createAppClientComponent(IDataModel model, IProject earProject) throws Exception {
        if (earProject != null) {
            model.setBooleanProperty(IAppClientComponentCreationDataModelProperties.ADD_TO_EAR, true);
            model.setProperty(IAppClientComponentCreationDataModelProperties.EAR_COMPONENT_NAME, earProject.getName());
        }
		AppClientComponentCreationOperation appOp = new AppClientComponentCreationOperation(model);
		appOp.execute(new NullProgressMonitor(), null);
		ProjectUtility.verifyProject(model.getStringProperty(IAppClientComponentCreationDataModelProperties.PROJECT_NAME), true);
	}

	public static void createRarComponent(IDataModel model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(IConnectorComponentCreationDataModelProperties.ADD_TO_EAR, true);
			model.setProperty(IConnectorComponentCreationDataModelProperties.EAR_COMPONENT_NAME, earProject.getName());
		}
		ConnectorComponentCreationOperation rarOp = new ConnectorComponentCreationOperation(model);
		rarOp.execute(new NullProgressMonitor(), null);
		ProjectUtility.verifyProject(model.getStringProperty(IConnectorComponentCreationDataModelProperties.PROJECT_NAME), true);
	}

	public static IProject createEJBComponent(String earName, String ejbName, int j2eeEARVersion, int j2eeEJBVersion)
		throws Exception {
		ProjectUtility.deleteAllProjects();
		IDataModel model = null;
        IDataModel ejbDataModel = null;
		if (earName != null) {
			IProject earProject = ProjectUtility.getProject(earName);
			model = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
			model.setProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME, earName);
			//model.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
			model.setIntProperty(IEarComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeEARVersion);
			EARComponentCreationOperation op = new EARComponentCreationOperation(model);

			IProject ejbProject = ProjectUtility.getProject(ejbName);
			ejbDataModel = DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
			ejbDataModel.setProperty(IEjbComponentCreationDataModelProperties.PROJECT_NAME, ejbName);
			//ejbDataModel.setProperty(EjbComponentCreationDataModel.PROJECT_LOCATION, ejbProject.getLocation());
			ejbDataModel.setIntProperty(IEjbComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeEJBVersion);
			IProject ejbp = createEJBComponent(ejbDataModel, ProjectUtilities.getProject(model.getStringProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME)));

		}
        return ProjectUtilities.getProject(ejbDataModel.getStringProperty(IEjbComponentCreationDataModelProperties.PROJECT_NAME));
	}

	public static void addJavaMainClassToApplicationModel(IProject appProject) {
		J2EENature runtime = J2EENature.getRuntime(appProject,IApplicationClientNatureConstants.NATURE_ID);
		IFile file = runtime.getEMFRoot().getFile(new Path(ArchiveConstants.MANIFEST_URI));
		ArchiveManifestImpl manifest = null;
		InputStream inputStream = null;
		try {
			inputStream = file.getContents();
			manifest = new ArchiveManifestImpl(inputStream);
		} catch (IOException e) {
			new Exception(MANIFEST_LOCK_ERROR);
		} catch (CoreException e) {
			new Exception(MANIFEST_CORE_ERROR);
		} finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException ex) {
				}
			}
		}
		manifest.setMainClass(MANIFEST_CLASS_NAME);
		try {
			//attempt with manifest dataObject -- J2EEProjectUtilities.writeManifest(appProject, manifest);
		} catch (Exception e) {
			new Exception(MANIFEST_WRITE_ERROR);
		}
	}

	public void testAddingEJBtoEarModule() throws Exception {
		ProjectUtility.deleteAllProjects();
		setupEARDataObject(RandomObjectGenerator.createCorrectRandomProjectNames(), J2EEVersionConstants.J2EE_1_3_ID);

	}

	public String setupEARDataObject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject earProject = ProjectUtility.getProject(aProjectName);
		IDataModel earDataModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
		earDataModel.setProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME, aProjectName);
		//earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
		//String projectName = setupEJBProject(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
		IProject ejbProject = ProjectUtility.getProject(projectName);
		ArrayList list = new ArrayList();
		list.add(ejbProject);
		earDataModel.setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, list);
		earDataModel.setIntProperty(IEarComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		earDataModel.getProperty(AddArchiveProjectsToEARDataModel.MODULE_MODELS);
		return earDataModel.getStringProperty(IEarComponentCreationDataModelProperties.PROJECT_NAME);
	}

	public AbstractJ2EEComponentCreationTest() {
		super();
	}

}
