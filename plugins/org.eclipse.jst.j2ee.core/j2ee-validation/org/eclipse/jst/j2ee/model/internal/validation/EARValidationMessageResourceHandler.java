package org.eclipse.jst.j2ee.model.internal.validation;


import org.eclipse.osgi.util.NLS;

public class EARValidationMessageResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "earvalidation";//$NON-NLS-1$

	private EARValidationMessageResourceHandler() {
		// Do not instantiate
	}


	public static String ERROR_EAR_VALIDATION_FAILED;
	public static String ERROR_EAR_INVALID_EAR_FILE;
	public static String ERROR_EAR_DUPLICATE_ROLES;
	public static String MESSAGE_EAR_NO_MODULE_URI;
	public static String MESSAGE_EAR_MISSING_URI;
	public static String EAR_DD_PARSE_LINECOL;
	public static String EAR_DD_PARSE_LINE;
	public static String EAR_DD_PARSE_NOINFO;
	public static String EAR_DD_CANNOT_OPEN_DD;
	public static String URI_CONTAINS_SPACES_ERROR_;
	public static String MESSAGE_EAR_DUPLICATE_URI_ERROR_;
	public static String MESSAGE_EAR_DUPICATE_ROOTCONTEXT_ERROR_;
	public static String MESSAGE_EMPTY_ALT_DD_ERROR_;
	public static String DUPLICATE_UTILJAR_FOR_PROJECT_NAME_ERROR_;
	public static String DUPLICATE_MODULE_FOR_PROJECT_NAME_ERROR_;
	public static String MESSAGE_UTIL_URI_NAME_COLLISION_ERROR_;
	public static String MESSAGE_UTIL_PROJECT_NAME_COLLISION_ERROR_;
	public static String ERROR_READING_MANIFEST_ERROR_;
	public static String MANIFEST_LINE_END_ERROR_;
	public static String MANIFEST_LINE_EXCEEDS_LENGTH_ERROR_;
	public static String INVALID_URI_FOR_MODULE_ERROR_;
	public static String EAR_INVALID_DOC_TYPE_ERROR_;
	public static String MODULE_DD_PARSE_LINECOL_ERROR_;
	public static String MODULE_DD_PARSE_LINE_ERROR_;
	public static String MODULE_DD_PARSE_NOINFO_ERROR_;
	public static String EJB_BEAN_EJB_LINK_INTEFACE_MISMATCH_ERROR_;
	public static String WARNING_METAFOLDER_MISSING;
	public static String WARNING_FILE_MISSING;
	public static String MESSAGE_INVALID_ALT_DD_WARN_;
	public static String INVALID_MANIFEST_CLASSPATH_ONE_WARN_;
	public static String INVALID_MANIFEST_CLASSPATH_TWO_WARN_;
	public static String INVALID_MANIFEST_CLASSPATH_DEPENDENCY_WARN_;
	public static String PROJECT_IS_CLOSED_WARN_;
	public static String PROJECT_DOES_NOT_EXIST_WARN_;
	public static String MISSING_WEBNATURE_FORMODULE_WARN_;
	public static String DEPRECATED_ABSPATHS_WARN_;
	public static String INVALID_CONTEXTROOT_WEBMODULE_WARN_;
	public static String MISSING_CLIENTNATURE_FORMODULE_WARN_;
	public static String MISSING_EJBNATURE_FORMODULE_WARN_;
	public static String MISSING_PROJECT_FORMODULE_WARN_;
	public static String INVALID_CASE_FOR_MANIFEST_ERROR_;
	public static String URI_ALREADY_EXISTS_IN_EAR_WARN_;
	public static String MISSING_MODULE_EXTENSION_ERROR_;
	public static String PROJECT_SERVER_TARGET_DOES_NOT_MATCH_EAR;
	public static String NO_SERVER_TARGET_MODULE_IN_EAR_WITH_SERVER_TARGET;
	public static String NO_SERVER_TARGET_ON_EAR_WITH_MODULE_SERVER_TARGETS;
	public static String INVALID_EAR_SERVER_TARGET_FOR_14_SPEC_LEVEL;
	public static String INVALID_MODULE_SERVER_TARGET_FOR_14_SPEC_LEVEL;
	public static String MESSAGE_INCOMPATIBLE_SPEC_WARNING_;
	public static String MESSAGE_INCOMPATIBLE_13_SPEC_WARNING_;
	public static String MESSAGE_INCOMPATIBLE_14_SPEC_WARNING_;
	public static String EAR_VALIDATION_INTERNAL_ERROR_UI_;


	static {
		NLS.initializeMessages(BUNDLE_NAME, EARValidationMessageResourceHandler.class);
	}	
	
	/**
	 *   String message;
      ...
      // no args
      message = EARValidationMessageResourceHandler.key_one;
      ...
      // bind one arg
      message = NLS.bind(EARValidationMessageResourceHandler.key_two, "example usage"); //$NON-NLS-1$

	 */
}
