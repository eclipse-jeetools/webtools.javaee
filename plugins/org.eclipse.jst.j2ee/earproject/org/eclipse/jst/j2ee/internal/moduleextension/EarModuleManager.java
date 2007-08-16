/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Sep 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.moduleextension;

import java.util.HashMap;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class EarModuleManager {
	static HashMap moduleExtensions = new HashMap();


	/**
	 * @return
	 */
	public static HashMap getModuleExtensions() {
		return moduleExtensions;
	}

	public static EarModuleExtension getModuleExtension(String key) {
		//Make sure the registry is loaded
		EarModuleExtensionRegistry.getInstance();
		return (EarModuleExtension) moduleExtensions.get(key);
	}

	/**
	 * @return
	 */
	public static EjbModuleExtension getEJBModuleExtension() {
		return (EjbModuleExtension) getModuleExtension(J2EEProjectUtilities.EJB);
	}

	/**
	 * @return
	 */
	public static JcaModuleExtension getJCAModuleExtension() {
		return (JcaModuleExtension) getModuleExtension(J2EEProjectUtilities.JCA);
	}

	/**
	 * @return
	 */
	public static WebModuleExtension getWebModuleExtension() {
		return (WebModuleExtension) getModuleExtension(J2EEProjectUtilities.DYNAMIC_WEB);
	}

	public static boolean hasEJBModuleExtension() {
		return (EjbModuleExtension) getModuleExtension(J2EEProjectUtilities.EJB) != null;
	}

	public static boolean hasJCAModuleExtension() {
		return (JcaModuleExtension) getModuleExtension(J2EEProjectUtilities.JCA) != null;
	}

	public static boolean hasWebModuleExtension() {
		return getModuleExtension(J2EEProjectUtilities.DYNAMIC_WEB) != null;
	}

	public static void registerModuleExtension(EarModuleExtension ext) {
		if (ext instanceof WebModuleExtension)
			moduleExtensions.put(J2EEProjectUtilities.DYNAMIC_WEB, ext);
		else if (ext instanceof EjbModuleExtension)
			moduleExtensions.put(J2EEProjectUtilities.EJB, ext);
		else if (ext instanceof JcaModuleExtension)
			moduleExtensions.put(J2EEProjectUtilities.JCA, ext);
		else
			moduleExtensions.put(J2EEProjectUtilities.ENTERPRISE_APPLICATION, ext);
	}

	public static void removeModuleExtension(String key) {
		moduleExtensions.remove(key);
	}
}
