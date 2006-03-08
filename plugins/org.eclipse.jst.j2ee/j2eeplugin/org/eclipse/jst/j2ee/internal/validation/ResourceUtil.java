/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.internal.adapters.jdom.JDOMAdaptor;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.validation.internal.operations.IResourceUtil;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.xml.core.internal.document.ElementImpl;
import org.eclipse.wst.xml.core.internal.emf2xml.EMF2DOMSSEAdapter;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Node;


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
			EMF2DOMSSEAdapter adapter = null;

			Collection c = ro.eAdapters();
			if (c != null) {
				Iterator iterator = c.iterator();
				while (iterator.hasNext()) {
					Adapter a = (Adapter) iterator.next();
//					if ((a != null) && a.isAdapterForType(ReadAdaptor.TYPE_KEY) && (a instanceof JDOMAdaptor)) {
//						jdomAdapter = (JDOMAdaptor) a;
//					}
					if ((a != null) && (a instanceof EMF2DOMSSEAdapter)) {
						adapter = (EMF2DOMSSEAdapter) a;
						Node node = adapter.getNode();
						if( node instanceof IDOMNode ){
							IDOMNode idomNode = (IDOMNode)node;
							//int no = idomNode.getEndOffset();
							int no = idomNode.getStartOffset();
							IStructuredDocument structureDoc = (IStructuredDocument)idomNode.getStructuredDocument();
							structureDoc.getNumberOfLines();
							int LineNo = structureDoc.getLineOfOffset(no);
							System.out.println("Line no=" + LineNo);
						}else if( node instanceof ElementImpl ){
							ElementImpl impl = (ElementImpl)node;
							int no = impl.getStartOffset();
						}
						node.getNodeName();
						node.getNodeValue();


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
