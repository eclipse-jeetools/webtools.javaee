package org.eclipse.jst.validation.test.fwk;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationException;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.IOperationRunnable;
import org.eclipse.jst.validation.test.ValidationTypeEnum;
import org.eclipse.jst.validation.test.fwk.validator.JDTUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.internal.util.BVTRunner;
import org.eclipse.jst.validation.test.setup.IBuffer;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.GlobalConfiguration;
import org.eclipse.wst.validation.internal.InternalValidatorManager;
import org.eclipse.wst.validation.internal.TaskListUtility;
import org.eclipse.wst.validation.internal.ValidationConfiguration;
import org.eclipse.wst.validation.internal.ValidatorMetaData;
import org.eclipse.wst.validation.internal.operations.AllValidatorsOperation;
import org.eclipse.wst.validation.internal.operations.EnabledIncrementalValidatorsOperation;
import org.eclipse.wst.validation.internal.operations.EnabledValidatorsOperation;
import org.eclipse.wst.validation.internal.operations.IRuleGroup;
import org.eclipse.wst.validation.internal.operations.OneValidatorOperation;
import org.eclipse.wst.validation.internal.operations.ValidationOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorSubsetOperation;
import org.eclipse.wst.validation.internal.operations.WorkbenchReporter;

/**
 * This class tests every constructor available in the ValidationOperation
 * hierarchy and ensures that an operation, created with that constructor, 
 * results in the expected validation IMarkers.
 */
public class TestOpConstrOperation implements IOperationRunnable {
	private IProject _project = null;
	private IBuffer _buffer = null;
	private String _testCaseName = null;
	
	/**
	 * Must have a public default constructor in order to createExecutableExtension.
	 * MUST call setBuffer, setProject, and setName before using the operation.
	 */
	public TestOpConstrOperation() {
	}
	
