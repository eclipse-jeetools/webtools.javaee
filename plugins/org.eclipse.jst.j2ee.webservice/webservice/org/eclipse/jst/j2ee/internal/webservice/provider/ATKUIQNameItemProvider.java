/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.provider.QNameItemProvider;


public class ATKUIQNameItemProvider extends QNameItemProvider implements ITableItemLabelProvider {
	public ATKUIQNameItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnText.
	 */
	public String getColumnText(Object object, int columnIndex) {
		QName qname = (QName) object;
		switch (columnIndex) {
			case 0 :
				return toDisplayString(qname.getNamespaceURI());
			case 1 :
				return toDisplayString(qname.getLocalPart());
			default :
				StringBuffer sb = new StringBuffer();
				sb.append(qname.getNamespaceURI());
				sb.append("#"); //$NON-NLS-1$
				sb.append(qname.getLocalPart());
				return sb.toString();
		}
	}

	protected String toDisplayString(String s) {
		return (s != null) ? s : ""; //$NON-NLS-1$
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnImage.
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		return null;
	}
}