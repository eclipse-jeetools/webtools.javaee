/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.MessageLimitException;
import org.eclipse.wst.validation.core.ValidationException;

import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * This class checks ejb-jar.xml for errors or potential errors.
 * If any problems are found, an error, warning, or info marker is added to the task list.
 *
 * 15.2.5.3 Declaration of security roles referenced from the bean's code
 * The Bean Provider is responsible for declaring in the security-role-ref elements of the deploy-ment
 * descriptor all the security role names used in the enterprise bean code. Declaring the security roles
 * references in the code allows the Application Assembler or Deployer to link the names of the security
 * roles used in the code to the security roles defined for an assembled application through the secu-rity-
 * role elements.
 * The Bean Provider must declare each security role referenced in the code using the secu-rity-
 * role-ref element as follows:
 *      Declare the name of the security role using the role-name element. The name must be the
 * security role name that is used as a parameter to the isCallerInRole(String role-Name)
 * method.
 *      Optional: Provide a description of the security role in the description element.
 * A security role reference, including the name defined by the role-name element, is scoped to the ses-sion
 * or entity bean element whose declaration contains the security-role-ref element.
 * The following example illustrates how an enterprise bean's references to security roles are declared in
 * the deployment descriptor.
 *    ...
 *    <enterprise-beans>
 *       ...
 *       <entity>
 *          <ejb-name>AardvarkPayroll</ejb-name>
 *          <ejb-class>com.aardvark.payroll.PayrollBean</ejb-class>
 *          ...
 *          <security-role-ref>
 *             <description>
 *                 This security role should be assigned to the
 *                 employees of the payroll department who are
 *                 allowed to update employees' salaries.
 *             </description>
 *             <role-name>payroll</role-name>
 *          </security-role-ref>
 *          ...
 *       </entity>
 *       ...
 *    </enterprise-beans>
 *    ...
 *
 * The deployment descriptor above indicates that the enterprise bean AardvarkPayroll makes the
 * security check using isCallerInRole("payroll") in its business method.
 *
 *
 * 15.3.3 Linking security role references to security roles
 * If the Application Assembler defines the security-role elements in the deployment descriptor, he
 * or she is also responsible for linking all the security role references declared in the secu-rity-
 * role-ref elements to the security roles defined in the security-role elements.
 * The Application Assembler links each security role reference to a security role using the role-link
 * element. The value of the role-link element must be the name of one of the security roles defined in
 * a security-role element.
 * A role-link element must be used even if the value of role-name is the same as the value of the
 * role-link reference.
 * The following deployment descriptor example shows how to link the security role reference named
 * payroll to the security role named payroll-department.
 *    ...
 *    <enterprise-beans>
 *       ...
 *       <entity>
 *          <ejb-name>AardvarkPayroll</ejb-name>
 *          <ejb-class>com.aardvark.payroll.PayrollBean</ejb-class>
 *          ...
 *          <security-role-ref>
 *             <description>
 *                This role should be assigned to the
 *                employees of the payroll department.
 *                Members of this role have access to
 *                anyone's payroll record.
 *
 *                The role has been linked to the
 *                payroll-department role.
 *             </description>
 *             <role-name>payroll</role-name>
 *             <role-link>payroll-department</role-link>
 *          </security-role-ref>
 *          ...
 *       </entity>
 *       ...
 *    </enterprise-beans>
 *    ...
 */
