/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
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
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.internal.runtime.Assert;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.ejb.plugin.EjbPlugin;
import org.eclispe.wst.common.framework.enablement.Identifiable;
import org.eclispe.wst.common.framework.enablement.IdentifiableComparator;
import org.eclispe.wst.common.internal.framework.enablement.EnablementManager;

import com.ibm.wtp.common.RegistryReader;
import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EJBCodegenHandlerExtensionReader extends RegistryReader {

	static EJBCodegenHandlerExtensionReader instance = null;

	protected SortedSet handlerExtensions = null;

	public static class Descriptor implements Identifiable {

		public static final String CODEGEN_EXTENSION = "codegenExtensionHandler"; //$NON-NLS-1$

		public static final String RUN = "run"; //$NON-NLS-1$

		public static final String ATT_ID = "id"; //$NON-NLS-1$

		public static final String ATT_CLASS = "class"; //$NON-NLS-1$

		private static int loadOrderCounter = 0;

		private IConfigurationElement element;

		private String id;

		private int loadOrder;

		private IEJBCodegenHandler instance;

		private boolean errorCondition = false;

		public Descriptor(IConfigurationElement element) {
			Assert.isLegal(CODEGEN_EXTENSION.equals(element.getName()), EJBCodeGenResourceHandler.getString("EJBCodegenHandlerExtensionReader_ERROR_0", new Object[]{CODEGEN_EXTENSION})); //$NON-NLS-1$
			this.element = element;
			init();
		}

		private void init() {
			this.id = this.element.getAttribute(ATT_ID);
			this.loadOrder = loadOrderCounter++;
		}

		/**
		 * @return Returns the id.
		 */
		public String getID() {
			return id;
		}

		/**
		 * @return Returns the loadOrder.
		 */
		public int getLoadOrder() {
			return loadOrder;
		}

		public IEJBCodegenHandler getInstance() {
			try {
				if (instance == null && !errorCondition)
					instance = (IEJBCodegenHandler) element.createExecutableExtension("run"); //$NON-NLS-1$
			} catch (Throwable e) {
				Logger.getLogger().logError(e);
				errorCondition = true;
			}
			return instance;
		}

		public String toString() {
			return new StringBuffer("CodegenDescriptor[id=\"").append(this.id).append("\", loadOrder=").append(this.loadOrder).append("]").toString(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
	}

	public EJBCodegenHandlerExtensionReader() {
		super(Platform.getPluginRegistry(), EjbPlugin.PLUGIN_ID, "EJBCodegenHandler"); //$NON-NLS-1$
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtension(IConfigurationElement newExtension) {
		getHandlerExtensions().add(new Descriptor(newExtension));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	public boolean readElement(IConfigurationElement element) {
		if (Descriptor.CODEGEN_EXTENSION.equals(element.getName())) {
			addExtension(element);
			return true;
		}
		return false;
	}

	/**
	 * @return the appropriate handler for the project based on priorities of those which are
	 *         available and enabled
	 */
	public IEJBCodegenHandler getEJBExtHandler(IProject project) {
		Descriptor descriptor;
		for (Iterator descriptorsItr = getHandlerExtensions().iterator(); descriptorsItr.hasNext();) {
			descriptor = (Descriptor) descriptorsItr.next();
			if (EnablementManager.INSTANCE.getIdentifier(descriptor.getID(), project).isEnabled())
				return descriptor.getInstance();
		}
		return null;
	}

	/**
	 * Gets the instance.
	 * 
	 * @return Returns a EJBCodegenHandlerExtensionReader
	 */
	public static EJBCodegenHandlerExtensionReader getInstance() {
		if (instance == null) {
			instance = new EJBCodegenHandlerExtensionReader();
			instance.readRegistry();
		}
		return instance;
	}

	/**
	 * @return Returns the handlerExtensions.
	 */
	protected SortedSet getHandlerExtensions() {
		if (handlerExtensions == null)
			handlerExtensions = new TreeSet(IdentifiableComparator.getInstance());
		return handlerExtensions;
	}
}