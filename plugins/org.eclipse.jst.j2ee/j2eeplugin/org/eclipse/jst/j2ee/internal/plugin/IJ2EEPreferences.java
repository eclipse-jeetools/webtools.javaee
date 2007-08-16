/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;


public interface IJ2EEPreferences {

	String PREF_J2EEWEBCONTENT = "org.eclipse.jst.j2ee.internal.preference.j2eeWebContentName"; //$NON-NLS-1$
	String PREF_STATICWEBCONTENT = "org.eclipse.jst.j2ee.internal.preference.staticWebContentName"; //$NON-NLS-1$
	String PREF_JAVASOURCE = "org.eclipse.jst.j2ee.internal.preference.javaSourceName"; //$NON-NLS-1$
	String DEFAULT_J2EEWEBCONTENT = "WebContent"; //$NON-NLS-1$
	String DEFAULT_STATICWEBCONTENT = "WebContent"; //$NON-NLS-1$
	String DEFAULT_JAVASOURCE = "JavaSource"; //$NON-NLS-1$
	String SERVER_TARGET_SUPPORT = "org.eclipse.jst.j2ee.internal.preference.servertargetsupport"; //$NON-NLS-1$


}