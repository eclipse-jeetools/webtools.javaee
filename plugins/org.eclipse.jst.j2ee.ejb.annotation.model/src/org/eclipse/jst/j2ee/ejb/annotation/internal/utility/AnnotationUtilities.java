/**
 * 
 */
package org.eclipse.jst.j2ee.ejb.annotation.internal.utility;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.AnnotationPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IAnnotationProvider;

/**
 * @author naci
 */
public class AnnotationUtilities {

	public static void addAnnotationBuilderToProject(IConfigurationElement emitter,
			IProject targetProject) throws CoreException {
		String builderId = emitter.getAttribute("builderId");
		ProjectUtilities.addToBuildSpec(emitter.getNamespace()+ "." + builderId, targetProject);
		
	}

	public static IConfigurationElement getPreferredAnnotationProvider()
	{
		String providerId = AnnotationPreferenceStore.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER);
		return findAnnotationProviderConfigurationByName(providerId);
	}
	public static IConfigurationElement getPreferredAnnotationEmitter()
	{
		String providerId = AnnotationPreferenceStore.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER);
		IConfigurationElement provider =  findAnnotationProviderConfigurationByName(providerId);
		return findAnnotationEmitterByName(provider.getAttribute("emitter"));
	}

	public static IConfigurationElement findAnnotationProviderConfigurationByName(String id)
	{
		
		IConfigurationElement configurationElement[] = getAnnotationExtensions();
		for (int i = 0; i < configurationElement.length; i++) {
			IConfigurationElement element = configurationElement[i];
			String emitterId = element.getAttribute("name");
			if("provider".equals(element.getName()) && emitterId != null && emitterId.equals(id))
				return element;
		}
		return null;
	}
	
	public static IAnnotationProvider findAnnotationProviderByName(String id) throws InvalidRegistryObjectException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		
		IConfigurationElement configurationElement[] = getAnnotationExtensions();
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
	
	public static IConfigurationElement findAnnotationEmitterByName(String id)
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

	public static String[] getProviderNames() {
		IConfigurationElement configurationElement[] = getAnnotationExtensions();
		ArrayList names = new ArrayList();
		for (int i = 0; i < configurationElement.length; i++) {
			IConfigurationElement element = configurationElement[i];
			if("provider".equals(element.getName()))
				names.add(element.getAttribute("name"));
		}
		return (String [])names.toArray(new String[names.size()]);
	}

}
