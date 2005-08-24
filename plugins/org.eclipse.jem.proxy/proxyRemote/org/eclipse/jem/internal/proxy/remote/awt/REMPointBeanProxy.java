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
package org.eclipse.jem.internal.proxy.remote.awt;
/*
 *  $RCSfile: REMPointBeanProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
 */

import org.eclipse.jem.internal.proxy.remote.*;
import org.eclipse.jem.internal.proxy.awt.*;
import org.eclipse.jem.internal.proxy.core.*;

/**
 * The REM Bean Proxy for java.awt.Point.
 */
public class REMPointBeanProxy extends REMBeanProxy implements IPointBeanProxy {
	
	protected REMPointBeanProxy(REMProxyFactoryRegistry aRegistry, Integer anID, IBeanTypeProxy aType) {	
		super(aRegistry, anID, aType);
	}
	
	public int getX() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getPointXFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public int getY() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getPointYFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public void setX(int x) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getPointXFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(x));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setY(int y) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getPointYFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(y));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setLocation(int x, int y) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getPointSetLocationXYProxy().invokeCatchThrowableExceptions(this, new IBeanProxy[] {fFactory.getBeanProxyFactory().createBeanProxyWith(x), fFactory.getBeanProxyFactory().createBeanProxyWith(y)});
	}
	
	public void setLocation(IPointBeanProxy fromPointProxy) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getPointSetLocationPProxy().invokeCatchThrowableExceptions(this, fromPointProxy);
	}	
}


