package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.operations.AddArchiveProjectsToEARDataModel;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationOperation;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleCreationOperation;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationOperation;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
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
		J2EEArtifactCreationDataModel model = null;
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
	protected void checkValidDataModel(J2EEArtifactCreationDataModel model) {
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

	public J2EEModuleCreationDataModel setupEJBProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		EJBModuleCreationDataModel model = new EJBModuleCreationDataModel();
		model.setProperty(EJBModuleCreationDataModel.PROJECT_NAME, javaProject.getName());
		model.setProperty(EJBModuleCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(EJBModuleCreationDataModel.J2EE_MODULE_VERSION, j2eeVersion);
		createEJBProject(model, null);
		return model;
	}

	public EnterpriseApplicationCreationDataModel setupEARProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject earProject = ProjectUtility.getProject(aProjectName);
		EnterpriseApplicationCreationDataModel earDataModel = new EnterpriseApplicationCreationDataModel();
		earDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, aProjectName);
		earDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
		earDataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, j2eeVersion);
		OperationTestCase.runAndVerify(earDataModel);
		return earDataModel;
	}

	public J2EEModuleCreationDataModel setupWebProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		WebModuleCreationDataModel model = new WebModuleCreationDataModel();
		model.setProperty(WebModuleCreationDataModel.PROJECT_NAME, javaProject.getName());
		model.setProperty(WebModuleCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(WebModuleCreationDataModel.J2EE_MODULE_VERSION, j2eeVersion);
		createWebProject(model, null);
		return model;
	}

	public J2EEModuleCreationDataModel setupApplicationClientProject(String aProjectName, int j2eeVersion)
		throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		AppClientModuleCreationDataModel model = new AppClientModuleCreationDataModel();
		model.setProperty(AppClientModuleCreationDataModel.PROJECT_NAME, javaProject.getName());
		model.setProperty(AppClientModuleCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(AppClientModuleCreationDataModel.J2EE_MODULE_VERSION, j2eeVersion);
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
			WTPOperationDataModel earProjectCreationDataModel = new EnterpriseApplicationCreationDataModel();
			earProjectCreationDataModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
			earProjectCreationDataModel.getDefaultOperation().run(null);
			ProjectUtility.verifyProject(earProject, true);
		}

		WTPOperationDataModel projectCreationDataModel = null;
		switch (projectType) {
			case EJB_PROJECT :
				projectCreationDataModel = new EnterpriseApplicationCreationDataModel();
				break;
			case WEB_PROJECT :
				projectCreationDataModel = new EnterpriseApplicationCreationDataModel();
				break;
			case APPLICATION_CLIENT_PROJECT :
				projectCreationDataModel = new EnterpriseApplicationCreationDataModel();
				break;
		}
		if (earProject != null) {
			projectCreationDataModel.setBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR, true);
			projectCreationDataModel.setProperty(J2EEModuleCreationDataModel.EAR_PROJECT_NAME, earProject);
		}
		projectCreationDataModel.setProperty(EJBModuleCreationDataModel.PROJECT_NAME, projectName);
		projectCreationDataModel.getDefaultOperation().run(null);
		ProjectUtility.verifyProject(projectName, true);

		return (IProject) ProjectUtility.getProject(projectName);
	}


	public static IProject createEARProject(String earProject) throws Exception {
		EnterpriseApplicationCreationDataModel projectCreationModel = new EnterpriseApplicationCreationDataModel();
		projectCreationModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
		return createEARProject(projectCreationModel);
	}

	public static IProject createEARProject(EnterpriseApplicationCreationDataModel model) throws Exception {
		EnterpriseApplicationCreationOperation op = new EnterpriseApplicationCreationOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static IProject createEJBProject(EJBModuleCreationDataModel model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(EJBModuleCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(EJBModuleCreationDataModel.EAR_PROJECT_NAME, earProject.getName());
		}
		EJBModuleCreationOperation ejbOp = new EJBModuleCreationOperation(model);
		ejbOp.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EJBModuleCreationDataModel.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static void createEARProject(EnterpriseApplicationCreationDataModel model, boolean notImport) throws Exception {
		model.setBooleanProperty(EnterpriseApplicationCreationDataModel.CREATE_DEFAULT_FILES, notImport);
		EnterpriseApplicationCreationOperation op = new EnterpriseApplicationCreationOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME), true);
	}

	public static void createWebProject(WebModuleCreationDataModel model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(WebModuleCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(WebModuleCreationDataModel.EAR_PROJECT_NAME, earProject.getName());
		}
		WebModuleCreationOperation webOp = new WebModuleCreationOperation(model);
		webOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();
	}

	public static void createAppClientProject(AppClientModuleCreationDataModel model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(AppClientModuleCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(AppClientModuleCreationDataModel.EAR_PROJECT_NAME, earProject.getName());
		}
		AppClientModuleCreationOperation appOp = new AppClientModuleCreationOperation(model);
		appOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static void createRarProject(ConnectorModuleCreationDataModel model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(ConnectorModuleCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(ConnectorModuleCreationDataModel.EAR_PROJECT_NAME, earProject.getName());
		}
		ConnectorModuleCreationOperation rarOp = new ConnectorModuleCreationOperation(model);
		rarOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static IProject createEJBProject(String earName, String ejbName, int j2eeEARVersion, int j2eeEJBVersion)
		throws Exception {
		ProjectUtility.deleteAllProjects();
		EnterpriseApplicationCreationDataModel model = null;
		EJBModuleCreationDataModel ejbDataModel = null;
		if (earName != null) {
			IProject earProject = ProjectUtility.getProject(earName);
			model = new EnterpriseApplicationCreationDataModel();
			model.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, earName);
			model.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
			model.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, j2eeEARVersion);
			EnterpriseApplicationCreationOperation op = new EnterpriseApplicationCreationOperation(model);

			IProject ejbProject = ProjectUtility.getProject(ejbName);
			ejbDataModel = new EJBModuleCreationDataModel();
			ejbDataModel.setProperty(EJBModuleCreationDataModel.PROJECT_NAME, ejbName);
			ejbDataModel.setProperty(EJBModuleCreationDataModel.PROJECT_LOCATION, ejbProject.getLocation());
			ejbDataModel.setIntProperty(EJBModuleCreationDataModel.J2EE_MODULE_VERSION, j2eeEJBVersion);
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
		EnterpriseApplicationCreationDataModel earDataModel = new EnterpriseApplicationCreationDataModel();
		earDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME, aProjectName);
		earDataModel.setProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
		//String projectName = setupEJBProject(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
		IProject ejbProject = ProjectUtility.getProject(projectName);
		ArrayList list = new ArrayList();
		list.add(ejbProject);
		earDataModel.setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, list);
		earDataModel.setIntProperty(EnterpriseApplicationCreationDataModel.APPLICATION_VERSION, j2eeVersion);
		earDataModel.getProperty(AddArchiveProjectsToEARDataModel.MODULE_MODELS);
		AddArchiveProjectsToEARDataModel arcModel = earDataModel.getAddModulesToEARDataModel();
		return (earDataModel.getStringProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME));
	}

	public AbstractProjectCreationTest() {
		super();
	}

}
