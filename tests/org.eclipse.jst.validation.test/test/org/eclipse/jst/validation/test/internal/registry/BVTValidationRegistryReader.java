package org.eclipse.jst.validation.test.internal.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.IOperationRunnable;
import org.eclipse.jst.validation.test.setup.IBuffer;
import org.eclipse.jst.validation.test.setup.IImportOperation;
import org.eclipse.wst.validation.internal.ValidationRegistryReader;
import org.eclipse.wst.validation.internal.ValidatorMetaData;

/**
 * BVTValidationRegistryReader is a singleton who reads the plugin registry
 * for Validator extensions. The read is done once (in the constructor). 
 * 
 * 
 * <extension
 *		point = "com.ibm.etools.validation.fvt.test"
 *		id = "ValidatorFVT"
 *		name = "Validator FVT Test">
 *			<test project="projectName" 
 * 				  validator="com.ibm.etools.validation.ejb.EJBValidator"
 * 				  version="1.1">
 *				<message id="CHKJ2816" resource="ejb-jar.xml" location="216"/>
 *				<message id="CHKJ2816" resource="ejb-jar.xml" location="237"/>
 *				<message id="CHKJ2816" resource="ejb-jar.xml" location="258"/>
 *				<message id="CHKJ2816" resource="ejb-jar.xml" location="279"/>
 *				<message id="CHKJ2816" resource="ejb-jar.xml" location="300"/>
 *			</test>
 * 			... as many <test> as needed
 * </extension>
 */
public final class BVTValidationRegistryReader implements RegistryConstants {
	private static BVTValidationRegistryReader inst = null;
	private static OperationTestReader _opReader = null;
	private static TestSetupReader _setupReader = null;
	private static ValidationTestReader _valReader = null;

	/**
	 * The registry is read once - when this class is instantiated.
	 */
	private BVTValidationRegistryReader() {
		super();
	}

	/**
	 * Return all visible test cases for all projects.
	 */
	public ITestcaseMetaData[] getTests(IProgressMonitor monitor) {
		Set tests = new HashSet();
		ValidatorTestcase[] vts = getValidatorTests(monitor, (String)null);
		for(int j=0; j<vts.length; j++) {
			tests.add(vts[j]);
		}
		
		OperationTestcase[] ots = getOperationTests(monitor, (String)null);
		for(int k=0; k<ots.length; k++) {
			tests.add(ots[k]);
		}

		ITestcaseMetaData[] result = new ITestcaseMetaData[tests.size()];
		tests.toArray(result);
		return result;
	}
	
	/**
	 * Return all visible test cases for a project.
	 */
	public ITestcaseMetaData[] getTests(IProgressMonitor monitor, IProject[] projects) {
		Set tests = new HashSet();
		for(int i=0; i<projects.length; i++) {
			IProject project = projects[i];
			ValidatorTestcase[] vts = getValidatorTests(monitor, project);
			for(int j=0; j<vts.length; j++) {
				tests.add(vts[j]);
			}
			
			OperationTestcase[] ots = getOperationTests(monitor, project);
			for(int k=0; k<ots.length; k++) {
				tests.add(ots[k]);
			}
		}
		
		ITestcaseMetaData[] result = new ITestcaseMetaData[tests.size()];
		tests.toArray(result);
		return result;
	}
	

	/**
	 * Return all validator tests for a project.
	 */
	public ValidatorTestcase[] getAllValidatorTests(IProgressMonitor monitor, IProject project) throws IllegalArgumentException {
		ValidationTestReader reader = getValidationTestReader();
		return reader.getValidatorTests(monitor, project.getName());
	}

