/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclispe.jst.j2ee.internal.ejb.association.codegen;



import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IDependentGenerator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


/**
 * Insert the type's description here. Creation date: (5/3/2001 11:25:00 AM)
 * 
 * @author: Administrator
 */
public abstract class EJBRoleMethodGenerator extends org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodGenerator {
	private boolean hasPrimKeyField = false;

	/**
	 * EJBLinkMethodGenerator constructor comment.
	 */
	public EJBRoleMethodGenerator() {
		super();
	}

	/**
	 * This implementation initializes the generators with the RoleHelper.
	 */
	protected void createDependentGenerator(String aGeneratorName) throws GenerationException {
		createDependentGenerator(aGeneratorName, getSourceElement());
	}

	/**
	 * This implementation initializes the generators with
	 * 
	 * @anObject.
	 */
	protected void createDependentGenerator(String aGeneratorName, Object anObject) throws GenerationException {
		IDependentGenerator depGen = getDependentGenerator(aGeneratorName);
		depGen.initialize(anObject);
	}

	protected String format(String pattern, String[] replacements) {
		return java.text.MessageFormat.format(pattern, replacements);
	}

	protected RoleHelper getRoleHelper() {
		return (RoleHelper) getSourceElement();
	}

	/**
	 * Insert the method's description here. Creation date: (6/7/2001 1:00:46 PM)
	 * 
	 * @return boolean
	 */
	protected boolean hasPrimKeyField() {
		return hasPrimKeyField;
	}

	/**
	 * Initialize the flag for the prim-key field from the RoleHelper's type.
	 */
	public void initialize(Object roleHelper) throws GenerationException {
		super.initialize(roleHelper);
		initializeHasPrimKeyField();
	}

	protected void initializeHasPrimKeyField() {
		ContainerManagedEntity cmp = getRoleHelper().getRoleType();
		CMPAttribute primKeyAttribute = cmp == null ? null : cmp.getPrimKeyField();
		setHasPrimKeyField(primKeyAttribute != null);
	}

	/**
	 * Insert the method's description here. Creation date: (6/7/2001 1:00:46 PM)
	 * 
	 * @return boolean
	 */
	protected boolean setHasPrimKeyField(boolean aBoolean) {
		return hasPrimKeyField = aBoolean;
	}
}