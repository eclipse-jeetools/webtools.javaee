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



import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHistory;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory;



/**
 * Insert the type's description here. Creation date: (9/1/2000 2:13:07 PM)
 * 
 * @author: Administrator
 */
public abstract class PersistentAttributeCommand extends PersistentFeatureCommand implements IPersistentAttributeCommand {
	protected static final String ARRAY_BRACKETS = "[]";//$NON-NLS-1$
	protected boolean fReadOnly = false; // READ AccessIntent

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public PersistentAttributeCommand(IRootCommand parent, String aName) {
		super(parent, aName);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public PersistentAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava) {
		super(parent, aName, shouldGenJava);
	}

	/**
	 * PersistentAttributeCommand constructor comment.
	 */
	public PersistentAttributeCommand(IRootCommand parent, String aName, boolean shouldGenJava, boolean shouldGenMetadata) {
		super(parent, aName, shouldGenJava, shouldGenMetadata);
	}

	protected EJBGenerationHelper createCodegenHelper() {
		return new AttributeHelper(getAttribute());
	}



	protected EJBGenerationHelper createInverseCodegenHelper() {
		return new AttributeHelper(getOriginalAttribute());
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return int
	 */
	public int getArrayDimensions() {
		return getAttributeCodegenHelper().getArrayDimensions();
	}

	public CMPAttribute getAttribute() {
		return (CMPAttribute) getSourceMetaType();
	}

	private AttributeHelper getAttributeCodegenHelper() {
		return (AttributeHelper) getHelper();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInitializer() {
		return getAttributeCodegenHelper().getInitializer();
	}

	public CMPAttribute getOriginalAttribute() {
		return getMetadataHistory() == null ? null : (CMPAttribute) getMetadataHistory().getOldMetadata();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTypeName() {
		return getAttributeCodegenHelper().getTypeName();
	}

	protected void initializeFeature() {
		if (getAttribute() == null)
			setAttribute(getContainerManagedEntity().getPersistentAttribute(getName()));
	}

	protected void initializeKey() {
		if (!isKey() && getAttribute() != null)
			setKey(getAttribute().isKey());
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 10:46:21 PM)
	 * 
	 * @return boolean
	 */
	public boolean isArray() {
		return getAttributeCodegenHelper().isArray();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 2:19:59 PM)
	 * 
	 * @return boolean
	 */
	public boolean isRemote() {
		return getAttributeCodegenHelper().isRemote();
	}

	public boolean isLocal() {
		return getAttributeCodegenHelper().isLocal();
	}

	/**
	 * Override to return the proper MetadataHistory.
	 */
	protected MetadataHistory primCreateMetadataHistory() {
		return new AttributeHistory();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newArrayDimensions
	 *            int
	 */
	public void setArrayDimensions(int newArrayDimensions) {
		getAttributeCodegenHelper().setArrayDimensions(newArrayDimensions);
	}

	public void setAttribute(EAttribute anAttribute) {
		setSourceMetaType(anAttribute);
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newGenerateAccessors
	 *            boolean
	 */
	public void setGenerateAccessors(boolean newGenerateAccessors) {
		getAttributeCodegenHelper().setGenerateAccessors(newGenerateAccessors);
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newInitializer
	 *            java.lang.String
	 */
	public void setInitializer(java.lang.String newInitializer) {
		getAttributeCodegenHelper().setInitializer(newInitializer);
	}

	/**
	 * setIsReadOnly method comment. true will create a READ AccessIntent
	 */
	public void setIsReadOnly(boolean newIsReadOnly) {
		fReadOnly = newIsReadOnly;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 2:19:59 PM)
	 * 
	 * @param newRemote
	 *            boolean
	 */
	public void setRemote(boolean newRemote) {
		getAttributeCodegenHelper().setRemote(newRemote);
	}

	public void setLocal(boolean isLocal) {
		getAttributeCodegenHelper().setLocal(isLocal);
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @param newTypeName
	 *            java.lang.String
	 */
	public void setTypeName(java.lang.String newTypeName) {
		getAttributeCodegenHelper().setTypeName(newTypeName);
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		super.setupHelper();
		if (getTypeName() != null)
			((AttributeHelper) getHelper()).setTypeName(getTypeName());
	}

	/**
	 * Override to perform any setup on the code generation inverse helper before being passed to
	 * code generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		super.setupInverseHelper();
		if (getTypeName() != null)
			((AttributeHelper) getInverseHelper()).setTypeName(getTypeName());
	}

	/**
	 * @return Returns the fReadOnly.
	 */
	public boolean getIsReadOnly() {
		return fReadOnly;
	}

}