	/**
	 * Return all test cases of the named validators on the given project.
	 */	
	public ValidatorTestcase[] getAllValidatorTests(IProgressMonitor monitor, IProject project, String[] validatorNames) {
		Set testSet = new HashSet();		
		ValidatorTestcase[] tests = getAllValidatorTests(monitor, project);
		for(int i=0; i<validatorNames.length; i++) {
			String validator = validatorNames[i];
			for(int j=0; j<tests.length; j++) {
				ValidatorTestcase tmd = tests[j];
				if(tmd.getValidatorClass().equals(validator)) {
					testSet.add(tmd);
					break;
				}
			}
		}
		
		ValidatorTestcase[] result = new ValidatorTestcase[testSet.size()];
		testSet.toArray(result);
		return result;
	}
	
	/**
	 * Return all of the visible test cases that register messages against this project.
	 */
	public ValidatorTestcase[] getValidatorTests(IProgressMonitor monitor, IProject project) throws IllegalArgumentException {
		return getValidatorTests(monitor, project.getName());
	}

	private ValidationTestReader getValidationTestReader() {
		if(_valReader == null) {
			_valReader = new ValidationTestReader();
		}
		return _valReader;
	}
	
	private OperationTestReader getOperationTestReader() {
		if(_opReader == null) {
			_opReader = new OperationTestReader();
		}
		return _opReader;
	}
	
	private TestSetupReader getTestSetupReader() {
		if (_setupReader == null) {
			_setupReader = new TestSetupReader();
		}
		return _setupReader;
	}
	
	/**
	 * Return the test cases named testName, or if testName is null, return all test cases.
	 */
	public ValidatorTestcase[] getValidatorTests(IProgressMonitor monitor, String testName) throws IllegalArgumentException {
		return getValidationTestReader().getVisibleValidatorTests(monitor, testName);
	}
	
	/**
	 * Return the test cases that register messages against this project.
	 */
	public OperationTestcase[] getOperationTests(IProgressMonitor monitor, IProject project) throws IllegalArgumentException {
		return getOperationTests(monitor, project.getName());
	}

	/**
	 * Return the test cases named testName, or if testName is null, return all test cases.
	 */
	public OperationTestcase[] getOperationTests(IProgressMonitor monitor, String testName) throws IllegalArgumentException {
		return getOperationTestReader().getOperationTests(monitor, testName);
	}

	/**
	 * If files need to be imported, import them now (into projects that are named
	 * the same as the files.)
	 * 
	 * If ITestcaseMetaData is not null, return the TestSetupImport for that particular test.
	 * If ITestcaseMetaData is null, return all TestSetupImport.
	 */
	public TestSetupImport[] getTestSetup(IBuffer buffer, String dir, ITestcaseMetaData tmd, boolean verbose) {
		return getTestSetupReader().getTestSetup(buffer, dir, tmd, verbose);
	}

	/**
	 * Returns the singleton ValidationTVTRegistryReader.
	 */
	public static BVTValidationRegistryReader getReader() {
		if (inst == null) {
			inst = new BVTValidationRegistryReader();
		}
		return inst;
	}

	private class ValidationTestReader {
		private Map _validatorTests = null;
		
		ValidationTestReader() {
		}
		
		public ValidatorTestcase[] getVisibleValidatorTests(IProgressMonitor monitor, String testName) throws IllegalArgumentException {
			ValidatorTestcase[] vts = getValidatorTests(monitor, testName);
			ValidatorTestcase[] temp = new ValidatorTestcase[vts.length];
			int count = 0;
			for(int i=0; i<vts.length; i++) {
				ValidatorTestcase vt = vts[i];
				if(vt.isVisible()) {
					temp[count++] = vt;
				}
			}
			ValidatorTestcase[] result = new ValidatorTestcase[count];
			System.arraycopy(temp, 0, result, 0, count);
			return result;
		}
		
		public ValidatorTestcase[] getValidatorTests(IProgressMonitor monitor, String testName) throws IllegalArgumentException {
			if (_validatorTests == null) {
				_validatorTests = new HashMap();
				readTestcaseRegistry(monitor);
			}
	
			Collection tmds = null;
			if (testName == null) {
				tmds = new HashSet();
				Iterator iterator = _validatorTests.values().iterator();
				while (iterator.hasNext()) {
					tmds.addAll((Collection) iterator.next());
				}
			}
			else {
				tmds = (Collection) _validatorTests.get(testName);
			}
			if ((tmds == null) || (tmds.size() == 0)) {
				return new ValidatorTestcase[0];
			}
			ValidatorTestcase[] result = new ValidatorTestcase[tmds.size()];
			tmds.toArray(result);
			return result;
		}

