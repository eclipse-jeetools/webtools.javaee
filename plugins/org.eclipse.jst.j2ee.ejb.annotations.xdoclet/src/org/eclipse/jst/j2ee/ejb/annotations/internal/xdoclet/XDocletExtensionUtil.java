package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

public class XDocletExtensionUtil {

	public static XDocletRuntime[] getRuntimes() {
		IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
				"org.eclipse.jst.j2ee.ejb.annotations.xdoclet.xdocletRuntime").getExtensions();

		XDocletRuntime[] runtimes = new XDocletRuntime[extensions.length];
		for (int i = 0; i < extensions.length; i++) {
			runtimes[i] = new XDocletRuntime();

			IExtension extension = extensions[i];
			IConfigurationElement configurationElement = getRuntimeElement(extension);
			if (configurationElement != null) {
				runtimes[i].setVersion(configurationElement.getAttribute("xdoclet"));
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
		IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
				"org.eclipse.jst.j2ee.ejb.annotations.xdoclet.xdocletRuntime").getExtensions();

		for (int i = 0; i < extensions.length; i++) {
			XDocletRuntime runtime = new XDocletRuntime();

			IExtension extension = extensions[i];
			IConfigurationElement configurationElement = getRuntimeElement(extension);
			if (configurationElement != null) {
				if (versionID.equals(configurationElement.getAttribute("xdoclet"))) {
					runtime.setVersion(configurationElement.getAttribute("xdoclet"));
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

	public static IConfigurationElement[] getRuntimeLibraries(IExtension extension) {
		ArrayList arrayList = new ArrayList();
		IConfigurationElement[] elements = extension.getConfigurationElements();
		if (elements != null) {
			for (int j = 0; j < elements.length; j++) {
				IConfigurationElement element = elements[j];
				if ("RuntimeLib".equals(element.getName()))
					arrayList.add(element);
			}
		}
		return (IConfigurationElement[]) arrayList.toArray(new IConfigurationElement[arrayList.size()]);
	}
}
