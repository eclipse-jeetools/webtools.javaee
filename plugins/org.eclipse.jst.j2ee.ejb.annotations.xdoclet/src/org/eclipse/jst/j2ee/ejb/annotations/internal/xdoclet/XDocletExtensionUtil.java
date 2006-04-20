/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;

public class XDocletExtensionUtil {

	public static XDocletRuntime[] getRuntimes() {
		IExtension[] extensions = Platform
				.getExtensionRegistry()
				.getExtensionPoint(
						"org.eclipse.jst.j2ee.ejb.annotations.xdoclet.xdocletRuntime")
				.getExtensions();

		XDocletRuntime[] runtimes = new XDocletRuntime[extensions.length];
		for (int i = 0; i < extensions.length; i++) {
			runtimes[i] = new XDocletRuntime();

			IExtension extension = extensions[i];
			IConfigurationElement configurationElement = getRuntimeElement(extension);
			if (configurationElement != null) {
				runtimes[i].setVersion(configurationElement
						.getAttribute("xdoclet"));
				IConfigurationElement[] libs = getRuntimeLibraries(extension);
				String[] libsArray = new String[libs.length];
				for (int j = 0; j < libs.length; j++) {
					IConfigurationElement aLibrary = libs[j];
					libsArray[j] = aLibrary.getAttribute("location");
				}
				runtimes[i].setLibs(libsArray);
			}
		}
		return runtimes;
	}

	public static XDocletRuntime getRuntime(String versionID) {
		IExtension[] extensions = Platform
				.getExtensionRegistry()
				.getExtensionPoint(
						"org.eclipse.jst.j2ee.ejb.annotations.xdoclet.xdocletRuntime")
				.getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			XDocletRuntime runtime = new XDocletRuntime();

			IExtension extension = extensions[i];
			IConfigurationElement configurationElement = getRuntimeElement(extension);
			if (configurationElement != null) {
				if (versionID.equals(configurationElement
						.getAttribute("xdoclet"))) {
					runtime.setVersion(configurationElement
							.getAttribute("xdoclet"));
					IConfigurationElement[] libs = getRuntimeLibraries(extension);
					String[] libsArray = new String[libs.length];
					for (int j = 0; j < libs.length; j++) {
						IConfigurationElement aLibrary = libs[j];
						libsArray[j] = aLibrary.getAttribute("location");
					}
					runtime.setLibs(libsArray);
					return runtime;
				}
			}
		}
		return null;
	}

	public static IConfigurationElement getRuntimeElement(IExtension extension) {
		IConfigurationElement[] elements = extension.getConfigurationElements();
		if (elements != null) {
			for (int j = 0; j < elements.length; j++) {
				IConfigurationElement element = elements[j];
				if ("RuntimeVersion".equals(element.getName()))
					return element;
			}
		}
		return null;
	}

	public static IConfigurationElement[] getRuntimeLibraries(
			IExtension extension) {
		ArrayList arrayList = new ArrayList();
		IConfigurationElement[] elements = extension.getConfigurationElements();
		if (elements != null) {
			for (int j = 0; j < elements.length; j++) {
				IConfigurationElement element = elements[j];
				if ("RuntimeLib".equals(element.getName()))
					arrayList.add(element);
			}
		}
		return (IConfigurationElement[]) arrayList
				.toArray(new IConfigurationElement[arrayList.size()]);
	}

	public static String getRuntimeTypeAnnotations(IEnterpriseBean beanModel) {

		IProject project = (IProject) beanModel.getDataModel().getProperty(
				IEnterpriseBeanClassDataModelProperties.PROJECT);

		if (!J2EEProjectUtilities.isEJBProject(project))
			return "";

		IExtension[] extensions = Platform
				.getExtensionRegistry()
				.getExtensionPoint(
						"org.eclipse.jst.j2ee.ejb.annotations.xdoclet.ejbDocletTaskProvider")
				.getExtensions();
		StringBuffer annotations = new StringBuffer(512);
		for (int i = 0; extensions != null && i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension
					.getConfigurationElements();
			if (elements == null)
				continue;
			try {
				XDocletPreferenceStore preferenceStore = new XDocletPreferenceStore(project);
				if (! preferenceStore.getBooleanProperty(elements[0].getAttribute("id") + ".defaultSelection")) 
					continue;	
				for (int j = 0; j < elements.length; j++) {
					IConfigurationElement element = elements[j];
					if ("AnnotationProvider".equals(element.getName())) {

						String pluginDescriptor = element
								.getDeclaringExtension().getContributor()
								.getName();

						org.osgi.framework.Bundle bundle = Platform
						.getBundle(pluginDescriptor);

						
						if (isRuntimeAnnotationApplicable(beanModel, element)) {
							Class c = bundle.loadClass(element
									.getAttribute("class"));

							if (c != null) {
								IXDocletRuntimeAnnotation annotationProvider = (IXDocletRuntimeAnnotation) c
										.newInstance();
								annotationProvider.setPreferenceStore(preferenceStore);
								annotations
										.append(annotationProvider
												.getTypeAnnotations(project,
														beanModel));
								annotations.append("\n");

							}
						}
					}
				}

			} catch (Exception e) {
				Logger.logException(e);
			}
		}
		return annotations.toString();
	}

	private static boolean isRuntimeAnnotationApplicable(IEnterpriseBean beanModel, IConfigurationElement element) {
		boolean include = Boolean.valueOf(
				element.getAttribute("include")).booleanValue();
		if(!include)
			return false;
		String type = element.getAttribute("type");
		boolean generate = false;
		if (IXDocletRuntimeAnnotation.entity.equals(type)
				&& beanModel.getEnterpriseBean().isEntity())
			generate = true;
		if (IXDocletRuntimeAnnotation.session.equals(type)
				&& beanModel.getEnterpriseBean().isSession())
			generate = true;
		if (IXDocletRuntimeAnnotation.mdb.equals(type)
				&& beanModel.getEnterpriseBean()
						.isMessageDriven())
			generate = true;
		return generate;
	}

}
