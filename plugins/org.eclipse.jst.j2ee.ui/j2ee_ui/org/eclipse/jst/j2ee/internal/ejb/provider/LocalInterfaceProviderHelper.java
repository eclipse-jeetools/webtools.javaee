/***************************************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;


import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.graphics.Image;


public class LocalInterfaceProviderHelper extends J2EEJavaClassProviderHelper {

	private static Image image;

	/**
	 * Constructor for LocalInterfaceProviderHelper.
	 * 
	 * @param anEJB
	 */
	public LocalInterfaceProviderHelper(EnterpriseBean anEJB) {
		super(anEJB);
	}

	@Override
	public Image getImage() {
		if (image == null)
			image = createImage();
		return image;
	}

	/**
	 * @see J2EEJavaClassProviderHelper#getJavaClass()
	 */
	@Override
	public JavaClass getJavaClass() {
		return getEjb().getLocalInterface();
	}

	/**
	 * @see J2EEJavaClassProviderHelper#getOverlayKey()
	 */
	@Override
	protected String getOverlayKey() {
		return "local_interface_overlay_obj";//$NON-NLS-1$
	}

	/**
	 * @see J2EEJavaClassProviderHelper#getTypeString(String)
	 */
	@Override
	public String getTypeString(String className) {
		return J2EEUIMessages.getResourceString("Local_Interface_UI_", new Object[]{className}); //$NON-NLS-1$ = "Local Interface"
	}

}