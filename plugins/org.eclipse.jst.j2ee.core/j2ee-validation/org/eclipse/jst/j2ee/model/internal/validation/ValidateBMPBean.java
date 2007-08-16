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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


/**
 * This class checks entity bean classes for errors or potential errors.
 * If any problems are found, an error, warning, or info marker is added to the task list.
 *
 * Enterprise JavaBeans Specification ("Specification")
 * Version: 1.1
 * Status: Final Release
 * Release: 12/17/99
 * URL: http://java.sun.com/products/ejb/docs.html
 *
 * All 9.2.X sections describe BMP requirements.
 * If a CMP requirement is different than these, then the differences are
 * documented in 9.4.X sections.
 */
public class ValidateBMPBean extends AValidateEntityBean implements IMessagePrefixEjb11Constants {
	private static final String MSSGID = ".eb"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE + SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS + SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private static final Object ID = IValidationRuleList.EJB11_BMP_BEANCLASS;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_BMP_HOME, IValidationRuleList.EJB11_BMP_REMOTE, IValidationRuleList.EJB11_BMP_KEYCLASS};
	private static final Map MESSAGE_IDS;
	
	boolean hasPKMethod = false;
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2002, new String[]{CHKJ2002+BEXT, CHKJ2002+MEXT});
		MESSAGE_IDS.put(CHKJ2006, new String[]{CHKJ2006+EXT});
		MESSAGE_IDS.put(CHKJ2007, new String[]{CHKJ2007+EXT});
		MESSAGE_IDS.put(CHKJ2009, new String[]{CHKJ2009+EXT});
		
		MESSAGE_IDS.put(CHKJ2013, new String[]{CHKJ2013+EXT});
		MESSAGE_IDS.put(CHKJ2014, new String[]{CHKJ2014+EXT});
		MESSAGE_IDS.put(CHKJ2015, new String[]{CHKJ2015+EXT});
		
		MESSAGE_IDS.put(CHKJ2022, new String[]{CHKJ2022+EXT});
		MESSAGE_IDS.put(CHKJ2028, new String[]{CHKJ2028+BEXT, CHKJ2028+MEXT});
		MESSAGE_IDS.put(CHKJ2029, new String[]{CHKJ2029+BEXT, CHKJ2029+MEXT});
		
		MESSAGE_IDS.put(CHKJ2033, new String[]{CHKJ2033+EXT});
		MESSAGE_IDS.put(CHKJ2034, new String[]{CHKJ2034+EXT});
		MESSAGE_IDS.put(CHKJ2035, new String[]{CHKJ2035+EXT});
		MESSAGE_IDS.put(CHKJ2036, new String[]{CHKJ2036+EXT});
		MESSAGE_IDS.put(CHKJ2037, new String[]{CHKJ2037+EXT});
		MESSAGE_IDS.put(CHKJ2038, new String[]{CHKJ2038+EXT});
		MESSAGE_IDS.put(CHKJ2039, new String[]{CHKJ2039+EXT});
		
		MESSAGE_IDS.put(CHKJ2103, new String[]{CHKJ2103 + SPEC});
		MESSAGE_IDS.put(CHKJ2200, new String[]{CHKJ2200+ON_BASE+SPEC, CHKJ2200+ON_THIS+SPEC}); // CHKJ2200 is a special case. It's shared by all bean types.
		
		MESSAGE_IDS.put(CHKJ2400_bus, new String[]{CHKJ2400_bus+BEXT, CHKJ2400_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbCreate, new String[]{CHKJ2400_ejbCreate+BEXT, CHKJ2400_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbFind, new String[]{CHKJ2400_ejbFind+BEXT, CHKJ2400_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2400_ejbPostCreate, new String[]{CHKJ2400_ejbPostCreate+BEXT, CHKJ2400_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2406, new String[]{CHKJ2406+BEXT, CHKJ2406+MEXT});
		MESSAGE_IDS.put(CHKJ2407, new String[]{CHKJ2407+BEXT, CHKJ2407+MEXT});
		MESSAGE_IDS.put(CHKJ2408_bus, new String[]{CHKJ2408_bus+BEXT, CHKJ2408_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbCreate, new String[]{CHKJ2408_ejbCreate+BEXT, CHKJ2408_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbFind, new String[]{CHKJ2408_ejbFind+BEXT, CHKJ2408_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2408_ejbPostCreate, new String[]{CHKJ2408_ejbPostCreate+BEXT, CHKJ2408_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2409_bus, new String[]{CHKJ2409_bus+BEXT, CHKJ2409_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbCreate, new String[]{CHKJ2409_ejbCreate+BEXT, CHKJ2409_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbFind, new String[]{CHKJ2409_ejbFind+BEXT, CHKJ2409_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2409_ejbPostCreate, new String[]{CHKJ2409_ejbPostCreate+BEXT, CHKJ2409_ejbPostCreate+MEXT});
		
		MESSAGE_IDS.put(CHKJ2410_bus, new String[]{CHKJ2410_bus+BEXT, CHKJ2410_bus+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbCreate, new String[]{CHKJ2410_ejbCreate+BEXT, CHKJ2410_ejbCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbFind, new String[]{CHKJ2410_ejbFind+BEXT, CHKJ2410_ejbFind+MEXT});
		MESSAGE_IDS.put(CHKJ2410_ejbPostCreate, new String[]{CHKJ2410_ejbPostCreate+BEXT, CHKJ2410_ejbPostCreate+MEXT});
		MESSAGE_IDS.put(CHKJ2411, new String[]{CHKJ2411+BEXT, CHKJ2411+MEXT});
		MESSAGE_IDS.put(CHKJ2412, new String[]{CHKJ2412+BEXT, CHKJ2412+MEXT});
		MESSAGE_IDS.put(CHKJ2413, new String[]{CHKJ2413+BEXT, CHKJ2413+MEXT});
		MESSAGE_IDS.put(CHKJ2414, new String[]{CHKJ2414+BEXT, CHKJ2414+MEXT});
		MESSAGE_IDS.put(CHKJ2418, new String[]{CHKJ2418+BEXT, CHKJ2418+MEXT});
		
		MESSAGE_IDS.put(CHKJ2420, new String[]{CHKJ2420+BEXT, CHKJ2420+MEXT});
		
		MESSAGE_IDS.put(CHKJ2432, new String[]{CHKJ2432+BEXT, CHKJ2432+MEXT});

		MESSAGE_IDS.put(CHKJ2041, new String[]{CHKJ2041}); // special case. Shared by all types.
		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});
		MESSAGE_IDS.put(CHKJ2456, new String[]{CHKJ2456+ON_BASE, CHKJ2456+ON_THIS}); // special case (shared by all types)
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	public final Object[] getDependsOn() {
		return DEPENDS_ON;
	}
	
	public final Object getId() {
		return ID;
	}
	
	protected void incrementFindByPrimaryKeyCount(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) {
		if (method == null) {
			return;
		}
		hasPKMethod = true;
	}
	
	/**
	 * Checks to see if @ejbMethod is one of the required methods.
	 */
	protected void primValidateExistence(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method ejbMethod) throws InvalidInputException {
		super.primValidateExistence(vc, bean, clazz, ejbMethod);

		// BMPs must implement ejbFindByPrimaryKey. If it isn't implemented, validateMethodExists() will
		// output an error. (hasPKMethod = true implies implemented, otherwise not implemented)
		if (!hasPKMethod && IMethodAndFieldConstants.METHODNAME_EJBFINDBYPRIMARYKEY.equals(ejbMethod.getName())) {
			incrementFindByPrimaryKeyCount(vc, bean, clazz, ejbMethod);
		}
		vc.terminateIfCancelled();
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.6
	 */
	public void validateBusinessMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP business method checks
		super.validateBusinessMethod(vc, bean, clazz, method);

		// No specific BMP business method checks.
		// All of the points in 9.2.6 are common to both BMPs & CMPs.
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.2
	 */
	public void validateClass(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		// All of the above checks are performed by the parent.
		super.validateClass(vc, bean, clazz);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.5
	 */
	public void validateEjbFindMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// A finder method name must start with the prefix "ejbFind" 
		// (e.g. ejbFindByPrimaryKey, ejbFindLargeAccounts, ejbFindLateShipments).
		// The method which calls this method performs the above check.

		// Every entity bean must define the ejbFindByPrimaryKey method. The result type for 
		// this method must be the primary key type (i.e. the ejbFindByPrimaryKey method must 
		// be a single-object finder).
		if (method == null) {
			return;
		}

		vc.terminateIfCancelled();
		// A finder method must be declared as public.
		if (!ValidationRuleUtility.isPublic(method)) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2408_ejbFind, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method must not be declared as final or static.
		if (method.isStatic()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2410_ejbFind, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		if (method.isFinal()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2409_ejbFind, IEJBValidationContext.ERROR, bean, clazz, method, this);
			vc.addMessage(message);
		}

		// The method argument types must be legal types for RMI-IIOP.
		validateLegalRMIMethodArguments(vc, bean, clazz, method);

		// The return type of a finder method must be the entity bean's primary key type, 
		// or a collection of primary keys (see Section Subsection 9.1.8).
		validateEjbFindMethod_key(vc, bean, clazz, method);

		// Compatibility Note: EJB 1.0 allowed the finder methods to throw the 
		// java.rmi.RemoteException to indicate a non-application exception. 
		// This practice is deprecated in EJB 1.1 -- an EJB 1.1 compliant enterprise bean 
		// should throw the javax.ejb.EJBException or another java.lang.RuntimeException
		// to indicate non-application exceptions to the Container (see Section 12.2.2).
		validateNoRemoteException(vc, bean, clazz, method, IMessagePrefixEjb11Constants.CHKJ2400_ejbFind);

		validateEjbFindMethod_homeDep(vc, bean, clazz, method);
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.5
	 */
	public void validateEjbFindMethod_key(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		if (method == null) {
			return;
		}
		// The return type of a finder method must be the entity bean's primary key type, 
		// or a collection of primary keys (see Section Subsection 9.1.8).
		vc.terminateIfCancelled();

		JavaHelpers returnType = method.getReturnType();

		JavaClass primaryKey = ((Entity) bean).getPrimaryKey();
		ValidationRuleUtility.isValidTypeHierarchy(bean, primaryKey);

		if( !(ValidationRuleUtility.isAssignableFrom(returnType, primaryKey) ||
			  ValidationRuleUtility.isAssignableFromCollection(returnType, bean) ||
			  ValidationRuleUtility.isAssignableFromEnumeration(returnType, bean)
		   )) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IEJBValidatorMessageConstants.CHKJ2407, IEJBValidationContext.WARNING, bean, clazz, method, new String[] { primaryKey.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	
	/**
	 * Checks that the ejbPostCreate method follows the EJB 1.1. specification.
	 * Section: 9.2.4
	*/
	public void validateEjbPostCreateMethod(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz, Method method) throws InvalidInputException {
		// Perform common BMP/CMP ejbPostCreate method checks
		super.validateEjbPostCreateMethod(vc, bean, clazz, method);

		// No specific BMP ejbPostCreate method checks.
		// All of the points in 9.2.4 are common to both BMPs & CMPs.
	}
	
	/**
	 * EJB 1.1 specification
	 * Section: 9.2.5
	 */
	public void validateMethodExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		super.validateMethodExists(vc, bean, clazz);

		if (!hasPKMethod) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2009, IEJBValidationContext.ERROR, bean, clazz, new String[] { clazz.getQualifiedName()}, this);
			vc.addMessage(message);
		}
	}
	
	public void verifyFieldExists(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws InvalidInputException {
		/*
		// Plus, check that at least one field exists on the bean.
		List fields = getFields();
		if((fields == null) || (fields.size() == 0)) {
			addValidationMessage(IEJBValidationContext.WARNING, IMessagePrefixEjb11Constants.EJB_BMP_NOFIELDS, new String[] {getModelObjectName()}, getModelObject());
			return;
		}
		*/
	}
	
	/*
	 * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException {
		super.preValidate(vc, targetParent, target);
		hasPKMethod = false;
	}

}
