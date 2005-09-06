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

import java.util.Collection;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.datamodel.properties.IAddWebComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * 
 */
public class AddWebComponentToEARDataModelProvider extends AddComponentToEnterpriseApplicationDataModelProvider implements IAddWebComponentToEnterpriseApplicationDataModelProperties {

	public Collection getPropertyNames() {
		Collection propertyNames = super.getPropertyNames();
		propertyNames.add(CONTEXT_ROOT);
		return propertyNames;
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
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean notify = super.propertySet(propertyName, propertyValue);
		if (notify && propertyName.equals(CONTEXT_ROOT))
			model.notifyPropertyChange(CONTEXT_ROOT, IDataModel.DEFAULT_CHG);
		return notify;
	}

	/**
	 * 
	 */
	public Object getDefaultProperty(String propertyName) {
		// if (propertyName.equals(CONTEXT_ROOT))
		// return getDefaultContextRoot();
		return super.getDefaultProperty(propertyName);
	}


	/**
	 * 
	 */
	public IStatus validate(String propertyName) {
		if (CONTEXT_ROOT.equals(propertyName)) {
			return validateContextRoot(getStringProperty(CONTEXT_ROOT));
		}
		return super.validate(propertyName);
	}

	/**
	 * 
	 * @param contextRoot
	 * @return
	 */
	public IStatus validateContextRoot(String contextRoot) {
		if (contextRoot.equals("") || contextRoot == null) { //$NON-NLS-1$
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Context_Root_cannot_be_empty_2", new Object[]{contextRoot}), null); //$NON-NLS-1$
		} else if (contextRoot.trim().equals(contextRoot)) {
			StringTokenizer stok = new StringTokenizer(contextRoot, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				for (int i = 0; i < token.length(); i++) {
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						Object[] invalidChar = new Object[]{(new Character(token.charAt(i))).toString()};
						String errorStatus = ProjectSupportResourceHandler.getString("The_character_is_invalid_in_a_context_root", invalidChar); //$NON-NLS-1$
						return J2EEPlugin.newErrorStatus(errorStatus, null);
					}
				}
			}
		} else
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString("Names_cannot_begin_or_end_with_whitespace_5", new Object[]{contextRoot}), null); //$NON-NLS-1$
		return OK_STATUS;
	}
}