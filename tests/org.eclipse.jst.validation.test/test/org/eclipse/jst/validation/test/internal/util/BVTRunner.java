package org.eclipse.jst.validation.test.internal.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationException;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.ValidationTypeEnum;
import org.eclipse.jst.validation.test.internal.registry.BVTValidationRegistryReader;
import org.eclipse.jst.validation.test.internal.registry.ITestcaseMetaData;
import org.eclipse.jst.validation.test.internal.registry.MessageMetaData;
import org.eclipse.jst.validation.test.internal.registry.MessageUtility;
import org.eclipse.jst.validation.test.internal.registry.OperationTestcase;
import org.eclipse.jst.validation.test.internal.registry.TestSetupImport;
import org.eclipse.jst.validation.test.internal.registry.TestcaseUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.setup.IBuffer;
import org.eclipse.jst.validation.test.setup.ITestStatus;
import org.eclipse.wst.validation.internal.InternalValidatorManager;
import org.eclipse.wst.validation.internal.TaskListUtility;
import org.eclipse.wst.validation.internal.VThreadManager;
import org.eclipse.wst.validation.internal.ValidatorMetaData;
import org.eclipse.wst.validation.internal.operations.OneValidatorOperation;
import org.eclipse.wst.validation.internal.operations.ValidationOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;
import org.eclipse.wst.validation.internal.plugin.ValidationPlugin;

import com.ibm.wtp.common.logger.LogEntry;
import com.ibm.wtp.common.logger.proxy.Logger;

/**
 */
public final class BVTRunner {
	private static BVTRunner _inst = null;
	private List _extra = null; // The extra markers reported (stored in a list to make reading easier).
	private List _missing = null; // The missing MMD that were supposed to be reported (stored in a list to make reading easier).

	public static BVTRunner singleton() {
		if(_inst == null) {
			_inst = new BVTRunner();
		}
		return _inst;
	}
	
	private BVTRunner() {
		super();
		_extra = new ArrayList();
		_missing = new ArrayList();
	}
	
	private boolean isVerbose() {
		Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
		if(logger.isLoggingLevel(Level.FINEST)) {
			return true;
		}
		return false;
	}

	private char getSeverityChar(Integer severity) {
		if(severity == null) {
			return '?';
		}
		
		switch(severity.intValue()) {
			case(IMarker.SEVERITY_ERROR): {
				return 'E';
			}
			
			case(IMarker.SEVERITY_WARNING): {
				return 'W';
			}
			
			case(IMarker.SEVERITY_INFO): {
				return 'I';
			}
			
			default: {
				return '?';
			}
		}
	}
	
	private String[] getNames(IProject[] projects) {
		if(projects == null) {
			return new String[0];
		}
		String[] names = new String[projects.length];
		for(int i=0; i<projects.length; i++) {
			IProject project = projects[i];
			names[i] = project.getName();
		}
		return names;
	}

	private void debug(IBuffer buffer, IMarker[] messages) {
		if(messages == null) {
			buffer.write("Nothing to debug because nothing was reported"); //$NON-NLS-1$
		}
		
		for(int i=0; i<messages.length; i++) {
			IMarker marker = messages[i];
			buffer.write(MessageUtility.toString(marker));
		}
	}

	protected IProgressMonitor getDebugMonitor(IProgressMonitor monitor) {
		if(isVerbose()) {
			return monitor;
		}
		else {
			return new NullProgressMonitor(); // if verbose is true, show progress messages for the creation & validation of the project. Otherwise show only the BVT status.
		}
	}
	
