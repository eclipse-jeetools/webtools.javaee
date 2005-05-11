package org.eclipse.jem.internal.proxy.core;
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
 *  $RCSfile: IRectangleBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
 */


/**
 * Interface to a Rectangle bean proxy.
 * <p>
 * These are common for different windowing systems, e.g. AWT and SWT. So this here
 * is common interface for them.
 * Creation date: (4/7/00 3:46:39 PM)
 * @author: Administrator
 */
public interface IRectangleBeanProxy extends IBeanProxy {
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
	public void setLocation(int x, int y);
	public void setLocation(IPointBeanProxy point);
	
	public int getHeight();
	public int getWidth();
	public void setHeight(int height);
	public void setWidth(int width);
	public void setSize(int width, int height);
	public void setSize(IDimensionBeanProxy dim);
	
	public void setBounds(int x, int y, int width, int height);
	public void setBounds(IRectangleBeanProxy rect);	
}
