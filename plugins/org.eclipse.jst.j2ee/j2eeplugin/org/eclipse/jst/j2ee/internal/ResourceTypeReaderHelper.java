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
 * Created on 10-Oct-03
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

/**
 * @author sapnam
 * 
 * This helper class was created for ResourceRefTypeReader and ResourceEnvRefTypeReader to read all
 * valid types from extensions of each and to return a array of String to populate the Ref 'Type'
 * fields.
 */
public class ResourceTypeReaderHelper {
	private static String extensionPoint;
	private static String typeAttributeName;
	private static final String USAGE_TYPE = "type"; //$NON-NLS-1$
	private static final String USAGE_ATTRIBUTE_NAME = "usage"; //$NON-NLS-1$
	private static final String USAGE_COMMON = "Common"; //$NON-NLS-1$
	private static final String USAGE_EJB = "EJB Jar"; //$NON-NLS-1$
	private static final String SERVER_TARGET_IDS_ATTRIBUTE_NAME = "target-server-ids"; //$NON-NLS-1$
	private static final String SERVER_TARGET_IDS_SEPARATOR = ","; //$NON-NLS-1$
	private boolean isEJBJar;

	public static final int RESOURCE_REF = 0;
	public static final int RESOURCE_ENV_REF = 1;

	private static final String[] EXTENSION_POINTS = {"org.eclipse.jst.j2ee.resourceRefType", "org.eclipse.jst.j2ee.resourceEnvRefType"};//$NON-NLS-1$ //$NON-NLS-2$

	public static String[] getAllReferences(int refType, String serverTargetID, boolean isEJBJar, String[] baseItems) {
		String extensionPointLocal = EXTENSION_POINTS[refType];
		ResourceTypeReaderHelper reader = new ResourceTypeReaderHelper(extensionPointLocal, USAGE_TYPE, isEJBJar);
		String[] extendedItems = null == serverTargetID ? reader.getResTypes() : reader.getResTypes(serverTargetID);
		String[] allItems = null;
		if (null == extendedItems || 0 == extendedItems.length) {
			allItems = baseItems;
		} else {
			allItems = new String[baseItems.length + extendedItems.length];
			System.arraycopy(baseItems, 0, allItems, 0, baseItems.length);
			System.arraycopy(extendedItems, 0, allItems, baseItems.length, extendedItems.length);
		}
		Arrays.sort(allItems);
		return allItems;
	}


	private ResourceTypeReaderHelper(String extPoint, String typeAttName, boolean ejbJar) {
		extensionPoint = extPoint;
		typeAttributeName = typeAttName;
		isEJBJar = ejbJar;
	}

	private IExtensionPoint getExtensionPoint() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint exPoint = registry.getExtensionPoint(extensionPoint); //$NON-NLS-1$
		return exPoint;
	}

	private ArrayList processExtensions() {
		IExtension[] allExtensions = getExtensionPoint().getExtensions();
		ArrayList tempTypes = new ArrayList();
		for (int x = 0; x < allExtensions.length; ++x) {
			IExtension config = allExtensions[x];
			IConfigurationElement[] cElems = config.getConfigurationElements();
			for (int i = 0; i < cElems.length; i++) {
				if (getValidTypes(cElems[i]) != null) {
					tempTypes.add(getValidTypes(cElems[i]));
				}
			}
		}
		return tempTypes;
	}


	/**
	 * @return an array of string containing valid Resource Ref or Resource Env Ref types.
	 */
	public String[] getResTypes() {
		ArrayList types = processExtensions();
		String[] allTypes = new String[types.size()];
		for (int z = 0; z < types.size(); z++) {
			allTypes[z] = (String) types.get(z);
		}
		return allTypes;
	}

	private ArrayList processExtensions(String targetServer) {
		IExtension[] allExtensions = getExtensionPoint().getExtensions();
		ArrayList tempTypes = new ArrayList();
		for (int x = 0; x < allExtensions.length; ++x) {
			IExtension config = allExtensions[x];
			IConfigurationElement[] cElems = config.getConfigurationElements();
			for (int i = 0; i < cElems.length; i++) {
				if (getValidTypes(cElems[i], targetServer) != null) {
					tempTypes.add(getValidTypes(cElems[i], targetServer));
				}
			}
		}
		return tempTypes;
	}

	/**
	 * @param element
	 *            A configuration element.
	 * @return A valid Type
	 */
	private Object getValidTypes(IConfigurationElement element, String targetServer) {
		String elementServerIDs = element.getAttribute(SERVER_TARGET_IDS_ATTRIBUTE_NAME);
		if (null == elementServerIDs || isValidTargeted(elementServerIDs, targetServer)) {
			String usage = element.getAttribute(USAGE_ATTRIBUTE_NAME);
			if (usage.equals(USAGE_COMMON)) {
				return element.getAttribute(typeAttributeName);
			} else if (isEJBJar && usage.equals(USAGE_EJB)) {
				return element.getAttribute(typeAttributeName);
			}
		}
		return null;
	}

	/**
	 * @param element
	 *            A configuration element.
	 * @return A valid Type
	 */
	private Object getValidTypes(IConfigurationElement element) {
		return getValidTypes(element, null);
	}

	/**
	 * @return an array of string containing valid Resource Ref or Resource Env Ref types.
	 */
	public String[] getResTypes(String targetServer) {
		ArrayList types = processExtensions(targetServer);
		String[] allTypes = new String[types.size()];
		for (int z = 0; z < types.size(); z++) {
			allTypes[z] = (String) types.get(z);
		}
		return allTypes;
	}

	private boolean isValidTargeted(String attribute, String targetServerId) {
		if (attribute == null || targetServerId == null)
			return false;
		if (attribute.trim().equalsIgnoreCase(targetServerId.trim()))
			return true;
		if (attribute.indexOf(targetServerId.trim() + SERVER_TARGET_IDS_SEPARATOR) >= 0)
			return true;
		if (attribute.trim().length() > (targetServerId.trim().length() + 1) && attribute.substring(attribute.trim().length() - targetServerId.length()).equalsIgnoreCase(targetServerId.trim()))
			return true;
		return false;
	}

}