	/**
	 * When invoking the test from the UI, the input has already been imported, so just test it.
	 * The name of the test is derived from the name of the project.
	 * Return the number of tests that passed.
	 */	
	public int test(IBuffer buffer, IProject[] projects) throws BVTValidationException {	
		String[] testNames = getNames(projects);
		BVTValidationRegistryReader reader = BVTValidationRegistryReader.getReader();
		
		buffer.getProgressMonitor().beginTask("Running BVT tests", projects.length); //$NON-NLS-1$
		int totalTests = 0;
		int totalPassed = 0;
		for(int i=0; i<projects.length; i++) {
			buffer.getProgressMonitor().subTask("Testing project " + (i+1) + " of " + projects.length); //$NON-NLS-1$ //$NON-NLS-2$
			IProject project = projects[i];
			ValidatorTestcase[] tests = reader.getValidatorTests(buffer.getProgressMonitor(), testNames[i]);
			if((tests == null) || (tests.length == 0)) {
				buffer.getProgressMonitor().subTask("There are no tests to run."); //$NON-NLS-1$
				continue;
			}
			
			int numTestsPassed = 0;
			for(int j=0; j<tests.length; j++) {
				ValidatorTestcase tmd = tests[j];
				if(test(buffer, project, tmd, ValidationTypeEnum.RUN_VALIDATION)) {
					numTestsPassed++;
					buffer.getProgressMonitor().subTask("Test " + (j+1) + " of " + tests.length + " passed."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				else {
					buffer.getProgressMonitor().subTask("Test " + (j+1) + " of " + tests.length + " failed."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				buffer.getProgressMonitor().worked(1);
			}
			
			totalTests += tests.length;
			totalPassed += numTestsPassed;
			
			buffer.write(numTestsPassed + " of " + tests.length + " tests passed."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		buffer.write("\n>>>>>>>>>>TEST RESULTS>>>>>>>>>>"); //$NON-NLS-1$
		buffer.write(totalPassed + " of " + totalTests + " tests passed."); //$NON-NLS-1$ //$NON-NLS-2$
		buffer.getProgressMonitor().done();
		
		return totalPassed;
	}
	
	/**
	 * Construct a ValidationOperation on the IProject, invoke the operation, and verify that the
	 * resulting IMarkers match the expected messages in the tmd.
	 */	
	public boolean test(IBuffer buffer, IProject project, ValidatorTestcase tmd, int validationEnumType) throws BVTValidationException {
		switch(validationEnumType) {
			case(ValidationTypeEnum.RUN_VALIDATION): {
				return test(buffer, tmd.getName(), ValidationTypeEnum.RUN_VALIDATION_NAME, tmd.getMessages(validationEnumType), new OneValidatorOperation(project, tmd.getValidatorClass(), true, false), InternalValidatorManager.getManager().getValidatorNames(new ValidatorMetaData[]{tmd.getValidatorMetaData()})); // true=force, false=no for
			}
			
			case(ValidationTypeEnum.FULL_VALIDATION): {
				return build(buffer, project, tmd, validationEnumType, ValidationTypeEnum.FULL_VALIDATION_NAME);
			}
			
			case(ValidationTypeEnum.INCREMENTAL_VALIDATION): {
				return build(buffer, project, tmd, validationEnumType, ValidationTypeEnum.INCREMENTAL_VALIDATION_NAME);
			}
			
			case(ValidationTypeEnum.AUTO_VALIDATION): {
				return build(buffer, project, tmd, validationEnumType, ValidationTypeEnum.AUTO_VALIDATION_NAME);
			}
			
			default: {
				buffer.addExecutionStatus(tmd.getName(), null, ITestStatus.FAIL, buffer, "Unrecognized validation option:" + validationEnumType); //$NON-NLS-1$
				return false;
			}
		}
	}
	
	private boolean build(IBuffer buffer, final IProject project, final ValidatorTestcase tmd, final int validationEnumType, String subTaskName) throws BVTValidationException {
		try {
			IProgressMonitor nullMonitor = new NullProgressMonitor();
			ResourcesPlugin.getWorkspace().run(
			new IWorkspaceRunnable(){
				public void run(IProgressMonitor monitor) {
					try {
						IResource[] resources = tmd.getResources(project);
						for(int i=0; i<resources.length; i++) {
							IResource res = resources[i];
							res.touch(monitor);
						}
					}
					catch(CoreException exc) {
						Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
						if(logger.isLoggingLevel(Level.SEVERE)) {
							logger.write(exc);
						}
					}
				}
			}, nullMonitor);
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(exc);
			}
			buffer.addExecutionStatus(tmd.getName(), subTaskName, ITestStatus.FAIL, buffer, "CoreException caught; stack trace in LoggingUtil.log"); //$NON-NLS-1$
			return false;
		}

		try {
			// Do not build using only the validation builder because some validators
			// (i.e., the EJB Validator) depend on the output of other builders 
			// (i.e., the Java Builder).
			project.build(validationEnumType, new NullProgressMonitor());
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(exc);
			}
			buffer.write("CoreException caught; stack trace in LoggingUtil.log"); //$NON-NLS-1$
			return false;
		}

		IMarker[] messages = TaskListUtility.getValidationTasks(project, InternalValidatorManager.getManager().getValidatorNames(new ValidatorMetaData[]{tmd.getValidatorMetaData()}));
		return verify(buffer, tmd.getName(), subTaskName, project, tmd.getMessages(validationEnumType), messages);
	}
	
	/**
	 * Run the given ValidationOperation and ensure that the results match the results of the given test case.
	 * Return true if the results match and false if they don't.
	 * 
	 * This method is public because it's needed for the TestOpConstr operation.
	 */
	public boolean test(IBuffer buffer, String testName, String subTaskName, List expectedMessages, ValidationOperation op, String[] validatorNames) throws BVTValidationException {
		IProgressMonitor debugMonitor = getDebugMonitor(buffer.getProgressMonitor());
		boolean passed = true;
		try {
			
			if(op == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.WARNING)) {
					logger.write(Level.WARNING, "Cannot run tests because the Operation is null."); //$NON-NLS-1$
				}
				passed = false;
			}
			else {
				try {
						ValidatorManager.getManager().setNoMessageLimit(op.getProject());
					
						ResourcesPlugin.getWorkspace().run(op, debugMonitor); 
						
						if(op.isFork()) {
							// Wait until all forked threads are complete
							while(!VThreadManager.getManager().isDone()){};
						}
			
						// Load the enabled validators from the test, not the operation,
						// because the test has the messages for a single validator, and
						// if the messages from all enabled validators are retrieved,
						// then "extra" messages, reported by validators whose messages
						// aren't expected by the test, fail the test case. 
						IMarker[] messages = TaskListUtility.getValidationTasks(op.getProject(), validatorNames);
						passed = verify(buffer, testName, subTaskName, op.getProject(), expectedMessages, messages);
				}
				catch(Throwable exc) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if(logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, exc);
					}
					passed = false;
				}
			}
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			passed = false;
		}
		finally {
			return passed;
		}
	}

