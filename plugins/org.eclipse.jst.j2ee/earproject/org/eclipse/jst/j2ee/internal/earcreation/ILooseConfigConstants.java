/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;


import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseConfigRegister;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


public interface ILooseConfigConstants {
	/**
	 * The short name of the loose configuration file; will most often need to use
	 * {@link #CONFIG_PATH}
	 */
	String CONFIG_FILE_NAME = "looseconfig.xmi"; //$NON-NLS-1$
	/** The absolute path to the loose configuration file */
	String CONFIG_PATH = J2EEPlugin.getDefault().getStateLocation().append(CONFIG_FILE_NAME).toOSString();
	/** The property name used by WebSphere to lookup the loose configuration location to load */
	String WAS_PROPERTY = LooseConfigRegister.LOOSE_CONFIG_PROPERTY;

}