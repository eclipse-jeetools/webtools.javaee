package org.eclipse.jst.j2ee.model.internal.validation;


import org.eclipse.osgi.util.NLS;

public class ERefValidationMessageResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "erefvalidation";//$NON-NLS-1$

	private ERefValidationMessageResourceHandler() {
		// Do not instantiate
	}

	public static String ERROR_EAR_MISSING_EREFNAME;
	public static String ERROR_EAR_INVALID_EREFTYPE;
	public static String ERROR_EAR_MISSING_EREFTYPE;
	public static String ERROR_EAR_MISSING_EREFHOME;
	public static String ERROR_EAR_MISSING_EREFREMOTE;
	public static String ERROR_EAR_MISSING_EJB_ROLE;
	public static String ERROR_EAR_DUPLICATE_RESREF;
	public static String UNRESOLVED_EJB_REF_WARN_;
	public static String ERROR_EAR_DUPLICATE_SERVICEREF;
	public static String ERROR_EAR_DUPLICATE_SECURITYROLEREF;
	public static String ERROR_EAR_DUPLICATE_EJBREF;
	public static String ERROR_EAR_DUPLICATE_RESENVREF;
	public static String ERROR_EAR_DUPLICATE_MESSSAGEDESTINATIONREF;
	public static String ERROR_EAR_MISSING_MESSSAGEDESTINATION;
	public static String ERROR_UNRESOLVED_MDB_MISSING_MESSAGE_DESTINATION;
	public static String ERROR_EAR_MISSING_EMPTY_MESSSAGEDESTINATION;

	

	static {
		NLS.initializeMessages(BUNDLE_NAME, ERefValidationMessageResourceHandler.class);
	}	

}
