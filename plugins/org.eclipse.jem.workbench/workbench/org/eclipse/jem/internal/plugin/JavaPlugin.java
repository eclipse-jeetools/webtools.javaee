package org.eclipse.jem.internal.plugin;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaPlugin.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:33:53 $ 
 */

import org.eclipse.core.runtime.*;
import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.internal.core.EclipseLogMsgLogger;
import org.eclipse.jem.internal.core.MsgLogger;
import org.eclipse.jem.internal.java.impl.JavaRefFactoryImpl;
/**
 * This is a top-level class of the java plugin tool.
 * @see AbstractUIPlugin for additional information on UI plugins
 */

public class JavaPlugin extends Plugin {
	
	// Default instance of the receiver
	private static JavaPlugin inst;
	private MsgLogger msgLogger;
	private static final String NATURE_REGISTRATION_POINT = "com.ibm.etools.java.nature_registration"; //$NON-NLS-1$
	private static final String NATURE  = "nature"; //$NON-NLS-1$
	private static final String ID		= "id"; //$NON-NLS-1$
/**
 * Create the Java plugin and cache its default instance
 */
public JavaPlugin(IPluginDescriptor descriptor) {
	super(descriptor);
	inst = this;
}

/**
 * Get the plugin singleton.
 */
static public JavaPlugin getDefault() {
	return inst;
}

	
public MsgLogger getMsgLogger() {
	if (msgLogger == null)
		msgLogger = EclipseLogMsgLogger.createLogger(this);
	return msgLogger;
}

protected void readNatureRegistrationPoint() throws CoreException {
	// register Nature IDs for the J2EENatures
	IPluginRegistry r = Platform.getPluginRegistry();
	IConfigurationElement[] ce = r.getConfigurationElementsFor(NATURE_REGISTRATION_POINT);
	String natureId;
	for (int i=0; i<ce.length; i++) {
		if (ce[i].getName().equals(NATURE)) {
			natureId = ce[i].getAttribute(ID);
			if (natureId != null)
				AbstractJavaMOFNatureRuntime.registerNatureID(natureId);
		}
	}
}
public void startup() throws CoreException {
	JavaRefFactoryImpl.setReflectionAdapterFactoryClass(JavaJDOMAdapterFactory.class);
	readNatureRegistrationPoint();
}


}
