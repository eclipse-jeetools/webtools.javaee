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
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;


public class ATKUIPortComponentRefItemProvider extends PortComponentRefItemProvider implements ITableItemLabelProvider {
	public ATKUIPortComponentRefItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This does the same thing as ITableLabelProvider.getColumnText.
	 */
	public String getColumnText(Object object, int columnIndex) {
		PortComponentRef portCompRef = (PortComponentRef) object;
		switch (columnIndex) {
			case 0 :
				return toDisplayString(portCompRef.getServiceEndpointInterface());
			case 1 :
				return toDisplayString(portCompRef.getPortComponentLink());
			default :
				return toDisplayString(portCompRef.getServiceEndpointInterface());
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

	protected String toDisplayString(JavaClass javaClass) {
		String className = javaClass.getQualifiedNameForReflection();
		return (className != null) ? className : ""; //$NON-NLS-1$
	}

}