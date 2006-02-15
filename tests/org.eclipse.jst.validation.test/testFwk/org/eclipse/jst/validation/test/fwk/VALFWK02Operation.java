package org.eclipse.jst.validation.test.fwk;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.IOperationRunnable;
import org.eclipse.jst.validation.test.ValidationTypeEnum;
import org.eclipse.jst.validation.test.fwk.validator.JDTUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.internal.util.BVTRunner;
import org.eclipse.jst.validation.test.setup.IBuffer;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.GlobalConfiguration;
import org.eclipse.wst.validation.internal.ProjectConfiguration;
import org.eclipse.wst.validation.internal.TaskListUtility;
import org.eclipse.wst.validation.internal.ValidationConfiguration;
import org.eclipse.wst.validation.internal.ValidationRegistryReader;
import org.eclipse.wst.validation.internal.ValidatorMetaData;
import org.eclipse.wst.validation.internal.operations.EnabledValidatorsOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;
import org.eclipse.wst.validation.internal.plugin.ValidationPlugin;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * This class runs the VALFWK02 test.
 */
public final class VALFWK02Operation implements IOperationRunnable {
	private IProject _project = null;
	private IBuffer _buffer = null;
	private String _testCaseName = null;
	public static String NEWLINE = System.getProperty("line.separator"); //$NON-NLS-1$
	
	/**
	 * Must have a public default constructor in order to createExecutableExtension.
	 * MUST call setBuffer, setProject, and setName before using the operation.
	 */
	public VALFWK02Operation() {
	}
	
	/**
	 * IProject must exist and be open.
	 */
	public VALFWK02Operation(IBuffer buffer, IProject project, String testCaseName) {
		setProject(project);
		setBuffer(buffer);
		setName(testCaseName);
	}

	public IBuffer getBuffer() { 
		return _buffer;
	}
	
	public void setBuffer(IBuffer b) {
		_buffer = b;
	}
	
	public IProject getProject() {
		return _project;
	}
	
	public void setProject(IProject p) {
		_project = p;
	}

	public String getName() {
		return _testCaseName;
	}
	
	public void setName(String name) {
		_testCaseName = name;
	}	