	public boolean setupTests(IBuffer buffer, boolean verbose) {
		ValidatorTestcase[] vts = BVTValidationRegistryReader.getReader().getValidatorTests(buffer.getProgressMonitor(), (String)null);
		OperationTestcase[] ots = BVTValidationRegistryReader.getReader().getOperationTests(buffer.getProgressMonitor(), (String)null);
		boolean successful = true;
		for(int i=0; i<vts.length; i++) {
			ValidatorTestcase vtest = vts[i];
			successful = setupTests(buffer, vtest, false) && successful;
		}
		
		for(int j=0; j<ots.length; j++) {
			OperationTestcase otest = ots[j];
			successful = setupTests(buffer, otest, false) && successful;
		}
		
		return successful;
	}
	
	/**
	 * Return true if all of the test cases' input were found and imported.
	 */
	public boolean setupTests(IBuffer buffer, ITestcaseMetaData tmd, boolean verbose) {
		int executionMap = 0x0;
		boolean imported = true;
		Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
		try {
			BVTValidationRegistryReader reader = BVTValidationRegistryReader.getReader();
	
			// Each import operation deletes any project before it re-imports it.
			String dir = TestcaseUtility.getInputDir(tmd);
			TestSetupImport[] setup = reader.getTestSetup(buffer, dir, tmd, verbose);
			if((setup == null) || (setup.length == 0)) {
				String message = "Cannot import input that does not exist. Check that directory " + dir + " exists."; //$NON-NLS-1$ //$NON-NLS-2$ 
				buffer.write(message);
				executionMap |= 0x1;
				imported = false;
				if(logger.isLoggingLevel(Level.SEVERE)) {
					LogEntry entry = ValidationPlugin.getLogEntry();
					entry.setExecutionMap(executionMap);
					entry.setText(message);
					logger.write(Level.SEVERE, entry);
				}
			}
			else {
				buffer.getProgressMonitor().beginTask("Importing files; please wait...", setup.length); //$NON-NLS-1$
				for(int i=0; i<setup.length; i++) {
					buffer.getProgressMonitor().subTask("Importing file " + (i+1) + " of " + setup.length); //$NON-NLS-1$ //$NON-NLS-2$
					if(!setup[i].importFile(buffer.getProgressMonitor(), dir)) {
						// One of the input files couldn't be imported.
						executionMap |= 0x2;
						imported = false;
						if(logger.isLoggingLevel(Level.SEVERE)) {
							LogEntry entry = ValidationPlugin.getLogEntry();
							entry.setExecutionMap(executionMap);
							entry.setText("Import failed for dir " + dir); //$NON-NLS-1$
							logger.write(Level.SEVERE, entry);
						}
						continue;
					}
					buffer.getProgressMonitor().worked(1);
				}
				buffer.getProgressMonitor().done();
			}
		}
		finally {
			if(!imported) {
				String tmdName = (tmd == null) ? "?" : tmd.getName(); //$NON-NLS-1$
				LogEntry entry = ValidationPlugin.getLogEntry();
				entry.setExecutionMap(executionMap);
				entry.setText("Test setup for " + tmdName + " was unsuccessful."); //$NON-NLS-1$ //$NON-NLS-2$
				logger.write(Level.SEVERE, entry);
			}
			return imported;
		}
	}

