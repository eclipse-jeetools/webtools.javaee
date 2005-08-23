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
 * Created on Mar 27, 2003
 *
 */
package org.eclipse.jst.j2ee.ejb.internal.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.internal.emf.resource.FileNameResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;


/**
 * @author schacher
 */
public class EJBJarResourceFactory extends TranslatorResourceFactory {
	
	public static void registerDtds() {
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.EJBJAR_SYSTEMID_1_1, "ejb-jar_1_1.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.EJBJAR_SYSTEMID_2_0, "ejb-jar_2_0.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.EJB_JAR_SCHEMA_LOC_2_1, "ejb-jar_2_1.xsd"); //$NON-NLS-1$
	}

	/**
	 * @param aRendererFactory
	 */
	public EJBJarResourceFactory(RendererFactory aRendererFactory) {
		super(aRendererFactory);
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceFactory#createResource(org.eclipse.emf.common.util.URI, com.ibm.etools.emf2xml.Renderer)
	 */
	protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new EJBResourceImpl(uri, aRenderer);
	}
	/**
	 * Register myself with the Resource.Factory.Registry
	 */
	public static void registerWith(RendererFactory aRendererFactory) {
		J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(J2EEConstants.EJBJAR_DD_SHORT_NAME, new EJBJarResourceFactory(aRendererFactory));
	}
	public static Resource.Factory getRegisteredFactory() {
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(J2EEConstants.EJBJAR_DD_URI_OBJ);
	}
	
	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register() {
		registerWith(RendererFactory.getDefaultRendererFactory());
	}
	public static void register(FileNameResourceFactoryRegistry aRegistry) {
		aRegistry.registerLastFileSegment(J2EEConstants.EJBJAR_DD_SHORT_NAME, new EJBJarResourceFactory(RendererFactory.getDefaultRendererFactory()));
	}

}
