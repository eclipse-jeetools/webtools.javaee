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
package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;



import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.codegen.GenerationHelper;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Insert the type's description here. Creation date: (9/1/2000 1:16:42 PM)
 * 
 * @author: Administrator
 */
public abstract class EJBGenerationHelper extends GenerationHelper implements IEJBCodegenHelper {
	private static final String UNKNOWN = "Unknown";//$NON-NLS-1$
	private static final String LEFT_PAREN = "(", RIGHT_PAREN = ")";//$NON-NLS-2$//$NON-NLS-1$
	private EObject metaObject;
	private static final int COMMAND_CREATE = 0;
	private static final int COMMAND_DELETE = 1;
	private static final int COMMAND_UPDATE = 2;
	private int fCommandType = COMMAND_CREATE;
	private String fUpdateGeneratorName;
	public MetadataHistory metadataHistory;
	protected EObject oldMetadata;
	protected IEJBCodegenHandler codegenHandler;

	/**
	 * CodegenHelper constructor comment.
	 */
	public EJBGenerationHelper(EObject aMetaObject) {
		setMetaObject(aMetaObject);
	}

	protected static String format(String pattern, String[] replacements) {
		return java.text.MessageFormat.format(pattern, replacements);
	}

	public EnterpriseBean getEjb() {
		if (getParent() != null)
			return ((IEJBCodegenHelper) getParent()).getEjb();
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:59:49 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.ejb.codegen.helpers.MetadataHistory
	 */
	public MetadataHistory getMetadataHistory() {
		if (metadataHistory == null)
			metadataHistory = new MetadataHistory();
		return metadataHistory;
	}

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 8:45:38 PM)
	 * 
	 * @return org.eclipse.emf.ecore.EObject
	 */
	public EObject getMetaObject() {
		return metaObject;
	}

	/**
	 * Insert the method's description here. Creation date: (10/10/00 1:18:59 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getOldName() {
		if (getMetadataHistory() == null)
			return null;
		return getMetadataHistory().getName();
	}

	/**
	 * Insert the method's description here. Creation date: (10/12/2000 6:44:51 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getUpdateGeneratorName() {
		return fUpdateGeneratorName;
	}

	protected IEJBCodegenHandler getCodegenHandler() {
		if (codegenHandler == null) {
			IProject project = ProjectUtilities.getProject(getMetaObject());
			codegenHandler = EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(project);
		}
		return codegenHandler;
	}

	public boolean isClassReferenceHelper() {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2000 7:43:19 PM)
	 * 
	 * @return boolean
	 */
	public boolean isCreate() {
		return fCommandType == COMMAND_CREATE;
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2000 7:43:19 PM)
	 * 
	 * @return boolean
	 */
	public boolean isDelete() {
		return fCommandType == COMMAND_DELETE;
	}

	public boolean isPersistentFeatureHelper() {
		return false;
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2000 7:43:19 PM)
	 * 
	 * @return boolean
	 */
	public boolean isUpdate() {
		return fCommandType == COMMAND_UPDATE;
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2000 7:43:19 PM)
	 * 
	 * @return boolean
	 */
	public void setCreate() {
		fCommandType = COMMAND_CREATE;
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2000 7:43:19 PM)
	 * 
	 * @return boolean
	 */
	public void setDelete() {
		fCommandType = COMMAND_DELETE;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:59:49 PM)
	 * 
	 * @param newMetadataHistory
	 *            org.eclipse.jst.j2ee.ejb.codegen.helpers.MetadataHistory
	 */
	public void setMetadataHistory(MetadataHistory newMetadataHistory) {
		metadataHistory = newMetadataHistory;
	}

	/**
	 * Insert the method's description here. Creation date: (9/4/2000 8:45:38 PM)
	 * 
	 * @param newMetaObject
	 *            org.eclipse.emf.ecore.EObject
	 */
	public void setMetaObject(EObject newMetaObject) {
		metaObject = newMetaObject;
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2000 7:43:19 PM)
	 * 
	 * @return boolean
	 */
	public void setUpdate() {
		fCommandType = COMMAND_UPDATE;
	}

	/**
	 * Insert the method's description here. Creation date: (10/12/2000 6:44:51 PM)
	 * 
	 * @param newUpdateGeneratorName
	 *            java.lang.String
	 */
	public void setUpdateGeneratorName(java.lang.String newUpdateGeneratorName) {
		fUpdateGeneratorName = newUpdateGeneratorName;
	}

	public String toString() {
		String id = getMetaObject() == null ? UNKNOWN : ((XMIResource) getMetaObject().eResource()).getID(getMetaObject());
		return getClass().getName() + LEFT_PAREN + id + RIGHT_PAREN;
	}

	/**
	 * Subclasses should override if they are an extended helper.
	 */
	public boolean isHelperForType(Object type) {
		return false;
	}
}