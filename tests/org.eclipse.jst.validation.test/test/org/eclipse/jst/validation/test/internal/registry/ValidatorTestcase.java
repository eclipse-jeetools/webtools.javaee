package org.eclipse.jst.validation.test.internal.registry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationException;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.ValidationTypeEnum;
import org.eclipse.jst.validation.test.internal.util.BVTRunner;
import org.eclipse.jst.validation.test.setup.IBuffer;
import org.eclipse.wst.validation.core.IFileDelta;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.GlobalConfiguration;
import org.eclipse.wst.validation.internal.TaskListUtility;
import org.eclipse.wst.validation.internal.VThreadManager;
import org.eclipse.wst.validation.internal.ValidatorMetaData;
import org.eclipse.wst.validation.internal.operations.ValidatorSubsetOperation;

public class ValidatorTestcase implements ITestcaseMetaData {
	private String _pluginId = null; // The plugin id that has contributed this test case.
	private ValidatorMetaData _vmd = null;
	private MessageMetaData[] _messages = null;
	private String _projectName = null; // the name of the project that this test case tests
	private String[] _resourceNames = null; // the resources listed in the MessageMetaData of this test case.
	private String _inputFileName = null;
	private String _name = null; // the name of the test case
	private boolean _visible = true; // Is this test case visible on the Test Collector menu
	
	public ValidatorTestcase(String pluginName, String project, ValidatorMetaData vmd, String inputFileName, boolean visible) {
		_pluginId = pluginName;
		_projectName = project;
		_vmd = vmd;
		_inputFileName = inputFileName;
		_visible = visible;
	}
	
	public boolean isVisible() {
		return _visible;
	}
	
	public String getDeclaringPluginId() {
		return _pluginId;
	}
	
	public String getProjectName() {
		return _projectName;
	}
	
	public IProject getProject() {
		return TestcaseUtility.findProject(this);
	}
	
	public String getInputFileName() {
		return _inputFileName;
	}
	
	/**
	 * Return the number of messages
	 */
	public int getNumMessages() {
		return getMessages(ValidationTypeEnum.RUN_VALIDATION).size();
	}
	
	/**
	 * Return the list of messages that should be reported for the given build
	 * type. (e.g. if the validator does not support incremental validation,
	 * then instead of returning the list of expected messages, return an empty 
	 * list.)
	 */
	public List getMessages(int buildType) {
		// The messages need to be stored in a list instead of an array because
		// the list is sorted before searching & displaying.
		switch(buildType) {
			case(ValidationTypeEnum.AUTO_VALIDATION):
			case(ValidationTypeEnum.INCREMENTAL_VALIDATION): {
				if(!getValidatorMetaData().isIncremental()) {
					return Collections.EMPTY_LIST;
				}
				// Otherwise, return the default copy below.
				break;
			}

			case(ValidationTypeEnum.FULL_VALIDATION): {
				if(!getValidatorMetaData().isFullBuild()) {
					return Collections.EMPTY_LIST;
				}
				// Otherwise, return the default copy below.
				break;
			}

			case(ValidationTypeEnum.RUN_VALIDATION):
			default: {
				// Return the default copy below.
			}
			
		}
		
		List copy = new ArrayList();
		for(int i=0; i<_messages.length; i++) {
			copy.add(_messages[i]);
		}
		
		return copy;
	}
	
	// Return the resources in this project for which there is a message in this test case.
	// No resource instance will be in the resource more than once.
	public IResource[] getResources(IProject project) {
		Set temp = new HashSet(); // use a set in case there is more than one message registered against a resource (don't want duplicates in the list).
		String[] resourceNames = getResourceNames();
		for(int i=0; i<resourceNames.length; i++) {
			String resourceName = resourceNames[i];
			IResource resource = project.findMember(resourceName);
			if(resource != null) {
				// resource exists
				temp.add(resource);
			}
		}
		
		IResource[] result = new IResource[temp.size()];
		temp.toArray(result);
		return result;
	}
	
	public String[] getResourceNames() {
		if(_resourceNames == null) {
			_resourceNames = new String[_messages.length];
			for(int i=0; i<_messages.length; i++) {
				_resourceNames[i] = _messages[i].getResource();
			}
		}
		return _resourceNames;
	}
	
