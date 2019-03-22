/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityXMIResourceImpl;


/**
 * @deprecated No longer required - change to extend CompatibilityXMIResourceImpl
 */
public class J2EEXMIResource extends CompatibilityXMIResourceImpl implements J2EEVersionResource{

	/**
	 * Constructor for J2EEXMIResource.
	 */
	public J2EEXMIResource() {
		super();
	}

	/**
	 * Constructor for J2EEXMIResource.
	 * @param uri
	 */
	public J2EEXMIResource(URI uri) {
		super(uri);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.J2EEVersionResource#getJ2EEVersionID()
	 */
	public int getJ2EEVersionID() {
		return J2EEVersionConstants.J2EE_1_3_ID;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.J2EEVersionResource#getModuleVersionID()
	 */
	public int getModuleVersionID() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.J2EEVersionResource#getVersionID()
	 */
	public int getVersionID() {
		return 0;
	}
}
