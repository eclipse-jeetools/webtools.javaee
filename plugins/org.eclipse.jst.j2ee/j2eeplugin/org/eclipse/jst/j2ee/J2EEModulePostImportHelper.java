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
 * Created on Jun 9, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.plugin.J2EEPluginResourceHandler;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class J2EEModulePostImportHelper {

	private static final String WEB = J2EEPluginResourceHandler.getString("J2EEModulePostImportHelper.0"); //$NON-NLS-1$
	private static final String EJB = J2EEPluginResourceHandler.getString("J2EEModulePostImportHelper.1"); //$NON-NLS-1$
	private static final String APP_CLIENT = J2EEPluginResourceHandler.getString("J2EEModulePostImportHelper.2"); //$NON-NLS-1$
	private static final String CONNECTOR = J2EEPluginResourceHandler.getString("J2EEModulePostImportHelper.3"); //$NON-NLS-1$

	private static IConfigurationElement[] webExtensions = null;
	private static IConfigurationElement[] ejbExtensions = null;
	private static IConfigurationElement[] appClientExtensions = null;
	private static IConfigurationElement[] connectorExtensions = null;

	private static boolean firstTimeLoading = true;

	public static void notifyWebExtensions(IProject project) {
		if (webExtensions == null) {
			loadConfiguration(WEB);
		}
		notifyExtensions(webExtensions, project);
	}

	public static void notifyEjbExtensions(IProject project) {
		if (ejbExtensions == null) {
			loadConfiguration(EJB);
		}
		notifyExtensions(ejbExtensions, project);
	}

	public static void notifyAppClientExtensions(IProject project) {
		if (appClientExtensions == null) {
			loadConfiguration(APP_CLIENT);
		}
		notifyExtensions(appClientExtensions, project);
	}

	public static void notifyConnectorExtensions(IProject project) {
		if (connectorExtensions == null) {
			loadConfiguration(CONNECTOR);
		}
		notifyExtensions(connectorExtensions, project);
	}

	private static void notifyExtensions(IConfigurationElement[] postImportElement, IProject project) {
		for (int i = 0; i < postImportElement.length; i++) {
			try {
				J2EEModulePostImportHandler postCreate = (J2EEModulePostImportHandler) postImportElement[i].createExecutableExtension("className"); //$NON-NLS-1$
				postCreate.moduleImported(project);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	private static void loadConfiguration(final String loadingModuleType) {
		boolean shouldLogErrors = firstTimeLoading;
		firstTimeLoading = false;

		J2EEPlugin plugin = J2EEPlugin.getPlugin();
		IExtension[] importExtensions = plugin.getDescriptor().getExtensionPoint("J2EEModulePostImport").getExtensions(); //$NON-NLS-1$

		ArrayList interestedExtensions = new ArrayList();
		for (int i = 0; i < importExtensions.length; i++) {
			IExtension extension = importExtensions[i];
			IConfigurationElement[] configElements = extension.getConfigurationElements();
			boolean isExtensionInterested = false;
			IConfigurationElement postImportElement = null;
			int moduleCount = 0;
			for (int j = 0; j < configElements.length; j++) {
				try {
					IConfigurationElement element = configElements[j];
					if (element.getName().equalsIgnoreCase("postImport")) { //$NON-NLS-1$
						postImportElement = element;
					} else if (element.getName().equalsIgnoreCase(("module"))) { //$NON-NLS-1$
						moduleCount++;
						if (!isExtensionInterested) {
							String moduleType = element.getAttribute("type"); //$NON-NLS-1$
							if (WEB == loadingModuleType && WEB.equalsIgnoreCase(moduleType)) {
								isExtensionInterested = true;
							} else if (EJB == loadingModuleType && EJB.equalsIgnoreCase(moduleType)) {
								isExtensionInterested = true;
							} else if (APP_CLIENT == loadingModuleType && APP_CLIENT.equalsIgnoreCase(moduleType)) {
								isExtensionInterested = true;
							} else if (CONNECTOR == loadingModuleType && CONNECTOR.equalsIgnoreCase(moduleType)) {
								isExtensionInterested = true;
							}
						}
					}
				} catch (Exception e) {
					if (shouldLogErrors) {
						e.printStackTrace();
					}
				}
			}

			//if no module types are defined the default is to listen to all of them.
			if (!isExtensionInterested && 0 == moduleCount) {
				isExtensionInterested = true;
			}

			if (isExtensionInterested) {
				try {
					//try instantiating the class before adding it to the list.
					postImportElement.createExecutableExtension("className"); //$NON-NLS-1$
					interestedExtensions.add(postImportElement);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		IConfigurationElement[] configElements = new IConfigurationElement[interestedExtensions.size()];
		for (int i = 0; i < configElements.length; i++) {
			configElements[i] = (IConfigurationElement) interestedExtensions.get(i);
		}
		if (WEB == loadingModuleType) {
			webExtensions = configElements;
		} else if (EJB == loadingModuleType) {
			ejbExtensions = configElements;
		} else if (APP_CLIENT == loadingModuleType) {
			appClientExtensions = configElements;
		} else if (CONNECTOR == loadingModuleType) {
			connectorExtensions = configElements;
		}
	}
}