public class EJBJar11VRule extends AValidationRule implements IMessagePrefixEjb11Constants {
	private DuplicatesTable _ejbName = null;
	private static final Object ID = IValidationRuleList.EJB11_EJBJAR;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB11_SESSION_BEANCLASS, IValidationRuleList.EJB11_SESSION_REMOTE, IValidationRuleList.EJB11_SESSION_HOME, IValidationRuleList.EJB11_CMP_BEANCLASS, IValidationRuleList.EJB11_CMP_REMOTE, IValidationRuleList.EJB11_CMP_HOME, IValidationRuleList.EJB11_CMP_KEYCLASS, IValidationRuleList.EJB11_BMP_BEANCLASS, IValidationRuleList.EJB11_BMP_REMOTE, IValidationRuleList.EJB11_BMP_HOME, IValidationRuleList.EJB11_BMP_KEYCLASS, IValidationRuleList.EJB11_EJBEXT};
	
	private static final Map MESSAGE_IDS;
	
	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2814, new String[]{CHKJ2814 + SPEC});

		MESSAGE_IDS.put(CHKJ2825, new String[]{CHKJ2825 + SPEC});
		MESSAGE_IDS.put(CHKJ2826, new String[]{CHKJ2826 + SPEC});

		MESSAGE_IDS.put(CHKJ2842, new String[]{CHKJ2842 + SPEC});
		MESSAGE_IDS.put(CHKJ2843, new String[]{CHKJ2843 + SPEC});
		MESSAGE_IDS.put(CHKJ2844, new String[]{CHKJ2844 + SPEC});
		MESSAGE_IDS.put(CHKJ2845, new String[]{CHKJ2845 + SPEC});
		MESSAGE_IDS.put(CHKJ2846, new String[]{CHKJ2846 + SPEC});
		MESSAGE_IDS.put(CHKJ2847, new String[]{CHKJ2847 + SPEC});

		MESSAGE_IDS.put(CHKJ2850, new String[]{CHKJ2850 + SPEC});
		MESSAGE_IDS.put(CHKJ2852, new String[]{CHKJ2852});

		MESSAGE_IDS.put(CHKJ2875, new String[]{CHKJ2875 + SPEC});

		MESSAGE_IDS.put(CHKJ2895, new String[]{CHKJ2895 + SPEC});
	}
	
	public EJBJar11VRule() {
		_ejbName = new DuplicatesTable();
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

	public Object getTarget(Object parent, Object target) {
		return null;
	}

	/**
	 * 15.3.1 Security roles
	 * The Application Assembler can define one or more security roles in 
	 * the deployment descriptor. The Application Assembler then assigns 
	 * groups of methods of the enterprise beans' home and remote interfaces
	 * to the security roles to define the security view of the application.
	 * Because the Application Assembler does not, in general, know the 
	 * security environment of the operational environment, the security 
	 * roles are meant to be logical roles (or actors), each representing 
	 * a type of user that should have the same access rights to the 
	 * application. The Deployer then assigns user groups and/or user 
	 * accounts defined in the operational environment to the security roles 
	 * defined by the Application Assembler.
	 *    Defining the security roles in the deployment descriptor is optional [17]
	 * for the Application Assembler.  Their omission in the deployment 
	 * descriptor means that the Application Assembler chose not to pass any 
	 * security deployment related instructions to the Deployer in the 
	 * deployment descriptor. The Application Assembler is responsible for 
	 * the following:
	 *    - Define each security role using a security-role element.
	 *    - Use the role-name element to define the name of the security role.
	 *    - Optionally, use the description element to provide a description of 
	 *      a security role.
	 * The security roles defined by the security-role elements are scoped to 
	 * the ejb-jar file level, and apply to all the enterprise beans in the 
	 * ejb-jar file.
	 * [17] If the Application Assembler does not define security roles in the 
	 * deployment descriptor, the Deployer will have to define security
	 * roles at deployment time.
	 *...
	*/
	protected void validateAssemblyDescriptorElement(IValidationContext vc, EJBJar ejbJar) {
		vc.terminateIfCancelled();

		// Validate the security roles, if they're defined in the assembly-descriptor.
		if (ejbJar == null) {
			// nothing to validate
			return;
		}

		/**
		 * Need to build up a list of duplicate role names, but the validation message
		 * needs to be registered against the duplicate SecurityRole instance.
		 * (Without the instance, we cannot get line numbers.)
		 *
		 * This class wrappers the SecurityRol instance so that the wrapper's
		 * implemention of equals compares the names, but the validation message will
		 * still be able to get the ref from the duplicate name.
		 */
		class RoleWrapper {
			private SecurityRole _role = null;

			public RoleWrapper(SecurityRole role) {
				_role = role;
			}

			public boolean equals(Object o) {
				if (o instanceof RoleWrapper) {
					RoleWrapper other = (RoleWrapper) o;
					return _role.getRoleName().equals(other.getRole().getRoleName());
				}
				return false;
			}

			public SecurityRole getRole() {
				return _role;
			}
		}

		AssemblyDescriptor assemblyDescriptor = ejbJar.getAssemblyDescriptor();
		if (assemblyDescriptor == null) {
			// nothing to validate
			return;
		}

		List roles = assemblyDescriptor.getSecurityRoles();
		if (roles != null) {
			DuplicatesTable roleNames = new DuplicatesTable();
			SecurityRole role = null;
			Iterator roleIt = roles.iterator();
			while (roleIt.hasNext()) {
				vc.terminateIfCancelled();
				// Check that the role-name element has been set
				role = (SecurityRole) roleIt.next();
				if (role == null) {
					// role-name not set
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2825, IValidationContext.WARNING, ejbJar, this);
					vc.addMessage(message);
				}
				else if ((!role.eIsSet(CommonPackage.eINSTANCE.getSecurityRole_RoleName())) || (role.getRoleName().equals(""))) { //$NON-NLS-1$
					// role-name not set
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2825, IValidationContext.WARNING, role, this);
					vc.addMessage(message);
				}
				else {
					// Build up hashtable to check for duplicate role-names.
					roleNames.add(new RoleWrapper(role));
				}
			}

			// Check that there are no duplicate role-names. (15.3.1)
			if (roleNames.containsDuplicates()) {
				List duplicates = roleNames.getDuplicates();
				Iterator iterator = duplicates.iterator();
				while (iterator.hasNext()) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2826, IValidationContext.WARNING, ((RoleWrapper) iterator.next()).getRole(), this);
					vc.addMessage(message);
				}
			}
			roleNames.clear();
		}

		List methTrans = assemblyDescriptor.getMethodTransactions();
		MethodTransaction mt = null;
		Iterator iterator = methTrans.iterator();
		while (iterator.hasNext()) {
			vc.terminateIfCancelled();

			try {
				mt = (MethodTransaction) iterator.next();
			}
			catch (Throwable exc) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINER)) {
					logger.write(Level.FINER, exc);
				}
				mt = null;
			}

			if (mt == null) {
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.FINEST)) {
					LogEntry entry = vc.getLogEntry();
					entry.setSourceID("DDValidator.validateAssemblyDescriptorElement"); //$NON-NLS-1$
					entry.setText("mt is null"); //$NON-NLS-1$
					logger.write(Level.FINEST, entry);
				}
				continue;
			}

			boolean hasValidMethod = validateMethodElements(vc, ejbJar, mt.getMethodElements());
			if (!hasValidMethod) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2847, IValidationContext.WARNING, mt, this);
				vc.addMessage(message);
			}
		}

		List methodPermissions = assemblyDescriptor.getMethodPermissions();
		iterator = methodPermissions.iterator();
		while (iterator.hasNext()) {
			MethodPermission mp = (MethodPermission) iterator.next();

			boolean hasValidMethod = validateMethodElements(vc, ejbJar, mp.getMethodElements());
			if (!hasValidMethod) {
				// 15.3.2, p. 229, a <method-permission> must have at least one method listed (and that method must be found)
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2846, IValidationContext.WARNING, mp, this);
				vc.addMessage(message);
			}

			// at least one security-role must be defined
			List mproles = mp.getRoles();
			if ((mproles == null) || (mproles.size() == 0)) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2845, IValidationContext.WARNING, mp, this);
				vc.addMessage(message);
			}
		}
	}
	
	/**
	 * This validateDeploymentDescriptor is called if the EJBJar could load, which means
	 * that the syntax of the JAR is (mostly) correct.
	 *
	 * EJB spec 1.1, section C.4, "Added the requirement for the Bean Provider to specify whether the
	 * enterprise bean uses a bean-managed or container-managed transaction."
	 */
	public void validate(IValidationContext vc, Object targetParent, Object target) throws ValidationException {
		EJBJar ejbJar = (EJBJar) target;
		List enterpriseBeans = ejbJar.getEnterpriseBeans();
		Iterator iterator = enterpriseBeans.iterator();
		EnterpriseBean bean = null;
		String beanName = null;
		while (iterator.hasNext()) {
			try {
				bean = (EnterpriseBean) iterator.next();
				register(vc, ejbJar, bean);

				Object id = IValidationRuleList.EJB11_ENTERPRISEBEAN;
				IValidationRule vRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
				if (vRule == null) {
					// This has already been logged by the AbstractEJBValidationRuleFactory, so just
					// need to add "Cannot validate" to the task list.
					continue;
				}
				try {
					vRule.preValidate(vc, ejbJar, bean);
					vRule.validate(vc, ejbJar, bean);
					vRule.postValidate(vc, ejbJar, bean);
				}
				catch (ValidationCancelledException exc) {
					// Clean up the messages which are on the task list? Or is it nicer to leave them behind?
				}
				catch(MessageLimitException exc) {
					throw exc;
				}
				catch(ValidationException e) {
					throw e;
				}
				catch (Throwable exc) {
					addInternalErrorMessage(vc, exc);
				}
				finally {
					EJBValidationRuleFactory.getFactory().release(vRule);
				}
			}
			catch (MessageLimitException e) {
				throw e;
			}
			catch(ValidationCancelledException e) {
				throw e;
			}
			catch (ValidationException e) {
				throw e;
			}
			catch (Throwable exc) {
				// If there's a problem, proceed with the next bean.
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2852, IValidationContext.WARNING, bean, new String[] { ArchiveConstants.EJBJAR_DD_SHORT_NAME, beanName }, this);
				vc.addMessage(message);
				Logger logger = vc.getMsgLogger();
				if (logger != null && logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
			}
		}

		// Since the assembly descriptor is not specific to a bean, validate it once, after all bean processing is complete.
		validateAssemblyDescriptorElement(vc, ejbJar);
		validateUniqueEjbNames(vc, ejbJar);
		validateClientJAR(vc, ejbJar);
	}
	
	public void reset() {
		super.reset();
		_ejbName.clear();
	}
	
	protected void register(IValidationContext vc, EJBJar ejbJar, EnterpriseBean bean) {
		// To check if every bean name is unique, need to build a list
		_ejbName.add(new EjbNameWrapper(bean));
	}

	private void addInternalErrorMessage(IValidationContext vc, Throwable exc) {
		IMessage mssg = vc.getMessage();
		mssg.setId(IMessagePrefixEjb11Constants.CHKJ2900);
		vc.addMessage(mssg);

		if(exc != null) {
			Logger logger = vc.getMsgLogger();
			if (logger != null && logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
	
	public void validateUniqueEjbNames(IValidationContext vc, EJBJar ejbJar) {
		List names = _ejbName.getDuplicates();
		if(names.size() == 0) {
			return;
		}
		
		Iterator iterator = names.iterator();
		while(iterator.hasNext()) {
			EjbNameWrapper wrapper = (EjbNameWrapper)iterator.next();
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2895, IValidationContext.WARNING, wrapper.getBean(), new String[]{wrapper.getBean().getName()}, this);
			vc.addMessage(message);
		}
	}

	protected void validateClientJAR(IValidationContext vc, EJBJar ejbJar) {
		String clientJARName = ejbJar.getEjbClientJar();
		if(clientJARName == null) {
			// No client JAR specified; everything's okay.
			return;
		}
		
		Boolean exists = (Boolean)vc.loadModel(EJBValidatorModelEnum.EJB_CLIENTJAR, new Object[]{clientJARName});
		if(exists == null) {
			// Helper doesn't support load model. WAS?
			// Can't perform this check, so just return.
			return;
		}
		
		if(!exists.booleanValue()) {
			IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2875, IValidationContext.ERROR, ejbJar, new String[]{clientJARName}, this);
			vc.addMessage(message);
		}
	}

	/**
	 * Both section 11.4.1 and 15.3.2 need the <method> element. Also refer
	 * to 16.5 for syntax.
	 *
	 * Return true if at least one of the methods referenced by this list of 
	 * MethodElement can be found.
	*/
	protected boolean validateMethodElements(IValidationContext vc, EJBJar ejbJar, List elements) {
		if ((elements == null) || (elements.size() == 0)) {
			return false;
		}

		boolean hasValidMethod = false;
		Iterator iterator = elements.iterator();
		while (iterator.hasNext()) {
			vc.terminateIfCancelled();
			MethodElement element = (MethodElement) iterator.next();

			EnterpriseBean bean = element.getEnterpriseBean();
			if (bean == null) {
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2814, IValidationContext.WARNING, element, this);
				vc.addMessage(message);
				continue;
			}

			if (element.getName() != null) {
				// Do not attempt to access the methods on the home or remote interface if there'
				// been a problem locating or reflecting those types
				boolean reflected = true;
				try {
					ValidationRuleUtility.isValidType(bean.getHomeInterface());
				}
				catch (InvalidInputException e) {
					reflected = false;
					String className = (e.getJavaClass() == null) ? IEJBValidatorConstants.NULL_HOME : e.getJavaClass().getQualifiedName();
					String[] msgParm = { className };
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2850, IValidationContext.WARNING, bean, msgParm, this);
					vc.addMessage(message);
				}
				try {
					ValidationRuleUtility.isValidType(bean.getRemoteInterface());
				}
				catch (InvalidInputException e) {
					reflected = false;
					String className = (e.getJavaClass() == null) ? IEJBValidatorConstants.NULL_REMOTE : e.getJavaClass().getQualifiedName();
					String[] msgParm = { className };
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2850, IValidationContext.WARNING, bean, msgParm, this);
					vc.addMessage(message);
				}
				
				if(reflected) {
					// The "element.getMethods()" has a null pointer exception when it attempts to retrieve the methods from the home/remote interface,
					// if either of the interfaces don't exist.
					String name = element.getName();

					Method[] methods = element.getMethods(); // get all methods which will be retrieved for the given method-permission
					boolean hasMethods = ((methods != null) && (methods.length > 0));

					if (!hasMethods) {
						// warning
						IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2843, IValidationContext.WARNING, element, new String[] { bean.getName()}, this);
						vc.addMessage(message);
					}
					else {
						hasValidMethod = true; // a <method-permission> must have at least one method (15.3.2, p.229)
						if (name.equals("*")) { //$NON-NLS-1$
							List params = element.getMethodParams();
							if ((params != null) && (params.size() > 0)) {
								// warning
								IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2842, IValidationContext.WARNING, element, this);
								vc.addMessage(message);
							}
						}
					}
				}
			}
			else {
				// error
				IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb11Constants.CHKJ2844, IValidationContext.WARNING, element, this);
				vc.addMessage(message);
			}
		}

		return hasValidMethod;
	}
	
	/**
	 * Need to build up a list of duplicate EJB names, but the validation message
	 * needs to be registered against the duplicate EnterpriseBean instance.
	 * (Without the instance, we cannot get line numbers.)
	 *
	 * This class wrappers the EnterpriseBean instance so that the wrapper's
	 * implemention of equals compares the names, but the validation message will
	 * still be able to get the ref from the duplicate name.
	 */
	class EjbNameWrapper {
		private EnterpriseBean _bean = null;

		public EjbNameWrapper(EnterpriseBean bean) {
			_bean = bean;;
		}

		public boolean equals(Object o) {
			if (o instanceof EjbNameWrapper) {
				EjbNameWrapper other = (EjbNameWrapper)o;
				if((_bean.getName() == null) && (other.getBean().getName() == null)) {
					return true;
				}
				else if(_bean.getName() == null) {
					return false;
				}
				else if(other.getBean().getName() == null) {
					return false;
				}
				return _bean.getName().equals(other.getBean().getName());
			}
			return false;
		}
		
		public int hashCode() {
			if((getBean() != null) && (getBean().getName() != null)) {
				return getBean().getName().hashCode();
			}
			return super.hashCode();
		}

		public EnterpriseBean getBean() {
			return _bean;
		}
	}
}
