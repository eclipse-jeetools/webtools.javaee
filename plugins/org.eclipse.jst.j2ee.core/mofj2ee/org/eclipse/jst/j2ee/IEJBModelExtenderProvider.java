/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 25, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee;

/**
 * @author mdelder
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface IEJBModelExtenderProvider {

    /**
     * 
     * @param context a supplied object to determine the context (e.g. IProject if available)
     * @return the appropriate EjbModuleExtensionHelper or <b>null</b> if none exists
     */
    EjbModuleExtensionHelper getEJBModuleExtension(Object context);

    /**
     * 
     * @param context a supplied object to determine the context (e.g. IProject if available)
     * @return true if the given context has an extension 
     */
    boolean hasEJBModuleExtension(Object context);

}
