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
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.TaskViewUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifierFactory;

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
		J2EEComponentCreationDataModel model = null;
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

	public J2EEComponentCreationDataModel setupEJBComponent(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		EjbComponentCreationDataModel model = new EjbComponentCreationDataModel();
		model.setProperty(EjbComponentCreationDataModel.PROJECT_NAME, javaProject.getName());
		//model.setProperty(EjbComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(EjbComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		createEJBComponent(model, null);
		return model;
	}

	public EARComponentCreationDataModel setupEARComponent(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject earProject = ProjectUtility.getProject(aProjectName);
		EARComponentCreationDataModel earDataModel = new EARComponentCreationDataModel();
		earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_NAME, aProjectName);
		//earDataModel.setProperty(EARComponentCreationDataModel.PROJECT_LOCATION, earProject.getLocation());
		earDataModel.setIntProperty(EARComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		OperationTestCase.runAndVerify(earDataModel);
		return earDataModel;
	}

	public J2EEComponentCreationDataModel setupWebComponent(String aProjectName, int j2eeVersion) throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		WebComponentCreationDataModel model = new WebComponentCreationDataModel();
		model.setProperty(WebComponentCreationDataModel.PROJECT_NAME, javaProject.getName());
		//model.setProperty(WebComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(WebComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
		createWebComponent(model, null);
		return model;
	}

	public J2EEComponentCreationDataModel setupApplicationClientComponent(String aProjectName, int j2eeVersion)
		throws Exception {
		projectName = aProjectName;
		IProject javaProject = ProjectUtility.getProject(projectName);
		AppClientComponentCreationDataModel model = new AppClientComponentCreationDataModel();
		model.setProperty(AppClientComponentCreationDataModel.PROJECT_NAME, javaProject.getName());
		//model.setProperty(AppClientComponentCreationDataModel.PROJECT_LOCATION, javaProject.getLocation());
		model.setIntProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION, j2eeVersion);
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
			WTPOperationDataModel earProjectCreationDataModel = new EARComponentCreationDataModel();
			earProjectCreationDataModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
			earProjectCreationDataModel.getDefaultOperation().run(null);
			ProjectUtility.verifyProject(earProject, true);
		}

		WTPOperationDataModel projectCreationDataModel = null;
		switch (projectType) {
			case EJB_MODULE :
				projectCreationDataModel = new EARComponentCreationDataModel();
				break;
			case WEB_MODULE :
				projectCreationDataModel = new EARComponentCreationDataModel();
				break;
			case APPLICATION_CLIENT_MODULE :
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


	public static IProject createEARComponent(String earProject) throws Exception {
		EARComponentCreationDataModel projectCreationModel = new EARComponentCreationDataModel();
		projectCreationModel.setProperty(ProjectCreationDataModel.PROJECT_NAME, earProject);
		return createEARComponent(projectCreationModel);
	}

	public static IProject createEARComponent(EARComponentCreationDataModel model) throws Exception {
		EARComponentCreationOperation op = new EARComponentCreationOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EARComponentCreationDataModel.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static IProject createEJBComponent(EjbComponentCreationDataModel model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(EjbComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(EjbComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		EjbComponentCreationOperation ejbOp = new EjbComponentCreationOperation(model);
		ejbOp.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EjbComponentCreationDataModel.PROJECT_NAME), true);
		return model.getTargetProject();
	}

	public static void createEARComponent(EARComponentCreationDataModel model, boolean notImport) throws Exception {
		model.setBooleanProperty(EARComponentCreationDataModel.CREATE_DEFAULT_FILES, notImport);
		EARComponentCreationOperation op = new EARComponentCreationOperation(model);
		op.run(null);
		ProjectUtility.verifyProject(model.getStringProperty(EARComponentCreationDataModel.PROJECT_NAME), true);
	}

	public static void createWebComponent(WebComponentCreationDataModel model, IProject earProject) throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(WebComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(WebComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		WebComponentCreationOperation webOp = new WebComponentCreationOperation(model);
		webOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		TaskViewUtility.verifyNoErrors();
	}

	public static void createAppClientComponent(AppClientComponentCreationDataModel model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(AppClientComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(AppClientComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		AppClientComponentCreationOperation appOp = new AppClientComponentCreationOperation(model);
		appOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static void createRarComponent(ConnectorComponentCreationDataModel model, IProject earProject)
		throws Exception {
		if (earProject != null) {
			model.setBooleanProperty(ConnectorComponentCreationDataModel.ADD_TO_EAR, true);
			model.setProperty(ConnectorComponentCreationDataModel.EAR_MODULE_NAME, earProject.getName());
		}
		ConnectorComponentCreationOperation rarOp = new ConnectorComponentCreationOperation(model);
		rarOp.run(null);
		ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
	}

	public static IProject createEJBComponent(String earName, String ejbName, int j2eeEARVersion, int j2eeEJBVersion)
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
			IProject ejbp = createEJBComponent(ejbDataModel, model.getTargetProject());

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

	public AbstractJ2EEComponentCreationTest() {
		super();
	}

}
