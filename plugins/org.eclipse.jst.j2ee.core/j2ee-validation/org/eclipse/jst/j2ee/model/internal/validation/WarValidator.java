/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.common.internal.impl.EJBLocalRefImpl;
import org.eclipse.jst.j2ee.common.internal.impl.EjbRefImpl;
import org.eclipse.jst.j2ee.common.internal.impl.ResourceRefImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.ErrorCodeErrorPage;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.FormLoginConfig;
import org.eclipse.jst.j2ee.webapplication.HTTPMethodType;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.LoginConfig;
import org.eclipse.jst.j2ee.webapplication.MimeMapping;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.SessionConfig;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.TransportGuaranteeType;
import org.eclipse.jst.j2ee.webapplication.UserDataConstraint;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.validation.internal.ResourceConstants;
import org.eclipse.wst.validation.internal.ResourceHandler;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;

// import org.eclipse.jst.j2ee.internal.plugin.nls.ResourceHandler;

public class WarValidator extends J2EEValidatorNew implements WARMessageConstants {
	protected WARFile warFile;
	protected WebApp webDD;
	private Hashtable httpMethods = null;

	// Optional child validators
	// protected WebExtValidator webExtValidator;
	// protected WebBndValidator webBndValidator;


	/**
	 * RelationshipMapValidator constructor comment.
	 */
	public WarValidator() {
		super();
	}

