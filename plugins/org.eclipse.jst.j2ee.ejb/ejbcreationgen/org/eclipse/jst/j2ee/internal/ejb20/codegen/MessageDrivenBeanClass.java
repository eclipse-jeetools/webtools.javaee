/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EnterpriseBeanBeanClass;
import org.eclipse.jst.j2ee.internal.ejb.codegen.UnimplementedMethodGroupGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBClassReferenceHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;


public class MessageDrivenBeanClass extends EnterpriseBeanBeanClass {

	/**
	 * Constructor for MessageDrivenBeanClass.
	 */
	public MessageDrivenBeanClass() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.codegen.EnterpriseBeanBeanClass#initialize(java.lang.Object)
	 */
	public void initialize(Object mofObject) throws GenerationException {
		super.initialize(mofObject);
		if (getTopLevelHelper().isDeleteAll())
			return;
		MessageDriven mdb = getMessageDriven();
		if (mdb.getVersionID() == J2EEVersionConstants.EJB_2_1_ID) {
			initializeForNonJMSGenerator(mdb);
		}
	}

	/**
	 *  
	 */
	private void initializeForNonJMSGenerator(MessageDriven mdb) throws GenerationException {

		IBaseGenerator generator = null;
		JavaClass messageType = mdb.getMessagingType();
		if (messageType != null && !messageType.getQualifiedName().equals("javax.jms.MessageListener")) { //$NON-NLS-1$
			generator = getGenerator(IEJB20GenConstants.UNIMPLEMENTED_METHOD_GENERATOR);
			((UnimplementedMethodGroupGenerator) generator).setUnimplementedInterface(messageType);
			generator.initialize(this.getClassRefHelper());
		}
	}

	private MessageDriven getMessageDriven() {
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		return (MessageDriven) helper.getEjb();
	}

	/*
	 * @see EnterpriseBeanClass#getInheritedSuperInterfaceNames()
	 */
	protected List getInheritedSuperInterfaceNames() {
		List names = new ArrayList();
		if (!hasSourceSupertype()) {
			names.add(IEJB20GenConstants.MESSAGE_DRIVEN_BEAN_INTERFACE_NAME);
			MessageDriven mdb = getMessageDriven();
			JavaClass messageType = mdb.getMessagingType();
			if (messageType != null)
				names.add(messageType.getQualifiedName());
			else
				names.add(IEJB20GenConstants.MESSAGE_LISTENTER_INTERFACE_NAME);
		}
		return names;
	}

	/*
	 * @see EnterpriseBeanClass#getRequiredMemberGeneratorNames()
	 */
	protected List getRequiredMemberGeneratorNames() throws GenerationException {
		EJBClassReferenceHelper refHelper = getClassRefHelper();
		EnterpriseBeanHelper helper = (EnterpriseBeanHelper) getTopLevelHelper();
		List names = new ArrayList();

		// Do them all on create.
		if (refHelper != null && refHelper.isCreate()) {
			if (!hasSourceSupertype()) {
				names.add(IEJB20GenConstants.MDB_CONTEXT_FIELD);
				names.add(IEJB20GenConstants.MDB_CONTEXT_GETTER);
				names.add(IEJB20GenConstants.MDB_CONTEXT_SETTER);
				names.add(IEJB20GenConstants.MDB_EJBCREATE);
			}
			names.add(IEJB20GenConstants.MDB_ONMESSAGE);
			names.add(IEJB20GenConstants.MDB_EJBREMOVE);
		} else if (helper.isChangingInheritance()) {
			names.add(IEJB20GenConstants.MDB_CONTEXT_FIELD);
			names.add(IEJB20GenConstants.MDB_CONTEXT_GETTER);
			names.add(IEJB20GenConstants.MDB_CONTEXT_SETTER);
			names.add(IEJB20GenConstants.MDB_EJBCREATE);
		}

		return names;
	}
}