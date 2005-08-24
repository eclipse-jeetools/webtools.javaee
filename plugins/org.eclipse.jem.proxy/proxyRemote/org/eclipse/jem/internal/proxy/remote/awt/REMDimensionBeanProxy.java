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
 *  $RCSfile: REMDimensionBeanProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
 */

import org.eclipse.jem.internal.proxy.remote.*;
import org.eclipse.jem.internal.proxy.awt.*;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * The REM Bean Proxy for java.awt.Dimension.
 */
public class REMDimensionBeanProxy extends REMBeanProxy implements IDimensionBeanProxy {
	
	protected REMDimensionBeanProxy(REMProxyFactoryRegistry aRegistry, Integer anID, IBeanTypeProxy aType) {	
		super(aRegistry, anID, aType);
	}
	
	public int getHeight() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getDimensionHeightFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public int getWidth() {
		try {
			IIntegerBeanProxy h = (IIntegerBeanProxy) JavaStandardAwtBeanConstants.getConstants(fFactory).getDimensionWidthFieldProxy().get(this);
			return h.intValue();
		} catch (ThrowableProxy e) {
			return 0;
		}
	}
	
	public void setHeight(int height) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getDimensionHeightFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(height));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setWidth(int width) {
		try {
			JavaStandardAwtBeanConstants.getConstants(fFactory).getDimensionWidthFieldProxy().set(this, fFactory.getBeanProxyFactory().createBeanProxyWith(width));
		} catch (ThrowableProxy e) {
		}
	}
	
	public void setSize(int width, int height) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getDimensionSetSizeHWProxy().invokeCatchThrowableExceptions(this, new IBeanProxy[] {fFactory.getBeanProxyFactory().createBeanProxyWith(width), fFactory.getBeanProxyFactory().createBeanProxyWith(height)});
	}
	
	public void setSize(IDimensionBeanProxy fromDimensionProxy) {
		JavaStandardAwtBeanConstants.getConstants(fFactory).getDimensionSetSizeDProxy().invokeCatchThrowableExceptions(this, fromDimensionProxy);
	}	
}