	/**
	 * Will construct a HashTable of roles, and check for duplicates and null entries
	 * 
	 * @return java.util.Hashtable
	 * @param roles
	 *            org.eclipse.emf.common.util.EList
	 */
	public Hashtable getAndValidateSecurityRoles(EList roles) {

		Hashtable secRoles = new Hashtable();
		if (roles.isEmpty())
			return (secRoles);

		Iterator sRoles = roles.iterator();

		while (sRoles.hasNext()) {
			
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			SecurityRole role = (SecurityRole) sRoles.next();


			String name = role.getRoleName();
			if (name != null)
				name = name.trim();
			if (name == null || name.equals("")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Security_Role_Name_6;
				addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, role);
				continue;
			}
			if (secRoles.get(name) != null) { // Check for dups

				String[] parms = new String[1];
				parms[0] = name;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, role);
				continue;
			}
			secRoles.put(name, "Yea"); //$NON-NLS-1$
		}


		return secRoles;
	}

	/**
	 * <p>
	 * Answer the id of the resource bundle which is used by the receiver.
	 * </p>
	 */
	public String getBaseName() {
		return WAR_CATEGORY;
	}

	// Messaging helpers ...

	/**
	 * Returns the name of the Validator, as it should be displayed in the UI.
	 * 
	 * @see J2EEValidator#getName
	 */

	public String getName() {
		return WARValidationResourceHandler.Web_Archive_Validator_8;
	}

	// Messaging helpers ...

	/**
	 * Returns the name of the Validator, as it should be displayed in the UI.
	 * 
	 * @see J2EEValidator#getName
	 */

	public String getName(Locale locale) {
		return getMessage(null, "webArchiveValidator.name", locale); //$NON-NLS-1$
	}

	/**
	 * This is a utility function used by the validateSecurityConstraints.
	 * 
	 * @return boolean
	 */
	protected boolean isHttpMethod(String method) {


		if (httpMethods == null) { // Need to construct the Hashtable, once
			String[] mList = {"GET", "PUT", "HEAD", "TRACE", "POST", "DELETE", "OPTIONS"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

			httpMethods = new Hashtable();
			for (int i = 0; i < mList.length; i++)
				httpMethods.put(mList[i], "bla"); //$NON-NLS-1$
		}
		String compare = method.trim();
		return (httpMethods.get(compare) != null);
	}

	/**
	 * WAR validation is driven by 3 prong approach: o XML/DTD validation (this is now handled by
	 * the XML Validator) o Consistent web.xml data entry validation beyond DTD e.g., duplicate
	 * entries, null entries etc. This is also driven by this validator. o web.xml reference to
	 * resources in the "file system". This will not be provided by this validator. In the workbench
	 * this function is already provided by the link builder.
	 * 
	 */
	public void validate() throws ValidationException {
		validateMimeMapping();
		validateContextParameters();
		validateTagLibs();
		validateServletMappings(webDD.getServletMappings());
		validateWelcomeFileList(webDD.getFileList());
		validateErrorPages(webDD.getErrorPages());
		validateSecurityAndServlets();
		validateFilters(webDD.getFilters());
		validateFilterMappings(webDD.getFilterMappings());
		validateRefs();
		validateLoginConfig(webDD.getLoginConfig());
		validateEnvironmentEntries(webDD.getEnvironmentProperties());
		validateOther();
		validate14();
	}

	/**
	 * 
	 */
	private void validate14() {
		int versionId = webDD.getVersionID();
		if (versionId == J2EEVersionConstants.WEB_2_4_ID) {
			validateUrlPattern();
		}
	}

	/**
	 * 
	 */
	private void validateUrlPattern() {
		List servletMappings = webDD.getServletMappings();
		for (int i = 0; i < servletMappings.size(); i++) {
			ServletMapping mapping = (ServletMapping) servletMappings.get(i);
			String urlPattern = mapping.getUrlPattern();
			int newLineChar = urlPattern.indexOf(Character.LINE_SEPARATOR);
			if (newLineChar != -1) {
				String[] parms = new String[2];
				parms[0] = urlPattern;
				parms[1] = mapping.getServlet().getDisplayName();
				addError(WAR_CATEGORY, MESSAGE_URL_PATTERN_END_WITH_CARRAIGE_RETURN, parms, mapping);
			}
		}

	}

	/**
	 * This is the method which performs the validation on the MOF model. <br>
	 * <br>
	 * <code>helper</code> and <code>reporter</code> may not be null. <code>changedFiles</code>
	 * may be null, if a full build is desired. <br>
	 * <br>
	 * <code>helper</code> loads a EObject. The EObject is the root of the MOF model about to be
	 * validated. When this object is traversed, you can reach every element in the MOF model which
	 * needs to be validated. <br>
	 * <br>
	 * <code>reporter</code> is an instance of an IReporter interface, which is used for
	 * interaction with the user. <br>
	 * <br>
	 * <code>changedFiles</code> is an array of file names which have changed since the last
	 * validation. If <code>changedFiles</code> is null, or if it is an empty array, then a full
	 * build is performed. Otherwise, validation on just the files listed in the Vector is
	 * performed.
	 */
	public void validate(IValidationContext inHelper, IReporter inReporter) throws ValidationException {
		validateInJob(inHelper, inReporter);
	}
	
	
	public IStatus validateInJob(IValidationContext inHelper, IReporter inReporter) throws ValidationException {
		status = super.validateInJob(inHelper, inReporter);
		

		// First remove all previous msg. for this project
		_reporter.removeAllMessages(this, null); // Note the WarHelper will return web.xml with a
													// null object as well

		if( !_reporter.isCancelled()){
			
			warFile = (WARFile) _helper.loadModel(WAR_MODEL_NAME);
			
			try {
				if (warFile != null) {
					webDD = warFile.getDeploymentDescriptor();
					validate();
				} else {
					IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_INVALID_WAR_FILE);
					status = WTPCommonPlugin.createErrorStatus(errorMsg.getText());
					throw new ValidationException(errorMsg);
				}
	
			} catch (ValidationException ex) {
				throw ex;
			} catch (Exception e) {
				String[] parms = new String[1];
				parms[0] = e.toString();
				IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_WAR_VALIDATION_FAILED, parms);
				status = WTPCommonPlugin.createErrorStatus(errorMsg.getText());
				throw new ValidationException(errorMsg, e);
			}
		}else{
			String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
			status = WTPCommonPlugin.createCancelStatus(msg);
		}
		return status;
	}

	/**
	 * This validator can be used for validation when the reporter and helper have been supplied via
	 * the constructor.
	 */
	public void validate(Object aWarFile) throws ValidationException {

		try {
			warFile = (WARFile) aWarFile;
			webDD = warFile.getDeploymentDescriptor();

			validate();

		} catch (Exception e) {
			e.printStackTrace();
			String[] parms = new String[1];
			parms[0] = e.toString();
			IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_WAR_VALIDATION_FAILED, parms);
			throw new ValidationException(errorMsg);
		}
	}

	/**
	 * The auth-constraint element indicates the user roles that should be permitted access to this
	 * resource collection. The role used here must appear in a security-role-ref element. <!ELEMENT
	 * auth-constraint (description?, role-name*)> The role-name element contains the name of a
	 * security role. <!ELEMENT role-name (#PCDATA)> Creation date: (7/6/2001 3:39:34 PM)
	 * 
	 * @param authConstraint
	 *            org.eclipse.jst.j2ee.internal.webapplication.AuthConstraint
	 */
	public void validateAuthConstraint(AuthConstraint authConstraint, Hashtable secRoles) {

		EList roleList = authConstraint.getRoles();

		if (roleList != null && !roleList.isEmpty()) {
			Iterator authRoles = roleList.iterator();
			Hashtable remember = new Hashtable();
			while (authRoles.hasNext()) {
				String role = (String) authRoles.next();
				if (role == null || role.trim().equals("")) { //$NON-NLS-1$
					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_Role_Name_19;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, authConstraint);
					continue;
				}
				if (remember.get(role.trim()) != null) {
					String[] parms = new String[1];
					parms[0] = role;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, authConstraint);
					continue;
				}
				remember.put(role.trim(), "Yea"); //$NON-NLS-1$
				validateAuthSecRole("", role, secRoles, authConstraint); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Insert the method's description here. Creation date: (7/5/2001 2:20:02 PM)
	 */
	public void validateContextParameters() {

		// we must verify that every param-name is unique;
		// param-name appears in context parameters, filter
		// and servlets. We must check each.
		Hashtable remember = new Hashtable();

		// check context parameters - each param-name should be unique within the web application
		Iterator cparams = webDD.getContexts().iterator();
		while (cparams.hasNext()) {
			
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			ContextParam context = (ContextParam) cparams.next();

			String name = context.getParamName();
			if (name != null)
				name = name.trim();

			if (name == null || name.equals("")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Parameter_Name_25;

				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, context);
				continue;
			}
			// if (remember.get(name + value) != null) { // Check for dups
			if (remember.get(name) != null) { // Check for dups
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Parameter_Name_25 + ": " + name; //$NON-NLS-1$
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, context);
				continue;
			}
			// remember.put(name + value, "Yea");
			remember.put(name, "Yea"); //$NON-NLS-1$
		}


		// check servlet init-param - each param-name should be unique within a servlet
		Iterator servlets = webDD.getServlets().iterator();
		while (servlets.hasNext()) {
			Servlet nextServlet = (Servlet) servlets.next();
			Iterator params = nextServlet.getParams().iterator();
			remember.clear();
			while (params.hasNext()) {
				InitParam initParam = (InitParam) params.next();
				String name = initParam.getParamName();

				if (name != null)
					name = name.trim();

				if (name == null || name.equals("")) { //$NON-NLS-1$ 
					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_Parameter_Name_32;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, initParam);
					continue;
				}
				if (remember.get(name) != null) { // Check for dups

					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_Parameter_Name_25 + ": " + name; //$NON-NLS-1$
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, initParam);
					continue;
				}
				remember.put(name, "Yea"); //$NON-NLS-1$
			}
		}

		// check filter init-param - each param-name should be unique within a filter
		Iterator filters = webDD.getFilters().iterator();
		while (filters.hasNext()) {
			Filter nextFilter = (Filter) filters.next();
			Iterator params = nextFilter.getInitParams().iterator();
			remember.clear();
			while (params.hasNext()) {
				InitParam initParam = (InitParam) params.next();
				String name = initParam.getParamName();

				if (name != null)
					name = name.trim();

				if (name == null || name.equals("")) { //$NON-NLS-1$ 
					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_Parameter_Name_39;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, initParam);
					continue;
				}
				if (remember.get(name) != null) { // Check for dups
					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_Parameter_Name_25 + ": " + name;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, initParam);
					continue;
				}
				remember.put(name, "Yea"); //$NON-NLS-1$
			}
		}

	}

	/**
	 * Validate EJB references.
	 */

	public void validateEJBRefs(EjbRefImpl eref) {
		// try {

		EARFile earFile = warFile.getEARFile();
		EnterpriseBean eb = null;

		if (earFile != null)
			eb = earFile.getEnterpiseBeanFromRef(eref, warFile.getURI());

		if (!(eb == null)) {
			List ejbRefs = eb.getEjbRefs();

			int numRefs = ejbRefs.size();

			Set refSet = new HashSet(numRefs);

			for (int refNo = 0; refNo < numRefs; refNo++) {
				String nextName = ((EjbRefImpl) (ejbRefs.get(refNo))).getName();

				String[] parms = new String[1];
				parms[0] = eb.getName();
				if (!(refSet.add(nextName))) {
					addWarning(WAR_CATEGORY, ERROR_EAR_DUPLICATE_ROLES, parms, ejbRefs.get(refNo));
				}
			}
		}

		// } catch (UncontainedModuleFileException ue) {
		// String[] parms = new String[1];
		// parms[0] = warFile.getName();
		// addError(EREF_CATEGORY, ERROR_EAR_UNCONTAINED_MODULE_FILE_EXCEPTION, parms);
		// }
	}

	/**
	 * validate for duplicates in EAR Roles
	 */

	public void validateEJBRefs(List ejbRefs) {
		int numRefs = ejbRefs.size();
		Hashtable remember = new Hashtable();
		for (int refNo = 0; refNo < numRefs; refNo++) {
			EjbRefImpl eref = (EjbRefImpl) ejbRefs.get(refNo);
			if (eref.isSetType()) {
				String type = eref.getType().getName();
				if (type == null) {
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_EJB_REF_TYPE, null, eref); // Type
																										// must
																										// be
																										// of
																										// ENTITY
																										// or
																										// FOO
				}
			}
			if (eref.getName() != null) {
				String name = eref.getName();
				name = name.trim();

				if (name.equals("")) { //$NON-NLS-1$
					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_ejb_ref_name_44;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, eref);
					continue;
				}
				if (remember.get(name) != null) { // check for duplicates
					String[] parms = new String[1];
					parms[0] = name;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_EJB_REF, parms, eref);
					continue;
				}
				remember.put(name, "Yea"); //$NON-NLS-1$
			} else {
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_ejb_ref_name_44;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, eref);
				continue;
			}


			validateEJBRefs(eref);
			validateEJBRefManadatoryElements(eref, webDD.getDisplayName());
		}
	}


	/**
	 * validate for duplicates in EAR Roles
	 */

	public void validateEJBLocalRefs(List ejbRefs) {
		int numRefs = ejbRefs.size();
		Hashtable remember = new Hashtable();
		for (int refNo = 0; refNo < numRefs; refNo++) {
			EJBLocalRefImpl eref = (EJBLocalRefImpl) ejbRefs.get(refNo);
			if (eref.isSetType()) {
				String type = eref.getType().getName();
				if (type == null) {
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_EJB_REF_TYPE, null, eref); // Type
																										// must
																										// be
																										// of
																										// ENTITY
																										// or
																										// FOO
				}
			}
			if (eref.getName() != null) {
				String name = eref.getName();
				name = name.trim();

				if (name.equals("")) { //$NON-NLS-1$
					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_ejb_ref_name_44;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, eref);
					continue;
				}
				if (remember.get(name) != null) { // check for duplicates
					String[] parms = new String[1];
					parms[0] = name;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_EJB_REF, parms, eref);
					continue;
				}
				remember.put(name, "Yea"); //$NON-NLS-1$
			} else {
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_ejb_ref_name_44;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, eref);
				continue;
			}


			// validateEJBRefs(eref);
			// validateEJBRefManadatoryElements(eref, webDD.getDisplayName());
		}
	}

	/**
	 * Link build should verify location of exception class
	 */

	public void validateErrorPages(EList errorPageList) {
		Iterator errorPages = errorPageList.iterator();

		if (errorPageList == null || errorPageList.isEmpty())
			return;

		while (errorPages.hasNext()) {
			
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			ErrorPage nextPage = (ErrorPage) errorPages.next();
			String location = nextPage.getLocation();
			if (location == null || location.equals("")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Error_Location_47;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, nextPage);
			}
			if (!location.startsWith("/")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Error_Location_49;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_ERROR_PAGE, parms, nextPage);
			}


			// check for valid HTTP error code - must be an Integer, 3 digits, and the starting
			// digit must be a 1,2,3,4, or 5.
			if (nextPage.isErrorCode()) {
				ErrorCodeErrorPage ecep = (ErrorCodeErrorPage) nextPage;
				String errorCode = ecep.getErrorCode();

				boolean valid = false;
				if (errorCode.length() == 3) {
					try {
						Integer tempInt = new Integer(errorCode);
						// ok, it's a valid 3-digit integer
						int code = tempInt.intValue();
						if (code >= 100 && code < 600) {
							// valid HTTP status code - starting digit must be between 1-5
							valid = true;
						}
					} catch (NumberFormatException exc) {
						// Ignore
					}
				}
				if (!valid) {
					String[] parms = new String[1];
					parms[0] = errorCode;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_ERROR_CODE, parms, ecep);
				}

			}

			// If exception-type... the specified class must be or inherit from java.lang.Throwable
			else if (nextPage.isExceptionType()) {
				ExceptionTypeErrorPage etep = (ExceptionTypeErrorPage) nextPage;
				JavaClass javaType = etep.getExceptionType();
				String exceptionType = "java.lang.Throwable";//$NON-NLS-1$
				String specifiedClassName = etep.getExceptionTypeName();
				if (javaType != null) {
					ResourceSet lookupSet = javaType.eResource().getResourceSet();
					if (lookupSet != null) {
						if (!javaType.inheritsFrom(JavaRefFactory.eINSTANCE.reflectType(exceptionType, lookupSet).getWrapper())) {
							String[] parms = new String[1];
							parms[0] = specifiedClassName;
							addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_EXCEPTION_TYPE, parms, etep);
						}
					}
				} else {
					String[] parms = new String[1];
					parms[0] = "";//$NON-NLS-1$
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_EXCEPTION_TYPE, parms, etep);
				}
				// TBD
			} else { // Error Code
				// TBD
			}

			// TBD remember location/code/exception dups
		}

	}

	/**
	 * Validate the loginConfig section is correct
	 */
	public void validateLoginConfig(LoginConfig loginConfig) {
		// com.ibm.etools.validate.ValidatorManager.setResourceUtilClass(com.ibm.etools.validate.ui.UIResourceUtil.class);

		if (loginConfig != null) {
			String auth = loginConfig.getAuthMethod().getName();

			if (auth == null) {
				String[] parms = new String[0];
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_AUTH_METHOD, parms, loginConfig);
			}
			// Give a warning if form elements are supplied but FORM auth method is not used
			else if (!auth.equals("FORM")) { //$NON-NLS-1$
				FormLoginConfig cfg = loginConfig.getFormLoginConfig();
				if (cfg != null) {
					String[] parms = new String[1];
					parms[0] = auth;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_IGNORE_FORM_LOGIN, parms, loginConfig);
				}
			}
		}
	}

	/**
	 * Validate the loginConfig section is correct
	 */
	public void validateEnvironmentEntries(EList envEntries) {
		if (envEntries.isEmpty())
			return;

		// boolean isVersion22 = warFile.getDeploymentDescriptor().isVersion2_2Descriptor();
		Iterator entries = envEntries.iterator();
		Hashtable remember = new Hashtable();

		while (entries.hasNext()) {
			EnvEntry entry = (EnvEntry) entries.next();
			if (entry.getType().getName() == null) {
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_ENV_ENTRY, null, entry);
			}

			// check for duplicate env-entry-name's
			String name = entry.getName();
			if (name != null) {
				if (remember.get(name) != null) { // Check for dups
					String[] parms = new String[1];
					parms[0] = WARValidationResourceHandler.of_Type_Env_Entry_Name___88 + ": " + name;
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, entry);
				} else {
					remember.put(name, "Yea"); //$NON-NLS-1$
				}
			}
		}

	}


	/**
	 * Insert the method's description here. Creation date: (7/5/2001 12:11:23 PM)
	 */
	public void validateMimeMapping() {

		if (webDD.getMimeMappings().isEmpty())
			return;

		Iterator mimes = webDD.getMimeMappings().iterator();
		Hashtable remember = new Hashtable();

		while (mimes.hasNext()) {
			
			if( _reporter.isCancelled()){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);				
			}
			MimeMapping mimeMap = (MimeMapping) mimes.next();

			String ext = mimeMap.getExtension();
			String mtype = mimeMap.getMimeType();
			if (ext != null)
				ext = ext.trim();
			if (mtype != null)
				mtype = mtype.trim();

			if (ext == null || ext.equals("") || mtype == null || mtype.equals("")) { //$NON-NLS-1$ //$NON-NLS-2$
				String[] parms = new String[1];
				if (ext == null || ext.trim().equals("")) //$NON-NLS-1$
					parms[0] = WARValidationResourceHandler.of_Type_Mime_Extension_54;
				else
					parms[0] = WARValidationResourceHandler.of_Type_Mime_Type_55;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, mimeMap);
				continue;
			}
			if (remember.get(ext) != null) { // Check for dups

				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Mime_Extension___56 + ": " + ext;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, mimeMap);
				continue;
			}
			remember.put(ext, "Yea"); //$NON-NLS-1$
		}
	}

	/**
	 * Insert the method's description here. Creation date: (7/5/2001 11:46:58 AM)
	 */
	public void validateOther() {

		SessionConfig sessionCfg = webDD.getSessionConfig();
		if (sessionCfg != null) {
			int timeout = sessionCfg.getSessionTimeout();
			if (timeout == 0) {
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_SESSION_TIMEOUT, null, sessionCfg);
			}
		}



	}

	/**
	 * validateRefs(WebApp) - validate EJB and resource references - details tbd
	 */

	public void validateRefs() {
		EList ejbRefs = webDD.getEjbRefs();

		if (!ejbRefs.isEmpty())
			validateEJBRefs(ejbRefs);


		EList ejblocalRefs = webDD.getEjbLocalRefs();

		if (!ejblocalRefs.isEmpty())
			validateEJBLocalRefs(ejblocalRefs);


		EList resourceRefs = webDD.getResourceRefs();

		if (!resourceRefs.isEmpty()) {
			// validateResourceRefs(resourceRefs); // we want to do additional checks here

			int numRefs = resourceRefs.size();
			Set refSet = new HashSet(numRefs);
			boolean isVersion22 = warFile.getDeploymentDescriptor().getVersionID() <= J2EEVersionConstants.WEB_2_2_ID;

			for (int refNo = 0; refNo < numRefs; refNo++) {
				
				if( _reporter.isCancelled() ){
					String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
					status = WTPCommonPlugin.createCancelStatus(msg);
				}
				
				ResourceRefImpl ref = (ResourceRefImpl) (resourceRefs.get(refNo));
				String auth = ref.getAuth().getName();

				// Check: a res-auth element containing Container or Application must be supplied
				if (auth == null || !ref.isSetAuth()) {
					String[] parms = new String[1];
					parms[0] = ref.getName();
					String msgId = isVersion22 ? MESSAGE_WAR_VALIDATION_RES_AUTH_REQUIRED_22 : MESSAGE_WAR_VALIDATION_RES_AUTH_REQUIRED_23;
					addError(WAR_CATEGORY, msgId, parms, ref);
					
				} else if (isVersion22 && auth.equals("Application")) { //$NON-NLS-1$
					String[] parms = new String[1];
					parms[0] = ref.getName();
					addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_RES_AUTH_INVALID_22, parms, ref);
				} else if (!isVersion22 && auth.equals("SERVLET")) { //$NON-NLS-1$
					String[] parms = new String[1];
					parms[0] = ref.getName();
					addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_RES_AUTH_INVALID_23, parms, ref);
				}

				// validate res-sharing-scope is allowable value
				String sharingScope = ref.getResSharingScope().getName();
				if (!isVersion22) {
					if (sharingScope == null || !ref.isSetResSharingScope()) {
						String[] parms = new String[0];
						addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_RES_SHARING_SCOPE, parms, ref);
					}
				}

				String refName = ref.getName();
				String[] parms = new String[1];
				parms[0] = refName;
				if (!(refSet.add(refName))){
					addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_RESREF, parms, ref);
				}
			}
		}

	}


	/**
	 * Insert the method's description here. Creation date: (7/6/2001 4:11:09 PM)
	 * 
	 * @return boolean
	 * @param role
	 *            java.lang.String
	 * @param defineRoles
	 *            java.util.Hashtable
	 * 
	 * The auth-constraint only needs to have role-name specified. <auth-constraint> <description></description>
	 * <role-name>guest</role-name> </auth-constraint>
	 * 
	 */
	protected void validateAuthSecRole(String link, String role, Hashtable definedRoles, EObject targetObject) {
		String rName = role;
		String lName = link;
		// boolean validRole = true;
		// boolean validLink = true;

		if (rName != null)
			rName = rName.trim();
		if (lName != null)
			lName = lName.trim();

		if (rName != null) {
			if (!rName.equals("*") && !rName.equals("")) { //$NON-NLS-1$ //$NON-NLS-2$	
				if (definedRoles.get(rName) == null && definedRoles.get(lName) == null) {
					String[] parms = new String[1];
					parms[0] = rName;
					addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_SEC_ROLE_NAME, parms, targetObject);
				}
			}
		}
	}



	/**
	 * Insert the method's description here. Creation date: (7/6/2001 4:11:09 PM)
	 * 
	 * @return boolean
	 * @param role
	 *            java.lang.String
	 * @param defineRoles
	 *            java.util.Hashtable
	 * 
	 * 
	 * the security-role-ref must have a non-null role-name and the role-link must contain the name
	 * of a role specified in the security-role section <security-role-ref> <role-name>MyName</role-name>
	 * <role-link>ExistingRole</role-link> </security-role-ref>
	 * 
	 * 
	 */
	protected void validateSecRole(String link, String role, Hashtable definedRoles, EObject targetObject) {
		String rName = role;
		String lName = link;
		// boolean validRole = true;
		// boolean validLink = true;

		if (rName != null)
			rName = rName.trim();
		if (lName != null)
			lName = lName.trim();


		// the security role-name cannot be null
		if ((rName == null || rName.equals(""))) { //$NON-NLS-1$
			String[] parms = new String[1];
			parms[0] = rName;
			addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_SEC_ROLE_NAME, parms, targetObject);
		}

		if (rName == null || !rName.equals("*")) { //$NON-NLS-1$		
			// check that security role-link matches a defines security role
			if (lName != null && definedRoles.get(lName) == null) {

				String[] parms = new String[1];
				parms[0] = lName;
				addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_SEC_ROLE, parms, targetObject);
			}
		}
	}

	/**
	 * validateSecurity(WebApp) - validate security constraints, roles, and security role refs -
	 * details tbd
	 */

	public void validateSecurityAndServlets() {


		EList webRoles = webDD.getSecurityRoles();

		// Validate security roles
		Hashtable secRoles = getAndValidateSecurityRoles(webRoles);

		// Validate Servlets/JSPs and their role ref.
		validateServlets(webDD.getServlets(), secRoles);

		validateSecurityConstraints(webDD.getConstraints(), secRoles);

		// try {
		if (!(webRoles.isEmpty())) {
			EARFile module = warFile.getEARFile();
			if (module != null) {
				EList earRoleList = module.getDeploymentDescriptor().getSecurityRoles();
				validateWEBRolesWithEARRoles(earRoleList, webRoles);
			}
		}
		// } catch (UncontainedModuleFileException ue) {
		// String[] parms = new String[1];
		// parms[0] = warFile.getName();
		// addError(EREF_CATEGORY, ERROR_EAR_UNCONTAINED_MODULE_FILE_EXCEPTION, parms);
		// }
	}

	/**
	 * The security-constraint element is used to associate security constraints with one or more
	 * web resource collections <!ELEMENT security-constraint (web-resource-collection+,
	 * auth-constraint?, user-data-constraint?)>
	 * 
	 * @param constraints
	 *            org.eclipse.emf.common.util.EList
	 */
	public void validateSecurityConstraints(EList constraints, Hashtable secRoles) {

		if (constraints.isEmpty())
			return;

		Iterator constList = constraints.iterator();
		while (constList.hasNext()) {
			
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			SecurityConstraint constraint = (SecurityConstraint) constList.next();

			EList webResourceList = constraint.getWebResourceCollections();
			if (webResourceList == null || webResourceList.isEmpty()) {
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Web_Resource_Collection_64;
				addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, constraint);
				continue;
			}
			validateWebResourceCollections(webResourceList);

			AuthConstraint authConstraint = constraint.getAuthConstraint();
			if (authConstraint != null)
				validateAuthConstraint(authConstraint, secRoles);

			UserDataConstraint dataConstraint = constraint.getUserDataConstraint();
			if (dataConstraint != null && dataConstraint.getTransportGuarantee() != null) {
				// <!ELEMENT user-data-constraint (description?, transport-guarantee)>
				// The transport-guarantee element specifies that the communication
				// between client and server should be NONE, INTEGRAL, or
				// CONFIDENTIAL.

				// EEnumLiteral transport = dataConstraint.getTransportGuarantee();
				TransportGuaranteeType transport = dataConstraint.getTransportGuarantee();
				if (transport == null || !dataConstraint.isSetTransportGuarantee()) {
					addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_TRANSPORT, new String[0], dataConstraint);
				}
			}
		}
	}

	public void validateFilters(EList filterList) {
		if (filterList.isEmpty())
			return;

		Iterator filters = filterList.iterator();

		Hashtable remember = new Hashtable();

		while (filters.hasNext()) {
			
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			Filter nextFilter = (Filter) filters.next();
			String name = nextFilter.getName();
			if (name != null)
				name = name.trim();

			if (name == null || name.equals("")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Filter_Name_66;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, nextFilter);
				continue;
			}
			if (remember.get(name) != null) { // check for duplicates
				String[] parms = new String[1];
				parms[0] = name;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_FILTER, parms, nextFilter);
				continue;
			}
			remember.put(name, "Yea"); //$NON-NLS-1$
		}
	}

	/**
	 * validateFilterMappings(Elist(Filters's)) - for each filter mapping, make sure the named
	 * filter exists
	 */
	public void validateFilterMappings(EList filterMappingsList) {

		if (filterMappingsList.isEmpty())
			return;

		Iterator filterMappings = filterMappingsList.iterator();
		while (filterMappings.hasNext()) {
			FilterMapping nextMapping = (FilterMapping) filterMappings.next();

			// Mapping can be either servlet or url
			if (nextMapping.getUrlPattern() != null) {
				String[] parms = new String[1];
				String url = nextMapping.getUrlPattern();
				parms[0] = url;
				if (url == null) {
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_URL, parms, nextMapping);
					continue;
				}
				if (nextMapping.getFilter() == null || nextMapping.getFilter().equals("")) { //$NON-NLS-1$
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_BROKEN_FILTER_MAPPING, parms, nextMapping);
					continue;
				}

				// Is is a valid URI notation ?
				try {
					if (url.equals(""))
						throw new Exception(WARValidationResourceHandler.Invalid_URL_70);
					// You can't use com.ibm.webtools.URI here...
					// com.ibm.iwt.webtools.URI uri = new com.ibm.iwt.webtools.URI(url) ;
				} catch (Exception e) {
					parms = new String[1];
					parms[0] = nextMapping.getUrlPattern();
					addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_URL, parms, nextMapping);
					continue;
				}
			} else if (nextMapping.getServletName() != null) {
				String[] parms = new String[1];
				String servletName = nextMapping.getServletName();
				parms[0] = servletName;
				if (nextMapping.getServlet() == null) {
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_BROKEN_SERVLET_MAPPING, parms, nextMapping);
					continue;
				}
				if (nextMapping.getFilter() == null || nextMapping.getFilter().equals("")) { //$NON-NLS-1$
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_BROKEN_FILTER_MAPPING, parms, nextMapping);
					continue;
				}
			}
			// else {
			// // otherwise neither servlet-name or url-mapping is defined - this gets flagged by
			// the xml validator
			// // as this does not conform to the DTD
			//		
			// }


		}

	}



	/**
	 * validateServlets(Elist(Servlet's)) - for each servlet mapping, make sure the named servlet
	 * exists
	 */

	public void validateServletMappings(EList servletMappingsList) {

		if (servletMappingsList.isEmpty())
			return;

		Iterator servletMappings = servletMappingsList.iterator();

		Hashtable remember = new Hashtable();
		while (servletMappings.hasNext()) {
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			ServletMapping nextMapping = (ServletMapping) servletMappings.next();
			String url = nextMapping.getUrlPattern();

			// Check for null servlet, if the name is right the reflection
			// has already resolved it.

			if (url != null) { // check for duplicate first, no need to repeat earlier error,
				url = url.trim();
				if (remember.get(url) != null) {
					String[] parms = new String[1];
					parms[0] = nextMapping.getUrlPattern();
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_MAPPING, parms, nextMapping);
					continue;
				}
				remember.put(nextMapping.getUrlPattern(), "Yea"); //$NON-NLS-1$
			}


			if (url == null || nextMapping.getServlet() == null || nextMapping.getServlet().equals("")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = url;
				if (url == null)
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_URL, parms, nextMapping);
				else
					addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_BROKEN_SERVLET_MAPPING, parms, nextMapping);
				continue;
			}

			// Is is a valid URI notation ?
			try {
				if (url.equals(""))
					throw new Exception(WARValidationResourceHandler.Invalid_URL_75);

				//
				// The spec does not prohibit whitespace in the url-patterns
				//	   
				// // check to see if the string is a valid URL- has no white space
				// char[] chars = url.toCharArray();
				// int len = chars.length;
				// for (int cnt = 0; cnt < chars.length; cnt++)
				// {
				// if (Character.isWhitespace(chars[cnt]))
				// throw new Exception("Invalid URL") ;
				// }
				// You can't use com.ibm.webtools.URI here...
				// com.ibm.iwt.webtools.URI uri = new com.ibm.iwt.webtools.URI(url) ;
			} catch (Exception e) {
				String[] parms = new String[1];
				parms[0] = nextMapping.getUrlPattern();
				addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_URL, parms, nextMapping);
				continue;
			}
		}

	}

	/**
	 * validateServlets(Elist(Servlet's)) - if it's a JSP, verify the file exists - validate
	 * optional security role refs for existence of the security role
	 */

	public void validateServlets(EList servletList, Hashtable secRoles) {
		if (servletList.isEmpty())
			return;

		Iterator servlets = servletList.iterator();

		Hashtable remember = new Hashtable();

		while (servlets.hasNext()) {
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			Servlet nextServlet = (Servlet) servlets.next();
			String name = nextServlet.getServletName();
			if (name != null)
				name = name.trim();

			if (name == null || name.equals("")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Servlet_Name_77;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, nextServlet);
				continue;
			}
			if (remember.get(name) != null) { // check for duplicates
				String[] parms = new String[1];
				parms[0] = name;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_SERVLET, parms, nextServlet);
				continue;
			}
			remember.put(name, "Yea"); //$NON-NLS-1$

			// Validate servlet roles
			EList rolesList = nextServlet.getSecurityRoleRefs();
			if (!rolesList.isEmpty()) {
				Iterator roles = rolesList.iterator();
				while (roles.hasNext()) {
					SecurityRoleRef role = (SecurityRoleRef) roles.next();
					validateSecRole(role.getLink(), role.getName(), secRoles, role);
				}
			}

			// if it's a JSP, the jsp-file attribute MUST be a full-path, according to the servlet
			// 2.2 spec
			if (nextServlet.getWebType().isJspType()) {
				JSPType jspType = (JSPType) (nextServlet.getWebType());

				String jspFileName = jspType.getJspFile();
				if (jspFileName == null || !(jspFileName.length() > 0)) {
					String[] parms = new String[2];
					parms[0] = jspFileName;
					parms[1] = name;
					addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_JSPFILE_REF, parms, nextServlet);
				}
			}

			/* No need to check for the existence of JSPs or Servlets. the LinkBuilder will do this */

		}
	}

	public void validateTagLibs() {

		if (webDD.getTagLibs().isEmpty())
			return;

		Iterator tags = webDD.getTagLibs().iterator();
		Hashtable remember = new Hashtable();

		while (tags.hasNext()) {

			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			TagLibRef taglib = (TagLibRef) tags.next(); // ClassCastException at runtime
			String uri = taglib.getTaglibURI();

			if (uri != null)
				uri = uri.trim();

			if (uri == null || uri.equals("")) { //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Taglib_80;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, taglib);
				continue;
			}
			if (remember.get(uri) != null) { // Check for dups

				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_TagLib___81 + ": " + uri;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, taglib);
				continue;
			}
			remember.put(uri, "Yea"); //$NON-NLS-1$
		}
	}

	public void validateWebResourceCollections(EList webResourceList) {


		Iterator resourceList = webResourceList.iterator();
		while (resourceList.hasNext()) { // Check the web resource collections
			WebResourceCollection resource = (WebResourceCollection) resourceList.next();

			String name = resource.getWebResourceName();
			if (name != null)
				name = name.trim();
			if (name == null || name.equals("")) { // should have a name //$NON-NLS-1$
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Web_Resource_Name_84;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_EMPTY_ENTRY, parms, resource);
			}

			// Check that the http methods, if any is correct
			EList httpList = resource.getHTTPs();
			if (!httpList.isEmpty()) {
				Iterator https = httpList.iterator();
				while (https.hasNext()) {
					HTTPMethodType httpMethod = (HTTPMethodType) https.next();
					String method = httpMethod.getHttpMethod();

					if (method == null || !isHttpMethod(method)) {
						String[] parms = new String[1];
						parms[0] = method;
						addError(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_HTTP_CMD, parms, httpMethod);
					}


				}
			}
		}
	}

	/**
	 * validateWelcomeFileList(WelcomeFileList) - walk through the files and verify they exist
	 */

	public void validateWelcomeFileList(WelcomeFileList fileList) {
		if (fileList == null)
			return;

		Iterator files = fileList.getFile().iterator();

		Hashtable remember = new Hashtable();
		while (files.hasNext()) {
			
			if( _reporter.isCancelled() ){
				String msg = ResourceHandler.getExternalizedMessage(ResourceConstants.VBF_STATUS_VALIDATOR_TERMINATED);			
				status = WTPCommonPlugin.createCancelStatus(msg);
			}
			
			WelcomeFile nextFile = (WelcomeFile) files.next();
			String fileName = nextFile.getWelcomeFile();

			if ((fileName == null) || (fileName.length() == 0)) {
				String[] parms = new String[0];
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_NO_WELCOME_FILE, parms, nextFile);
			} else if ((fileName.startsWith("/")) || (fileName.endsWith("/"))) { //$NON-NLS-1$ //$NON-NLS-2$
				String[] parms = new String[0];
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_INVALID_WELCOME_FILE, parms, nextFile);
			}
			fileName = fileName.trim();
			if (remember.get(fileName) != null) {
				String[] parms = new String[1];
				parms[0] = WARValidationResourceHandler.of_Type_Welcome_File_Name__87 + ": " + fileName;
				addWarning(WAR_CATEGORY, MESSAGE_WAR_VALIDATION_DUPLICATE_ENTRY, parms, nextFile);
				continue;
			}
			remember.put(fileName, "Yea"); //$NON-NLS-1$
		}
	}

	public ISchedulingRule getSchedulingRule(IValidationContext helper) {
		_helper = helper;
		return null;
	}
}
