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
 *  $RCSfile: IDERectangleBeanProxy.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 22:57:26 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.ide.*;
import org.eclipse.jem.internal.proxy.awt.*;
import java.awt.Rectangle;

public class IDERectangleBeanProxy extends IDEObjectBeanProxy implements IRectangleBeanProxy {
	
	protected Rectangle fRectangle;

IDERectangleBeanProxy(IDEProxyFactoryRegistry aRegistry,Object aRectangle, IBeanTypeProxy aBeanTypeProxy){
	super(aRegistry,aRectangle,aBeanTypeProxy);
	fRectangle = (Rectangle)aRectangle;
}
public int getX(){
	return fRectangle.x;
}
public void setX(int anX){
	fRectangle.x = anX;
}
public int getY(){
	return fRectangle.y;
}
public void setY(int aY){
	fRectangle.y = aY;
}
public int getHeight(){
	return fRectangle.height;
}
public void setHeight(int aHeight){
	fRectangle.height = aHeight;
}
public void setWidth(int aWidth){
	fRectangle.width = aWidth;
}
public int getWidth(){
	return fRectangle.width;
}
public void setBounds(IRectangleBeanProxy aBeanProxy){
	fRectangle.setBounds(aBeanProxy.getX(),aBeanProxy.getY(),aBeanProxy.getWidth(),aBeanProxy.getHeight());
}
public void setBounds(int x, int y, int width, int height){
	fRectangle.setBounds(x,y,width,height);
}
public void setSize(IDimensionBeanProxy aBeanProxy){
	fRectangle.setSize(aBeanProxy.getWidth(),aBeanProxy.getWidth());
}
public void setLocation(IPointBeanProxy aBeanProxy){
	fRectangle.setLocation(aBeanProxy.getX(),aBeanProxy.getY());
}
public void setLocation(int x, int y){
	fRectangle.setLocation(x,y);
}
public void setSize(int width, int height){
	fRectangle.setSize(width,height);
}
}
