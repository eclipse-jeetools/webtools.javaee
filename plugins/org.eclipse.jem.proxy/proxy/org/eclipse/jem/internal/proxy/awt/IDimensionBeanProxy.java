package org.eclipse.jem.internal.proxy.awt;
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
 *  $RCSfile: IDimensionBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */



import org.eclipse.jem.internal.proxy.core.IBeanProxy;
/**
 * Interface to a Dimension bean proxy.
 * Creation date: (4/7/00 3:46:39 PM)
 * @author: Administrator
 */
public interface IDimensionBeanProxy extends IBeanProxy {
	public int getHeight();
	public int getWidth();
	public void setHeight(int height);
	public void setWidth(int width);
	public void setSize(int width, int height);
	public void setSize(IDimensionBeanProxy dim);
}
