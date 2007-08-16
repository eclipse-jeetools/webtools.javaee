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
import org.eclipse.jst.j2ee.webservice.wscommon.PortName;


public class ATKUIPortNameItemProvider extends PortNameItemProvider implements ITableItemLabelProvider {
	public ATKUIPortNameItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnText.
	 */
	public String getColumnText(Object object, int columnIndex) {
		PortName portName = (PortName) object;
		return toDisplayString(portName.getPortName());
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnImage.
	 */
	public Object getColumnImage(Object object, int columnIndex) {
		return null;
	}
}