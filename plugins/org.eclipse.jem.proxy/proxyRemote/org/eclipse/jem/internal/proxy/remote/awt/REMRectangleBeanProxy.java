package org.eclipse.jem.internal.proxy.remote.awt;
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
 *  $RCSfile: REMRectangleBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.remote.*;
import org.eclipse.jem.internal.proxy.awt.*;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * The REM Bean Proxy for java.awt.Rectangle.
 */
public class REMRectangleBeanProxy extends REMBeanProxy implements IRectangleBeanProxy {
	
	protected REMRectangleBeanProxy(REMProxyFactoryRegistry aRegistry, Integer anID, IBeanTypeProxy aType) {	
		super(aRegistry, anID, aType);
	}
	
	public int getX() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleXFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public int getY() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleYFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public void setX(int x) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleXFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(x));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setY(int y) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleYFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(y));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setLocation(int x, int y) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleSetLocationXYProxy().invokeCatchThrowableExceptions(this, new IBeanProxy[] {fFactory.getBeanProxyFactory().createBeanProxyWith(x), fFactory.getBeanProxyFactory().createBeanProxyWith(y)});
	}
	
	public void setLocation(IPointBeanProxy fromPointProxy) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleSetLocationPProxy().invokeCatchThrowableExceptions(this, fromPointProxy);
	}
	
	
	public int getHeight() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleHeightFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public int getWidth() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleWidthFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public void setHeight(int height) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleHeightFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(height));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setWidth(int width) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleWidthFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(width));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setSize(int width, int height) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleSetSizeHWProxy().invokeCatchThrowableExceptions(this, new IBeanProxy[] {fFactory.getBeanProxyFactory().createBeanProxyWith(width), fFactory.getBeanProxyFactory().createBeanProxyWith(height)});
	}
	
	public void setSize(IDimensionBeanProxy fromDimensionProxy) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleSetSizeDProxy().invokeCatchThrowableExceptions(this, fromDimensionProxy);
	}
	
	public void setBounds(int x, int y, int width, int height) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleSetBoundsXYHWProxy().invokeCatchThrowableExceptions(this, new IBeanProxy[] {fFactory.getBeanProxyFactory().createBeanProxyWith(x), fFactory.getBeanProxyFactory().createBeanProxyWith(y), fFactory.getBeanProxyFactory().createBeanProxyWith(width), fFactory.getBeanProxyFactory().createBeanProxyWith(height)});
	}
	
	public void setBounds(IRectangleBeanProxy fromRectangleProxy) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getRectangleSetBoundsRProxy().invokeCatchThrowableExceptions(this, fromRectangleProxy);
	}			
}


