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
 * Created on Sep 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EJBModelExtenderManager implements IEJBModelExtenderManager { 

    private IEJBModelExtenderProvider provider = new DefaultEJBModelExtenderProvider();
    
    /**
     * @deprecated Should use IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
     */
    public static EjbModuleExtensionHelper getEJBModuleExtension() {
        return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
    }

    public EjbModuleExtensionHelper getEJBModuleExtension(Object context) {
        return provider.getEJBModuleExtension(context);
    }
    
    public boolean hasEJBModuleExtension(Object context) {
        return getEJBModuleExtension(context) != null;
    } 
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.core.moduleextension.helper.IEJBModelExtenderManager#setProvider(org.eclipse.jst.j2ee.core.moduleextension.helper.IEJBModelExtenderProvider)
     */
    public void setProvider(IEJBModelExtenderProvider provider) {
        this.provider = provider;
    }
    
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.core.moduleextension.helper.IEJBModelExtenderManager#isProviderSet()
     */
    public boolean isProviderSet() {
        return !(this.provider instanceof DefaultEJBModelExtenderProvider);
    }
}
