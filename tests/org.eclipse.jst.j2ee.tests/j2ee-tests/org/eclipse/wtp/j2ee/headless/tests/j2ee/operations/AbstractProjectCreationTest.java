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
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationDataModelOld;
import org.eclipse.jst.j2ee.application.operations.EnterpriseApplicationCreationOperationOld;
import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModelOld;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleCreationOperationOld;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleCreationOperationOld;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleCreationOperationOld;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationOperationOld;
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
		J2EEArtifactCreationDataModelOld model = null;
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
	protected void checkValidDataModel(J2EEArtifactCreationDataModelOld model) {
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

	public J2EEModuleCreationDataModelOld setupEJBProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		EJBModuleCreationDataModelOld model = new EJBModuleCreationDataModelOld();
		model.setProperty(EJBModuleCreationDataModelOld.PROJECT_NAME, javaProject.getName());
		model.setProperty(EJBModuleCreationDataModelOld.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(EJBModuleCreationDataModelOld.J2EE_MODULE_VERSION, j2eeVersion);
		createEJBProject(model, null);
		return model;
	}

	public EnterpriseApplicationCreationDataModelOld setupEARProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject earProject = ProjectUtility.getProject(aProjectName);
		EnterpriseApplicationCreationDataModelOld earDataModel = new EnterpriseApplicationCreationDataModelOld();
		earDataModel.setProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_NAME, aProjectName);
		earDataModel.setProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_LOCATION, earProject.getLocation());
		earDataModel.setIntProperty(EnterpriseApplicationCreationDataModelOld.APPLICATION_VERSION, j2eeVersion);
		OperationTestCase.runAndVerify(earDataModel);
		return earDataModel;
	}

	public J2EEModuleCreationDataModelOld setupWebProject(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		WebModuleCreationDataModelOld model = new WebModuleCreationDataModelOld();
		model.setProperty(WebModuleCreationDataModelOld.PROJECT_NAME, javaProject.getName());
		model.setProperty(WebModuleCreationDataModelOld.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(WebModuleCreationDataModelOld.J2EE_MODULE_VERSION, j2eeVersion);
		createWebProject(model, null);
		return model;
	}

	public J2EEModuleCreationDataModelOld setupApplicationClientProject(String aProjectName, int j2eeVersion)
		throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		AppClientModuleCreationDataModelOld model = new AppClientModuleCreationDataModelOld();
		model.setProperty(AppClientModuleCreationDataModelOld.PROJECT_NAME, javaProject.getName());
		model.setProperty(AppClientModuleCreationDataModelOld.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(AppClientModuleCreationDataModelOld.J2EE_MODULE_VERSION, j2eeVersion);
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
			WTPOperationDataModel earProjectCreationDataModel = new EnterpriseApplicationCreationDataModelOld();
			earProjectCreationDataModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
			earProjectCreationDataModel.getDefaultOperation().run(null);
			ProjectUtility.verifyProject(earProject, true);
		}

		WTPOperationDataModel projectCreationDataModel = null;
		switch (projectType) {
			case EJB_PROJECT :
				projectCreationDataModel = new EnterpriseApplicationCreationDataModelOld();
				break;
			case WEB_PROJECT :
				projectCreationDataModel = new EnterpriseApplicationCreationDataModelOld();
				break;
			case APPLICATION_CLIENT_PROJECT :
				projectCreationDataModel = new EnterpriseApplicationCreationDataModelOld();
				break;
		}
		if (earProject != null) {
			projectCreationDataModel.setBooleanProperty(J2EEModuleCreationDataModelOld.ADD_TO_EAR, true);
			projectCreationDataModel.setProperty(J2EEModuleCreationDataModelOld.EAR_PROJECT_NAME, earProject);
		}
		projectCreationDataModel.setProperty(EJBModuleCreationDataModelOld.PROJECT_NAME, projectName);
		projectCreationDataModel.getDefaultOperation().run(null);
		ProjectUtility.verifyProject(projectName, true);

		return (IProject) ProjectUtility.getProject(projectName);
	}


	public static IProject createEARProject(String earProject) throws Exception {
		EnterpriseApplicationCreationDataModelOld projectCreationModel = new EnterpriseApplicationCreationDataModelOld();
		projectCreationModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
		return createEARProject(projectCreationModel);
	}

	public static IProject createEARProject(EnterpriseApplicationCreationDataModelOld model) throws Exception {
		EnterpriseApplicationCreationOperationOld op = new EnterpriseApplicationCreationOperationOld(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static IProject createEJBProject(EJBModuleCreationDataModelOld model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(EJBModuleCreationDataModelOld.ADD_TO_EAR, true);
			model.setProperty(EJBModuleCreationDataModelOld.EAR_PROJECT_NAME, earProject.getName());
		}
		EJBModuleCreationOperationOld ejbOp = new EJBModuleCreationOperationOld(model);
		ejbOp.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EJBModuleCreationDataModelOld.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static void createEARProject(EnterpriseApplicationCreationDataModelOld model, boolean notImport) throws Exception {
		model.setBooleanProperty(EnterpriseApplicationCreationDataModelOld.CREATE_DEFAULT_FILES, notImport);
		EnterpriseApplicationCreationOperationOld op = new EnterpriseApplicationCreationOperationOld(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_NAME), true);
	}

	public static void createWebProject(WebModuleCreationDataModelOld model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(WebModuleCreationDataModelOld.ADD_TO_EAR, true);
			model.setProperty(WebModuleCreationDataModelOld.EAR_PROJECT_NAME, earProject.getName());
		}
		WebModuleCreationOperationOld webOp = new WebModuleCreationOperationOld(model);
		webOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();
	}

	public static void createAppClientProject(AppClientModuleCreationDataModelOld model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(AppClientModuleCreationDataModelOld.ADD_TO_EAR, true);
			model.setProperty(AppClientModuleCreationDataModelOld.EAR_PROJECT_NAME, earProject.getName());
		}
		AppClientModuleCreationOperationOld appOp = new AppClientModuleCreationOperationOld(model);
		appOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static void createRarProject(ConnectorModuleCreationDataModelOld model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(ConnectorModuleCreationDataModelOld.ADD_TO_EAR, true);
			model.setProperty(ConnectorModuleCreationDataModelOld.EAR_PROJECT_NAME, earProject.getName());
		}
		ConnectorModuleCreationOperationOld rarOp = new ConnectorModuleCreationOperationOld(model);
		rarOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static IProject createEJBProject(String earName, String ejbName, int j2eeEARVersion, int j2eeEJBVersion)
		throws Exception {
		ProjectUtility.deleteAllProjects();
		EnterpriseApplicationCreationDataModelOld model = null;
		EJBModuleCreationDataModelOld ejbDataModel = null;
		if (earName != null) {
			IProject earProject = ProjectUtility.getProject(earName);
			model = new EnterpriseApplicationCreationDataModelOld();
			model.setProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_NAME, earName);
			model.setProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_LOCATION, earProject.getLocation());
			model.setIntProperty(EnterpriseApplicationCreationDataModelOld.APPLICATION_VERSION, j2eeEARVersion);
			EnterpriseApplicationCreationOperationOld op = new EnterpriseApplicationCreationOperationOld(model);

			IProject ejbProject = ProjectUtility.getProject(ejbName);
			ejbDataModel = new EJBModuleCreationDataModelOld();
			ejbDataModel.setProperty(EJBModuleCreationDataModelOld.PROJECT_NAME, ejbName);
			ejbDataModel.setProperty(EJBModuleCreationDataModelOld.PROJECT_LOCATION, ejbProject.getLocation());
			ejbDataModel.setIntProperty(EJBModuleCreationDataModelOld.J2EE_MODULE_VERSION, j2eeEJBVersion);
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
		EnterpriseApplicationCreationDataModelOld earDataModel = new EnterpriseApplicationCreationDataModelOld();
		earDataModel.setProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_NAME, aProjectName);
		earDataModel.setProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_LOCATION, earProject.getLocation());
		//String projectName = setupEJBProject(RandomObjectGenerator.createCorrectRandomProjectNames(), j2eeVersion);
		IProject ejbProject = ProjectUtility.getProject(projectName);
		ArrayList list = new ArrayList();
		list.add(ejbProject);
		earDataModel.setProperty(AddArchiveProjectsToEARDataModel.MODULE_LIST, list);
		earDataModel.setIntProperty(EnterpriseApplicationCreationDataModelOld.APPLICATION_VERSION, j2eeVersion);
		earDataModel.getProperty(AddArchiveProjectsToEARDataModel.MODULE_MODELS);
		AddArchiveProjectsToEARDataModel arcModel = earDataModel.getAddModulesToEARDataModel();
		return (earDataModel.getStringProperty(EnterpriseApplicationCreationDataModelOld.PROJECT_NAME));
	}

	public AbstractProjectCreationTest() {
		super();
	}

}
