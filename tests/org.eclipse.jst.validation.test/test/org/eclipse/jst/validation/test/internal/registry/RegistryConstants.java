package org.eclipse.jst.validation.test.internal.registry;

/**
 * Validation constants needed to declare an extension point, and
 * to implement an extension.
 */
public interface RegistryConstants {
	public static final String PLUGIN_ID = org.eclipse.jst.validation.test.BVTValidationPlugin.PLUGIN_ID;
	
	public static final String VALIDATOR_TESTCASE_EXT_PT_ID = "test"; // extension point declaration of the validator test case //$NON-NLS-1$
	public static final String VALIDATOR_SETUP_EXT_PT_ID = "testSetup"; // extension point declaration of the test case setup mechanism (i.e., import the EAR or JAR) //$NON-NLS-1$
	public static final String VALIDATOR_OPERATION_TESTCASE_EXT_PT_ID = "opTest"; // extension point declaration of the operation test case //$NON-NLS-1$
	
	public static final Double EJB20 = new Double(2.0); // EJB 2.0 input or 1.3 EAR
	public static final Double EJB11 = new Double(1.1); // EJB 1.1 input or 1.2 EAR
	public static final Double EJB = null; // common EJB level, such as reflection errors
	public static final String EJB20_MSSG = "ejb20"; // in a .properties line, if the message id contains this string, it's an EJB 2.0 rule //$NON-NLS-1$
	public static final String EJB11_MSSG = "ejb11"; // in a .properties line, if the message id contains this string, it's an EJB 1.1 rule //$NON-NLS-1$
	public static final String EJB_MSSG = null; // in a .properties file, if the message is neither EJB20 nor EJB11, the rule is cross-specs. (e.g. reflection errors) //$NON-NLS-1$
	

	/*package*/ static final String ATT_VALIDATOR = "validator"; // the validator  //$NON-NLS-1$
	/*package*/ static final String ATT_PLUGIN = "plugin"; // identifies the plugin which has the class //$NON-NLS-1$
	/*package*/ static final String TAG_TEST = "test"; //$NON-NLS-1$
	/*package*/ static final String TAG_IMPORT = "import"; //$NON-NLS-1$
	/*package*/ static final String ATT_OPERATION = "operation"; // the fully-qualified name of the IImportOperation which is used to import the input //$NON-NLS-1$
	/*package*/ static final String ATT_INPUT = "input"; //$NON-NLS-1$
	/*package*/ static final String ATT_VERSION = "version"; // the version of the input, e.g. EJB 1.1 vs. EJB 2.0 //$NON-NLS-1$
	/*package*/ static final String TAG_MESSAGE = "message"; //$NON-NLS-1$
	/*package*/ static final String ATT_PREFIX = "prefix"; //$NON-NLS-1$
	/*package*/ static final String ATT_SEVERITY = "severity"; //$NON-NLS-1$
	/*package*/ static final String ATT_PROJECT = "project"; //$NON-NLS-1$
	/*package*/ static final String ATT_RESOURCE = "resource"; //$NON-NLS-1$
	/*package*/ static final String ATT_LOCATION = "location"; //$NON-NLS-1$
	/*package*/ static final String ATT_TEXT = "text"; //$NON-NLS-1$
	/*package*/ static final String ATT_VISIBLE = "visible"; // Is this test visible on the Test Collector menu? //$NON-NLS-1$
}
