/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
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
 *  $RCSfile: IDEPointBeanProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/05/11 19:01:12 $ 
 */

import java.awt.Point;

import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IPointBeanProxy;
import org.eclipse.jem.internal.proxy.ide.IDEObjectBeanProxy;
import org.eclipse.jem.internal.proxy.ide.IDEProxyFactoryRegistry;

public class IDEPointBeanProxy extends IDEObjectBeanProxy implements IPointBeanProxy {
	
	protected Point fPoint;

IDEPointBeanProxy(IDEProxyFactoryRegistry aRegistry,Object aPoint, IBeanTypeProxy aBeanTypeProxy){
	super(aRegistry,aPoint,aBeanTypeProxy);
	fPoint = (Point)aPoint;
}
public int getX(){
	return fPoint.x;
}
public void setX(int anX){
	fPoint.x = anX;
}
public int getY(){
	return fPoint.y;
}
public void setY(int aY){
	fPoint.y = aY;
}
public void setLocation(IPointBeanProxy aBeanProxy){
	fPoint.setLocation(new Point(aBeanProxy.getX(),aBeanProxy.getY()));
}
public void setLocation(int anX, int aY){
	fPoint.x = anX;
	fPoint.y = aY;
}
}
