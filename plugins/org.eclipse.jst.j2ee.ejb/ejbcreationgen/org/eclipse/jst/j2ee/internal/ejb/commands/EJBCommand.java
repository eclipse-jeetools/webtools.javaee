/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;



import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBGenerationHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;


abstract public class EJBCommand extends AbstractEJBCommand implements IEJBCommand {
	protected EtoolsCopyUtility copyUtility;
	protected EObject sourceMetaType;
	protected boolean generateMetadata = true;
	protected boolean generateJava = true;
	protected EJBGenerationHelper helper;
	protected EJBGenerationHelper inverseHelper;
	public org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory metadataHistory;
	public org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory inverseMetadataHistory;

	/**
	 * Insert the method's description here. Creation date: (8/20/2000 6:32:52 PM)
	 */
	public EJBCommand() {
	}

	public EJBCommand(String desc) {
		super(null, desc);
	}

	/**
	 * Append aHelper to the parent EnterpriseBeanCommand.
	 */
	public void append(EJBGenerationHelper aHelper) {
		getParent().append(aHelper);
	}

	/**
	 * Append aHelper to the parent EnterpriseBeanCommand.
	 */
	public void appendInverse(EJBGenerationHelper aHelper) {
		((EnterpriseBeanCommand) getParent()).appendInverse(aHelper);
	}

	/**
	 * Return the number of units that are being worked.
	 */
	public int calculateTotalWork() {
		return 1;
	}

	// Cancel the command. Send when the command is
	// no longer needed so that it can clean up any resources.
	public void cancel() {
		// Default is to do nothing. Override if your command needs
		// to clean up.
	}

	protected abstract EJBGenerationHelper createCodegenHelper();

	protected void createCopy() {
		if (getSourceMetaType() != null)
			getCopyUtility().copy(getSourceMetaType());
	}

	protected void createCopyIfNecessary() {
		EObject copy = getMetadataCopy();
		if (copy == null && getSourceMetaType() != null)
			createCopy();
	}

	/**
	 * Return a helper that is the inverse of the codegen helper.
	 */
	protected abstract EJBGenerationHelper createInverseCodegenHelper();

	/**
	 * Override to setup MetadataHistory if necessary.
	 */
	protected MetadataHistory createInverseMetadataHistory() {
		inverseMetadataHistory = primCreateMetadataHistory();
		if (inverseMetadataHistory != null)
			inverseMetadataHistory.setOldMetadata(getSourceMetaType());
		return inverseMetadataHistory;
	}

	/**
	 * Override to setup MetadataHistory if necessary.
	 */
	protected MetadataHistory createMetadataHistory() {
		metadataHistory = primCreateMetadataHistory();
		initializeOldMetadata();
		return metadataHistory;
	}

	protected void initializeOldMetadata() {
		if (metadataHistory != null && metadataHistory.getOldMetadata() == null)
			metadataHistory.setOldMetadata(findOriginalSourceMetaType());
	}

	/**
	 * Decide whether to execute for metadata updates and/or Java generation.
	 */
	public void execute() {
		setupMetadataHistory();
		if (shouldGenerateMetadata())
			executeForMetadataGeneration();
		if (shouldGenerateJava())
			executeForCodeGeneration();
		worked(1);
	}

	/**
	 * Append the codegen Helper. Override in the subclass, if more needs to be done to prepare for
	 * Java generation.
	 */
	protected void executeForCodeGeneration() throws CommandExecutionFailure {
		setupHelper();
		append(getHelper());
	}

	/**
	 * Override to perform the necessary operation to update the Metadata.
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		//	createMetadataHistory();
	}

	/**
	 * By default return the copy of the sourceMetaType. Creation date: (11/20/2000 10:28:03 AM)
	 * 
	 * @return EObject
	 */
	public EObject findOriginalSourceMetaType() {
		return getMetadataCopy();
	}

	/**
	 * This returns the collection of things which this command wishes to present as the objects
	 * affected by the command. Typically should could be used as the selection that should be
	 * highlighted to best illustrate the effect of the command. The result of calling this before
	 * an execute, redo, or undo is undefined. The result may be different after an undo than it is
	 * after an execute or redo, but the result should be the same (equivalent) after either an
	 * execute or redo.
	 */
	public Collection getAffectedObjects() {
		return java.util.Collections.EMPTY_LIST;
	}

	/**
	 * Return the codegen command from the parent since dependent commands do not have their own
	 * codegen component.
	 */
	public EnterpriseBeanCodegenCommand getCodegenCommand() {
		return getParent().getCodegenCommand();
	}

	public EObject getCopy(EObject anObject) {
		if (hasParent())
			return getParent().getCopy(anObject);
		return getCopyUtility().getCopy(anObject);
	}

