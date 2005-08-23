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
package org.eclipse.jst.j2ee.ejb.internal.util;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class ConvertedEJBAdapter extends AdapterImpl {

	public static final String ADAPTER_KEY = "ConvertedEJBAdpater";  //$NON-NLS-1$
	public static final int VERSION_1_0 = 1;
	
	protected int version = -1;
	protected boolean readOnly = false;
	
	
	/**
	 * Constructor for ConvertedEJBAdapter.
	 */
	public ConvertedEJBAdapter() {
		super();
	}

	/**
	 * Gets the version.  This will be one of the static constants.
	 * @return Returns a int
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version.  This should be one of the static constants.
	 * @param version The version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Gets the readOnly.
	 * @return Returns a boolean
	 */
	public boolean isReadOnly() {
		return readOnly;
	}

	/**
	 * Sets the readOnly.
	 * @param readOnly The readOnly to set
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
	public boolean isFromVersion1_0() {
		return version == VERSION_1_0;
	}

}

