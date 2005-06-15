/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IExtensionRegistration.java,v $
 *  $Revision: 1.1 $  $Date: 2005/06/15 20:19:11 $ 
 */
package org.eclipse.jem.internal.proxy.core;
 

/**
 * Extensions point ("extensions" elements of the "org.eclipse.jem.proxy.contributors" extension point).
 * <p>
 * Implementers of this interface are called when the appropriate registry type for a given classPath/plugin is present.
 * It is used to register the registry type specific extensions.
 * @since 1.1.0
 */
public interface IExtensionRegistration {
	
	/**
	 * This will be called before any contributeToRegistry's have been called. Typically all that
	 * should be done in here is to register extension factories or constants. It is best if no
	 * actual proxy calls are made. This is because the registry does not yet have all of the 
	 * appropriate extensions registered and it would be possible to accidently reference a bean type
	 * that has not yet had its appropriate factory registered.
	 * <p>
	 * <b>Note:</b> This may be called more than once if used in more than one extension or if used in a plugin and a container path and both are found.
	 * Should take this into account and not register twice.
	 * @param baseRegistry
	 * 
	 * @since 1.1.0
	 */
	public void register(BaseProxyFactoryRegistry baseRegistry);

}
