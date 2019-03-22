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
package org.eclipse.jem.internal.beaninfo.adapters;
/*


 */
/**
 * This is internal interface for reading beaninfoConfig either through IConfigurationElements or
 * w3.dom.Nodes. This is because when reading the .beaninfoConfig we will be using Nodes, but
 * when reading configs from registered extensions they will be IConfigurationElements.
 * @version 	1.0
 * TODO Need to move to core when we make things API.
 */
public interface IReader {
	
	public Object getChildren(Object node);
	public int getLength(Object nodeList);
	public Object getItem(Object nodeList, int index);
	public boolean isNodeTypeElement(Object node);	// Is this an element type node
	public String getNodeName(Object node);
	public String getAttribute(Object element, String attributeName);

}
