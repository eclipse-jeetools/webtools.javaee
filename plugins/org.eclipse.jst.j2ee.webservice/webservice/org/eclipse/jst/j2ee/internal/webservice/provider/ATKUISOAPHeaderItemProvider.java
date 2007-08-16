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
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;




public class ATKUISOAPHeaderItemProvider extends SOAPHeaderItemProvider implements ITableItemLabelProvider {
	public ATKUISOAPHeaderItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnText.
	 */
	public String getColumnText(Object object, int columnIndex) {
		SOAPHeader soapHeader = (SOAPHeader) object;
		switch (columnIndex) {
			case 0 :
				return toDisplayString(soapHeader.getNamespaceURI());
			case 1 :
				return toDisplayString(soapHeader.getLocalPart());
			default :
				StringBuffer sb = new StringBuffer();
				sb.append(soapHeader.getNamespaceURI());
				sb.append("#"); //$NON-NLS-1$
				sb.append(soapHeader.getLocalPart());
				return sb.toString();
		}
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnImage.
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		return null;
	}
}