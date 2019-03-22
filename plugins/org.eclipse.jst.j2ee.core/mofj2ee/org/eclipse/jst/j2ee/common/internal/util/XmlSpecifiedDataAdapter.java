/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.util;

/**
 * Insert the type's description here.
 * Creation date: (06/04/01 7:56:22 PM)
 * @author: Administrator
 */
public class XmlSpecifiedDataAdapter extends org.eclipse.emf.common.notify.impl.AdapterImpl {
	protected final static String ADAPTER_KEY = "XML_SPECIFIED_ADAPTER"; //$NON-NLS-1$
/**
 * XmlSpecifiedDataAdapter constructor comment.
 */
public XmlSpecifiedDataAdapter() {
	super();
}
@Override
public boolean isAdapterForType(Object type) {
	return ADAPTER_KEY.equals(type);
}
}


