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
package org.eclipse.jst.j2ee.internal;

/**
 * The IEJBModelExtenderManager replaces the former J2EEModuleExtensionManager and acts
 * as a facade for a single IEJBModelExtenderProvider. The IEJBModelExtenderProvider will
 * provide the correct extension helper based on a given context (@see IEJBModelExtenderProvider).
 * 
 * @author mdelder
 *  
 */
public interface IEJBModelExtenderManager extends IEJBModelExtenderProvider {

    IEJBModelExtenderManager INSTANCE = new EJBModelExtenderManager();

    /**
     * 
     * @return true if the provider for which the manager is a facade has been set
     */
    boolean isProviderSet();

    /**
     * 
     * @param provider the manager will act as a facade to the given provider
     */
    void setProvider(IEJBModelExtenderProvider provider);

}