	/**
	 * When an empty TMD is used to test an operation, and the operation needs the IResource[]
	 * affected by the TMD to know whether or not the operation passes (i.e., one of the ValidatorSubsetOperation
	 * constructors takes an IResource[], and the IResource[] must not be empty or null), then
	 * this method is used to set the "resources" affected by the test case.
	 */
	public void setResourceNames(String[] resourceNames) {
		_resourceNames = resourceNames;
	}
	
	public void setMessages(MessageMetaData[] messages) {
		// If messages are null, that means that the test case expects no validation errors.
		_messages = ((messages == null) ? new MessageMetaData[0] : messages);
	}
	
	public ValidatorMetaData getValidatorMetaData() {
		return _vmd;
	}
	
	public String getValidatorClass() {
		return getValidatorMetaData().getValidatorUniqueName();
	}

	public String getName() {
		if(_name == null) {
			_name = _vmd.getValidatorDisplayName() + "::" + getProjectName(); //$NON-NLS-1$
		}
		return _name;
	}	
	
	public void run(IBuffer buffer, IProject project) {
		String status = ">>>>>RUN VALIDATION TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$			
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		buffer.addExecutionStatus(getName(), ValidationTypeEnum.RUN_VALIDATION_NAME, validate(buffer, project, ValidationTypeEnum.RUN_VALIDATION, ValidationTypeEnum.RUN_VALIDATION_NAME));
		status = ">>>>>END RUN VALIDATION TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		
		status = ">>>>>FULL BUILD TEST PASS [" + project.getName() + "]>>>>>";			//$NON-NLS-1$ //$NON-NLS-2$		
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		buffer.addExecutionStatus(getName(), ValidationTypeEnum.FULL_VALIDATION_NAME, validate(buffer, project, ValidationTypeEnum.FULL_VALIDATION, ValidationTypeEnum.FULL_VALIDATION_NAME));
		status = ">>>>>END FULL BUILD TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		
		status = ">>>>>INCREMENTAL BUILD TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		buffer.addExecutionStatus(getName(), ValidationTypeEnum.INCREMENTAL_VALIDATION_NAME, validate(buffer, project, ValidationTypeEnum.INCREMENTAL_VALIDATION, ValidationTypeEnum.INCREMENTAL_VALIDATION_NAME));
		status = ">>>>>END INCREMENTAL BUILD TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		
		status = ">>>>>AUTO BUILD TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		buffer.addExecutionStatus(getName(), ValidationTypeEnum.AUTO_VALIDATION_NAME, validate(buffer, project, ValidationTypeEnum.AUTO_VALIDATION, ValidationTypeEnum.AUTO_VALIDATION_NAME));
		status = ">>>>>END AUTO BUILD TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
		buffer.getProgressMonitor().subTask(status);
		buffer.write(status);
		
		if(getValidatorMetaData().isAsync()) {
			status = ">>>>>ASYNCHRONOUS VALIDATION TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
			buffer.getProgressMonitor().subTask(status);
			buffer.write(status);
			buffer.addExecutionStatus(getName(), ValidationTypeEnum.ASYNC_NAME, asyncValidate(buffer, project, ValidationTypeEnum.ASYNC_NAME));
			status = ">>>>>END ASYNCHRONOUS VALIDATION TEST PASS [" + project.getName() + "]>>>>>";	//$NON-NLS-1$ //$NON-NLS-2$
			buffer.getProgressMonitor().subTask(status);
			buffer.write(status);
		}
		else {
			// By default, "pass" the asynchrous tests of any validator that cannot run asynchronous tests.
			buffer.addExecutionStatus(getName(), ValidationTypeEnum.ASYNC_NAME, true);
		}
	}
	