	/**
	 * Insert the method's description here. Creation date: (12/21/2000 3:04:07 PM)
	 * 
	 * @return com.ibm.etools.emf.ecore.utilities.copy.EtoolsCopyUtility
	 */
	public EtoolsCopyUtility getCopyUtility() {
		if (hasParent())
			return getParent().getCopyUtility();
		return primGetCopyUtility();
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 3:20:36 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.EJBGenerationHelper
	 */
	public EJBGenerationHelper getHelper() {
		if (helper == null) {
			helper = createCodegenHelper();
		}
		return helper;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 4:02:47 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.EJBGenerationHelper
	 */
	public EJBGenerationHelper getInverseHelper() {
		if (inverseHelper == null) {
			inverseHelper = createInverseCodegenHelper();
		}
		return inverseHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (3/6/2001 10:21:27 AM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.MetadataHistory
	 */
	public org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory getInverseMetadataHistory() {
		return inverseMetadataHistory;
	}

	/**
	 * Return the copy of the current sourceMetaType.
	 */

	public EObject getMetadataCopy() {
		return getCopy(getSourceMetaType());
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 1:03:59 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.MetadataHistory
	 */
	public org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.MetadataHistory getMetadataHistory() {
		return metadataHistory;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 10:23:32 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getName() {
		return getDescription();
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getOldName() {
		return getHelper().getOldName();
	}

	/**
	 * This returns collection of things which this command wishes to present as it's result. The
	 * result of calling this before an execute or redo, or after an undo, is undefined.
	 */
	public Collection getResult() {
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 10:28:03 AM)
	 * 
	 * @return EObject
	 */
	public EObject getSourceMetaType() {
		return sourceMetaType;
	}

	public boolean isRoot() {
		return getParent() == null;
	}

	/**
	 * Override to return the proper MetadataHistory.
	 */
	protected MetadataHistory primCreateMetadataHistory() {
		return new MetadataHistory();
	}

	/**
	 * Insert the method's description here. Creation date: (12/21/2000 3:04:07 PM)
	 * 
	 * @return com.ibm.etools.emf.ecore.utilities.copy.EtoolsCopyUtility
	 */
	protected EtoolsCopyUtility primGetCopyUtility() {
		if (copyUtility == null)
			copyUtility = new EtoolsCopyUtility();
		return copyUtility;
	}

	/**
	 * redo method comment.
	 */
	public void redo() {
		setCopyUtility(null);
		execute();
	}

	/**
	 * no-op. Dependent commands do not have a codegen component.
	 */
	public void setCodegenCommand(IEJBCommand newCodegenCommand) {
	}

	/**
	 * Insert the method's description here. Creation date: (12/21/2000 3:04:07 PM)
	 * 
	 * @param newCopyUtility
	 *            com.ibm.etools.emf.ecore.utilities.copy.EtoolsCopyUtility
	 */
	public void setCopyUtility(EtoolsCopyUtility newCopyUtility) {
		copyUtility = newCopyUtility;
	}

	/**
	 * Insert the method's description here. Creation date: (10/9/2000 8:55:19 AM)
	 * 
	 * @param newGenerateJava
	 *            boolean
	 */
	public void setGenerateJava(boolean newGenerateJava) {
		generateJava = newGenerateJava;
	}

	/**
	 * Insert the method's description here. Creation date: (10/9/2000 8:55:19 AM)
	 * 
	 * @param newGenerateMetadata
	 *            boolean
	 */
	public void setGenerateMetadata(boolean newGenerateMetadata) {
		generateMetadata = newGenerateMetadata;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 4:02:47 PM)
	 * 
	 * @param newInverseHelper
	 *            org.eclipse.jst.j2ee.ejb.codegen.helpers.EJBGenerationHelper
	 */
	public void setInverseHelper(EJBGenerationHelper newInverseHelper) {
		inverseHelper = newInverseHelper;
	}

	/**
	 * Insert the method's description here. Creation date: (9/5/2000 10:23:32 PM)
	 * 
	 * @param newName
	 *            java.lang.String
	 */
	public void setName(java.lang.String newName) {
		setDescription(newName);
	}

	/**
	 * Insert the method's description here. Creation date: (11/20/2000 10:28:03 AM)
	 * 
	 * @param newSourceMetaType
	 *            org.eclipse.emf.ecore.EObject
	 */
	public void setSourceMetaType(EObject newSourceMetaType) {
		sourceMetaType = newSourceMetaType;
	}

	/**
	 * Override to perform any setup on the code generation helper before being passed to code
	 * generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupHelper() {
		if (getHelper() != null) {
			getHelper().setMetadataHistory(getMetadataHistory());
			if (getHelper().getMetaObject() == null)
				getHelper().setMetaObject(getSourceMetaType());
		}
	}

	/**
	 * Override to perform any setup on the code generation inverse helper before being passed to
	 * code generation framework. Creation date: (10/11/2000 10:30:08 AM)
	 */
	protected void setupInverseHelper() {
		getInverseHelper().setMetadataHistory(getInverseMetadataHistory());
	}

	protected void setupMetadataHistory() {
		createCopyIfNecessary();
		createMetadataHistory();
	}

	/**
	 * Insert the method's description here. Creation date: (10/9/2000 8:55:19 AM)
	 * 
	 * @return boolean
	 */
	public boolean shouldGenerateJava() {
		return generateJava;
	}

	/**
	 * Insert the method's description here. Creation date: (10/9/2000 8:55:19 AM)
	 * 
	 * @return boolean
	 */
	public boolean shouldGenerateMetadata() {
		return generateMetadata;
	}

	public void subTask(String name) {
		if (hasParent())
			getParent().subTask(name);
	}

	/**
	 * Decide whether to undo for metadata updates and/or Java generation.
	 */
	public void undo() {
		if (shouldGenerateMetadata())
			undoMetadataGeneration();
		if (shouldGenerateJava())
			undoJavaGeneration();
		worked(1);
	}

	/**
	 * Append the inverse codegen Helper. Override in the subclass, if more needs to be done to
	 * prepare for undoing Java generation.
	 */
	protected void undoJavaGeneration() {
		setupInverseHelper();
		appendInverse(getInverseHelper());
	}

	/**
	 * Override to perform the necessary operation to undo the Metadata update.
	 */
	protected void undoMetadataGeneration() {
		createInverseMetadataHistory();
		setSourceMetaType(null);
	}

	public void worked(int units) {
		if (hasParent())
			getParent().worked(units);
	}

	public void setShouldClearCopierOnPropagation(boolean shouldClearCopier) {
		getParent().setShouldClearCopierOnPropagation(shouldClearCopier);
	}
}