package org.eclipse.jst.j2ee.flexible.project.fvtests;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.FlexibileWebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.FlexibileWebModuleCreationOperation;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;

public abstract class AbstractModuleCreationTest extends TestCase {
	protected static final String ILLEGAL_PROJECT_NAME_MESSAGE = "Illegal project name: ";
	protected static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected exception";
	protected static final String TEST_FAILED_MESSAGE = "Test fails Exception should of been trown";
	protected static final String MANIFEST_CLASS_NAME = "Junit_Test_Dummy_Class";
	protected static final String MANIFEST_WRITE_ERROR = "Could not write to manifest test failed";
	protected static final String MANIFEST_LOCK_ERROR = "Manifest IO error - File could be locked";
	protected static final String MANIFEST_CORE_ERROR = "Java core error";

	
	public static final int APPLICATION_CLIENT_MODULE = 0;
	public static final int WEB_MODULE = 1;
	public static final int EJB_MODULE = 2;
	public static final int EAR_MODULE = 3;
//	public IProject ejbproject;
//	public IProject earproject;
	protected String projectName = null;
	
	public AbstractModuleCreationTest(String name) {
		super(name);
	}
	
	public AbstractModuleCreationTest() {
		super();
	}	


	public   FlexibleJ2EEModuleCreationDataModel setupWebModule(int j2eeVersion) throws Exception {
		projectName = getWebModuleProjectName();
		IProject javaProject = ProjectUtility.getProject(projectName);
		
		FlexibileWebModuleCreationDataModel model = new FlexibileWebModuleCreationDataModel();
		
		model.setProperty( FlexibileWebModuleCreationDataModel.PROJECT_NAME, javaProject.getName());
		model.setIntProperty(FlexibileWebModuleCreationDataModel.J2EE_MODULE_VERSION, j2eeVersion);
		
		createWebModule(model, null);
		return model;
	}

	public static void createWebModule(FlexibileWebModuleCreationDataModel model, IProject earProject) throws Exception {
//		if (earProject != null) {
//			model.setBooleanProperty(WebModuleCreationDataModel.ADD_TO_EAR, true);
//			model.setProperty(WebModuleCreationDataModel.EAR_PROJECT_NAME, earProject.getName());
//		}
		
		FlexibileWebModuleCreationOperation webOp = new FlexibileWebModuleCreationOperation(model);
		webOp.run(null);
		
		//ProjectUtility.verifyProject(model.getTargetProject().getName(), true);
		//TaskViewUtility.verifyNoErrors();
	}

	public String getWebModuleProjectName() {
		return "Flexibile"; //$NON-NLS-1$
	}
}