		/**
		 * Return the name of the test case if it exists. Otherwise, return null.
		 */
		private String getProject(IConfigurationElement element) {
			if (element == null) {
				return null;
			}

			return element.getAttribute(ATT_PROJECT);
		}

		private MessageMetaData[] getMessages(ValidatorTestcase tmd, IConfigurationElement testElement) {
			IConfigurationElement[] messages = testElement.getChildren(TAG_MESSAGE);
			if ((messages == null) || (messages.length == 0)) {
				// No messages are expected.
				return null;
			}

			MessageMetaData[] mmdList = new MessageMetaData[messages.length];
			int count = 0;
			for (int i = 0; i < messages.length; i++) {
				IConfigurationElement message = messages[i];
				String prefix = message.getAttribute(ATT_PREFIX);
				if (prefix == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Every <message> must have a prefix."); //$NON-NLS-1$
					}
					continue;
				}

				// The following attributes are optional.			
				String resourceName = message.getAttribute(ATT_RESOURCE);
				String location = message.getAttribute(ATT_LOCATION);
				String text = message.getAttribute(ATT_TEXT);
				int lineNumber = -1;
				MessageMetaData mmd = null;
				if ((location != null)) {
					try {
						lineNumber = Integer.parseInt(location);
						mmd = new MessageMetaData(tmd, prefix, resourceName, lineNumber, text);
					}
					catch (NumberFormatException exc) {
						// Don't need to log - the location just isn't a number.
					}
				}

				if (mmd == null) {
					mmd = new MessageMetaData(tmd, prefix, resourceName, location, text);
				}

				mmdList[count++] = mmd;
			}

			if (count == mmdList.length) {
				return mmdList;
			}
			else {
				MessageMetaData[] result = new MessageMetaData[count];
				System.arraycopy(mmdList, 0, result, 0, count);
				return result;
			}
		}

		private void addTest(IProgressMonitor monitor, IExtension extension, IConfigurationElement[] testElements) {
			monitor.subTask("Reading test cases; please wait..."); //$NON-NLS-1$
			String pluginId = extension.getDeclaringPluginDescriptor().getUniqueIdentifier();
			for (int i = 0; i < testElements.length; i++) {
				monitor.subTask("Reading test case " + (i + 1) + " of " + testElements.length); //$NON-NLS-1$ //$NON-NLS-2$
				IConfigurationElement test = testElements[i];

				String projectName = getProject(test);
				if (projectName == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Every test must name the project that it verifies. " + test.toString()); //$NON-NLS-1$
					}
					continue;
				}

				ValidatorMetaData vmd = getValidatorMetaData(test);
				if (vmd == null) {
					// already logged, so just read the next test case.
					continue;
				}

				String inputFileName = getInputFileName(test);
				if (inputFileName == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Every test must name the file to be imported and then tested." + test.toString()); //$NON-NLS-1$
					}
					continue;
				}

				boolean visible = getVisible(test);

				ValidatorTestcase tmd = new ValidatorTestcase(pluginId, projectName, vmd, inputFileName, visible);
				tmd.setMessages(getMessages(tmd, test));

