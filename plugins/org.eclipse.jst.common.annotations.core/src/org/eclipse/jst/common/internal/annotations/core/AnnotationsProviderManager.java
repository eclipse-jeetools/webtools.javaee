/*******************************************************************************
 * Copyright (c) 2006, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.annotations.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jem.util.logger.proxy.Logger;

public class AnnotationsProviderManager extends RegistryReader {
	
	public static final AnnotationsProviderManager INSTANCE = new AnnotationsProviderManager();
	
	static {
		INSTANCE.readRegistry();
	}

	public static class Descriptor {

		public static final String EXTENSION_PT_PLUGIN = "org.eclipse.jst.common.annotations.core"; //$NON-NLS-1$
		public static final String ANNOTATIONS_PROVIDER = "annotationsProvider"; //$NON-NLS-1$
		public static final String CLASSNAME = "className"; //$NON-NLS-1$
		
		private final IConfigurationElement configElement;

		public Descriptor(IConfigurationElement aConfigElement) {
			super();
			configElement = aConfigElement;
		}

		public IAnnotationsProvider createInstance() {
			IAnnotationsProvider instance = null;
			try {
				instance = (IAnnotationsProvider) configElement.createExecutableExtension(CLASSNAME);
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
			return instance;
		}
	}
	
	private List annotationsProviders;
	
	/**
	 * Default constructor
	 */
	public AnnotationsProviderManager() {
		super(Descriptor.EXTENSION_PT_PLUGIN, Descriptor.ANNOTATIONS_PROVIDER);
	}
	
	@Override
	public boolean readElement(IConfigurationElement element) {
		if (!element.getName().equals(Descriptor.ANNOTATIONS_PROVIDER))
			return false;
		addAnnotationsProvider(new Descriptor(element));
		return true;
	}
	
	/**
	 * @param descriptor
	 */
	protected void addAnnotationsProvider(Descriptor descriptor) {
		IAnnotationsProvider provider = descriptor.createInstance();
		if (provider != null)
			getAnnotationsProviders().add(provider);
	}
	
	/**
	 * @return Returns the annotationsProviders.
	 */
	public List getAnnotationsProviders() {
		if (annotationsProviders == null)
			annotationsProviders = new ArrayList();
		return annotationsProviders;
	}

}
