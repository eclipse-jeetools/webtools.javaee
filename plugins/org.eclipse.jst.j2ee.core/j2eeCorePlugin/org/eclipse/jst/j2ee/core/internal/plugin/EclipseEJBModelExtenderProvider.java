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
 * Created on Oct 29, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.core.internal.plugin;

import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.internal.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderProvider;
import org.eclispe.wst.common.frameworks.internal.enablement.Identifiable;
import org.eclispe.wst.common.frameworks.internal.enablement.IdentifiableComparator;

/**
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EclipseEJBModelExtenderProvider extends RegistryReader implements IEJBModelExtenderProvider {

    private SortedSet extensionHelpers;
    
    /*
     * static final String EXTENSION_NAME = "e"; //$NON-NLS-1$ static final
     * String ELEMENT_J2EEMODULE_EXTENSION = "j2eeModuleExtensionHelper";
     * //$NON-NLS-1$ static final String MODULE_EXTENSION_CLASS =
     * "extensionClass"; //$NON-NLS-1$
     */

    private static EclipseEJBModelExtenderProvider INSTANCE = null;

    public static class Descriptor implements Identifiable {

        public static final String EJB_MODEL_EXTENDER = "ejbModelExtender"; //$NON-NLS-1$

        public static final String ATT_ID = "id"; //$NON-NLS-1$
 
        public static final String ATT_EJB_EXTENSION_CLASS = "extensionClass"; //$NON-NLS-1$

        private static int loadOrderCounter = 0;

        private final String id;

        private final int loadOrder;

        private IConfigurationElement element;

        private EjbModuleExtensionHelper instance;

        public Descriptor(IConfigurationElement element) {
            Assert.isLegal(EJB_MODEL_EXTENDER.equals(element.getName()),
                    "Invalid IConfigurationElement used to create EclipseEJBModelExtenderProvider.Descriptor."); //$NON-NLS-1$
            this.element = element;
            this.id = this.element.getAttribute(ATT_ID);
            this.loadOrder = loadOrderCounter++;
        }

        public String getID() {
            return this.id;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.wst.common.frameworks.internal.enablement.Identifiable#getLoadOrder()
         */
        public int getLoadOrder() {
            return loadOrder;
        }

        public EjbModuleExtensionHelper getInstance() {
            try {
                if (instance == null)
                        instance = (EjbModuleExtensionHelper) element
                                .createExecutableExtension(ATT_EJB_EXTENSION_CLASS);
            } catch (CoreException e) {
                Logger.getLogger().logError(e);
            }
            return instance;
        }
    }

    protected EclipseEJBModelExtenderProvider() {
        super(J2EECorePlugin.PLUGIN_ID, Descriptor.EJB_MODEL_EXTENDER);
    }

    public static EclipseEJBModelExtenderProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EclipseEJBModelExtenderProvider();
            INSTANCE.readRegistry();
        }
        return INSTANCE;
    }

    /**
     * readElement() - parse and deal w/ an extension like: <earModuleExtension
     * extensionClass = "com.ibm.etools.web.plugin.WebModuleExtensionImpl"/>
     */
    public boolean readElement(IConfigurationElement element) {
        if (!element.getName().equals(Descriptor.EJB_MODEL_EXTENDER)) return false;

        addModuleExtension(new Descriptor(element));
        return true;
    }

    /**
     * @param descriptor
     */
    private void addModuleExtension(Descriptor descriptor) {
        getExtensionHelpers().add(descriptor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.j2ee.internal.core.moduleextension.helper.IEJBModelExtenderProvider#getEJBModuleExtension(java.lang.Object)
     */
    public EjbModuleExtensionHelper getEJBModuleExtension(Object context) {
        //IProject project = null; // TODO MDE Get the project using Project Utilities (add depend to emf.workbench ?)
    	if (getExtensionHelpers().isEmpty())
    		return null;
        Descriptor descriptor = (Descriptor) getExtensionHelpers().first();
        if(descriptor != null)
            return descriptor.getInstance();
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.j2ee.internal.core.moduleextension.helper.IEJBModelExtenderProvider#hasEJBModuleExtension(java.lang.Object)
     */
    public boolean hasEJBModuleExtension(Object context) {
        return false;
    }

    /**
     * @return Returns the extensionHelpers.
     */
    protected SortedSet getExtensionHelpers() { 
        if (extensionHelpers == null) extensionHelpers = new TreeSet(IdentifiableComparator.getInstance());
        return extensionHelpers;
    }
}
