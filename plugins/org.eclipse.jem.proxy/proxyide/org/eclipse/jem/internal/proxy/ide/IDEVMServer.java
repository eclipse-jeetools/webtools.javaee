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
 *  $RCSfile: IDEVMServer.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.common.*;
import org.eclipse.jem.internal.proxy.core.*;
import java.io.*;

public class IDEVMServer implements IVMServer {
	
	IDECallbackRegistry fCallbackRegistry;
	IDEStandardBeanProxyFactory fBeanProxyFactory;
	
IDEVMServer(IDECallbackRegistry aCallbackRegistry){
	fCallbackRegistry = aCallbackRegistry;
	fBeanProxyFactory = (IDEStandardBeanProxyFactory)fCallbackRegistry.fProxyFactoryRegistry.getBeanProxyFactory();
	
}	
public Object doCallback(ICallbackRunnable aRunnable){
	try {
		return aRunnable.run(new ICallbackHandler(){
			public Object callbackWithParms(int callbackID, int msgID, Object[] parms){
				// We are running in the same IDE so just call the registry directly
				// although we must convert the parms to bean proxies
				IBeanProxy[] proxyParms = null;
				// If we have any parms then convert them to bean proxies
				if ( parms != null ) {
					proxyParms = new IBeanProxy[parms.length];
					for ( int i=0;i<parms.length;i++){
						proxyParms[i] = fBeanProxyFactory.createIDEBeanProxyWith(parms[i]);		
					}
				}
				return fCallbackRegistry.vmCallback(callbackID,msgID,proxyParms);
			}
		});
	} catch ( CommandException exc ) {
		return null;	
	}
}

public OutputStream requestStream(int callbackID, int msgID) throws CommandException {
	return fCallbackRegistry.requestStream(callbackID,msgID);
}
}