	/**
	 * Return true if the expected messages (tmdMmdList) matches the reported messages (ml2).
	 */
	public boolean compare(IBuffer buffer, String testName, String subTaskName, List tmdMmdList, IMarker[] ml2) {
		// Convert one of the arrays into a list so that it can be sorted.
		int numMatched = 0;
		List mmdList = new ArrayList(tmdMmdList); // create a copy of the tmd's expected output so that the tmd's output remains intact
		int numExpected = mmdList.size();
		int numReported = ml2.length;
		boolean passed = true;
	

		try {		
			Collections.sort(mmdList, MessageUtility.getMessageComparator(buffer, isVerbose()));
	
			try {
				for(int i=0; i<ml2.length; i++) {
					IMarker marker = ml2[i];
					MessageMetaData matching = getMatchingMetaData(buffer, mmdList, marker);
					if(matching == null) {
						_extra.add(marker);
						continue;
					}
					else {
						// Every time a mmd is matched to a marker, remove the mmd
						// from the list so that mmds without a marker can be flagged.
						mmdList.remove(matching);
						numMatched++;
					}
				}
	
				if(mmdList.size() != 0) {
					_missing.addAll(mmdList);
				}
			}
			catch(Throwable exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
				passed = false;
			}
			finally {
				passed = passed && ((_missing.size() == 0) && (_extra.size() == 0));
				
				// Report the missing markers after the MMD list is sorted
				Collections.sort(_missing, MessageUtility.getMessageComparator(buffer, isVerbose()));
				Iterator iterator = _missing.iterator();
				while(iterator.hasNext()) {
					MessageMetaData mmd = (MessageMetaData)iterator.next();
					buffer.addExecutionStatus(testName, subTaskName, ITestStatus.FAIL, buffer, "ERROR[missing message]: " + MessageUtility.toString(mmd)); //$NON-NLS-1$
				}
				
				// Report the extra markers after they're sorted.
				Collections.sort(_extra, MessageUtility.getMessageComparator(buffer, isVerbose()));
				iterator = _extra.iterator();
				while(iterator.hasNext()) {
					IMarker marker = (IMarker)iterator.next();
					buffer.addExecutionStatus(testName, subTaskName, ITestStatus.FAIL, buffer, "ERROR[extra message]: " + MessageUtility.toString(marker) + ", Text is: \"" + MessageUtility.getMessage(marker) + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
		
				if(numExpected != numReported) {
					buffer.write("Wrong number of messages reported. Expected " + numExpected + " and got " + numReported); //$NON-NLS-1$ //$NON-NLS-2$
				}
	
				buffer.write("Number of extra messages: " + _extra.size()); //$NON-NLS-1$
				buffer.write("Number of missing messages: " + _missing.size()); //$NON-NLS-1$
				buffer.write("Number of matched messages: " + numMatched); //$NON-NLS-1$
			}
		}
		finally {
			mmdList.clear();
			_extra.clear(); // Clear the list of extra markers (stored in a list to make reading easier).
			_missing.clear(); // Clear the list of missing markers (stored in a list to make reading easier).
		}
		return passed;
	}
	
	public boolean verify(IBuffer buffer, String testName, String subTaskName, IProject project, List mmdListOrig, IMarker[] messages) {
		buffer.write("Test case: " + testName); //$NON-NLS-1$

		int numMatched = 0;
		int numExpected = 0;
		int numReported = 0;
		boolean passed = true;
		List mmdList = new ArrayList(mmdListOrig); // Need to modify the list, but do not want to modify the original, so create a copy.
		try {
			// First, check that the number of messages match.
			if(messages == null) {
				passed = false;
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.INFO)) {
					logger.write(Level.INFO, "No messages were reported."); //$NON-NLS-1$
				}
				return passed;
			}
			
			numExpected = mmdList.size();
			numReported = messages.length;
			
			Collections.sort(mmdList, MessageUtility.getMessageComparator(buffer, isVerbose()));

			for(int i=0; i<messages.length; i++) {
				IMarker marker = messages[i];
				MessageMetaData mmd = getMatchingMetaData(buffer, mmdList, marker);
	
				if(mmd == null) {
					_extra.add(marker);
					continue;
				}
				else {
					// Every time a mmd is matched to a marker, remove the mmd
					// from the list so that mmds without a marker can be flagged.
					mmdList.remove(mmd);
					if(isVerbose()) {
						buffer.write("matched " + MessageUtility.toString(marker) + " to " + MessageUtility.toString(mmd)); //$NON-NLS-1$ //$NON-NLS-2$
					}
					numMatched++;
				}
				
				// Check severity
				try {
					// Fail a test case based on severity mismatch because EJB deploy
					// relies on the severity to determine whether or not deployment
					// can continue?
					verifySeverity(buffer, testName, subTaskName, mmd, marker);
				}
				catch(CoreException e) {
					passed = false;
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if(logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, e);
					}
					buffer.write("ERROR[Testcase::" + testName + "]. CoreException caught while verifying severity"); //$NON-NLS-1$ //$NON-NLS-2$
					continue;
				}
				
				// Check resource
				try {
					// Don't fail a test case based on resource, because the "location" column will
					// uniquely identify the location of the message, anyway. Should still let the 
					// user know, though.
					verifyResource(buffer, testName, subTaskName, mmd, marker);
				}
				catch(CoreException e) {
					passed = false;
					buffer.write("ERROR[Testcase::" + testName + "]. CoreException caught while verifying resource"); //$NON-NLS-1$ //$NON-NLS-2$
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if(logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, e);
					}
					continue;
				}
				
				// Check location
				try {
					// Don't fail a test case based on location, because the line number calculation
					// is different in UI & batch, and the test cases are based on UI test results.
					// Should still alert the user, though.
					verifyLocation(buffer, testName, subTaskName,  mmd, marker);
				}
				catch(CoreException e) {
					passed = false;
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if(logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, e);
					}
					buffer.write("ERROR[Testcase::" + testName + "]. CoreException caught while verifying line number"); //$NON-NLS-1$ //$NON-NLS-2$
					continue;
				}
			}
			
			if(mmdList.size() != 0) {
				_missing.addAll(mmdList);
			}
		}
		catch(Throwable exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			passed = false;
		}
		finally {
			if(!((_missing.size() == 0) && (_extra.size() == 0))) {
				passed = false;
			}
			
			if(passed) {
				buffer.write("PASS[Testcase::" + testName + "]"); //$NON-NLS-1$ //$NON-NLS-2$
				buffer.addExecutionStatus(testName, subTaskName, true); // If fail, will be reported in the extra & missing below.
			}
			else {
				buffer.write("FAIL[Testcase::" + testName + "]"); //$NON-NLS-1$ //$NON-NLS-2$

				// No point in listing the mmdList because it's empty by the time the finally block is reached
				if(isVerbose()) {
					buffer.write("List of markers reported:"); //$NON-NLS-1$
					debug(buffer, messages);
				}
			}
			
			// Report the missing markers after the MMD list is sorted
			Collections.sort(_missing, MessageUtility.getMessageComparator(buffer, isVerbose()));
			Iterator iterator = _missing.iterator();
			while(iterator.hasNext()) {
				MessageMetaData mmd = (MessageMetaData)iterator.next();
				buffer.addExecutionStatus(testName, subTaskName, ITestStatus.FAIL, buffer, "ERROR[missing message]: " + MessageUtility.toString(mmd)); //$NON-NLS-1$
			}
			
			// Report the extra markers after they're sorted.
			Collections.sort(_extra, MessageUtility.getMessageComparator(buffer, isVerbose()));
			iterator = _extra.iterator();
			while(iterator.hasNext()) {
				IMarker marker = (IMarker)iterator.next();
				buffer.addExecutionStatus(testName, subTaskName, ITestStatus.FAIL, buffer, "ERROR[extra message]: " + MessageUtility.toString(marker) + ", Text is: \"" + MessageUtility.getMessage(marker) + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
	
			if(numExpected != numReported) {
				buffer.write("Wrong number of messages reported. Expected " + numExpected + " and got " + numReported); //$NON-NLS-1$ //$NON-NLS-2$
			}

			buffer.write("Number of extra messages: " + _extra.size()); //$NON-NLS-1$
			buffer.write("Number of missing messages: " + _missing.size()); //$NON-NLS-1$
			buffer.write("Number of matched messages: " + numMatched); //$NON-NLS-1$
			buffer.write("End of test case: " + testName); //$NON-NLS-1$
			_extra.clear(); // Clear the list of extra markers (stored in a list to make reading easier).
			_missing.clear(); // Clear the list of missing markers (stored in a list to make reading easier).
		}
		return passed;
	}
	
	private int verifySeverity(IBuffer buffer, String testName, String subTaskName, MessageMetaData mmd, IMarker marker) throws CoreException {
		// If prefixSeverity is null, marker has no prefix, therefore it has no severity
		Integer prefixSeverity = MessageUtility.getSeverity(marker);
		if(prefixSeverity != null) {
			Integer severity = (Integer)marker.getAttribute(IMarker.SEVERITY); // IMarker.SEVERITY_ERROR, IMarker.SEVERITY_WARNING, IMarker.SEVERITY_INFO
			boolean severityEqual = true;
			if((severity == null) && (prefixSeverity == null)) {
				severityEqual = true;
			}
			else if(severity == null) {
				severityEqual = false;
			}
			else if(prefixSeverity == null) {
				severityEqual = false;
			}
			else {
				severityEqual = severity.equals(prefixSeverity);
			}
			
			if(!severityEqual) {
				buffer.addExecutionStatus(testName, subTaskName, ITestStatus.WARN, buffer, "WARNING[mismatching severity]: expected " + getSeverityChar(prefixSeverity) + ", got " + getSeverityChar(severity) + " on marker " + MessageUtility.toString(marker)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return ITestStatus.WARN;
			}
		}
			
		return ITestStatus.PASS;
	}
	
	private int verifyResource(IBuffer buffer, String testName, String subTaskName, MessageMetaData mmd, IMarker marker) throws CoreException {
		String resource = MessageUtility.getResource(marker);
		if(!resource.equals(mmd.getResource())) {
			buffer.addExecutionStatus(testName, subTaskName, ITestStatus.WARN, buffer, "INFO[wrong resource]: expected " + mmd.getResource() + ", got " + resource + " on marker " + MessageUtility.toString(marker)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			return ITestStatus.WARN;
		}
		return ITestStatus.PASS;
	}
	
	private int verifyLocation(IBuffer buffer, String testName, String subTaskName, MessageMetaData mmd, IMarker marker) throws CoreException {
		// Either compare two line numbers or two text locations, but not a line number to a text location.
		if(mmd.isSetLineNumber()) {
			Integer lineNumber = MessageUtility.getLineNumber(marker);
			if(lineNumber == null) {
				// Marker has a text location, not a line number.
				return ITestStatus.PASS;
			}
			
			if(lineNumber.intValue() != mmd.getLineNumber()) {
				buffer.addExecutionStatus(testName, subTaskName, ITestStatus.WARN, buffer, "INFO[wrong location]: expected " + mmd.getLineNumber() + ", got " + lineNumber + " on marker " + MessageUtility.toString(marker)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return ITestStatus.WARN;
			}
		}
		else if(mmd.isSetLocation()) {
			String location = MessageUtility.getLocation(marker);
			if(location == null) {
				// marker has a line number, not a text location
				return ITestStatus.PASS;
			}
			
			if(!(mmd.getLocation().equals(location))) {
				buffer.addExecutionStatus(testName, subTaskName, ITestStatus.WARN, buffer, "INFO[wrong location]: expected " + mmd.getLocation() + ", got " + location + " on marker " + MessageUtility.toString(marker)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return ITestStatus.WARN;
			}
		}
		
		return ITestStatus.PASS;
	}
	
	
	private MessageMetaData getMatchingMetaData(IBuffer buffer, List mmdList, IMarker marker) {
		int result = Collections.binarySearch(mmdList, marker, MessageUtility.getMessageComparator(buffer, isVerbose()));
		if(result < 0) {
			return null;
		}
		return (MessageMetaData)mmdList.get(result);
	}
}