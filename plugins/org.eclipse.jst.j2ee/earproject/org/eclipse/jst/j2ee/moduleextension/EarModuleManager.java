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
 * Created on Sep 19, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.moduleextension;

import java.util.HashMap;

import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;


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
		return (EjbModuleExtension) getModuleExtension(IEJBNatureConstants.NATURE_ID);
	}

	/**
	 * @return
	 */
	public static JcaModuleExtension getJCAModuleExtension() {
		return (JcaModuleExtension) getModuleExtension(IConnectorNatureConstants.NATURE_ID);
	}

	/**
	 * @return
	 */
	public static WebModuleExtension getWebModuleExtension() {
		return (WebModuleExtension) getModuleExtension(IWebNatureConstants.J2EE_NATURE_ID);
	}

	public static boolean hasEJBModuleExtension() {
		return (EjbModuleExtension) getModuleExtension(IEJBNatureConstants.NATURE_ID) != null;
	}

	public static boolean hasJCAModuleExtension() {
		return (JcaModuleExtension) getModuleExtension(IConnectorNatureConstants.NATURE_ID) != null;
	}

	public static boolean hasWebModuleExtension() {
		return (WebModuleExtension) getModuleExtension(IWebNatureConstants.J2EE_NATURE_ID) != null;
	}

	public static void registerModuleExtension(EarModuleExtension ext) {
		moduleExtensions.put(ext.getNatureID(), ext);
	}

	public static void removeModuleExtension(String key) {
		moduleExtensions.remove(key);
	}
}