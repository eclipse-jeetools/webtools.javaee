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
package org.eclipse.jst.j2ee.internal.xml;



/**
 * This interface represents a catalog of all the tag names which occur in 
 * an EAR deployment descriptor. Interface shared by readers and 
 * writers to have access to common constants
 */
public interface EarDeploymentDescriptorXmlMapperI extends DeploymentDescriptorXmlMapperI {
	String ALT_DD = "alt-dd";//$NON-NLS-1$
	String APPLICATION = "application";//$NON-NLS-1$
	String CONTEXT_ROOT = "context-root";//$NON-NLS-1$
	String EJB = "ejb";//$NON-NLS-1$
	String JAVA = "java";//$NON-NLS-1$
	String MODULE = "module";//$NON-NLS-1$
	String WEB = "web";//$NON-NLS-1$
	String WEB_URI = "web-uri";//$NON-NLS-1$
	String CONNECTOR = "connector";//$NON-NLS-1$
}


