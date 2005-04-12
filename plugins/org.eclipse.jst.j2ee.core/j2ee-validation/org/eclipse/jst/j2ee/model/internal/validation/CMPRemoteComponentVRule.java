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

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclispe.wst.validation.internal.core.ValidationException;


/**
 * @version 	1.0
 * @author
 */
public final class CMPRemoteComponentVRule extends AComponentVRule implements IRemoteType, IComponentType, IMessagePrefixEjb20Constants {
	private static final Object ID = IValidationRuleList.EJB20_CMP_REMOTE;
	private static final Object[] DEPENDS_ON = new Object[]{IValidationRuleList.EJB20_CMP_BEANCLASS};
	private static final Map MESSAGE_IDS;

	private static final String MSSGID = ".crc"; // In messages, to identify which message version belongs to the BMP bean class, this id is used. //$NON-NLS-1$
	private static final String EXT = MSSGID + SPEC; // Extension to be used on non-method, non-field messages
	private static final String BEXT = MSSGID + ON_BASE_SPEC; // Extension to be used on a method/field message when the method/field is inherited from a base type
	private static final String MEXT = MSSGID + ON_THIS_SPEC; // Extension to be used on a method/field message when the method/field is implemented on the current type
	
	private final long[] SUPERTYPES = new long[]{JAVAX_EJB_EJBOBJECT};
	private final long[] SHOULD_NOT_BE_SUPERTYPES = null;

	private final long[] METHODS_WHICH_MUST_EXIST = null;
	private static final long[] METHODS_WHICH_MUST_NOT_EXIST = new long[]{EJBSELECT};

	private final long[] KNOWN_METHOD_TYPES = new long[]{CLINIT, EJBSELECT}; // Must know EJBSELECT to check that it's not exposed on this interface

	static {
		MESSAGE_IDS = new HashMap();
		
		MESSAGE_IDS.put(CHKJ2017, new String[]{CHKJ2017+EXT});

		MESSAGE_IDS.put(CHKJ2023, new String[]{CHKJ2023+BEXT, CHKJ2023+MEXT});

		MESSAGE_IDS.put(CHKJ2404, new String[]{CHKJ2404+ON_BASE_SPEC, CHKJ2404+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2105, new String[]{CHKJ2105+SPEC});
		MESSAGE_IDS.put(CHKJ2416, new String[]{CHKJ2416+ON_BASE_SPEC, CHKJ2416+ON_THIS_SPEC}); // special case (shared by all types)

		MESSAGE_IDS.put(CHKJ2433, new String[]{CHKJ2433});

		MESSAGE_IDS.put(CHKJ2468, new String[]{CHKJ2468+BEXT, CHKJ2468+MEXT});
		MESSAGE_IDS.put(CHKJ2469, new String[]{CHKJ2469+BEXT, CHKJ2469+MEXT});

		MESSAGE_IDS.put(CHKJ2470, new String[]{CHKJ2470+BEXT, CHKJ2470+MEXT});
		MESSAGE_IDS.put(CHKJ2471, new String[]{CHKJ2471+BEXT, CHKJ2471+MEXT});
		MESSAGE_IDS.put(CHKJ2472, new String[]{CHKJ2472+BEXT, CHKJ2472+MEXT});
		MESSAGE_IDS.put(CHKJ2473, new String[]{CHKJ2473+BEXT, CHKJ2473+MEXT});
		MESSAGE_IDS.put(CHKJ2474, new String[]{CHKJ2474+BEXT, CHKJ2474+MEXT});

		MESSAGE_IDS.put(CHKJ2500_business, new String[]{CHKJ2500_business+BEXT, CHKJ2500_business+MEXT});
		MESSAGE_IDS.put(CHKJ2500_create, new String[]{CHKJ2500_create+BEXT, CHKJ2500_create+MEXT});
		MESSAGE_IDS.put(CHKJ2500_home, new String[]{CHKJ2500_home+BEXT, CHKJ2500_home+MEXT});
		MESSAGE_IDS.put(CHKJ2502_ejbSelect, new String[]{CHKJ2502_ejbSelect+BEXT, CHKJ2502_ejbSelect+MEXT});
		MESSAGE_IDS.put(CHKJ2503_bus, new String[]{CHKJ2503_bus+BEXT, CHKJ2503_bus+MEXT});
		
		MESSAGE_IDS.put(CHKJ2907, new String[]{CHKJ2907});
	}
	
	public final Map getMessageIds() {
		return MESSAGE_IDS;
	}
	
	public final int getMessageRemoteExceptionSeverity() {
		return MESSAGE_REMOTE_EXCEPTION_SEVERITY;
	}

	public final Object[] getDependsOn() {
		return DEPENDS_ON;
	}
	
	public final Object getId() {
		return ID;
	}

	public final long[] getBaseTypes() {
		return getSupertypes();
	}
	
	public final long[] getSupertypes() {
		return SUPERTYPES;
	}
	
	public final long[] getShouldNotBeSupertypes() {
		return SHOULD_NOT_BE_SUPERTYPES;
	}

	
	public final int isRemote() {
		return IS_REMOTE;
	}
	
	public final long[] getMethodsWhichMustExist() {
		return METHODS_WHICH_MUST_EXIST;
	}
	
	public final long[] getMethodsWhichMustNotExist() {
		return METHODS_WHICH_MUST_NOT_EXIST;
	}

	public final long[] getKnownMethodTypes() {
		return KNOWN_METHOD_TYPES;
	}

	public void validate(IEJBValidationContext vc, EnterpriseBean bean, JavaClass clazz) throws ValidationCancelledException, InvalidInputException, ValidationException {
		super.validate(vc, bean, clazz);
		
		ContainerManagedEntity cmp = (ContainerManagedEntity)bean;
		
		// 10.3.1, container-managed persistent fields and container-managed relationship 
		// fields must *not* be defined in the entity bean class. 
		List cmrFields = cmp.getCMRFields();
		Iterator iterator = cmrFields.iterator();
		while(iterator.hasNext()) {
			CMRField cmrField = (CMRField)iterator.next();
			if((cmrField != null) && (!cmrField.isDerived())) {

				JavaHelpers localType = ValidationRuleUtility.getCMRFieldType(vc, cmp, clazz, cmrField);
				if(localType == null) {
					// user made a mistake in ejb-jar.xml by trying to create a relation
					// to a CMP which doesn't have a local interface.
					// Don't report an error here; let the DD validation report the error.
					continue;
				}

				// Cannot expose get/set methods of CMR fields on the remote home or interface
				Method remoteGetMethod = ValidationRuleUtility.getMethodExtended(clazz, cmrField.getGetterName(), new JavaHelpers[0], localType);
				if(remoteGetMethod != null) {
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2473, IEJBValidationContext.WARNING, cmp, clazz, remoteGetMethod, new String[]{cmrField.getGetterName()}, this);
					vc.addMessage(message);
				}

				JavaHelpers[] setMethodParms = new JavaHelpers[]{localType};
				Method remoteSetMethod = ValidationRuleUtility.getMethodExtended(clazz, cmrField.getSetterName(), setMethodParms);
				if(remoteSetMethod != null) {
					String setterName = cmrField.getSetterName() + "(" + ValidationRuleUtility.getParmsAsString(setMethodParms) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
					IMessage message = MessageUtility.getUtility().getMessage(vc, IMessagePrefixEjb20Constants.CHKJ2473, IEJBValidationContext.WARNING, cmp, clazz, remoteSetMethod, new String[]{setterName}, this);
					vc.addMessage(message);
				}
			}
		}
	}	
}
