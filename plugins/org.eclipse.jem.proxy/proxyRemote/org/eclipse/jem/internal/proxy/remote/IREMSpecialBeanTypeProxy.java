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

import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
/**
 * This is a special interface for allowing abstract
 * types to create the correct subclass types.
 * Only ones that use REMAnAbstractBeanTypeProxy
 * should implement this.
 */
public interface IREMSpecialBeanTypeProxy {
	
	/**
	 * Called by REMAnAbstractBeanTypeProxy to create a subclass of it.
	 * This allows correct types to be created depending upon the
	 * main super type.
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract, IBeanTypeProxy superType);
}
