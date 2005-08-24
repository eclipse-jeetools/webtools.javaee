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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: IREMMethodProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
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
