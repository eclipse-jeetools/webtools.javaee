/***************************************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.internal.adapters.jdom.JDOMAdaptor;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.wst.validation.internal.operations.IResourceUtil;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;


public class ResourceUtil implements IResourceUtil {
	public ResourceUtil() {
		super();
	}

	public int getLineNo(Object object) {
		if (object == null) {
			return IMessage.LINENO_UNSET;
		}

		if (object instanceof EObject) { // Error discovered using MOF objects

			EObject ro = (EObject) object;
			JDOMAdaptor jdomAdapter = null;

			Collection c = ro.eAdapters();
			if (c != null) {
				Iterator iterator = c.iterator();
				while (iterator.hasNext()) {
					Adapter a = (Adapter) iterator.next();
					if ((a != null) && a.isAdapterForType(ReadAdaptor.TYPE_KEY) && (a instanceof JDOMAdaptor)) {
						jdomAdapter = (JDOMAdaptor) a;
					}
				}
			}

			if (jdomAdapter != null) {
				int lineNo = jdomAdapter.getLineNo();
				if (lineNo != JDOMAdaptor.INVALID_LINENO)
					return lineNo + 1;
			}

		}

		return IMessage.LINENO_UNSET;
	}
}