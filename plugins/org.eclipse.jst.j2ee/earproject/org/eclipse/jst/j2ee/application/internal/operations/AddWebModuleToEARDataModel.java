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

package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;


/**
 *
 */
public class AddWebModuleToEARDataModel extends AddComponentToEnterpriseApplicationDataModel {
	
	/**
	 * Optional - This is the context root stored with the module in the application.xml.
	 */
	public static final String CONTEXT_ROOT = "AddWebModuleToEARDataModel.CONTEXT_ROOT"; //$NON-NLS-1$

	/**
	 * 
	 */
	public String defaultContextRoot = ""; //$NON-NLS-1$
	
	/**
	 * 
	 * @param earModuleName
	 * @param module
	 * @return
	 */
	public static AddWebModuleToEARDataModel createAddWebModuleToEARDataModel(String earModuleName, WorkbenchComponent module) {
		AddWebModuleToEARDataModel model = new AddWebModuleToEARDataModel();
		model.setProperty(ArtifactEditOperationDataModel.MODULE_NAME, earModuleName);
		model.setProperty(AddArchiveToEARDataModel.ARCHIVE_MODULE, module);
		return model;
	}
	
	/**
	 * 
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(CONTEXT_ROOT);
	}

	/**
	 * 
	 */
	protected String getDefaultURIExtension() {
		return "war"; //$NON-NLS-1$
	}

	/**
	 * 
	 */
	public boolean isWebModuleArchive() {
		return true;
	}

	/**
	 * 
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean notify = super.doSetProperty(propertyName, propertyValue);
		if (notify && propertyName.equals(ARCHIVE_MODULE))
			notifyDefaultChange(CONTEXT_ROOT);
		return notify;
	}

	/**
	 * 
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CONTEXT_ROOT))
			return getDefaultContextRoot();
		return super.getDefaultProperty(propertyName);
	}

	/**
	 * 
	 * @return
	 */
	private String getDefaultContextRoot() {
		if (defaultContextRoot.equals("")) //$NON-NLS-1$
			return computeDefaultContextRoot();
		return defaultContextRoot;
	}

	private String computeDefaultContextRoot() {
		String wbCompName = (String)getProperty(ARCHIVE_MODULE);
		if (wbCompName != null && wbCompName.length() > 0)
			return wbCompName.replace(' ', '_');
		return null;
	}

	/**
	 * 
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (CONTEXT_ROOT.equals(propertyName)) {
			return validateContextRoot(getStringProperty(CONTEXT_ROOT));
		}
		return super.doValidateProperty(propertyName);
	}

	/**
	 * 
	 * @param contextRoot
	 * @return
	 */
	public IStatus validateContextRoot(String contextRoot) {
		if (contextRoot.equals("") || contextRoot == null) { //$NON-NLS-1$
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Context_Root_cannot_be_empty_2", new Object[]{contextRoot}), null); //$NON-NLS-1$
		}
		else if (contextRoot.trim().equals(contextRoot)) {
			StringTokenizer stok = new StringTokenizer(contextRoot, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				for (int i = 0; i < token.length(); i++) {
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						Object[] invalidChar = new Object[]{(new Character(token.charAt(i))).toString()};
						String errorStatus = ProjectSupportResourceHandler.getString("The_character_is_invalid_in_a_context_root",invalidChar); //$NON-NLS-1$
						return J2EEPlugin.newErrorStatus(errorStatus, null);
					}
				}
			}
		} 
		else
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Names_cannot_begin_or_end_with_whitespace_5", new Object[]{contextRoot}), null); //$NON-NLS-1$
		return OK_STATUS;
	}
}