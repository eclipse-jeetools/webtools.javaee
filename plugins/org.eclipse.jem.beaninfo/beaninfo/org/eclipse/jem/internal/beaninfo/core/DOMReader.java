package org.eclipse.jem.internal.beaninfo.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: DOMReader.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/22 23:49:10 $ 
 */

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * The internal class for reading dom elements.
 * @version 	1.0
 * @author
 */
class DOMReader implements IReader {
	
	/*
	 * @see IReader#getChildren(Object)
	 */
	public Object getChildren(Object node) {
		return (node instanceof Node) ? ((Node) node).getChildNodes() : null;
	}
	
	
	/*
	 * @see IReader#getLength(Object)
	 */
	public int getLength(Object nodeList) {
		return (nodeList instanceof NodeList) ? ((NodeList) nodeList).getLength() : 0;
	}

	/*
	 * @see IReader#getItem(Object, int)
	 */
	public Object getItem(Object nodeList, int index) {
		return (nodeList instanceof NodeList) ? ((NodeList) nodeList).item(index) : null;
	}

	/*
	 * @see IReader#isNodeTypeElement(Object)
	 */
	public boolean isNodeTypeElement(Object node) {
		return (node instanceof Node) ? ((Node) node).getNodeType() == Node.ELEMENT_NODE : false;
	}

	/*
	 * @see IReader#getNodeName(Object)
	 */
	public String getNodeName(Object node) {
		return (node instanceof Node) ? ((Node) node).getNodeName() : ""; //$NON-NLS-1$
	}

	/*
	 * @see IReader#getAttribute(Object, String)
	 */
	public String getAttribute(Object element, String attributeName) {
		return (element instanceof Element) && ((Element) element).hasAttribute(attributeName) ? ((Element) element).getAttribute(attributeName) : null;
	}

}