/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*


 */

import java.lang.reflect.Array;

import org.eclipse.core.runtime.IConfigurationElement;

import org.eclipse.jem.internal.beaninfo.adapters.*;


/**
 * For reading from IConfigurationElements
 * @version 	1.0
 * @author
 */
class ConfigurationElementReader implements IReader {

	/*
	 * @see IReader#getChildren(Object)
	 */
	public Object getChildren(Object node) {
		return (node instanceof IConfigurationElement) ? ((IConfigurationElement) node).getChildren() : new IConfigurationElement[0];
	}

	/*
	 * @see IReader#getLength(Object)
	 */
	public int getLength(Object nodeList) {
		return (nodeList instanceof IConfigurationElement[]) ? Array.getLength(nodeList) : 0;
	}

	/*
	 * @see IReader#getItem(Object, int)
	 */
	public Object getItem(Object nodeList, int index) {
		return (nodeList instanceof IConfigurationElement[]) ? Array.get(nodeList, index) : null;
	}

	/*
	 * @see IReader#isNodeTypeElement(Object)
	 */
	public boolean isNodeTypeElement(Object node) {
		return node instanceof IConfigurationElement;
	}

	/*
	 * @see IReader#getNodeName(Object)
	 */
	public String getNodeName(Object node) {
		return (node instanceof IConfigurationElement) ? ((IConfigurationElement) node).getName() : ""; //$NON-NLS-1$
	}

	/*
	 * @see IReader#getAttribute(Object, String)
	 */
	public String getAttribute(Object element, String attributeName) {
		return (element instanceof IConfigurationElement) ? ((IConfigurationElement) element).getAttribute(attributeName) : null;
	}

}
