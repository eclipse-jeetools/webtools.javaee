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
package org.eclipse.jem.internal.proxy.ide.awt;
/*
 *  $RCSfile: IDEDimensionBeanProxy.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:39:07 $ 
 */

import java.awt.Dimension;

import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IDimensionBeanProxy;
import org.eclipse.jem.internal.proxy.ide.IDEObjectBeanProxy;
import org.eclipse.jem.internal.proxy.ide.IDEProxyFactoryRegistry;

public class IDEDimensionBeanProxy extends IDEObjectBeanProxy implements IDimensionBeanProxy {
	
	protected Dimension fDimension;

IDEDimensionBeanProxy(IDEProxyFactoryRegistry aRegistry,Object aDimension, IBeanTypeProxy aBeanTypeProxy){
	super(aRegistry,aDimension,aBeanTypeProxy);
	fDimension = (Dimension)aDimension;
}
public int getWidth(){
	return fDimension.width;
}
public void setWidth(int aWidth){
	fDimension.width = aWidth;
}
public int getHeight(){
	return fDimension.height;
}
public void setHeight(int aHeight){
	fDimension.height = aHeight;
}
public void setSize(IDimensionBeanProxy aBeanProxy){
	fDimension.setSize(new Dimension(aBeanProxy.getWidth(),aBeanProxy.getHeight()));
}
public void setSize(int width, int height){
	fDimension.width = width;
	fDimension.height = height;
}
}