				// Now add the test case to the Map, with the project name as the key into 
				// the map.
				Set prjTmd = (Set) _validatorTests.get(projectName);
				if (prjTmd == null) {
					prjTmd = new HashSet();
				}
				prjTmd.add(tmd);
				_validatorTests.put(projectName, prjTmd);
			}
		}

		/**
		 * Retrieve an instance of the class with the given fully-qualified name.
		 * If no such class can be found, return null.
		 */
		private ValidatorMetaData getValidatorMetaData(IConfigurationElement element) {
			if (element == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "IConfigurationElement is null"); //$NON-NLS-1$
				}
				return null;
			}

			String validatorClass = element.getAttribute(ATT_VALIDATOR);
			try {
				if (validatorClass == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Cannot locate validator attribute"); //$NON-NLS-1$
					}
				}
				ValidatorMetaData vmd = ValidationRegistryReader.getReader().getValidatorMetaData(validatorClass);
				if (vmd == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Could not find validator " + validatorClass); //$NON-NLS-1$
					}
				}
				return vmd;
			}
			catch (Throwable exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "Throwable caught while retrieving IValidator " + validatorClass); //$NON-NLS-1$
					logger.write(Level.SEVERE, exc);
				}
				return null;
			}
		}

		/**
		 * Reads the registry to find the test cases that have been implemented.
		 */
		private void readTestcaseRegistry(IProgressMonitor monitor) {
			// Get the extensions that have been registered.
			IExtensionPoint validatorEP = getTestcaseExtensionPoint();
			if (validatorEP == null) {
				// error logged in getValidatorTVTExtensionPoint
				return;
			}
			IExtension[] extensions = validatorEP.getExtensions();

			// find all runtime implementations
			for (int i = 0; i < extensions.length; i++) {
				readTestcaseExtension(monitor, extensions[i]);
			}
		}

		/**
		 * Reads one extension by looping through its configuration elements.
		 */
		private void readTestcaseExtension(IProgressMonitor monitor, IExtension extension) {
			IConfigurationElement[] elements = extension.getConfigurationElements();
			if ((elements == null) || (elements.length == 0)) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "No tests found for " + extension.getLabel()); //$NON-NLS-1$
				}
				return;
			}
			addTest(monitor, extension, elements);
		}

		/**
		 * Returns the operation extension point
		 */
		private IExtensionPoint getTestcaseExtensionPoint() {
			IPluginRegistry registry = Platform.getPluginRegistry();
			IExtensionPoint extensionPoint = registry.getExtensionPoint(PLUGIN_ID, VALIDATOR_TESTCASE_EXT_PT_ID);
			if (extensionPoint == null) {
				// If this happens it means that someone removed the "validator" extension point declaration 
				// from our plugin.xml file.
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, PLUGIN_ID + "." + VALIDATOR_TESTCASE_EXT_PT_ID + " has been removed from the validation TVT plugin.xml file"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			return extensionPoint;
		}

		/**
		 * Retrieve the name of the input which must be imported before the test case can be run.
		 * Return null if no input name is set.
		 */
		private boolean getVisible(IConfigurationElement element) {
			if (element == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "getVisible::IConfigurationElement is null"); //$NON-NLS-1$
				}
				return false;
			}

			String attrib = element.getAttribute(ATT_VISIBLE);
			if (attrib == null) {
				// default is visible
				return true;
			}

			return Boolean.valueOf(attrib).booleanValue();
		}

		/**
		 * Retrieve the name of the input which must be imported before the test case can be run.
		 * Return null if no input name is set.
		 */
		private String getInputFileName(IConfigurationElement element) {
			if (element == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "getInputFileName::IConfigurationElement is null"); //$NON-NLS-1$
				}
				return null;
			}

			return element.getAttribute(ATT_INPUT);
		}

	}

	private class OperationTestReader {
		private Map _operationTests = null;

		OperationTestReader() {
		}
		
		/**
		 * Return the test cases named testName, or if testName is null, return all test cases.
		 */
		public OperationTestcase[] getOperationTests(IProgressMonitor monitor, String testName) throws IllegalArgumentException {
			if (_operationTests == null) {
				_operationTests = new HashMap();
				readOperationTestcaseRegistry(monitor);
			}

			Collection tmds = null;
			if (testName == null) {
				tmds = new HashSet();
				Iterator iterator = _operationTests.values().iterator();
				while (iterator.hasNext()) {
					tmds.addAll((Collection) iterator.next());
				}
			}
			else {
				tmds = (Collection) _operationTests.get(testName);
			}
			if ((tmds == null) || (tmds.size() == 0)) {
				return new OperationTestcase[0];
			}
			OperationTestcase[] result = new OperationTestcase[tmds.size()];
			tmds.toArray(result);
			return result;
		}

		/**
		 * Retrieve the name of the input which must be imported before the test case can be run.
		 * Return null if no input name is set.
		 */
		private String getInputFileName(IConfigurationElement element) {
			if (element == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "getInputFileName::IConfigurationElement is null"); //$NON-NLS-1$
				}
				return null;
			}

			return element.getAttribute(ATT_INPUT);
		}

		private void addOperationTest(IProgressMonitor monitor, IExtension extension, IConfigurationElement[] testElements) {
			monitor.subTask("Reading test cases; please wait..."); //$NON-NLS-1$
			String pluginId = extension.getDeclaringPluginDescriptor().getUniqueIdentifier();
			for (int i = 0; i < testElements.length; i++) {
				monitor.subTask("Reading test case " + (i + 1) + " of " + testElements.length); //$NON-NLS-1$ //$NON-NLS-2$
				IConfigurationElement test = testElements[i];

				String projectName = getProject(test);
				if (projectName == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Every test must name the project that it verifies. " + test.toString()); //$NON-NLS-1$
					}
					continue;
				}

				IOperationRunnable op = getOperation(test);
				if (op == null) {
					// already logged, so just read the next test case.
					continue;
				}

				String inputFileName = getInputFileName(test);
				if (inputFileName == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Every test must name the file to be imported and then tested." + test.toString()); //$NON-NLS-1$
					}
					continue;
				}

				String id = extension.getLabel();

				OperationTestcase tmd = new OperationTestcase(pluginId, projectName, id, inputFileName, op);

				// Now add the test case to the Map, with the project name as the key into 
				// the map.
				Set prjTmd = (Set) _operationTests.get(projectName);
				if (prjTmd == null) {
					prjTmd = new HashSet();
				}
				prjTmd.add(tmd);
				_operationTests.put(projectName, prjTmd);
			}
		}

		/**
		 * Reads one extension by looping through its configuration elements.
		 */
		private void readOperationTestcaseExtension(IProgressMonitor monitor, IExtension extension) {
			IConfigurationElement[] elements = extension.getConfigurationElements();
			if ((elements == null) || (elements.length == 0)) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "No tests found for " + extension.getLabel()); //$NON-NLS-1$
				}
				return;
			}
			addOperationTest(monitor, extension, elements);
		}

		/**
		 * Reads the registry to find the test cases that have been implemented.
		 */
		private void readOperationTestcaseRegistry(IProgressMonitor monitor) {
			// Get the extensions that have been registered.
			IExtensionPoint opEP = getOperationTestcaseExtensionPoint();
			if (opEP == null) {
				// error logged in getValidatorTVTExtensionPoint
				return;
			}
			IExtension[] extensions = opEP.getExtensions();

			// find all runtime implementations
			for (int i = 0; i < extensions.length; i++) {
				readOperationTestcaseExtension(monitor, extensions[i]);
			}
		}

		/**
		 * Return the name of the test case if it exists. Otherwise, return null.
		 */
		private String getProject(IConfigurationElement element) {
			if (element == null) {
				return null;
			}

			return element.getAttribute(ATT_PROJECT);
		}

		/**
		 * Returns the TestCase extension point
		 */
		private IExtensionPoint getOperationTestcaseExtensionPoint() {
			IPluginRegistry registry = Platform.getPluginRegistry();
			IExtensionPoint extensionPoint = registry.getExtensionPoint(PLUGIN_ID, VALIDATOR_OPERATION_TESTCASE_EXT_PT_ID);
			if (extensionPoint == null) {
				// If this happens it means that someone removed the "optest" extension point declaration 
				// from our plugin.xml file.
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, PLUGIN_ID + "." + VALIDATOR_OPERATION_TESTCASE_EXT_PT_ID + " has been removed from the validation TVT plugin.xml file"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			return extensionPoint;
		}

		/**
		 * Retrieve an instance of the class with the given fully-qualified name.
		 * If no such class can be found, return null.
		 */
		private IOperationRunnable getOperation(IConfigurationElement element) {
			if (element == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "IConfigurationElement is null"); //$NON-NLS-1$
				}
				return null;
			}

			String opClass = element.getAttribute(ATT_OPERATION);
			try {
				if (opClass == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Cannot locate operation attribute"); //$NON-NLS-1$
					}
				}

				IOperationRunnable op = (IOperationRunnable) element.createExecutableExtension(ATT_OPERATION);
				if (op == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						logger.write(Level.SEVERE, "Could not find operation " + opClass); //$NON-NLS-1$
					}
				}
				return op;
			}
			catch (Throwable exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "Throwable caught while retrieving IValidator " + opClass); //$NON-NLS-1$
					logger.write(Level.SEVERE, exc);
				}
				return null;
			}
		}

	}

	private class TestSetupReader {
		private TestSetupImport[] _testSetup = null;

		TestSetupReader() {
		}
		
		/**
		 * Retrieve the name of the input which must be imported before the test case can be run.
		 * Return null if no input name is set.
		 */
		private String getInputFileName(IConfigurationElement element) {
			if (element == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, "getInputFileName::IConfigurationElement is null"); //$NON-NLS-1$
				}
				return null;
			}

			return element.getAttribute(ATT_INPUT);
		}

		/**
		 * If files need to be imported, import them now (into projects that are named
		 * the same as the files.)
		 * 
		 * If ITestcaseMetaData is not null, return the TestSetupImport for that particular test.
		 * If ITestcaseMetaData is null, return all TestSetupImport.
		 */
		public TestSetupImport[] getTestSetup(IBuffer buffer, String dir, ITestcaseMetaData tmd, boolean verbose) {
			if (_testSetup == null) {
				readSetupRegistry(buffer, verbose);
			}
			if (tmd != null) {
				for (int j = 0; j < _testSetup.length; j++) {
					TestSetupImport tsi = _testSetup[j];
					if (tsi.getFileName().endsWith(tmd.getInputFileName())) {
						return new TestSetupImport[] { tsi };
					}
				}
				return new TestSetupImport[0];
			}
			return _testSetup;
		}
	
		private void addSetup(IBuffer buffer, IConfigurationElement[] importElements, boolean verbose) {
			if ((importElements == null) || (importElements.length == 0)) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					String message = "No test setup found"; //$NON-NLS-1$
					logger.write(Level.SEVERE, message);
					buffer.write(message);
				}
				return;
			}

			TestSetupImport[] temp = new TestSetupImport[importElements.length];
			int count = 0;
			for (int i = 0; i < importElements.length; i++) {
				IConfigurationElement importElement = importElements[i];
				String inputFileName = getInputFileName(importElement);
				if (inputFileName == null) {
					// Already logged in getInputFileName
					continue;
				}

				IImportOperation op = getImportOperation(buffer, importElement);
				if (op == null) {
					buffer.write("Import operation cannot be found. Ignoring import."); //$NON-NLS-1$
					continue;
				}
				TestSetupImport ts = new TestSetupImport(op, inputFileName);
				temp[count++] = ts;
			}

			if (_testSetup == null) {
				if (count == temp.length) {
					_testSetup = temp;
				}
				else {
					_testSetup = new TestSetupImport[count];
					System.arraycopy(temp, 0, _testSetup, 0, count);
				}
			}
			else {
				TestSetupImport[] newTestSetup = new TestSetupImport[_testSetup.length + count];
				System.arraycopy(_testSetup, 0, newTestSetup, 0, _testSetup.length);
				System.arraycopy(temp, 0, newTestSetup, _testSetup.length, count);
				_testSetup = newTestSetup;
			}
		}

		/**
		 * Reads one extension by looping through its configuration elements.
		 */
		private void readSetupExtension(IBuffer buffer, IExtension extension, boolean verbose) {
			IConfigurationElement[] elements = extension.getConfigurationElements();
			addSetup(buffer, elements, verbose);
		}

		/**
		 * Returns the TestCaseSetup extension point
		 */
		private IExtensionPoint getSetupExtensionPoint() {
			IPluginRegistry registry = Platform.getPluginRegistry();
			IExtensionPoint extensionPoint = registry.getExtensionPoint(PLUGIN_ID, VALIDATOR_SETUP_EXT_PT_ID);
			if (extensionPoint == null) {
				// If this happens it means that someone removed the "validator" extension point declaration 
				// from our plugin.xml file.
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, PLUGIN_ID + "." + VALIDATOR_SETUP_EXT_PT_ID + " has been removed from the validation TVT plugin.xml file"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			return extensionPoint;
		}

		/**
		 * Reads the registry to find the test setup that must be performed.
		 */
		private void readSetupRegistry(IBuffer buffer, boolean verbose) {
			// Get the extensions that have been registered.
			IExtensionPoint validatorEP = getSetupExtensionPoint();
			if (validatorEP == null) {
				// error logged in getValidatorTVTExtensionPoint
				buffer.write("Extension point is missing. Cannot import test cases."); //$NON-NLS-1$
				return;
			}
			IExtension[] extensions = validatorEP.getExtensions();
			if (extensions.length == 0) {
				buffer.write("No extensions found. Cannot import test cases."); //$NON-NLS-1$
				return;
			}

			// find all runtime implementations
			buffer.getProgressMonitor().subTask("Reading setup <import> extensions; please wait..."); //$NON-NLS-1$
			for (int i = 0; i < extensions.length; i++) {
				buffer.getProgressMonitor().subTask("Reading setup <import> " + (i + 1) + " of " + extensions.length); //$NON-NLS-1$ //$NON-NLS-2$
				readSetupExtension(buffer, extensions[i], verbose);
			}
		}

		/**
		 * Return an instance of the operation which is used to import the input.
		 */
		private IImportOperation getImportOperation(IBuffer buffer, IConfigurationElement element) {
			if (element == null) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					String message = "IConfigurationElement is null"; //$NON-NLS-1$
					logger.write(Level.SEVERE, message);
					buffer.write(message);
				}
			}

			String opClass = element.getAttribute(ATT_OPERATION);
			try {
				if (opClass == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						String message = "Because there is no operation attribute, assuming that the input is a workspace."; //$NON-NLS-1$
						logger.write(Level.SEVERE, message);
						buffer.write(message);
					}
					return null;
				}

				Object temp = element.createExecutableExtension(ATT_OPERATION);
				if (temp == null) {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						String message = "operation " + opClass + " cannot be created. Ignoring the test case."; //$NON-NLS-1$ //$NON-NLS-2$
						logger.write(Level.SEVERE, message);
						buffer.write(message);
					}
					return null;
				}
				else if (temp instanceof IImportOperation) {
					return (IImportOperation) temp;
				}
				else {
					Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
					if (logger.isLoggingLevel(Level.SEVERE)) {
						String message = "operation must be an instance of IImportOperation. Ignoring the test case."; //$NON-NLS-1$
						logger.write(Level.SEVERE, message);
						buffer.write(message);
					}
					return null;
				}
			}
			catch (Throwable exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.SEVERE)) {
					String message = "Throwable caught while retrieving IImportOperation " + opClass + " Ignoring the test case."; //$NON-NLS-1$ //$NON-NLS-2$
					logger.write(Level.SEVERE, message);
					logger.write(Level.SEVERE, exc);
					buffer.write(message);
				}
				return null;
			}
		}

	}
}