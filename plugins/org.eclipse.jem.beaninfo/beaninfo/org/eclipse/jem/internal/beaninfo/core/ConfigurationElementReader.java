/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*
 *  $RCSfile: ConfigurationElementReader.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:31 $ 
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
		return (element instanceof IConfigurationElement) ? ((IConfigurationElement) element).getAttributeAsIs(attributeName) : null;
	}

}