	public void run(IProgressMonitor monitor) {
		IProject project = getProject();
		if(project == null) {
			throw new IllegalArgumentException("Run VALFWK02 on an existing open IProject."); //$NON-NLS-1$
		}
		
		// First, add the ValidationBuilder to the Java Project
		ValidatorManager.addProjectBuildValidationSupport(project);

		// Save the old user configuration and restore it in the finally block.
		try {
			GlobalConfiguration gconf = ConfigurationManager.getManager().getGlobalConfiguration();
			ProjectConfiguration pconf = ConfigurationManager.getManager().getProjectConfiguration(project);
			GlobalConfiguration origGconf = new GlobalConfiguration(gconf); // copy the original values so that they can be restored in the finally block
			ProjectConfiguration origPconf = new ProjectConfiguration(pconf); // copy the original valuse so that they can be restored in the finally block
			Level level = ValidationPlugin.getPlugin().getMsgLogger().getLevel();
			boolean autoBuildEnabled = ResourcesPlugin.getWorkspace().isAutoBuilding();
				
			ValidatorMetaData[] configuredValidators = pconf.getValidators();
			if(configuredValidators.length < 2) {
				monitor.subTask("Run VALFWK02 on an existing open project that has at least two validators configured: one to enable, and one to disable."); //$NON-NLS-1$
				return;
			}

			// Disable at least one validator for this test.
			// Remove the first enabled validator from the Properties, and the second enabled validator from the
			// Preferences. (It doesn't matter which validator is removed; it only matters that the Properties and
			// the Preferences have different validators.)
			ValidatorMetaData vfTestValidator = ValidationRegistryReader.getReader().getValidatorMetaData(TestOpConstrInputOperation.FWK_NOBUILD_TEST_VALIDATOR_CLASS); //$NON-NLS-1$
			ValidatorMetaData vfNoBuildTestValidator = ValidationRegistryReader.getReader().getValidatorMetaData(TestOpConstrInputOperation.FWK_TEST_VALIDATOR_CLASS); //$NON-NLS-1$
			ValidatorMetaData propertiesValidator = ValidationRegistryReader.getReader().getValidatorMetaData(TestOpConstrInputOperation.PROPERTIES_VALIDATOR_CLASS); //$NON-NLS-1$
			ValidatorMetaData[] enabledPropValidators = new ValidatorMetaData[2];
			enabledPropValidators[0] = vfTestValidator;
			enabledPropValidators[1] = vfNoBuildTestValidator;
			pconf.setEnabledValidators(enabledPropValidators);
			//pconf.setMaximumNumberOfMessages(1);
			pconf.passivate();

			ValidatorMetaData[] enabledPrefValidators = new ValidatorMetaData[1];
			enabledPrefValidators[0] = propertiesValidator;
			gconf.setEnabledValidators(enabledPrefValidators);
			//gconf.setMaximumNumberOfMessages(10);
			gconf.passivate();
			
			
			ValidatorTestcase[] tmds = JDTUtility.getVFTests(monitor, getProject());
			if((tmds == null) || (tmds.length == 0)) {
				monitor.subTask("Cannot run VALFWK02 because there are no test cases registered for JavaProject."); //$NON-NLS-1$
				return;
			}
			
			ValidatorTestcase fwkNobuildTestTMD = null;
			ValidatorTestcase fwkTestTMD = null;
			ValidatorTestcase propTMD = null;
			for(int i=0; i<tmds.length; i++) {
				ValidatorTestcase tmd = tmds[i];
				if(tmd.getValidatorClass().equals(TestOpConstrInputOperation.FWK_TEST_VALIDATOR_CLASS)) {
					fwkTestTMD = tmd;
				}
				else if(tmd.getValidatorClass().equals(TestOpConstrInputOperation.PROPERTIES_VALIDATOR_CLASS)) {
					propTMD = tmd;
				}
				else if(tmd.getValidatorClass().equals(TestOpConstrInputOperation.FWK_NOBUILD_TEST_VALIDATOR_CLASS)) {
					fwkNobuildTestTMD = tmd;
				}
				
				if((fwkTestTMD != null) && (propTMD != null) && (fwkNobuildTestTMD != null)) {
					break;
				}
			}
			
			if((fwkTestTMD == null) || (propTMD == null) || (fwkNobuildTestTMD == null)) {
				// Can't run the tests
				monitor.subTask("Cannot run VALFWK02 because the test case is missing for either the VF Test Validator, Properties Validator, or both."); //$NON-NLS-1$
				return;
			}
			
			try {
				// Set level to FINEST so that the launch validators are accumulated in ValidationOperation.
				// (See ValidationOperation::getLaunchedValidators())
				ValidationPlugin.getPlugin().getMsgLogger().setLevel(Level.FINEST); 
				
				getBuffer().write("testPropNotOverride"); //$NON-NLS-1$
				int allowPass = testPropNotOverride(monitor, project, gconf, pconf, propTMD);
				getBuffer().write(NEWLINE); //$NON-NLS-1$
				getBuffer().write("testPropOverride"); //$NON-NLS-1$
				int propPass = testPropOverride(monitor, project, gconf, pconf, fwkTestTMD);
				getBuffer().write(NEWLINE); //$NON-NLS-1$
				getBuffer().write("testPrefNotAllowOverride"); //$NON-NLS-1$
				int notAllowPass = testPrefNotAllowOverride(monitor, project, gconf, pconf, propTMD);
				StringBuffer result = new StringBuffer(NEWLINE);
				result.append("Properties Doesn't Override: "); //$NON-NLS-1$
				result.append(allowPass);
				result.append(" of 7 pass."); //$NON-NLS-1$
				result.append(NEWLINE);
				result.append("Properites Overrides: "); //$NON-NLS-1$
				result.append(propPass);
				result.append(" of 7 pass."); //$NON-NLS-1$
				result.append(NEWLINE);
				result.append("Preference Doesn't Allow Override: "); //$NON-NLS-1$
				result.append(notAllowPass);
				result.append(" of 7 pass."); //$NON-NLS-1$
				result.append(NEWLINE);
				monitor.subTask(result.toString());
				getBuffer().write(NEWLINE); //$NON-NLS-1$
				getBuffer().write(result.toString());
			}
			finally {
				gconf = origGconf;
				gconf.passivate();
				
				pconf = origPconf;
				pconf.passivate();
				
				JDTUtility.setAutoBuild(autoBuildEnabled);
	
				ValidationPlugin.getPlugin().getMsgLogger().setLevel(level);
			}
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
	
	private static void debug(IBuffer buffer, String title, ValidatorMetaData[] enabledValidators) {
		buffer.write("Contents of " + title); //$NON-NLS-1$
		for(int i=0; i<enabledValidators.length; i++) {
			ValidatorMetaData vmd = enabledValidators[i];
			buffer.write("\t" + vmd.getValidatorDisplayName()); //$NON-NLS-1$
		}
		buffer.write("End contents of " + title); //$NON-NLS-1$
		buffer.write(NEWLINE); //$NON-NLS-1$
	}
	
	
	private int testPropNotOverride(final IProgressMonitor monitor, final IProject project, GlobalConfiguration gconf, ProjectConfiguration pconf, ValidatorTestcase tmd) {
		int numPass = 0;
		try {
			// Set up the user configuration for the test. The run(IProgressMonitor) method
			// will set the values back to what they were before the test.		
			gconf.setCanProjectsOverride(true);
			gconf.passivate();
			pconf.setDoesProjectOverride(false);
			pconf.passivate();
			
			ValidatorManager.getManager().updateTaskList(project); // Update the task list because the preference and properties page do, so the configuration classes don't.
			
			debug(getBuffer(), "Enabled Properties Validators", pconf.getEnabledValidators()); //$NON-NLS-1$
			debug(getBuffer(), "Enabled Preference Validators", gconf.getEnabledValidators()); //$NON-NLS-1$
	
			numPass = validate(monitor, project, gconf, tmd, "testPropNotOverride"); //$NON-NLS-1$
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
			numPass = 0;
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			numPass = 0;
		}
		
			return numPass;
	}
	
	
	private int testPropOverride(final IProgressMonitor monitor, final IProject project, GlobalConfiguration gconf, ProjectConfiguration pconf, ValidatorTestcase tmd) {
		int numPass = 0;
		try {
			// Set up the user configuration for the test. The run(IProgressMonitor) method
			// will set the values back to what they were before the test.		
			gconf.setCanProjectsOverride(true);
			gconf.passivate();
			pconf.setDoesProjectOverride(true);
			pconf.passivate();

			ValidatorManager.getManager().updateTaskList(project); // Update the task list because the preference and properties page do, so the configuration classes don't.
			
			debug(getBuffer(), "Enabled Properties Validators", pconf.getEnabledValidators()); //$NON-NLS-1$
			debug(getBuffer(), "Enabled Preference Validators", gconf.getEnabledValidators()); //$NON-NLS-1$
	
			numPass = validate(monitor, project, pconf, tmd, "testPropOverride"); //$NON-NLS-1$
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
		return numPass;
	}
	
	private int testPrefNotAllowOverride(final IProgressMonitor monitor, final IProject project, GlobalConfiguration gconf, ProjectConfiguration pconf, ValidatorTestcase tmd) {
		int numPass = 0;
		try {
			// Set up the user configuration for the test. The run(IProgressMonitor) method
			// will set the values back to what they were before the test.		
			gconf.setCanProjectsOverride(false);
			gconf.passivate();
			pconf.setDoesProjectOverride(true);
			pconf.passivate();

			ValidatorManager.getManager().updateTaskList(project); // Update the task list because the preference and properties page do, so the configuration classes don't.
			
			debug(getBuffer(), "Enabled Properties Validators", pconf.getEnabledValidators()); //$NON-NLS-1$
			debug(getBuffer(), "Enabled Preference Validators", gconf.getEnabledValidators()); //$NON-NLS-1$
	
			numPass = validate(monitor, project, gconf, tmd, "testPrefNotAllowOverride"); //$NON-NLS-1$
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
		return numPass;
	}
	
	private int validate(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf, ValidatorTestcase tmd, String testName) {
		int numPass = 0;
		boolean successful = true; // Assume that all of the test cases will pass.
		try {
			// For the first four tests, need all of the messages to be reported
			//int originalLimit = vconf.getMaximumNumberOfMessages();
			//vconf.setMaximumNumberOfMessages(WorkbenchReporter.NO_MESSAGE_LIMIT);
			vconf.passivate();
			
			boolean pass = runValidation(monitor, project, vconf);
			if(!pass) {
//				getBuffer().write(testName + "::runValidation failed"); //$NON-NLS-1$
				successful = false;
			}
			else {
				numPass++;
//				getBuffer().write(testName + "::runValidation passed"); //$NON-NLS-1$
			}
			getBuffer().addExecutionStatus(getName(), "::" + testName + "runValidation", pass); //$NON-NLS-1$ //$NON-NLS-2$
			
			pass = fullBuildValidate(monitor, project, vconf, tmd);
			if(!pass) {
//				getBuffer().write(testName + "::fullBuildValidation failed"); //$NON-NLS-1$
				successful = false;
			}
			else {
				numPass++;
//				getBuffer().write(testName + "::fullBuildValidation passed"); //$NON-NLS-1$
			}
			getBuffer().addExecutionStatus(getName(), "::" + testName + "fullBuildValidation", pass); //$NON-NLS-1$ //$NON-NLS-2$
			
			pass = autoValidateEnabled(monitor, project, vconf, tmd);
			if(!pass) {
				successful = false;
//				getBuffer().write(testName + "::autoValidateEnabled failed"); //$NON-NLS-1$
			}
			else {
				numPass++;
//				getBuffer().write(testName + "::autoValidateEnabled passed"); //$NON-NLS-1$
			}
			getBuffer().addExecutionStatus(getName(), "::" + testName + "autoValidateEnabled", pass); //$NON-NLS-1$ //$NON-NLS-2$
			
			pass = autoValidateDisabled(monitor, project, vconf, tmd);
			if(!pass) {
//				getBuffer().write(testName + "::autoValidateDisabled failed"); //$NON-NLS-1$
				successful = false;
			}
			else {
				numPass++;
//				getBuffer().write(testName + "::autoValidateDisabled passed"); //$NON-NLS-1$
			}
			getBuffer().addExecutionStatus(getName(), "::" + testName + "autoValidateDisabled", pass); //$NON-NLS-1$ //$NON-NLS-2$
			
			pass = buildValidateEnabled(monitor, project, vconf, tmd);
			if(!pass) {
//				getBuffer().write(testName + "::buildValidateEnabled failed"); //$NON-NLS-1$
				successful = false;
			}
			else {
				numPass++;
//				getBuffer().write(testName + "::buildValidateEnabled passed"); //$NON-NLS-1$
			}
			getBuffer().addExecutionStatus(getName(), "::" + testName + "buildValidateEnabled", pass); //$NON-NLS-1$ //$NON-NLS-2$

			pass = buildValidateDisabled(monitor, project, vconf, tmd);
			if(!pass) {
//				getBuffer().write(testName + "::buildValidateDisabled failed"); //$NON-NLS-1$
				successful = false;
			}
			else {
				numPass++;
//				getBuffer().write(testName + "::buildValidateDisabled passed"); //$NON-NLS-1$
			}
			getBuffer().addExecutionStatus(getName(), "::" + testName + "buildValidateDisabled", pass); //$NON-NLS-1$ //$NON-NLS-2$

			// And for this test, the original limit needs to be enforced
			//vconf.setMaximumNumberOfMessages(originalLimit);
			vconf.passivate();

			pass = maxMessagesReported(monitor, project, vconf);
			if(!pass) {
				successful = false;
//				getBuffer().write(testName + "::maxMessagesReported failed"); //$NON-NLS-1$
			}
			else {
				numPass++;
//				getBuffer().write(testName + "::maxMessagesReported passed"); //$NON-NLS-1$
			}
			getBuffer().addExecutionStatus(getName(), "::" + testName + "maxMessagesReported", pass); //$NON-NLS-1$ //$NON-NLS-2$
		}
		catch(InvocationTargetException exc) {
			successful = false;
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
		}
		finally {
			getBuffer().addExecutionStatus(getName(), testName, successful); //$NON-NLS-1$
		}
		return numPass;
	}
	
	private boolean runValidation(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf) {
		boolean passed = true;
		try {
			// Unlike the other validations, which rely on a build, this method has access to the
			// operation and can query it for the list of validators that were launched. This technique
			// is better because it doesn't rely on the validator producing the correct output (markers).
			// VALFWK02 just wants to check that the correct validators were launched; the TestOpConstr
			// test wants to check the result given different output and constructor of the Operation.
			
			EnabledValidatorsOperation validOp = new EnabledValidatorsOperation(project,false);
			ResourcesPlugin.getWorkspace().run(validOp, monitor);
			// Launched validators should be configured, enabled, and had files to validate on the project.
			Set launchedValidators = validOp.getLaunchedValidators();
			
			// Since a full validation was run, the launched validators should be equivalent
			// to the configured enabled valdiators.
			ValidatorMetaData[] enabledValidators = vconf.getEnabledValidators();
			for(int i=0; i<enabledValidators.length; i++) {
				ValidatorMetaData vmd = enabledValidators[i];
				
				// If it's configured on the project, ensure that the enabled validator was launched.
				// (May not be configured if the enabled validators is the list of Preference enabled
				// validators.)
				if(!launchedValidators.contains(vmd)) {
					getBuffer().write("ERROR: Expected validator " + vmd.getValidatorDisplayName() + " was not run on project " + project.getName() + ". Failing test."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					passed = false;
				}
				else {
					getBuffer().write("INFO: Expected validator " + vmd.getValidatorDisplayName() + " ran as expected on project " + project.getName() + "."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
			
			if(passed == false) {
				getBuffer().write("launched validators are the following:"); //$NON-NLS-1$
				Iterator iterator = launchedValidators.iterator();
				while(iterator.hasNext()) {
					ValidatorMetaData vmd = (ValidatorMetaData)iterator.next();
					getBuffer().write("\t" + vmd.getValidatorDisplayName()); //$NON-NLS-1$
				}
				getBuffer().write("end of launched validators"); //$NON-NLS-1$
			}
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			passed = false;
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
		}
		
		
		return passed;
	}
	
	private boolean fullBuildValidate(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf, ValidatorTestcase tmd) {
		try {
			//vconf.setBuildValidate(true);
			vconf.passivate();

			TaskListUtility.removeAllTasks(project);
			IResource[] changedResources = tmd.getResources(project);;
			TestOpConstrInputOperation.touch(project, changedResources);
			
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
			IMarker[] messagesAfterBuild = TaskListUtility.getValidationTasks(project, IMessage.ALL_MESSAGES);
			
			return BVTRunner.singleton().compare(getBuffer(), getName(), tmd.getName(), tmd.getMessages(ValidationTypeEnum.FULL_VALIDATION), messagesAfterBuild);
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return false;
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
			return false;
		}
	}
	
	private boolean autoValidateEnabled(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf, ValidatorTestcase tmd) {
		JDTUtility.setAutoBuild(true);
		
		try {
			//vconf.setAutoValidate(true);
			vconf.passivate();
			
			TaskListUtility.removeAllTasks(project);
			IResource[] changedResources = tmd.getResources(project);;
			TestOpConstrInputOperation.touch(project, changedResources);
			
			project.build(IncrementalProjectBuilder.AUTO_BUILD, monitor);
			IMarker[] messagesAfterBuild = TaskListUtility.getValidationTasks(project, IMessage.ALL_MESSAGES);
			
			return BVTRunner.singleton().compare(getBuffer(), getName(), tmd.getName(), tmd.getMessages(ValidationTypeEnum.AUTO_VALIDATION), messagesAfterBuild);
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return false;
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
			return false;
		}
	}
	
	private boolean autoValidateDisabled(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf, ValidatorTestcase tmd) {
		JDTUtility.setAutoBuild(true);

		try {		
			//vconf.setAutoValidate(false);
			vconf.passivate();

			TaskListUtility.removeAllTasks(project);
			IResource[] changedResources = tmd.getResources(project);
			TestOpConstrInputOperation.touch(project, changedResources);
		
			project.build(IncrementalProjectBuilder.AUTO_BUILD, monitor);
			IMarker[] messagesAfterBuild = TaskListUtility.getValidationTasks(project, IMessage.ALL_MESSAGES);
			
			// Pass in an empty list because the validation isn't supposed to run.
			return BVTRunner.singleton().compare(getBuffer(), getName(), tmd.getName(), Collections.EMPTY_LIST, messagesAfterBuild);
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return false;
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
			return false;
		}
	}
	
	private boolean buildValidateEnabled(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf, ValidatorTestcase tmd) {
		try {
			//vconf.setBuildValidate(true);
			vconf.passivate();
			JDTUtility.setAutoBuild(false);
	
			// Construct the input
			TaskListUtility.removeAllTasks(project);
			IResource[] changedResources = tmd.getResources(project);
			TestOpConstrInputOperation.touch(project, changedResources);
			try {		
				project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
			}
			catch(CoreException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
				return false;
			}
			IMarker[] messagesAfterBuild = TaskListUtility.getValidationTasks(project, IMessage.ALL_MESSAGES);
			
			return BVTRunner.singleton().compare(getBuffer(), getName(), tmd.getName(), tmd.getMessages(ValidationTypeEnum.INCREMENTAL_VALIDATION), messagesAfterBuild);
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
			return false;
		}
	}
	
	private boolean buildValidateDisabled(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf, ValidatorTestcase tmd) {
		try {
			//vconf.setBuildValidate(false);
			vconf.passivate();
			JDTUtility.setAutoBuild(false);
			
			// Construct the input
			TaskListUtility.removeAllTasks(project);
			IResource[] changedResources = tmd.getResources(project);
			TestOpConstrInputOperation.touch(project, changedResources);
			try {		
				project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
				IMarker[] messagesAfterBuild = TaskListUtility.getValidationTasks(project, IMessage.ALL_MESSAGES);

				// Pass in an empty list because validation isn't supposed to run.				
				return BVTRunner.singleton().compare(getBuffer(), getName(), tmd.getName(), Collections.EMPTY_LIST, messagesAfterBuild);
			}
			catch(CoreException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
				return false;
			}
		}
		catch(InvocationTargetException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
			return false;
		}
	}
	
	private boolean maxMessagesReported(IProgressMonitor monitor, IProject project, ValidationConfiguration vconf) {
			EnabledValidatorsOperation validOp = null;
			try {
				validOp = new EnabledValidatorsOperation(project,false);
				ResourcesPlugin.getWorkspace().run(validOp, monitor);
			}
			catch(CoreException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
				return false;
			}
			
			IMarker[] messages = TaskListUtility.getValidationTasks(project, IMessage.ALL_MESSAGES);
			//if(messages.length <= vconf.getMaximumNumberOfMessages()+1) { // add one for the IWAD3000 message, i.e., "Validation was terminated because the maximum number of messages ..."
			//	return true;
			//}
			
			return false;
		}
		
}


