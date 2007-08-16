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
package org.eclipse.jst.j2ee.internal.ejb.provider;


import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.graphics.Image;


/**
 * Insert the type's description here. Creation date: (6/20/2001 11:23:30 PM)
 * 
 * @author: Administrator
 */
public class HomeInterfaceProviderHelper extends J2EEJavaClassProviderHelper {
	private static Image image;

	/**
	 * HomeInterfaceProviderHelper constructor comment.
	 * 
	 * @param cls
	 *            org.eclipse.jem.internal.java.JavaClass
	 */
	public HomeInterfaceProviderHelper(EnterpriseBean anEJB) {
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
		return getEjb().getHomeInterface();
	}

	protected String getOverlayKey() {
		return "home_interface_overlay_obj";//$NON-NLS-1$
	}

	public String getTypeString(String className) {
		return J2EEUIMessages.getResourceString("Home_Interface_UI_", new Object[]{className}); //$NON-NLS-1$ = "Home Interface"
	}
}