/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.emitter;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

/**
 * @author naci
 */
public class EmitterUtilities {

	public static void addAnnotationBuilderToProject(IConfigurationElement emitter,
			IProject targetProject) throws CoreException {
		String builderId = emitter.getAttribute("builderId");
		ProjectUtilities.addToBuildSpec(emitter.getNamespace()+ "." + builderId, targetProject);
		
	}

	
	public static IConfigurationElement findEmitter(String id)
	{
		
		IConfigurationElement configurationElement[] = getAnnotationExtensions();
		for (int i = 0; i < configurationElement.length; i++) {
			IConfigurationElement element = configurationElement[i];
			String emitterId = element.getAttribute("name");
			if("emitter".equals(element.getName()) && emitterId != null && emitterId.equals(id))
				return element;
		}
		return null;
	}
	
	public static IConfigurationElement[] getAnnotationExtensions()
	{
		IConfigurationElement[] configurationElements = Platform
				.getExtensionRegistry()
				.getConfigurationElementsFor(
						"org.eclipse.jst.j2ee.ejb.annotations.emitter.template");
		return configurationElements;
	}
	

}
