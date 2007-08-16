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
/*
 * Created on Aug 5, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.xml;

/**
 * @author dfholttp
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface WebServicesDeploymentDescriptorXmlMapperI extends DeploymentDescriptorXmlMapperI{
	//1.3 specific WebServices DD
	public String COMP_SCOPED_REFS = "component-scoped-refs";//$NON-NLS-1$
	public String COMP_COMPONENT_NAME = "component-name";//$NON-NLS-1$
	public String WEB_SERVICES_CLIENT = "webservicesclient";//$NON-NLS-1$
	public String LOCALPART = "localpart";//$NON-NLS-1$
	public String NAMESPACEURI = "namespaceURI";//$NON-NLS-1$
}