	/**
	 * IProject must exist and be open.
	 */
	public TestOpConstrOperation(IBuffer buffer, IProject project, String testCaseName) {
		setBuffer(buffer);
		setProject(project);
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
		try {
			GlobalConfiguration gconf = ConfigurationManager.getManager().getGlobalConfiguration();
			GlobalConfiguration origGconf = new GlobalConfiguration(gconf);
			boolean autoBuildEnabled = ResourcesPlugin.getWorkspace().isAutoBuilding();
			try {
				// In order for the operations to work correctly, set the enabled validators to
				// the validators for which tests exist, and turn off auto-build so that a
				// validation is forced.
				gconf.setCanProjectsOverride(false);
				gconf.setAutoValidate(true);
				gconf.setBuildValidate(true);
				gconf.setMaximumNumberOfMessages(WorkbenchReporter.NO_MESSAGE_LIMIT);
				gconf.passivate();
				
				JDTUtility.setAutoBuild(false);
				
				monitor.subTask("Loading operations; please wait..."); //$NON-NLS-1$
				TestWrapper[] testCaseAndOperations = getOperations(monitor, gconf);
				if(testCaseAndOperations == null) {
					String message = "Test case failed; cannot test null operations."; //$NON-NLS-1$
					getBuffer().write(message);
					monitor.subTask(message);
					return;
				}
	
				int numPassed = 0;
				for(int i=0; i<testCaseAndOperations.length; i++) {
					TestWrapper testWrapper = testCaseAndOperations[i];
					ValidationOperation operation = testWrapper.getOperation();
					boolean tpassed = false;
					try {
						if(monitor.isCanceled()) {
							break;
						}
						monitor.subTask("Testing " + (i+1) + " of " + testCaseAndOperations.length + " constructors."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						preTest(testWrapper);
						if(test(monitor, testWrapper)) {
							numPassed++;
							tpassed = true;
						}
					}
					catch(Throwable exc) {
						getBuffer().write("Throwable caught while testing #" + (i+1) + " " + operation.getClass().getName()); //$NON-NLS-1$ //$NON-NLS-2$
						Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
						if(logger.isLoggingLevel(Level.SEVERE)) {
							logger.write(Level.SEVERE, exc);
						}
					}
					finally {
						String message = "Test #" + (i+1) + " of " + testCaseAndOperations.length + ((tpassed) ? " passed." : " failed."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						monitor.subTask(message);
						getBuffer().write(message);
						getBuffer().addExecutionStatus(getName(), testWrapper.getName(), tpassed); //$NON-NLS-1$
					}
				}
				
				String status = numPassed + " of " + testCaseAndOperations.length + " tests passed."; //$NON-NLS-1$ //$NON-NLS-2$
				monitor.subTask(status);
				getBuffer().write(status);
			}
			finally {
				// Set the project's settings back to what they were.
				gconf = origGconf;
				gconf.passivate();
				
				JDTUtility.setAutoBuild(autoBuildEnabled);
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
	}
	
	private void preTest(TestWrapper wrapper) throws CoreException {
		// First, remove all of the markers. Can't rely on the operation to remove the markers between
		// one invocation and the next because sometimes the validation is not supposed to run.
		TaskListUtility.removeAllTasks(getProject(), wrapper.getValidatorNames()); // null=delete markers on all objects
	}
	
	/**
	 * Return true if the test passes, and false if it failed.
	 */
	private boolean test(IProgressMonitor monitor, TestWrapper testWrapper) throws InvocationTargetException {
		boolean result = true;
		try {
			result = BVTRunner.singleton().test(getBuffer(), getName(), testWrapper.getName(), testWrapper.getExpectedMessages(), testWrapper.getOperation(), testWrapper.getValidatorNames()) && result;
		}
		catch(BVTValidationException exc) {
			result = false;
			if(exc.getTargetException() != null) {
				throw new InvocationTargetException(exc.getTargetException(), exc.getMessage());
			}
		}
		return result;
	}
	
	/**
	 * Return an array, with the first entry in the array the ValidatorTestcase that 
	 * holds the expected results, and the second entry of the array a ValidationOperation[]
	 * containing the operations to be tested.
	 */
	private TestWrapper[] getOperations(IProgressMonitor monitor, ValidationConfiguration vconf) throws InvocationTargetException {
		ValidatorTestcase[] allTmds = JDTUtility.getVFTests(monitor, getProject());
		if((allTmds == null) || (allTmds.length == 0)) {
			return null;
		}

		// Some of the operation constructors require file extensions. 
		String fileExtension = ".java"; //$NON-NLS-1$
		String defaultExtension = null;
		IFile javaFile = getProject().getFile("foo.java"); //$NON-NLS-1$

		// Some of the operations below pass if they don't produce any results.
		// Create a test case that passes the operation if the operation does nothing.
		List tmdEmpty = new ArrayList(); // expected messages == none
		List tmdInc = new ArrayList(); // expected messages when incremental validators ran
		List tmdEnabled = new ArrayList(); // expected messages when enabled validators ran
		List tmdJava = new ArrayList(); // expected messages when validators that validate .java files are run
		List tmdFull = new ArrayList(); // expected messages when validators that support only "Run Validation" are run
		Set enabledValidators = new HashSet(); // enabled validators == FWK Val, FWK NoBuild Val, Properties Val
		Set enabledIncrementalValidators = new HashSet(); // enabled incremental == FWK Val, Properties Val (support incremental validation)
		Set javaValidators = new HashSet(); // java val == FWK Val, FWK NoBuild Val (validate .java files)
		Set fullValidators = new HashSet(); // full val == FWK NoBuild Val (runs only when "Run Validation" clicked)
		ValidatorMetaData[] enabledVmd = new ValidatorMetaData[allTmds.length];
		for(int i=0; i<allTmds.length; i++) {
			ValidatorTestcase vt = allTmds[i];
			
			ValidatorMetaData vmd = vt.getValidatorMetaData();
			enabledValidators.add(vmd);
			enabledVmd[i] = vmd;
			if(vmd.isIncremental()) {
				enabledIncrementalValidators.add(vmd);
			}
			else {
				fullValidators.add(vmd);
				tmdFull.addAll(vt.getMessages(ValidationTypeEnum.RUN_VALIDATION));
			}
			
			// It is the responsibility of the code that instantiates the ValidatorSubsetOperation
			// to ensure that when an array of changed resources or objects is passed to the operation,
			// that only incremental validators are invoked by the operation. The framework does not
			// check if the validators are incremental or not; instead, the named validators will run,
			// even if they perform a full validation instead of validating just the arrays.
			if(vmd.isApplicableTo(javaFile) && vmd.isIncremental()) {
				javaValidators.add(vmd);
				tmdJava.addAll(vt.getMessages(ValidationTypeEnum.INCREMENTAL_VALIDATION));
			}
			
			tmdEnabled.addAll(vt.getMessages(ValidationTypeEnum.RUN_VALIDATION));
			tmdInc.addAll(vt.getMessages(ValidationTypeEnum.INCREMENTAL_VALIDATION));			
		}
		String[] allValidatorNames = InternalValidatorManager.getManager().getValidatorNames(enabledValidators);
		String[] incValidatorNames = InternalValidatorManager.getManager().getValidatorNames(enabledIncrementalValidators);
		String[] javaValidatorNames = InternalValidatorManager.getManager().getValidatorNames(javaValidators);
		String[] fullValidatorNames = InternalValidatorManager.getManager().getValidatorNames(fullValidators);
		
		vconf.setEnabledValidators(enabledVmd);
		vconf.passivate();
		
		// Start constructing the tests
		// For the tests that depend on the value of the autoBuild setting, it is assumed that
		// autoBuild is on. The tests will fail if autoBuild is disabled.
		//
		// Unlike the subsequent operations, these first two cannot use the allTmds TMD because
		// more than one validator runs. The allTmdFull TMD contains all tmd of validators that
		// will run. 
		AllValidatorsOperation op1 = new AllValidatorsOperation(getProject());
		TestWrapper test1 = new TestWrapper(op1, tmdEnabled, "op1", allValidatorNames); //$NON-NLS-1$
		
		AllValidatorsOperation op2 = new AllValidatorsOperation(getProject(), false); // don't fork
		TestWrapper test2 = new TestWrapper(op2, tmdEnabled, "op2", allValidatorNames); //$NON-NLS-1$

		// Construct the input for the rest of the operations using the VF Test validators.
		TestOpConstrInputOperation inputOp = new TestOpConstrInputOperation(getBuffer(), getProject());
		try {
			ResourcesPlugin.getWorkspace().run(inputOp, monitor);
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return new TestWrapper[0];
		}
		
		IResource[] changedResources = TestOpConstrInputOperation.getChangedResources(getProject(), allTmds);
		IResource[] emptyResources = TestOpConstrInputOperation.getEmptyResources(getProject());
		JavaHelpers[] changedClasses = inputOp.getChangedClasses(changedResources);
		IResourceDelta emptyDelta = inputOp.getEmptyDelta();
		IResourceDelta changedDelta = inputOp.getChangedDelta();
		// end construct input

		EnabledIncrementalValidatorsOperation op3 = new EnabledIncrementalValidatorsOperation(getProject(), null,true);
		TestWrapper test3 = new TestWrapper(op3, tmdInc, "op3", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op4 = new EnabledIncrementalValidatorsOperation(getProject(), changedDelta);
		TestWrapper test4 = new TestWrapper(op4, tmdInc, "op4", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op5 = new EnabledIncrementalValidatorsOperation(getProject(), emptyDelta);
		TestWrapper test5 = new TestWrapper(op5, tmdEmpty, "op5", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op6 = new EnabledIncrementalValidatorsOperation(getProject(), null, IRuleGroup.PASS_FAST);
		TestWrapper test6 = new TestWrapper(op6, tmdInc, "op6", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op7 = new EnabledIncrementalValidatorsOperation(getProject(), changedDelta, IRuleGroup.PASS_FAST);
		TestWrapper test7 = new TestWrapper(op7, tmdInc, "op7", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op8 = new EnabledIncrementalValidatorsOperation(getProject(), emptyDelta, IRuleGroup.PASS_FAST);
		TestWrapper test8 = new TestWrapper(op8, tmdEmpty, "op8", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op9 = new EnabledIncrementalValidatorsOperation(getProject(), enabledIncrementalValidators, null, IRuleGroup.PASS_FAST);
		TestWrapper test9 = new TestWrapper(op9, tmdInc, "op9", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op10 = new EnabledIncrementalValidatorsOperation(getProject(), enabledIncrementalValidators, changedDelta, IRuleGroup.PASS_FAST);
		TestWrapper test10 = new TestWrapper(op10, tmdInc, "op10", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op11 = new EnabledIncrementalValidatorsOperation(getProject(), enabledIncrementalValidators, emptyDelta, IRuleGroup.PASS_FAST);
		TestWrapper test11 = new TestWrapper(op11, tmdEmpty, "op11", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op12 = new EnabledIncrementalValidatorsOperation(getProject(), null, false); // full validate, don't fork
		TestWrapper test12 = new TestWrapper(op12, tmdInc, "op12", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op13 = new EnabledIncrementalValidatorsOperation(getProject(), changedDelta, false); // delta validate, don't fork
		TestWrapper test13 = new TestWrapper(op13, tmdInc, "op13", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op14 = new EnabledIncrementalValidatorsOperation(getProject(), emptyDelta, false); // empty delta validate, don't fork
		TestWrapper test14 = new TestWrapper(op14, tmdEmpty, "op14", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op15 = new EnabledIncrementalValidatorsOperation(getProject(), null, IRuleGroup.PASS_FAST, false); // full validate, don't fork
		TestWrapper test15 = new TestWrapper(op15, tmdInc, "op15", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op16 = new EnabledIncrementalValidatorsOperation(getProject(), changedDelta, IRuleGroup.PASS_FAST, false); // delta validate, don't fork
		TestWrapper test16 = new TestWrapper(op16, tmdInc, "op16", incValidatorNames); //$NON-NLS-1$
		
		EnabledIncrementalValidatorsOperation op17 = new EnabledIncrementalValidatorsOperation(getProject(), emptyDelta, IRuleGroup.PASS_FAST, false); // empty delta validate, don't fork
		TestWrapper test17 = new TestWrapper(op17, tmdEmpty, "op17", incValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op18 = new EnabledValidatorsOperation(getProject());
		TestWrapper test18= new TestWrapper(op18, tmdEnabled, "op18", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op19 = new EnabledValidatorsOperation(getProject(), false); // don't fork
		TestWrapper test19 = new TestWrapper(op19, tmdEnabled, "op19", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op20 = new EnabledValidatorsOperation(getProject(), IRuleGroup.PASS_FAST);
		TestWrapper test20 = new TestWrapper(op20, tmdEnabled, "op20", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op21 = new EnabledValidatorsOperation(getProject(), IRuleGroup.PASS_FAST, true); // force validation if it doesn't need to run
		TestWrapper test21 = new TestWrapper(op21, tmdEnabled, "op21", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op22 = new EnabledValidatorsOperation(getProject(), IRuleGroup.PASS_FAST, false); // no force validation if it doesn't need to run
		TestWrapper test22 = new TestWrapper(op22, tmdEnabled, "op22", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op23 = new EnabledValidatorsOperation(getProject(), IRuleGroup.PASS_FAST, true, false); // force, don't fork
		TestWrapper test23 = new TestWrapper(op23, tmdEnabled, "op23", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op24 = new EnabledValidatorsOperation(getProject(), IRuleGroup.PASS_FAST, false, false); // no force, don't fork
		TestWrapper test24 = new TestWrapper(op24, tmdEnabled, "op24", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op25 = new EnabledValidatorsOperation(getProject(), null); 
		TestWrapper test25 = new TestWrapper(op25, tmdEnabled, "op25", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op26 = new EnabledValidatorsOperation(getProject(), changedDelta);
		TestWrapper test26 = new TestWrapper(op26, tmdInc, "op26", incValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op27 = new EnabledValidatorsOperation(getProject(), emptyDelta);
		TestWrapper test27 = new TestWrapper(op27, tmdEmpty, "op27", incValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op28 = new EnabledValidatorsOperation(getProject(), null, IRuleGroup.PASS_FAST);
		TestWrapper test28 = new TestWrapper(op28, tmdEnabled, "op28", allValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op29 = new EnabledValidatorsOperation(getProject(), changedDelta, IRuleGroup.PASS_FAST);
		TestWrapper test29 = new TestWrapper(op29, tmdInc, "op29", incValidatorNames); //$NON-NLS-1$
		
		EnabledValidatorsOperation op30 = new EnabledValidatorsOperation(getProject(), emptyDelta, IRuleGroup.PASS_FAST); 
		TestWrapper test30 = new TestWrapper(op30, tmdEmpty, "op30", incValidatorNames); //$NON-NLS-1$

		ValidatorMetaData vmd = allTmds[0].getValidatorMetaData();
		String validatorName = vmd.getValidatorUniqueName();
		String[] vmdNames = InternalValidatorManager.getManager().getValidatorNames(new ValidatorMetaData[]{vmd});
		List expectedMessages = allTmds[0].getMessages(ValidationTypeEnum.RUN_VALIDATION);
		OneValidatorOperation op31 = new OneValidatorOperation(getProject(), validatorName);
		TestWrapper test31 = new TestWrapper(op31, expectedMessages, "op31", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op32 = new OneValidatorOperation(getProject(), validatorName, true); // force if necessary
		TestWrapper test32 = new TestWrapper(op32, expectedMessages, "op32", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op33 = new OneValidatorOperation(getProject(), validatorName, false); // no force
		TestWrapper test33 = new TestWrapper(op33, expectedMessages, "op33", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op34 = new OneValidatorOperation(getProject(), validatorName, true, false); // force, no fork
		TestWrapper test34 = new TestWrapper(op34, expectedMessages, "op34", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op35 = new OneValidatorOperation(getProject(), validatorName, false, false); // no force, no fork
		TestWrapper test35 = new TestWrapper(op35, expectedMessages, "op35", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op36 = new OneValidatorOperation(getProject(), validatorName, true, IRuleGroup.PASS_FAST); // force
		TestWrapper test36 = new TestWrapper(op36, expectedMessages, "op36", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op37 = new OneValidatorOperation(getProject(), validatorName, false, IRuleGroup.PASS_FAST); // no force
		TestWrapper test37 = new TestWrapper(op37, expectedMessages, "op37", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op38 = new OneValidatorOperation(getProject(), validatorName, true, IRuleGroup.PASS_FAST, false); // force, no fork
		TestWrapper test38 = new TestWrapper(op38, expectedMessages, "op38", vmdNames); //$NON-NLS-1$
		
		OneValidatorOperation op39 = new OneValidatorOperation(getProject(), validatorName, false, IRuleGroup.PASS_FAST, false); // no force, no fork
		TestWrapper test39 = new TestWrapper(op39, expectedMessages, "op39", vmdNames); //$NON-NLS-1$

		ValidatorSubsetOperation op40 = new ValidatorSubsetOperation(getProject());
		op40.setValidators(allValidatorNames);
		TestWrapper test40 = new TestWrapper(op40, tmdEnabled, "op40", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op41 = new ValidatorSubsetOperation(getProject(), true); // force
		op41.setValidators(allValidatorNames);
		TestWrapper test41 = new TestWrapper(op41, tmdEnabled, "op41", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op42 = new ValidatorSubsetOperation(getProject(), false); // no force
		op42.setValidators(allValidatorNames);
		TestWrapper test42 = new TestWrapper(op42, tmdEnabled, "op42", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op43 = new ValidatorSubsetOperation(getProject(), true, false); // force, no fork
		op43.setValidators(allValidatorNames);
		TestWrapper test43 = new TestWrapper(op43, tmdEnabled, "op43", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op44 = new ValidatorSubsetOperation(getProject(), false, false); // no force, no fork
		op44.setValidators(allValidatorNames);
		TestWrapper test44 = new TestWrapper(op44, tmdEnabled, "op44", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op45 = new ValidatorSubsetOperation(getProject(), true, IRuleGroup.PASS_FAST); // force
		op45.setValidators(allValidatorNames);
		TestWrapper test45 = new TestWrapper(op45, tmdEnabled, "op45", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op46 = new ValidatorSubsetOperation(getProject(), false, IRuleGroup.PASS_FAST); // no force
		op46.setValidators(allValidatorNames);
		TestWrapper test46 = new TestWrapper(op46, tmdEnabled, "op46", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op47 = new ValidatorSubsetOperation(getProject(), true, IRuleGroup.PASS_FAST, false); // force, no fork
		op47.setValidators(allValidatorNames);
		TestWrapper test47 = new TestWrapper(op47, tmdEnabled, "op47", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op48 = new ValidatorSubsetOperation(getProject(), false, IRuleGroup.PASS_FAST, false); // no force, no fork
		op48.setValidators(allValidatorNames);
		TestWrapper test48 = new TestWrapper(op48, tmdEnabled, "op48", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op49 = new ValidatorSubsetOperation(getProject(), null);
		op49.setValidators(allValidatorNames);
		TestWrapper test49 = new TestWrapper(op49, tmdEnabled, "op49", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op50 = new ValidatorSubsetOperation(getProject(), changedDelta);
		op50.setValidators(incValidatorNames);
		TestWrapper test50 = new TestWrapper(op50, tmdInc, "op50", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op51 = new ValidatorSubsetOperation(getProject(), emptyDelta);
		op51.setValidators(incValidatorNames);
		TestWrapper test51 = new TestWrapper(op51, tmdEmpty, "op51", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op52 = new ValidatorSubsetOperation(getProject(), null, false); // false=autoBuild disabled
		op52.setValidators(allValidatorNames);
		TestWrapper test52 = new TestWrapper(op52, tmdEnabled, "op52", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op53 = new ValidatorSubsetOperation(getProject(), null, true); // true=autoBuild enabled
		op53.setValidators(fullValidatorNames);
		TestWrapper test53 = new TestWrapper(op53, tmdFull, "op53", fullValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op54 = new ValidatorSubsetOperation(getProject(), changedDelta, false); // false=autoBuild disabled
		op54.setValidators(incValidatorNames);
		TestWrapper test54 = new TestWrapper(op54, tmdInc, "op54", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op55 = new ValidatorSubsetOperation(getProject(), changedDelta, true); // true=autoBuild enabled
		op55.setValidators(incValidatorNames);
		TestWrapper test55 = new TestWrapper(op55, tmdEmpty, "op55", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op56 = new ValidatorSubsetOperation(getProject(), emptyDelta, false); // false=autoBuild disabled
		op56.setValidators(incValidatorNames);
		TestWrapper test56 = new TestWrapper(op56, tmdEmpty, "op56", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op57 = new ValidatorSubsetOperation(getProject(), emptyDelta, true); // true=autoBuild enabled
		op57.setValidators(incValidatorNames);
		TestWrapper test57 = new TestWrapper(op57, tmdEmpty, "op57", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op58 = new ValidatorSubsetOperation(getProject(), null, false, IRuleGroup.PASS_FAST); // false=autoBuild disabled
		op58.setValidators(allValidatorNames);
		TestWrapper test58 = new TestWrapper(op58, tmdEnabled, "op58", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op59 = new ValidatorSubsetOperation(getProject(), null, true, IRuleGroup.PASS_FAST); // true=autoBuild enabled
		op59.setValidators(allValidatorNames);
		TestWrapper test59 = new TestWrapper(op59, tmdFull, "op59", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op60 = new ValidatorSubsetOperation(getProject(), changedDelta, false, IRuleGroup.PASS_FAST); // false=autoBuild disabled
		op60.setValidators(incValidatorNames);
		TestWrapper test60 = new TestWrapper(op60, tmdInc, "op60", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op61 = new ValidatorSubsetOperation(getProject(), changedDelta, true, IRuleGroup.PASS_FAST); // true=autoBuild enabled
		op61.setValidators(incValidatorNames);
		TestWrapper test61 = new TestWrapper(op61, tmdEmpty, "op61", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op62 = new ValidatorSubsetOperation(getProject(), emptyDelta, false, IRuleGroup.PASS_FAST); // false=autoBuild disabled
		op62.setValidators(incValidatorNames);
		TestWrapper test62 = new TestWrapper(op62, tmdEmpty, "op62", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op63 = new ValidatorSubsetOperation(getProject(), emptyDelta, true, IRuleGroup.PASS_FAST); // true=autoBuild enabled
		op63.setValidators(incValidatorNames);
		TestWrapper test63 = new TestWrapper(op63, tmdEmpty, "op63", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op64 = new ValidatorSubsetOperation(getProject(), null, IRuleGroup.PASS_FAST);
		op64.setValidators(allValidatorNames);
		TestWrapper test64 = new TestWrapper(op64, tmdEnabled, "op64", allValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op65 = new ValidatorSubsetOperation(getProject(), changedDelta, IRuleGroup.PASS_FAST);
		op65.setValidators(incValidatorNames);
		TestWrapper test65 = new TestWrapper(op65, tmdInc, "op65", incValidatorNames); //$NON-NLS-1$
		
		ValidatorSubsetOperation op66 = new ValidatorSubsetOperation(getProject(), emptyDelta, IRuleGroup.PASS_FAST);
		op66.setValidators(incValidatorNames);
		TestWrapper test66 = new TestWrapper(op66, tmdEmpty, "op66", incValidatorNames); //$NON-NLS-1$

		if(changedClasses != null) {
			ValidatorSubsetOperation op67 = new ValidatorSubsetOperation(getProject(), fileExtension, changedResources);
			op67.setValidators(javaValidatorNames);
			TestWrapper test67 = new TestWrapper(op67, tmdJava, "op67", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op68 = new ValidatorSubsetOperation(getProject(), fileExtension, changedResources, false); // no fork
			op68.setValidators(javaValidatorNames);
			TestWrapper test68 = new TestWrapper(op68, tmdJava, "op68", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op69 = new ValidatorSubsetOperation(getProject(), fileExtension, defaultExtension, changedResources);
			op69.setValidators(javaValidatorNames);
			TestWrapper test69 = new TestWrapper(op69, tmdJava, "op69", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op70 = new ValidatorSubsetOperation(getProject(), fileExtension, defaultExtension, changedResources, false); // no fork
			op70.setValidators(javaValidatorNames);
			TestWrapper test70 = new TestWrapper(op70, tmdJava, "op70", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op71 = new ValidatorSubsetOperation(getProject(), fileExtension, emptyResources);
			op71.setValidators(javaValidatorNames);
			TestWrapper test71 = new TestWrapper(op71, tmdEmpty, "op71", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op72 = new ValidatorSubsetOperation(getProject(), fileExtension, emptyResources, false); // no fork
			op72.setValidators(javaValidatorNames);
			TestWrapper test72 = new TestWrapper(op72, tmdEmpty, "op72", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op73 = new ValidatorSubsetOperation(getProject(), fileExtension, defaultExtension, emptyResources);
			op73.setValidators(javaValidatorNames);
			TestWrapper test73 = new TestWrapper(op73, tmdEmpty, "op73", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op74 = new ValidatorSubsetOperation(getProject(), fileExtension, defaultExtension, emptyResources, false); // no fork
			op74.setValidators(javaValidatorNames);
			TestWrapper test74 = new TestWrapper(op74, tmdEmpty, "op74", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op75 = new ValidatorSubsetOperation(getProject(), fileExtension, changedClasses);
			op75.setValidators(javaValidatorNames);
			TestWrapper test75 = new TestWrapper(op75, tmdJava, "op75", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op76 = new ValidatorSubsetOperation(getProject(), fileExtension, changedClasses, false); // no fork
			op76.setValidators(javaValidatorNames);
			TestWrapper test76 = new TestWrapper(op76, tmdJava, "op76", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op77 = new ValidatorSubsetOperation(getProject(), fileExtension, defaultExtension, changedClasses);
			op77.setValidators(javaValidatorNames);
			TestWrapper test77 = new TestWrapper(op77, tmdJava, "op77", javaValidatorNames); //$NON-NLS-1$
			
			ValidatorSubsetOperation op78 = new ValidatorSubsetOperation(getProject(), fileExtension, defaultExtension, changedClasses, false); // no fork
			op78.setValidators(javaValidatorNames);
			TestWrapper test78 = new TestWrapper(op78, tmdJava, "op78", javaValidatorNames); //$NON-NLS-1$
			
			TestWrapper[] result = new TestWrapper[] {
				test1, test2, test3, test4, test5, test6, test7, test8, test9,
				test10, test11, test12, test13, test14, test15, test16, test17, test18, test19,
				test20, test21, test22, test23, test24, test25, test26, test27, test28, test29,
				test30, test31, test32, test33, test34, test35, test36, test37, test38, test39,
				test40, test41, test42, test43, test44, test45, test46, test47, test48, test49,
				test50, test51, test52, test53, test54, test55, test56, test57, test58, test59,
				test60, test61, test62, test63, test64, test65, test66, test67, test68, test69,
				test70, test71, test72, test73, test74, test75, test76, test77, test78
			};
			return result;
		}
		else {
			TestWrapper[] result = new TestWrapper[] {
				test1, test2, test3, test4, test5, test6, test7, test8, test9,
				test10, test11, test12, test13, test14, test15, test16, test17, test18, test19,
				test20, test21, test22, test23, test24, test25, test26, test27, test28, test29,
				test30, test31, test32, test33, test34, test35, test36, test37, test38, test39,
				test40, test41, test42, test43, test44, test45, test46, test47, test48, test49,
				test50, test51, test52, test53, test54, test55, test56, test57, test58, test59,
				test60, test61, test62, test63, test64, test65, test66
			};
			return result;
		}		
	}

	private class TestWrapper {
		private List _messages = null;
		private ValidationOperation _operation = null;
		private String _testName = null;
		private String[] _validatorNames = null;
		
		TestWrapper(ValidationOperation op, List expectedMessages, String testName, String[] validatorNames) {
			_messages = expectedMessages;
			_operation = op;
			_testName = testName;
			_validatorNames = validatorNames;
		}
		
		public List getExpectedMessages() {
			return _messages;
		}
		
		public ValidationOperation getOperation() {
			return _operation;
		}
		
		public String getName() {
			return _testName;
		}
		
		public String[] getValidatorNames() {
			return _validatorNames;
		}
	}
}

