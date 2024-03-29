/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
package org.eclipse.jst.j2ee.internal;

/**
 * @author mdelder
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DefaultEJBModelExtenderProvider implements IEJBModelExtenderProvider {
    
	/**
	 * 
	 */
	public DefaultEJBModelExtenderProvider() {
		super();
		
	}
	
	
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.internal.core.moduleextension.helper.IEJBModelExtenderProvider#getEJBModuleExtension(java.lang.Object)
     */
    @Override
	public EjbModuleExtensionHelper getEJBModuleExtension(Object context) {
        return null;
    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.internal.core.moduleextension.helper.IEJBModelExtenderProvider#hasEJBModuleExtension(java.lang.Object)
     */
    @Override
	public boolean hasEJBModuleExtension(Object context) {
        return getEJBModuleExtension(context) != null;
    }
    
}
