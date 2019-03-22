/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Set;

import com.ibm.icu.text.UTF16;
import com.ibm.icu.util.StringTokenizer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.datamodel.properties.IAddWebComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * 
 */
public class AddWebComponentToEARDataModelProvider extends AddComponentToEnterpriseApplicationDataModelProvider implements IAddWebComponentToEnterpriseApplicationDataModelProperties {

	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
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
	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean notify = super.propertySet(propertyName, propertyValue);
		if (notify && propertyName.equals(CONTEXT_ROOT))
			model.notifyPropertyChange(CONTEXT_ROOT, IDataModel.DEFAULT_CHG);
		return notify;
	}

	/**
	 * 
	 */
	@Override
	public Object getDefaultProperty(String propertyName) {
		// if (propertyName.equals(CONTEXT_ROOT))
		// return getDefaultContextRoot();
		return super.getDefaultProperty(propertyName);
	}


	/**
	 * 
	 */
	@Override
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
		if (contextRoot.equals("")) { //$NON-NLS-1$
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Context_Root_cannot_be_empty_2, new Object[]{contextRoot}), null); 
		} else if (contextRoot.trim().equals(contextRoot)) {
			StringTokenizer stok = new StringTokenizer(contextRoot, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				int cp;
		        for (int i = 0; i < token.length(); i += UTF16.getCharCount(cp)) {
		            cp = UTF16.charAt(token, i);
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						String invalidCharString = null;
						if (UTF16.getCharCount(cp)>1)
						{
							invalidCharString = UTF16.valueOf(cp); 
						}
						else
						{
							invalidCharString = (new Character(token.charAt(i))).toString();
						}
						Object[] invalidChar = new Object[]{invalidCharString};
						String errorStatus = ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.The_character_is_invalid_in_a_context_root, invalidChar); 
						return J2EEPlugin.newErrorStatus(errorStatus, null);
					}
				}
			}
		} else
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Names_cannot_begin_or_end_with_whitespace_5, new Object[]{contextRoot}), null); 
		return OK_STATUS;
	}
}
