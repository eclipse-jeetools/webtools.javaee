package org.eclipse.jem.internal.proxy.ide.awt;
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
 *  $RCSfile: IDEPointBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.ide.*;
import org.eclipse.jem.internal.proxy.awt.*;
import java.awt.Point;

public class IDEPointBeanProxy extends IDEObjectBeanProxy implements IPointBeanProxy {
	
	protected Point fPoint;

IDEPointBeanProxy(ProxyFactoryRegistry aRegistry,Object aPoint, IBeanTypeProxy aBeanTypeProxy){
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