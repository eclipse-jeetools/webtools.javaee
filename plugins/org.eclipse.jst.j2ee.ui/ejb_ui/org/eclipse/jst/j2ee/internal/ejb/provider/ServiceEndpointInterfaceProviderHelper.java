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
 * Created on Mar 19, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.provider;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.graphics.Image;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class ServiceEndpointInterfaceProviderHelper extends J2EEJavaClassProviderHelper {
	private static Image image;

	/**
	 * @param anEJB
	 */
	public ServiceEndpointInterfaceProviderHelper(EnterpriseBean anEJB) {
		super(anEJB);
	}

	public Image getImage() {
		if (image == null)
			image = createImage();
		return image;
	}

	/**
	 * Insert the method's description here. Creation date: (7/11/2001 1:53:49 PM)
	 * 
	 * @return org.eclipse.jem.internal.java.JavaClass
	 */
	public JavaClass getJavaClass() {
		return ((Session) getEjb()).getServiceEndpoint();
	}

	protected String getOverlayKey() {
		//TODO: DFH we need an icon
		return "";//$NON-NLS-1$
	}

	public String getTypeString(String className) {
		return J2EEUIMessages.getResourceString("Service_Endpoint_Interface_UI_", new Object[]{className}); //$NON-NLS-1$ = "Home Interface"
	}
}