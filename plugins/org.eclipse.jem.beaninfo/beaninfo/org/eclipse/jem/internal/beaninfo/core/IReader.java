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
 *  $RCSfile: IReader.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/22 23:49:10 $ 
 */
/**
 * This is internal interface for reading beaninfoConfig either through IConfigurationElements or
 * w3.dom.Nodes. This is because when reading the .beaninfoConfig we will be using Nodes, but
 * when reading configs from registered extensions they will be IConfigurationElements.
 * @version 	1.0
 * @author
 */
interface IReader {
	
	public Object getChildren(Object node);
	public int getLength(Object nodeList);
	public Object getItem(Object nodeList, int index);
	public boolean isNodeTypeElement(Object node);	// Is this an element type node
	public String getNodeName(Object node);
	public String getAttribute(Object element, String attributeName);

}
