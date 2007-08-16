/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;


public interface IEJBValidatorConstants {
	// Name of .properties file
	public static final String BUNDLE_NAME = "ejbvalidator"; //$NON-NLS-1$
	
	// Name of logger
	public static final String LOGNAME = "EJBValidatorLog"; //$NON-NLS-1$
	
	// Status constants which do not require a message prefix (e.g. ABCD0000E)
	public static final String STATUS_VALIDATING = "STATUS_VALIDATING"; //$NON-NLS-1$
	
	public static final String NULL_PRIMARY_KEY = "<primary-key>"; // when a key class name needs to be displayed, but that class is null, display this instead //$NON-NLS-1$
	public static final String NULL_BEAN_CLASS = "<ejb-class>"; // when a bean class name needs to be displayed, but that class is null, display this instead //$NON-NLS-1$
	public static final String NULL_LOCAL_HOME = "<local-home>"; // when a local-home class name needs to be displayed, but that class is null, display this instead //$NON-NLS-1$
	public static final String NULL_LOCAL = "<local>"; // when a local class name needs to be displayed, but that class is null, display this instead //$NON-NLS-1$
	public static final String NULL_HOME = "<home>"; // when a home class name needs to be displayed, but that class is null, display this instead //$NON-NLS-1$
	public static final String NULL_REMOTE = "<remote>"; // when a remote class name needs to be displayed, but that class is null, display this instead //$NON-NLS-1$
	
	// We use a string here because this interface is shipped with WAS, but
	// J2EECorePlugin is not. Therefore having the J2EECorePlugin.PLUGIN_ID 
	// dependency works fine in the tooling env, but will result in NoClassDefFound in WAS	
	public static final String J2EE_CORE_PLUGIN = "org.eclipse.jst.j2ee.internal.core"; // $NON-NLS-1$ //$NON-NLS-1$
}
