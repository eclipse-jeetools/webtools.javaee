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
 *  $Revision: 1.2 $  $Date: 2004/02/04 21:25:37 $ 
 */

import java.io.OutputStream;

import org.eclipse.jem.internal.proxy.common.*;

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
				Object[] proxyParms = null;
				// If we have any parms then convert them to bean proxies
				if ( parms != null ) {
					proxyParms = new Object[parms.length];
					for ( int i=0;i<parms.length;i++){
						Object p = parms[i];
						proxyParms[i] = createNextParm(p);
					}
				}
				return fCallbackRegistry.vmCallback(callbackID,msgID,proxyParms);
			}

			private Object createNextParm(Object p) {
				if (!(p instanceof ICallbackHandler.TransmitableArray)) {
					return fBeanProxyFactory.createIDEBeanProxyWith(p);
				} else {
					Object[] array = ((ICallbackHandler.TransmitableArray) p).getArray();
					Object[] parm = new Object[array.length];
					for (int i = 0; i < array.length; i++) {
						parm[i] = createNextParm(array[i]);
					}
					return parm;
				}
			}
			
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.common.ICallbackHandler#callbackAsConstants(int, int, java.lang.Object)
			 */
			public Object callbackAsConstants(int callbackID, int msgID, Object parm) throws CommandException {
				return fCallbackRegistry.vmCallback(callbackID,msgID,parm);
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