	private boolean validate(IBuffer buffer, IProject project, int validationTypeEnum, String taskName) {
		long start = System.currentTimeMillis();
		boolean passed = true;
		try {
			passed = BVTRunner.singleton().test(buffer, project, this, validationTypeEnum);
		}
		catch(BVTValidationException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
				if(exc.getTargetException() != null) {
					logger.write(Level.SEVERE, exc.getTargetException());
				}
			}
		}
		finally {
			long end = System.currentTimeMillis();
			buffer.addElapsedTime(taskName, (end - start));
			buffer.write("Total time for validator " + getValidatorMetaData().getValidatorDisplayName() + " of project " + project.getName() + " in " + taskName + " mode was " + (end - start) + " milliseconds."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
		return passed;
	}
	
	private boolean asyncValidate(IBuffer buffer, IProject project, String subTaskName) {
		boolean passed = true;
		long start = System.currentTimeMillis();
		long end = 0;
		try {
			GlobalConfiguration gconf = ConfigurationManager.getManager().getGlobalConfiguration();
			boolean autoValidate = gconf.isAutoValidate();
			boolean buildValidate = gconf.isBuildValidate();
			try {
				// Turn off auto-validate so that the project isn't validated automatically.
				gconf.setAutoValidate(false);
				gconf.setBuildValidate(false);
				gconf.passivate();
				
				// Two tests: 
				//   1. Run validation on copies of the original project: "projectName.fork" and "projectName.noFork"
				//      Compare the markers of the copies to the FVT TestcaseMetaData, and the validator passes if the 
				//		results of each copy matches the TestcaseMetaData.
				//   2. Run validation on copies, but this time introduce changes during the validation.
				//		Extract the names of resources from the testcase metadata, find those resources, and
				//		add/delete/rename/move/close/change them.
				
				boolean pass1 = validateAndCompare(buffer, project, subTaskName);
				boolean pass2 = validateChangeAndCompare(buffer, project, IFileDelta.CHANGED, subTaskName);
				boolean pass3 = validateChangeAndCompare(buffer, project, IFileDelta.DELETED, subTaskName);
				passed = (pass1 && pass2 && pass3);
			}
			catch(CoreException exc) {
				// Couldn't create copy.Just continue with the next test.
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
			catch(BVTValidationException exc) {
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
			finally {
				gconf.setAutoValidate(autoValidate);
				gconf.setBuildValidate(buildValidate);
				gconf.passivate();
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
		finally {
			end = System.currentTimeMillis();
			buffer.addElapsedTime(ValidationTypeEnum.ASYNC_NAME, (end - start));
			buffer.write("Total time for validator " + getValidatorMetaData().getValidatorDisplayName() + " of project " + project.getName() + " in asynchronous mode was " + (end - start) + " milliseconds."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			
		}
		return passed;
	}

	/**
	 * Run validation, using several operations (both forked and not forked), on the IProject.
	 * Return true if the result, after the last operation is complete, is the expected result.
	 */
	private boolean validateAndCompare(IBuffer buffer, IProject project, String subTaskName) throws CoreException, BVTValidationException {
		// Now compare the IProject's result to the expected TestcaseMetaData result.
		// Validate
		ValidatorSubsetOperation noForkOp = new ValidatorSubsetOperation(project, true, false);
		noForkOp.setValidators(getValidatorMetaData().getValidatorNames());
		noForkOp.run(buffer.getProgressMonitor());
		
		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOp = new ValidatorSubsetOperation(project, true, true);
		forkOp.setValidators(getValidatorMetaData().getValidatorNames());
		forkOp.run(buffer.getProgressMonitor());
		
		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation noForkOp2 = new ValidatorSubsetOperation(project, true, false);
		noForkOp2.setValidators(getValidatorMetaData().getValidatorNames());
		noForkOp2.run(buffer.getProgressMonitor());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOp2 = new ValidatorSubsetOperation(project, true, true);
		forkOp2.setValidators(getValidatorMetaData().getValidatorNames());
		forkOp2.run(buffer.getProgressMonitor());
		
		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOp3 = new ValidatorSubsetOperation(project, true, true);
		forkOp3.setValidators(getValidatorMetaData().getValidatorNames());
		forkOp3.run(buffer.getProgressMonitor());
		
		// Wait until all of the threads have complete.
		buffer.getProgressMonitor().subTask("Waiting for all forked threads to finish..."); //$NON-NLS-1$
		while(!VThreadManager.getManager().isDone() && !buffer.getProgressMonitor().isCanceled()) {}
		buffer.getProgressMonitor().subTask("All threads are complete. Beginning the comparison."); //$NON-NLS-1$
		
		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		// Compare
		IMarker[] markers = TaskListUtility.getValidationTasks(project, getValidatorMetaData().getValidatorNames());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		return BVTRunner.singleton().verify(buffer, getName(), subTaskName, project, getMessages(ValidationTypeEnum.RUN_VALIDATION), markers); 
	}

	private boolean validateChangeAndCompare(IBuffer buffer, IProject project, int ifileDeltaType, String subTaskName) throws BVTValidationException, CoreException {
		boolean passed = true;
		
		ValidatorMetaData[] vmds = new ValidatorMetaData[]{getValidatorMetaData()};
		
		ValidatorSubsetOperation noForkOpOrig = new ValidatorSubsetOperation(project, true, false);
		noForkOpOrig.setValidators(getValidatorMetaData().getValidatorNames());
		noForkOpOrig.run(buffer.getProgressMonitor());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation noForkOpChanged = new ValidatorSubsetOperation(project, vmds, getResources(project), ifileDeltaType, false, false); // false = do not force if there's no deltas to validate
		noForkOpChanged.run(buffer.getProgressMonitor());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOpOrig = new ValidatorSubsetOperation(project, true, true);
		forkOpOrig.setValidators(getValidatorMetaData().getValidatorNames());
		forkOpOrig.run(buffer.getProgressMonitor());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOpChanged = new ValidatorSubsetOperation(project, vmds,  getResources(project), ifileDeltaType, false, true); // false = do not force if there's no deltas to validate
		forkOpChanged.run(buffer.getProgressMonitor());
		
		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation noForkOp2Orig = new ValidatorSubsetOperation(project, true, false);
		noForkOp2Orig.setValidators(getValidatorMetaData().getValidatorNames());
		noForkOp2Orig.run(buffer.getProgressMonitor());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation noForkOp2Changed = new ValidatorSubsetOperation(project, vmds,  getResources(project), ifileDeltaType, false, false); // false = do not force if there's no deltas to validate
		noForkOp2Changed.run(buffer.getProgressMonitor());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOp2Orig = new ValidatorSubsetOperation(project, true, true);
		forkOp2Orig.setValidators(getValidatorMetaData().getValidatorNames());
		forkOp2Orig.run(buffer.getProgressMonitor());
		
		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOp2Changed = new ValidatorSubsetOperation(project, vmds,  getResources(project), ifileDeltaType, false, true); // false = do not force if there's no deltas to validate
		forkOp2Changed.run(buffer.getProgressMonitor());

		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOp3Orig = new ValidatorSubsetOperation(project, true, true);
		forkOp3Orig.setValidators(getValidatorMetaData().getValidatorNames());
		forkOp3Orig.run(buffer.getProgressMonitor());
		
		if(buffer.getProgressMonitor().isCanceled()) {
			return false;
		}

		ValidatorSubsetOperation forkOp3Changed = new ValidatorSubsetOperation(project, vmds, getResources(project), ifileDeltaType, false, true); // false = do not force if there's no deltas to validate
		forkOp3Changed.run(buffer.getProgressMonitor());
		
		// Wait until all of the threads have complete.
		buffer.getProgressMonitor().subTask("Waiting for all forked threads to finish..."); //$NON-NLS-1$
		while(!VThreadManager.getManager().isDone() && !buffer.getProgressMonitor().isCanceled()) {}
		if(buffer.getProgressMonitor().isCanceled()) {
			buffer.getProgressMonitor().subTask("Comparison cancelled. Performing cleanup."); //$NON-NLS-1$
			return false;
		}
		buffer.getProgressMonitor().subTask("All threads are complete. Beginning the comparison."); //$NON-NLS-1$
		
		// Compare
		IMarker[] markers = TaskListUtility.getValidationTasks(project, getValidatorMetaData().getValidatorNames());

		// Now compare the IProject's result to the expected TestcaseMetaData result.
		// Don't write _passed = _passed && get...
		// When the _passed == false, then java didn't bother running the test.
		passed = BVTRunner.singleton().verify(buffer, getName(), subTaskName, project, getMessages(ValidationTypeEnum.RUN_VALIDATION), markers);
		
		ResourcesPlugin.getWorkspace().deleteMarkers(markers);
	
		return passed;
		
	}
}
