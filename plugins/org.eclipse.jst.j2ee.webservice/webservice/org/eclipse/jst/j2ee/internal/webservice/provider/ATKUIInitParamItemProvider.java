/***************************************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.jst.j2ee.webservice.wscommon.InitParam;


public class ATKUIInitParamItemProvider extends InitParamItemProvider implements ITableItemLabelProvider {
	public ATKUIInitParamItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnText.
	 */
	@Override
	public String getColumnText(Object object, int columnIndex) {
		InitParam initParam = (InitParam) object;
		switch (columnIndex) {
			case 0 :
				return toDisplayString(initParam.getParamName());
			case 1 :
				return toDisplayString(initParam.getParamValue());
			case 2 :
				return toDisplayString(initParam.getDescription());
			default :
				StringBuffer sb = new StringBuffer();
				sb.append(initParam.getParamName());
				sb.append("="); //$NON-NLS-1$
				sb.append(initParam.getParamValue());
				return sb.toString();
		}
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnImage.
	 */
	@Override
	public Object getColumnImage(Object object, int columnIndex) {
		return null;
	}
}