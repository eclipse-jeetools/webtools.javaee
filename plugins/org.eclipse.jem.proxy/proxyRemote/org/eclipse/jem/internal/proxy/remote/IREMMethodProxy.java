package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: IREMMethodProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
/**
 * This interface for REMMethodProxy allows the invokation where
 * the parms are a mixture of constant objects (i.e. can be sent
 * as values to the client) and IBeanProxies. It is only to be
 * used by other factories that want to invoke methods where
 * some of the parms are constants so that there is no need
 * create IBeanProxies for them, e.g. String. This should be
 * used with extreme care.
 */

public interface IREMMethodProxy extends IMethodProxy, IREMBeanProxy {
	public IBeanProxy invokeWithParms(IBeanProxy subject, Object[] parms) throws ThrowableProxy;
	
		

}