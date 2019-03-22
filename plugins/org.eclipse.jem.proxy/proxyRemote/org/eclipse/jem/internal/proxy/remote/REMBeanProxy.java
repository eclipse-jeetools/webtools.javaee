/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*


 */


import org.eclipse.jem.internal.proxy.core.*;
/**
 * Standard implementation of IREMBeanProxy
 */

public class REMBeanProxy extends REMAbstractBeanProxy {
	private IBeanTypeProxy fType;

	protected REMBeanProxy(REMProxyFactoryRegistry aRegistry, Integer anID, IBeanTypeProxy aType){
		super(aRegistry, anID);
		fType = aType;
	}
	
	public IBeanTypeProxy getTypeProxy() {
		return fType;
	}
	
	public void release() {
		fType = null;
		super.release();
	}
}
