package org.eclipse.jem.internal.proxy.awt;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IPointBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:53:47 $ 
 */


import org.eclipse.jem.internal.proxy.core.IBeanProxy;
/**
 * Interface to a Point bean proxy.
 * Creation date: (4/7/00 3:46:39 PM)
 * @author: Administrator
 */
public interface IPointBeanProxy extends IBeanProxy {
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public void setLocation(int x, int y);
	public void setLocation(IPointBeanProxy point);
}
