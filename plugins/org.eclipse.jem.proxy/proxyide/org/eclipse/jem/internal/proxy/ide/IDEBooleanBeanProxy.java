package org.eclipse.jem.internal.proxy.ide;
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
 *  $RCSfile: IDEBooleanBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:36 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * IDE Implementation of IBooleanBeanProxy..
 * Creation date: (2/6/00 9:02:54 AM)
 * @author: Joe Winchester
 */
class IDEBooleanBeanProxy extends IDEObjectBeanProxy implements IBooleanBeanProxy {
	protected Boolean fBooleanValue;
/**
 * As well as storing the bean store the boolean value so that we can return the booleanValue faster
 * without the need for repeated re-casting. It is package protected because it is created in
 * special way, so no one else should create these.
 * @param aBean java.lang.Object
 */
IDEBooleanBeanProxy(IDEProxyFactoryRegistry aRegistry, Object aBean, IBeanTypeProxy aBeanTypeProxy) {
	super(aRegistry, aBean,aBeanTypeProxy);
	fBooleanValue = (Boolean)aBean;
}
public boolean booleanValue() {
	return fBooleanValue.booleanValue();
}
public Boolean getBooleanValue() {
	return fBooleanValue;
}
}




