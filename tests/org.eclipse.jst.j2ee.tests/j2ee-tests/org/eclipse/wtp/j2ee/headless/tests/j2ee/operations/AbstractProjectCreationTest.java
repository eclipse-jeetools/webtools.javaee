package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.AddArchiveProjectsToEARDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.EARComponentCreationOperation;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationOperation;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.TaskViewUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifierFactory;

public abstract class AbstractProjectCreationTest extends TestCase {
	protected static final String ILLEGAL_PROJECT_NAME_MESSAGE = "Illegal project name: ";
	protected static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected exception";
	protected static final String TEST_FAILED_MESSAGE = "Test fails Exception should of been trown";
	protected static final String MANIFEST_CLASS_NAME = "Junit_Test_Dummy_Class";
	protected static final String MANIFEST_WRITE_ERROR = "Could not write to manifest test failed";
	protected static final String MANIFEST_LOCK_ERROR = "Manifest IO error - File could be locked";
	protected static final String MANIFEST_CORE_ERROR = "Java core error";
	protected String projectName = null;
	public static final int APPLICATION_CLIENT_PROJECT = 0;
	public static final int WEB_PROJECT = 1;
	public static final int EJB_PROJECT = 2;
	public static final int EAR_PROJECT = 3;
	public IProject ejbproject;
	public IProject earproject;

	public AbstractProjectCreationTest(String name) {
		super(name);
	}

