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
package org.eclipse.jem.internal.proxy.ide.awt;
/*
 *  $RCSfile: IDEDimensionBeanProxy.java,v $
 *  $Revision: 1.2.4.1 $  $Date: 2004/06/24 18:19:03 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.ide.*;
import org.eclipse.jem.internal.proxy.awt.*;
import java.awt.Dimension;

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
