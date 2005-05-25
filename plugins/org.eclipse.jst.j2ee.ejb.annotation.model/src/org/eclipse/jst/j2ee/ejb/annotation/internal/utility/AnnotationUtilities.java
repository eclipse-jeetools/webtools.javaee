/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.utility;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IAnnotationProvider;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IEJBGenerator;

/**
 * @author naci
 */
public class AnnotationUtilities {



	public static IConfigurationElement findAnnotationProviderConfigurationByName(String id)
	{
		
		IConfigurationElement configurationElement[] = getGeneratorExtensions();
		for (int i = 0; i < configurationElement.length; i++) {
			IConfigurationElement element = configurationElement[i];
			String providerID = element.getAttribute("name");
			if("provider".equals(element.getName()) && providerID != null && providerID.equals(id))
				return element;
		}
		return null;
	}
	
	public static IAnnotationProvider findAnnotationProviderByName(String id) throws InvalidRegistryObjectException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		
		IConfigurationElement configurationElement[] = getGeneratorExtensions();
		for (int i = 0; i < configurationElement.length; i++) {
			IConfigurationElement element = configurationElement[i];
			String emitterId = element.getAttribute("name");
			if("provider".equals(element.getName()) && emitterId != null && emitterId.equals(id)){
				String pluginDescriptor = element.getDeclaringExtension().getNamespace();

				org.osgi.framework.Bundle bundle = Platform.getBundle(pluginDescriptor);
				Class c = bundle.loadClass(element.getAttribute("class"));
				IAnnotationProvider provider = (IAnnotationProvider) c.newInstance();
				
				return provider;
			}
		}
		return null;
	}
	
	public static IEJBGenerator findEjbGeneratorByName(String id) throws InvalidRegistryObjectException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		
		IConfigurationElement configurationElement[] = getGeneratorExtensions();
		for (int i = 0; i < configurationElement.length; i++) {
			IConfigurationElement element = configurationElement[i];
			String generatorID = element.getAttribute("name");
			if("ejbGenerator".equals(element.getName()) && generatorID != null && generatorID.equals(id)){
				String pluginDescriptor = element.getDeclaringExtension().getNamespace();

				org.osgi.framework.Bundle bundle = Platform.getBundle(pluginDescriptor);
				Class c = bundle.loadClass(element.getAttribute("class"));
				IEJBGenerator provider = (IEJBGenerator) c.newInstance();
				
				return provider;
			}
		}
		return null;
	}
	

	
	public static IConfigurationElement[] getGeneratorExtensions()
	{
		IConfigurationElement[] configurationElements = Platform
				.getExtensionRegistry()
				.getConfigurationElementsFor(
						"org.eclipse.jst.j2ee.ejb.annotation.model.ejbGenerator");
		return configurationElements;
	}

	public static String[] getProviderNames() {
		IConfigurationElement configurationElement[] = getGeneratorExtensions();
		ArrayList names = new ArrayList();
		for (int i = 0; i < configurationElement.length; i++) {
			IConfigurationElement element = configurationElement[i];
			if("provider".equals(element.getName()))
				names.add(element.getAttribute("name"));
		}
		return (String [])names.toArray(new String[names.size()]);
	}

}