	public void createVaildProjectNameCreationWithAlphabetChars(
		int PROJECT_TYPE,
		int j2eeVersion,
		boolean isMixedChars)
		throws Exception {

		LogUtility.getInstance().resetLogging();
		J2EEComponentCreationDataModel model = null;
		switch (PROJECT_TYPE) {
			case WEB_PROJECT :
				{
					if (!isMixedChars)
						model = setupWebProject(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
					else
						model =
							setupWebProject(
								RandomObjectGenerator.createCorrectRandomProjectNamesAllChars(),
								j2eeVersion);
					break;
				}
			case EJB_PROJECT :
				{
					if (!isMixedChars)
						model = setupEJBProject(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
					else
						model =
							setupEJBProject(
								RandomObjectGenerator.createCorrectRandomProjectNamesAllChars(),
								j2eeVersion);
					break;
				}
			case APPLICATION_CLIENT_PROJECT :
				{
					if (!isMixedChars)
						model =
							setupApplicationClientProject(
								RandomObjectGenerator.createCorrectRandomProjectNames(),
								j2eeVersion);
					else
						model =
							setupApplicationClientProject(
								RandomObjectGenerator.createCorrectRandomProjectNamesAllChars(),
								j2eeVersion);
					break;
				}
			case EAR_PROJECT :
				{
					if (!isMixedChars)
						model = setupEARProject(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
					else
						model =
							setupEARProject(
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
	protected void checkValidDataModel(J2EEComponentCreationDataModel model) {
		DataModelVerifier verifier = DataModelVerifierFactory.getInstance().createVerifier(model);
		try {
			verifier.verify(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected static void checkVaildProjectName(String projectName) {
		ProjectUtility.verifyProject(projectName, true);
		//To do verify
	}

	public J2EEComponentCreationDataModel setupEJBProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		EjbComponentCreationDataModel model = new EjbComponentCreationDataModel();
		model.setProperty(EjbComponentCreationDataModel.PROJECT_NAME, javaProject.getName());
		//model.setProperty(EjbComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(EjbComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		createEJBProject(model, null);
		return model;
	}

	public EARComponentCreationDataModel setupEARProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject earProject = ProjectUtility.getProject(aProjectName);
		EARComponentCreationDataModel earDataModel = new EARComponentCreationDataModel();
		earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, aProjectName);
		//earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
		earDataModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		OperationTestCase.runAndVerify(earDataModel);
		return earDataModel;
	}

	public J2EEComponentCreationDataModel setupWebProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		WebComponentCreationDataModel model = new WebComponentCreationDataModel();
		model.setProperty(WebComponentCreationDataModel.PROJECT_NAME, javaProject.getName());
		//model.setProperty(WebComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(WebComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		createWebProject(model, null);
		return model;
	}

	public J2EEComponentCreationDataModel setupApplicationClientProject(String aProjectName, int j2eeVersion)
		throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		AppClientComponentCreationDataModel model = new AppClientComponentCreationDataModel();
		model.setProperty(AppClientComponentCreationDataModel.PROJECT_NAME, javaProject.getName());
		//model.setProperty(AppClientComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		createAppClientProject(model, null);
		return model;
	}

	public void testJavaCreation() throws Exception {
		createEJBProject("testEAR", "testEJB", J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.EJB_1_1_ID);
	}

	/**
	 * Create a project and its containing EAR with default model settings
	 * @param projectType
	 * @param earProject
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public static IProject createProject(int projectType, String earProject, boolean createEAR, String projectName) throws Exception {
		if (createEAR)
			ProjectUtility.deleteProjectIfExists(earProject);
		ProjectUtility.deleteProjectIfExists(projectName);

		if (earProject != null && createEAR) {
			WTPOperationDataModel earProjectCreationDataModel = new EARComponentCreationDataModel();
			earProjectCreationDataModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
			earProjectCreationDataModel.getDefaultOperation().run(null);
			ProjectUtility.verifyProject(earProject, true);
		}

		WTPOperationDataModel projectCreationDataModel = null;
		switch (projectType) {
			case EJB_PROJECT :
				projectCreationDataModel = new EARComponentCreationDataModel();
				break;
			case WEB_PROJECT :
				projectCreationDataModel = new EARComponentCreationDataModel();
				break;
			case APPLICATION_CLIENT_PROJECT :
				projectCreationDataModel = new EARComponentCreationDataModel();
				break;
		}
		if (earProject != null) {
			projectCreationDataModel.setBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR, true);
			projectCreationDataModel.setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, earProject);
		}
		projectCreationDataModel.setProperty(EjbComponentCreationDataModel.PROJECT_NAME, projectName);
		projectCreationDataModel.getDefaultOperation().run(null);
		ProjectUtility.verifyProject(projectName, true);

		return (IProject) ProjectUtility.getProject(projectName);
	}


	public static IProject createEARProject(String earProject) throws Exception {
		EARComponentCreationDataModel projectCreationModel = new EARComponentCreationDataModel();
		projectCreationModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
		return createEARProject(projectCreationModel);
	}

	public static IProject createEARProject(EARComponentCreationDataModel model) throws Exception {
		EARComponentCreationOperation op = new EARComponentCreationOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EARComponentCreationDataModel.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static IProject createEJBProject(EjbComponentCreationDataModel model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(EjbComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(EjbComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		EjbComponentCreationOperation ejbOp = new EjbComponentCreationOperation(model);
		ejbOp.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EjbComponentCreationDataModel.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static void createEARProject(EARComponentCreationDataModel model, boolean notImport) throws Exception {
		model.setBooleanProperty(EARComponentCreationDataModel.CREATE_DEFAULT_FILES, notImport);
		EARComponentCreationOperation op = new EARComponentCreationOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EARComponentCreationDataModel.PROJECT_NAME), true);
	}

	public static void createWebProject(WebComponentCreationDataModel model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(WebComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(WebComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		WebComponentCreationOperation webOp = new WebComponentCreationOperation(model);
		webOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();
	}

	public static void createAppClientProject(AppClientComponentCreationDataModel model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(AppClientComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(AppClientComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		AppClientComponentCreationOperation appOp = new AppClientComponentCreationOperation(model);
		appOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static void createRarProject(ConnectorComponentCreationDataModel model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(ConnectorComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(ConnectorComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		ConnectorComponentCreationOperation rarOp = new ConnectorComponentCreationOperation(model);
		rarOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static IProject createEJBProject(String earName, String ejbName, int j2eeEARVersion, int j2eeEJBVersion)
		throws Exception {
		ProjectUtility.deleteAllProjects();
		EARComponentCreationDataModel model = null;
		EjbComponentCreationDataModel ejbDataModel = null;
		if (earName != null) {
			IProject earProject = ProjectUtility.getProject(earName);
			model = new EARComponentCreationDataModel();
			model.setProperty(EARComponentCreationDataModel.PROJECT_NAME, earName);
			//model.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
			model.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, j2eeEARVersion);
			EARComponentCreationOperation op = new EARComponentCreationOperation(model);

			IProject ejbProject = ProjectUtility.getProject(ejbName);
			ejbDataModel = new EjbComponentCreationDataModel();
			ejbDataModel.setProperty(EjbComponentCreationDataModel.PROJECT_NAME, ejbName);
			//ejbDataModel.setProperty(EjbComponentCreationDataModel.PROJECT_LOCATION, ejbProject.getLocation());
			ejbDataModel.setIntProperty(EjbComponentCreationDataModel.COMPONENT_VERSION, j2eeEJBVersion);
			IProject ejbp = createEJBProject(ejbDataModel, model.getTargetProject());

		}
		return ejbDataModel.getTargetProject();
	}

	public static void addJavaMainClassToApplicationModel(IProject appProject) {
		ApplicationClientNatureRuntime runtime = ApplicationClientNatureRuntime.getRuntime(appProject);
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
		EARComponentCreationDataModel earDataModel = new EARComponentCreationDataModel();
		earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, aProjectName);
		//earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
		//String projectName = setupEJBProject(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
		IProject ejbProject = ProjectUtility.getProject(projectName);
		ArrayList list = new ArrayList();
		list.add(ejbProject);
		earDataModel.setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, list);
		earDataModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		earDataModel.getProperty(AddArchiveProjectsToEARDataModel.MODULE_MODELS);
		AddComponentToEnterpriseApplicationDataModel arcModel = earDataModel.addComponentToEARDataModel;
		return (earDataModel.getStringProperty(EARComponentCreationDataModel.PROJECT_NAME));
	}

	public AbstractProjectCreationTest() {
		super();
	}

}
