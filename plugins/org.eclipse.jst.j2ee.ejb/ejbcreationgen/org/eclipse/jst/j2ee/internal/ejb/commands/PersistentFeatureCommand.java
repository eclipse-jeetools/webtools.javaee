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
package org.eclipse.jst.j2ee.internal.ejb.commands;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.FeatureHelper;


/**
 * Insert the type's description here. Creation date: (9/1/2000 2:12:34 PM)
 * 
 * @author: Administrator
 */
public abstract class PersistentFeatureCommand extends EJBDependentCommand implements IPersistentFeatureCommand {
	protected static final String NULL_EJB = EJBCodeGenResourceHandler.getString("IWAJ0130I_Cannot_perform_t_ERROR_"); //$NON-NLS-1$ = "IWAJ0130I Cannot perform the command request because the EJB is null for the command:  "
	protected static final String NULL_FEATURE_ADD_KEY_ERROR = EJBCodeGenResourceHandler.getString("IWAJ0131I_Cannot_add_the_f_ERROR_"); //$NON-NLS-1$ = "IWAJ0131I Cannot add the feature to the key because the feature is null for the command: "
	protected static final String NULL_FEATURE_REMOVE_KEY_ERROR = EJBCodeGenResourceHandler.getString("IWAJ0132I_Cannot_remove_th_ERROR_"); //$NON-NLS-1$ = "IWAJ0132I Cannot remove the feature from the key because the feature is null for the command: "
	protected String name;
	protected boolean key = false;
	protected List addedReadOnlyMethods;
	protected List removedReadOnlyMethods;

	/**
	 * PersistentFeatureCommand constructor comment.
	 */
	public PersistentFeatureCommand(IRootCommand parent, String aName) {
		this(parent, aName, true);
	}

	/**
	 * PersistentFeatureCommand constructor comment.
	 */
	public PersistentFeatureCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		this(parent, aName, shouldGenJava, true);
	}

	/**
	 * PersistentFeatureCommand constructor comment.
	 */
	public PersistentFeatureCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, shouldGenJava, shouldGenMetadata);
		setName(aName);
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2001 12:55:06 PM)
	 * 
	 * @return java.util.List
	 */
	protected java.util.List getAddedReadOnlyMethods() {
		if (addedReadOnlyMethods == null)
			addedReadOnlyMethods = new ArrayList();
		return addedReadOnlyMethods;
	}

	public ContainerManagedEntity getContainerManagedEntity() {
		return (ContainerManagedEntity) getEjb();
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2001 12:57:22 PM)
	 * 
	 * @return java.util.List
	 */
	protected java.util.List getRemovedReadOnlyMethods() {
		if (removedReadOnlyMethods == null)
			removedReadOnlyMethods = new ArrayList();
		return removedReadOnlyMethods;
	}

	/**
	 * Initialize the feature and key attribute fields.
	 */
	public void initializeEjbDependentFields() {
		super.initializeEjbDependentFields();
		if (getContainerManagedEntity() != null) {
			initializeKey();
		}
	}

	protected abstract void initializeFeature();

	protected abstract void initializeKey();

	/**
	 * Initialize the sourceMetaType
	 */
	protected void initializeSourceMetaType() {
		initializeFeature();
	}

	/**
	 * Insert the method's description here. Creation date: (10/8/2000 5:25:46 PM)
	 * 
	 * @return boolean
	 */
	public boolean isKey() {
		return key;
	}

	/**
	 * We must return true by default because it is safer to return true when in fact the key shape
	 * is not changing than to return false when it is. We do not know when this is called and
	 * therefore we cannot be assured that it is initialized.
	 */
	public boolean isKeyShapeChangeCommand() {
		if (getSourceMetaType() == null)
			return true;
		return isKey();
	}

	protected void nullEJBError() {
		EJBCommandErrorHandler.handleError(NULL_EJB + this.toString());
	}

	protected void nullFeatureAddKeyError() {
		EJBCommandErrorHandler.handleError(NULL_FEATURE_ADD_KEY_ERROR + this.toString());
	}

	protected void nullFeatureRemoveKeyError() {
		EJBCommandErrorHandler.handleError(NULL_FEATURE_REMOVE_KEY_ERROR + this.toString());
	}

	/**
	 * Insert the method's description here. Creation date: (10/8/2000 5:25:46 PM)
	 * 
	 * @param newKey
	 *            boolean
	 */
	public void setKey(boolean newKey) {
		key = newKey;
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		FeatureHelper myHelper = (FeatureHelper) getHelper();
		myHelper.setKey(isKey());
		myHelper.setName(getName());
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelperHelper() {
		super.setupInverseHelper();
		FeatureHelper myHelper = (FeatureHelper) getHelper();
		myHelper.setKey(isKey());
		myHelper.setName(getName());
	}
}