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
/*
 * Created on Mar 17, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddJspPropGroupDataModel extends J2EEModelModifierOperationDataModel {
	public static final String DISPLAY_NAME = "AddJspPropGroupDataModel.DISPLAY_NAME"; //$NON-NLS-1$
	public static final String DESCRIPTION = "AddJspPropGroupDataModel.DESCRIPTION"; //$NON-NLS-1$
	public static final String PAGE_ENCODING = "AddJspPropGroupDataModel.PAGE_ENCODING"; //$NON-NLS-1$
	public static final String IS_XML = "AddJspPropGroupDataModel.IS_XML"; //$NON-NLS-1$
	public static final String EL_IGNORED = "AddJspPropGroupDataModel.EL_IGNORED"; //$NON-NLS-1$
	public static final String SCRIPTING_VALID = "AddJspPropGroupDataModel.SCRIPTING_VALID"; //$NON-NLS-1$
	public static final String URL_PATTERNS = "AddJspPropGroupDataModel.URL_PATTERNS"; //$NON-NLS-1$
	public static final String INCLUDE_PRELUDES = "AddJspPropGroupDataModel.INCLUDE_PRELUDES"; //$NON-NLS-1$
	public static final String INCLUDE_CODAS = "AddJspPropGroupDataModel.INCLUDE_CODAS"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddJspPropGroupOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(DISPLAY_NAME);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(PAGE_ENCODING);
		addValidBaseProperty(IS_XML);
		addValidBaseProperty(EL_IGNORED);
		addValidBaseProperty(SCRIPTING_VALID);
		addValidBaseProperty(URL_PATTERNS);
		addValidBaseProperty(INCLUDE_PRELUDES);
		addValidBaseProperty(INCLUDE_CODAS);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(IS_XML)) {
			return new Boolean(false);
		}
		if (propertyName.equals(EL_IGNORED)) {
			return new Boolean(false);
		}
		if (propertyName.equals(SCRIPTING_VALID)) {
			return new Boolean(false);
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(PAGE_ENCODING))
			return validatePageEncoding(getStringProperty(propertyName));
		if (propertyName.equals(URL_PATTERNS))
			return validateURLPatterns((List) getProperty(propertyName));
		if (propertyName.equals(INCLUDE_PRELUDES))
			return validateIncludePreludes((List) getProperty(propertyName));
		if (propertyName.equals(INCLUDE_CODAS))
			return validateIncludeCodas((List) getProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	private IStatus validatePageEncoding(String prop) {
		if (prop == null || prop.trim().length() == 0) {
			String msg = WebMessages.getResourceString(WebMessages.ERR_PAGE_ENCODING_EMPTY, new String[]{prop});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateURLPatterns(List prop) {
		if (prop != null && !prop.isEmpty()) {
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_URL_PATTERN_EXIST);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateIncludePreludes(List prop) {
		if (prop != null && !prop.isEmpty()) {
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_INCLUDE_PRELUDES_EXIST);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private IStatus validateIncludeCodas(List prop) {
		if (prop != null && !prop.isEmpty()) {
			boolean dup = hasDuplicatesInStringArrayList(prop);
			if (dup) {
				String msg = WebMessages.getResourceString(WebMessages.ERR_INCLUDE_CODAS_EXIST);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	private boolean hasDuplicatesInStringArrayList(List input) {
		if (input == null)
			return false;
		int n = input.size();
		boolean dup = false;
		for (int i = 0; i < n; i++) {
			String[] sArray1 = (String[]) input.get(i);
			for (int j = i + 1; j < n; j++) {
				String[] sArray2 = (String[]) input.get(j);
				if (isTwoStringArraysEqual(sArray1, sArray2)) {
					dup = true;
					break;
				}
			}
			if (dup)
				break;
		}
		return dup;
	}

	private boolean isTwoStringArraysEqual(String[] sArray1, String[] sArray2) {
		if (sArray1 == null || sArray2 == null)
			return false;
		int n1 = sArray1.length;
		int n2 = sArray1.length;
		if (n1 == 0 || n2 == 0)
			return false;
		if (n1 != n2)
			return false;
		for (int k = 0; k < n1; k++) {
			if (!sArray1[k].equals(sArray2[k])) {
				return false;
			}
		}
		return true;
	}
}